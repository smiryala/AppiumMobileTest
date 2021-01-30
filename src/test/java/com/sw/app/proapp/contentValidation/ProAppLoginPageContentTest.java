package com.sw.app.proapp.contentValidation;

import com.applitools.eyes.Region;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.testsuites.MobileApplitoolsCoreBaseTest;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ProAppLoginPageContentTest extends MobileApplitoolsCoreBaseTest {
    AppDataFromDB appDataFromDB = null;

    HomePage homePage = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;

    @BeforeMethod(alwaysRun = true)
    public void before_ProAppLoginPageContentTest() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        homePage = new HomePage(getCommon());
        loginPage = new LoginPage(getCommon());
        accountPage = new AccountPage(getCommon());

        Assert.assertTrue(getEyeInstance().getIsOpen());
        getCommon().sleepFor(5);
        if (getCommon().getDriver().getPageSource().toLowerCase().contains("account summary")) {
            accountPage.signOutProApp();
        }
        loginPage.clickSignIn();
        accountPage.clickAccountButton();
        if (getDriver() instanceof AndroidDriver) {
            accountPage.clickAccountSettingsButton();
        }
        accountPage.verifyAndSetQADashboard();
    }

    @Test(groups = {"mobile_content_validation", "android"}, description = "Verify Login Page")
    public void verifyLoginPage() {
//        checkWindow("Validate Sign In Page");
//        loginPage.clickSignIn();
        checkWindow("Validate Login Page");
        loginPage.enterEmail("Test");
        loginPage.pressEnter();
        loginPage.hideKeyboard();
        checkWindow("Validate Errors");
        loginPage.enterEmail("Test@test.com");
        loginPage.enterLoginPassword("Test");
        loginPage.pressEnter();
        loginPage.hideKeyboard();
        checkWindow("Validate Sign In Button");
        verifyEyesTest();
    }

    @Test(groups = {"mobile_content_validation", "ios"}, description = "Verify Login Page")
    public void verifyLoginPageIOS() {
        getEyeInstance().setForceFullPageScreenshot(false);
        List<Region> ignoreList = new ArrayList<>();
        ignoreList.add(new Region(10, 450, 400, 330));
//        checkWindow("Validate Sign In Page", ignoreList);
//        loginPage.clickSignIn();
        checkCurrentWindow("Validate Login Page");
        loginPage.enterEmail("Test");
        loginPage.hideKeyboard();
        checkCurrentWindow("Validate Errors");
        loginPage.enterEmail("Test@test.com");
        loginPage.hideKeyboard();
        loginPage.enterLoginPassword("Test");
        loginPage.pressEnter();
        loginPage.hideKeyboard();
        checkCurrentWindow("Validate Sign In Button");
        verifyEyesTest();
    }
}
