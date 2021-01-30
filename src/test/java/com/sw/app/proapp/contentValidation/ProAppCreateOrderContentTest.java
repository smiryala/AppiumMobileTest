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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProAppCreateOrderContentTest extends MobileApplitoolsCoreBaseTest {

    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;
    ContentValidationUtil contentValidationUtil = null;

    /**
     * Page variables
     * Main Pages provided below
     */
    HomePage homePage = null;
    LoginPage loginPage = null;
    AccountPage accountPage = null;
    CartPage cartPage = null;
    OrderPage orderPage = null;
    PDPage pDPage = null;

    public ProAppCreateOrderContentTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ProAppCreateOrderContentTest() {
        getCommon().setInsertResults(insertResults);
//        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
//        Login login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
        contentValidationUtil = new ContentValidationUtil(getCommon());

        homePage = new HomePage(getCommon());
        loginPage = new LoginPage(getCommon());
        accountPage = new AccountPage(getCommon());
        //cartPage = new CartPage(getCommon(),);
        orderPage = new OrderPage(getCommon());
        pDPage = new PDPage(getCommon());
        Assert.assertTrue(getEyeInstance().getIsOpen());

    }

    @Test(groups = {"mobile_content_validation"}, description = "Verify Content of Create Order Page")
    public void verifyPageContent() {
        homePage.clickFirstProduct();
        checkWindow("Validate Product Detail Page");
        pDPage.clickAddToCart();
        pDPage.clickGoToCart();
        checkWindow("Validate Cart Page");
        pDPage.clickGoToCheckOut();
        checkWindow("Validate Checkout Page");
        //pDPage.doLogin("sherwin123");
        checkWindow("Validate Review Page");
        pDPage.clickSubmitOrder();
        pDPage.clickDontShowAgain();
        checkWindow("Validate Order Confirmation Page");

        verifyEyesTest();
    }
}
