package pages;

import org.openqa.selenium.support.FindBy;

import java.util.List;

import org.openqa.selenium.WebElement;


public class TrainingListPage extends AbstractPage {
	
	@FindBy(className = "filter-toggle__arrow-icon")
	private WebElement expandSkillsArrow;

	@FindBy(xpath = "//div[contains(text(),'By skills')]")
	private WebElement bySkillsButton;

	@FindBy(xpath = "//input[@name='training-filter-input']")
	private WebElement skillsSearchInput;

	@FindBy(xpath = "//label[contains(.,'Java')]//span")
	private WebElement javaCheckbox;

	@FindBy(xpath = "//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']")
	private WebElement collapseSkillsArrow;

	@FindBy(xpath = "//div[@class='training-list__container training-list__desktop']//a")
	private List<WebElement> skillsSearchResultsList;

	@FindBy(className = "filter-field__input-item-close-icon")
	private WebElement closeIcon;

	@FindBy(xpath = "//label[contains(.,'Data')]//span")
	private WebElement dataCheckbox;

	@FindBy(xpath = "//label[@class='training-list__container training-list__desktop']//a")
	private List<WebElement> dskillsSearchResultsList;

	@FindBy(xpath = "//label[contains(.,'pascal')]//span")
	private WebElement pascalCheckbox;

	@FindBy(xpath = "//div[contains(text(),'By locations')]")
	private WebElement byLocationButton;

	@FindBy(xpath = "//li/div[contains(text(),\"Ukraine\")]")
	private WebElement country;

	@FindBy(xpath = "//label[contains(.,'Lviv')]//span")
	private WebElement lvivCheckbox;

	@FindBy(css = ".training-item__location ng-binding:before")
	private List<WebElement> skillsByLocationSearchResultsList;
    
	public WebElement getExpandSkillsArrow() {
		waitForVisibility(expandSkillsArrow);
		return expandSkillsArrow;
	}

	public void setExpandSkillsArrow(WebElement expandSkillsArrow) {
		this.expandSkillsArrow = expandSkillsArrow;
	}

	public WebElement getBySkillsButton() {
		waitForVisibility(bySkillsButton);
		return bySkillsButton;
	}

	public WebElement getSkillsSearchInput() {
		waitForVisibility(skillsSearchInput);
		return skillsSearchInput;
	}

	public WebElement getJavaCheckbox() {
		waitForVisibility(javaCheckbox);
		return javaCheckbox;
	}

	public WebElement getCollapseSkillsArrow() {
		waitForVisibility(collapseSkillsArrow);
		return collapseSkillsArrow;
	}

	public List<WebElement> getSkillsSearchResultsList() {
		return skillsSearchResultsList;
	}

	public WebElement getCloseIcon() {
		waitForVisibility(closeIcon);
		return closeIcon;
	}

	public WebElement getDataCheckbox() {
		waitForVisibility(dataCheckbox);
		return dataCheckbox;
	}

	public List<WebElement> getDskillsSearchResultsList() {
		return dskillsSearchResultsList;
	}

	public WebElement getPascalCheckbox() {
		return pascalCheckbox;
	}

	public WebElement getByLocationButton() {
		return byLocationButton;
	}

	public WebElement getCountry() {
		return country;
	}

	public WebElement getLvivCheckbox() {
		return lvivCheckbox;
	}

	public List<WebElement> getSkillsByLocationSearchResultsList() {
		return skillsByLocationSearchResultsList;
	}


	
}
