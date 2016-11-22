package ComponentsPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to Merchandise tab in Application Manager
 *
 */
public class AM_MerchandiseTab {
	
	WebDriver driver;
	public AM_General amGenfunc;
	
	public String AMMerchandiseTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0104";
	public String AMOrderTemplate = "/easyorder/WSHGM1";
		
	public AM_MerchandiseTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	this.amGenfunc = PageFactory.initElements(driver, AM_General.class);
	}
	
	
	//*********************************************************************
	

	
	
	//*********************************************************************
	
	
	//(@Author:Durga;@Param:webShopUrl)To navigate to Merchandise Tab in AM
	public void gotoAMMerchandiseTab(String webShopUrl)
	{
		String aMMerchandiseTab = webShopUrl+AMMerchandiseTab;		
		driver.get(aMMerchandiseTab);		
	}
	
	//(@Author:Durga;@Param:webShopUrl)To navigate to order template page in AM
	public void gotoOrderTemplatePage(String webShopUrl)
	{
		String OrderTemplateLink = webShopUrl+AMOrderTemplate;		
		driver.get(OrderTemplateLink);		
	}

	//(@Author:Durga;@Param:OrderTemplateType,OrderTemplateName)To navigate to relate page of an Account	
	public boolean checkShareOrderTemplateIcon(String webShopUrl,String OrderTemplateType,String OrderTemplateName)
	{
		boolean flag = false;
		gotoOrderTemplatePage(webShopUrl);
		amGenfunc.searchAndRelate(OrderTemplateType);
		if(amGenfunc.searchAndCheckShare(OrderTemplateName))
		{
			flag = true;
		}
		return flag;
	}
}