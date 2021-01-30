package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidNamePageData;
import com.sw.proapp.bids.data.BidsPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class BidNamePageLocators extends MobileBasePage {

    public BidNamePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = BidNamePageData.BID_NAME_INPUT_IOS)
    @AndroidFindBy(id = BidNamePageData.BID_NAME_INPUT)
    protected WebElement bidNameInput;

    @iOSXCUITFindBy(xpath = BidNamePageData.SAVE_BUTTON_IOS)
    @AndroidFindBy(id = BidNamePageData.SAVE_BUTTON)
    protected WebElement saveButton;

    @iOSXCUITFindBy(xpath = BidNamePageData.DISCARD_BUTTON_IOS)
    @AndroidFindBy(id = BidNamePageData.DISCARD_BUTTON)
    protected WebElement discardButton;
}