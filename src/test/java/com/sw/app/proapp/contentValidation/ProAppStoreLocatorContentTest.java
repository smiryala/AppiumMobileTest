package com.sw.app.proapp.contentValidation;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.testsuites.MobileApplitoolsCoreBaseTest;
import com.sw.proapp.contentValidation.pages.ContentValidationUtil;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import com.sw.proapp.pages.StoreLocatorPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProAppStoreLocatorContentTest extends MobileApplitoolsCoreBaseTest {
    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;
    HomePage homePage = null;
    StoreLocatorPage storeLocatorPage = null;
    ContentValidationUtil contentValidationUtil = null;
    String ZIPCODE = "99501";

    // Temporary Space for Locators before merging fully
    private static final String STORE_LOCATOR_STORE_LIST_BUTTON_ANDROID = "//*[@resource-id='com.sherwin.probuyplus.debug:id/listButton']";
    private static final String STORE_LOCATOR_STORE_LIST_SCROLLABLE_ANDROID = "//*[@resource-id='com.sherwin.probuyplus.debug:id/storesListRecyclerView']";

    public ProAppStoreLocatorContentTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ProAppStoreLocatorTest() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        loginPage = new LoginPage(getCommon());
        homePage = new HomePage(getCommon());
        storeLocatorPage = new StoreLocatorPage((getCommon()));
        accountPage = new AccountPage(getCommon());
        contentValidationUtil = new ContentValidationUtil(getCommon());

        loginPage.clickSignIn();
        accountPage.clickAccountButton();
        if (getDriver() instanceof AndroidDriver) {
            accountPage.clickAccountSettingsButton();
        }
        accountPage.verifyAndSetQADashboard();

    }

    @Test(groups = {"mobile_content_validation", "android", "broken"}, description = "Verify the Store Locator Page in SW PRO APP Android")
    public void verifyStoreLocatorPageAndroid() throws Exception {
        homePage.clickStores();
        storeLocatorPage.locationPermission("allow once");
        storeLocatorPage.searchStore(ZIPCODE);
        storeLocatorPage.waitForLoading();
        storeLocatorPage.clickListOfStore();
        getCommon().sleepFor(2);
        storeLocatorPage.hideKeyboard();
        WebElement switchStore = getCommon().getDriver().findElement(new By.ByXPath(STORE_LOCATOR_STORE_LIST_BUTTON_ANDROID));
        getCommon().clickAndVerifyIsTrue(switchStore, "Unable to click store list");
        WebElement scrollable = getCommon().getDriver().findElement(new By.ByXPath(STORE_LOCATOR_STORE_LIST_SCROLLABLE_ANDROID));
        checkRecyclerView("Validate the store locator page with list", scrollable);
        storeLocatorPage.clickMapOfStore();
        storeLocatorPage.waitForLoading();
        checkWindow("Validate the store locator page");
        verifyEyesTest();
    }

    @Test(groups = {"mobile_content_validation", "ios"}, description = "Verify the Store Locator Page")
    public void verifyStoreLocatorPageIOS() throws Exception {
        homePage.clickStores();
        storeLocatorPage.locationPermission("Allow Once");
        storeLocatorPage.searchStore(ZIPCODE);
        storeLocatorPage.waitForLoading();
        getCommon().sleepFor(2);
        storeLocatorPage.hideKeyboard();
        storeLocatorPage.clickListOfStore();
        checkCurrentWindow("Verify Store List");
        storeLocatorPage.clickMapOfStore();
        checkCurrentWindow("Verify Store Map");
        verifyEyesTest();
    }

    @Test(groups = {"manual_scroll", "android"}, description = "Verify the Store Locator Page with Manual Scrolling")
    public void verifyStoreLocatorPageAndroidMS() throws Exception {
        getEyeInstance().setForceFullPageScreenshot(false);
        homePage.clickStores();
        storeLocatorPage.locationPermission("allow once");
        storeLocatorPage.searchStore(ZIPCODE);
        getCommon().sleepFor(5);
        storeLocatorPage.hideKeyboard();
        WebElement switchStoreList = getCommon().getDriver().findElement(new By.ByXPath(STORE_LOCATOR_STORE_LIST_BUTTON_ANDROID));
        getCommon().clickAndVerifyIsTrue(switchStoreList, "Unable to click store list");
        getCommon().sleepFor(5);
        MobileElement scrollable = contentValidationUtil.getStoreLocatorStoreListScrollable();
        validateRecyclerView(scrollable);
        getCommon().clickAndVerifyIsTrue(switchStoreList, "Unable to click store list");
        checkCurrentWindow("Verify Store Map");
        verifyEyesTest();
    }

    @AfterMethod(alwaysRun = true)
    public void after_ProAppStoreLocatorTest() {
        appDataFromDB.setResults(proAppTestCase);
    }
}