package com.sw.app.colorsnap;

import com.sw.colorsnap.pages.*;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.helpers.Common;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ColorsnapAppLoginPageTest {

    private LoginPage loginPage = null;
    private HomePage homePage = null;
    private ResourcesPage resourcesPage = null;
    private RegisterPage registerPage = null;
    private MenuNavigationPage menuNavigationPage = null;
    AppDataFromDB appDataFromDB = null;

    public ColorsnapAppLoginPageTest(Common common) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        loginPage = new LoginPage(common);
        homePage = new HomePage(common);
        resourcesPage = new ResourcesPage(common);
        registerPage = new RegisterPage(common);
        menuNavigationPage = new MenuNavigationPage(common);
    }

    @Step()
    public void verifyLoginPageIOS(Login login) {
        menuNavigationPage.clickOnResources();
        resourcesPage.clickLogintoSW();

        Assert.assertTrue(loginPage.isEmailTextDisplayed(), "Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isPasswordTextDisplayed(), "Password Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isRememberCheckboxDisplayed(), "Remember checkbox is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isForgotPasswordDisplayed(), "Forgot password is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isRegisterDisplayed(), "Register button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isWhyRegisterDisplayed(), "Why Register button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isHeaderTitleDisplayed(), "Header Title is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isBackButtonDisplayed(), "Back button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isImageDisplayed(), "Image is Not Displayed on Login Page");

        loginPage.clickForgotPassword();
        Assert.assertTrue(loginPage.isBackButtonDisplayed(), "Back button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isEmailTextDisplayed(), "Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isSendVerificationEmailDisplayed(), "Send Verification Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isContactUsDisplayed(), "ContactUs is Not Displayed on Login Page");

        loginPage.clickOnBackButton();
        resourcesPage.clickWhyRegister();

        Assert.assertTrue(registerPage.isHeaderDisplayed(), "Header is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isBackButtonDisplayed(), "Back Button is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isContentDisplayed(), "Content is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isRegisterButtonisplayed(), "Register Button is Not Displayed on Register Page");

        registerPage.clickBackIcon();

        loginPage.enterUserName(login.getUserName());
        loginPage.enterPassword(login.getPassword());

        loginPage.clickSignIn();

        Assert.assertTrue(resourcesPage.isLogintoSWisNotDispalyed(), "Login Displayed on Resource Page");
        Assert.assertTrue(resourcesPage.isLogOutDispalyed(), "LogOut Not Displayed on Resource Page");
        resourcesPage.clickLogOut();
    }

    @Test(groups = { "colorsnap_smoke", "android" }, description = "Verify login page.")
    public void verifyLoginPageAndroid(Login login) {

        menuNavigationPage.clickOnResources();
        resourcesPage.clickLogintoSW();

        Assert.assertTrue(loginPage.isEmailTextDisplayed(), "Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isPasswordTextDisplayed(), "Password Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isRememberCheckboxDisplayed(), "Remember checkbox is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isForgotPasswordDisplayed(), "Forgot password is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isRegisterDisplayed(), "Register button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isWhyRegisterDisplayed(), "Why Register button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isHeaderTitleDisplayed(), "Header Title is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isBackButtonDisplayed(), "Back button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isImageDisplayed(), "Image is Not Displayed on Login Page");

        loginPage.clickForgotPassword();
        Assert.assertTrue(loginPage.isBackButtonDisplayed(), "Back button is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isEmailTextDisplayed(), "Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isSendVerificationEmailDisplayed(), "Send Verification Email Text is Not Displayed on Login Page");
        Assert.assertTrue(loginPage.isContactUsDisplayed(), "ContactUs is Not Displayed on Login Page");

        loginPage.clickOnBackButton();
        resourcesPage.clickWhyRegister();

        Assert.assertTrue(registerPage.isHeaderDisplayed(), "Header is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isBackButtonDisplayed(), "Back Button is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isContentDisplayed(), "Content is Not Displayed on Register Page");
        Assert.assertTrue(registerPage.isRegisterButtonisplayed(), "Register Button is Not Displayed on Register Page");

        registerPage.clickBackIcon();

        loginPage.enterUserName(login.getUserName());
        loginPage.enterPassword(login.getPassword());

        loginPage.clickSignIn();

        Assert.assertTrue(resourcesPage.isLogintoSWisNotDispalyed(), "Login Displayed on Resource Page");
        Assert.assertTrue(resourcesPage.isLogOutDispalyed(), "LogOut Not Displayed on Resource Page");
        resourcesPage.clickLogOut();
    }
}