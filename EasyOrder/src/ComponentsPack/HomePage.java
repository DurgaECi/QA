package ComponentsPack;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import SupportLib.TestWait;


/**
 * @author - dsuresh
 * Description - Objects and methods Home Page
 *
 */
public class HomePage {
	
	WebDriver driver;
	private GeneralFunctions genfunc;
	private DriverPack.TestConfig config = new DriverPack.TestConfig() ;	
	public TestWait wait = new  TestWait();
	
	
	public HomePage(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}
	
	//*********************************************************************
	@FindBy(xpath ="//*[@id='login_interact']") WebElement buttonLogout1;
	@FindBy(xpath ="//*[@id='det_buttons']/a[2]") List <WebElement> buttonLogout2;	
	@FindBy(xpath ="//span[@id='nav_custhdr']//h3/a") List <WebElement> linkMainHeaderMenu;
	@FindBy(id="nav_nom_tmp") WebElement linkOrderTemplate;
	@FindBy(xpath="//*[@class='EWD_Ordertemp_context']//a[contains(@onclick,'new_form_dialog')]") WebElement linkNewOrderTemplate;
	@FindBy(css ="select[id=\"change_form_group\"]") WebElement dropdownOrderTemplateGroup;
	@FindBy(css ="input[id=\"change_form_name\"]") WebElement editOrderTemplateName;
	@FindBy(css ="a[id=\"new_ok\"]") WebElement buttonOKNewOrderTemplatePanel;
	@FindBy(css ="a[id=\"copy_ok\"]") WebElement buttonOKCopyOrderTemplatePanel;
	@FindBy(css ="a[id=\"edit_ok\"]") WebElement buttonOKEditOrderTemplatePanel;
	@FindBy(xpath="//*[@class='text_link purshtTruncated']") List<WebElement> listCreatedOrderTemplates;
	@FindBy(xpath ="//*[@id='delete_dialog']//a[contains(@onclick,'delete_form')]") WebElement buttonOKDeleteOrderTemplatePanel;
	@FindBy(xpath ="//*[contains(@id,'sharing_container')]//img[contains(@src,'close')]") WebElement buttonCloseShareOrderTem;
	@FindBy(xpath ="//*[@id='sharing_options']") List<WebElement> panelShareOrderTem;
	@FindBy(xpath ="//*[@id='sharing_options']//*[@class='multiselect']") List<WebElement> panelSelectedUsersSOT;
	@FindBy(xpath ="//*[@id='group_']/ul") WebElement panelOrderTemplateList;
	@FindBy(css ="input[id=\"owner\"]") WebElement radioButtonSOTYourselfOnly;
	@FindBy(css ="input[id=\"account\"]") WebElement radioButtonSOTAccountUsers;
	@FindBy(css ="input[id=\"users\"]") WebElement radioButtonSOTSelectedUsers;
	@FindBy(xpath="//a[contains(@onclick,'copy_form_dialog')]") WebElement listCopyOrderTemplates;
	
	//*********************************************************************
	
	//(@Author:Durga;@Param:OrderTemplateName)To return the number of Action Icons associated for an order template
	public int SOTNoOfActionIcon(String OrderTemplateName)		
	{
		wait.WaitforMaxTime(driver, driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']")));
		List<WebElement> listIcons = driver.findElements(By.xpath("//*[@name='"+OrderTemplateName+"']/a"));
		return listIcons.size()-1;	
	}
	
	//(@Author:Durga;@Param:OrderTemplateName)To get share button for an order template
	public WebElement buttonShareOrderTem(String OrderTemplateName)		
	{   WebElement buttonShareOrderTem = null;
		if (SOTNoOfActionIcon(OrderTemplateName)>=4)
		{
			wait.WaitforMaxTime(driver, driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']//img[contains(@src,'share')]")));
			buttonShareOrderTem = driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']//img[contains(@src,'share')]"));
		}
		return buttonShareOrderTem;		
	}
	//(@Author:Durga;@Param:OrderTemplateName)To get Copy button for an order template
	public WebElement buttonCopyOrderTem(String OrderTemplateName) throws InterruptedException		
	{	WebElement buttonCopyOrderTem = null;
		if (SOTNoOfActionIcon(OrderTemplateName)>=4)
		{
			wait.WaitforMaxTime(driver, panelOrderTemplateList);
			buttonCopyOrderTem =driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']//img[contains(@src,'copy')]"));		
		}
		return buttonCopyOrderTem;
	}
	//(@Author:Durga;@Param:OrderTemplateName)To get Delete button for an order template
	public WebElement buttonDeleteOrderTem(String OrderTemplateName)		
	{	WebElement buttonDeleteOrderTem = null;
		if (SOTNoOfActionIcon(OrderTemplateName)>=4)
		{	
			wait.WaitforMaxTime(driver, panelOrderTemplateList);
		buttonDeleteOrderTem = driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']//img[contains(@src,'trash')]"));
		}
		return buttonDeleteOrderTem;		
	}
	//(@Author:Durga;@Param:OrderTemplateName)To get Edit button for an order template
	public WebElement buttonEditOrderTem(String OrderTemplateName)		
	{	WebElement buttonEditOrderTem = null;
		if (SOTNoOfActionIcon(OrderTemplateName)>=4)
		{
			wait.WaitforMaxTime(driver, panelOrderTemplateList);
			buttonEditOrderTem = driver.findElement(By.xpath("//*[@name='"+OrderTemplateName+"']//img[contains(@src,'edit')]"));
		}
		return buttonEditOrderTem;		
	}
	//(@Author:Durga;@Param:OrderTemplateName)To select a user from SOT selected Users panel
	public List<WebElement> checkboxSelectedUserSOT(String UserID)		
	{	
		List<WebElement> checkboxSelectedUser=  driver.findElements(By.xpath("//*[@id='sharing_options']//*[@class='multiselect']//*[@value='"+UserID+"']"));
		return checkboxSelectedUser;		
	}	
	
	//*********************************************************************
	
	//(@Author:Durga)To Logout
	public void logout()
	{
		WebElement buttonLogout;		
		if (buttonLogout2.size()<=0)
		{
			buttonLogout = buttonLogout1;
		}else{
			buttonLogout = buttonLogout2.get(0);
		}
		
		buttonLogout.click();
	}
	
	//(@Author:Durga)To go to Home page
	public void gotoHomePage(String Url)
	{
		driver.get(Url);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("login_interact"))); 
	}
	
	//(@Author:Durga)To get the list of header menu and check for a particular Main header Menu
	public Boolean checkMainHeaderMenu(String MainHeaderMenu)
	{
		Boolean flag = false;
		for(int i=1;i<=linkMainHeaderMenu.size();i++)
		{
			if(linkMainHeaderMenu.get(i).getText().contains(MainHeaderMenu))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	//(@Author:Durga)To click the Main header Menu
	public void clickMainHeaderMenu(String MainHeaderMenu)
	{
		for(int i=1;i<=linkMainHeaderMenu.size();i++)
		{
			if(linkMainHeaderMenu.get(i).getText().contains(MainHeaderMenu))
			{
				linkMainHeaderMenu.get(i).click();
				break;
			}
		}
	}
	
	//(@Author:Durga)To click the Main header Menu
	public Boolean checkSubHeaderMenuPosition(String[] HeaderMenu)
	{
		Boolean flag= false;
		String firstSubHeader = HeaderMenu[2];
		for(int i=0;i<linkMainHeaderMenu.size();i++)
		{							
			 if(linkMainHeaderMenu.get(i).getText().contains(HeaderMenu[0]))
			 {
				WebElement linkSubHeaderMenu = driver.findElement(By.xpath("//span[@id='nav_custhdr']/span[@class='group_container']["+(i+1)+"]//*[@class='group_list']//li[1]/a"));
				 if(linkSubHeaderMenu.getText().contains(firstSubHeader))
				{
						flag = true;
						break;
				}	
			 }			
		}
		return flag;
	}

	//(@Author:Durga)To click the Order Template link in catalog menu-home page 
	public void gotoOrderTemplatePage()
	{
		wait.WaitforMinTime(driver, linkOrderTemplate);	
		if(linkOrderTemplate.isDisplayed())
		{
			linkOrderTemplate.click();
		}
	}
	
	//(@Author:Durga)To click on New order template link in Order template page
	public void clickNewOrderTemplate()
	{
		if(linkNewOrderTemplate.isDisplayed())
		{
			linkNewOrderTemplate.click();
		}
	}
	
	//(@Author:Durga;@Param:BackOffice Name)To select Order template group
	public Boolean selectOrderTemplateGroup(String OrderTemplateGrouptxt)
	{
		Boolean flag = false;
		Select OrderTemplateGroup = genfunc.dropdownlist(dropdownOrderTemplateGroup);
		OrderTemplateGroup.selectByVisibleText(OrderTemplateGrouptxt);
		String selectedOTG = OrderTemplateGroup.getFirstSelectedOption().getText();
		if(OrderTemplateGrouptxt.equals(selectedOTG))
		{
			flag=true;
		}
		return flag;
	}

	//(@Author:Durga)To enter order template name
	public String enterOrderTemplateName()
	{
		String name = (config.timeStampGenerator());
		if(editOrderTemplateName.isDisplayed()& buttonOKNewOrderTemplatePanel.isDisplayed())
		{
			editOrderTemplateName.sendKeys(name);
			buttonOKNewOrderTemplatePanel.click();
		}else
		{
			name=null;
		}
		return name.trim();
	}
	
	//(@Author:Durga)To get the list of order templates and check for a particular Order template name
	public Boolean checkCreatedOrderTemplate(String OrderTemplateName)
	{
		Boolean flag = false;
		gotoOrderTemplatePage();
		for(int i=0;i<listCreatedOrderTemplates.size();i++)
		{
			if(listCreatedOrderTemplates.get(i).getText().contains(OrderTemplateName))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}

	//(@Author:Durga)To edit the Order template name
	public String EditOrderTemplateName(String OrderTemplateName)
	{
		String EditedOTName = "Edited_"+OrderTemplateName;
		if(checkCreatedOrderTemplate(OrderTemplateName))
		{
			buttonEditOrderTem(OrderTemplateName).click();
			editOrderTemplateName.clear();
			editOrderTemplateName.sendKeys(EditedOTName);
			buttonOKEditOrderTemplatePanel.click();			
		}
		
		if(checkCreatedOrderTemplate(EditedOTName))
		{
		return EditedOTName;
		}else{
		return null;
		}
	}	
	
	//(@Author:Durga)To copy the Order template name
	public String CopyOrderTemplateName(String OrderTemplateName) throws InterruptedException
	{
		String CopiedOTName = "Copied_"+OrderTemplateName;
		if(checkCreatedOrderTemplate(OrderTemplateName))
		{
			buttonCopyOrderTem(OrderTemplateName).click();	
			editOrderTemplateName.clear();
			editOrderTemplateName.sendKeys(CopiedOTName);
			buttonOKCopyOrderTemplatePanel.click();			
		}
		if(checkCreatedOrderTemplate(CopiedOTName))
		{
		return CopiedOTName;
		}else{
		return null;
		}
	}
		
	//(@Author:Durga)To delete the Order template name
	public Boolean DeleteOrderTemplateName(String OrderTemplateName)
	{
		Boolean flag = false;
	
		if(checkCreatedOrderTemplate(OrderTemplateName))
		{
			buttonDeleteOrderTem(OrderTemplateName).click();	
			buttonOKDeleteOrderTemplatePanel.click();			
		}
		if(!checkCreatedOrderTemplate(OrderTemplateName))
		{
		flag = true;
		}		
		return flag;
	}
	
	//(@Author:Durga)To Share the Order template 
	public Boolean ValidateShareOrderTemplatePanel(String OrderTemplateName)
	{
		Boolean flag = false;
		if(panelShareOrderTem.size()<2)
		{
		buttonShareOrderTem(OrderTemplateName).click();
		}
		wait.WaitforMaxTime(driver, panelShareOrderTem.get(0));
		if(radioButtonSOTYourselfOnly.isDisplayed()&radioButtonSOTAccountUsers.isDisplayed()&radioButtonSOTSelectedUsers.isDisplayed())
		{
			flag=true;
		}
		return flag;
	}
	
	//(@Author:Durga)To Share the Order template 
	public void SelectShareOrderTemplateType(String Type,String OrderTemplateName)
	{
		gotoOrderTemplatePage();
		if(panelShareOrderTem.size()<2)
		{
		buttonShareOrderTem(OrderTemplateName).click();
		}
		wait.WaitforMaxTime(driver, panelShareOrderTem.get(0));
		switch(Type)
		{
		case "YourselfOnly" :
			radioButtonSOTYourselfOnly.click();		
			break;
		case "Account":
			radioButtonSOTAccountUsers.click();
			break;
		case "SelectedUsers":
			radioButtonSOTSelectedUsers.click();
			break;
		}
	}
	
	//(@Author:Durga)To Share the Order template 
	public Boolean SelectSOTSelectedUser(String UserID)
	{	
		wait.WaitforMaxTime(driver, panelSelectedUsersSOT.get(0));
		Boolean flag = false;
		if(checkboxSelectedUserSOT(UserID).size()>0)			
		{
			checkboxSelectedUserSOT(UserID).get(0).click();
			flag=true;
		}
		return flag;
	}
	
	//(@Author:Durga)To check for delete/share/edit/copy icons for Order template 
		public Boolean checkSOTActionIcons(String OrderTemplate)
		{	
			Boolean flag = false;
			if(SOTNoOfActionIcon(OrderTemplate)>1)
			{
				flag = true;
			}
			return flag;
		}



}





