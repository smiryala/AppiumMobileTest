package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.InstantPaintPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class InstantPaintPage extends InstantPaintPageLocators {

    public InstantPaintPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step
    public InstantPaintPage clickOnStartPainting() {
        if (existsElement(getStartPaintButton())) {
            getCommon().clickAndVerifyIsTrue(getStartPaintButton(),
                    "Unable to click Start Painting.");
        }
        return this;
    }

    @Step
    public InstantPaintPage clickOnAllow() {
        if (existsElement(getAllowButton())) {
            getCommon().clickAndVerifyIsTrue(getAllowButton(),
                    "Unable to click Allow");
        }
        return this;
    }

    @Step()
    public boolean isExpertsColorsTabDisplayed() {
        if (!getCommon().isDisplayed(getExpertColorsTab()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isFindColorsTabDisplayed() {
        if (!getCommon().isDisplayed(getFindColorsTab()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isMyIdeasTabDisplayed() {
        if (!getCommon().isDisplayed(getMyIdeasTab()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isElipsisDisplayed() {
        if (!getCommon().isDisplayed(getElipsis()))
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

    @Step
    public InstantPaintPage clickOnBackButton() {
        if (existsElement(getBackButton())) {
            getCommon().clickAndVerifyIsTrue(getBackButton(),
                    "Unable to click Back Button.");
        }
        return this;
    }
}