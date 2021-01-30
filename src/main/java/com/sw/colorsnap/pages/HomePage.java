package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.HomePageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends HomePageLocators {

    public HomePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public HomePage clickColorsnapAppButton() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getColorsnapAppHomeButton(), "Unable to click ColorsnapAppHomeButton.");
        return this;
    }

}