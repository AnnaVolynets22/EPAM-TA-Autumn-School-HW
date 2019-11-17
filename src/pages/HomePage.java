package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends AbstractPage {

    String baseURL = "https://training.by/#/Home";
    @FindBy(xpath="//p[@class='header-auth__signin']//span")
    private WebElement signInButtonBy;

    public HomePage goToHomePage (){
        driver.get(baseURL);
        return this;
    }
 
    public LoginPage goToLoginPage (){
        click(signInButtonBy);
        return new LoginPage();
    }

}
