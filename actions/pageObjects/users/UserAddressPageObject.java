package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.UserAddressPageUI;
import pageUIs.UserRewardPointPageUI;

public class UserAddressPageObject extends BasePage {
	private WebDriver driver;
	
	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
