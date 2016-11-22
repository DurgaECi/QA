package ComponentsPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to Design tab in Application Manager
 *
 */
public class AM_PricingTab {
	
	WebDriver driver;
	public AM_General amGenfunc;
	
	public String AMPriceTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0106";
	public String AMGrossPrice = "/easyorder/WPRIM1";
		
	///easyorder/wprim1?id=0161001&id3=99&id4=1,00 &action=edit
	public AM_PricingTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.amGenfunc = PageFactory.initElements(driver, AM_General.class);
	}
	
	
	//*********************************************************************
	@FindBy(css ="input[name=\"pricePerUnit\"]") WebElement EditPricePerUnit;

	
	
	//*********************************************************************
	
	
	//(@Author:Durga)To navigate to Pricing Tab in AM
	public void gotoAMDesignTab(String webShopUrl)
	{
		String aMPriceTab = webShopUrl+AMPriceTab;		
		driver.get(aMPriceTab);		
	}
	
	//(@Author:Durga)To navigate to Gross Price page in AM
	public void gotoGrossPricePage(String webShopUrl)
	{
		String aMPriceTab = webShopUrl+AMGrossPrice;		
		driver.get(aMPriceTab);		
	}

	//(@Author:Durga)To set gross price
	public void toSetGrossConsumerPrice(String webShopUrl,String pdtCode,int priceSheetNo,float priceAmount)
	{
		String pdtGrossPrice = webShopUrl+"/easyorder/wprim1?id="+pdtCode+"&id3="+priceSheetNo+"&id4=1,00 &action=edit";	
		driver.get(pdtGrossPrice);
		EditPricePerUnit.clear();
		String PPU = (Float.toString(priceAmount)).replace(".", ",");
		EditPricePerUnit.sendKeys(PPU);
	}
}