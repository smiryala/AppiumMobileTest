package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.EnvSelectorPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class EnvSelectorPage extends EnvSelectorPageLocators {

    public EnvSelectorPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public EnvSelectorPage clickServiceEndPoint() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getColorsnapServiceEndPoint(), "Unable to click ServiceEndPOint");
        return this;
    }

    @Step()
    public EnvSelectorPage setQADashboard() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getColorsnapQATestEndPoint(), "Unable to click QAX TEST ENDPOINT ");
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getBackButton(), "Unable to click Back");
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getBackButton(), "Unable to click Back");
        return this;
    }

    @Step()
    public EnvSelectorPage clickiOSQAEndPoint() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getiOSQAXEndPoint(), "Unable to click iOSEnd Point for QAX");
        return this;
    }

}