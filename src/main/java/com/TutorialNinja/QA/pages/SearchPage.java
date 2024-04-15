package com.TutorialNinja.QA.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessageText;
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	public boolean displayStatusOfvalidHPProduct() {
		 boolean validHPProductText = validHPProduct.isDisplayed();
		 return validHPProductText;
	}
	
	
	public String retriveNoProductMessageText() {
		String noProductMessage = noProductMessageText.getText();
		return noProductMessage;
	}
	
	

}
