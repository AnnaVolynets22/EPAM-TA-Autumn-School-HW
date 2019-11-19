package driverfactory;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.Constants;

import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverFactory {

	private static WebDriver driver = null;

	private DriverFactory() {
	}

	public static WebDriver initDriver(String type) {
		if (driver == null) {
			if (type.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(Constants.IMP_WAIT_TIMEOUT, TimeUnit.SECONDS);
			}
			/*if (type.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.firefox.driver", "C:\\firefoxdriver.exe");
				driver = new FirefoxDriver();
			}*/
		}

		return driver;
	}
    
	public static WebDriver getDriver() 
	{
		return driver;
	}

}