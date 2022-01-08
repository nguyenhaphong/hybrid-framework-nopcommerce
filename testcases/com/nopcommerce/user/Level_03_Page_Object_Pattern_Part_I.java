package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.users.UserHomePageObject;
import pageObjects.users.UserRegisterPageObject;

public class Level_03_Page_Object_Pattern_Part_I {
	private WebDriver driver;
	private String emailAddress;
	private UserHomePageObject homePage; // khai báo
	private UserRegisterPageObject registerPage; // khai báo
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		
		// Mở url thì mở ra trang homepage
		driver.get("https://demo.nopcommerce.com/");
		//Khởi tạo page lên
		homePage = new UserHomePageObject(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new UserRegisterPageObject(driver);
		
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
		registerPage = new UserRegisterPageObject(driver);
		
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
		registerPage = new UserRegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");
		
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");

		registerPage.clickToLogoutLink(); // Từ trang Register chuyển về trang Home Page
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new UserRegisterPageObject(driver);

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
		registerPage = new UserRegisterPageObject(driver);

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
		registerPage = new UserRegisterPageObject(driver);

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
