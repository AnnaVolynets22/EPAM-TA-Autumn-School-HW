package tests;
 

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import driverfactory.DriverFactory;
import pages.HomePage;

 
public class BaseTest {
 
   /* @AfterTest
    public void logOut () {
        HomePage homePage = new HomePage();
        if(!homePage.isUserInfoNameEmpty()) {
        homePage.logOut();
        }
    }*/
    
    @AfterSuite
    public void quit () {
        DriverFactory.getDriver().quit();
    }
}