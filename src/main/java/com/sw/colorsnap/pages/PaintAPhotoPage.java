package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.PaintAPhotoPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class PaintAPhotoPage extends PaintAPhotoPageLocators {

    public PaintAPhotoPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public boolean isTakeaPhotoButtonDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotoTakePhoto()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isChooseaPhotoButtonDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotoChoosePhoto()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isContentDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotoContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isImageDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotImage()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isElipsisDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotoElipsis()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isBackButtonDisplayed() {
        if (!getCommon().isDisplayed(getPaintAPhotoBackButton()))
            return false;
        else
            return true;
    }

    @Step()
    public PaintAPhotoPage clickOnBackButton() {
        if (existsElement(getPaintAPhotoBackButton())) {
            getCommon().clickAndVerifyIsTrue(getPaintAPhotoBackButton(),
                    "Unable to click Back Button.");
        }
        return this;
    }

}
