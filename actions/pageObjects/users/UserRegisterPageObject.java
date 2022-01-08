package pageObjects.users;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);	
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);	
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);	
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);	
	}

	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTERED_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTERED_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getExistedEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EXISTED_EMAIL_ERROR_MESSAGE);
	}

}
