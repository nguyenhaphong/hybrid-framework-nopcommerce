package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageUIs.UserRegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
	@FindBy(css = "input[id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(css = "input[id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(css = "input[id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(css = "input[id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(css = "input[id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutLink;
	
	@FindBy(css = "#FirstName-error")
	private WebElement firstNameErrorMsg;
	
	@FindBy(css = "#LastName-error")
	private WebElement lastNameErrorMsg;
	
	@FindBy(css = "#Email-error")
	private WebElement emailErrorMsg;
	
	@FindBy(css = "#Password-error")
	private WebElement passwordErrorMsg;
	
	@FindBy(css = "#ConfirmPassword-error")
	private WebElement confirmPasswordErrorMsg;
	
	@FindBy(css = "div[class='result']")
	private WebElement registeredSuccessMsg;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]//li")
	private WebElement existedEmailErrorMsg;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(registerButton);
	}

	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, firstNameErrorMsg);
		return getElementText(firstNameErrorMsg);
	}

	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, lastNameErrorMsg);
		return getElementText(lastNameErrorMsg);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, emailErrorMsg);
		return getElementText(emailErrorMsg);
	}

	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, passwordErrorMsg);
		return getElementText(passwordErrorMsg);
	}

	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, confirmPasswordErrorMsg);
		return getElementText(confirmPasswordErrorMsg);
	}

	public void sendkeyToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(firstNameTextbox, firstName);
	}

	public void sendkeyToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(lastNameTextbox, lastName);	
	}

	public void sendkeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(emailTextbox, emailAddress);	
	}

	public void sendkeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(passwordTextbox, password);	
	}

	public void sendkeyToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(confirmPasswordTextbox, confirmPassword);	
	}

	public String getRegisteredSuccessMessage() {
		waitForElementVisible(driver, registeredSuccessMsg);
		return getElementText(registeredSuccessMsg);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(logoutLink);
		
	}

	public String getExistedEmailErrorMessage() {
		waitForElementVisible(driver, existedEmailErrorMsg);
		return getElementText(existedEmailErrorMsg);
	}

}
