package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.LoginPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends LoginPageLocators {

    public LoginPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step
    public LoginPage clickSignIn() {
        if (existsElement(getLogInButton())) {
            getCommon().clickAndVerifyIsTrue(getLogInButton(),
                        "Unable to click SignIn/Register.");
        }
         return this;
    }

    @Step("enterUserNamePwd username {0} and password {1}")
    public LoginPage enterUserNamePwd(String username, String password) {
        getCommon().sleepFor(2);
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim()
                .isEmpty()) {
            getCommon().typeAndVerifyIsTrue(getUserName(), username,
                    "Unable to send keys to the Logon field as expected.");
            getCommon().typeAndVerifyIsTrue(getPassWord(), password,
                    "Unable to send keys to the Logon field as expected.");
        }
        return this;
    }

    @Step()
    public LoginPage enterUserName(String username) {
        getCommon().sleepFor(2);
        getCommon().typeAndVerifyIsTrue(getUserName(), username,
                    "Unable to send keys to the Logon field as expected.");
        return this;
    }

    @Step()
    public LoginPage enterPassword(String password) {
        getCommon().sleepFor(2);
        getCommon().typeAndVerifyIsTrue(getPassWord(), password,
                "Unable to send keys to the Password field as expected.");
        return this;
    }

    @Step()
    public boolean isHeaderTitleDisplayed() {
        getCommon().sleepFor(2);
        if (!getCommon().isDisplayed(getHeaderTitle()))
            return false;
        else
            return true;
    }

    @Step()
    public void clickOnHeaderTitle() {
        getCommon().sleepFor(2);
        getCommon().click(getHeaderTitle(), "Not able to click on Header");
    }
    @Step()
    public boolean isBackButtonDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getBackButton(),"Backbutton not displayed");
        if (!getCommon().isDisplayed(getBackButton()))
            return false;
        else
            return true;
    }

    @Step()
    public LoginPage clickOnBackButton() {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getBackButton(), "Unable to click Back");
        return this;
    }

    @Step()
    public boolean isImageDisplayed() {
        if (!getCommon().isDisplayed(getImage()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isEmailTextDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getEmailText(),"EmailText not displayed");
        if (!getCommon().isDisplayed(getEmailText()))
            return false;
        else
            return true;
    }

    @Step()
    public void clickOnEmailText() {
        getCommon().click(getEmailText(),"Not able to click on email text");
    }

    @Step()
    public boolean isPasswordTextDisplayed() {
        if (!getCommon().isDisplayed(getPasswordText()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isRememberCheckboxDisplayed() {
        if (!getCommon().isDisplayed(getRememberCheckbox()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isForgotPasswordDisplayed() {
        if (!getCommon().isDisplayed(getForgotPassword()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isRegisterDisplayed() {
        if (!getCommon().isDisplayed(getRegisterButton()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isWhyRegisterDisplayed() {
        if (!getCommon().isDisplayed(getWhyRegisterButton()))
            return false;
        else
            return true;
    }

    @Step
    public LoginPage clickForgotPassword() {
        if (existsElement(getForgotPassword())) {
            getCommon().clickAndVerifyIsTrue(getForgotPassword(),
                    "Unable to click Forgot Password.");
        }
        return this;
    }

    @Step()
    public boolean isSendVerificationEmailDisplayed() {
        if (!getCommon().isDisplayed(getSendVerificationEmail()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isContactUsDisplayed() {
        if (!getCommon().isDisplayed(getContactUs()))
            return false;
        else
            return true;
    }
}