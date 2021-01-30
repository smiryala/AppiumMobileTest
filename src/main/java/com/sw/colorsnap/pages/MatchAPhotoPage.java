package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.ColorNumberPageLocators;
import com.sw.colorsnap.locators.MatchAPhotoPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class MatchAPhotoPage extends MatchAPhotoPageLocators {

    public MatchAPhotoPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    @Step()
    public boolean isTakeaPhotoButtonDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotoTakePhoto()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isChooseaPhotoButtonDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotoChoosePhoto()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isContentDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotoContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isImageDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotImage()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isElipsisDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotoElipsis()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isBackButtonDisplayed() {
        if (!getCommon().isDisplayed(getMatchAPhotoBackButton()))
            return false;
        else
            return true;
    }

    @Step
    public MatchAPhotoPage clickOnBackButton() {
        if (existsElement(getMatchAPhotoBackButton())) {
            getCommon().clickAndVerifyIsTrue(getMatchAPhotoBackButton(),
                    "Unable to click Back Button.");
        }
        return this;
    }
}