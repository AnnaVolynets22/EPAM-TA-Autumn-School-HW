package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import pages.HomePage;

public class LoginPage extends AbstractPage {
	String baseURL = "https://training.by/#/Home";
	
	@FindBy(id = "signInEmail")
	private WebElement userNameBy;
	
	@FindBy(id = "signInPassword")
	private WebElement passwordBy;
	
	@FindBy(className = "popup-reg-sign-in-form__sign-in")
	private WebElement loginButtonBy;
	
	@FindBy(xpath = "//div[@class='popup__error-message ng-binding']")
	private WebElement errorMessage;
	
	@FindBy(className = "popup-title__close")
	private WebElement closeLogin;
	
	public WebElement getUserNameBy() {
		return userNameBy;
	}

	public void setUserNameBy(WebElement userNameBy) {
		this.userNameBy = userNameBy;
	}


	public WebElement getPasswordBy() {
		return passwordBy;
	}


	public void setPasswordBy(WebElement passwordBy) {
		this.passwordBy = passwordBy;
	}


	public WebElement getLoginButtonBy() {
		return loginButtonBy;
	}

	public WebElement getErrorMessage() {
		return errorMessage;
	}
	
	public WebElement getCloseLogin() {
		return closeLogin;
	}
    
	public boolean isErrorMessageDisplayed()
	{
		return errorMessage.isDisplayed();
	}
	
	
	public LoginPage login(String username, String password) {
		// Enter Username(Email)
		writeText(userNameBy, username);
		// Enter Password
		writeText(passwordBy, password);
		// Click Login Button
		click(loginButtonBy);
		return this;
	}
	


	public LoginPage verifyLogin(String expectedText) {
		containText(errorMessage, expectedText);
		return this;
	}
	
	public void closeLoginPage() {
		click(closeLogin);
	}
	
}