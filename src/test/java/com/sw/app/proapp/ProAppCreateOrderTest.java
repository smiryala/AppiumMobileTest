package com.sw.app.proapp;

import com.aventstack.extentreports.Status;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.RestAPIOMSHelper;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import com.sw.proapp.pages.OrderPage;
import com.sw.proapp.pages.PDPage;
import io.qameta.allure.Step;

public class ProAppCreateOrderTest {

    private LoginPage loginPage = null;
    private HomePage homePage = null;
    private AccountPage accountPage = null;
    private PDPage pdPage = null;
    private OrderPage orderPage = null;
    private Login login = null;
    AppDataFromDB appDataFromDB = null;
    String proAppTestCase= null;

    public ProAppCreateOrderTest(Common common, String proAppTestCase) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        loginPage = new LoginPage(common);
        homePage = new HomePage(common);
        pdPage = new PDPage(common);
        orderPage = new OrderPage(common);
        accountPage = new AccountPage(common);
        this.proAppTestCase = proAppTestCase;
        login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);

    }

    public ProAppCreateOrderTest(Common common, String proAppTestCase, String language) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        loginPage = new LoginPage(common);
        homePage = new HomePage(common);
        pdPage = new PDPage(common);
        orderPage = new OrderPage(common);
        accountPage = new AccountPage(common);
        this.proAppTestCase = proAppTestCase;
        login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
        homePage.languageBase=language;
        pdPage.languageBase=language;
        orderPage.languageBase=language;
        accountPage.languageBase=language;

    }

   /* @Step("verifyUserCreateOrderAndroid with productName {0} and color as {1}")
    public void verifyUserCreateOrderAndroid(String productName, String color) throws Exception {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyUserCreateOrderData");
        homePage.emptyCart();
        homePage.clickSearchButton(productName);
        pdPage.enterAndSelectColor(color);
        pdPage.clickAddToCart();
        pdPage.clickGoToCart();
        pdPage.clickGoToCheckOut();
        loginPage.sleepFor(5);
        loginPage.enterPassword(login.getPassword());
        homePage.scrollDown3();
        homePage.scrollDown3();
        pdPage.clickSubmitOrder();
        pdPage.clickDontShowAgain();
        String orderNo = orderPage.getOrderNumberText();
        //logReportWithScreenShot("Order Confirmation Page");
        orderPage.getEmailConfNumberText();
        orderPage.clickContinueShopping();
        accountPage.signOutProApp();
        String omsStatus = RestAPIOMSHelper.getQAOrderStatus("PA_" + orderNo);
        ExtentTestManager.getTest().log(Status.INFO, "OMS Order Status " + omsStatus);
    }

    @Step("verifyUserCreateOrderIOS with productName {0} and color as {1}")
    public void verifyUserCreateOrderIOS(String productName, String color) throws Exception {
    	ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyUserCreateOrderData");
        homePage.emptyCart();
        homePage.clickSearchButton(productName);
        pdPage.enterAndSelectColor(color);
        // pdPage.enterAndSelectQuantity(2);
        pdPage.clickAddToCart();
        pdPage.clickGoToCart();
        pdPage.clickGoToCheckOut();
        pdPage.enterTextToElementByTextForIOS("Add Job Name or P.O.", "PO_MOBILE_AUTOMATION");
        pdPage.enterTextToElementByTextForIOS("Add Special Instructions and/or delivery details.", "This is Smoke Test for PROAPP Order");
        homePage.scrollDown3();
        homePage.scrollDown3();
        pdPage.clickSubmitOrder();
        pdPage.clickDontShowAgain();
        //logReportWithScreenShot("Order Confirmation Page");
        orderPage.getEmailConfNumberText();
        orderPage.clickCloseButtonInSearch();
        homePage.clickOnCancelSearch();
        homePage.scrollToElementByText("RECENT ONLINE ORDERS", "english");
        String orderNo = orderPage.getOrderNumberText();
        System.out.println("orderNo" + orderNo);
        accountPage.signOutProApp();
        String omsStatus = RestAPIOMSHelper.getQAOrderStatus("PA_" + orderNo);
        ExtentTestManager.getTest().log(Status.INFO, "OMS Order Status " + omsStatus);
    }*/

    @Step("verifyUserCreateOrderAndroid with productName {0} and color as {1}")
    public void verifyUserCreateOrderAndroid(String productName, String color) throws Exception {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyUserCreateOrderData");
        homePage.emptyCart();
        homePage.clickSearchButton(productName);
        pdPage.enterAndSelectColor(color);
        pdPage.clickAddToCart();
        pdPage.clickGoToCart();
        pdPage.clickGoToCheckOut();
        loginPage.sleepFor(5);
        loginPage.enterPassword(login.getPassword());
        homePage.scrollDown3();
        homePage.scrollDown3();
        pdPage.clickSubmitOrder();
        pdPage.clickDontShowAgain();
        String orderNo = orderPage.getOrderNumberText();
        //logReportWithScreenShot("Order Confirmation Page");
        orderPage.getEmailConfNumberText();
        orderPage.clickContinueShopping();
        // NOT NEEDED accountPage.signOutProApp();
        String omsStatus = RestAPIOMSHelper.getQAOrderStatus("PA_" + orderNo);
        ExtentTestManager.getTest().log(Status.INFO, "OMS Order Status " + omsStatus);
    }

    @Step("verifyUserCreateOrderIOS with productName {0} and color as {1}")
    public void verifyUserCreateOrderIOS(String productName, String color, String language) throws Exception {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyUserCreateOrderData");
        homePage.emptyCart();
        homePage.clickSearchButton(productName);
        pdPage.enterAndSelectColor(color);
        // pdPage.enterAndSelectQuantity(2);
        pdPage.clickAddToCart();
        pdPage.clickGoToCart();
        pdPage.clickGoToCheckOut();
        String labelAddName=pdPage.getTextByLanguage("Add Job Name or P.O.°Agregar nombre de trabajo u orden de compra", pdPage.languageBase);
        String labelAddInstructions=pdPage.getTextByLanguage("Add Special Instructions and/or delivery details.°Agrega instrucciones especiales y/o detalles de entrega.", pdPage.languageBase);
        pdPage.enterTextToElementByTextForIOS(labelAddName, "PO_MOBILE_AUTOMATION");
        pdPage.enterTextToElementByTextForIOS(labelAddInstructions, "This is Smoke Test for PROAPP Order");
        homePage.scrollDown3();
        homePage.scrollDown3();
        pdPage.clickSubmitOrder();
        pdPage.clickDontShowAgain();
        //logReportWithScreenShot("Order Confirmation Page");
        orderPage.getEmailConfNumberText();
        orderPage.clickCloseButtonInSearch();
        homePage.clickOnCancelSearch();
        homePage.scrollDown3();//homePage.scrollToElementByText("RECENT ONLINE ORDERS", "english");
        String orderNo = orderPage.getOrderNumberText();
        System.out.println("orderNo" + orderNo);
        // NOT NEEDED accountPage.signOutProApp();
        String omsStatus = RestAPIOMSHelper.getQAOrderStatus("PA_" + orderNo);
        ExtentTestManager.getTest().log(Status.INFO, "OMS Order Status " + omsStatus);
    }


}