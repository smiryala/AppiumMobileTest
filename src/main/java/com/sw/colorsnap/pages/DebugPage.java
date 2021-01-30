package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.DebugPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class DebugPage extends DebugPageLocators {

    public DebugPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public DebugPage clickSetupServers() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getSetupServer(), "Unable to click Server Setup Page");
        return this;
    }

}