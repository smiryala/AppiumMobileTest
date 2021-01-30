package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.FindAStorePageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindAStorePageLocators extends MobileBasePage {

    public FindAStorePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = FindAStorePageData.LOCATION_SET_AWUA)
    @AndroidFindBy(xpath = FindAStorePageData.LOCATION_SET_AWUA)
    protected WebElement allowWhileUsingApp;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.LOCATION_SET_AO_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.LOCATION_SET_AO)
    protected WebElement allowOnce;

    @iOSXCUITFindBy(xpath = FindAStorePageData.LOCATION_SET_DA)
    @AndroidFindBy(xpath = FindAStorePageData.LOCATION_SET_DA)
    protected WebElement dontAllow;

    @iOSXCUITFindBy(xpath = FindAStorePageData.IN_PROGRESS_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.PLEASE_WAIT)
    protected WebElement inProgress;

    @iOSXCUITFindBy(iOSNsPredicate = FindAStorePageData.NEAREST_STORE_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.NEAREST_STORE)
    protected WebElement nearestStore;

    @iOSXCUITFindBy(xpath = FindAStorePageData.SEARCH_STORES_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.SEARCH_STORES)
    protected WebElement searchStore;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.SWITCH_STORE_LIST_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.SWITCH_STORE_LIST)
    protected WebElement switchStoreList;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.SWITCH_STORE_MAP_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.SWITCH_STORE_MAP)
    protected WebElement switchStoreMap;

    @iOSXCUITFindBy(xpath = FindAStorePageData.STORE_LIST_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_LIST)
    protected List<WebElement> storeList;

    @iOSXCUITFindBy(xpath = FindAStorePageData.STORE_FILTER_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_FILTER)
    protected WebElement storeFilter;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_COMMERCIAL_FILTER_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_COMMERCIAL_FILTER)
    protected WebElement storeCommFilter;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.STORE_COMMERCIAL_FILTER_TEXT_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_COMMERCIAL_FILTER)
    protected WebElement storeCommFilterText;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_COMMERCIAL_CHECKED_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_COMMERCIAL_CHECKED)
    protected WebElement storeCommChecked;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_PAINT_CHECKED_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_PAINT_CHECKED)
    protected WebElement storePaintChecked;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.STORE_PAINT_FILTER_TEXT_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_PAINT_FILTER)
    protected WebElement storePaintFilterText;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_PAINT_FILTER_IOS)
    @AndroidFindBy(id = FindAStorePageData.STORE_PAINT_FILTER)
    protected WebElement storePaintFilter;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_CALL_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_CALL)
    protected WebElement call;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_DIRECTIONS_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_DIRECTIONS)
    protected WebElement directions;

    @iOSXCUITFindBy(iOSClassChain = FindAStorePageData.STORE_HOURS_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_HOURS)
    protected WebElement storeHours;

    @iOSXCUITFindBy(xpath = FindAStorePageData.STORE_BACK_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_BACK)
    protected WebElement back;

    @iOSXCUITFindBy(xpath = FindAStorePageData.STORE_TITLE_IOS)
    @AndroidFindBy(xpath = FindAStorePageData.STORE_TITLE)
    protected WebElement findAStoreTitle;

    @iOSXCUITFindBy(accessibility = FindAStorePageData.STORE_APPLY)
    protected WebElement apply;

    /**
     * By Locators
     */
    protected By inProgressBy = By.xpath(FindAStorePageData.IN_PROGRESS_IOS);

    public WebElement getStoreCommChecked() {
        return storeCommChecked;
    }

    public WebElement getStorePaintChecked() {
        return storePaintChecked;
    }

    public WebElement getAllowWhileUsingApp() {
        return allowWhileUsingApp;
    }

    public WebElement getAllowOnce() {
        return allowOnce;
    }

    public WebElement getDontAllow() {
        return dontAllow;
    }

    public WebElement getInProgress() {
        return inProgress;
    }

    public WebElement getNearestStore() {
        return nearestStore;
    }

    public WebElement getSearchStore() {
        return searchStore;
    }

    public WebElement getSwitchStoreList() {
        return switchStoreList;
    }

    public WebElement getSwitchStoreMap() {
        return switchStoreMap;
    }

    public List<WebElement> getStoreList() {
        return storeList;
    }

    public WebElement getStoreFilter() {
        return storeFilter;
    }

    public WebElement getStoreCommFilter() {
        return storeCommFilter;
    }

    public WebElement getStoreCommFilterText() {
        return storeCommFilterText;
    }

    public WebElement getStorePaintFilterText() {
        return storePaintFilterText;
    }

    public WebElement getStorePaintFilter() {
        return storePaintFilter;
    }

    public WebElement getCall() {
        return call;
    }

    public WebElement getDirections() {
        return directions;
    }

    public WebElement getStoreHours() {
        return storeHours;
    }

    public WebElement getBack() {
        return back;
    }

    public WebElement getFindAStoreTitle() {
        return findAStoreTitle;
    }

    public WebElement getApply() {
        return apply;
    }
}


