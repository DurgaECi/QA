/**
 * 
 */
package ComponentsPack;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author - Durga Suresh
 * Description - Objects and methods related to User & Accounts Tab
 *
 */
public class AM_OrdersAndDeliveryTab {

	WebDriver driver;
	public String AMOrdersAndDeliveryTab = "/easyorder/applMenu?mode=cnt&menu=EOB2B&uni=0107";
	public String MinimumOrderValuePage = "/easyorder/WMOVM1";
	
	
	
	
	public AM_OrdersAndDeliveryTab(WebDriver Idriver)
	{
	this.driver = Idriver;	
	}
	
	//*********************************************************************
		@FindBy(xpath ="//*[@id='quickLinks']//img") WebElement buttonAddMOV;
		@FindBy(xpath ="//*[@id='display']//input[@value='custPriceGrp' and @type='radio']") WebElement radioMovAPG;
		@FindBy(xpath ="//*[@id='display']//input[@value='custGroup' and @type='radio']") WebElement radioMovAG;
		@FindBy(xpath ="//*[@id='display']//input[@value='accountAlfa' and @type='radio']") WebElement radioMovAcc;
		@FindBy(xpath ="//*[@id='display']//input[@value='userCode' and @type='radio']") WebElement radioMovUsr;
		@FindBy(xpath ="//*[@id='display']//input[@value='allUsers' and @type='radio']") WebElement radioMovAll;
		@FindBy(xpath ="//*[@id='display']//input[@type= 'text' and @name='minOrdValue']") WebElement editMovAmt;
		@FindBy(xpath ="//input[@name='custPriceGrp' and @type='text']") WebElement editMovAPG;
		@FindBy(xpath ="//input[@name='custGroup' and @type='text']") WebElement editMovAG;
		@FindBy(xpath ="//input[@name='accountAlfa' and @type='text']") WebElement editMovAcc;
		@FindBy(xpath ="//input[@name='userCode' and @type='text']") WebElement editMovUsr;
		@FindBy(xpath ="//*[@id='list']/table/tbody//td[1]") List<WebElement> tableMovRec;
			
		
	//*********************************************************************
		
	//(@Author:Durga;@Param:URL)To navigate to orders and delivery Tab in AM
	public void gotoAMOrdersAndDeliveryTab(String webShopUrl)
	{
		String aMOrdersAndDeliveryTab = webShopUrl+AMOrdersAndDeliveryTab;		
		driver.get(aMOrdersAndDeliveryTab);		
	}
	
	//(@Author:Durga;@Param:URL)To navigate to Minimum order value price group page
	public void gotoMinimumOrderValuePage(String webShopUrl)
	{
		String movPage = webShopUrl+MinimumOrderValuePage;		
		driver.get(movPage);
	}
	
	//(@Author:Durga;@Param:URL)To click on add new MOV link
	public void addNewMOV()
	{
		buttonAddMOV.click();
	}
	
	//(@Author:Durga;@Param:URL)To enter APG value to related to field
	public void selectEnterAPGMOV(String APG)
	{
		radioMovAPG.click();
		editMovAPG.clear();
		editMovAPG.sendKeys(APG);
	}
	
	//(@Author:Durga;@Param:URL)To enter AG value to related to field
	public void selectEnterAGMOV(String AG)
	{
		radioMovAG.click();
		editMovAG.clear();
		editMovAG.sendKeys(AG);
	}
	
	//(@Author:Durga;@Param:URL)To enter Acc value to related to field
	public void selectEnterAccMOV(String Acc)
	{
		radioMovAcc.click();
		editMovAcc.clear();
		editMovAcc.sendKeys(Acc);
	}
	
	//(@Author:Durga;@Param:URL)To enter User value to related to field
	public void selectEnterUsrMOV(String Usr)
	{
		radioMovUsr.click();
		editMovUsr.clear();
		editMovUsr.sendKeys(Usr);
	}
	
	//(@Author:Durga;@Param:URL)To select ALL users radio button in related to field in MOV
	public void selectAllUsersMOV()
	{
		radioMovAll.click();
	}
	
	//(@Author:Durga;@Param:MOV Amount)To enter MOV amount in minimum order value page
	public void enterMOVAmt(String movAmt)
	{
		editMovAmt.clear();
		editMovAmt.sendKeys(movAmt);		
	}
	
	//(@Author:Durga)To get the delete button of a MOV record
	public List<WebElement> buttonDeleteMOV(int i)
	{		
		List<WebElement> buttonDeleteMOV = driver.findElements(By.xpath("//*[@id='list']/table/tbody/tr["+i+"]//img[@title='wissen']"));
		return buttonDeleteMOV;
	}
	
	
	
	//(@Author:Durga)To delete the MOV record
	public int deleteMOV()
	{
		
		int i=2;
		int tableSize = tableMovRec.size();
		int noOfRecDel = 0;
		for(;i<=tableMovRec.size();)
		{
			if(buttonDeleteMOV(i).size()>0)
			{
			buttonDeleteMOV(i).get(0).click();
			//to handle alert pop up
			Alert alert = driver.switchTo().alert();
			alert.accept();
			noOfRecDel = tableSize-1;
			
			}else if (buttonDeleteMOV(i).size()==0)
			{
				break;
			}
			
		}
		
		return noOfRecDel;
		
	}
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
