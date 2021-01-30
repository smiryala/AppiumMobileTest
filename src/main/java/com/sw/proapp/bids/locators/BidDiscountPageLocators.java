package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidsDiscountPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BidDiscountPageLocators extends MobileBasePage {

    public BidDiscountPageLocators(Common common) {
        super(common);
    }
    @AndroidFindBy(id = BidsDiscountPageData.BID_DISCOUNT_FIELD_ID)
    protected WebElement bidDiscountField;

    @AndroidFindBy(id = BidsDiscountPageData.BID_DISCOUNT_DISCOUNTUNIT_ID)
    protected WebElement bidDiscountUnit;

}