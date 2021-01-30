package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.AboutPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends AboutPageLocators {

    public AboutPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public AboutPage clickQADshboard() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getColorsnapDashBoard(), "Unable to click QA Dashboard");
        return this;
    }
    @Step()
    public AboutPage clickDebugPage() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getDebug(), "Unable to click Debug Link");
        return this;
    }
}