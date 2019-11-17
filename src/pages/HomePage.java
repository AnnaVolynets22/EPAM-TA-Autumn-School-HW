package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends AbstractPage {

    String baseURL = "https://training.by/#/Home";
    
    @FindBy(xpath = "//p[@class='header-auth__signin']//span")
    private WebElement signInButton;
    
    @FindBy(xpath = "//*[@class='main-nav__list']//a[@class='topNavItem training click hover' and contains(@href,'TrainingList')]")
    private WebElement trainingListButton;
    
    @FindBy(xpath = "//*[@class='main-nav__list']//*[@class='topNavItem news click hover']")
    private WebElement newsButton;
    
	@FindBy(xpath = "//*[@class='user-info dropdown-toggle']/*[@class='user-info__arrow']//*[@class='arrow']")
	private WebElement userInfoArrow;
	
	@FindBy(xpath = "//*[@id='header']/div[1]/div[2]/div[2]/div[1]/div/div[5]/a")
	private WebElement logOut;
	
	@FindBy(className = "user-info__name")
	private WebElement userInfoName;
    

    public HomePage goToHomePage (){
        driver.get(baseURL);
        return this;
    }
 
    public LoginPage goToLoginPage (){
    	//waitForVisibility(signInButton);
        click(signInButton);
        return new LoginPage();
    }
   
    public TrainingListPage goToTrainingListPage (){
        click(trainingListButton);
        return new TrainingListPage();
    }
    
    public NewsPage goToNewsPage (){
        click(newsButton);
        return new NewsPage();
    }
    
	public HomePage logOut() {
		click(userInfoArrow);
		//waitForVisibility(logOut);
		click(logOut);
		return new HomePage();
	}
	
	public boolean isUserInfoNameEmpty()
	{
		return userInfoName.getText().isEmpty();
	}
}
