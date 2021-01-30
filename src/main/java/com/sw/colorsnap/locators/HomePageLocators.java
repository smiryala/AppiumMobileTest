package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.HomePageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePageLocators extends MobileBasePage {

    public HomePageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(id = HomePageData.COLORSNAP_HOME_BUTTON)
    protected WebElement colorsnapAppHomeButton;
    public WebElement getColorsnapAppHomeButton() {
        return colorsnapAppHomeButton;
    }
}