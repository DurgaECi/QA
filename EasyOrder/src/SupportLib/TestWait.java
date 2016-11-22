package SupportLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverPack.TestConfig;

public class TestWait {
	
	public WebDriverWait wait;
	private TestConfig value = new TestConfig();
	

	public void WaitforMinTime(WebDriver driver,WebElement object)
	{
	int minTimeMs = value.waitMin;
	wait = new WebDriverWait(driver, minTimeMs);
	wait.until(ExpectedConditions.elementToBeClickable(object));
	}
	
	public void WaitforMaxTime(WebDriver driver,WebElement object)
	{
	int maxTimeMs = value.waitMax;
	wait = new WebDriverWait(driver, maxTimeMs);
	wait.until(ExpectedConditions.elementToBeClickable(object));
	}

}
