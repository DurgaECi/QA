package DriverPack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import ComponentsPack.TestAMFunctions;
import ComponentsPack.TestFunctions;
import ExcelPack.TestData;
import ExcelPack.TestExcelError;
import ReportPack.TestReport;
import SupportLib.TestBrowser;

/**
 * @author - Durga Suresh
 * Description - Driver script of the framework, which will drive the test execution.
 *
 */
@SuppressWarnings("all")
public class TestExecute {
	
	private TestData data;	
	private TestFunctions function;	
	private TestAMFunctions aMFunction;
	private WebDriver driver;
	private TestExcelError handler = new TestExcelError();	
	private ExtentReports report;
	private ExtentTest logger ;
	private TestConfig value = new TestConfig();
	private TestReport log = new TestReport();
	public TestBrowser browser = new TestBrowser();
	

	
	protected TestData getTestData() {		
		if (data == null)
			try {
				data = new TestData(value.filePath, value.fileName, "TestRunner");						
				}catch (Exception e) {				
				e.printStackTrace();
				}return data;		
	}	
		
	private ExtentReports setReport() {			
			if (report == null)
				try {
					report = new ExtentReports(value.reportPathGen());					
					}catch (Exception e) {
					e.printStackTrace();
				}return report;		
	}
	
	public static void main(String[] args)  
	 {		
		TestExecute Test = new TestExecute();
		Test.validateExcel();	
		Test.executeAllTestCases();			
		Test.tearDown();		
	 }
	
	private void validateExcel() {
		
		try {
			//To find if duplicate Test case IDs are entered in Test Case sheet
			handler.validateDuplicateTC(setReport());
			
			//To find if TestCaseId in Test Runner sheet is available in TestCase sheet
			handler.validateTCListConsistency(setReport());			
			setReport().endTest(logger);
			}catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void executeAllTestCases() {		
		String currentTcID = null;		
		try {						
			//Get the row indexes which has Run_Mode as Yes
			Vector yesMode = getTestData().getRowIndexes("Yes", getTestData().getColIndex("RunMode"));	
						 
			//To get the list of Test cases having YES as Run Mode
			Vector testCases = getTestData().getResColList(yesMode, "TestCaseID");
			 
			//to run through the test case list with Yes and go to TestCase sheet to check Keywords
			for(int i=0;i<testCases.size();i++)		
			{
				//Current Test Case ID which needs to be executed
				currentTcID = (String) testCases.get(i);				
				//To start reporting for the testcase
				ExtentReports reporter = setReport();
				logger = reporter.startTest(currentTcID);
				executeTestCase(currentTcID,logger,reporter);				
				System.out.println("Status of TestCase "+currentTcID+" is "+logger.getRunStatus());	
				setReport().endTest(logger);				
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			}
	}
	
	private void executeTestCase(String currentTcID, ExtentTest logger, ExtentReports reporter){
		try {			
			//To set the sheet to the TestCase sheet to execute keyword list
			getTestData().setSheet("TestCase");
						
			//Get the array of row indexes in Test Case sheet which has Run_Mode as Yes
			Vector currentTcRow = getTestData().getRowIndexes(currentTcID, getTestData().getColIndex("TestCaseID"));
			int currentRowIndex = (int) currentTcRow.get(0);//---Current Test case row
						
			//-------3.To get the array of Keywords corresponding to each TestCase-------
			Vector KeywordList = getTestData().getKeywords(currentRowIndex);		
						
			//To execute the keywords for current test case			
			executeKeyword(KeywordList,currentTcID,logger,reporter);	
			
			} catch (IllegalArgumentException e) {
			
			logger.log(LogStatus.FATAL, "Error in Execution of Test Case -"+currentTcID);
			e.printStackTrace();
			} 
	}
	
	private void executeKeyword(Vector KeywordList,String currentTcID, ExtentTest logger, ExtentReports reporter) {		
		
		System.out.println("Current execution is done on Test case - "+currentTcID);
		Boolean stopExe = true;		
		
 		 //To launch the respective browser
 		 String browserName = null;		
 		 try {
 			 browserName = getTestData().getData(currentTcID, "Browser", "TestRunner");			 
 		 	 } catch (Exception e) {
 		 	 // TODO Auto-generated catch block
			 e.printStackTrace();
 		 	 } 	
 		 
 		//To initiate the browser
 		 try {
			 this.driver = browser.startBrowser(logger, browserName);
 		 	 } catch (Exception e) {
			 e.printStackTrace();
 		 	 } 
 		 
 		function = new TestFunctions(getTestData(), driver); 
 		aMFunction = new TestAMFunctions(getTestData(), driver); 
 		 
		//To run through keyword list and execute them
			for(int j=0;j<KeywordList.size();j++)
			{
				
				if(stopExe)
				{
					String ctKeyword = (String) KeywordList.get(j);
					if(!ctKeyword.isEmpty())
					{
						if(ctKeyword.contains("AM"))
						{
							//To retrieve the methods from function library to check for the expected keyword method as enetered in Testcase sheet
							Method method[]= aMFunction.getClass().getDeclaredMethods();
							//To run through the keywords and execute them
							Boolean keywordExistence = false;
							for(int k=0;k<method.length;k++)
							{
								
								if (method[k].getName().equalsIgnoreCase(ctKeyword))
								{
									try
									{
										method[k].invoke(aMFunction,currentTcID,logger,reporter);	
										keywordExistence = true;
										break;
										
										
									} catch (IllegalAccessException e) {
										e.printStackTrace();
										logger.log(LogStatus.FATAL, "Error in execution of the keyword - "+ctKeyword);
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
										logger.log(LogStatus.FATAL, "Error in Arguments of the keyword -"+ctKeyword);
									} catch (InvocationTargetException e) {
										//e.printStackTrace();
										logger.log(LogStatus.FAIL, "Error in execution of "+ctKeyword+" keyword");									
										System.out.println("Error in execution of "+ctKeyword +" keyword");
										stopExe = false;
										break;	
									}								
								}
								if((!keywordExistence)&(k==(method.length-1)))
								{
									logger.log(LogStatus.FATAL, "Keyword - "+ctKeyword+" is not available in Fucntion library");	
									function.CloseBrowser();
									break;
								}							
							}
						}
						else
						{
							//To retrive the methods from function library to check for the expected keyword method as enetered in Testcase sheet
							Method method[]= function.getClass().getDeclaredMethods();
							//To run through the keywords and execute them
							Boolean keywordExistence = false;
							for(int k=0;k<method.length;k++)
							{
								
								if (method[k].getName().equalsIgnoreCase(ctKeyword))
								{
									try
									{
										method[k].invoke(function,currentTcID,logger,reporter);	
										keywordExistence = true;
										break;
										
										
									} catch (IllegalAccessException e) {
										e.printStackTrace();
										logger.log(LogStatus.FATAL, "Error in execution of the keyword - "+ctKeyword);
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
										logger.log(LogStatus.FATAL, "Error in Arguments of the keyword -"+ctKeyword);
									} catch (InvocationTargetException e) {
										//e.printStackTrace();
										logger.log(LogStatus.FAIL, "Error in execution of "+ctKeyword +" keyword");									
										System.out.println("Error in execution of "+ctKeyword +" keyword");
										stopExe = false;
										break;	
									}								
								}
								if((!keywordExistence)&(k==(method.length-1)))
								{
									logger.log(LogStatus.FATAL, "Keyword - "+ctKeyword+" is not available in Fucntion library");	
									function.CloseBrowser();
									break;
								}							
							}
						}
					}else{
						logger.log(LogStatus.WARNING, "TestCaseID - "+currentTcID+" has blank keywords in TestCase sheet");
					}	
					}else if(!stopExe)
					{
						break;	
					}
					
			
		  }
		//To close the browser after execution of all keywords of a test case.
		function.CloseBrowser();						 
	}

	private void tearDown() { 
			//To write to report
	 		setReport().flush();	 		
		 }

	}


