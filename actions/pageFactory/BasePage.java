package pageFactory;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	/* Web Browser */
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public Alert waitAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		waitAlertPresence(driver).sendKeys(value);
	}

	public void switchToWindowByID(WebDriver driver, String windownPageID) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String window : allWindowns) {
			if (!window.equals(windownPageID)) {
				driver.switchTo().window(window);
			}
		}
	}

	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String window : allWindowns) {
			driver.switchTo().window(window);
			sleepInSecond(2);

			String actualPageTitle = driver.getTitle().trim();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent (WebDriver driver, String parentPageID) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String window : allWindowns) {
			if (!window.equals(parentPageID))  {
				driver.switchTo().window(window);
				sleepInSecond(2);
				driver.close();
			}
		}
		driver.switchTo().window(parentPageID);
		sleepInSecond(1);
	}
	
	public void sleepInSecond(long timeoutlnSecond) {
		try {
			Thread.sleep(timeoutlnSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sleepInMiliSecond(long timeoutlnMiliSecond) {
		try {
			Thread.sleep(timeoutlnMiliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* Web Element */	
	
	public void clickToElement(WebElement element) {
		element.click();
	}
	
	public void sendkeyToElement(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public void selectItemInDefaultDropdown(WebElement element, String itemText) {
		new Select(element).selectByVisibleText(itemText);
	}
	
	public String getSelectTextInDefaultDropdown(WebElement element) {
		return new Select(element).getFirstSelectedOption().getText();
	}
	
	public boolean isDefaultDropdownMultiple(WebElement element) {
		return new Select(element).isMultiple();
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public int getElementsNumber(List<WebElement> elements) {
		return elements.size();
	}
	
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	private long longTimeout = 30;

}
