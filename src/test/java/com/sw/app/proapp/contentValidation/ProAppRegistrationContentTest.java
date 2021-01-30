package com.sw.app.proapp.contentValidation;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.testsuites.MobileApplitoolsCoreBaseTest;
import com.sw.proapp.contentValidation.pages.ContentValidationUtil;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import com.sw.proapp.pages.RegistrationPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProAppRegistrationContentTest extends MobileApplitoolsCoreBaseTest {

    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;

    /**
     * Page variables
     * Main Pages provided below
     */
    ContentValidationUtil contentValidationUtil = null;

    HomePage homePage = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;
    RegistrationPage registrationPage = null;

    //Temporary Locators Until Full Merge
    String REGISTRATION_BUTTON_ANDROID = "//*[@resource-id='com.sherwin.probuyplus.debug:id/registrationInfoCTA']";
    String REGISTRATION_BUTTON_IOS = "//XCUIElementTypeButton[@name='SIGN UP']";

    public ProAppRegistrationContentTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ProAppRegistrationContentTest() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        contentValidationUtil = new ContentValidationUtil(getCommon());
        homePage = new HomePage(getCommon());
        loginPage = new LoginPage(getCommon());
        accountPage = new AccountPage(getCommon());
        registrationPage = new RegistrationPage(getCommon());
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

    @Test(groups = {"mobile_content_validation", "android"}, description = "Verify Content of Registration Page")
    public void verifyRegistrationPageContent() {
        WebElement registrationButton = getCommon().getDriver().findElement(new By.ByXPath(REGISTRATION_BUTTON_ANDROID));
        getCommon().click(registrationButton, "Unable to click registration button");
        checkWindow("Validate Registration Page");
        verifyEyesTest();
    }

    @Test(groups = {"mobile_content_validation", "ios"}, description = "Verify Content of Registration Page")
    public void verifyRegistrationPageContentIOS() {
        WebElement registrationButton = getCommon().getDriver().findElement(new By.ByXPath(REGISTRATION_BUTTON_IOS));
        getCommon().click(registrationButton, "Unable to click registration button");
        checkCurrentWindow("Validate Registration Page");
        verifyEyesTest();
    }
}
