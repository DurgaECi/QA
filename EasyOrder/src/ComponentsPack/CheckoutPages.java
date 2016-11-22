package ComponentsPack;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import SupportLib.TestWait;

/**
 * @author - Durga Suresh
 * Description - Objects and methods in Checkout page of webshop
 * 
 */
public class CheckoutPages {
	
	WebDriver driver;	
	public TestWait wait = new  TestWait();
	public final String checkoutPageUrl = "/easyorder/shopbas01?order=submit&progr=SHOPBAS01";
	private GeneralFunctions genfunc;	
	public CheckoutPages(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.genfunc = PageFactory.initElements(driver, GeneralFunctions.class);
	}	
	
	
	//*********************************************************************
	@FindBy(css ="img[alt=\"Delete\"]") WebElement buttonDeleteBasket;
	@FindBy(css ="img[alt=\"Wissen\"]") WebElement buttonWissenBasket; 
	@FindBy(css ="a[title=\"Order wissen\"]") List<WebElement> availDelBasketBtn; 
	@FindBy(xpath = "//*[@id='orderLines']") WebElement textBasketPdt;
	@FindBy(xpath = "//*[@class='button_order next_button']") WebElement buttonOrder03Next;
	@FindBy(css = "a[id=\"step_button_nxt_shipinv\"]") WebElement buttonOrder04Next;
	@FindBy(css = "a[id=\"order_handling\"]") WebElement buttonPlaceOrder;
	@FindBy(xpath = "//input[@name='catSrchTrms']") WebElement editSearchBox;
	@FindBy(css = "body[id=\"ORDER04\"]") List<WebElement> pageOrder04;
	@FindBy(css = "body[id=\"ORDER03\"]") List<WebElement> pageOrder03; 
	@FindBy(css = "body[id=\"ORDCONF\"]") List<WebElement> pageOrderConfirmationPage1; 
	@FindBy(css = "body[id=\"IDEALSUCCESS\"]") List<WebElement> pageOrderConfirmationPage2; //Order success
	@FindBy(css = "body[id=\"IDEALFAILURE\"]") List<WebElement> pageOrderFailurePage;
	@FindBy(id = "payment_types") WebElement panelPaymentTypes;
	@FindBy(css ="input[value=\"ACC\"]") WebElement radioAccPaymtType;
	@FindBy(css ="input[value=\"NE1\"]") WebElement radioNE1PaymtType;
	@FindBy(css ="input[value=\"TNS\"]") WebElement radioTNSPaymtType;
	@FindBy(css ="input[value=\"PAP\"]") WebElement radioPAPPaymtType;	
	@FindBy(xpath ="//*[@id='payment_TNS']//*[@value=\"NEW\"]") WebElement radioTNSRegister;
	@FindBy(xpath ="//*[@id='payment_TNS']//*[@id='tokenECI']") List<WebElement> radioTNSToken;
	@FindBy(xpath ="//*[@id='payment_TNS']/div[@class='total_body']/fieldset/div") List<WebElement> listTNSoptions; 
	@FindBy(xpath ="//*[@id='cnt_intro']//*[@id='saveThePlanet']") List<WebElement> panelInContentMinOrderValue;
	@FindBy(xpath ="//*[@id='ORDER03']/*[@id='saveThePlanet']")  List<WebElement> panelPopUpMinOrderValue;
	@FindBy(id = "saveThePlanet")  List<WebElement> panelMinOrderValue;
	@FindBy(xpath = "//*[@id='saveThePlanet']//span[@class='button_end']") WebElement buttonContinueMOV;
	@FindBy(xpath = "//*[@class='total_item price_incex']//span[@class='amount']") WebElement textTotalPdtAmt;
	@FindBy(xpath = "//*[@id='items']//*[@class='order04-total-savings']/*[@class='amount']") List<WebElement> textTotalSavingAmt;
	@FindBy(css ="a[id=\"select_shipping\"]") WebElement buttonSelectShippingAdd;
	@FindBy(xpath="//*[@id='ship_block']//a[contains(@onclick,'editAddress')]") WebElement buttonEditShippingAdd;
	@FindBy(css ="a[id=\"select_invoice\"]") WebElement buttonSelectInvoiceAdd;
	@FindBy(xpath="//*[@id='invoice_block']//a[contains(@onclick,'editAddress')]") WebElement buttonEditInvoiceAdd;
	@FindBy(xpath ="//*[@id='cnt_core']//*[@id='address_dialog']") List<WebElement> panelInContentEditAdd;
	@FindBy(xpath ="//*[@id='ORDER04']/*[@id='address_dialog']")  List<WebElement> panelPopUpEditAdd;
	@FindBy(xpath ="//*[@id='cnt_core']//*[@id='select_dialog']") List<WebElement> panelInContentSelectAdd;
	@FindBy(xpath ="//*[@id='ORDER04']/*[@id='select_dialog']")  List<WebElement> panelPopUpSelectAdd;
	@FindBy(xpath ="//*[@id='select_dialog']//img[contains(@src,'close')]") List<WebElement> buttonClosePanel1;
	@FindBy(xpath ="//*[@id='address_dialog']//img[contains(@src,'close')]") List<WebElement> buttonClosePanel2;
	//@FindBy(xpath ="//span[text()='Sluiten']") WebElement buttonClosePanel1;
	@FindBy(xpath ="//input[@type='checkbox' and contains(@name,'substitution')]") List<WebElement> listSubstituteOrderLines;
	@FindBy(xpath = "//*[@class='clear']/strong[contains(text(),'Order')]") WebElement textOrderNumber;
	@FindBy(xpath ="//*[@id='cnt_tools']//img[contains(@src,'save')]") WebElement buttonParkOrder;      
	@FindBy(xpath ="//*[@class='EWD_Odrsave_context']") WebElement panelOrderSavedMsg;      
	//------------------------PaymentService---------------------------------------------------------
	@FindBy(id = "logo") WebElement imgNE1Logo;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlSinglePayment_ctlCreditCardPayment_txtCardnumber\"]") WebElement editCCNoNE1;
	@FindBy(css ="select[id=\"ContentPlaceHolder1_ctlSinglePayment_ctlCreditCardPayment_ddlExpMonth\"]") WebElement dropdownExpiryMmNE1;
	@FindBy(css ="select[id=\"ContentPlaceHolder1_ctlSinglePayment_ctlCreditCardPayment_ddlExpYear\"]") WebElement dropdownExpiryYyyyNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlSinglePayment_ctlCreditCardPayment_txtCVV\"]") WebElement editCVVNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_btnSubmit\"]") WebElement buttonSubmitNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlBillingInformation_txtFirstName\"]") WebElement editBillInfoFirstNameNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlBillingInformation_txtLastName\"]") WebElement editBillInfoLastNameNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlBillingInformation_txtAddress\"]") WebElement editBillInfoAddrNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlBillingInformation_txtCity\"]") WebElement editBillInfoCityNE1;
	@FindBy(css ="select[id=\"ContentPlaceHolder1_ctlBillingInformation_ddlState\"]") WebElement dropdownBillInfoStateNE1;
	@FindBy(css ="select[id=\"ContentPlaceHolder1_ctlBillingInformation_ddlCountry\"]") WebElement dropdownBillInfoCountryNE1;
	@FindBy(css ="input[id=\"ContentPlaceHolder1_ctlBillingInformation_txtZip\"]") WebElement editZipCodeNE1;
	
	@FindBy(xpath = ".//*[@id='cardNumber']") WebElement editCCNoTNS;
	@FindBy(css ="select[id=\"expiryMonth\"]") WebElement dropdownExpiryMmTNS;
	@FindBy(css ="select[id=\"expiryYear\"]") WebElement dropdownExpiryYyTNS;
	@FindBy(css ="input[id=\"csc\"]") WebElement editCVVTNS;
	@FindBy(css ="input[id=\"cardHolderName\"]") WebElement editNameTNS;
	@FindBy(css ="button[class=\"submitButton btn btn-success\"]") WebElement buttonPayNowTNS;
	
	@FindBy(xpath = "//*[@id='loginBox']/p/label[@for='login_email']") WebElement panelPAPLoginBox;
	@FindBy(css ="input[id=\"loadLogin\"]") WebElement linkMyAccPAP;
	@FindBy(css ="input[id=\"login_email\"]") WebElement editEmailIdPAP;
	@FindBy(css ="input[id=\"login_password\"]") WebElement editPasswordPAP;
	@FindBy(css ="input[id=\"submitLogin\"]") WebElement buttonLoginPAP;
	@FindBy(css ="input[id=\"continue_abovefold\"]") WebElement buttonPayNowPAP;
	//*********************************************************************

	//(@Author:Durga;@Param:Url)To navigate to checkout page
	public Boolean gotoBasket(String Url)
	{	
		Boolean flag = null;
		driver.get(Url+checkoutPageUrl);
		if (pageOrder03.size()>0)
		{
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:Url)To set delete basket button
	public void DeleteBasket(String Url)
	{	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(textBasketPdt));
				//presenceOfElementLocated(textBasketPdt.click();));
				//elementToBeClickable(By.id("orderLines")));
		if(!textBasketPdt.getText().equalsIgnoreCase("0"))
		{	WebElement DelBasketBtn;
			gotoBasket(Url);			
			if 	(availDelBasketBtn.size()>0)
			{
				DelBasketBtn = availDelBasketBtn.get(0);
				
			}else{
				DelBasketBtn = buttonDeleteBasket;
				
			}			
			
			DelBasketBtn.click();
			//to handle alert pop up
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}	
	}
	
	//(@Author:Durga;@Param:ProductCode)To goto Order04 page
	public Boolean gotoOrder04Page()
	{
		Boolean flag = false;
		wait.WaitforMinTime(driver, buttonOrder03Next);
		buttonOrder03Next.click();		
		if (pageOrder04.size()>0)
		{
			flag = true;
		}
		return flag;
	}

	//(@Author:Durga;@Param:ProductCode)To goto Order04 page
	public Boolean gotoOrderClosingPage()
	{
		Boolean flag = false;
		buttonOrder04Next.click();		
		if (buttonPlaceOrder.isDisplayed())
		{
			flag = true;
		}
		return flag;
	}
	
	//(@Author:Durga)To click Place order button
	public void clickPlaceOrder()
	{
		buttonPlaceOrder.click();		
	}

	//(@Author:Durga;@Param:paymentType)To goto Order04 page
	public void selectPaymentType(String paymentType)
	{
		switch(paymentType){
		case "ACC":
			radioAccPaymtType.click();
			break;
		case "NE1":
			radioNE1PaymtType.click();
			break;
		case "TNS":
			radioTNSPaymtType.click();
			break;
		case "PAP":	
			radioPAPPaymtType.click();
			break;
		}
	}
	
	//(@Author:Durga;@Param:ProductCode)To verify order confirmation page
	public Boolean verifyOrderConfirmtnPage()
	{
		Boolean flag = null;
		wait.WaitforMinTime(driver, editSearchBox);			
		if((pageOrderConfirmationPage1.size()>0)|(pageOrderConfirmationPage2.size()>0))
		{
			flag = true;
		}else if (pageOrderFailurePage.size()>0)
		{
			flag = false;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To Verify NE1 payment Page
	public Boolean verifyNE1Page()
	{
		Boolean flag = false;
		wait.WaitforMinTime(driver, imgNE1Logo);
		if(imgNE1Logo.isDisplayed())
		{
			flag = true;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To validate NE1 payment method
	public boolean validateNE1(String CCNo,String ExpMM,String ExpYYYY)
	{
		
		try {
			Boolean flag = verifyNE1Page();				
			genfunc.enterData(CCNo, editCCNoNE1);
			dropdownExpiryMmNE1.sendKeys(ExpMM);
			dropdownExpiryYyyyNE1.sendKeys(ExpYYYY);
			genfunc.enterData("100", editCVVNE1);
			dropdownBillInfoCountryNE1.sendKeys("Netherlands");
			genfunc.enterData("V O F", editBillInfoFirstNameNE1);
			genfunc.enterData("SPEELEILAND THOLENTOP TOYS", editBillInfoLastNameNE1);
			genfunc.enterData("KERKSTRAAT 5 7" , editBillInfoAddrNE1);
			genfunc.enterData("AMSTERDAM", editBillInfoCityNE1);
			genfunc.enterData("1101CN", editZipCodeNE1);
			Thread.sleep(200);
			genfunc.selectDropdownbyVisibleTxt("Noord-Holland", genfunc.dropdownlist(dropdownBillInfoStateNE1));
			buttonSubmitNE1.click();
			
			//to handle alert pop up
			genfunc.handleAlert();
			
			return flag;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	
	}
	
	//(@Author:Durga;@Param:paymentType)To select TNS options
	public Boolean selectTNSOptn(String option,String CCNo)
	{	
		Boolean flag = false;
		String tokenNo = CCNo.substring(CCNo.length()-4, CCNo.length());
		
		if (option.equalsIgnoreCase("REGISTER"))
		{
			radioTNSRegister.click();
			flag = true;
		}else if(option.equalsIgnoreCase("TOKEN"))
		{			
			for(int i=1;i<listTNSoptions.size()+1;i++)
			{	WebElement token = driver.findElement(By.xpath("//*[@id='payment_TNS']//fieldset/div["+i+"]"));
			
				if(token.getText().contains(tokenNo))					
				{
					driver.findElement(By.xpath("//*[@id='payment_TNS']//fieldset/div["+i+"]/input[@name='tokenECI']")).click();
					flag = true;
				}	
			}			
		}	
		return flag;
	}
	
	//(@Author:Durga;@Param:paymentType)To delete TNS token
	public Boolean deleteTNSToken(String CCNo)
	{	
		Boolean flag = false;
		String tokenNo = CCNo.substring(CCNo.length()-4, CCNo.length());
		
					
			for(int i=1;i<listTNSoptions.size()+1;i++)
			{	
				WebElement token = driver.findElement(By.xpath("//*[@id='payment_TNS']//fieldset/div["+i+"]"));
			
				if(token.getText().contains(tokenNo))					
				{
					WebElement deleteBtn = driver.findElement(By.xpath("//*[@id='payment_TNS']//fieldset/div["+i+"]/span/a[@class='script_link delete_button']"));
					deleteBtn.click();
					//to handle alert pop up
					Alert alert = driver.switchTo().alert();
					alert.accept();					
					flag = true;
					break;
				}	
			}			
			
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To Verify NE1 payment Page
	public Boolean verifyTNSPage()
	{
		Boolean flag = false;
		wait.WaitforMinTime(driver, editCCNoTNS);
		if(editCCNoTNS.isDisplayed())
		{
			flag = true;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To validate TNS payment method
	public boolean validateTNS(String CCNo,String ExpMM,String ExpYY)
	{	
		
		try {
			Boolean flag = verifyTNSPage();
			editCCNoTNS.sendKeys(CCNo);
			dropdownExpiryMmTNS.sendKeys(ExpMM);
			dropdownExpiryYyTNS.sendKeys(ExpYY);
			editCVVTNS.sendKeys("100");
			editNameTNS.sendKeys("TEST");
			buttonPayNowTNS.click();
			return flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
		
	//(@Author:Durga;@Param:ProductCode)To Verify NE1 payment Page
	public Boolean verifyPAPPage()
	{
		Boolean flag = false;
		if((driver.findElements(By.id("miniCartContent")).size()>0))
		{
			flag = true;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To validate NE1 payment method	
	public boolean validatePAP()
	{
		
		try {
			Boolean flag = verifyPAPPage();	
			linkMyAccPAP.click();
			wait.WaitforMinTime(driver, editEmailIdPAP);
			editEmailIdPAP.sendKeys("eci_buyer@ecisolutions.com");
			editPasswordPAP.sendKeys("2130Kast$");
			buttonLoginPAP.click();
			wait.WaitforMaxTime(driver, buttonPayNowPAP);
			buttonPayNowPAP.click();			
			return flag;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//(@Author:Durga;@Param:ProductCode)To verify if Minimum order value pop up is displayed
	public boolean verifyMinOrderValue()
	{
		Boolean flag = false;
		if (panelMinOrderValue.size()>0)
		{
			flag = true;
		}
		return flag;
	}

	//(@Author:Durga;@Param:ProductCode)To click on continue in the minimum order value pop up
	public void clickContinueMOV()
	{
		buttonContinueMOV.click();
	}
	
	//(@Author:Durga)To get the products total amount in the basket
	public String getTotalPdtAmt()
	{		
		return textTotalPdtAmt.getText().replace(",", ".");		
	}
	
	//(@Author:Durga;@Param:ProductPositionNo)To increase the quantity of a product in the basket
	public void addMorePdtQty(String pdtRowNo)
	{
		WebElement buttonAddQty = driver.findElement(By.xpath("//*[@id='product0000"+pdtRowNo+"00']//a[@class='quant_more']"));
		WebElement buttonCalculate = driver.findElement(By.xpath("//*[@id='product0000"+pdtRowNo+"00']//*[@id='recalc0000"+pdtRowNo+"00']"));		
		buttonAddQty.click();
		buttonCalculate.click();
	}
	
	//(@Author:Durga)To check for total savings in order closing page
	public String totalSavings()
	{	
		String savings="";		
		if(textTotalSavingAmt.size()>0)
				if(!textTotalSavingAmt.get(0).getText().isEmpty())
				{
					savings = textTotalSavingAmt.get(0).getText();
				}
		return (savings);
	}
	
	//(@Author:Durga;@Param:)To check Minimum Order Value panel with Disable Panel setting ON
	public boolean VerifyInContentMOV()
	{
		boolean flag = false;		
		if(panelInContentMinOrderValue.size()>0)
		{
			flag= true;
		}
		return flag;
	}

	//(@Author:Durga;@Param:)To check Minimum Order Value panel with Disable Panel setting ON
	public boolean VerifyPopUpMOV()
	{
		boolean flag = false;		
		if(panelPopUpMinOrderValue.size()>0)
		{
			flag= true;
		}
		return flag;
	}

	//(@Author:Durga;@Param:)To click Edit shipping address button
	public boolean clickEditShippingAdd()
	{
		boolean flag = false;		
		if(buttonEditShippingAdd.isDisplayed())
		{
			flag= true;
			buttonEditShippingAdd.click();
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:)To click Select shipping address button
	public boolean clickSelectShippingAdd()
	{
		boolean flag = false;		
		if(buttonSelectShippingAdd.isDisplayed())
		{
			flag= true;
			buttonSelectShippingAdd.click();
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:)To click Edit invoice address button
	public boolean clickEditInvoiceAdd()
	{
		boolean flag = false;		
		if(buttonEditInvoiceAdd.isDisplayed())
		{
			flag= true;
			buttonEditInvoiceAdd.click();
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:)To click Select invoice address button
	public boolean clickSelectInvoiceAdd()
	{
		boolean flag = false;		
		if(buttonSelectInvoiceAdd.isDisplayed())
		{
			flag= true;
			buttonSelectInvoiceAdd.click();
		}
		return flag;
	}


	//(@Author:Durga;@Param:)To check Edit Shipping/Invoice Address panel with Disable Panel setting ON
	public boolean VerifyInContentEditAdd(String DisablePanelStatus)
	{
		boolean flag = false;		
		if(panelInContentEditAdd.size()>0 & DisablePanelStatus.contains("ON"))
		{
			wait.WaitforMaxTime(driver, panelInContentEditAdd.get(0));	
			flag= true;
		}else if(panelPopUpEditAdd.size()>0 & DisablePanelStatus.contains("OFF")) 
		{
			wait.WaitforMaxTime(driver, panelPopUpEditAdd.get(0));	
			flag = true;
		}else
		{
			flag=false;
		}
		return flag;
	}	

	//(@Author:Durga;@Param:)To check Select Shipping/Invoice Address panel with Disable Panel setting ON
	public boolean VerifyInContentSelectAdd(String DisablePanelStatus)
	{
		boolean flag = false;		
		if(panelInContentSelectAdd.size()>0 & DisablePanelStatus.contains("ON"))
		{
			wait.WaitforMaxTime(driver, panelInContentSelectAdd.get(0));		
			flag= true;
		}else if(panelPopUpSelectAdd.size()>0 & DisablePanelStatus.contains("OFF")) 
		{
			wait.WaitforMaxTime(driver, panelPopUpSelectAdd.get(0));		
			flag = true;
		}else
		{
			flag=false;
		}
		return flag;
	}	
	
	//(@Author:Durga)To close a panel in web page
	public Boolean closePanel()
	{
		Boolean flag =false;
		if(buttonClosePanel1.size()>0)
		{
		buttonClosePanel1.get(0).click();		
		flag = true;
		}else if(buttonClosePanel2.size()>0)
		{
		buttonClosePanel2.get(0).click();		
		flag = true;
		}
		return flag;
	}

	//(@Author:Durga)to return the number of substitute orderlines in checkout page
	public int substituteOrderlines()
	{
		return listSubstituteOrderLines.size();		
		
	}
	
	//(@Author:Durga;@Param:OrderLineNo)to check substitute orderlines in checkout page
	public boolean checkSubstituteOrderline(int OrderLineNo)
	{
		Boolean flag = false;
		if(!listSubstituteOrderLines.get(OrderLineNo-1).isSelected())
		{
			listSubstituteOrderLines.get(OrderLineNo-1).click();
			flag = true;
		}
		else if (listSubstituteOrderLines.get(OrderLineNo-1).isSelected())
		{
			flag = true;
		}		
		return flag;
	}
	
	//(@Author:Durga;@Param:OrderLineNo)to return order number of current open order
	public String currentOrderNo()
	{
		int sepeartorIndex = 0;		
		
			if(textOrderNumber.getText().contains(":"))
			{
				sepeartorIndex = textOrderNumber.getText().indexOf(":");
			}
		
		String OrderNo = (textOrderNumber.getText().substring(sepeartorIndex+1)).trim();
		return OrderNo;
		
	}
	
	//(@Author:Durga)to save an open order in basket
	public boolean ParkOrder()
	{
		boolean flag = false;
		buttonParkOrder.click();
		if(panelOrderSavedMsg.isDisplayed())
		{
			flag = true;
		}
		
		return flag;
	}


}


