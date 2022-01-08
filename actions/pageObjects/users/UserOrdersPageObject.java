package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.UserOrderPageUI;

public class UserOrdersPageObject extends BasePage {
	private WebDriver driver;
	
	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
