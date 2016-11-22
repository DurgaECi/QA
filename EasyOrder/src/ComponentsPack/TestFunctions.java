package ComponentsPack;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import DatabasePack.DatabaseConnection;
import ExcelPack.TestData;
import ReportPack.TestReport;
import SupportLib.TestBrowser;


/**
 * @author - Durga Suresh
 * Description - All methods of the application
 *
 */

public class TestFunctions  {
	
	private TestData data;
	private WebDriver driver;
	public  ExtentReports reporter;
	private TestReport report = new TestReport();
	public TestBrowser browser = new TestBrowser();
	private BeforeLoginPage loginPage;
	private HomePage homePage;
	private AM_General amGenfunc;
	private AM_ECiTab amEciTab;
	private AM_UserAccountTab amUserAccTab;
	private AM_CatalogTab amCatalogTab;
	private GeneralFunctions genfunc;
	private INFOPR3_4Page infopr;
	private CheckoutPages checkout;
	private AM_OrdersAndDeliveryTab amOrdDelTab;
	private AM_DesignTab desTab;
	private TestAMFunctions AMFunc;
	private DatabaseConnection dbConnect = new DatabaseConnection();
	
	//To instantiate data sheet and other related dependent classes
	public TestFunctions(ExcelPack.TestData testData,WebDriver driver) {
		this.data = testData;	
		this.driver = driver;  		
 		this.loginPage = PageFactory.initElements(driver, BeforeLoginPage.class);
 		this.homePage = PageFactory.initElements(driver, HomePage.class);
 		this.amGenfunc = PageFactory.initElements(driver, AM_General.class);
 		this.amEciTab = PageFactory.initElements(driver, AM_ECiTab.class);
 		this.amUserAccTab = PageFactory.initElements(driver, AM_UserAccountTab.class);
 		this.amCatalogTab = PageFactory.initElements(driver, AM_CatalogTab.class);
 		this.infopr= PageFactory.initElements(driver, INFOPR3_4Page.class);
 		this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
 		this.checkout = PageFactory.initElements(driver, CheckoutPages.class);
 		this.amOrdDelTab = PageFactory.initElements(driver, AM_OrdersAndDeliveryTab.class);
 		this.desTab = PageFactory.initElements(driver, AM_DesignTab.class);
 		AMFunc = new TestAMFunctions(data, driver);
 		
	}	
		
	//(@Author:Durga)To launch the URL
	public void LaunchUrl(String tcId, ExtentTest logger,ExtentReports setReport)
    {  
		String URL = data.getTestData(tcId, "URL");

		//to launch URL
		loginPage.launchUrl(URL);
		String title = genfunc.pageTitle();
		if (!((title.contains("Inloggen")|(title.contains("Home")|(title.contains("Login"))))))		
		{
			report.LogFATAL(driver,logger,"URL not Launched");
		}else{
			report.LogPASS(driver,logger,"Launched the URL successfully");
		}
    }
	
	//(@Author:Durga)To login to the application
	public void Login(String tcId, ExtentTest logger,ExtentReports setReport) 
    { 
		String userName = data.getTestData(tcId, "LoginID");
		String passWord = data.getTestData(tcId, "Password");
		//Login	
		try{
			loginPage.login(userName, passWord);		
			if(!(genfunc.pageTitle()).contains("Home"))
			{
				String errorMsg = loginPage.checkLoginError();
				if(errorMsg == null)
				{
				report.LogFATAL(driver,logger, "NOT Logged-In");
				}else{
				report.LogFATAL(driver,logger, "Login Credentials are Invalid.Error message captured as-"+errorMsg);					
				}
			}
				report.LogPASS(driver,logger, "Logged In successfully"); 
			} catch (Exception e) {
				report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
				e.printStackTrace();
			}
	}

	//(@Author:Durga)To logout	
	public void Logout(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		try{
			homePage.gotoHomePage(URL);
			homePage.logout();
			if(!((genfunc.pageTitle()).contains("Uitloggen")|(genfunc.pageTitle()).contains("Logout")))
			{
				report.LogFATAL(driver,logger, "NOT Logged-OUT");  
			}
				report.LogPASS(driver,logger, "Logged OUT successfully");			
			} catch (Exception e) {
				report.LogFATAL(driver,logger, "Logout button not available");  
				e.printStackTrace();				
			}
	}
	
	//(@Author:Durga)To quit and close browser
	public void CloseBrowser()
	{
		genfunc.closeBrowser();		
	}
	
	//(@Author:Durga)To close active browser tab
	public void CloseActiveBrowserTab(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		int oldtabsize = genfunc.browserTabsSize();
		genfunc.closeActiveBrowserTab();	
		int newtabsize = genfunc.browserTabsSize();
		if (!(oldtabsize==(newtabsize+1)))
		{		
			report.LogFATAL(driver,logger, "Active Browser tab is not closed");
		}
		report.LogPASS(driver,logger, "Active Browser tab is closed successfully");
	}
	
	//(@Author:Durga)To open new browser tab
	public void OpenNewBrowserTab(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		int oldtabsize = genfunc.browserTabsSize();
		genfunc.openNewBrowserTab();
		
		int newtabsize = genfunc.browserTabsSize();
		if (!(oldtabsize==(newtabsize-1)))
		{		
			report.LogFATAL(driver,logger, "New Browser tab is NOT opened successfully");
		}
		
		report.LogPASS(driver,logger, "New Browser tab is opened successfully");	
	}
  
	//(@Author:Durga)To validate the Display split functionality across 7 hierarchy levels
	public void DisplaySplit(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		ExtentTest childLogger = null;		
		int scenarionCounter = 0;
		String webShopUrl = data.getTestData(tcId, "URL");			
		String supCode = data.getTestData(tcId, "AM_SupplierCode");
		String aPGCode = data.getTestData(tcId, "AM_APGCode");
		String accGrpcode = data.getTestData(tcId, "AM_AccGrpCode");
		String AccNo = data.getTestData(tcId, "AM_AccNo");
		String pdtGrpCode = data.getTestData(tcId, "AM_PdtGrpCode");
		String pdtId = data.getTestData(tcId, "PdtID");
		String shipAddCode = data.getTestData(tcId, "AM_ShipAddNo");
		boolean Expected;
		boolean Actual;		
		String optionPdtSelected;String optionPdtGrpSelected;String optionShipAddSelected;String optionaccSelected;String optionaccgrpSelected;	String optionAPGSelected;
		int breaki = 0;	int breakj = 0;	int breakk = 0;	int breakl = 0;	int breakm = 0;	int breakn = 0;
		
		//to open new browser tab for AM actions
		genfunc.openNewBrowserTab();		
		try{
		for(int o=0;o<3;o++)//---Supplier level---
		{	
			amEciTab.gotoSupplierEditGenenral(webShopUrl, supCode);//to move to edit Specific supplier page			
			amEciTab.gotoSupplierEditSpecificSettingsTab();//to move to SpecificSettings tab			
			if(!amEciTab.labelSupplierDisplaySplit.isDisplayed())//to check for display split
			{
				report.LogFATAL(driver,logger,"Dispaly split field is not available in Supplier Edit Specific settings");
			}			
			Select supDisplaySplit = genfunc.dropdownlist(amEciTab.dropdownDisplaySplit);
			String optionSupSelected = genfunc.selectDropdownOption(o, supDisplaySplit);				
			//report.LogINFO(driver, logger, "Display Split selected in supplier level is - "+optionSupSelected);				
			amGenfunc.save();
			
			for (int n=0;n<3;n++) //----------Address Price Group---------
			 {
				amUserAccTab.gotoEditAPG(webShopUrl, aPGCode);
				if(!amUserAccTab.labelAPGDisplaySplit.isDisplayed())//to check for display split
				{
						report.LogFATAL(driver,logger,"Dsipaly split field is not available in Account price group page");
				}
				Select apgDisplaySplit = genfunc.dropdownlist(amUserAccTab.dropdownAPGDisplaySplit);
				if(o==0)
				{
					optionAPGSelected = genfunc.selectDropdownOption(n, apgDisplaySplit);
				}else{
					optionAPGSelected = genfunc.selectDropdownOption(0, apgDisplaySplit);
					breakn++;
				}
				if(breakn>1)
				{
					breakn=0;
					break;
				}
				//report.LogINFO(driver, logger, "ii.Display Split selected in AccountPriceGroup level is - "+optionAPGSelected);				
				amGenfunc.save();
				
				for (int m=0;m<3;m++) //----------Account Group---------
				 {
					amUserAccTab.gotoEditAccGrp(webShopUrl, accGrpcode);
					if(!amUserAccTab.labelAccGrpDisplaySplit.isDisplayed())//to check for display split
					{
							report.LogFATAL(driver,logger,"Dispaly split field is not available in Account group page");
					}
					Select accgrpDisplaySplit = genfunc.dropdownlist(amUserAccTab.dropdownAccGrpDisplaySplit);
					if(n==0 && o==0)
					{
						optionaccgrpSelected = genfunc.selectDropdownOption(m, accgrpDisplaySplit);
					}else{
						optionaccgrpSelected = genfunc.selectDropdownOption(0, accgrpDisplaySplit);
						breakm++;
					}
					if(breakm>1)
					{
						breakm=0;
						break;
					}
					//report.LogINFO(driver, logger, "iii.Display Split selected in AccountGroup level is - "+optionaccgrpSelected);				
					amGenfunc.save();
					

					for (int l=0;l<3;l++) //----------Account ----------
					 {						
						amUserAccTab.gotoAccountPage(webShopUrl);
						amGenfunc.searchAndEdit(AccNo);
						if(!amUserAccTab.labelAccDisplaySplit.isDisplayed())//to check for display split
						{
								report.LogFATAL(driver,logger,"Dispaly split field is not available in Account page");
						}
						Select accDisplaySplit = genfunc.dropdownlist(amUserAccTab.dropdownAccDisplaySplit);
						if(m==0 && n==0 && o==0)
						{
							optionaccSelected = genfunc.selectDropdownOption(l, accDisplaySplit);
						}else{
							optionaccSelected = genfunc.selectDropdownOption(0, accDisplaySplit);
							breakl++;
						}
						if(breakl>1)
						{
							breakl=0;
							break;
						}
						//report.LogINFO(driver, logger, "iv.Display Split selected in Account level is - "+optionaccSelected);				
						amGenfunc.save();						
						
						 for(int k=0;k<3;k++) //Shipping Address-----------------------------------------------------------------------------
				 		 {
							amUserAccTab.gotoShippingAddressPage(webShopUrl);
							amGenfunc.searchAndSelect(AccNo);
							amGenfunc.getsubFrame();
							amGenfunc.searchAndEdit(shipAddCode);
							
							if(!amUserAccTab.labelShipAddDisplaySplit.isDisplayed())//to check for display split
							{
									report.LogFATAL(driver,logger,"Dispaly split field is not available in Shipping Address page");
							}
							Select shipAddDisplaySplit = genfunc.dropdownlist(amUserAccTab.dropdownShipAddDisplaySplit);
							
							if(l==0 && m==0 && n==0 && o==0)
							{
								optionShipAddSelected = genfunc.selectDropdownOption(k, shipAddDisplaySplit);
							}else{
								optionShipAddSelected = genfunc.selectDropdownOption(0, shipAddDisplaySplit);
								breakk++;
							}
							if(breakk>1)
							{
								breakk=0;
								break;
							}
							
							//report.LogINFO(driver, logger, "v.Display Split selected in Shipping Address level is - "+optionShipAddSelected);				
							amGenfunc.save();
							
							 for(int j=0;j<3;j++) //Product Group-----------------------------------------------------------------------------
					 		 {
								amCatalogTab.gotoEditPdtGrp(webShopUrl, pdtGrpCode);
								if(!amCatalogTab.labelPdtGrpDisplaySplit.isDisplayed())//to check for display split
								{
										report.LogFATAL(driver,logger,"Dispaly split field is not available in Shipping Address page");
								}
								Select pdtGrpDisplaySplit = genfunc.dropdownlist(amCatalogTab.dropdownPdtGrpDisplaySplit);								
								if(k==0 && l==0 && m==0 && n==0 && o==0)
								{
									optionPdtGrpSelected = genfunc.selectDropdownOption(j, pdtGrpDisplaySplit);
								}else{
									optionPdtGrpSelected = genfunc.selectDropdownOption(0, pdtGrpDisplaySplit);
									breakj++;
								}
								if(breakj>1)
								{
									breakj = 0;
									break;
								}
								//report.LogINFO(driver, logger, "vi.Display Split selected in Product Group level is - "+optionPdtGrpSelected);				
								amGenfunc.save();
								
								for(int i=0;i<3;i++) //Product-----------------------------------------------------------------------------
						 		 {	
									amCatalogTab.gotoEditPdt(webShopUrl, pdtId);
									if(!amCatalogTab.labelPdtDisplaySplit().isDisplayed())//to check for display split
									{
											report.LogFATAL(driver,logger,"Dispaly split field is not available in Shipping Address page");
									}
									Select pdtDisplaySplit = genfunc.dropdownlist(amCatalogTab.dropdownPdtDisplaySplit());
									if (j==0 && k==0 && l==0 && m==0 && n==0 && o==0)
									{
										optionPdtSelected = genfunc.selectDropdownOption(i, pdtDisplaySplit);	
									}else{
										optionPdtSelected = genfunc.selectDropdownOption(0, pdtDisplaySplit);
										breaki++;
									}
									if(breaki>1)
									{
										breaki=0;
										break;
									}
									
									//report.LogINFO(driver, logger, "vii.Display Split selected in Product level is - "+optionPdtSelected);				
									amGenfunc.save();
									String message = "AM Settings: Supplier-"+optionSupSelected+" & AddressPriceGroup-"+optionAPGSelected+" & AccountGroup-"+optionaccgrpSelected+" & Account-"+optionaccSelected+" & ShippingAddress-"+optionShipAddSelected+" & ProductGroup-"+optionPdtGrpSelected+" & Product-"+optionPdtSelected;
									childLogger = setReport.startTest("Scenario-"+ ++scenarionCounter+" ["+message+"]");									
									if (optionPdtSelected.equalsIgnoreCase("Yes"))// Product level-----------------------------------------------------
						   			{
						   				Expected = true;
						   				genfunc.switchBrowserTab("1");
						   				Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
						   				genfunc.compareActExp(Expected, Actual,childLogger);	
						   				genfunc.switchBrowserTab("2");
						   			}
						   			else if (optionPdtSelected.equalsIgnoreCase( "No"))
					   				{
						   				Expected = false;
						   				genfunc.switchBrowserTab("1");
						   				Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
						   				genfunc.compareActExp(Expected, Actual,childLogger);	
						   				genfunc.switchBrowserTab("2");
					   					
					   				}
						   			else if(optionPdtSelected.equalsIgnoreCase("Default"))
						   			{
						   				
						   				if(optionPdtGrpSelected.equalsIgnoreCase("Yes"))// Product group level-----------------------------------------------------
						   				{
						   					Expected = true;
						   					genfunc.switchBrowserTab("1");
						   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
							   				genfunc.compareActExp(Expected, Actual,childLogger);	
							   				genfunc.switchBrowserTab("2");
						   				}
						   					
						   				else if(optionPdtGrpSelected.equalsIgnoreCase("No"))
						   				{
						   					Expected = false;
						   					genfunc.switchBrowserTab("1");
						   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
							   				genfunc.compareActExp(Expected, Actual,childLogger);	
							   				genfunc.switchBrowserTab("2");
						   				}
						   				else if (optionPdtGrpSelected.equalsIgnoreCase("Default"))
						   				{
						   					if(optionShipAddSelected.equalsIgnoreCase("Yes"))// Shipping Address level-----------------------------------------------------
							   				{
							   					Expected = true;
							   					genfunc.switchBrowserTab("1");
							   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
								   				genfunc.compareActExp(Expected, Actual,childLogger);	
								   				genfunc.switchBrowserTab("2");
							   				}
							   					
							   				else if(optionShipAddSelected.equalsIgnoreCase("No"))
							   				{
							   					Expected = false;
							   					genfunc.switchBrowserTab("1");
							   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
								   				genfunc.compareActExp(Expected, Actual,childLogger);	
								   				genfunc.switchBrowserTab("2");
							   				}
							   				else if (optionShipAddSelected.equalsIgnoreCase("Default"))
							   				{
							   					if(optionaccSelected.equalsIgnoreCase("Yes"))// Account level----------------------------------------------------
								   				{
								   					Expected = true;
								   					genfunc.switchBrowserTab("1");
								   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
									   				genfunc.compareActExp(Expected, Actual,childLogger);	
									   				genfunc.switchBrowserTab("2");
								   				}
								   					
								   				else if(optionaccSelected.equalsIgnoreCase("No"))
								   				{
								   					Expected = false;
								   					genfunc.switchBrowserTab("1");
								   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
									   				genfunc.compareActExp(Expected, Actual,childLogger);	
									   				genfunc.switchBrowserTab("2");
								   				}
								   				else if (optionaccSelected.equalsIgnoreCase("Default"))
								   				{

								   					if(optionaccgrpSelected.equalsIgnoreCase("Yes"))// Account Group level---------------------------------------------------
									   				{
									   					Expected = true;
									   					genfunc.switchBrowserTab("1");
									   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
										   				genfunc.compareActExp(Expected, Actual,childLogger);	
										   				genfunc.switchBrowserTab("2");
									   				}
									   					
									   				else if(optionaccgrpSelected.equalsIgnoreCase("No"))
									   				{
									   					Expected = false;
									   					genfunc.switchBrowserTab("1");
									   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
										   				genfunc.compareActExp(Expected, Actual,childLogger);	
										   				genfunc.switchBrowserTab("2");
									   				}
									   				else if (optionaccgrpSelected.equalsIgnoreCase("Default"))
									   				{

									   					if(optionAPGSelected.equalsIgnoreCase("Yes"))// Address price level---------------------------------------------------
										   				{
										   					Expected = true;
										   					genfunc.switchBrowserTab("1");
										   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
											   				genfunc.compareActExp(Expected, Actual,childLogger);	
											   				genfunc.switchBrowserTab("2");
										   				}
										   					
										   				else if(optionAPGSelected.equalsIgnoreCase("No"))
										   				{
										   					Expected = false;
										   					genfunc.switchBrowserTab("1");
										   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
											   				genfunc.compareActExp(Expected, Actual,childLogger);	
											   				genfunc.switchBrowserTab("2");
										   				}
										   				else if (optionAPGSelected.equalsIgnoreCase("Default"))
										   				{

										   					if(optionSupSelected.equalsIgnoreCase("Yes"))// Supplier level--------------------------------------------------
											   				{
											   					Expected = true;
											   					genfunc.switchBrowserTab("1");
											   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
												   				genfunc.compareActExp(Expected, Actual,childLogger);	
												   				genfunc.switchBrowserTab("2");
											   				}											   					
											   				else 
											   				{
											   					Expected = false;	
											   					genfunc.switchBrowserTab("1");
											   					Actual = infopr.searchVerifyProductOrderBtn(pdtId);						   				
												   				genfunc.compareActExp(Expected, Actual,childLogger);	
												   				genfunc.switchBrowserTab("2");											   											   					
											   			    }
										   	}}}}}}
									logger.appendChild(childLogger);
									}
								}
							 }
						 }										
					 }
				 }
			}		
	}catch (Exception e) {
		report.LogFATAL(driver,logger, "WebPage/Webelement not available"); 		
		logger.appendChild(childLogger);
		e.printStackTrace();
    }									
	
	
}
	
	//(@Author:Durga)To validate the InAssortment Indicator Icon across 3 hierarchy levels
	public void InAssortmentIndicator(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		ExtentTest childLogger = null;		
		int scenarionCounter = 0;
		String webShopUrl = data.getTestData(tcId, "URL");
		String supCode = data.getTestData(tcId, "AM_SupplierCode");
		String AccNo = data.getTestData(tcId, "AM_AccNo");
		String userID = data.getTestData(tcId, "LoginID");
		String pdtId = data.getTestData(tcId, "PdtID");
		boolean Expected;
		boolean Actual;
		
		//to open new browser tab for AM actions
		genfunc.openNewBrowserTab();		
		
		try {
			for(int k=1;k<3;k++)//---Supplier level---
			{				
				amEciTab.gotoSupplierEditSpecific(webShopUrl, supCode);//to move to edit Specific supplier page			
				amEciTab.gotoSupplierSpecSpecificSettingsTab();//to move to SpecificSettings tab			
				Select supInAssortmentInd = genfunc.dropdownlist(amEciTab.dropdownSupplierInAssortmentInd);
				String optionSupSelected = genfunc.selectDropdownOption(k, supInAssortmentInd);								
				amGenfunc.save();
				
				for(int j=0;j<3;j++)//---Account level---
				{				
					amUserAccTab.gotoAccountPage(webShopUrl);//go to account page
					amGenfunc.searchAndEdit(AccNo);//go to edit page of account
					amUserAccTab.gotoAccountEXTRASettings();//go to extra settings		
					Select accInAssortmentInd = genfunc.dropdownlist(amUserAccTab.dropdownAccountInAssortmentInd);
					String optionAccSelected = genfunc.selectDropdownOption(j, accInAssortmentInd);								
					amGenfunc.save();
					
					for(int i=0;i<3;i++)//---User level---
					{				
						amUserAccTab.gotoUSerPage(webShopUrl);//go to User page
						amGenfunc.searchAndEdit(userID);
						amUserAccTab.gotoUserAdvancedSettings();								
						Select userInAssortmentInd = genfunc.dropdownlist(amUserAccTab.dropdownUserInAssortmentInd);
						String optionUserSelected = genfunc.selectDropdownOption(i, userInAssortmentInd);								
						amGenfunc.back();
						amGenfunc.save();
						scenarionCounter++;
						String message = "AM Settings: Supplier-"+optionSupSelected+" & Account-"+optionAccSelected+" & User-"+optionUserSelected;
						childLogger = setReport.startTest("Scenario-"+scenarionCounter+" ["+message+"]");	
						
						if (optionUserSelected.equalsIgnoreCase("Yes")|optionUserSelected.equalsIgnoreCase("Ja"))// User level-----------------------------------------------------
			   			{
			   				Expected = true;
			   				genfunc.switchBrowserTab("1");
			   				Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
			   				genfunc.compareActExp(Expected, Actual,childLogger);	
			   				genfunc.switchBrowserTab("2");
			   			}
			   			else if (optionUserSelected.equalsIgnoreCase( "No")|optionUserSelected.equalsIgnoreCase( "Nee"))
		   				{
			   				Expected = false;
			   				genfunc.switchBrowserTab("1");
			   				Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
			   				genfunc.compareActExp(Expected, Actual,childLogger);	
			   				genfunc.switchBrowserTab("2");
		   					
		   				}
			   			else if(optionUserSelected.equalsIgnoreCase("Default")|optionUserSelected.equalsIgnoreCase("Standaard"))
			   			{
			   				
			   				if(optionAccSelected.equalsIgnoreCase("Yes")|optionAccSelected.equalsIgnoreCase("Ja"))// Account group level-----------------------------------------------------
			   				{
			   					Expected = true;
			   					genfunc.switchBrowserTab("1");
			   					Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
				   				genfunc.compareActExp(Expected, Actual,childLogger);	
				   				genfunc.switchBrowserTab("2");
			   				}
			   					
			   				else if(optionAccSelected.equalsIgnoreCase("No")|optionAccSelected.equalsIgnoreCase("Nee"))
			   				{
			   					Expected = false;
			   					genfunc.switchBrowserTab("1");
			   					Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
				   				genfunc.compareActExp(Expected, Actual,childLogger);	
				   				genfunc.switchBrowserTab("2");
			   				}
			   				else if (optionAccSelected.equalsIgnoreCase("Default")|optionAccSelected.equalsIgnoreCase("Standaard"))
			   				{
			   					if(optionSupSelected.equalsIgnoreCase("Default yes")|optionSupSelected.equalsIgnoreCase("Standaard ja"))// Supplier Address level-----------------------------------------------------
				   				{
				   					Expected = true;
				   					genfunc.switchBrowserTab("1");
				   					Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
					   				genfunc.compareActExp(Expected, Actual,childLogger);	
					   				genfunc.switchBrowserTab("2");
				   				}
				   					
				   				else if(optionSupSelected.equalsIgnoreCase("Default no")|optionSupSelected.equalsIgnoreCase("Standaard nee"))
				   				{
				   					Expected = false;
				   					genfunc.switchBrowserTab("1");
				   					Actual = infopr.searchVerifyProductInAssIcon(pdtId);						   				
					   				genfunc.compareActExp(Expected, Actual,childLogger);	
					   				genfunc.switchBrowserTab("2");
				   				}}}logger.appendChild(childLogger);
						}
				}
			}
	}catch (Exception e) {
		report.LogFATAL(driver,childLogger,"WebElement is not available");
		logger.appendChild(childLogger);
		e.printStackTrace();		
	}		
	}
		
	//(@Author:Durga)To validate the function of adding a product to basket
	public int AddPdt2Basket(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		
		String pdtId = data.getTestData(tcId, "PdtID");
		String URL = data.getTestData(tcId, "URL");	
		String[] pdtIds = {pdtId};
		try {
			homePage.gotoHomePage(URL);
			checkout.DeleteBasket(URL);
			
			if(pdtId.contains(";"))
			{
				pdtIds = pdtId.split(";");
			}
			
			for(int i=0;i<pdtIds.length;i++)
			{
				if(!infopr.searchAddPdttoBasket(pdtIds[i]))
				{
					report.LogFATAL(driver,logger, "Product-"+pdtIds[i]+" is not added to basket, check if Product-ID is correct");
				}		
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			report.LogFATAL(driver,logger,"WebElement is not available");
		}
		return pdtIds.length;
	}	
	
	//(@Author:Durga)To navigate to Order closing screen in the basket
	public void GotoOrderClosingPage(String tcId, ExtentTest logger,ExtentReports setReport)
	{		
		String URL = data.getTestData(tcId, "URL");		
		try {
			homePage.gotoHomePage(URL);
			//To go to Basket page	
			if(!checkout.gotoBasket(URL))
			{
				report.LogFATAL(driver,logger, "Page not navigated to Basket page");	
			}	
			//To go to Order04 page from checkout page
			if(!checkout.gotoOrder04Page())
			{
				report.LogFATAL(driver,logger, "Page not navigated to Order04 page");
			}
			//To go to Order closing page from Order04 page		
			if(!checkout.gotoOrderClosingPage())
			{
				report.LogFATAL(driver,logger, "Page not navigated to Order Closing page");
			}
			} catch (Exception e) {
				report.LogFATAL(driver,logger,"WebElement is not available");
				e.printStackTrace();
			}		
	}
	
	//(@Author:Durga)To validate the functionality of placing order with Account Payment method
	public void DefaultPlaceOrder(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		try {			
			//To select Payment Type
			checkout.selectPaymentType("ACC");
			
			//To Place Order
			checkout.clickPlaceOrder();
			
			if(checkout.verifyOrderConfirmtnPage())
			{
				report.LogPASS(driver,logger, "Payment has been done successfully for the Order");
			}else if (!checkout.verifyOrderConfirmtnPage())
			{
				report.LogWARN(driver,logger, "Payment has been failed for the Order");
			}else
			{
				report.LogFATAL(driver,logger,"Order placement has not been done");
			}
		} catch (Exception e) {
			report.LogFATAL(driver,logger,"WebElement is not available");
			e.printStackTrace();
		}
		
	}	
	
	//(@Author:Durga)To validate the payment methods - NETONE,PAYPAL,TNS Pay
	public void ValidatePaymentMethods(String tcId, ExtentTest logger,ExtentReports setReport)
	{		
		String PaymentMethods[] = {"NE1","TNS","PAP"};	
		String URL = data.getTestData(tcId, "URL");
		String CCNo = "5123456789012346";
		ExtentTest childLogger = null;		
		int scenarionCounter = 0;
		
		try {		
			for (int i=0;i<3;i++)
			{	
				scenarionCounter++;
				switch(PaymentMethods[i])
				{
				case "NE1":
					childLogger = setReport.startTest("Scenario "+scenarionCounter+" - "+PaymentMethods[i]);	
					//Add product and got to order closing page
					AddPdt2Basket(tcId, childLogger, setReport);
					GotoOrderClosingPage(tcId, childLogger, setReport);
					//To select Payment Type
					checkout.selectPaymentType(PaymentMethods[i]);		
					report.LogPASS(driver,childLogger, PaymentMethods[i]+" - payment method has been selected");
					//To Place Order
					checkout.clickPlaceOrder();
					//To capture NE1 page
					if(!checkout.verifyNE1Page())
					{
						report.LogFAIL(driver,childLogger,"Page not navigated to NE1 payment page");
					}
					report.LogPASS(driver,childLogger, "Page navigated to NE1 payment page successfully");
					//to pay via NETONE payment service
					if(checkout.validateNE1(CCNo, "May - 05", "2017"))
					{
					//To verify order placement confirmation						
					if(!checkout.verifyOrderConfirmtnPage())
					{
						report.LogFAIL(driver,childLogger,"Order placement with NE1 Failure - Error in Order confirmation Page");
					}else{
						report.LogPASS(driver,childLogger, "Order Placed  with NE1 successfully");
					}
					}else{
						report.LogFATAL(driver,childLogger,"Credit/Debit card details are wrong");
					}
					logger.appendChild(childLogger);
					break;
					
				case "TNS":
					
					String scenarios[] = {"REGISTER","TOKEN","DELETE"};												
					for (int j=0;j<3;j++)
					{
						childLogger = setReport.startTest("Scenario "+scenarionCounter+"."+(j+1)+" - "+PaymentMethods[i]+" via "+scenarios[j]);		
						//To go to home Page
						homePage.gotoHomePage(URL);
						//Add product and got to order closing page
						AddPdt2Basket(tcId, childLogger, setReport);
						GotoOrderClosingPage(tcId, childLogger, setReport);						
						//To select Payment Type
						checkout.selectPaymentType(PaymentMethods[i]);	
						//to delete TNS token
						if(scenarios[j].equalsIgnoreCase("Delete"))
						{
							if(!checkout.deleteTNSToken(CCNo))
							{								
								report.LogFAIL(driver,childLogger, "Either " +PaymentMethods[i]+" Token - "+CCNo+" has been NOT deleted or Token was not available");
								logger.appendChild(childLogger);								
							}else{
								report.LogPASS(driver,childLogger, PaymentMethods[i]+" Token - "+CCNo+" has been deleted successfully");
								logger.appendChild(childLogger);
							}break;
						}
						report.LogPASS(driver,childLogger, PaymentMethods[i]+" - payment method has been selected");
						//To select TNS option	
						if(checkout.selectTNSOptn(scenarios[j],CCNo))
						{						
							report.LogPASS(driver,childLogger, "TNS payment will be done via - "+ scenarios[j]);						
							//To Place Order
							checkout.clickPlaceOrder();
							if(scenarios[j].equalsIgnoreCase("Register"))
							{
								if(!checkout.verifyTNSPage())
								{
									report.LogFAIL(driver,childLogger,"Page not navigated to TNS payment regsiteration page");
								}
								report.LogPASS(driver,childLogger, "Page navigated to TNS payment regsiteration page successfully");
								//To register / use token for payment with TNS
								if(!checkout.validateTNS(CCNo, "05", "17"))
								{
									report.LogFATAL(driver,childLogger,"Credit/Debit card details are wrong");						
								}
							}						
							
							//To verify order placement confirmation						
							if(!checkout.verifyOrderConfirmtnPage())
							{
								report.LogFAIL(driver,childLogger,"Order placement  with TNS Failure - Error in Order confirmation Page");
							}
							report.LogPASS(driver,childLogger, "Order Placed  with TNS successfully");						
						}else
						{
							report.LogFAIL(driver,childLogger, "TNS token - "+CCNo+" is not available");							
						}
						logger.appendChild(childLogger);
					}					
				break;	
				
				
				case "PAP":
				childLogger = setReport.startTest("Scenario "+scenarionCounter+" - "+PaymentMethods[i]);	
				//Add product and got to order closing page
				AddPdt2Basket(tcId, childLogger, setReport);
				GotoOrderClosingPage(tcId, childLogger, setReport);
				//To select Payment Type
				checkout.selectPaymentType(PaymentMethods[i]);		
				report.LogPASS(driver,childLogger, PaymentMethods[i]+" - payment method has been selected");
				//To Place Order
				checkout.clickPlaceOrder();			
				
				//To capture PAP page
				if(!checkout.verifyPAPPage())
				{
					report.LogFATAL(driver,childLogger,"Page not navigated to PAP payment page");
				}else
				{
				report.LogPASS(driver,childLogger, "Page navigated to PAP payment page successfully");
				//to pay via PAP payment service
				if(checkout.validatePAP())
				{
				//To verify order placement confirmation						
				if(!checkout.verifyOrderConfirmtnPage())
				{
					report.LogFAIL(driver,childLogger,"Order placement with PAP Failure - Error in Order confirmation Page");
				}else{
					report.LogPASS(driver,childLogger, "Order Placed  with PAP successfully");
				}
				}else{
					report.LogFATAL(driver,childLogger,"Credit/Debit card details are wrong");
				}
				}				
				logger.appendChild(childLogger);
				break;
				
			}		
		}
		} catch (Exception e) {
			logger.appendChild(childLogger);
			report.LogFATAL(driver,logger,"WebElement is not available");
			e.printStackTrace();
		}
		
	}

	//(@Author:Durga)To validate the Minimum order value functionality with all possible creation of MOVs in AM
	public void ValidateMOV(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		ExtentTest childLogger = null;		
		int scenarionCounter = 0;
		String webShopUrl = data.getTestData(tcId, "URL");			
		String aPGCode = data.getTestData(tcId, "AM_APGCode");
		String accGrpcode = data.getTestData(tcId, "AM_AccGrpCode");
		String accNo = data.getTestData(tcId, "AM_AccNo");
		String userId = data.getTestData(tcId, "LoginID");
		String relatedTo[] = {"APG","AG","ACC","USR","ALL","NoMOV"};
		String movAmt = data.getTestData(tcId, "AM_MOVAmount");
		
		try {
			//to validate the MOV by creating various MOVs as possible and check the corresponding in webshop		
			for (int i=0;i<6;i++)
			{				
				scenarionCounter++;
				childLogger = setReport.startTest("Scenario "+scenarionCounter+" - "+relatedTo[i]);
				AddPdt2Basket(tcId, childLogger, setReport);
				genfunc.openNewBrowserTab();//to open new tab
				//to navigate to MOV and add a new MOV
				amOrdDelTab.gotoMinimumOrderValuePage(webShopUrl);
				//To remove all the MOV if available
				int noOfMovRecDeleted = amOrdDelTab.deleteMOV();
				if(noOfMovRecDeleted>0)
				{
				report.LogINFO(driver,childLogger, "Number of MOV deleted are - "+noOfMovRecDeleted);
				}
				
				if(!relatedTo[i].equalsIgnoreCase("NoMOV"))
				{
					//to add new MOV
					amOrdDelTab.addNewMOV();
					switch(relatedTo[i])
					{
						case "APG":
							amOrdDelTab.selectEnterAPGMOV(aPGCode);
							break;
						case "AG":
							amOrdDelTab.selectEnterAGMOV(accGrpcode);
							break;
						case "ACC":
							amOrdDelTab.selectEnterAccMOV(accNo);
							break;
						case "USR":
							amOrdDelTab.selectEnterUsrMOV(userId);
							break;
						case "ALL":
							amOrdDelTab.selectAllUsersMOV();
							break;			
					}
					amOrdDelTab.enterMOVAmt(movAmt);
					report.LogINFO(driver,childLogger, "New MOV to be created is filled in with valid data");
					amGenfunc.save();//to save the MOV
					report.LogPASS(driver,childLogger, "New MOV is created successfully in AM");			
					genfunc.closeActiveBrowserTab();//to close the tab			
					//To go to Basket page	
					if(!checkout.gotoBasket(webShopUrl))
					{
						report.LogFATAL(driver,childLogger, "Page not navigated to Basket page");	
					}
					report.LogPASS(driver,childLogger, "Page navigated to Basket page successfully");	
					//To compare MOV and Total Pdts amount'							
					//to check if MOV pop up/panel is displayed	
					//MOV > TotalPdtAmt
					float minOVAmt = Float.parseFloat(movAmt);
					float totalAmt =Float.parseFloat(checkout.getTotalPdtAmt()); 
					if(minOVAmt > totalAmt)
					{
						//To go to next page
						checkout.gotoOrder04Page();	
						if(!checkout.verifyMinOrderValue())
						{
							report.LogFAIL(driver,childLogger, "Minimum Order value message/pop-up/panel is not displayed when MOV amount("+minOVAmt+") is more than Total Products Amount("+totalAmt+") in basket");	
						}else
						{
						report.LogPASS(driver,childLogger,"Minimum Order value message/pop-up/panel is displayed when MOV amount("+minOVAmt+")  is more than Total Products Amount("+totalAmt+") in basket" );		
						checkout.clickContinueMOV();
						}
						//To make the Total Pdts Amt greater than MOV amount
						checkout.gotoBasket(webShopUrl);
						while(Float.parseFloat(movAmt)>Float.parseFloat(checkout.getTotalPdtAmt()))
						{	
							checkout.addMorePdtQty("01");
						}
					}
					//MOV < TotalPdtAmt
					float MovAmt = Float.parseFloat(movAmt);
					float TotalAmt =Float.parseFloat(checkout.getTotalPdtAmt()); 
					if(MovAmt < TotalAmt)
					{
						//To go to next page
						checkout.gotoOrder04Page();	
						if(checkout.verifyMinOrderValue())
						{						
							report.LogFAIL(driver,childLogger, "Minimum Order value message/pop-up/panel is displayed when MOV amount("+MovAmt+") is lesser than Total Products Amount("+TotalAmt+") in basket");
							checkout.clickContinueMOV();
						}else
						{
						report.LogPASS(driver,childLogger,"Minimum Order value message/pop-up/panel is not displayed when MOV amount("+MovAmt+") is lesser than Total Products Amount("+TotalAmt+") in basket" );
						}
					}			
				}else
				{
					genfunc.closeActiveBrowserTab();//to close the tab			
					//To go to Basket page	
					if(!checkout.gotoBasket(webShopUrl))
					{
						report.LogFATAL(driver,childLogger, "Page not navigated to Basket page");	
					}
					report.LogPASS(driver,childLogger, "Page navigated to Basket page successfully");	
					//To go to next page
					checkout.gotoOrder04Page();	
					//to check if MOV pop up/panel is displayed	
					if(checkout.verifyMinOrderValue())
					{
						report.LogFAIL(driver,childLogger, "Minimum Order value message/pop-up/panel is displayed when MOV is not created or available in AM");
						checkout.clickContinueMOV();
					}else
					{
					report.LogPASS(driver,childLogger,"Minimum Order value message/pop-up/panel is not displayed when MOV is not created or available in AM" );	
					}
				}logger.appendChild(childLogger);
			}
			
		} catch (Exception e) {
			report.LogFATAL(driver,childLogger,"WebElement is not available");		
			e.printStackTrace();
			logger.appendChild(childLogger);
		}
	}
	
	//(@Author:Durga)To validate the Custom header Menu
	public void ValidateCustomHeaderMenu(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		ExtentTest childLogger1 = setReport.startTest("Validate Main Headers in WebShop");	
		ExtentTest childLogger2 = setReport.startTest("Validate Position of Sub-Headers in WebShop");
		ExtentTest childLogger3 = setReport.startTest("Delete created headers");
		String Headers[] = AMFunc.AMAddArrangeHeader(tcId, logger, setReport);
		String Url = data.getTestData(tcId, "URL");
		try {
			genfunc.switchBrowserTab("1");
			homePage.gotoHomePage(Url);
			if(homePage.checkMainHeaderMenu(Headers[0]))
			{
				homePage.clickMainHeaderMenu(Headers[0]);
				report.LogPASS(driver,childLogger1, "Main Header Menu is displayed in the Home Page");
				
				if(homePage.checkSubHeaderMenuPosition(Headers))
				{
					report.LogPASS(driver,childLogger2, "SubHeaderMenu2 -"+Headers[2]+" is displayed above SubHeaderMenu1 -"+Headers[1]+" as arranged in AM" );
				}else
				{
					report.LogFATAL(driver,childLogger2, "SubHeaderMenu2 -"+Headers[2]+" is NOT displayed above SubHeaderMenu1 -"+Headers[1]+" as arranged in AM" );
				}
				
			}else
			{
				report.LogFATAL(driver,childLogger1, "Main Header Menu is NOT displayed in the Home Page");
			}
			
			
			genfunc.switchBrowserTab("2");
			desTab.deleteHeaderMenu(Headers);
			report.LogPASS(driver,childLogger3, "Headers created are deleted in AM");
			
			genfunc.switchBrowserTab("1");
			homePage.gotoHomePage(Url);
			report.LogPASS(driver,childLogger3, "Headers created are deleted successfully in WebShop");
			
			logger.appendChild(childLogger1);
			logger.appendChild(childLogger2);
			logger.appendChild(childLogger3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.appendChild(childLogger1);
			logger.appendChild(childLogger2);
			logger.appendChild(childLogger3);
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
		}
	}
	
	//(@Author:Durga)To validate the supress negative margin functionality
	public void ValidateSupressNegativeMargin(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		
			String pdtId = data.getTestData(tcId, "PdtID");
			int GrossSheetNo = 1;
			int ConsumerSheetNo = 99;
			float minGrossPrice = 20;
			float consumerPrice = 25;
			float maxGrossPrice = 30;
			String scenario[] = {"Gross price < Consumer price","Gross price >=Consumer price "};
			for(int i=0;i<scenario.length;i++)
			{
				ExtentTest childLogger = setReport.startTest(scenario[i]);
				try {
							
					//To set gross price
					if(i==0)
					{
						AMFunc.AMSetGrossConsumerPrice(GrossSheetNo, minGrossPrice, tcId, logger);
					}else if(i==1)
					{
						AMFunc.AMSetGrossConsumerPrice(GrossSheetNo, maxGrossPrice, tcId, logger);
					}
					//To set Consumer price
					AMFunc.AMSetGrossConsumerPrice(ConsumerSheetNo, consumerPrice, tcId, logger);
						
					
					//List View
					infopr.searchProduct(pdtId.substring(0, 3));//search for product
					if(infopr.switchToListView()) //to check for list view
					{
						if((!infopr.ListViewMarginPrice(pdtId).isEmpty())&(i==0))
						{
							report.LogPASS(driver,childLogger, "LIST VIEW:Positive Margin Value-"+infopr.ListViewMarginPrice(pdtId)+" is displayed for the product-"+pdtId);
						}else if((infopr.ListViewMarginPrice(pdtId).isEmpty())&(i==1))
						{
							report.LogPASS(driver,childLogger, "LIST VIEW:Negative Margin Value is not displayed for the product-"+pdtId);
						}
						else
						{
							report.LogFAIL(driver,childLogger, "LIST VIEW: Margin Value is not displayed as expected");
						}
					}		
					//Grid View
					if(infopr.switchToGridView())//to switch to Grid view
					{
						if(!infopr.GridViewMarginPrice(pdtId).isEmpty()&(i==0))
						{
							report.LogPASS(driver,childLogger, "GRIID VIEW:Positive Margin Value-"+infopr.GridViewMarginPrice(pdtId)+" is displayed for the product-"+pdtId);
						}else if((infopr.GridViewMarginPrice(pdtId).isEmpty())&(i==1))
						{
							report.LogPASS(driver,childLogger, "GRIID VIEW:Negative Margin Value is not displayed for the product-"+pdtId);
						}
						else
						{
							report.LogFAIL(driver,childLogger, "GRIID VIEW: Margin Value is not displayed as expected");
						}
					}
					
					//Product detail page
					infopr.searchProduct(pdtId);//search for product
					if(!infopr.PdtDetailMarginPrice(pdtId).isEmpty()&(i==0))
					{
						report.LogPASS(driver,childLogger, "PRODUCT DETAIL:Positive Margin Value-"+infopr.PdtDetailMarginPrice(pdtId)+" is displayed for the product-"+pdtId);
					}else if((infopr.PdtDetailMarginPrice(pdtId).isEmpty())&(i==1))
					{
						report.LogPASS(driver,childLogger, "PRODUCT VIEW:Negative Margin Value is not displayed for the product-"+pdtId);
					}
					else
					{
						report.LogFAIL(driver,childLogger, "PRODUCT VIEW: Margin Value is not displayed as expected");
					}
					
					//Order Closing Screen
					AddPdt2Basket(tcId, childLogger, setReport);
					GotoOrderClosingPage(tcId, childLogger, setReport);
					if(!checkout.totalSavings().isEmpty()&(i==0))
					{
						report.LogPASS(driver,childLogger, "CHECKOUT PAGE:Total savings-"+checkout.totalSavings()+" is displayed for ordering the product-"+pdtId);
					}else if((checkout.totalSavings().isEmpty())&(i==1))
					{
						report.LogPASS(driver,childLogger, "CHECKOUT PAGE:Total Savings Amount is not displayed");
					}
					else
					{
						report.LogFAIL(driver,childLogger, "CHECKOUT PAGE: Total Savings Amount is not displayed as expected");
					}logger.appendChild(childLogger);
				} catch (Exception e) {
					logger.appendChild(childLogger);
					report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
					e.printStackTrace();
					logger.appendChild(childLogger);
				}
		}		
	}

	//(@Author:Durga)To validate the Attribute filters before Login functionality
	public void ValidateAttributeFilters(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String pdtId = data.getTestData(tcId, "PdtID");		
		infopr.searchProduct(pdtId);		
		if(loginPage.checkAttributeFilter())
		{
			report.LogPASS(driver,logger, "Attribute Filter is available");
		}else
		{
			report.LogFAIL(driver,logger, "Attribute Filter is NOT available");
		}
	}	

	//(@Author:Durga)To validate the panel/pop up with Minimum Order value
	public void ValidatePanelMOV(String tcId, ExtentTest logger,ExtentReports setReport)
	{	
		String URL = data.getTestData(tcId, "URL");
		String[] DisablePanelStatus = {"ON","OFF"};		
		//add product to basket
		AddPdt2Basket(tcId, logger, setReport);
		for(int i=0;i<2;i++)		
		{
			ExtentTest childLogger = setReport.startTest("Validate Disable Panel in WebShop when it is turned "+DisablePanelStatus[i]+" in AM");;
			try {
				
				switch(DisablePanelStatus[i])
				{				
					case "ON":
					{						
						AMFunc.AMONDisablePanels(tcId, childLogger, setReport);//AM Setting
						//To go to Basket page	
						if(!checkout.gotoBasket(URL))
						{
							report.LogFATAL(driver,childLogger, "Page not navigated to Basket page");	
						}
							report.LogPASS(driver,childLogger, "Page navigated to Basket page successfully");
						//To go to next page
						checkout.gotoOrder04Page();
						//check for panel
						if(checkout.VerifyInContentMOV())
						{
							report.LogPASS(driver,childLogger,"Minimum Order value message is displayed in IN-CONTENT" );			
						}else{
							report.LogFAIL(driver,childLogger,"Minimum Order value message is NOT displayed in IN-CONTENT" );
						}
						break;						
					}
					case "OFF":
					{
						AMFunc.AMOFFDisablePanels(tcId, childLogger, setReport);//AM Setting
						//To go to Basket page	
						if(!checkout.gotoBasket(URL))
						{
							report.LogFATAL(driver,childLogger, "Page not navigated to Basket page");	
						}
							report.LogPASS(driver,childLogger, "Page navigated to Basket page successfully");
						//To go to next page
						checkout.gotoOrder04Page();
						//check for panel
						if(checkout.VerifyPopUpMOV())
						{
							report.LogPASS(driver,childLogger,"Minimum Order value message is displayed in Pop-Up" );			
						}else{
							report.LogFAIL(driver,childLogger,"Minimum Order value message is NOT displayed in Pop-Up" );
						}
						break;						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.appendChild(childLogger);
				amOrdDelTab.deleteMOV();
				report.LogFATAL(driver,childLogger, "WebElement/WebPage properties not found");
				
				}		
			logger.appendChild(childLogger);}
		
	}
	
	//(@Author:Durga)To validate the panel/pop up with Minimum Order value
	public void ValidatePanelStuurTeken(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String[] DisablePanelStatus = {"ON","OFF"};
		ExtentTest childLogger= null;
		//add product to basket
		AddPdt2Basket(tcId, logger, setReport);		
		try{
			for(int i=0;i<2;i++)		
			{
				childLogger = setReport.startTest("Validate Disable Panel in Order04 when it is turned "+DisablePanelStatus[i]+" in AM");
			
					//AM Setting
					if(DisablePanelStatus[i].contains("ON"))
					{
						AMFunc.AMONDisablePanels(tcId, childLogger, setReport);
					}else if(DisablePanelStatus[i].contains("OFF")) {
						AMFunc.AMOFFDisablePanels(tcId, childLogger, setReport);
					}
					
					//Check for select shipping address
					genfunc.refreshPage();checkout.gotoBasket(URL);checkout.gotoOrder04Page(); //To go to ORDER04 page
					if(checkout.clickSelectShippingAdd())
					{
						if((checkout.VerifyInContentSelectAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("ON")))
						{
							report.LogPASS(driver,childLogger,"Select shipping Address panel is displayed in IN-CONTENT" );			
						}else if((checkout.VerifyInContentSelectAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("OFF"))){
							report.LogPASS(driver,childLogger,"Select shipping Address panel is displayed in POP-UP" );
						}else{
							report.LogFAIL(driver,childLogger,"Select shipping Address panel is NOT displayed as expected" );
						}
						/*if(!checkout.closePanel())
						{
							report.LogFAIL(driver,childLogger,"Close button is not available in Select shipping Address panel" );
							genfunc.refreshPage();
						}*/
					}else
					{
						report.LogFAIL(driver,childLogger,"Select shipping Address button is not available" );	
					}	
					
					//Check for Edit Shipping address
					genfunc.refreshPage();checkout.gotoBasket(URL);checkout.gotoOrder04Page(); //To go to ORDER04 page	
					if(checkout.clickEditShippingAdd())
					{
						if((checkout.VerifyInContentEditAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("ON")))
						{
							report.LogPASS(driver,childLogger,"Edit shipping Address panel is displayed in IN-CONTENT" );			

						}else if((checkout.VerifyInContentEditAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("OFF"))){
							report.LogPASS(driver,childLogger,"Edit shipping Address panel is displayed in POP-UP" );
						}else{
							report.LogFAIL(driver,childLogger,"Edit shipping Address panel is NOT displayed as expected" );
						}
						/*if(!checkout.closePanel())
						{
							report.LogFAIL(driver,childLogger,"Close button is not available in Edit shipping Address panel" );
							genfunc.refreshPage();
						}*/
					}else
					{
						report.LogFAIL(driver,childLogger,"Edit shipping Address button is not available" );	
					}
					
					//Check for select Invoice address
					genfunc.refreshPage();checkout.gotoBasket(URL);checkout.gotoOrder04Page(); //To go to ORDER04 page	
					if(checkout.clickSelectInvoiceAdd())
					{	
						if((checkout.VerifyInContentSelectAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("ON")))
						{
							report.LogPASS(driver,childLogger,"Select Invoice Address panel is displayed in IN-CONTENT" );			
						}else if((checkout.VerifyInContentSelectAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("OFF"))){
							report.LogPASS(driver,childLogger,"Select Invoice Address panel is displayed in POP-UP" );
						}else{
							report.LogFAIL(driver,childLogger,"Select Invoice Address panel is NOT displayed as expected" );
						}
						/*if(!checkout.closePanel())
						{
							report.LogFAIL(driver,childLogger,"Close button is not available in Select Invoice Address panel" );
							genfunc.refreshPage();
						}*/
					}else
					{
						report.LogFAIL(driver,childLogger,"Select Invoice Address button is not available" );	
					}	
					
					//Check for Edit Invoice address					
					genfunc.refreshPage();checkout.gotoBasket(URL);checkout.gotoOrder04Page(); //To go to ORDER04 page	
					if(checkout.clickEditInvoiceAdd())
					{
						if((checkout.VerifyInContentEditAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("ON")))
						{
							report.LogPASS(driver,childLogger,"Edit Invoice Address panel is displayed in IN-CONTENT" );			
						}else if((checkout.VerifyInContentEditAdd(DisablePanelStatus[i]))&(DisablePanelStatus[i].contains("OFF"))){
							report.LogPASS(driver,childLogger,"Edit Invoice Address panel is displayed in POP-UP" );
						}else{
							report.LogFAIL(driver,childLogger,"Edit Invoice Address panel is NOT displayed as expected" );
						}
						/*if(!checkout.closePanel())
						{
							report.LogFAIL(driver,childLogger,"Close button is not available in Edit Invoice Address panel" );
							genfunc.refreshPage();
						}*/
					}else
					{
						report.LogFAIL(driver,childLogger,"Edit Invoice Address button is not available" );	
					}logger.appendChild(childLogger);		
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				report.LogFATAL(driver,childLogger, "WebElement/WebPage properties not found");	
				logger.appendChild(childLogger);
				
			}
		}
	
	
	//(@Author:Durga)To validate the panel/pop up with Minimum Order value
	public void ValidateSubstituteProducts(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String[] SubsPdtOpt = {"NO","Only on Header level","On Header and Line level","DB-Validation"};		
		
		//add product to basket
		int NoOfPdtsinBasket = AddPdt2Basket(tcId, logger, setReport);
	
		//To validate for three substitute options		
		for(int i=0;i<SubsPdtOpt.length;i++)		
		{
			ExtentTest childLogger = setReport.startTest("Validation of Substitute Products is Set -"+SubsPdtOpt[i]+" in AM");
			try {
				//To go to Basket page	
				if(!checkout.gotoBasket(URL))
				{
					report.LogFATAL(driver,childLogger, "Page not navigated to Basket page");	
					logger.appendChild(childLogger);
				}
				switch(SubsPdtOpt[i])
					{
					case "NO":
						AMFunc.AMSetNoSubstitutePdt(tcId, childLogger, setReport);
						genfunc.refreshPage();
						//OrderLines
						if(checkout.substituteOrderlines()>0)
						{
							report.LogFAIL(driver, childLogger, "Do not Substitute checkbox is available across orderlines in checkout page");
						}else{
							report.LogPASS(driver, childLogger, "Do not Substitute checkbox is not available across orderlines in checkout page");
						}
						//HeaderLevel
						GotoOrderClosingPage(tcId, childLogger, setReport);
						if(checkout.substituteOrderlines()>0)
						{
							report.LogFAIL(driver, childLogger, "I do not accept replacement products checkbox is availabl in order closing page");
						}else{
							report.LogPASS(driver, childLogger, "I do not accept replacement products checkbox is not available in order closing page");
						}
						break;
					case "Only on Header level"	:
						AMFunc.AMSetHeaderlevelSubstitutePdt(tcId, childLogger, setReport);
						genfunc.refreshPage();
						//OrderLines
						if(checkout.substituteOrderlines()>0)
						{
							report.LogFAIL(driver, childLogger, "Do not Substitute checkbox is available across orderlines in checkout page");
						}else{
							report.LogPASS(driver, childLogger, "Do not Substitute checkbox is not available across orderlines in checkout page");
						}
						//HeaderLevel
						GotoOrderClosingPage(tcId, childLogger, setReport);
						if(checkout.substituteOrderlines()>0)
						{
							report.LogPASS(driver, childLogger, "I do not accept replacement products checkbox is available in order closing page");
						}else{
							report.LogFAIL(driver, childLogger, "I do not accept replacement products checkbox is NOT available in order closing page");
						}
						break;
					case "On Header and Line level":
						AMFunc.AMSetHeaderLinelevelSubstitutePdt(tcId, childLogger, setReport);
						genfunc.refreshPage();
						//OrderLines
						if(checkout.substituteOrderlines()<0)
						{
							report.LogFAIL(driver, childLogger, "Do not Substitute checkbox is NOT available across orderlines in checkout page");
						}else if(checkout.substituteOrderlines()==NoOfPdtsinBasket){
							report.LogPASS(driver, childLogger, "Do not Substitute checkbox is available across all "+NoOfPdtsinBasket+" orderlines in checkout page");
						}	
						//HeaderLevel
						GotoOrderClosingPage(tcId, childLogger, setReport);
						if(checkout.substituteOrderlines()>0)
						{
							report.LogPASS(driver, childLogger, "I do not accept replacement products checkbox is available in order closing page");
						}else{
							report.LogFAIL(driver, childLogger, "I do not accept replacement products checkbox is NOT available in order closing page");
						}
						break;
					case "DB-Validation":						
						
						String OrderNo = checkout.currentOrderNo();
						//Unchecked Substitute pdts - DB value
						String uncheckedOrderLine = dbConnect.getDBData("select is2subst  from isor2pf where is2lin=100 and is2nbr="+OrderNo, childLogger);
						String uncheckedHeader = dbConnect.getDBData("select  is1subsa from isor1pf where is1nbr="+OrderNo, childLogger);						
						//To check substitute Pdt in orderline
						Boolean SPOL = checkout.checkSubstituteOrderline(1);					
						//Move to order closing and to check substitute Pdt in header
						checkout.gotoOrder04Page();
						checkout.gotoOrderClosingPage();
						Boolean SPHL = checkout.checkSubstituteOrderline(1);
						Boolean SaveOrder = checkout.ParkOrder();	
						//Checked Substitute pdts - DB value
						String checkedOrderLine = dbConnect.getDBData("select is2subst  from isor2pf where is2lin=100 and is2nbr="+OrderNo, childLogger);
						String checkedHeader = dbConnect.getDBData("select  is1subsa from isor1pf where is1nbr="+OrderNo, childLogger);
						
						//Orderlinelevel unchecked report
						if(uncheckedOrderLine.contains("1"))
						{
							report.LogFAILmsg(childLogger, "Do not Substitute orderline level is turned ON & is1subsa is set to-"+uncheckedOrderLine+" in DB");
						}else{
							report.LogPASSmsg(childLogger, "Do not Substitute orderline level is not turned ON & is1subsa is set to-0 in DB");
						}
						//HeaderLevel unchecked report
						if(uncheckedHeader.contains("1"))
						{
							report.LogFAILmsg(childLogger, "Do not Substitute header level is turned ON & is1subsa is set to-"+uncheckedHeader+" in DB");
						}else{
							report.LogPASSmsg(childLogger, "Do not Substitute header level is not turned ON & is1subsa is set to-"+uncheckedHeader+" in DB");
						}
						//To check if order is parked
						if(SPOL & SPHL & SaveOrder)
						{
							report.LogPASS(driver, childLogger, "Do not Substitute orderline level and Header level are turned ON & the order-"+OrderNo+" is parked successufully");
						}else
						{
							report.LogFATAL(driver, childLogger, "Do not Substitute orderline level and Header level are not turned ON & the order-"+OrderNo+" is not parked successufully");
						}
						
						//Orderlinelevel checked report
						if(checkedOrderLine.contains("1"))
						{
							report.LogPASSmsg(childLogger, "Do not Substitute orderline level is turned ON & is1subsa is set to-"+checkedOrderLine+" in DB");
						}else{
							report.LogFAILmsg(childLogger, "Do not Substitute orderline level is not turned ON & is1subsa is set to-"+checkedOrderLine+" in DB");
						}
						
						//HeaderLevel checked report
						if(checkedHeader.contains("1"))
						{
							report.LogPASSmsg(childLogger, "Do not Substitute header level is turned ON & is1subsa is set to-"+checkedHeader+" in DB");
						}else{
							report.LogFAILmsg(childLogger, "Do not Substitute header level is not turned ON & is1subsa is set to-"+checkedHeader+" in DB");
						}	
						break;	
					}logger.appendChild(childLogger);
			}catch (Exception e) {
			e.printStackTrace();
			report.LogFATAL(driver,childLogger, "WebElement/WebPage properties not found");
			logger.appendChild(childLogger);
			}
			
		}
	}
	
	//(@Author:Durga)To shared order template
	public void ValidateSharedOrderTemplate(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		String URL = data.getTestData(tcId, "URL");
		String userID = data.getTestData(tcId, "LoginID");
		String passWord = data.getTestData(tcId, "Password");
		String OrderTemplateGroupName = data.getTestData(tcId, "OTGroupName");
		String OrderTemplateName = null;		
		String CopiedOrderTemplateName = null;
		String EditedOrderTemplateName = null;
		String SelectedUser[] =data.getTestData(tcId, "User2").split("/"); 
		String User2ID = SelectedUser[0];
		String User2Password = SelectedUser[1];
		//create order template in shop
		ExtentTest childLogger1 = setReport.startTest("Creation of Shared Order Template");
		try {
			homePage.gotoOrderTemplatePage();
			homePage.clickNewOrderTemplate();		
			if(homePage.selectOrderTemplateGroup(OrderTemplateGroupName))
			{
				report.LogPASS(driver, childLogger1, "Order Template group - "+OrderTemplateGroupName+" is selected successfully");
			}
			OrderTemplateName = homePage.enterOrderTemplateName();		
			if((!OrderTemplateName.equals(null))&homePage.checkCreatedOrderTemplate(OrderTemplateName))
			{
				report.LogPASS(driver, childLogger1, "Order Template - "+OrderTemplateName+" is created successfully");
			}else
			{
				report.LogFATAL(driver, childLogger1, "Order Template has Not been created");
			}logger.appendChild(childLogger1);
		} catch (Exception e) {
			report.LogFATAL(driver, childLogger1, "Error in creation/availability of Order template of created OrderTemplate");
			logger.appendChild(childLogger1);
			e.printStackTrace();
		}
		

		//to validate copying
		ExtentTest childLogger2 = setReport.startTest("Copying of Shared Order Template");
		try {
			CopiedOrderTemplateName = homePage.CopyOrderTemplateName(OrderTemplateName);
			if(!CopiedOrderTemplateName.equals(null))
			{
				report.LogPASS(driver, childLogger2, "Order Template - "+OrderTemplateName+" has been copied to "+CopiedOrderTemplateName+" successfully");
			}logger.appendChild(childLogger2);	
		} catch (InterruptedException e) {
			report.LogFATAL(driver, childLogger2, "Error in Copying of Order template");
			logger.appendChild(childLogger2);	
			e.printStackTrace();
		}			
		
		//to validate sharing
		ExtentTest childLogger = setReport.startTest("Sharing of Shared Order Template");
		if(homePage.ValidateShareOrderTemplatePanel(OrderTemplateName))
		{
			report.LogPASS(driver, childLogger, "Shared Order Template Panel has 3 sharing options : YourselfOnly , Account , SelectedUsers ");
			String[] SharingType ={"YourselfOnly","Account","SelectedUsers"};
			homePage.logout();
			for(int i=0;i<3;i++)
			{
				loginPage.login(URL, userID, passWord);
				ExtentTest SOTLogger = setReport.startTest("Sharing Order Template with type - "+SharingType[i]);
				try
				{
					
					switch(SharingType[i])
					{
						case "YourselfOnly" :
							homePage.SelectShareOrderTemplateType(SharingType[i], OrderTemplateName);
							report.LogPASS(driver, SOTLogger, "Order Template has been shared with - "+SharingType[i] +" successfully");
							homePage.logout();
							
							loginPage.login(URL, User2ID, User2Password);
							if(!homePage.checkCreatedOrderTemplate(OrderTemplateName))
							{
								report.LogPASS(driver, SOTLogger, "Order Template has not been shared with - "+User2ID+" user");
							}else
							{
								report.LogFAIL(driver, SOTLogger, "Order Template has been shared with - "+User2ID+" user");
							}
							homePage.logout();
							break;
						case "Account":
							homePage.SelectShareOrderTemplateType(SharingType[i], OrderTemplateName);
							report.LogPASS(driver, SOTLogger, "Order Template has been shared with - "+SharingType[i] +" successfully");
							homePage.logout();
							
							loginPage.login(URL, User2ID, User2Password);
							if(homePage.checkCreatedOrderTemplate(OrderTemplateName))
							{
								report.LogPASS(driver, SOTLogger, "Order Template has been shared with - "+User2ID+" user");
							}else
							{
								report.LogFAIL(driver, SOTLogger, "Order Template has NOT been shared with - "+User2ID+" user");
							}
							homePage.logout();
							break;
						case "SelectedUsers":
							homePage.SelectShareOrderTemplateType(SharingType[i], OrderTemplateName);
							if(homePage.SelectSOTSelectedUser(User2ID))
							{
								report.LogPASS(driver, SOTLogger, User2ID+" user has been successfully added to under Selected Users");		
								homePage.logout();
								
								loginPage.login(URL, User2ID, User2Password);
								if(homePage.checkCreatedOrderTemplate(OrderTemplateName))
								{
									report.LogPASS(driver, SOTLogger, "Order Template has been shared with - "+User2ID+" user");
									//to check if user is not able to delete/edit/share the order template
									if(!homePage.checkSOTActionIcons(OrderTemplateName))
									{
										report.LogPASS(driver, childLogger, "Shared Order Template has no Share/Delete/Copy/Edit options for other user - "+User2ID);
									}else
									{
										report.LogFAIL(driver, childLogger, "Shared Order Template has Share/Delete/Copy/Edit options for other user - "+User2ID);
									}					
								}else
								{
									report.LogFAIL(driver, SOTLogger, "Order Template has NOT been shared with - "+User2ID+" user");
								}									
							}else
							{
								report.LogFATAL(driver, SOTLogger, User2ID+" user is not available under Selected Users, check if the users are having same Account");	
							}
							
							homePage.logout();																
							break;
					}
				}catch (Exception e) {
					report.LogFATAL(driver, childLogger, "WebElement not available/Error in Sharing of Order template");
					e.printStackTrace();
				}childLogger.appendChild(SOTLogger);
			}logger.appendChild(childLogger);							
			loginPage.login(URL, userID, passWord);
		}else
		{
			report.LogFATAL(driver, childLogger, "Shared order template option is not available for Order template-"+OrderTemplateName);
			logger.appendChild(childLogger);
		}
		
		
		//to validate editing
		ExtentTest childLogger3 = setReport.startTest("Editing of Shared Order Template");
		try {
			EditedOrderTemplateName = homePage.EditOrderTemplateName(OrderTemplateName);
			if(!EditedOrderTemplateName.equals(null))
			{
				report.LogPASS(driver, childLogger3, "Order Template - "+OrderTemplateName+" has been edited to "+EditedOrderTemplateName+" successfully");
				OrderTemplateName = EditedOrderTemplateName;
			}else
			{
				report.LogFATAL(driver, childLogger3, "Order Template - "+OrderTemplateName+" has not been edited as expected");
			}
			//to check for the shared user
			homePage.logout();			
			loginPage.login(URL, User2ID, User2Password);
			if(homePage.checkCreatedOrderTemplate(OrderTemplateName))
			{
				report.LogPASS(driver, childLogger3, "Edited Order Template name has been updated also for - "+User2ID+" shared user");
			}else
			{
				report.LogFAIL(driver, childLogger3, "Edited Order Template name has NOT been updated for - "+User2ID+" shared user");
			}
			homePage.logout();
			
		} catch (Exception e) {
			report.LogFATAL(driver, childLogger3, "Error in Editing of Order template");
			e.printStackTrace();
		}logger.appendChild(childLogger3);
		loginPage.login(URL, userID, passWord);
		
		//to validate Deleting
		ExtentTest childLogger4 = setReport.startTest("Deleting of Shared Order Template");
		try {
			if(homePage.DeleteOrderTemplateName(OrderTemplateName)&homePage.DeleteOrderTemplateName(CopiedOrderTemplateName))
			{
				report.LogPASS(driver, childLogger4, "Order Template - "+OrderTemplateName+" & "+CopiedOrderTemplateName+" has been deleted successfully");
			}else{
				report.LogFATAL(driver, childLogger4, "Error in Deleting of Order template");
			}
			//to check for the shared user
			homePage.logout();			
			loginPage.login(URL, User2ID, User2Password);
			if(!homePage.checkCreatedOrderTemplate(OrderTemplateName))
			{
				report.LogPASS(driver, childLogger4, "Order Template name has been deleted also for - "+User2ID+" shared user");
			}else
			{
				report.LogFAIL(driver, childLogger4, "Order Template name has NOT been deleted for - "+User2ID+" shared user");
			}
		} catch (Exception e) {
			report.LogFATAL(driver, childLogger4, "Error in Deleting of Order template");
			e.printStackTrace();
		}logger.appendChild(childLogger4);	
	}
	
	//(@Author:Durga)To validate the Sort by option 
	public void ValidateSortByOption(String tcId, ExtentTest logger,ExtentReports setReport)
	{
		try {
			String pdtId = data.getTestData(tcId, "PdtID");
			infopr.searchProduct(pdtId.substring(0, 3));//search for product
			if(infopr.CheckPriceAscSortByOption())
			{				
				report.LogPASS(driver, logger, "Sort by Price(0-9) dropdown option is available");
			}else
			{
				report.LogPASS(driver, logger, "Sort by Price(0-9) dropdown option is NOT available");
			}	
			
			if(infopr.CheckPriceDescSortByOption())
			{				
				report.LogPASS(driver, logger, "Sort by Price(9-0) dropdown option is available");
			}else
			{
				report.LogPASS(driver, logger, "Sort by Price(9-0) dropdown option is NOT available");
			}
		} catch (Exception e) {
			report.LogFATAL(driver,logger, "WebElement/WebPage properties not found");
			e.printStackTrace();
		}
	}
















}
	

	
	
	
	



