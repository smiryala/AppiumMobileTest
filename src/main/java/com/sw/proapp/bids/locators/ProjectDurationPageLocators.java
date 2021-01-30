package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.ProjectDurationPageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProjectDurationPageLocators extends MobileBasePage {

    public ProjectDurationPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(id = ProjectDurationPageData.NUMBER_INPUT)
    @iOSXCUITFindBy(xpath = ProjectDurationPageData.NUMBER_INPUT_IOS)
    protected WebElement projectDurationNumberInput;

    @AndroidFindBy(id = ProjectDurationPageData.AMOUNT_DROPDOWN)
    @iOSXCUITFindBy(xpath = ProjectDurationPageData.AMOUNT_DROPDOWN_IOS)
    protected WebElement projectDurationAmountDropdown;

    @iOSXCUITFindBy(xpath = ProjectDurationPageData.DROPDOWN_DONE_BUTTON_IOS)
    protected WebElement projectDurationDropdownDoneButton;

    @AndroidFindBy(id = ProjectDurationPageData.SAVE_BUTTON)
    @iOSXCUITFindBy(xpath = ProjectDurationPageData.SAVE_BUTTON_IOS)
    protected WebElement projectDurationSaveButton;

    @iOSXCUITFindBy(xpath = ProjectDurationPageData.DISCARD_BUTTON_IOS)
    protected WebElement projectDurationDiscardButton;
    
    @AndroidFindBy(xpath = ProjectDurationPageData.DROPDOWN_HOURS_CTA)
    protected WebElement projectDurationDropdownHoursButton;
}