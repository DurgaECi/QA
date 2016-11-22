/**
 * 
 */
package ComponentsPack;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *@author - Durga S
 * Description - Objects and methods related to catalog Tab
 * 
 */
public class AM_CatalogTab {
	
	WebDriver driver;
	public String AMCatalogTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0102";
	
	public AM_CatalogTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	}
	

	//*********************************************************************
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr/th") WebElement labelPdtGrpDisplaySplit;
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr/td/select") WebElement dropdownPdtGrpDisplaySplit;		
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[26]/th") WebElement labelPdtDisplaySplit1;
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[26]/td/select")  List <WebElement> dropdownPdtDisplaySplit;		
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[26]/td/select") WebElement dropdownPdtDisplaySplit1;	
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[25]/th")  WebElement labelPdtDisplaySplit2;
		@FindBy(xpath ="//*[@id='display']/table[3]/tbody/tr[25]/td/select") WebElement dropdownPdtDisplaySplit2;	
		@FindBy(xpath ="//*[@id='display']//input[@name='prdSplPack']") WebElement checkboxPdtSplitPack;
		
	//*********************************************************************
	
	//(@Author:Durga)To navigate to Edit page of a Product group 
	public void gotoEditPdtGrp(String webShopUrl,String pdtGrpCode)
	{
		String pdtGrpEdit =webShopUrl+"/easyorder/wgrpm1?id="+pdtGrpCode+"&action=edit";		
		driver.get(pdtGrpEdit);		
	}
	
	//(@Author:Durga)To navigate to Edit page of a Product  
	public void gotoEditPdt(String webShopUrl,String pdtId)
	{
		String pdtEdit =webShopUrl+"/easyorder/wprdm1?id="+pdtId+"&action=edit";		
		driver.get(pdtEdit);		
	}

	//(@Author:Durga)To get the label of Display split WebElement in Product page
	public WebElement labelPdtDisplaySplit()
	{
		WebElement labelPdtDisplaySplit;		
		if (dropdownPdtDisplaySplit.size()>0)
		{
			labelPdtDisplaySplit = labelPdtDisplaySplit1;
		}else{
			labelPdtDisplaySplit = labelPdtDisplaySplit2;
		}
		
		return labelPdtDisplaySplit;
	}
	
	//(@Author:Durga)To get the label of Display split WebElement in Product page
	public WebElement dropdownPdtDisplaySplit()
	{
		WebElement CorrectdropdownPdtDisplaySplit;
		if (dropdownPdtDisplaySplit.size()>0)
		{
			CorrectdropdownPdtDisplaySplit = dropdownPdtDisplaySplit1;
		}else{
			CorrectdropdownPdtDisplaySplit = dropdownPdtDisplaySplit2;
		}
		
		return (WebElement) CorrectdropdownPdtDisplaySplit;
	}
	
	//(@Author:Durga)To turn ON Restricted Assortment
	public Boolean setProductSplitPack(String status)
	{	
		Boolean flag = false;
		switch(status)
		{
			case "ON":
				if(!checkboxPdtSplitPack.isSelected())
				{
					checkboxPdtSplitPack.click();
					flag = true;
				}
				else if (checkboxPdtSplitPack.isSelected())
				{
					flag = true;
				}				
				break;
			
			case "OFF":
				if(checkboxPdtSplitPack.isSelected())
				{
					checkboxPdtSplitPack.click();
					flag = true;
				}
				else if (!checkboxPdtSplitPack.isSelected())
				{
					flag = true;
				}				
				break;
		}
		return flag;	
	}
	

}
