package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX, "value");
	}

}
