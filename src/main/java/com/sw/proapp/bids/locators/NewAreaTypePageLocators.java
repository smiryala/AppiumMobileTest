package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaTypePageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaTypePageLocators extends MobileBasePage {

    public NewAreaTypePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaTypePageData.INTERIOR_CTA_IOS)
    @AndroidFindBy(id = NewAreaTypePageData.INTERIOR_CTA)
    protected WebElement interiorCta;

    @iOSXCUITFindBy(xpath = NewAreaTypePageData.EXTERIOR_CTA_IOS)
    @AndroidFindBy(id = NewAreaTypePageData.EXTERIOR_CTA)
    protected WebElement exteriorCta;
}