package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

	@Test(enabled = true)
	public void validLoginTest() {

		HomePage homePage = new HomePage();

		Assert.assertFalse(homePage.isUserInfoNameEmpty(), "Login fails.");

	}

	@Test( enabled = true)
	public void invalidLoginTest() {
        HomePage homePage = new HomePage();
        
		LoginPage loginPage = homePage.goToHomePage()
		        .logOut()
		        .goToLoginPage()
		        .login("incorrect@gmail.com", "incorrect");

		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Login is successful with incorrect credentials.");
		loginPage.closeLoginPage();
	}
}