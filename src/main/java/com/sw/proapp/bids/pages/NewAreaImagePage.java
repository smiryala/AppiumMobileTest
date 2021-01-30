package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaImagePageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class NewAreaImagePage extends NewAreaImagePageLocators {

    public NewAreaImagePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isNewAreaImagePageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("area image") || pageSource.contains("IMAGEN  DEL ÁREA".toLowerCase());
    }

    public NewAreaImagePage clickSaveCta() {
        getCommon().clickAndVerifyIsTrue(saveCta, "Unable to click the save button");
        return this;
    }
}