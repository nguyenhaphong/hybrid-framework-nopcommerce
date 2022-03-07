package com.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;

public class Level_11_Upload_Files extends BaseTest {
	String anh1FileName = "Anh1.jpg";
	String anh2FileName = "Anh2.jpg";
	String anh3FileName = "Anh3.jpg";
	String[] multipleFileNames = { anh1FileName, anh2FileName, anh3FileName };
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
	}

	@Test
	public void Upload_01_One_File() {
		homePage.uploadMultipleFiles(driver, anh1FileName);
		
		Assert.assertTrue(homePage.isFileLoadedByName("anh1FileName"));
		
		homePage.clickToStartButton();
		
		Assert.assertTrue(homePage.isFileUpLoadedByName("anh1FileName"));

	}
	
	@Test
	public void Upload_02_Multiple_File() {
		homePage.uploadMultipleFiles(driver, multipleFileNames);	
		
		Assert.assertTrue(homePage.isFileLoadedByName(""));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private HomePageObject homePage;
}
