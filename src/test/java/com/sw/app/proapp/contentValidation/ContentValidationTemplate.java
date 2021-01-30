package com.sw.app.proapp.contentValidation;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.testsuites.MobileApplitoolsCoreBaseTest;
import com.sw.proapp.contentValidation.pages.ContentValidationUtil;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.CartPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import com.sw.proapp.pages.OrderPage;
import com.sw.proapp.pages.PDPage;
import com.sw.proapp.pages.RegistrationPage;
import com.sw.proapp.pages.StoreLocatorPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContentValidationTemplate extends MobileApplitoolsCoreBaseTest {

    //TODO: Replace all occurrences of ContentValidationTemplate with test name

    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;
    ContentValidationUtil contentValidationUtil = null;

    /**
     * Page variables
     * Main Pages provided below
     */
    HomePage homePage = null;
    StoreLocatorPage storeLocatorPage = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;
    RegistrationPage registrationPage = null;
    CartPage cartPage = null;
    OrderPage orderPage = null;
    PDPage pDPage = null;

    public ContentValidationTemplate() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ContentValidationTemplate() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        contentValidationUtil = new ContentValidationUtil(getCommon());
        homePage = new HomePage(getCommon());
        storeLocatorPage = new StoreLocatorPage(getCommon());
        loginPage = new LoginPage(getCommon());
        accountPage = new AccountPage(getCommon());
        registrationPage = new RegistrationPage(getCommon());
        Assert.assertTrue(getEyeInstance().getIsOpen());
    }

    @Test(groups = {"mobile_content_validation", "android", "ios"}, description = "Verify Content of [ProAppPage]")
    public void verifyPageContent() {
        if (getCommon().getDriver() instanceof AndroidDriver) {
            checkWindow("Validate [ProAppPage]");
        } else {
            checkWindow("Validate [ProAppPage]");
        }
        verifyEyesTest();
    }
}
