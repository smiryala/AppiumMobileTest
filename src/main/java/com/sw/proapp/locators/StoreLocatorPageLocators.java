package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.StoreLocatorPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreLocatorPageLocators extends MobileBasePage {

    public StoreLocatorPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = StoreLocatorPageData.LOCATION_SET_AWUA)
    @AndroidFindBy(xpath = StoreLocatorPageData.LOCATION_SET_AWUA)
    protected WebElement allowWhileUsingApp;

    @iOSXCUITFindBy(accessibility = StoreLocatorPageData.LOCATION_SET_AO_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.LOCATION_SET_AO)
    protected WebElement allowOnce;

    @iOSXCUITFindBy(xpath = StoreLocatorPageData.LOCATION_SET_DA)
    @AndroidFindBy(xpath = StoreLocatorPageData.LOCATION_SET_DA)
    protected WebElement dontAllow;

    @iOSXCUITFindBy(xpath = StoreLocatorPageData.IN_PROGRESS_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.PLEASE_WAIT)
    protected WebElement inProgress;

    @iOSXCUITFindBy(iOSNsPredicate =  StoreLocatorPageData.NEAREST_STORE_IOS)
    @AndroidFindBy(id = StoreLocatorPageData.NEAREST_STORE)
    protected WebElement nearestStore;

    @iOSXCUITFindBy(xpath = StoreLocatorPageData.SEARCH_STORES_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.SEARCH_STORES)
    protected WebElement searchStore;

    @iOSXCUITFindBy(accessibility = StoreLocatorPageData.SWITCH_STORE_LIST_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.SWITCH_STORE_LIST)
    protected WebElement switchStoreList;

    @iOSXCUITFindBy(accessibility =  StoreLocatorPageData.SWITCH_STORE_MAP_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.SWITCH_STORE_MAP)
    protected WebElement switchStoreMap;

    @iOSXCUITFindBy(xpath = StoreLocatorPageData.STORE_LIST_IOS)
    @AndroidFindBy(xpath = StoreLocatorPageData.STORE_LIST)
    protected List<WebElement> storeList;

    /**
     * By Locators
     */
    protected By inProgressBy = By.xpath(StoreLocatorPageData.IN_PROGRESS_IOS);
}


