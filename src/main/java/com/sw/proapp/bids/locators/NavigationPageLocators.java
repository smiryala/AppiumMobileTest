package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NavigationPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class NavigationPageLocators extends MobileBasePage {

    public NavigationPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NavigationPageData.FIRST_EDIT_TEXT_IOS)
    @AndroidFindBy(xpath = NavigationPageData.FIRST_EDIT_TEXT_ANDROID)
    protected WebElement firstEditText;

    @iOSXCUITFindBy(xpath = NavigationPageData.GO_BACK_ARROW_IOS)
    @AndroidFindBy(xpath = NavigationPageData.GO_BACK_ARROW_ANDROID)
    protected WebElement goBackArrow;

    @iOSXCUITFindBy(xpath = NavigationPageData.GO_BACK_CONFIRM_IOS)
    @AndroidFindBy(xpath = NavigationPageData.GO_BACK_CONFIRM_ANDROID)
    protected WebElement goBackConfirm;

    @iOSXCUITFindBy(xpath = NavigationPageData.GO_BACK_CANCEL_IOS)
    @AndroidFindBy(xpath = NavigationPageData.GO_BACK_CANCEL_ANDROID)
    protected WebElement goBackCancel;
}
