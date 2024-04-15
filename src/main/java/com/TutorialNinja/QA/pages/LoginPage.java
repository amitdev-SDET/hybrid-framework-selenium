package com.TutorialNinja.QA.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	 
	WebDriver driver;
	
	//Objects
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarningMessage;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Actions
	public  void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}
	

	public  void enterPassword(String passwordText) {
			passwordField.sendKeys(passwordText);
		}
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public AccountPage login(String email,String passwordText) {
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public String retriveEmailPasswordNotMatchingWarningMessage() {
		String warningMsg=emailPasswordNotMatchingWarningMessage.getText();
		return warningMsg;
	}

}
