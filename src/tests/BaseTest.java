package tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import driverfactory.DriverFactory;
import pagesBO.HomePageBO;
import utility.Constants;

public class BaseTest {

	@BeforeMethod
	public void login() {
		HomePageBO homePageBO = new HomePageBO();

		homePageBO.goToHomePage().goToLoginPage().login(Constants.USERNAME, Constants.PASSWORD);
	}
	
	@AfterMethod
	public void logOut() {
		HomePageBO homePageBO = new HomePageBO();
        try {
		homePageBO.logOut();
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