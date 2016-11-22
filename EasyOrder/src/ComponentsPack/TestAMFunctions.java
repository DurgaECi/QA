package ComponentsPack;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import ExcelPack.TestData;
import ReportPack.TestReport;


/**
 * @author - Durga Suresh
 * Description - All methods of the application
 *
 */

public class TestAMFunctions {	
	
	private TestData data;
	private WebDriver driver;
	public  ExtentReports reporter;
	private TestReport report = new TestReport();	
	private AM_General appMenu;
	private AM_ECiTab eciTab;
	private AM_UserAccountTab useraccTab;
	private AM_CatalogTab catalogTab;
	private GeneralFunctions genfunc;
	private AM_DesignTab desTab;
	private AM_PricingTab priceTab;
	private AM_OrdersAndDeliveryTab amOrdDelTab;
	private AM_MerchandiseTab amMerchandiseTab;
		
	//To instantiate data sheet and other related dependent classes
	public TestAMFunctions(ExcelPack.TestData testData,WebDriver driver) {
		this.data = testData;
		this.driver = driver; 	
		
 		this.appMenu = PageFactory.initElements(driver, AM_General.class);
 		this.eciTab = PageFactory.initElements(driver, AM_ECiTab.class);
 		this.useraccTab = PageFactory.initElements(driver, AM_UserAccountTab.class);
 		this.catalogTab = PageFactory.initElements(driver, AM_CatalogTab.class);
 		this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
 		this.desTab = PageFactory.initElements(driver, AM_DesignTab.class);
 		this.priceTab = PageFactory.initElements(driver, AM_PricingTab.class);
 		this.amOrdDelTab = PageFactory.initElements(driver, AM_OrdersAndDeliveryTab.class);
 		this.amMerchandiseTab = PageFactory.initElements(driver, AM_MerchandiseTab.class);
 		 		
	}	
		
	//(@Author:Durga)To Launch Application Manager
	public void AMLaunch(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		try{
			appMenu.launchAM(URL);		
			if(!(genfunc.pageTitle()).contains("Application Manager EasyOrder"))
			{
				report.LogFATAL(driver,logger, "Application Manager not Launched");  
			}
				report.LogPASS(driver,logger, "Application Manager Launched Successfully"); 
			} catch (Exception e) {
				report.LogFATAL(driver,logger, "Application Manager not available for the User");
				e.printStackTrace();
			}
	}
		
	//(@Author:Durga)To turn ON Restricted assortment in User
	public void AMONRestrictedAssortment(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String userID = data.getTestData(tcId, "LoginID");
		String URL = data.getTestData(tcId, "URL");
		
		try {
			genfunc.openNewBrowserTab();
			useraccTab.gotoUSerPage(URL);
			appMenu.searchAndEdit(userID);
			useraccTab.gotoUserAdvancedSettings();
			if (useraccTab.setRestrictedAssortment("ON"))	
				{report.LogPASS(driver,logger, "Restricted Assortment checkbox is Ticked successfully for user - "+userID);}
			else
				{report.LogFAIL(driver,logger, "Restricted Assortment checkbox is NOT Ticked successfully for user - "+userID);}
			appMenu.back();
			appMenu.save();
			
		} catch (Exception e) {			
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}	
		genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To turn OFF Restricted assortment in User
	public void AMOFFRestrictedAssortment(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String userID = data.getTestData(tcId, "LoginID");
		String URL = data.getTestData(tcId, "URL");
		
		try {
			genfunc.openNewBrowserTab();
			useraccTab.gotoUSerPage(URL);
			appMenu.searchAndEdit(userID);
			useraccTab.gotoUserAdvancedSettings();
			if (useraccTab.setRestrictedAssortment("OFF"))	
				{report.LogPASS(driver,logger, "Restricted Assortment checkbox is UnTicked successfully for user - "+userID);}
			else
				{report.LogFAIL(driver,logger, "Restricted Assortment checkbox is NOT UnTicked successfully for user - "+userID);}
			appMenu.back();
			appMenu.save();
			
		} catch (Exception e) {			
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}	
		genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To turn ON Split pack in Supplier and product level
	public void AMONSplitPack(String tcId, ExtentTest logger,ExtentReports setReport)
	{
			String userID = data.getTestData(tcId, "LoginID");
			String URL = data.getTestData(tcId, "URL");
			String supCode = data.getTestData(tcId, "AM_SupplierCode");
			String pdtId = data.getTestData(tcId, "PdtID");			
			
			try {
				genfunc.openNewBrowserTab();
				eciTab.gotoSupplierEditGenenral(URL, supCode);//Go ot general edit page of supplier
				eciTab.gotoSupplierEditSpecificSettingsTab();//to move to SpecificSettings tab		
				Boolean supSplit = eciTab.setSupSplitPack("ON");
				appMenu.save();	
				catalogTab.gotoEditPdt(URL, pdtId);
				Boolean pdtSplit = catalogTab.setProductSplitPack("ON");
				appMenu.save();	
				
				if (supSplit&&pdtSplit)	
					{report.LogPASS(driver,logger, " Split Pack is turned ON successfully on Supplier and Product level");}
				else
					{report.LogFAIL(driver,logger, "Split Pack is NOT turned ON on Supplier/Product level");}
				
							
			} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}	
			
	//(@Author:Durga)To turn OFF Split pack in Supplier and product level
	public void AMOFFSplitPack(String tcId, ExtentTest logger,ExtentReports setReport)
	{
			String URL = data.getTestData(tcId, "URL");
			String supCode = data.getTestData(tcId, "AM_SupplierCode");
			String pdtId = data.getTestData(tcId, "PdtID");			
			
			try {
				genfunc.openNewBrowserTab();
				eciTab.gotoSupplierEditGenenral(URL, supCode);//Go ot general edit page of supplier
				eciTab.gotoSupplierEditSpecificSettingsTab();//to move to SpecificSettings tab		
				Boolean supSplit = eciTab.setSupSplitPack("OFF");
				appMenu.save();	
				catalogTab.gotoEditPdt(URL, pdtId);
				Boolean pdtSplit = catalogTab.setProductSplitPack("OFF");
				appMenu.save();	
				
				if (supSplit&&pdtSplit)	
					{report.LogPASS(driver,logger, "Split Pack is turned OFF successfully on Supplier and Product level");}
				else
					{report.LogFAIL(driver,logger, "Split Pack is NOT turned OFF on Supplier/Product level");}
				
							
			} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}	
		
	//(@Author:Durga)To set payment method in user level in AM
	public void AMSetUserPaymntmethod(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String paymentMethod = data.getTestData(tcId, "AM_PaymentMethod");
		String userID = data.getTestData(tcId, "LoginID");
		
		try {
			genfunc.openNewBrowserTab();
			useraccTab.gotoUSerPage(URL);
			appMenu.searchAndEdit(userID);
			useraccTab.gotoUserAdvancedSettings();
			useraccTab.setUserPaymntMethod(paymentMethod);
			report.LogPASS(driver,logger, "Payment method -"+paymentMethod+" has been set for the user -"+userID);
			appMenu.back();
			appMenu.save();
			genfunc.closeActiveBrowserTab();
		} catch (Exception e) {	
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}		
	}
	
	//(@Author:Durga)To set payment method in user level in AM
	public void AMClearCache(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		
		try {
			genfunc.openNewBrowserTab();//to open new tab
			if(eciTab.clearHtmlServercache(URL))
			{
				report.LogPASS(driver,logger, "Cache cleared successfully");
			}else{
				report.LogFAIL(driver,logger, "Error in clearing Cache");
			}	
			genfunc.closeActiveBrowserTab();//to close the tab
			
		} catch (Exception e) {	
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}		
	}	
	
	//(@Author:Durga)To add Header menu
	public String[] AMAddArrangeHeader(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String webShopUrl = data.getTestData(tcId, "URL");
		ExtentTest childLogger1 = setReport.startTest("Create Headers in AM");	
		ExtentTest childLogger2 = setReport.startTest("Arrange Headers in AM");
		
		try 
		{
					
			genfunc.openNewBrowserTab();
			desTab.gotoAddNewHeaderMenu(webShopUrl);
			//Main header
			String MainheaderName =desTab.enterDataMainHeader();
			report.LogINFO(driver,childLogger1, "Date entered correctly for creating Main Header Menu");
			appMenu.save();
			if(!desTab.checkHeaderMenu(MainheaderName))
			{
				report.LogPASS(driver,childLogger1, "Date entered correctly for creating Main Header Menu");
			}
			
			desTab.gotoAddNewHeaderMenu(webShopUrl);	
			//Sub header 1
			String SubHeader1Name = desTab.enterDataSubHeader(MainheaderName);
			report.LogINFO(driver,childLogger1, "Date entered correctly for creating First Sub Header Menu");
			appMenu.save();
			
			desTab.gotoAddNewHeaderMenu(webShopUrl);	
			//Sub header 2
			String SubHeader2Name = desTab.enterDataSubHeader(MainheaderName);
			report.LogINFO(driver,childLogger1, "Date entered correctly for creating Second Sub Header Menu");
			appMenu.save();
			
			report.LogPASS(driver,childLogger1, "Main Header -"+MainheaderName+" and Sub header menus -"+SubHeader1Name+","+SubHeader2Name+" created Successfully");
			logger.appendChild(childLogger1);
			
			
			
			desTab.gotoHeaderMenuPage(webShopUrl);
			//To re-arrange sub header 2 above sub header 1			
			desTab.clickUpArrow(SubHeader2Name);
			report.LogPASS(driver,childLogger2, "Sub Headers - "+SubHeader1Name+","+SubHeader2Name+"  are re-arranged successfully in AM");
			
			desTab.ClickPublishHeader();//To publish
			String Headermenu[]={MainheaderName,SubHeader1Name,SubHeader2Name};
			logger.appendChild(childLogger2);
			return(Headermenu);
			
			
		} catch (Exception e) {	
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			logger.appendChild(childLogger1);
			logger.appendChild(childLogger2);
			e.printStackTrace();
		}
		return null;		
	}
	
	//(@Author:Durga)To turn ON SuppressNegative Margin Checkbox
	public void AMEnableSuppressnegativeMargin(String tcId, ExtentTest logger,ExtentReports setReport)
	{			
			String URL = data.getTestData(tcId, "URL");
			String supCode = data.getTestData(tcId, "AM_SupplierCode");					
			
			try {
				genfunc.openNewBrowserTab();
				eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to Specific edit page of supplier
				eciTab.gotoSupplierOrdNDelSettingsTab();//to move to Orders and Delivery tab		
				
				//Turn ON suppress margin checkbox in Supplier
				if(eciTab.ONSupressNegativeMargin())
				{
					report.LogPASS(driver,logger, "Supress Negative Margin has been Turned ON successfully");
					appMenu.save();	
				}else{
					report.LogFAIL(driver,logger, "Supress Negative Margin has NOT been Turned ON ");
					appMenu.back();
				}	
				
				//Select Yes,Calculated for Consumer Margin dropdown in Supplier 
				
				eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to Specific edit page of supplier
				eciTab.gotoSupplierOrdNDelSettingsTab();//to move to Orders and Delivery tab		
				
				String ConsumerEnabled = eciTab.EnableSupConsumerPrice();
				report.LogPASS(driver,logger, "Consumer price has been enabled in Supplier with "+ConsumerEnabled+" option selected for Show Margin Consumer Price dropdown");
				appMenu.save();	
				
			} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}	
	
	//(@Author:Durga)To set gross price
	public void AMSetGrossConsumerPrice(int PriceSheetNo,float PricePerUnit,String tcId, ExtentTest logger)
	{
		String webShopUrl = data.getTestData(tcId, "URL");
		String pdtCode = data.getTestData(tcId, "PdtID");
		
		genfunc.openNewBrowserTab();
		priceTab.gotoGrossPricePage(webShopUrl);
		priceTab.toSetGrossConsumerPrice(webShopUrl, pdtCode,PriceSheetNo, PricePerUnit);
		if (PriceSheetNo==1)
		{
		report.LogPASS(driver,logger, "Gross Price of Product-"+pdtCode+" is set to "+PricePerUnit);
		}else{
		report.LogPASS(driver,logger, "Consumer Price of Product-"+pdtCode+" is set to "+PricePerUnit);
		}
		appMenu.save();
		
		genfunc.closeActiveBrowserTab();		
	}
		
	//(@Author:Durga)To turn ON/OFF attribute filter before Login
	public void AMSetAttributeFilter(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String webShopUrl = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");	
		
		genfunc.openNewBrowserTab();
		eciTab.gotoSupplierEditSpecific(webShopUrl, supCode);//Go to Specific edit page of supplier
		eciTab.gotoSupplierSpecSearchSettingsTab();
		
		if(eciTab.setAttributeFilterB4Login("ON"))
		{
			report.LogPASS(driver,logger, "Attribute Filter Before Login has been Turned ON successfully");
			appMenu.save();					
		}else
		{
			report.LogFATAL(driver,logger, "Attribute Filter Before Login has NOT been Turned ON successfully");
		}
		genfunc.closeActiveBrowserTab();
	}
		
	//(@Author:Durga)To set Back office in Supplier
	public void AMSetBO(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");			
		String backOffice = data.getTestData(tcId, "AM_BackOffice");
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierOrdNDelSettingsTab();//to move to Orders and delivery Settings tab		
			if(eciTab.selectBackOffice(backOffice))
			{
				eciTab.dropdownBackOffice.sendKeys(Keys.ALT,Keys.ARROW_DOWN);
				report.LogPASS(driver,logger, "BackOffice in Supplier is Set to "+backOffice+" successfully");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "BackOffice in Supplier is NOT Set to "+backOffice);
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}	
	
	//(@Author:Durga)To set Progress budget rule for SOR in Account level
	public void AMSetProgressBudgetnValidate(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String webShopUrl = data.getTestData(tcId, "URL");	
		String AccNo = data.getTestData(tcId, "AM_AccNo");
		String userID = data.getTestData(tcId, "LoginID");
		
		genfunc.openNewBrowserTab();
		
		useraccTab.gotoEditAcc(webShopUrl, AccNo);
		useraccTab.gotoAccountEXTRASettings();	
		
		if(useraccTab.selectYesIncBudgetSOROption(userID))//to set Progress budget
		{
			report.LogPASS(driver,logger, "SOR is set to Yes,Include Budget option with Supervisor in Account successfully");
			appMenu.save();	
			AMCheckProgressSOR_AR(tcId,logger,setReport,"ON");							
		}else
		{
			report.LogFATAL(driver,logger, "SOR is NOT available in Account-Extra Info page and NOT set to Yes,Include Budget option in Account");
		}
		genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To remove Progress budget rule for SOR in Account level
	public void AMRemoveProgressBudgetnValidate(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String webShopUrl = data.getTestData(tcId, "URL");	
		String AccNo = data.getTestData(tcId, "AM_AccNo"); 
		
		genfunc.openNewBrowserTab();
		
		useraccTab.gotoEditAcc(webShopUrl, AccNo);
		useraccTab.gotoAccountEXTRASettings();	
		
		if(useraccTab.selectNoSOROption())//to set Progress budget
		{
			report.LogPASS(driver,logger, "SOR is set to NO option in Account successfully");
			appMenu.save();	
			AMCheckProgressSOR_AR(tcId,logger,setReport,"OFF");
							
		}else
		{
			report.LogFATAL(driver,logger, "SOR is NOT available in Account-Extra Info page and NOT set to NO option in Account");
		}
		genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To check Authorization Rules for an account
	public void AMCheckProgressSOR_AR(String tcId, ExtentTest logger,ExtentReports setReport,String ProgressOnOff)
	{
		String webShopUrl = data.getTestData(tcId, "URL");	
		String AccNo = data.getTestData(tcId, "AM_AccNo");
		String UserID = data.getTestData(tcId, "LoginID");
		
		useraccTab.gotoAuthorisationRulesPage(webShopUrl);
		useraccTab.enterAccountinAR(AccNo);
		report.LogPASS(driver,logger, "Authorisation Rule-User records for  Account-"+AccNo);
		
		if(useraccTab.clickAREmailIdLink(UserID))
		{
			if((useraccTab.checkARProgressRule())&(ProgressOnOff.equalsIgnoreCase("ON")))
			{
				report.LogPASS(driver,logger, "Progess Budget Rule for User-"+UserID+" is displayed");
			}else if((!useraccTab.checkARProgressRule())&(ProgressOnOff.equalsIgnoreCase("OFF")))
			{
				report.LogPASS(driver,logger, "Progess Budget Rule for User-"+UserID+" is NOT displayed");
			}else{
				report.LogFAIL(driver,logger, "Progess Budget Rule for User-"+UserID+" with Progress Budget Rule turned "+ProgressOnOff+" is NOT displayed as expected");
			}
		}else{
			report.LogFAIL(driver,logger, "Page not navigated to Authorisation Rules for User-"+UserID);
		}
	}

	//(@Author:Durga)To turn ON DisablePanels(show in content) in Supplier - Specific settings
	public void AMONDisablePanels(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab		
			if(eciTab.setDisablePanels("ON"))
			{
				report.LogPASS(driver,logger, "Disable Panels(show in content) is turned ON successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Disable Panels(show in content) is NOT turned ON ");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To turn ON DisablePanels(show in content) in Supplier - Specific settings
	public void AMOFFDisablePanels(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab		
			if(eciTab.setDisablePanels("OFF"))
			{
				report.LogPASS(driver,logger, "Disable Panels(show in content) is turned OFF successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Disable Panels(show in content) is NOT turned OFF");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}	
	
	//(@Author:Durga)To create Minimum order value for all in AM
	public void AMCreateMOV4All(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		String URL = data.getTestData(tcId, "URL");	
		String movAmt = data.getTestData(tcId, "AM_MOVAmount");
	
		genfunc.openNewBrowserTab();//to open new tab
		//to navigate to MOV and add a new MOV
		amOrdDelTab.gotoMinimumOrderValuePage(URL);
		//To remove all the MOV if available
		int noOfMovRecDeleted = amOrdDelTab.deleteMOV();
		if(noOfMovRecDeleted>0)
		{
		report.LogINFO(driver,logger, "Number of MOV deleted are - "+noOfMovRecDeleted);
		}
		
		//to add new MOV
		amOrdDelTab.addNewMOV();
		amOrdDelTab.selectAllUsersMOV();		
		amOrdDelTab.enterMOVAmt(movAmt);
		report.LogINFO(driver,logger, "New MOV to be created is filled in with valid data");
		appMenu.save();//to save the MOV
		report.LogPASS(driver,logger, "New MOV is created successfully in AM");			
		genfunc.closeActiveBrowserTab();//to close the tab	
	}
	
	//(@Author:Durga)To delete all Minimum order values in AM
	public void AMDeleteAllMOV(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		String URL = data.getTestData(tcId, "URL");	
			
		genfunc.openNewBrowserTab();//to open new tab
		//to navigate to MOV and add a new MOV
		amOrdDelTab.gotoMinimumOrderValuePage(URL);
		//To remove all the MOV if available
		int noOfMovRecDeleted = amOrdDelTab.deleteMOV();
		if(noOfMovRecDeleted>0)
		{
		report.LogINFO(driver,logger, "Number of MOV deleted are - "+noOfMovRecDeleted);
		}
		genfunc.closeActiveBrowserTab();//to close the tab	
	}

	//(@Author:Durga)To set Multiple Invoice Add - Select and Edit option in user level
	public void AMSetUserMultipleInvoiceAdd(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String userID = data.getTestData(tcId, "LoginID");
		
		try {
			genfunc.openNewBrowserTab();
			useraccTab.gotoUSerPage(URL);
			appMenu.searchAndEdit(userID);
			if(!useraccTab.selectUserMultipleInvoiceAddOpt("3"))
			{
				report.LogFATAL(driver,logger, "Multiple Invoice address is NOT Set as expected for the user");
			}
			report.LogPASS(driver,logger, "Multiple Invoice Address is turned ON and set to Select and Edit option Successfully");
			appMenu.save();
			genfunc.closeActiveBrowserTab();
		} catch (Exception e) {	
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}
	}		
	
	//(@Author:Durga)To set Substitute products to NO option
	public void AMSetNoSubstitutePdt(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab		
			if(eciTab.selectSubstituteProducts("0"))
			{
				report.LogPASS(driver,logger, "Substitue products is set to NO option successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Substitue products is NOT set to NO option in Supplier");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();	
	}
 
	//(@Author:Durga)To set Substitute products to Only on Header level option
	public void AMSetHeaderlevelSubstitutePdt(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab		
			if(eciTab.selectSubstituteProducts("1"))
			{
				report.LogPASS(driver,logger, "Substitue products is set to Only on Header level option successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Substitue products is NOT set to Only on Header level option in Supplier");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();	
	}
	
	//(@Author:Durga)To set Substitute products on Header and Line level option
	public void AMSetHeaderLinelevelSubstitutePdt(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab		
			if(eciTab.selectSubstituteProducts("2"))
			{
				report.LogPASS(driver,logger, "Substitue products is set on Header and Line level option successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Substitue products is NOT set on Header and Line level option in Supplier");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();	
	}
	
	//(@Author:Durga)To enable shared setting 
	public void AMSetSharedOrderTemplate(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");					
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);
			eciTab.gotoSupplierOrdNDelSettingsTab();	
			
			if(eciTab.setShareOrderTemplate("ON"))
			{
				report.LogPASS(driver,logger, "Share order templates is turned ON successfully in Supplier");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Share order templates is NOT turned ON ");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();	
	}
	
	//(@Author:Durga)To check for shared order template Icon
	public void AMCheckOrderTemplateShareIcon(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");		
		String supCode = data.getTestData(tcId, "AM_SupplierCode");	
		String OrderTemplateType = data.getTestData(tcId, "OTGroupName");		
		String SharedOrderTemplateName = data.getTestData(tcId, "SOTName");	
		String[] status = {"ON","OFF"};
		genfunc.openNewBrowserTab();
		
		for(int i=0;i<2;i++)
		{
			ExtentTest childlogger = setReport.startTest("Shared Order Template settings turned - "+status[i]);
			eciTab.gotoSupplierEditSpecific(URL, supCode);
			eciTab.gotoSupplierOrdNDelSettingsTab();
			try {
				switch(status[i])			
				{
					case "ON":							
						if(eciTab.setShareOrderTemplate("ON"))
						{
							report.LogPASS(driver,childlogger, "Share order templates is turned ON successfully in Supplier");
							appMenu.save();
							amMerchandiseTab.gotoOrderTemplatePage(URL);
							if(amMerchandiseTab.checkShareOrderTemplateIcon(URL, OrderTemplateType, SharedOrderTemplateName))
							{
								report.LogPASS(driver,childlogger, "Share Icon is available for OrderTemplate - "+SharedOrderTemplateName);				
							}else
							{
								report.LogFAIL(driver,childlogger, "Share Icon is NOT available for OrderTemplate - "+SharedOrderTemplateName);
							}	
						}else
						{
							report.LogFATAL(driver,childlogger, "Share order templates is NOT turned ON ");
						}break;
					
					case "OFF":
						if(eciTab.setShareOrderTemplate("OFF"))
						{
							report.LogPASS(driver,childlogger, "Share order templates is turned OFF successfully in Supplier");
							appMenu.save();
							amMerchandiseTab.gotoOrderTemplatePage(URL);
							if(!amMerchandiseTab.checkShareOrderTemplateIcon(URL, OrderTemplateType, SharedOrderTemplateName))
							{
								report.LogPASS(driver,childlogger, "Share Icon is not available for OrderTemplate - "+SharedOrderTemplateName);				
							}else
							{
								report.LogFAIL(driver,childlogger, "Share Icon is available for OrderTemplate - "+SharedOrderTemplateName);
							}	
						}else
						{
							report.LogFATAL(driver,childlogger, "Share order templates is NOT turned OFF ");
						}break;
					}
				}catch (Exception e) {			
				report.LogFATAL(driver,childlogger, "WebElement/WebPage properties not found");
				e.printStackTrace();
				}logger.appendChild(childlogger);}		
			genfunc.closeActiveBrowserTab();
			
	}
	
	//(@Author:Durga)To set Webservice ON
	public void AMSetONWebservice(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");	
		String WSPartyName = data.getTestData(tcId, "AM_WSPartyName");	
		String WSType = data.getTestData(tcId, "AM_WSType");	
		
		genfunc.openNewBrowserTab();
		try {
			
			eciTab.gotoAMWebservice(URL);	
			
			if(eciTab.setWebservice("ON", WSPartyName, WSType))
			{
				report.LogPASS(driver,logger, WSPartyName+" "+WSType+" Webservice has been turned ON successfully");
			}else
			{
				report.LogFATAL(driver,logger,  WSPartyName+" "+WSType+" Webservice has NOT been turned ON");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();	
	}
	
	
	//(@Author:Durga)To Turn ON Segmented Order Reference in Supplier
	public void AMONSegmentedOrderRef(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");	
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierOrdNDelSettingsTab();//to move to Orders and delivery Settings tab		
			if(eciTab.setSegmentedOrderReference("ON"))
			{
				report.LogPASS(driver,logger, "Segmented Order Reference in Supplier is turned ON successfully");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Segmented Order Reference in Supplier is NOT turned ON successfully");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}
	
	//(@Author:Durga)To Turn OFF Segmented Order Reference in Supplier
	public void AMOFFSegmentedOrderRef(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");	
		
		try {
			genfunc.openNewBrowserTab();
			eciTab.gotoSupplierEditSpecific(URL, supCode);//Go to general edit page of supplier
			eciTab.gotoSupplierOrdNDelSettingsTab();//to move to Orders and delivery Settings tab		
			if(eciTab.setSegmentedOrderReference("OFF"))
			{
				report.LogPASS(driver,logger, "Segmented Order Reference in Supplier is turned OFF successfully");
				appMenu.save();
			}else
			{
				report.LogFATAL(driver,logger, "Segmented Order Reference in Supplier is NOT turned OFF successfully");
			}		
		} catch (Exception e) {			
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
			genfunc.closeActiveBrowserTab();
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}










































