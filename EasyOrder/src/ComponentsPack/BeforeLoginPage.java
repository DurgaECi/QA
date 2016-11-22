package ComponentsPack;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import SupportLib.TestWait;

/**
 * @author - Durga Suresh
 * Description - Objects and methods in Before Login Home Page
 * and few generic functions to get page title, close browser and so.
 *
 */
public class BeforeLoginPage {
	
	WebDriver driver;
	public TestWait wait = new  TestWait();
	private GeneralFunctions genfunc;
	public BeforeLoginPage(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}
	
	//*********************************************************************
	@FindBy(name = "Email") WebElement editUsername;
	@FindBy(how=How.NAME,using="password") WebElement editPassword;
	@FindBy(xpath = "//*[@id='cnt_close']/div/a/span") WebElement buttonLogin;
	@FindBy(how=How.LINK_TEXT,using = "login_forgot") WebElement linkForgotPassword;
	@FindBy(xpath = "//*[@id='cnt_core']/div/div[5]/label/span[1]/input") WebElement checkboxRememberPwd;
	@FindBy(xpath = "//*[@id='cnt_intro']/div[@class='alert']") List <WebElement> textErrorMsg;	
	@FindBy(xpath = "//*[@id='filterblock']//*[contains(@id,'filter_attri')]") List <WebElement> sectionAttributeFilter;	
	@FindBy(xpath = "//*[@id='filterblock']") WebElement sectionFilter;	
	
	
	//*********************************************************************
	
	
	//(@Author:Durga;@Param:URL)To Launch Url
	public void launchUrl(String URL)
	{
		driver.get(URL);	
	}
	
	//(@Author:Durga;@Param:userName,passWord)To Login to application
	public void login(String userID,String passWord)
	{
		editUsername.clear();
		editUsername.sendKeys(userID);
		editPassword.sendKeys(passWord);
		buttonLogin.submit();
	}
	
	//(@Author:Durga;@Param:userName,passWord,URL)To Launch and Login in new browser
	public void login(String URL,String userID,String passWord)
	{
		launchUrl(URL);
		login(userID,passWord);
	}
	
	public String checkLoginError()
	{
		//List <WebElement> textInvalidLoginMsg = driver.findElements(By.xpath("//*[@id='cnt_intro']/div[@class='alert']"));
		String errorMsg = null;
		if(textErrorMsg.size()>0)
		{
			errorMsg = textErrorMsg.get(0).getText();
		}		
		return errorMsg;
	}
	
	public boolean checkAttributeFilter()
	{
		Boolean flag = false;
		wait.WaitforMaxTime(driver, sectionFilter);
		if(sectionAttributeFilter.size()>0)
		{
			flag = true;
		}
		return flag;
		
	}
	
	
}
