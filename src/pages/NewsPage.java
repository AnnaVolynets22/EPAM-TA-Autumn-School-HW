package pages;

import org.openqa.selenium.support.FindBy;

import java.util.List;

import org.openqa.selenium.WebElement;

public class NewsPage extends AbstractPage{
	
	@FindBy(xpath = "//*[@class='tab-nav__item ng-scope active' ]//span[contains(text(), 'News')]")
    private WebElement newsLink;
	
	@FindBy(xpath = "//*[@class='tab-nav__item ng-scope' ]//span[contains(text(), 'Success')]")
	private WebElement successStoriesLink;
	
	@FindBy(xpath = "//*[@class='tab-nav__item ng-scope' ]//span[contains(text(), 'Materials')]")
	private WebElement materialsLink;
	
	@FindBy(xpath = "//*[@class='tab-nav__item ng-scope' ]//span[contains(text(), 'Videos')]")
	private WebElement videosLink;
	
	@FindBy(xpath = "//div[@class='news-page-item__title']//a")
	private List<WebElement> searchResultTitleList;
	
	@FindBy(xpath = "//*[@class='tab-nav__item ng-scope active' ]//span[contains(text(), 'Materials')]")
	private WebElement materialsLinkIsActive;
	
	public boolean isNewsLinkDisplayed() {
		return newsLink.isDisplayed();
	}
	
	public boolean isSuccessStoriesLinkDisplayed() {
		return successStoriesLink.isDisplayed();
	}
	
	public boolean isMaterialsLinkDisplayed() {
		return materialsLink.isDisplayed();
	}
	
	public boolean isVideosLinkDisplayed() {
		return videosLink.isDisplayed();
	}
	
	public WebElement getMaterialsLinkActive() {
		return materialsLinkIsActive;
	}
	
	public boolean isMaterialsLinkActiveDisplayed() {
		return materialsLinkIsActive.isDisplayed();
	}
	
	public NewsPage clickMatrials() {
		waitForElementToBeClickable(materialsLink);
		click(materialsLink);
		return this;
	}
	
	public List<WebElement> getSearchResultTitleList(){
		
		return searchResultTitleList;
	}
	
}
