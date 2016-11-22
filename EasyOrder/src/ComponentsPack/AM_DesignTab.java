package ComponentsPack;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to Design tab in Application Manager
 *
 */
public class AM_DesignTab {
	
	WebDriver driver;
	private GeneralFunctions genfunc;
	private DriverPack.TestConfig config = new DriverPack.TestConfig() ;
	public String AMDesignTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0103";
	public String HeaderMenuPage = "/easyorder/WCHLM1";
	public String addNewHeaderMenu = "/easyorder/WCHLM1?mode=&action=new";
		
	public AM_DesignTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}
	
	
	//*********************************************************************
	
	@FindBy(xpath ="//*[@id='display']//select[@name='groupId']") WebElement dropdownHeaderMenuStructure;
	@FindBy(xpath ="//*[@id='display']//select[@name='loginOpt']") WebElement dropdownHeaderDisplay;	
	@FindBy(xpath ="//*[@id='display']/table[2]//tr[1]//input") WebElement editHeaderNLName;
	@FindBy(xpath ="//*[@id='display']/table[2]//tr[2]//input") WebElement editHeaderEngName;
	@FindBy(xpath ="//*[@id='display']//select[@name='groupId']/optgroup/option") List<WebElement>dropdownMainHeader;
	@FindBy(xpath ="//*[@id='quickLinks']//img[@title='online plaatsen']") WebElement buttonPublishHeader;
	@FindBy(xpath ="//*[@id='table']//span[2]/a") List<WebElement> linkHeaderMenuNames;
	@FindBy(xpath ="//*[@id='table']//span[1]/a/img[@title='omhoog']") List<WebElement> buttonUpMenu;
	@FindBy(xpath ="//*[@id='table']//span[1]/a/img[@title='omlaag']") List<WebElement> buttonDownMenu;
	@FindBy(xpath ="//*[@id='table']//span[1]/a/img[@title='wissen']") List<WebElement> buttonDeleteMenu;
	
	
	//*********************************************************************
	
	
	//(@Author:Durga)To navigate to Design Tab in AM
	public void gotoAMDesignTab(String webShopUrl)
	{
		String aMDesignTab = webShopUrl+AMDesignTab;		
		driver.get(aMDesignTab);		
	}
	
	//(@Author:Durga)To navigate to Header Menu Page
	public void gotoHeaderMenuPage(String webShopUrl)
	{
		String headerMenu = webShopUrl+HeaderMenuPage;		
		driver.get(headerMenu);
	}
	
	//(@Author:Durga)To navigate to go to add new header menu page
	public void gotoAddNewHeaderMenu(String webShopUrl)
	{
		String addNewMenu = webShopUrl+addNewHeaderMenu;		
		driver.get(addNewMenu);		
	}
	
	
	//(@Author:Durga)To add main Header Menu before and after login
	public String enterDataMainHeader()
	{
		//Fill in Menu structure
		Select MenuStruct = genfunc.dropdownlist(dropdownHeaderMenuStructure);		
		genfunc.selectDropdownOption("NEW", MenuStruct);
		
		//Fill in Before/After Login
		Select MenuDisplay = genfunc.dropdownlist(dropdownHeaderDisplay);		
		genfunc.selectDropdownOption("0", MenuDisplay);
		
		//Fill the name
		String name = config.timeStampGenerator();
		editHeaderNLName.sendKeys(name);
		editHeaderEngName.sendKeys(name);
		return name;	
	}
	

	//(@Author:Durga)To add Sub Header Menus before and after login
	public String enterDataSubHeader(String MainHeaderName)
	{
		//Fill in Menu structure		
		int MenuSize = dropdownMainHeader.size();	
				
		for(int i=1;i<=MenuSize;i++)
		{
			WebElement headerMenuName = driver.findElement(By.xpath("//*[@id='display']//select[@name='groupId']/optgroup/option["+i+"]"));
			if(headerMenuName.getText().contains(MainHeaderName))
			{
				headerMenuName.click();
				break;
			}
		} 
		
		//Fill in Before/After Login
		Select MenuDisplay = genfunc.dropdownlist(dropdownHeaderDisplay);		
		genfunc.selectDropdownOption("0", MenuDisplay);
		
		//Fill the name
		String name = config.timeStampGenerator();
		editHeaderNLName.sendKeys(name);
		editHeaderEngName.sendKeys(name);
		return name;	
	}
	
	//(@Author:Durga)To click header publish button
	public void ClickPublishHeader()
	{
		buttonPublishHeader.click();
	}
	
	//(@Author:Durga)To click delete button of header menu
	public Boolean checkHeaderMenu(String HeaderName)
	{	
		Boolean flag = false;
		for(int i=0;i<linkHeaderMenuNames.size();i++)
		{
			if(linkHeaderMenuNames.get(i).getText().equalsIgnoreCase(HeaderName))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	//(@Author:Durga)To click up button of header menu
	public void clickUpArrow(String HeaderName)
	{
		for(int i=0;i<linkHeaderMenuNames.size();i++)
		{
			if(linkHeaderMenuNames.get(i).getText().equalsIgnoreCase(HeaderName))
			{
				buttonUpMenu.get(i).click();
				break;
			}
		}
	}
	
	//(@Author:Durga)To click down button of header menu
	public void clickDownArrow(String HeaderName)
	{
		for(int i=0;i<linkHeaderMenuNames.size();i++)
		{
			if(linkHeaderMenuNames.get(i).getText().equalsIgnoreCase(HeaderName))
			{
				buttonDownMenu.get(i).click();
				break;
			}
		}
	}
	
	//(@Author:Durga)To click delete button of header menu
	public void deleteHeaderMenu(String[] Headermenu)
	{
		for(int j=0;j<Headermenu.length;j++)
		{
			for(int i=0;i<linkHeaderMenuNames.size();i++)
			{
				if(linkHeaderMenuNames.get(i).getText().equalsIgnoreCase(Headermenu[j]))
				{
					buttonDeleteMenu.get(i).click();
					//to handle alert pop up
					Alert alert = driver.switchTo().alert();
					alert.accept();
					
				}
			}
		}
		ClickPublishHeader();
	}
	
	
	
	
	
	
}
	
	
	
	

