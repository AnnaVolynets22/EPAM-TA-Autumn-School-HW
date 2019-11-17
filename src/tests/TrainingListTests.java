package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.TrainingListPage;

public class TrainingListTests extends BaseTest {

	@Test(enabled = true, priority = 2, description = "Verify 'Trainings' search works properly with searching in 'Skills'", expectedExceptions = NoSuchElementException.class)
	public void verifyTrainingsSearchWorksProperlyForSkills() {
        HomePage homePage = new HomePage();
        
        homePage.goToHomePage()
                .goToLoginPage()
                .login("ivanhorintest@gmail.com", "ivanhorintestPassword");
        
        Assert.assertFalse(homePage.isUserInfoNameEmpty(), "Login fails.");
        TrainingListPage trainingListPage = homePage.goToTrainingListPage();
        
        /*trainingListPage.click(trainingListPage.getExpandSkillsArrow());
        trainingListPage.click(trainingListPage.getBySkillsButton());
        trainingListPage.writeText(trainingListPage.getSkillsSearchInput(), "Java");
        trainingListPage.click(trainingListPage.getJavaCheckbox());
        trainingListPage.click(trainingListPage.getCollapseSkillsArrow());
        
        try {
        trainingListPage.getSkillsSearchResultsList().forEach(element ->System.out.println(element.getText()));
		trainingListPage.getSkillsSearchResultsList().forEach(element -> Assert.assertTrue(element.getText().toLowerCase().contains("java"),
				String.format("Element %s does not contain 'Java' word.", element)));
        }
        catch(org.openqa.selenium.NoSuchElementException e) {
        trainingListPage.getSkillsSearchResultsList().forEach(element -> Assert.assertTrue(element.getText().toLowerCase().contains("java"),
    			String.format("Element %s does not contain 'Java' word.", element)));
        }
		trainingListPage.click(trainingListPage.getCloseIcon());*/

		// Perform search for ‘DATA’ search term.
        trainingListPage.click(trainingListPage.getExpandSkillsArrow());
		trainingListPage.click(trainingListPage.getBySkillsButton());
		trainingListPage.writeText(trainingListPage.getSkillsSearchInput(), "Data");
		trainingListPage.click(trainingListPage.getDataCheckbox());
		trainingListPage.click(trainingListPage.getCollapseSkillsArrow());

		trainingListPage.getDskillsSearchResultsList().forEach(element -> Assert.assertTrue(element.getText().toLowerCase().contains("data"),
				String.format("Element %s does not contain 'data' word.", element)));
		
		trainingListPage.click(trainingListPage.getCloseIcon());
		// Perform search for ‘Pascal’ search term.
		trainingListPage.click(trainingListPage.getExpandSkillsArrow());
		trainingListPage.click(trainingListPage.getBySkillsButton());
		trainingListPage.writeText(trainingListPage.getSkillsSearchInput(), "Pascal");

		Assert.assertFalse(trainingListPage.getPascalCheckbox().isDisplayed());
		
        //cleanup
        homePage.logOut();
	}
	
	@Test(enabled = true, priority = 2, description = "Verify 'Trainings' search works properly with searching in 'Locations'")
	public void verifyTrainingsSearchWorksProperlyForLocations() {

        HomePage homePage = new HomePage();
 
        homePage.goToHomePage()
                .goToLoginPage()
                .login("ivanhorintest@gmail.com", "ivanhorintestPassword");
        
        Assert.assertFalse(homePage.isUserInfoNameEmpty(), "Login fails.");
        TrainingListPage trainingListPage = homePage.goToTrainingListPage();

		trainingListPage.click(trainingListPage.getExpandSkillsArrow());
		trainingListPage.click(trainingListPage.getByLocationButton());
		trainingListPage.click(trainingListPage.getCountry());
		trainingListPage.click(trainingListPage.getLvivCheckbox());
		trainingListPage.click(trainingListPage.getCollapseSkillsArrow());

		trainingListPage.getSkillsByLocationSearchResultsList().forEach(element -> Assert.assertEquals(element.getText(), "Lviv, Ukraine"));
        
        //cleanup
        homePage.logOut();
	}

}
