package com.TutorialNinja.QA.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	
	public static String generateEmailWithTimestamp() {
		Date date=new Date();
		String timeStamp=date.toString().replace(" ", "_").replace(":", "_");
		return "amitsdet3227"+timeStamp+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)  {
		   String exel_path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialNinja\\QA\\testData\\TutorialNinjaTestdata.xlsx";
		   FileInputStream fisExcel;
		   XSSFWorkbook  workbook=null;
		try {
			fisExcel = new FileInputStream(exel_path);
		    workbook=new XSSFWorkbook(fisExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   XSSFSheet sheet = workbook.getSheet(sheetName);
		   
		   int rows=sheet.getLastRowNum();
		   int cols=sheet.getRow(0).getLastCellNum();
		   
		   Object[][] data=new Object[rows][cols];
		   
		   for(int i=0;i<rows;i++) {
			   XSSFRow row = sheet.getRow(i+1);
			   for(int j=0;j<cols;j++) {
				   XSSFCell cell = row.getCell(j);
				   CellType celltype = cell.getCellType();
				   
				   switch (celltype) {
				   case STRING:
					   data[i][j]=cell.getStringCellValue();
					   break;
				   case NUMERIC:
					   data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					   break;
				   case BOOLEAN:
					   data[i][j]=cell.getBooleanCellValue();
					
				   }
			   }
		   }
		   
			
		return data;
	} 
	
	public static String captureScreenShot(WebDriver driver,String testName) {
		File screenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(screenShot,new File(destinationScreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenShotPath;
	}
	


}
