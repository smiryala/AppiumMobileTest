package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaImagePageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaImagePageLocators extends MobileBasePage {

    public NewAreaImagePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaImagePageData.SAVE_CTA_IOS)
    @AndroidFindBy(id = NewAreaImagePageData.SAVE_CTA)
    protected WebElement saveCta;
}