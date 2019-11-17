package tests;
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import driverfactory.DriverFactory;
 
public class BaseTest {
 
    @BeforeSuite
    public void setup () {
        
    }
 
    @AfterSuite
    public void quit () {
        DriverFactory.getDriver().quit();
    }
}