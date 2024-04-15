package com.TutorialNinjaproj.QA.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialNinja.QA.pages.Homepage;
import com.TutorialNinja.QA.pages.SearchPage;
import com.TutorialNinjaproj.QA.base.Base;


public class SearchTest extends Base {
	SearchPage searchPage;
	Homepage homePage;
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	@BeforeMethod
	public void setUp() {
		String browserName=prop.getProperty("browser");
		driver=initializeBrowserAndOpenAplicationURL(browserName);
		homePage = new Homepage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfvalidHPProduct(),"Valid Product"+dataProp.getProperty("validProduct")+"is not Displayed");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		searchPage=homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retriveNoProductMessageText(),"abcd","No Product Message in search Result Is not Displayed");
	    
	}
	
	@Test(priority = 3,dependsOnMethods= {"verifySearchWithValidProduct","verifySearchWithInValidProduct"})
	public void verifySearchWithAnyProduct() {
		searchPage=homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retriveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No Product Message in search Result Is not Displayed");
	    
		
	}
	
	

}
