package ExcelPack;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import DriverPack.TestConfig;
import ExcelPack.TestData;

/**
 * @author - Durga Suresh
 * Description - Functions to check if there are errors in the excel sheet
 *
 */
@SuppressWarnings("all")
public class TestExcelError {

	private TestConfig value = new TestConfig();
	private TestData excel;
	private ExtentReports report;
	private ExtentTest logger ;
	
	//(@Author:Durga;@Param:sheetName)To initialize the excel sheet.
	public TestData getData(String sheetName)
	{
		try {
			excel = new TestData(value.filePath, value.fileName, sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
	}
	
	//(@Author:Durga;@Param:report)To Find if Duplicate or Blank Test case Ids are enetered in Test Sheet
	public void  validateDuplicateTC(ExtentReports report)
	{
		try
		{
			String sheetName[] = {"TestRunner","TestData","TestCase"};
			for(int i=0;i<sheetName.length;i++)
			{
				getData("TestRunner");//Open Excel
				
				List<String> testCaseList =getData(sheetName[i]).getColList("TestCaseID");//Get test case Id list		
				Set<String> testCaseSet = new HashSet<String>();				
				for(int j=0;j<testCaseList.size();j++)
				{
					int count = 0;
					String ctVal = testCaseList.get(j);
					if (!ctVal.isEmpty())
					{
						if(testCaseSet.contains(ctVal))
						{
							continue;
						}	
						else 
						{
							for (String dupString : testCaseList)
							{
								if (ctVal.equals(dupString)){
									testCaseSet.add(ctVal);
									count++;
								}
							}
						}				
						if(count>1)
						{
							ExtentTest logger = report.startTest("Error in Excel Sheet");
							logger.log(LogStatus.FAIL, "TestCaseID - " + ctVal + "  is repeated for " + count +" times in "+sheetName[i]+ " sheet. Please fill in unique Test case IDs and Run again");
							report.flush();
							System.out.println("TestCaseID - " + ctVal + "  is repeated for " + count +" times in "+sheetName[i]+ " sheet. Please fill in unique Test case IDs and Run again");
							System.exit(0);
						}						   
					}
					else if (ctVal.isEmpty())
					{
						ExtentTest logger = report.startTest("Error in Excel Sheet");
						logger.log(LogStatus.FAIL, "TestCaseID can't be blank.Please fill in Valid TestCaseID in "+sheetName[i]+" Sheet");
						report.flush();
						System.out.println("TestCaseID can't be blank.Please fill in Valid TestCaseID in "+sheetName[i]+" Sheet");
						System.exit(0);
					}
				}
			}
		}catch (Exception e){
		   // TODO Auto-generated catch block
			ExtentTest logger = report.startTest("Error in Excel Sheet");
			logger.log(LogStatus.FAIL,"TestCaseIds are Empty, Please enter TestCaseIds or format the sheet - "+e.getMessage());
			e.printStackTrace();
			System.out.println("Error in Excel - TestCaseIds are Empty, Please enter TestCaseIds or format the sheet");
			System.exit(0);			
			}		
	} 
	
	//(@Author:Durga;@Param:report)To find if TestCaseId in Test Runner sheet is available only once in TestCase sheet
	public void validateTCListConsistency(ExtentReports report)
	{
		try
		{
			getData("TestRunner");//Open Excel and set to sheet TestRunner			
			
			//To get the list of Test cases having YES as Run Mode
			Vector yesMode = getData("TestRunner").getRowIndexes("Yes", excel.getColIndex("RunMode"));			
			List<String> testRunnertcList = getData("TestRunner").getResColList(yesMode, "TestCaseID");						
			
			getData("TestCase");//Open Excel and set to sheet TestRunner
			List<String> testCasetcList = getData("TestCase").getColList("TestCaseID");//Get test case Id list
			
			for(int TR = 0; TR<testRunnertcList.size();TR++)
			{
				int counter = 0;
				String tcTR = testRunnertcList.get(TR);
				for(int TC=0;TC<testCasetcList.size();TC++)
				{
					String tcTC = testCasetcList.get(TC);
					
					if(tcTR.equalsIgnoreCase(tcTC))
					{
						counter++;
					}
				}
				if(counter<1)
				{	
					ExtentTest logger = report.startTest("Error in Excel Sheet");
					logger.log(LogStatus.FAIL, "TestCaseID - "+tcTR+" in TestRunner is not available in TestCase sheet");
					report.flush();
					System.out.println("TestCaseID - "+tcTR+" in TestRunner is not available in TestCase sheet");
					System.exit(0);
				}
				else if(counter>1)
					
				{	ExtentTest logger = report.startTest("Error in Excel Sheet");
					logger.log(LogStatus.FAIL,"TestCaseID - "+tcTR+" in TestRunner is available "+counter+" times in TestCase sheet");
					report.flush();
					System.out.println("TestCaseID - "+tcTR+" in TestRunner is available "+counter+" times in TestCase sheet");
					System.exit(0);
				}
					
			}
		}catch(Exception e){
			   // TODO Auto-generated catch block
				ExtentTest logger = report.startTest("Error in Excel Sheet");
				logger.log(LogStatus.FAIL,"Error on accessing data in Excel sheet - "+e.getMessage());
				System.out.println("Error in Excel - Error on accessing data in Excel sheet ");
				e.printStackTrace();
				System.exit(0);					
			}
		}
	}
		
		
		



		
		




