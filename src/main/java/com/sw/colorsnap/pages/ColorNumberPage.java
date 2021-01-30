package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.ColorNumberPageLocators;
import com.sw.colorsnap.locators.PaintPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class ColorNumberPage extends ColorNumberPageLocators {

    public ColorNumberPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    @Step()
    public boolean isColorNumberContentDisplayed() {
        if (!getCommon().isDisplayed(getPaintColorNumContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isScanButtonDisplayed() {
        if (!getCommon().isDisplayed(getPaintColorNumScanButton()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isColurNumberImageDisplayed() {
        if (!getCommon().isDisplayed(getPaintColorNumImage()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isColorNumberTitleDisplayed() {
        if (!getCommon().isDisplayed(getPaintColorNumTitle()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isBackDisplayed() {
        if (!getCommon().isDisplayed(getPaintColorNumBackButton()))
            return false;
        else
            return true;
    }

    @Step
    public ColorNumberPage clickOnBackButton() {
        if (existsElement(getPaintColorNumBackButton())) {
            getCommon().clickAndVerifyIsTrue(getPaintColorNumBackButton(),
                    "Unable to click Back Button.");
        }
        return this;
    }
}