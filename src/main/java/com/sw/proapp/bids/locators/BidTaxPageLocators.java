package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidsTaxPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BidTaxPageLocators extends MobileBasePage {

    public BidTaxPageLocators(Common common) {
        super(common);
    }
    @AndroidFindBy(id = BidsTaxPageData.BID_TAX_FIELD_ID)
    protected WebElement bidTAxField;
}