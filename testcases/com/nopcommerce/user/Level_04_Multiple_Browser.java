package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String emailAddress;
	private HomePageObject homePage; // khai báo
	private RegisterPageObject registerPage; // khai báo
	

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		//Khởi tạo page lên
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
		Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password is required.");
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);
		
		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox("123@456$%%%");
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

		registerPage.clickToLogoutLink(); // Từ trang Register chuyển về trang Home Page
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		Assert.assertEquals(registerPage.getExistedEmailErrorMessage(), "The specified email already exists");

	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getPasswordErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters");

	}
	
	@Test
	public void TC_06_Invalid_Confirm_Password() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("654321");
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
