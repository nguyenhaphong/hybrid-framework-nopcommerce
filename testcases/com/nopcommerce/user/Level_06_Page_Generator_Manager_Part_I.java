package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_06_Page_Generator_Manager_Part_I extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage; // khai báo
	private RegisterPageObject registerPage; // khai báo
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, password;
	

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		//Khởi tạo page lên
		homePage = new HomePageObject(driver);
		
		firstName ="John";
		lastName = "Terry";
		emailAddress = "johnterry" + generateFakeNumber() + "@mail.net";
		password = "123456";
	}

	@Test
	public void User_01_Register_To_System() {
		homePage.clickToResgiterLink(); // Mở ra trang Register Page
		registerPage = new RegisterPageObject(driver);
		
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
		
		registerPage.clickToLogoutLink(); // Từ trang Register chuyển về trang Home Page
		homePage = new HomePageObject(driver);
	}

	@Test
	public void User_02_Login_To_System() {
		homePage.clickToLoginLink(); // Từ trang Home chuyển về trang Login Page
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver); // Từ trang Login chuyển về trang Home Page 

	}

	@Test
	public void User_03_My_Account_Infor() {
		homePage.clickToMyAccountLink();
		customerInfoPage = new CustomerInfoPageObject(driver); // Từ trang Home chuyển về trang Customer Info Page
		
		Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
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
