package com.TutorialNinjaproj.QA.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.TutorialNinja.QA.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	
	//public void loadPropertiesFile() 
	public Base() {
		prop=new Properties();
		dataProp=new Properties();
		String config_path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialNinja\\QA\\config\\config.properties";
		String test_data_path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialNinja\\QA\\testData\\testdata.properties";
		File propFile=new File(config_path);
		File testdataPropFile=new File(test_data_path);
		FileInputStream fis;
		FileInputStream fis1;
		
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis1 = new FileInputStream(testdataPropFile);
			dataProp.load(fis1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public WebDriver initializeBrowserAndOpenAplicationURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
		
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		
		return driver;
		
	}

}
