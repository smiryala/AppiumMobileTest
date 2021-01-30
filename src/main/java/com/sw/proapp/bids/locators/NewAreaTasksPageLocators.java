package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaTasksPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaTasksPageLocators extends MobileBasePage {

    public NewAreaTasksPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.SAVE_CTA_IOS)
    @AndroidFindBy(xpath = NewAreaTasksPageData.SAVE_CTA)
    protected WebElement saveCta;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.DISCARD_CTA_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.DISCARD_CTA)
    protected WebElement discardCta;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.WALLS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.WALLS_LABEL)
    protected WebElement wallsLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.CEILING_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.CEILING_LABEL)
    protected WebElement ceilingLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.INTERIOR_TRIM_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.INTERNAL_TRIM_LABEL)
    protected WebElement internalTrimLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.INTERIOR_DOORS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.INTERNAL_DOORS_LABEL)
    protected WebElement internalDoorsLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.INTERIOR_WINDOWS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.INTERNAL_WINDOWS_LABEL)
    protected WebElement internalWindowsLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.INTERIOR_OTHER_CTA_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.INTERNAL_OTHER_CTA)
    protected WebElement internalOtherCTA;
    
    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.INTERIOR_OTHER_INPUT_IOS)
    protected WebElement interiorOtherInput;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.BODY_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.BODY_LABEL)
    protected WebElement bodyLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.EXTERIOR_TRIM_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.EXTERNAL_TRIM_LABEL)
    protected WebElement externalTrimLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.SHUTTERS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.SHUTTERS_LABEL)
    protected WebElement shuttersLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.EXTERIOR_WINDOWS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.EXTERNAL_WINDOWS_LABEL)
    protected WebElement externalWindowsLabel;

    
    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.EXTERIOR_DOORS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.EXTERNAL_DOORS_LABEL)
    protected WebElement externalDoorsLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.GUTTERS_LABEL_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.GUTTERS_LABEL)
    protected WebElement guttersLabel;

    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.EXTERIOR_OTHER_CTA_IOS)
    @AndroidFindBy(id = NewAreaTasksPageData.EXTERNAL_OTHER_CTA)
    protected WebElement externalOtherCta;
    
    @iOSXCUITFindBy(xpath = NewAreaTasksPageData.EXTERIOR_OTHER_INPUT_IOS)
    protected WebElement exteriorOtherInput;
}