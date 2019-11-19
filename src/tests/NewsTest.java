package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewsPage;

public class NewsTest extends BaseTest{
	
    @Test (enabled = true, description = "Verify ‘News’ Page and Materials section")
    public void verifyNewsPageAndMaterialsSection() {
 
        HomePage homePage = new HomePage();
      
        Assert.assertFalse(homePage.isUserInfoNameEmpty(), "Login fails.");

        NewsPage newsPage = homePage.goToNewsPage();
        Assert.assertTrue(newsPage.isNewsLinkDisplayed());
        Assert.assertTrue(newsPage.isSuccessStoriesLinkDisplayed());
        Assert.assertTrue(newsPage.isMaterialsLinkDisplayed());
        Assert.assertTrue(newsPage.isVideosLinkDisplayed());
        
        newsPage.clickMatrials()
                .waitForVisibility(newsPage.getMaterialsLinkActive());
        Assert.assertTrue(newsPage.isMaterialsLinkActiveDisplayed());
       // newsPage.getSearchResultTitleList().forEach(element-> System.out.println(element.getText()));
        newsPage.getSearchResultTitleList().forEach(element-> Assert.assertTrue(
				element.getText().toLowerCase().contains("materials") || element.getText().contains("Useful"),
				String.format("Element %s does not contain 'Materials' or 'Useful' word.", element))); 
                 
    }

}
