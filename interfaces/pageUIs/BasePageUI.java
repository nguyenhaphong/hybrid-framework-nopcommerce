package pageUIs;

public class BasePageUI {
	public static final String ORDER_PAGE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Orders']";
	public static final String ADDRESS_PAGE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String REWARD_POINT_PAGE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	public static final String CUSTOMER_INFO_PAGE_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
	
	public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
	
	// Admin logout
	public static final String ADMIN_LOGOUT_LINK = "xpath//div[@id='navbarText']//a[text()='Logout']";
	public static final String USER_LOGOUT_LINK = "xpath=//a[@class='ico-logout']";
}
