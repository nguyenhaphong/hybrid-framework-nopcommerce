package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.OrderPageUI;

public class OrdersPageObject extends BasePage {
	private WebDriver driver;
	
	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
