package tests;
 
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
 
public class LoginTests extends BaseTest {
	
    @Test (priority = 1, enabled = true)
    public void validLoginTest() {
 
        HomePage homePage = new HomePage();
 
        homePage.goToHomePage()
                .goToLoginPage()
                .login("ivanhorintest@gmail.com", "ivanhorintestPassword");
        Assert.assertFalse(homePage.isUserInfoNameEmpty(), "Login fails.");
        //cleanup
        homePage.logOut();
    }
 
    @Test (priority = 0, enabled = true)
    public void invalidLoginTest() {
        HomePage homePage = new HomePage();

       LoginPage loginPage = homePage.goToHomePage()
                .goToLoginPage()
                .login("incorrect@gmail.com","incorrect");
       
       Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Login is successful with incorrect credentials.");
       loginPage.closeLoginPage();
    }
}