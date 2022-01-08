package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.UserRewardPointPageUI;

public class UserRewardPointPageObject extends BasePage {
	private WebDriver driver;
	
	public UserRewardPointPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
