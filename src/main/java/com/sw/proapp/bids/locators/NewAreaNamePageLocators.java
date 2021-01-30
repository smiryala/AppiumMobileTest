package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaNamePageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaNamePageLocators extends MobileBasePage {

    public NewAreaNamePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaNamePageData.NAME_INPUT_IOS)
    @AndroidFindBy(id = NewAreaNamePageData.NAME_INPUT)
    protected WebElement nameInput;

    @iOSXCUITFindBy(accessibility = NewAreaNamePageData.CONTINUE_CTA_IOS)
    @AndroidFindBy(id = NewAreaNamePageData.CONTINUE_CTA)
    protected WebElement continueCta;

    @AndroidFindBy(id = NewAreaNamePageData.DISCARD_CTA)
    protected WebElement discardCTA;
}