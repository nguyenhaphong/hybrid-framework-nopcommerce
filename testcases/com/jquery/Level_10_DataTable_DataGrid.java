package com.jquery;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActived("18"));
		
	}
	
	
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		homePage.sleepInSecond(3);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
		homePage.enterToHeaderTextboxByLabel("Females", "276880");
		homePage.enterToHeaderTextboxByLabel("Males", "276472");
		homePage.enterToHeaderTextboxByLabel("Total", "553353");
		homePage.sleepInSecond(3);
	}
	
	
	public void Table_03_Enter_To_Header() {
		// �?�?c dữ liệu từ file country.txt ra lưu vào 1 list<String> = Expected value
		
		
		actualAllCountryValues = homePage.getValueEachRowAtAllPage();
		
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}
	
	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.sleepInSecond(5);
		// Value để nhập
		// Row number tại row nào (Nhập vào dòng 2/3/5)
		// Column name tại coloumn nào (Tên cột)
		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "2", "Michael 97");
		homePage.sleepInSecond(2);
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "4", "Michael Jackson");
		homePage.sleepInSecond(2);
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1997");
		homePage.sleepInSecond(2);
		
		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "2", "150");
		homePage.sleepInSecond(2);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");
		homePage.sleepInSecond(2);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "Hong Kong");
		homePage.sleepInSecond(2);
		
		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "1", "US");
		homePage.sleepInSecond(2);
		
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
		
		homePage.clickToIconButtonByRowNumber("1", "Remove Current Row");
		
		homePage.clickToIconButtonByRowNumber("3", "Insert Row Above");
		
		homePage.clickToIconButtonByRowNumber("5", "Move Up");
		
		homePage.clickToIconButtonByRowNumber("5", "Remove Current Row");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;

}
