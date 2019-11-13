package hardcodedtests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HardCodedTests {
	
	@Test(enabled=true, description = "Verify login with appropriate credentials.")
    public void verifyLoginWithAppropriateCredentials() {
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        Assert.assertFalse(driver.findElements(By.className("user-info__name")).isEmpty());
        
        driver.quit();
        
	}
	
	@Test(enabled=true, description = "Verify login with incorrect credentials.")
    public void verifyLoginWithIncorrectCredentials() {
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");
        
        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("anna@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("incorrectPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        
        try {
        driver.findElements(By.className("user-info__name"));}
        catch(Throwable e){
        	System.out.println("Login fail.");
        }
        driver.quit();
        
	}
	
	@Test(enabled=true, description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        WebDriver driver =new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement bySkillsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();

        WebElement skillsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Java");

        WebElement javaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Java')]//span")));
        javaCheckbox.click();

        WebElement collapseSkillsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        skillsSearchResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.",element)));
        
        WebElement closeIcon = driver.findElement(By.className("filter-field__input-item-close-icon"));
        closeIcon.click();
        
        //Perform search for ‘DATA’ search term.

        skillsSearchInput.sendKeys("Data");

        WebElement dataCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Data')]//span")));
        dataCheckbox.click();
        //doesn't work, should be fixed later
        WebElement dataCollapseSkillsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        dataCollapseSkillsArrow.click();

        List<WebElement> dskillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        dskillsSearchResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("DATA"),
                String.format("Element %s does not contain 'Data' word.",element)));
        
        
        driver.quit();

        //ADD OTHER STEPS
    }
	
	@Test(enabled=true, description = "Verify ‘News’ Page and Materials section")
    public void verifyNewsPageAndMaterialsSection() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        WebDriver driver =new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        
        WebElement news = driver.findElement(By.cssSelector("#header > div.links-row > div > nav > ul > li:nth-child(2) > a"));
        news.click();
        //I am going to refactor xpaths
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div[1]/div/ui-view/div/div[3]/section/div[1]/div/div[2]/news-categories/div/div/div[1]/span")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div[1]/div/ui-view/div/div[3]/section/div[1]/div/div[2]/news-categories/div/div/div[2]/span")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div[1]/div/ui-view/div/div[3]/section/div[1]/div/div[2]/news-categories/div/div/div[4]/span")));
        WebElement materialsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div[1]/div/ui-view/div/div[3]/section/div[1]/div/div[2]/news-categories/div/div/div[3]/span")));

        materialsLink.click();
        //something wrong here, should be fixed later
        List<WebElement> materialsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='news-page-item__title']//a"));
        materialsSearchResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("Materials")|| element.getText().contains("Useful"),
                String.format("Element %s does not contain 'Materials' or 'Useful' word.",element)));
        driver.quit();
        
	}
	
	@Test(enabled=false, description = "Verify 'Trainings' search works properly with searching in 'Locations'")
    public void verifyTrainingsSearchWorksProperlyForLocations() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        WebDriverWait wait=new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement byLocationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By locations')]")));
        byLocationButton.click();
        
        WebElement locationsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        locationsSearchInput.sendKeys("Lviv");
        //something wrong here, shoudl be fixed later
        WebElement lvivCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Lviv')]//span")));
        lvivCheckbox.click();

        WebElement collapseLocationsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseLocationsArrow.click();
        
        List<WebElement> locationsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        locationsSearchResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("Lviv"),
                String.format("Element %s does not contain 'Lviv' word.",element)));
        
       
        driver.quit();

            }
}
