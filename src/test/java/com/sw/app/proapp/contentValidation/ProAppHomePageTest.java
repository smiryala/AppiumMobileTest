package com.sw.app.proapp.contentValidation;

import com.applitools.eyes.Region;
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
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProAppHomePageTest extends MobileApplitoolsCoreBaseTest {

    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;

    HomePage homePage = null;
    StoreLocatorPage storeLocatorPage = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;
    RegistrationPage registrationPage = null;
    CartPage cartPage = null;
    OrderPage orderPage = null;
    PDPage pDPage = null;
    ContentValidationUtil contentValidationUtil = null;

    public ProAppHomePageTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ProAppHomePageTest() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        accountPage = new AccountPage(getCommon());
        //cartPage = new CartPage(getCommon());
        homePage = new HomePage(getCommon());
        loginPage = new LoginPage(getCommon());
        orderPage = new OrderPage(getCommon());
        pDPage = new PDPage(getCommon());
        registrationPage = new RegistrationPage(getCommon());
        storeLocatorPage = new StoreLocatorPage(getCommon());
        contentValidationUtil = new ContentValidationUtil(getCommon());

        //mobilePageUtils.mobileLogin(loginPage);

        Assert.assertTrue(getEyeInstance().getIsOpen());
    }

    @Test(groups = {"mobile_content_validation", "android"}, description = "Verify Content of ProAppHomePage")
    public void verifyPageContent() {
        MobileElement scrollableContent = contentValidationUtil.getFirstRecyclerView();
        List<Region> ignoreList = new ArrayList<>();
        ignoreList.add(new Region(getElementRegion(homePage.getOrderHistory())));
        if (getCommon().getDriver() instanceof AndroidDriver) {
            checkRecyclerView("Verify Home Page", scrollableContent, ignoreList);
        } else {
            // TODO: IOS flow
        }
        verifyEyesTest();
    }

}
