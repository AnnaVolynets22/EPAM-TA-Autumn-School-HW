package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import driverfactory.DriverFactory;
import utility.Constants;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
	public WebDriverWait wait;

	public AbstractPage() {
		DriverFactory.initDriver("chrome");
		PageFactory.initElements(DriverFactory.getDriver(), this);
		wait = new WebDriverWait(DriverFactory.getDriver(), Constants.EXP_WAIT_TIMEOUT);
	}

	public void click(WebElement element) {
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
	
	public void staleHandle(WebElement element) {
		
		for(int i=0;i<5;i++) {
			try {
				click(element);
				break;
			} catch (StaleElementReferenceException e) {
				
				System.out.println("Trying to recover from stale reference exeption");
			}
		}
	}
	
}
