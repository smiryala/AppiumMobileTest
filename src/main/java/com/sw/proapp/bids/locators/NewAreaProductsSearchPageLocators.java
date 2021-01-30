package com.sw.proapp.bids.locators;

import com.sw.proapp.bids.pages.NewAreaProductsSearchPage;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidSummaryPageData;
import com.sw.proapp.bids.data.NewAreaProductsSearchPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaProductsSearchPageLocators extends MobileBasePage {

    public NewAreaProductsSearchPageLocators(Common common) {
        super(common);
    }
    
    @AndroidFindBy(xpath = BidSummaryPageData.CONFIRM_DELETE_CTA)
    protected WebElement confirmDeleteButton;
    
    @AndroidFindBy(xpath = BidSummaryPageData.DELETE_MENU_CTA)
    protected WebElement deleteMenuCta;
    
    @AndroidFindBy(xpath = BidSummaryPageData.AREA_MENU)
    protected List<WebElement> areaMenus;
    
    @iOSXCUITFindBy(xpath = NewAreaProductsSearchPageData.SAVE_AND_GO_IOS)
	@AndroidFindBy(xpath = NewAreaProductsSearchPageData.SAVE_AND_GO_ANDROID)
	protected WebElement saveAndGo;
    
    @iOSXCUITFindBy(id = NewAreaProductsSearchPageData.ITEM_DELETE_BUTTON_IOS)
	@AndroidFindBy(id = NewAreaProductsSearchPageData.ITEM_DELETE_BUTTON_ANDROID)
	protected List<WebElement> itemDeleteButtons;
    
    @iOSXCUITFindBy(id = NewAreaProductsSearchPageData.DELETE_CONFIRM_YES_IOS)
	@AndroidFindBy(id = NewAreaProductsSearchPageData.DELETE_CONFIRM_YES_ANDROID)
	protected WebElement deleteConfirmYes;

    @iOSXCUITFindBy(xpath = NewAreaProductsSearchPageData.CONTINUE_CTA_IOS)
    @AndroidFindBy(id = NewAreaProductsSearchPageData.CONTINUE_CTA)
    protected WebElement continueCta;

    @iOSXCUITFindBy(xpath = NewAreaProductsSearchPageData.SEARCH_PRODUCTS_CTA_IOS)
    protected WebElement searchProductsCta;

    @iOSXCUITFindBy(xpath = NewAreaProductsSearchPageData.SEARCH_PRODUCTS_SEARCH_FIELD)
    protected WebElement searchProductsSearchField;

    @iOSXCUITFindBy(xpath = NewAreaProductsSearchPageData.PRODUCT_SEARCH_FIRST_ITEM_ADD_CTA_IOS)
    protected WebElement productSearchFirstItemAddCta;
}