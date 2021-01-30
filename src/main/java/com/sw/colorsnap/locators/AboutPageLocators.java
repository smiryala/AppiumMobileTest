package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.AboutPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class AboutPageLocators extends MobileBasePage {

    public AboutPageLocators(Common common) {
        super(common);
    }


    @AndroidFindBy(xpath = AboutPageData.QA_DASHBOARD)
    protected WebElement colorsnapDashBoard;

    @iOSXCUITFindBy(accessibility = AboutPageData.ABOUT_DEBUG)
    protected WebElement debug;

    public WebElement getColorsnapDashBoard() {
        return colorsnapDashBoard;
    }

    public WebElement getDebug() {
        return debug;
    }
}