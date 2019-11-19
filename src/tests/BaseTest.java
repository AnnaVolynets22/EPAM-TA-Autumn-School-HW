package tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;
import pages.HomePage;

public class BaseTest {

	@BeforeMethod
	public void login() {
		HomePage homePage = new HomePage();

		homePage.goToHomePage().goToLoginPage().login("ivanhorintest@gmail.com", "ivanhorintestPassword");
	}
	
	@AfterMethod
	public void logOut() {
		HomePage homePage = new HomePage();
        try {
		homePage.logOut();
        }catch(NoSuchElementException e)
        {
        	System.out.println("You are trying to log out but you are not login.");
        }
	}

	@AfterSuite
	public void quit() {
		DriverFactory.getDriver().quit();
	}
}