package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.ProjectInfoPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ProjectInfoPageLocators extends MobileBasePage {
    public ProjectInfoPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.FIRST_NAME_INPUT_IOS)
    protected WebElement firstNameInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.LAST_NAME_INPUT_IOS)
    protected WebElement lastNameInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.EMAIL_INPUT_IOS)
    protected WebElement emailInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.PHONE_INPUT_IOS)
    protected WebElement phoneInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.ADDRESS_1_INPUT_IOS)
    protected WebElement address1Input;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.ADDRESS_2_INPUT_IOS)
    protected WebElement address2Input;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.CITY_INPUT_IOS)
    protected WebElement cityInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.ZIP_INPUT_IOS)
    protected WebElement zipInput;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.STATE_DROPDOWN_IOS)
    protected WebElement stateDropdown;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.DROPDOWN_DONE_BUTTON_IOS)
    protected WebElement dropdownDoneButton;

    @iOSXCUITFindBy(xpath = ProjectInfoPageData.PROJECT_INFO_NEXT_BUTTON_IOS)
    protected WebElement nextButton;
}