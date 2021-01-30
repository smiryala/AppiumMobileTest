package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.AddLogoPageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AddLogoPage extends AddLogoPageLocators {

    public AddLogoPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isLoggedInAddLogoPageDispalyed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("business logo") || pageSource.contains("logo de la compañía");
    }
}