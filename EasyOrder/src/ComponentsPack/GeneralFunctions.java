/**
 * 
 */
package ComponentsPack;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ReportPack.TestReport;
import SupportLib.TestBrowser;

/**
 * @author  Durga Suresh
 * Description - Generic functions
 *
 */
public class GeneralFunctions {
	
	WebDriver driver;
	private TestReport report = new TestReport();
	public TestBrowser browser = new TestBrowser();	
	public GeneralFunctions(WebDriver Idriver)
	{
	this.driver = Idriver;	
	}
	
	//*********************************************************************
	@FindBy(css = "body") WebElement pageBody;
	
	//*********************************************************************
	
	//(@Author:Durga)To open new browser tab
	public void openNewBrowserTab()
	{	
		if (driver.toString().contains("Chrome"))//for chrome browser
		{
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size()-1));
			

		}else//for other browsers
		{
			pageBody.sendKeys(Keys.CONTROL +"t");
			driver.switchTo().defaultContent();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		}			
	}
	
	//(@Author:Durga)To return number of Tabs opened in the browser
	public int browserTabsSize()
	{	
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		return tabs.size();	
	}
	
	//(@Author:Durga;@Param:TabNo)To switch between browser tab
	public void switchBrowserTab(String tabNo)
	{	
		pageBody.sendKeys(Keys.CONTROL +tabNo);				
		driver.switchTo().defaultContent();		
	}
		
	//(@Author:Durga)To close active browser tab
	public void closeActiveBrowserTab()
	{	
		if (driver.toString().contains("Chrome"))//for chrome browser
		{
			driver.close();
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size()-1));
			

		}else//for other browsers
		{
			pageBody.sendKeys(Keys.CONTROL +"w");
		}
					
	}
		
	//(@Author:Durga)To get page title
	public String pageTitle()
	{			
		return driver.getTitle();		
	}
	
	//(@Author:Durga)To close browser
	public void closeBrowser()
	{
		driver.quit();
		
	}

	//(@Author:Durga)To compare actual and expected result
	public void compareActExp(boolean Expected,boolean Actual,ExtentTest logger)
	{		
		if (Expected == Actual)
		{			  
		  report.LogPASS(driver,logger,"Result is as expected [Expected is - "+Expected+" and Actual is - "+Actual+"]");			  
		}
		else
		{
			report.LogFAIL(driver,logger,"Expected is - "+Expected+" and Actual is - "+Actual);			  
	    } 		  
	 }
	
	//(@Author:Durga)To refresh the web page
	public void refreshPage()
	{
		driver.navigate().refresh();
		handleAlert();		
	}
	
	public boolean handleAlert() 
	{ 
		//to handle alert pop up
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (Exception e) {
			
			return false;
		}	
	}
	//(@Author:Durga)To select dropdown option with index no
	public String selectDropdownOption(int No,Select dropdownOption)
	{		
		dropdownOption.selectByIndex(No);		
		List<WebElement> dropdownList = dropdownOption.getOptions();
		String SupSelected = dropdownList.get(No).getText();		
		return SupSelected;		
	}
	
	//(@Author:Durga)To select dropdown option with value
	public void selectDropdownOption(String valueName,Select dropdownOption)
	{				
		dropdownOption.selectByValue(valueName);
	}
	
	//(@Author:Durga)To select dropdown option with value
	public void selectDropdownbyVisibleTxt(String optionName,Select dropdownOption)
	{				
		dropdownOption.selectByVisibleText(optionName);		
	}

	//(@Author:Durga,@Param:select element)to get drop down menu
	public Select dropdownlist(WebElement dropdownMenu)
	{
		Select dropdownlist = new Select(dropdownMenu);
		return dropdownlist;		
	}

	//(@Author:Durga,@Param:Input,edit box webelement)to clear and eneter value in edit box
	public void enterData(String Input,WebElement editBox)
	{
		editBox.clear();
		editBox.sendKeys(Input);
	}


	
}
