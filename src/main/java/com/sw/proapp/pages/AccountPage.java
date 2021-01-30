package com.sw.proapp.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.locators.AccountPageLocators;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends AccountPageLocators {

    public AccountPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public AccountPage doLogin() {
        return this;
    }

    @Step()
    public AccountPage clickAccountButton() {
//        ExtentTestManager.getTest().log(Status.INFO, "clickAccountButton");
        hideKeyboard();
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getAccountHomeButton(), "Unable to click AccountHomeButton.");
        return this;
    }

    @Step()
    public AccountPage clickAccountSettingsButton() {
//        ExtentTestManager.getTest().log(Status.INFO, "clickAccountSettingsButton");
        getCommon().sleepFor(3);
        getCommon().clickAndVerifyIsTrue(getAccountSettingsButton(), "Unable to click AccountSettingsButton.");
        return this;
    }

    @Step()
    public AccountPage verifyAndSetQADashboard() {
//        ExtentTestManager.getTest().log(Status.INFO, "verifyAndSetQADashboard");
        if (getDriver() instanceof IOSDriver) {
            //getCommon().sleepFor(3);
            clickElementByText("Debug", "english");
            clickElementByText("API Environment", "english");
            //getCommon().clickAndVerifyIsTrue(getQa_dashboard_button(), "Unable to click QA_Dashboard_Button.");
            //getCommon().clickAndVerifyIsTrue(getApi_environment(), "Unable to click API Environment.");
            //getCommon().clickAndVerifyIsTrue(getQa_select_ios(), "Unable to select QA Endpoint URL");
            clickElementByText("QA V9", "english");
            ((AppiumDriver) getCommon().getDriver()).launchApp();
            closeAndLaunchApp();
        } else {
            getCommon().sleepFor(3);
            getCommon().clickAndVerifyIsTrue(getQa_dashboard_button(), "Unable to click QA_Dashboard_Button.");
            getCommon().clickAndVerifyIsTrue(getService_endpoint_select(), "Unable to click Service EndPoint dropdown.");
            clickElementByText("QAv9_TEST_SERVICES", "english");
            getCommon().sleepFor(3);
            //getCommon().clickAndVerifyIsTrue(getBackButton(), "Unable to click Back Button.");
            clickBackButton();
            getCommon().sleepFor(3);
            clickBackButton();
            clickBackButton();
        }
        return this;
    }

    @Step
    public AccountPage signOutProApp() {
        clickAccountButton();
        clickAccountSettingsButton();
        if (existsElement(getSignOut()))
            getCommon().clickAndVerifyIsTrue(getSignOut(), "Unable to click SignOut button");
        getCommon().sleepFor(3);
        String signInLabel=getTextByLanguage("SIGN IN°INICIAR SESIÓN", languageBase);
        if (!getCommon().getDriver().getPageSource().toUpperCase().contains(signInLabel)) {
            if (getCommon().getDriver() instanceof IOSDriver)
                getCommon().clickAndVerifyIsTrue(close_settings_ios, "Unable to click Close button");
            else if (getCommon().getDriver() instanceof AndroidDriver)
                clickBackButton();
        }
        return this;
    }
}