package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaNotesPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaNotesPageLocators extends MobileBasePage {

    public NewAreaNotesPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaNotesPageData.NOTES_INPUT_IOS)
    @AndroidFindBy(id = NewAreaNotesPageData.NOTES_INPUT)
    protected WebElement notesInputField;

    @iOSXCUITFindBy(xpath = NewAreaNotesPageData.CONTINUE_CTA_IOS)
    @AndroidFindBy(id = NewAreaNotesPageData.CONTINUE_CTA)
    protected WebElement continueCta;

    @iOSXCUITFindBy(xpath = NewAreaNotesPageData.DISCARD_CTA_IOS)
    @AndroidFindBy(id = NewAreaNotesPageData.DISCARD_CTA)
    protected WebElement discardCta;
}