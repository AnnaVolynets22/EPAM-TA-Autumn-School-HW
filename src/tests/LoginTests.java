package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pagesBO.HomePageBO;
import utility.Constants;

public class LoginTests extends BaseTest {

	@Test(enabled = true)
	public void validLoginTest() {

		HomePageBO homePageBO = new HomePageBO();

		Assert.assertFalse(homePageBO.isUserInfoNameEmpty(), "Login fails.");

	}

	@Test(enabled = true)
	public void invalidLoginTest() {
		HomePageBO homePageBO = new HomePageBO();

		homePageBO.goToHomePage();

		LoginPage loginPage = homePageBO.logOut().goToLoginPage().login(Constants.INCORRECT_USERNAME,
				Constants.INCORRECT_PASSWORD);

		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Login is successful with incorrect credentials.");
		loginPage.closeLoginPage();
	}
}