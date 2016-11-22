package SupportLib;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author -  Durga Suresh
 * Description - to set the driver based on data from excel sheet  and close the browser when required
 *
 */
public class TestBrowser {
		
	WebDriver driver;
	
	//(@Author:Durga;@Param:logger,browserName;@Return:driver)To open the browser based on Browser value in Test Runner
	public WebDriver startBrowser( ExtentTest logger,String browserName) {
		
			switch(browserName){
				case "Firefox":
					driver = new FirefoxDriver();					
					logger.log(LogStatus.INFO, "Firefox browser opened successfully");
					driver.manage().window().maximize();						
					return driver;
				case "Chrome":
					System.setProperty("webdriver.chrome.driver", "./BrowserDrivers/chromedriver.exe");		
					driver = new ChromeDriver();
					logger.log(LogStatus.INFO, "Chrome browser opened successfully");
					driver.manage().window().maximize();
					return driver;					
				
				case "IE":
					File IEDriver = new File("BrowserDrivers/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());	
					driver = new InternetExplorerDriver();
					logger.log(LogStatus.INFO, "Inter Explorer browser opened successfully");
					driver.manage().window().maximize();
					return driver;					

				default :					
					driver = new FirefoxDriver();
					logger.log(LogStatus.INFO, "Firefox browser opened successfully");
					driver.manage().window().maximize();
					return driver;					
					}
		}	
	}



