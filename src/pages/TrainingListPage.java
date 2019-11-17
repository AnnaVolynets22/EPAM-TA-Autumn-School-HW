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

	public void setBySkillsButton(WebElement bySkillsButton) {
		this.bySkillsButton = bySkillsButton;
	}

	public WebElement getSkillsSearchInput() {
		waitForVisibility(skillsSearchInput);
		return skillsSearchInput;
	}

	public void setSkillsSearchInput(WebElement skillsSearchInput) {
		this.skillsSearchInput = skillsSearchInput;
	}

	public WebElement getJavaCheckbox() {
		waitForVisibility(javaCheckbox);
		return javaCheckbox;
	}

	public void setJavaCheckbox(WebElement javaCheckbox) {
		this.javaCheckbox = javaCheckbox;
	}

	public WebElement getCollapseSkillsArrow() {
		waitForVisibility(collapseSkillsArrow);
		return collapseSkillsArrow;
	}

	public void setCollapseSkillsArrow(WebElement collapseSkillsArrow) {
		this.collapseSkillsArrow = collapseSkillsArrow;
	}

	public List<WebElement> getSkillsSearchResultsList() {
		return skillsSearchResultsList;
	}

	public void setSkillsSearchResultsList(List<WebElement> skillsSearchResultsList) {
		this.skillsSearchResultsList = skillsSearchResultsList;
	}

	public WebElement getCloseIcon() {
		waitForVisibility(closeIcon);
		return closeIcon;
	}

	public void setCloseIcon(WebElement closeIcon) {
		this.closeIcon = closeIcon;
	}

	public WebElement getDataCheckbox() {
		waitForVisibility(dataCheckbox);
		return dataCheckbox;
	}

	public void setDataCheckbox(WebElement dataCheckbox) {
		this.dataCheckbox = dataCheckbox;
	}

	public List<WebElement> getDskillsSearchResultsList() {
		return dskillsSearchResultsList;
	}

	public void setDskillsSearchResultsList(List<WebElement> dskillsSearchResultsList) {
		this.dskillsSearchResultsList = dskillsSearchResultsList;
	}

	public WebElement getPascalCheckbox() {
		return pascalCheckbox;
	}

	public void setPascalCheckbox(WebElement pascalCheckbox) {
		this.pascalCheckbox = pascalCheckbox;
	}

	public WebElement getByLocationButton() {
		return byLocationButton;
	}

	public void setByLocationButton(WebElement byLocationButton) {
		this.byLocationButton = byLocationButton;
	}

	public WebElement getCountry() {
		return country;
	}

	public void setCountry(WebElement country) {
		this.country = country;
	}

	public WebElement getLvivCheckbox() {
		return lvivCheckbox;
	}

	public void setLvivCheckbox(WebElement lvivCheckbox) {
		this.lvivCheckbox = lvivCheckbox;
	}

	public List<WebElement> getSkillsByLocationSearchResultsList() {
		return skillsByLocationSearchResultsList;
	}

	public void setSkillsByLocationSearchResultsList(List<WebElement> skillsByLocationSearchResultsList) {
		this.skillsByLocationSearchResultsList = skillsByLocationSearchResultsList;
	}

	
	
}
