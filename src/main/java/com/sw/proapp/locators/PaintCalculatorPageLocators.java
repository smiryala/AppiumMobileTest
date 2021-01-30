package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.PaintCalculatorPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PaintCalculatorPageLocators extends MobileBasePage {

    public PaintCalculatorPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = PaintCalculatorPageData.PAGE_TITLE)
    protected WebElement pageTitle;
}