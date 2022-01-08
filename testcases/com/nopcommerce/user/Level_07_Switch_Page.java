package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.users.UserAddressPageObject;
import pageObjects.users.UserCustomerInfoPageObject;
import pageObjects.users.UserHomePageObject;
import pageObjects.users.UserLoginPageObject;
import pageObjects.users.UserOrdersPageObject;
import pageObjects.users.UserRegisterPageObject;
import pageObjects.users.UserRewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage; // khai báo
	private UserRegisterPageObject registerPage; // khai báo
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserOrdersPageObject orderPage;
	private UserAddressPageObject addressPage;
	private UserRewardPointPageObject rewardPointPage;
	private String firstName, lastName, emailAddress, password;
	

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		//Khởi tạo page lên
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName ="John";
		lastName = "Terry";
		emailAddress = "johnterry" + generateFakeNumber() + "@mail.net";
		password = "123456";
	}

	@Test
	public void User_01_Register_To_System() {
		registerPage = homePage.clickToResgiterLink(); // Mở ra trang Register Page 
		
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink(); // Từ trang Register chuyển về trang Home Page
	}

	@Test
	public void User_02_Login_To_System() {
		loginPage = homePage.clickToLoginLink(); // Từ trang Home chuyển về trang Login Page
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton(); // Từ trang Login chuyển về trang Home Page 

	}

	@Test
	public void User_03_My_Account_Infor() {
		customerInfoPage = homePage.clickToMyAccountLink(); // Từ trang Home chuyển về trang Customer Info Page
		
		Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
		Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
	}
	
	@Test
	public void User_04_Navigate_Page() {
		// Customer Info Page => Orders Page
		orderPage = customerInfoPage.openOrderPage(driver);
		
		// Orders Page => Reward Point
		rewardPointPage = orderPage.openRewardPointPage(driver);
		
		// Reward Point => Orders Page
		orderPage = rewardPointPage.openOrderPage(driver);
		
		// Order Page => Customer Info Page
		customerInfoPage = orderPage.openCustomerInfoPage(driver);
		
		// Customer Info Page => Address Page
		addressPage = customerInfoPage.openAddressPage(driver);
		
		rewardPointPage = addressPage.openRewardPointPage(driver);
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
