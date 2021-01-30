package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidExpirationPageData;
import com.sw.proapp.bids.data.BidsPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class BidExpirationPageLocators extends MobileBasePage {

    public BidExpirationPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(id = BidExpirationPageData.BID_EXPIRATION_DROPDOWN_EMPTY)
    @iOSXCUITFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_DROPDOWN_EMPTY_IOS)
    protected WebElement bidExpirationDropdownEmpty;

    @iOSXCUITFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_DROPDOWN_POPULATED_IOS)
    protected WebElement bidExpirationDropdownPopulated;

    @iOSXCUITFindBy(xpath = BidExpirationPageData.DROPDOWN_DONE_BUTTON_IOS)
    protected WebElement bidExpirationDropdownButtonDone;

    @AndroidFindBy(id = BidExpirationPageData.SAVE_BUTTON)
    @iOSXCUITFindBy(xpath = BidExpirationPageData.SAVE_BUTTON_IOS)
    protected WebElement bidExpirationSaveButton;

    @AndroidFindBy(id = BidExpirationPageData.DISCARD_BUTTON)
    @iOSXCUITFindBy(xpath = BidExpirationPageData.DISCARD_BUTTON_IOS)
    protected WebElement bidExpirationDiscardButton;

    @AndroidFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_7_DAYS)
    protected WebElement bidExpiration7Days;

    @AndroidFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_15_DAYS)
    protected WebElement bidExpiration15Days;

    @AndroidFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_30_DAYS)
    protected WebElement bidExpiration30Days;

    @AndroidFindBy(xpath = BidExpirationPageData.BID_EXPIRATION_45_DAYS)
    protected WebElement bidExpiration45Days;

    @AndroidFindBy(xpath = BidExpirationPageData.BID_NO_EXPIRATION)
    protected WebElement bidNoExpiration;
}