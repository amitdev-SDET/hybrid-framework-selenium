package com.TutorialNinja.QA.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	WebDriver driver;
	
	//Objects 
	@FindBy(name = "search")
	private WebElement searchBoxField;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement  myAccountDropDownMenu;
	
	@FindBy(linkText = "Login")
	private WebElement  loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	
	public Homepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Actions
	public void clickOnMyAccount() {
		myAccountDropDownMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
		
	}
	public LoginPage navigateToLoginPage() {
		myAccountDropDownMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropDownMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductNameIntoSearchBoxField(String productName) {
		searchBoxField.sendKeys(productName);
	}
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchForAProduct(String productName) {
		searchBoxField.sendKeys(productName);
		searchButton.click();
		return new SearchPage(driver);
	}

}
