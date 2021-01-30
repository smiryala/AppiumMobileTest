package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.AccountPageData;
import com.sw.proapp.data.HomePageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageLocators extends MobileBasePage {

    public HomePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSNsPredicate  = HomePageData.SEARCH_BUTTON_IOS)
    @AndroidFindBy(id = HomePageData.SEARCH_BUTTON)
    protected WebElement searchButton;

    @iOSXCUITFindBy(accessibility =  HomePageData.PRO_HOME_BUTTON_IOS)
    @AndroidFindBy(id = HomePageData.PRO_HOME_BUTTON)
    protected WebElement proAppHomeButton;

    @iOSXCUITFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_EXPAND_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_EXPAND)
    protected WebElement frequentlyPurchasedExpand;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_CART_IOS)
    @AndroidFindBy(id = HomePageData.PRO_CART)
    protected WebElement cart;

    @iOSXCUITFindBy(accessibility = HomePageData.CART_ITEMS_0_IOS)
   // @AndroidFindBy(xpath = )
    protected WebElement cartEmpty;

    

	@iOSXCUITFindBy(xpath = HomePageData.PRO_REMOVE_MESSAGE_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_REMOVE_MESSAGE)
    protected WebElement removeMessage;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_FREQUENTLY_PURCHASED_SECTION_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_SECTION)
    protected WebElement frequentlyPurchasedSection;

    @iOSXCUITFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_MORE_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_MORE)
    protected WebElement frequentlyPurchasedMore;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_ACCOUNT_SUMMARY_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_ACCOUNT_SUMMARY)
    protected WebElement accountSummary;

    @iOSXCUITFindBy(xpath =  HomePageData.PRO_ACCOUNT_SUMMARY_DETAILS_IOS)
    @AndroidFindBy(id = HomePageData.PRO_ACCOUNT_SUMMARY_DETAILS)
    protected WebElement accountSummaryDetails;

    @iOSXCUITFindBy(iOSNsPredicate = HomePageData.PRO_STORE_INFO_IOS)
    @AndroidFindBy(id = HomePageData.PRO_STORE_INFO)
    protected WebElement storeInfo;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_ORDER_HISTORY_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_ORDER_HISTORY)
    protected WebElement orderHistory;

    @iOSXCUITFindBy(xpath =  HomePageData.PRO_RECENT_ONLINE_ORDER_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_RECENT_ONLINE_ORDER)
    protected WebElement recentOnlineOrder;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_DOING_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_DOING)
    protected WebElement howAreWeDoing;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_ADVANTAGES_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_ADVANTAGES)
    protected WebElement proAdvantages;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_CONTINUE_SHOPPING_IOS)
    @AndroidFindBy(id = HomePageData.PRO_CONTINUE_SHOPPING)
    protected WebElement continueShopping;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_LOGO_IOS)
    @AndroidFindBy(id = HomePageData.PRO_LOGO)
    protected WebElement proSherwinLogo;

    @iOSXCUITFindBy(xpath = HomePageData.SEARCH_CANCEL_IOS)
    protected WebElement search_cancel;

    @iOSXCUITFindBy(xpath = HomePageData.STORE_LOGO_IOS)
    @AndroidFindBy(xpath = HomePageData.FIND_STORES)
    protected WebElement find_store;

    //	List Elements
    @iOSXCUITFindBy(id = HomePageData.PRO_REMOVE_BUTTON_IOS)
    @AndroidFindBy(id = HomePageData.PRO_REMOVE_BUTTON)
    protected List<WebElement> removeButton;

    @iOSXCUITFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_ITEM_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_ITEM)
    protected List<WebElement> FrequentlyPurchasedItems;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_ADVANTAGES_SECTIONS_IOS)
    @AndroidFindBy(xpath = HomePageData.PRO_ADVANTAGES_SECTIONS)
    protected List<WebElement> proAdvantagesSections;

    @iOSXCUITFindBy(id = HomePageData.SEARCH_FOR_PRODUCTS_SEARCH_IOS)
    @AndroidFindBy(id = HomePageData.SEARCH_FOR_PRODUCTS_SEARCH)
    protected WebElement searchForProductsSearch;

    @iOSXCUITFindBy(xpath = HomePageData.ACCEPT_TERMS_OF_USE_IOS)
    protected WebElement acceptTermsOfUse;

    @iOSXCUITFindBy(xpath = HomePageData.NOTIFICATIONS_REMIND_ME_LATER_IOS)
    protected WebElement notificationsRemindMeLater;
    
    @iOSXCUITFindBy(xpath = HomePageData.PROJECTS_BIDS_SECTION)
    @AndroidFindBy(xpath = HomePageData.PROJECTS_BIDS_SECTION_ANDRIOID)
    protected WebElement projectBidsSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.TOP_SUPPLIES_SECTION)
    @AndroidFindBy(xpath = HomePageData.TOP_SUPPLIES_SECTION_ANDRIOID)
    protected WebElement topSuppliesSection;
    

	@iOSXCUITFindBy(accessibility = HomePageData.PRO_ALERTS_SECTION)
	 @AndroidFindBy(xpath = HomePageData.PRO_ALERTS_SECTION_ANDRIOID)
    protected WebElement proAlerts_Section;
    
    @iOSXCUITFindBy(accessibility = HomePageData.SW_DOCOM_PRODUCT_SECTION)
    @AndroidFindBy(xpath = HomePageData.SW_DOCOM_PRODUCT_SECTION_ANDRIOID)
    protected WebElement swdotcomProductInfoSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.SW_DOTCOM_DATSHEETS_SECTION)
    @AndroidFindBy(xpath = HomePageData.SW_DOTCOM_DATSHEETS_SECTION_ANDRIOID)
    protected WebElement swdotcomDataSheetsSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.STATEMENTS_PAYMENTS_SECTION)
    @AndroidFindBy(xpath = HomePageData.STATEMENTS_PAYMENTS_SECTION_ANDRIOID)
    protected WebElement statementsAndPaymentsSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.COLOR_SNAP_APP_SECTION)
    @AndroidFindBy(xpath = HomePageData.COLOR_SNAP_APP_SECTION_ANDRIOID)
    protected WebElement colorSnapAppSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.PAINT_CALCULATOR_SECTION)
    @AndroidFindBy(xpath= HomePageData.PAINT_CALCULATOR_SECTION_ANDRIOID)
    protected WebElement paintCalculatorSection;
    
    @iOSXCUITFindBy(accessibility = HomePageData.PPC_MAGZINE_SECTION)
    @AndroidFindBy(xpath = HomePageData.PPC_MAGZINE_SECTION_ANDRIOID)
    protected WebElement ppcMagzineSection;

    @AndroidFindBy(xpath = HomePageData.FIRST_FREQUENTLY_PURCHASED_ITEM_ANDROID)
    protected WebElement firstFrequentlyPurchasedItem;


    @iOSXCUITFindBy(xpath = HomePageData.PRO_ORDERHISTORY_DETAIL_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_PURCHASED_EXPAND)
    protected WebElement orderHistoryDetails;

    @iOSXCUITFindBy(xpath = HomePageData.PRO_FREQUENTLY_CARROUSELS_ITEMS_IOS)
    @AndroidFindBy(id = HomePageData.PRO_FREQUENTLY_CARROUSELS_ITEMS)
    protected List<WebElement> frequentlyCarrouselsItems;

	/**
     * By Elements
     */
    protected By proOrderHistoryBy = By.xpath(HomePageData.PRO_ORDER_HISTORY);
    protected By removeButtonBy = By.xpath("//*[contains(@id,'" + HomePageData.PRO_REMOVE_BUTTON + "') or " + "contains(@id,'" + HomePageData.PRO_REMOVE_BUTTON_IOS + "') and @visible = 'true']");
    protected By continueShoppingBy = By.xpath("//*[translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']");
    protected By accountSummaryBy = By.id(HomePageData.PRO_ACCOUNT_SUMMARY);

    /**
     * @return the searchForProductsSearch
     */
    public WebElement getSearchForProductsSearch() {
        return searchForProductsSearch;
    }

    /**
     * @param searchForProductsSearch the searchForProductsSearch to set
     */
    public void setSearchForProductsSearch(WebElement searchForProductsSearch) {
        this.searchForProductsSearch = searchForProductsSearch;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(WebElement searchButton) {
        this.searchButton = searchButton;
    }

    public WebElement getProAppHomeButton() {
        return proAppHomeButton;
    }

    public void setProAppHomeButton(WebElement proAppHomeButton) {
        this.proAppHomeButton = proAppHomeButton;
    }

    public WebElement getFrequentlyPurchasedExpand() {
        return frequentlyPurchasedExpand;
    }

    public WebElement getFrequentlyPurchasedSection() {
        return frequentlyPurchasedSection;
    }

    public List<WebElement> getFrequentlyPurchasedItems() {
        return FrequentlyPurchasedItems;
    }

    public WebElement getCart() {
        return cart;
    }

    public List<WebElement> getRemoveButton() {
        return removeButton;
    }

    public WebElement getRemoveMessage() {
        return removeMessage;
    }

    public WebElement getFrequentlyPurchasedMore() {
        return frequentlyPurchasedMore;
    }

    public WebElement getAccountSummary() {
        return accountSummary;
    }

    public WebElement getAccountSummaryDetails() {
        return accountSummaryDetails;
    }

    public WebElement getStoreInfo() {
        return storeInfo;
    }

    public WebElement getOrderHistory() {
        return orderHistory;
    }

    public WebElement getRecentOnlineOrder() {
        return recentOnlineOrder;
    }

    public WebElement getHowAreWeDoing() {
        return howAreWeDoing;
    }

    public WebElement getProAdvantages() {
        return proAdvantages;
    }


    public WebElement getContinueShopping() {
        return continueShopping;
    }

    /**
     * By function
     */
    public By getProOrderHistoryBy() {
        return proOrderHistoryBy;
    }

    public By getRemoveButtonBy() {
        return removeButtonBy;
    }

    public By getContinueShoppingBy() {
        return continueShoppingBy;
    }

    public WebElement getCartEmpty() {
		return cartEmpty;
	}

	public void setCartEmpty(WebElement cartEmpty) {
		this.cartEmpty = cartEmpty;
	}
	
  public WebElement getProjectBidsSection() {
		return projectBidsSection;
	}

	public void setProjectBidsSection(WebElement projectBidsSection) {
		this.projectBidsSection = projectBidsSection;
	}
	


	public WebElement getProAlerts_Section() {
		return proAlerts_Section;
	}

	public void setProAlerts_Section(WebElement proAlerts_Section) {
		this.proAlerts_Section = proAlerts_Section;
	}

	public WebElement getSwdotcomProductInfoSection() {
		return swdotcomProductInfoSection;
	}

	public void setSwdotcomProductInfoSection(WebElement swdotcomProductInfoSection) {
		this.swdotcomProductInfoSection = swdotcomProductInfoSection;
	}

	public WebElement getSwdotcomDataSheetsSection() {
		return swdotcomDataSheetsSection;
	}

	public void setSwdotcomDataSheetsSection(WebElement swdotcomDataSheetsSection) {
		this.swdotcomDataSheetsSection = swdotcomDataSheetsSection;
	}

	public WebElement getStatementsAndPaymentsSection() {
		return statementsAndPaymentsSection;
	}

	public void setStatementsAndPaymentsSection(WebElement statementsAndPaymentsSection) {
		this.statementsAndPaymentsSection = statementsAndPaymentsSection;
	}

	public WebElement getColorSnapAppSection() {
		return colorSnapAppSection;
	}

	public void setColorSnapAppSection(WebElement colorSnapAppSection) {
		this.colorSnapAppSection = colorSnapAppSection;
	}

	public WebElement getPaintCalculatorSection() {
		return paintCalculatorSection;
	}

	public void setPaintCalculatorSection(WebElement paintCalculatorSection) {
		this.paintCalculatorSection = paintCalculatorSection;
	}

	public WebElement getPpcMagzineSection() {
		return ppcMagzineSection;
	}

	public void setPpcMagzineSection(WebElement ppcMagzineSection) {
		this.ppcMagzineSection = ppcMagzineSection;
	}


	public WebElement getTopSuppliesSection() {
		return topSuppliesSection;
	}

	public void setTopSuppliesSection(WebElement topSuppliesSection) {
		this.topSuppliesSection = topSuppliesSection;
	}

    public WebElement getOrderHistoryDetailsElement() {
        return this.orderHistoryDetails;
    }

    public int getNumberOfDisplayedCarrouselItems() {
        return this.frequentlyCarrouselsItems.size();
    }
}