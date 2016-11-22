package ComponentsPack;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to ECi Admin tab in Application Manager
 *
 */
public class AM_ECiTab {
	
	WebDriver driver;
	private AM_General amGenfunc;
	private GeneralFunctions genfunc;
	public String AMECiTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0199";
	public String SupplierSettings = "/easyorder/WSUPM1";
	public String WebservicePage = "/easyorder/WWSSM1";
		
	public AM_ECiTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.amGenfunc = PageFactory.initElements(driver, AM_General.class);
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}
	
	
	//*********************************************************************
	@FindBy(xpath ="//*[@id='hTab4']") WebElement tabSupplierEditSpecificSettings;
	@FindBy(xpath ="//*[@id='hTab5']") WebElement tabSupplierSpecSpecificSettings;
	@FindBy(xpath ="//*[@id='hTab4']") WebElement tabSupplierSpecSearchSettings;
	@FindBy(xpath ="//*[@id='hTab3']") WebElement tabSupplierOrdNDelSettings;
	@FindBy(xpath ="//*[@id='Tab4']/table/tbody/tr[11]/th") WebElement labelSupplierDisplaySplit;
	@FindBy(xpath ="//*[@id='Tab4']/table/tbody/tr[11]/td[1]/select") WebElement dropdownDisplaySplit;
	@FindBy(css ="input[name=\"sw_WwSplPckLgc\"]") WebElement checkboxSupSplitPack;
	@FindBy(xpath ="//*[@id='Tab5']/table/tbody/tr[37]/td[1]/select") WebElement dropdownSupplierInAssortmentInd;
	@FindBy(xpath ="//*[@id='quickLinks']//img[@title='commando verzenden']") WebElement buttonClearCache;
	@FindBy(xpath ="//*[@id='quickLinks']//img[@title='submit command']") List<WebElement> buttonClearCacheEng;
	@FindBy(xpath ="//*[@id='selectCommando']/select/*[@value='7']") WebElement dropdownClearCacheHtmlServer;	
	@FindBy(xpath ="//*[@id='submitCmd']//*[@title='Verzend']") WebElement buttonCacheRefresh;	
	@FindBy(xpath ="//*[@id='submitCmd']//*[@title='submit']") WebElement buttonCacheRefreshEng;	
	@FindBy(css ="input[name=\"supNegMargin\"]") WebElement checkboxSupressNegMargin;
	@FindBy(css ="input[name=\"sw_FltBeforeLgIn\"]") WebElement checkboxAttributeFilterB4Login;
	@FindBy(css ="select[name=\"margin\"]") WebElement dropdownEnableConsumerPrice;
	@FindBy(css ="select[name=\"ownApplication\"]") WebElement dropdownBackOffice;
	@FindBy(css ="input[name=\"sw_disablePanels\"]") WebElement checkboxDisablePanels;
	@FindBy(css ="select[name=\"sw_Substitutions\"]") WebElement dropdownSubstitueProducts;
	@FindBy(css ="input[name=\"sw_sharedOrdTmpl\"]") WebElement checkboxShareOrderTemplate;
	@FindBy(xpath ="//*[@id='list']/table/tbody//input[@class='checkBox']") List<WebElement> tableWebservice; 
	@FindBy(css ="input[name=\"sgmOrdRef\"]") WebElement checkboxSegmentedOrderReference;
	
	//*********************************************************************
	
	
	//(@Author:Durga;@Param:Url)To navigate to ECi Tab in AM
	public void gotoAMECiTab(String webShopUrl)
	{
		String aMECiTab = webShopUrl+AMECiTab;		
		driver.get(aMECiTab);		
	}
	
	//(@Author:Durga;@Param:Url)To navigate to SUpplier Settings in ECi Tab in AM
	public void gotoSupplierSettings(String webShopUrl)
	{
		String eCiSupSet = webShopUrl+SupplierSettings;		
		driver.get(eCiSupSet);
	}
	
	//(@Author:Durga;@Param:Url,Supplier code)To navigate to Edit Specific page of a SUpplier in Supplier settings ECi Tab 
	public void gotoSupplierEditSpecific(String webShopUrl,String codeName)
	{
		String supplierEditSpecific = webShopUrl+"/easyorder/wsupm1?&id="+codeName+"&action=editebuzz";		
		driver.get(supplierEditSpecific);		
	}
	
	//(@Author:Durga;@Param:Url,Supplier code)To navigate to Edit General page of a Supplier in Supplier settings ECi Tab
	public void gotoSupplierEditGenenral(String webShopUrl,String codeName)
	{
		String supplierEditGeneral = webShopUrl+"/easyorder/wsupm1?&id="+codeName+"&action=editlimit";		
		driver.get(supplierEditGeneral);		
	}
	
	//(@Author:Durga)To navigate to General Edit-Specific Settings tab in Supplier settings ECi Tab
	public void gotoSupplierEditSpecificSettingsTab()
	{
		tabSupplierEditSpecificSettings.click();				
	}
	
	//(@Author:Durga)To navigate to Specific Edit-Specific Settings tab in Supplier settings ECi Tab
	public void gotoSupplierSpecSpecificSettingsTab()
	{
		tabSupplierSpecSpecificSettings.click();				
	}
	
	//(@Author:Durga)To navigate to Orders and Delivery Settings tab in Supplier settings ECi Tab
	public void gotoSupplierOrdNDelSettingsTab()
	{
		tabSupplierOrdNDelSettings.click();				
	}
	
	//(@Author:Durga)To navigate to Specific Edit-Search Settings tab in Supplier settings ECi Tab
	public void gotoSupplierSpecSearchSettingsTab()
	{
		tabSupplierSpecSearchSettings.click();				
	}
		
	//(@Author:Durga;@Param:Status)To turn ON/OFF Restricted Assortment
	public Boolean setSupSplitPack(String status)
	{	Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxSupSplitPack.isSelected())
				{
					checkboxSupSplitPack.click();
					flag = true;
				}
				else if (checkboxSupSplitPack.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxSupSplitPack.isSelected())
				{
					checkboxSupSplitPack.click();
					flag = true;
				}
				else if (!checkboxSupSplitPack.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}
	
	//(@Author:Durga)To clear HTML and server side cache
	public Boolean clearHtmlServercache(String webShopUrl)
	{
		Boolean flag= false;
		gotoSupplierSettings(webShopUrl);	
		if(buttonClearCacheEng.size()<=0)
		{
			buttonClearCache.click();
			dropdownClearCacheHtmlServer.click();
			buttonCacheRefresh.click();
			flag = true;		
			
		}else{
			buttonClearCacheEng.get(0).click();
			dropdownClearCacheHtmlServer.click();
			buttonCacheRefreshEng.click();
			flag = true;
		}
		return flag;
		
		
	}

	//(@Author:Durga)To turn on Suppress Negative Margin checkbox in Supplier Orders and delivery tab
	public Boolean ONSupressNegativeMargin()
	{
		Boolean flag = false;
		if(checkboxSupressNegMargin.isSelected())
		{
			flag = true;
		}else
		{
			checkboxSupressNegMargin.click();
			if(checkboxSupressNegMargin.isSelected())
			{
				flag = true;
			}
		}		
		return flag;
	}
	
	//(@Author:Durga)To turn set Show Margin Consumer Price dropdown with Yes,calculated option in Supplier Orders and delivery tab
	public String EnableSupConsumerPrice()
	{
		Select supConMargin = genfunc.dropdownlist(dropdownEnableConsumerPrice);
		String EnableConsumerMargin = genfunc.selectDropdownOption(1, supConMargin);
				
		return EnableConsumerMargin;
	}
		
	//(@Author:Durga;@Param:Status)To turn ON/OFF attribute filter b4 login in Search settings tab of supplier
	public Boolean setAttributeFilterB4Login(String status)
	{	Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxAttributeFilterB4Login.isSelected())
				{
					checkboxAttributeFilterB4Login.click();
					flag = true;
				}
				else if (checkboxAttributeFilterB4Login.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxAttributeFilterB4Login.isSelected())
				{
					checkboxAttributeFilterB4Login.click();
					flag = true;
				}
				else if (!checkboxAttributeFilterB4Login.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}
		
	//(@Author:Durga;@Param:BackOffice Name)To set the Back office in Supplier Orders and delivery tab
	public Boolean selectBackOffice(String BO)
	{
		Boolean flag = false;
		Select BackOffice = genfunc.dropdownlist(dropdownBackOffice);
		BackOffice.selectByVisibleText(BO);
		String selectedBO = BackOffice.getFirstSelectedOption().getText();
		if(BO.equals(selectedBO))
		{
			flag=true;
		}
		return flag;
		
	}

	//(@Author:Durga;@Param:Status)To turn ON/OFF Disable panels in Specific settings
	public Boolean setDisablePanels(String status)
	{	Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxDisablePanels.isSelected())
				{
					checkboxDisablePanels.click();
					flag = true;
				}
				else if (checkboxDisablePanels.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxDisablePanels.isSelected())
				{
					checkboxDisablePanels.click();
					flag = true;
				}
				else if (!checkboxDisablePanels.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}

	//(@Author:Durga;@Param:DropdownValue)To select dropdown option from Substitute with values as 0-No;1-Only on Header level;2-On Header and Line level;
	public boolean selectSubstituteProducts(String dropdownOpt)
	{		
		Boolean flag = false;
		if(dropdownSubstitueProducts.isDisplayed())
			{
			//get the drop down menu
			Select SubsitutuePdt = genfunc.dropdownlist(dropdownSubstitueProducts);		
			genfunc.selectDropdownOption(dropdownOpt, SubsitutuePdt);			
			flag = true;
		}
		return flag;
	}

	//(@Author:Durga;@Param:Status)To turn ON/OFF Share order template in Orders and delivery settings tab of supplier
	public Boolean setShareOrderTemplate(String status)
	{	Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxShareOrderTemplate.isSelected())
				{
					checkboxShareOrderTemplate.click();
					flag = true;
				}
				else if (checkboxShareOrderTemplate.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxShareOrderTemplate.isSelected())
				{
					checkboxShareOrderTemplate.click();
					flag = true;
				}
				else if (!checkboxShareOrderTemplate.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}

	//(@Author:Durga;@Param:Url)To navigate to Webservices Page in AM
	public void gotoAMWebservice(String webShopUrl)
	{
		String aMWebservicePage = webShopUrl+WebservicePage;		
		driver.get(aMWebservicePage);		
	}
	
	//(@Author:Durga;@Param:Status,PartyName,Type)To set Webservice
	public boolean setWebservice(String Status,String PartyName,String Type)
	{	boolean flag = false;
		for(int i=0;i<tableWebservice.size();i++)
		{
			if((tableWebservice.get(i).getAttribute("name").contains(PartyName))&(tableWebservice.get(i).getAttribute("name").contains(Type)))
				{
					switch(Status)
					{
						case "ON":
							if(!tableWebservice.get(i).isSelected())
							{
								tableWebservice.get(i).click();
								flag = true;
							}
							else if (tableWebservice.get(i).isSelected())
							{
								flag = true;
							}				
							break;
						
						case "OFF":
							if(tableWebservice.get(i).isSelected())
							{
								tableWebservice.get(i).click();
								flag = true;
							}
							else if (!tableWebservice.get(i).isSelected())
							{
								flag = true;
							}				
							break;
					}
				
				break;
				}
		}
		return flag;		
	}
	
	//(@Author:Durga;@Param:Status)To turn ON/OFF Segmented Order reference in Orders and delivery settings tab of supplier
	public Boolean setSegmentedOrderReference(String status)
	{	Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxSegmentedOrderReference.isSelected())
				{
					checkboxSegmentedOrderReference.click();
					flag = true;
				}
				else if (checkboxSegmentedOrderReference.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxSegmentedOrderReference.isSelected())
				{
					checkboxSegmentedOrderReference.click();
					flag = true;
				}
				else if (!checkboxSegmentedOrderReference.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}
	
	
}
	
	
	
	

