package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import driverfactory.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
	public WebDriver driver;
	public WebDriverWait wait;

	public AbstractPage() {
		driver = DriverFactory.initDriver("chrome");
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	public void click(WebElement element) {
		waitForElementToBeClickable(element);
		element.click();
	}

	public void writeText(WebElement element, String text) {
		waitForVisibility(element);
		element.sendKeys(text);
	}

	public String readText(WebElement element) {
		waitForVisibility(element);
		return element.getText();
	}

	public void containText(WebElement element, String expectedText) {
		Assert.assertEquals(readText(element), expectedText);
	}
	
	public void waitForVisibility(WebElement element) {
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
}
