package com.TutorialNinjaproj.QA.TestCases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialNinja.QA.pages.AccountSuccessPage;
import com.TutorialNinja.QA.pages.Homepage;
import com.TutorialNinja.QA.pages.RegisterPage;
import com.TutorialNinja.QA.utils.Utilities;
import com.TutorialNinjaproj.QA.base.Base;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		String browserName=prop.getProperty("browser");
		driver=initializeBrowserAndOpenAplicationURL(browserName);
		Homepage homePage=new Homepage(driver);
		registerPage=homePage.navigateToRegisterPage();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterAnAccountWithMandatoryFields() {
		AccountSuccessPage accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimestamp(),dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword1"));
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Please Check Your Details You Are Not Registered successfully");
	}
	
	@Test(priority = 2)
	public void verifyRegisterAccountByProvidingAllFields() {
		AccountSuccessPage accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimestamp(),dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword1"));
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Please Check Your Details You Are Not Registered successfully");
	}
	
	@Test(priority=3)
	public void verifyRegisterAccountWithExistingEmailAccount() {
		registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword1"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Msg Regarding Duplicate Email is not Display");
	}
	
	@Test(priority=4)
	public void  verifyRegisteringWithoutFillingAnydetails() {
		registerPage.clickOnContinueButton();	
	    Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")));
	
	}
	
	
	
}
