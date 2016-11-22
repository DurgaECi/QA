/**
 * 
 */
package ComponentsPack;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import SupportLib.TestWait;

/**
 * @author - Durga Suresh
 * Description - Objects and methods in INFOPR3 page of webshop
 * 
 */
public class INFOPR3_4Page {
	
	WebDriver driver;	
	public TestWait wait = new  TestWait();
	public INFOPR3_4Page(WebDriver Idriver)
	{
	this.driver = Idriver;		
	}
	
	//*********************************************************************
	@FindBy(xpath = "//input[@name='catSrchTrms']") WebElement editSearch;
	@FindBy(xpath = "//*[@id='button_search']/span/span") WebElement buttonSearch;
	@FindBy(css ="img[title=\"in assortiment\"]") List <WebElement> imgInAssortmentIcon;
	@FindBy(id = "quant_more") WebElement buttonAddQty;
	@FindBy(css ="span[class=\"prod_ordered\"]") List <WebElement> textPdtOrderedInfo;
	@FindBy(css ="tr[id=\"prod_margin\"]") List<WebElement> textPdtDetPriceMarginValue;	
	@FindBy(xpath = "//*[@id='cnt_core']/form/table//span[@class='list_code']") List <WebElement> listProducts;	
	@FindBy(xpath = "//*[@id='cnt_core']/form/div[@class='list_grid total_third']//div[@class='list_code']") List <WebElement> gridProducts;	
	@FindBy(xpath ="//*[@id='cnt_tools']/a[2]//span") WebElement linkListorGridView;	
	@FindBy(xpath ="//select[@class='sort']/option") List<WebElement> listSortBy;
	@FindBy(xpath ="//select[@class='sort']") WebElement dropdownSortBy;
	
	//*********************************************************************
	
	//(@Author:Durga;@Param:ProductCode)To get order button of a product
	public List<WebElement> getOrderButton(String ProductCode)		{	
		
		List <WebElement> OrderButton = driver.findElements(By.xpath("//*[@id='product_ctrls']/form/*[@id = 'prod_order']/*[@id='order_"+ProductCode+"']"));	
		return OrderButton;		
	}
	//*********************************************************************

	
	
	//(@Author:Durga;@Param:ProductCode)To check if order button of a product is available
	public Boolean checkOrderButton(String ProductCode)	
	{	
		Boolean buttonAvailability;
		
		if(getOrderButton(ProductCode).size()<1)
	    {
			buttonAvailability = false;     
	    }
	    else 
	    {	    	
	        buttonAvailability = true; 
	    }
		return buttonAvailability;		
	}

	//(@Author:Durga;@Param:ProductCode)To Search Product in webshop
	public void searchProduct(String ProductCode)
	{	
		wait.WaitforMinTime(driver, editSearch);
		//Search for product
		editSearch.clear();
		editSearch.sendKeys(ProductCode);
		buttonSearch.click();				
	}
	

	//(@Author:Durga;@Param:ProductCode)To Search and verify if Product is in webshop with order button
	public Boolean searchVerifyProductOrderBtn(String ProductCode)
	{	
		//Search for product
		searchProduct(ProductCode);
		
		//validate the search result
		Boolean orderButton = checkOrderButton(ProductCode);
		return orderButton;		
	}
	
	
	//(@Author:Durga;@Param:ProductCode)To check if In Assortment Icon is displayed for the product
	public Boolean getInAssortmentIcon(String ProductCode)	
	{	
		Boolean iconAvailability;
		if(imgInAssortmentIcon.size()<1)
	    {
			iconAvailability = false;     
	    }
	    else 
	    {	    	
	    	iconAvailability = true;
	    }
		return iconAvailability;		
	}
	
	//(@Author:Durga;@Param:ProductCode)To Search and verify if Product is in webshop with InAssortment Icon
	public Boolean searchVerifyProductInAssIcon(String ProductCode)
	{	
		//Search for product
		searchProduct(ProductCode);
		
		//validate the search result
		Boolean InAssortmentIcon = getInAssortmentIcon(ProductCode);
		return InAssortmentIcon;		
	}
	
	//(@Author:Durga;@Param:ProductCode)To click Order button
	public Boolean clickOrderBtn(String ProductCode) throws InterruptedException
	{	
		Boolean orderBtnTxt = true;			
		getOrderButton(ProductCode).get(0).click();			
		if(textPdtOrderedInfo.size()<0)
		{
			orderBtnTxt = false;
		}
		
		return orderBtnTxt;
	}
	
	//(@Author:Durga)To click increase arrow button for quantity
	public void addPdtQty()
	{
		buttonAddQty.click();		
	}
	
	//(@Author:Durga;@Param:ProductCode)To Search and add product to basket
	public Boolean searchAddPdttoBasket(String ProductCode) throws InterruptedException
	{
		Boolean flag = false;
		//Search for product'
		if(searchVerifyProductOrderBtn(ProductCode))
		{
			//Add Product Qty
			addPdtQty();
			
			//Add to basket
			flag = clickOrderBtn(ProductCode);
		}		
		return flag;
	}
	
	
	//(@Author:Durga;@Param:ProductCode)To check for products Margin price in list view
	public String ListViewMarginPrice(String ProductCode)
	{	
		String Margin="";			
		for(int i=0;i<listProducts.size();i++)
		{		
			if(listProducts.get(i).getText().contains(ProductCode))
			{
				List<WebElement> ProductSection = driver.findElements(By.xpath("//*[@id='cnt_core']/form//table[@class='list_item item_bottom']["+(i+1)+"]//tr[@class='prod_margper']"));			
				if(!(ProductSection.size()==0))
				{
				if(ProductSection.get(0).getText().contains("%"))
				{
					Margin = ProductSection.get(0).getText();
				}
				}break;
			}		
		}
		return Margin;
	}
	//(@Author:Durga;@Param:ProductCode)To switch to List view in INFOPR3
	public boolean switchToListView()
	{
		Boolean flag=false;
		 
		if(linkListorGridView.getText().contains("Lijst")|linkListorGridView.getText().contains("List"))
		{
			linkListorGridView.click();
			flag=true;
		}else
		{
			flag = true;
		}
		return flag;
	}
	
	//(@Author:Durga;@Param:ProductCode)To switch to grid view in INFOPR3
	public boolean switchToGridView()
	{
		Boolean flag=false;
		if(!(linkListorGridView.getText().contains("Lijst")|linkListorGridView.getText().contains("Lijst")))
		{
			linkListorGridView.click();
			flag=true;
		}else
		{
			flag = true;
		}
		return flag;
	}
		

	//(@Author:Durga;@Param:ProductCode)To check for products Margin price in grid view
	public String GridViewMarginPrice(String ProductCode)
	{	
		String Margin="";		
		for(int i=0;i<gridProducts.size();i++)
		{		
			if(gridProducts.get(i).getText().contains(ProductCode))
			{
				List<WebElement> ProductSection = driver.findElements(By.xpath("//*[@id='cnt_core']/form/div[@class='list_grid total_third']["+(i+1)+"]//tr[@class='prod_margper']"));
				if(!(ProductSection.size()==0))
				{
				if(ProductSection.get(0).getText().contains("%"))
				{
					Margin = ProductSection.get(0).getText();
				}
				}
			}		
		}
		return (Margin);
	}
	
	//(@Author:Durga;@Param:ProductCode)To check for products Margin price in Product detail page
	public String PdtDetailMarginPrice(String ProductCode)
	{	
		String Margin="";
		if(textPdtDetPriceMarginValue.size()>0)
		{
				if(textPdtDetPriceMarginValue.get(0).getText().contains("%"))
				{
					Margin = textPdtDetPriceMarginValue.get(0).getText();
				}
		}
		return (Margin);
	}


	
	//(@Author:Durga;@Param:ProductCode)To check for Price Ascending Sort By option
	public boolean CheckPriceAscSortByOption()
	{	boolean flag = false;	
		for(int i=0;i<listSortBy.size();i++)
		{		
			if(listSortBy.get(i).getAttribute("value").contains("priceAsc"))
			{	
				listSortBy.get(i).click();
				flag = true;
				break;
			}		
		}
		return flag;
	}
	

	//(@Author:Durga;@Param:ProductCode)To check for Price Ascending Sort By option
	public boolean CheckPriceDescSortByOption()
	{	boolean flag = false;	
		for(int i=0;i<listSortBy.size();i++)
		{		
			if(listSortBy.get(i).getAttribute("value").contains("priceDesc"))
			{	
				listSortBy.get(i).click();
				flag = true;
				break;
			}		
		}
		return flag;
	}
	
	
}
