package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	public By getByXpath(String xpath) {
		return By.xpath(xpath);
	}
	
	public WebElement getWebElement(WebDriver driver, String xpath) {
		return driver.findElement(getByXpath(xpath));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String xpath) {
		return driver.findElements(getByXpath(xpath));
	}
	
	public void clickToElement(WebDriver driver, String xpath) {
		getWebElement(driver, xpath).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String xpath, String value) {
		getWebElement(driver, xpath).clear();
		getWebElement(driver, xpath).sendKeys(value);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String itemText) {
		new Select(getWebElement(driver, xpath)).selectByVisibleText(itemText);
	}
	
	public String getSelectTextInDefaultDropdown(WebDriver driver, String xpath) {
		return new Select(getWebElement(driver, xpath)).getFirstSelectedOption().getText();
	}
	
	public boolean isDefaultDropdownMultiple(WebDriver driver, String xpath) {
		return new Select(getWebElement(driver, xpath)).isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getElementText(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).getText();
	}
	
	public String getElementAttribute(WebDriver driver, String xpath, String attributeName) {
		return getWebElement(driver, xpath).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpath, String propertyName) {
		return getWebElement(driver, xpath).getCssValue(propertyName);
	}
	
	public void getHexaColorByRgbaColor(String rgbaColor) {
		Color.fromString(rgbaColor).asHex();
	}
	
	public int getElementsNumber(WebDriver driver, String xpath) {
		return getWebElements(driver, xpath).size();
	}
	
	public void checkToRadioOrCheckbox(WebDriver driver, String xpath) {
		if(!getWebElement(driver, xpath).isSelected()) {
			getWebElement(driver, xpath).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String xpath) {
		if(getWebElement(driver, xpath).isSelected()) {
			getWebElement(driver, xpath).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String xpath) {
		return getWebElement(driver, xpath).isEnabled();
	}
	
	public void switchToFrame(WebDriver driver, String xpath) {
		driver.switchTo().frame(getWebElement(driver, xpath));
	}
	
	public void switchToDefaultContentPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpath) {
		new Actions(driver).moveToElement(getWebElement(driver, xpath)).perform();
	}
	
	public void pressKeyboardToElement(WebDriver driver, String xpath, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpath), key).perform();
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String xpath) {
		WebElement element = getWebElement(driver, xpath);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpath) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpath));
	}

	public void scrollToElement(WebDriver driver, String xpath) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpath));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpath, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, xpath));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpath, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpath));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpath) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpath));
	}

	public boolean isImageLoaded(WebDriver driver, String xpath) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpath));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String xpath) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpath)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpath) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(xpath)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpath) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpath)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpath) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpath)));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpath) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, xpath)));
	}
	
	private long longTimeout = 30;

}
