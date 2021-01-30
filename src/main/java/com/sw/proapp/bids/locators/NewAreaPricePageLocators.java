package com.sw.proapp.bids.locators;

import org.openqa.selenium.WebElement;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.NewAreaPricePageData;
import com.sw.proapp.pages.MobileBasePage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class NewAreaPricePageLocators extends MobileBasePage {

    public NewAreaPricePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.COST_UNIT_CTA_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.COST_UNIT_CTA)
    protected WebElement costUnitCta;
    
    @iOSXCUITFindBy(xpath = NewAreaPricePageData.COST_UNIT_PICKER_WHEEL_IOS)
    protected WebElement costUnitPickerWheel;
    
    @iOSXCUITFindBy(xpath = NewAreaPricePageData.COST_UNIT_DROPDOWN_DONE_BUTTON_IOS)
    protected WebElement costUnitDropdownDoneButton;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.HOURS_UNIT_IOS)
    @AndroidFindBy(xpath = NewAreaPricePageData.HOURS_UNIT)
    protected WebElement hoursUnit;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.SQUARE_FEET_UNIT_IOS)
    @AndroidFindBy(xpath = NewAreaPricePageData.SQUARE_FEET_UNIT)
    protected WebElement squareFeetUnit;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.LINEAR_FEET_UNIT_IOS)
    @AndroidFindBy(xpath = NewAreaPricePageData.LINEAR_FEET_UNIT)
    protected WebElement linearFeetUnit;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.EACH_UNIT_IOS)
    @AndroidFindBy(xpath = NewAreaPricePageData.EACH_UNIT)
    protected WebElement eachUnit;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.NUMBER_UNIT_INPUT_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.NUMBER_UNIT_INPUT)
    protected WebElement numberUnitInput;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.COST_INPUT_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.COST_INPUT)
    protected WebElement costInput;

    @iOSXCUITFindBy(accessibility = NewAreaPricePageData.SAVE_CTA_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.SAVE_CTA)
    protected WebElement saveCta;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.DISCARD_CTA_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.DISCARD_CTA)
    protected WebElement discardCta;

    @iOSXCUITFindBy(xpath = NewAreaPricePageData.TOTAL_PRICE_IOS)
    @AndroidFindBy(id = NewAreaPricePageData.TOTAL_PRICE)
    protected WebElement totalPrice;
    
    @iOSXCUITFindBy(xpath = NewAreaPricePageData.KEYBOARD_IOS)
    protected WebElement areaPriceKeyboard;
}

