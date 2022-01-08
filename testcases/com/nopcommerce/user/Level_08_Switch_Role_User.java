package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalContants;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.users.UserHomePageObject;
import pageObjects.users.UserLoginPageObject;

public class Level_08_Switch_Role_User extends BaseTest {
	private WebDriver driver;
	UserHomePageObject userHomePage; // khai báo
	UserLoginPageObject userLoginPage;
	AdminLoginPageObject adminLoginPage;
	AdminDashboardPageObject adminDashboardPage;
	
	String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

	@Parameters({ "browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		// 1 - Quyền user mở URL của trang user lên thì nó qua trang Homepage
		driver = getBrowserDriver(browserName, GlobalContants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userEmailAddress = "";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_Switch_User_To_Admin() {
		// Home => Login (User)
		userLoginPage = userHomePage.clickToLoginLink();
		
		// Login vào
		userLoginPage.inputToEmailTextbox(userEmailAddress);
		userLoginPage.inputToPasswordTextbox(userPassword);
		userHomePage = userLoginPage.clickToLoginButton();
		
		// Mở ra admin url => Login (Admin)
		userHomePage.openUrl(driver, GlobalContants.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		// Login => Dashboard
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword); 
		
		// Admin Page => Logout
		adminLoginPage = adminDashboardPage.clickAdminLogoutLink(driver);

	}

	@Test
	public void Role_02_Switch_Admin_To_User() {

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
