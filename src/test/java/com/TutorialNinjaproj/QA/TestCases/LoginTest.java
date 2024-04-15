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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialNinja.QA.pages.AccountPage;
import com.TutorialNinja.QA.pages.AccountSuccessPage;
import com.TutorialNinja.QA.pages.Homepage;
import com.TutorialNinja.QA.pages.LoginPage;
import com.TutorialNinja.QA.utils.Utilities;
import com.TutorialNinjaproj.QA.base.Base;



public class LoginTest extends Base {
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	
	
	@BeforeMethod
	public void setUp() {
		String browserName=prop.getProperty("browser");
		driver=initializeBrowserAndOpenAplicationURL(browserName);
		Homepage homePage = new Homepage(driver);
		loginPage = homePage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority = 1,dataProvider = "validCredentials")
	public void verifyLoginWithValidCredentials(String email, String password) {
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountOption(),"Edit Your Account Information option is not Displayed");
		
	}
	@DataProvider(name="validCredentials")
	public Object[][] TestDat(){
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	} 
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		loginPage.login(Utilities.generateEmailWithTimestamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.retriveEmailPasswordNotMatchingWarningMessage(), dataProp.getProperty("emailPasswordWarningMsg"),"Expected Warning Msg is not Displayed");
		
		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInValideEmailAndValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimestamp(), prop.getProperty("validPassword"));
		Assert.assertEquals(loginPage.retriveEmailPasswordNotMatchingWarningMessage(), dataProp.getProperty("emailPasswordWarningMsg"),"Expected Warning Msg is not Displayed");
		
		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValideEmailAndInValidPassword() {
		loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.retriveEmailPasswordNotMatchingWarningMessage(), dataProp.getProperty("emailPasswordWarningMsg"),"Expected Warning Msg is not Displayed");
		
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredential() {
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retriveEmailPasswordNotMatchingWarningMessage(), dataProp.getProperty("emailPasswordWarningMsg"),"Expected Warning Msg is not Displayed");
		
	}
	
	
}
