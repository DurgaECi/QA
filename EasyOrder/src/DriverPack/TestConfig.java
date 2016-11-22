package DriverPack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author - Durga Suresh
 * Description - General variable and functions to set report path/ excel path and so.
 *
 */
public class TestConfig {		
		
	public String filePath =  ".\\src\\DriverPack\\";
	public String fileName = "TestSheet.xlsx";
	public String sheetName = "TestRunner";	
	public int waitMax = 50;
	public int waitMin = 20;
	public String reportName;
	public String screenShotName;
	public String dbUrlDEV1 = "jdbc:as400:10.41.0.24";
	public String dbUserName = "DURGA";
	public String dbPassword = "dini207$";
	public String environmentName = "VDMEULTST";
	
	
	//(@Author:Durga)To generate time stamp
	public String timeStampGenerator()
	{
		DateFormat dateForm = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss.SS");
		Date date = new Date();
		String Date = dateForm.format(date);
		return Date;		
	}
	
	//(@Author:Durga)To generate report name based on time of execution
	public String reportNameGenerator()
	{
		String reportName = "AutomationReport-"+timeStampGenerator();
		return reportName;		
	}
	
	//(@Author:Durga)To generate Screenshot name based on time of execution
	public String screenShotNameGenerator()
	{
		String screenShotName = "Image-"+timeStampGenerator();
		return screenShotName;		
	}
	
	//(@Author:Durga)To generate report path based on time stamp
	public String reportPathGen()
	{
		String reportPath = "R:/QA/Regression/Release Test results/Regression Test Results_16.3/AutomationResult/Logs/"+reportNameGenerator()+".html"; //-- To store in local
		//String reportPath = "./Report/Logs/"+reportNameGenerator()+".html";	//-- Stores in Framework	
		return reportPath;		
	}
	
	//(@Author:Durga)To generate image path based on time stamp	
	public String imagePathGen()
	{
		String imagePath = "R:/QA/Regression/Release Test results/Regression Test Results_16.3/AutomationResult/Screenshots/"+screenShotNameGenerator()+".png"; //-- To store in local
		//String imagePath = "./Report/Screenshots/"+screenShotNameGenerator()+".png";	//-- Stores in Framework	
		return imagePath;		
	}
	
}





