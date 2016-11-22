package ComponentsPack;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

/**
 * @author - Durga Suresh
 * Description - general Objects and methods for Application Manager
 *
 */
public class AM_General {
	

	WebDriver driver;
	public final String AMUrl = "/easyorder/applMenu?mode=frs&menu=EOB2B";	
	
	public AM_General(WebDriver Idriver)
	{
	this.driver = Idriver;	
	}
	
	
	//*********************************************************************
	@FindBy(className = "save")List <WebElement> buttonSave;	
	@FindBy(className = "save_record")WebElement buttonSaveRecord;
	@FindBy(className = "left2") WebElement editSearchBox; ////*[@id="search"]/input
	@FindBy(xpath ="//*[@id='list']//tr[2]//img[contains(@src,'edit')]") WebElement buttonEdit;
	@FindBy(xpath ="//*[@id='list']//tr[2]//img[contains(@src,'link')]") WebElement buttonRelate;
	@FindBy(xpath ="//*[@id='list']//tr[2]//img[contains(@src,'relate')]") List<WebElement> buttonShare;
	@FindBy(xpath ="//*[@id='list']/table/tbody/tr[2]/td[2]/a") WebElement linkSelectItem;
	@FindBy(className = "back") WebElement buttonBACK;
	
	
	//*********************************************************************
	
	//(@Author:Durga)To Launch Application Manager
	public void launchAM(String webShopUrl)
	{
		String aMUrl = webShopUrl+AMUrl;
		driver.get(aMUrl);		
	}
	
	//(@Author:Durga)To Click on Submit or Save button in AM programs
	public void save()
	{	
		if (buttonSave.size()>0)
		{
			buttonSave.get(0).click();
		}
		else
		{
			buttonSaveRecord.click();
		}
		 		
	}
	
	//(@Author:Durga)To Click BACK in AM programs
	public void back()
	{	
		buttonBACK.click(); 		
	}
	

	//(@Author:Durga)To search in AM
	public void searchItem(String itemName)
	{
		//editSearchBox.clear();
		editSearchBox.sendKeys(itemName);
		editSearchBox.sendKeys(Keys.ENTER);
		
	}
	
	//(@Author:Durga)To search and navigate to edit page of the item in AM
	public void searchAndEdit(String itemName) 
	{
		try {
			searchItem(itemName);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
			buttonEdit.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	//(@Author:Durga)To search and navigate to relate page of the item in AM
	public void searchAndRelate(String itemName) 
	{
		searchItem(itemName);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		buttonRelate.click();					
	}
	
	//(@Author:Durga)To search and select item
	public void searchAndSelect(String itemName)
	{
		searchItem(itemName);
		linkSelectItem.click(); 
	}
	
	//(@Author:Durga)to get sub frames
	public void getsubFrame()
	{
		driver.switchTo().frame("sourceHolder");				
	}
	
	//(@Author:Durga)To search and check for share Icon
	public boolean searchAndCheckShare(String itemName)
	{
		boolean flag = false;
		searchItem(itemName);
		int i = buttonShare.size();
		if(i>0)
		{
			flag = true;
		}
		return flag;
	}
	
	
	
	

}
