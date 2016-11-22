/**
 * 
 */
package ComponentsPack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to User & Accounts Tab
 *
 */
public class AM_UserAccountTab {

	WebDriver driver;
	private AM_General amGenfunc;
	private GeneralFunctions genfunc;
	public String AMUserAccountTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B";
	public String AccountpriceGroupPage = "/easyorder/WGRAM1";
	public String AccountPage = "/easyorder/WDEBM1";
	public String ShipAddPage = "/easyorder/WDEBX1?MODE=DEL";
	public String UserPage = "/easyorder/USER10";
	public String AuthorisationRulesPage = "/easyorder/WAUTM1";
	
	
	public AM_UserAccountTab(WebDriver Idriver)
	{
	this.driver = Idriver;
	this.amGenfunc = PageFactory.initElements(driver, AM_General.class);
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}
	
	//*********************************************************************
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[4]/th") WebElement labelAPGDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[4]/td/select") WebElement dropdownAPGDisplaySplit;		
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[4]/th") WebElement labelAccGrpDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[4]/td/select") WebElement dropdownAccGrpDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[5]/tbody/tr[7]/th") WebElement labelAccDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[5]/tbody/tr[7]/td/select") WebElement dropdownAccDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[5]/tbody/tr[2]/th") WebElement labelShipAddDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[5]/tbody/tr[2]/td/select") WebElement dropdownShipAddDisplaySplit;	
		@FindBy(xpath ="//*[@id='linkAdvancedSettings']") WebElement linkUserAdvancedSettings;	
		@FindBy(css ="input[name=\"FLDOPQ\"]") WebElement checkboxRestrictedAssortment;
		@FindBy(xpath ="//*[@id='display']/table[2]/tbody/tr[8]/td[1]/select") WebElement dropdownAccountInAssortmentInd;
		@FindBy(xpath ="//*[@id='menu']/li[2]/a") WebElement tabAccExtraSettings;
		@FindBy(css ="select[name=\"OPTIAP\"]") WebElement dropdownUserInAssortmentInd;
		@FindBy(name = "FLDPYM") WebElement editPaymentMethod;
		@FindBy(xpath ="//*[@id='progrStrRef']") List<WebElement> panelSOR;
		@FindBy(css ="select[name=\"progrStrRef\"]") WebElement dropdownAccSORProgress;
		@FindBy(css ="input[name=\"autSupvCod\"]") WebElement editAuthoriseSORProgress;
		@FindBy(css ="input[name=\"namSelAccnt\"]") WebElement editAuthorisationRulesAccount;
		@FindBy(css ="class[name=\"itm_norm\"]") WebElement linkAREmailIds;
		@FindBy(xpath ="//*[@class='rulelist_item']//*[@class='itm_long']/span") List<WebElement> textARUserRules;
		@FindBy(xpath ="//*[@id='autlist']/*[@class='rulelist_item']") List<WebElement> listARUserRules;
		@FindBy(css ="input[name=\"FLDINA\"]") WebElement checkboxUsrMultipleInvoiceAdd;
		@FindBy(css ="select[name=\"OPTINA\"]") WebElement dropdownUsrMultipleInvoiceAdd;
		
		
	//*********************************************************************
		
	//(@Author:Durga;@Param:URL)To navigate to ECi Tab in AM
	public void gotoAMUserAccountTab(String webShopUrl)
	{
		String aMUserAccountTab = webShopUrl+AMUserAccountTab;		
		driver.get(aMUserAccountTab);		
	}
	
	//(@Author:Durga;@Param:URL)To navigate to Account price group page
	public void gotoAccountPriceGroupPage(String webShopUrl)
	{
		String uaAPGpage = webShopUrl+AccountpriceGroupPage;		
		driver.get(uaAPGpage);
	}
	
	//(@Author:Durga;@Param:URL)To navigate to Account Page
	public void gotoAccountPage(String webShopUrl)
	{
		String uaAccpage = webShopUrl+AccountPage;		
		driver.get(uaAccpage);
	}

	//(@Author:Durga;@Param:URL,APGcode)To navigate to Edit page of an Account Price group 
	public void gotoEditAPG(String webShopUrl,String aPGcode)
	{
		String apgEdit =webShopUrl+"/easyorder/wgram1?id="+aPGcode+"&action=edit";		
		driver.get(apgEdit);		
	}
	
	//(@Author:Durga;@Param:URL,AccGroup)To navigate to Edit page of an Account group 
	public void gotoEditAccGrp(String webShopUrl,String accGrpcode)
	{
		String accgpEdit =webShopUrl+"/easyorder/wcusm1?id="+accGrpcode+"&action=edit";		
		driver.get(accgpEdit);		
	}
	
	//(@Author:Durga;@Param:URL)To navigate to Account Page
	public void gotoShippingAddressPage(String webShopUrl)
	{
		String uaShipAddpage = webShopUrl+ShipAddPage;		
		driver.get(uaShipAddpage);
	}
	
	//(@Author:Durga;@Param:URL)To navigate to User Page
	public void gotoUSerPage(String webShopUrl)
	{
		String uaUserPage = webShopUrl+UserPage;		
		driver.get(uaUserPage);
	}
	
	//(@Author:Durga)To Click on advanced settings in User 
	public void gotoUserAdvancedSettings()
	{
		linkUserAdvancedSettings.click();
	}
	
	//(@Author:Durga;@Param:Acc)To navigate to Edit page of an Account	
	public void gotoEditAcc(String webShopUrl,String AccNo)
	{
		gotoAccountPage(webShopUrl);
		amGenfunc.searchAndEdit(AccNo);
			
	}
	
	//(@Author:Durga)To Click on Extra settings in Account 
	public void gotoAccountEXTRASettings()
	{
		tabAccExtraSettings.click();
	}
	
	//(@Author:Durga;@Param:setONorOFF)To turn ON Restricted Assortment
	public Boolean setRestrictedAssortment(String setONorOFF)
	{			
		Boolean flag = false;	
		
		if (setONorOFF.equalsIgnoreCase("ON"))
		{
			if(!checkboxRestrictedAssortment.isSelected())
			{
				checkboxRestrictedAssortment.click();
				flag = true;
			}
			else if (checkboxRestrictedAssortment.isSelected())
			{
				flag = true;
			}
		}else if (setONorOFF.equalsIgnoreCase("OFF"))
		{
			if(!checkboxRestrictedAssortment.isSelected())
			{
				flag = true;
			}
			else if (checkboxRestrictedAssortment.isSelected())
			{	
				checkboxRestrictedAssortment.click();
				flag = true;
			}
		}
		return flag;
	}
	
	
	//(@Author:Durga;@Param:PaymentMethod)To set Payment method on user level
	public void setUserPaymntMethod(String paymentMethod)
	{
		editPaymentMethod.clear();
		editPaymentMethod.sendKeys(paymentMethod);
	}
	
	//(@Author:Durga)To select Progress budget option from Structured Reference Progress in Account
	public boolean selectYesIncBudgetSOROption(String User)
	{	
		Boolean flag = false;
		if(panelSOR.size()>0)
		{
			//get the dropdown menu
			Select SOR = genfunc.dropdownlist(dropdownAccSORProgress);		
			genfunc.selectDropdownOption(2, SOR);
			if(editAuthoriseSORProgress.isDisplayed())
			{
				editAuthoriseSORProgress.sendKeys(User);
				flag = true;
			}
		}
		return flag;		
	}
	
	//(@Author:Durga)To select Progress budget option from Structured Reference Progress in Account
	public boolean selectNoSOROption()
	{		
		Boolean flag = false;
		//get the dropdown menu
		if(panelSOR.size()>0)
		{
			Select SOR = genfunc.dropdownlist(dropdownAccSORProgress);		
			genfunc.selectDropdownOption(0, SOR);		
			if(!editAuthoriseSORProgress.isDisplayed())
			{
				flag = true;
			}
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:URL)To navigate to Authorisation Rules Page  in AM
	public void gotoAuthorisationRulesPage(String webShopUrl)
	{
		String aMAuthorisationPage = webShopUrl+AuthorisationRulesPage;		
		driver.get(aMAuthorisationPage);		
	}
	
	//(@Author:Durga;@Param:URL)To enter Account name in Authorization Rules
	public void enterAccountinAR(String AccNo)
	{
		editAuthorisationRulesAccount.sendKeys(AccNo);
		editAuthorisationRulesAccount.sendKeys(Keys.ENTER);
	}
	
	//(@Author:Durga;@Param:URL)To check for user-email-id in Authorization Rules
	public boolean clickAREmailIdLink(String UserID)
	{
		boolean flag = false;
		WebElement linkAREmailId= driver.findElement(By.xpath("//*[@title='"+UserID+"']/*[@class='itm_norm']"));
		if(linkAREmailId.isDisplayed())
		{
			linkAREmailId.click();
			flag = true;		
		}		
		return flag;	
	}
	
	//(@Author:Durga;@Param:URL)To check for Progress AR in user level
	public boolean checkARProgressRule()
	{
		boolean flag = false;
		if(textARUserRules.size()>0)
		{
			for(int i=0;i<listARUserRules.size();i++)
			{
				String ProgressRule = textARUserRules.get(i).getText();
				if(ProgressRule.contains("Progress"))
				{
					flag = true;	
					break;
				}		
			}
		}
		return flag;	
		}
	
		//(@Author:Durga;@Param:PaymentMethod)To Turn ON Multiple billing address checkbox in user level
		public Boolean setONUserMultipleInvoiceAdd()
		{
			Boolean flag = false;	
			
			if(!checkboxUsrMultipleInvoiceAdd.isSelected())
			{
				checkboxUsrMultipleInvoiceAdd.click();
				flag = true;
			}
			else if (checkboxUsrMultipleInvoiceAdd.isSelected())
			{
				flag = true;
			}			
			return flag;
		}
	

		//(@Author:Durga;@Param:DropdownValue)To select dropdown option when multiple invoice address is turned ON in user level with Values as 2-Select Add;3-Select&Edit
		public boolean selectUserMultipleInvoiceAddOpt(String dropdownOpt)
		{		
			Boolean flag = false;
			if(setONUserMultipleInvoiceAdd())
				{
				//get the dropdown menu
				Select MultipleInvoice = genfunc.dropdownlist(dropdownUsrMultipleInvoiceAdd);		
				genfunc.selectDropdownOption(dropdownOpt, MultipleInvoice);			
				flag = true;
			}
			return flag;
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
