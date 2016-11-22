package ReportPack;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DriverPack.TestConfig;	


/**
 * @author - Durga Suresh
 * Description - Functions supporting the creation of report.
 *
 */
public class TestReport {	
	
	public  String reportName;
	public  String testCaseID;
	public  ExtentReports report;
	public  ExtentTest logger;
	public WebDriver driver;
	public TestConfig value =new TestConfig();	
	public int countFatal = 0;
	
	
	//(@Author:Durga;@Param:driver;@Return:Image Path)To capture screen shot and return the absolute path of the same with Webdriver as parameter
	public String CaptureScreenshot(WebDriver driver)
	{	String absolutePath = null;		
		try {			
			String screenshotPath = value.imagePathGen();
			TakesScreenshot Capture = (TakesScreenshot)driver;		
			File source = Capture.getScreenshotAs(OutputType.FILE);
			File capturePath = new File(screenshotPath);
			FileUtils.copyFile(source,capturePath); 			
			absolutePath = capturePath.getCanonicalPath();//to get the absolute path of the screenshot		
			} catch (Exception e) {
			// TODO Auto-generated catch block
			LogWARN(driver, logger, "Problem with Screenshot capture"+e.getMessage());				
			e.printStackTrace();
		}return absolutePath;		
	}
	
	//(@Author:Durga;@Param:driver,logger,message)To report as PASS
	public void LogPASS(WebDriver driver,ExtentTest logger,String message)
	{
		String screenCapturePath = CaptureScreenshot(driver);
		logger.log(LogStatus.PASS, message + logger.addScreenCapture(screenCapturePath));			
	}
	
	//(@Author:Durga;@Param:driver,logger,message)To report as FAIL
	public void LogFAIL(WebDriver driver,ExtentTest logger,String message)
	{
		String screenCapturePath = CaptureScreenshot(driver);
		logger.log(LogStatus.FAIL, message + logger.addScreenCapture(screenCapturePath));	
	}

	//(@Author:Durga;@Param:driver,logger,message)To report as INFO
	public void LogINFO(WebDriver driver,ExtentTest logger,String message)
	{
		String screenCapturePath = CaptureScreenshot(driver);
		logger.log(LogStatus.INFO, message + logger.addScreenCapture(screenCapturePath));			
	}
	
	//(@Author:Durga;@Param:driver,logger,message)To report as Warning
	public void LogWARN(WebDriver driver,ExtentTest logger,String message)
	{	
		String screenCapturePath = CaptureScreenshot(driver);
		logger.log(LogStatus.WARNING, message + logger.addScreenCapture(screenCapturePath));			
	}

	//(@Author:Durga;@Param:driver,logger,message)To report as FATAL error
	public void LogFATAL(WebDriver driver,ExtentTest logger,String message)
	{
		if(countFatal<1)
		{
		String screenCapturePath = CaptureScreenshot(driver);			
		logger.log(LogStatus.FATAL, message + logger.addScreenCapture(screenCapturePath));
		//report.flush();
		driver.quit();
		countFatal = +1;
		}else
		{
			logger.log(LogStatus.FATAL, message );
		}
	}

	//(@Author:Durga;@Param:logger,message)To report as PASS without IMG
	public void LogPASSmsg(ExtentTest logger,String message)
	{
		
		logger.log(LogStatus.PASS, message );			
	}
	
	//(@Author:Durga;@Param:logger,message)To report as FAIL without IMG
	public void LogFAILmsg(ExtentTest logger,String message)
	{
		
		logger.log(LogStatus.FAIL, message );	
	}
	
	//(@Author:Durga;@Param:logger,message)To report as FAIL without IMG
	public void LogFATALmsg(WebDriver driver,ExtentTest logger,String message)
	{
		
		logger.log(LogStatus.FATAL, "FAILURE : "+message );
		//report.flush();
		driver.quit();	
	}
	
	
}



