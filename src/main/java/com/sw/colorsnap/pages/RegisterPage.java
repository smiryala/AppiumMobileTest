package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.RegisterPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends RegisterPageLocators {

    public RegisterPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public boolean isHeaderDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getHeader(),"Header not displayed");
        if (!getCommon().isDisplayed(getHeader()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isContentDisplayed() {
        if (!getCommon().isDisplayed(getContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isBackButtonDisplayed() {
        if (!getCommon().isDisplayed(getBackButton()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isRegisterButtonisplayed() {
        if (!getCommon().isDisplayed(getRegisterButton()))
            return false;
        else
            return true;
    }

    @Step()
    public RegisterPage clickBackIcon() {
        getCommon().sleepFor(2);
        getCommon().click(getBackButton(), "Unable to click BackButton");
        return this;
    }
}