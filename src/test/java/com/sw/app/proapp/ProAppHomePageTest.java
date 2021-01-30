package com.sw.app.proapp;

import com.aventstack.extentreports.Status;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import io.qameta.allure.Step;
import org.testng.Assert;import com.sw.proapp.pages.PaintCalculatorPage;

public class ProAppHomePageTest {

    private HomePage homePage = null;
    private AccountPage accountPage = null;
    AppDataFromDB appDataFromDB = null;
    private PaintCalculatorPage paintCalculatorPage = null;

    public ProAppHomePageTest(Common common) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        homePage = new HomePage(common);
        accountPage = new AccountPage(common);
        paintCalculatorPage= new PaintCalculatorPage(common);
    }

    public ProAppHomePageTest(Common common, String language) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        homePage = new HomePage(common);
        accountPage = new AccountPage(common);
        homePage.languageBase=language;
        accountPage.languageBase=language;
    }

    /*@Step()
    public void verifyHomePageAndroid() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyHomepage");
        homePage.clickProAppButton();

        //Check Logo
        Assert.assertTrue(homePage.isSherwinLogoDisplayed(), "SherwinLogo is not displayed");

        //Check Account Summary
        Assert.assertTrue(homePage.getAccountSummary().isDisplayed(),
                "Account Summary is not displayed");

        //Check Account Summary Details
        Assert.assertTrue(homePage.getAccountSummaryDetails().isDisplayed(),
                "Account Summary Details is not displayed");

        //Check Store info
        Assert.assertTrue(homePage.getStoreInfo().isDisplayed(), "Store is not displayed");

        //Check Frequently Purchased
        try {
            Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
                    "Frequently purchased is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
                    "Frequently purchased is not displayed");
        }
        //Project BIDS
        homePage.scrollToElementByText("PROJECT BIDS", "english");
        Assert.assertTrue(homePage.getProjectBidsSection().isDisplayed(), "ProjectBids  is not displayed");
        
        //Check Order History
        try {
            Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
        }

        //Check Recent Online Orders
        try {
            Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");
        }
        //Check How are we doing
        try {
            Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");
        }
        //Check Pro Advantages
        try {
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        }

        homePage.scrollDown3();
        Assert.assertEquals(homePage.getProAdvantagesSections().size(), 8,
                "Pro advantages sections mismatched");

        //Top Supplies
        Assert.assertTrue(homePage.getTopSuppliesSection().isDisplayed(),
                "Top Supplies is not displayed");
        //PRO ALERTS
        Assert.assertTrue(homePage.getProAlerts_Section().isDisplayed(),
                "PRO ALERTS is not displayed");
        //S-W.COM PRODUCT INFO
        Assert.assertTrue(homePage.getSwdotcomProductInfoSection().isDisplayed(),
                "S-W.COM PRODUCT INFO is not displayed");
        //S-W.COM DATA SHEETS
        Assert.assertTrue(homePage.getSwdotcomDataSheetsSection().isDisplayed(),
                "S-W.COM DATA SHEETS is not displayed");
        //STATEMENTS & PAYMENTS
        Assert.assertTrue(homePage.getStatementsAndPaymentsSection().isDisplayed(),
                "STATEMENTS & PAYMENTS is not displayed");
        //COLORSNAP APP
        Assert.assertTrue(homePage.getColorSnapAppSection().isDisplayed(),
                "COLORSNAP APP is not displayed");
        //PAINT CALCULATOR
        Assert.assertTrue(homePage.getPaintCalculatorSection().isDisplayed(),
                "PAINT CALCULATOR is not displayed");
        //PPC MAGAZINE
        Assert.assertTrue(homePage.getPpcMagzineSection().isDisplayed(),
                "PPC MAGAZINE is not displayed");
        accountPage.signOutProApp();
    }

    @Step()
    public void verifyHomePageIOS() {
        homePage.clickProAppButton();
        //Check Logo
        Assert.assertTrue(homePage.isSherwinLogoDisplayed(), "SherwinLogo is not displayed");

        //Check Account Summary
        Assert.assertTrue(homePage.getAccountSummary().isDisplayed(), "Account Summary is not displayed");

        //Check Account Summary Details
        Assert.assertTrue(homePage.getAccountSummaryDetails().isDisplayed(), "Account Summary Details is not displayed");

        //Check Store info
        Assert.assertTrue(homePage.getStoreInfo().isDisplayed(), "Store is not displayed");

        //Check Frequently Purchased
        Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(), "Frequently purchased is not displayed");

        //Check the Project Bids
        homePage.scrollToElementByText("PROJECT BIDS", "english");
        Assert.assertTrue(homePage.getProjectBidsSection().isDisplayed(), "ProjectBids  is not displayed");

        homePage.scrollDown3();
        //Check Order History
        Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");

        //Check Recent Online Orders
        Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");

        //Check How are we doing
        homePage.scrollDown3();
        homePage.scrollDown3();
        Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");

        //Check Pro Advantages
        try {
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        }
        homePage.scrollDown3();
        //Assert.assertEquals(homePage.getProAdvantagesSections().size(), 8, "Pro advantages sections mismatched");

        //Top Supplies
        Assert.assertTrue(homePage.getTopSuppliesSection().isDisplayed(),
                "Top Supplies is not displayed");
        //PRO ALERTS
        Assert.assertTrue(homePage.getProAlerts_Section().isDisplayed(),
                "PRO ALERTS is not displayed");
        //S-W.COM PRODUCT INFO
        Assert.assertTrue(homePage.getSwdotcomProductInfoSection().isDisplayed(),
                "S-W.COM PRODUCT INFO is not displayed");
        //S-W.COM DATA SHEETS
        Assert.assertTrue(homePage.getSwdotcomDataSheetsSection().isDisplayed(),
                "S-W.COM DATA SHEETS is not displayed");
        //STATEMENTS & PAYMENTS
        Assert.assertTrue(homePage.getStatementsAndPaymentsSection().isDisplayed(),
                "STATEMENTS & PAYMENTS is not displayed");
        //COLORSNAP APP
        Assert.assertTrue(homePage.getColorSnapAppSection().isDisplayed(),
                "COLORSNAP APP is not displayed");
        //PAINT CALCULATOR
        Assert.assertTrue(homePage.getPaintCalculatorSection().isDisplayed(),
                "PAINT CALCULATOR is not displayed");
        //PPC MAGAZINE
        Assert.assertTrue(homePage.getPpcMagzineSection().isDisplayed(),
                "PPC MAGAZINE is not displayed");
        accountPage.signOutProApp();
    }*/

    @Step()
    public void verifyHomePageAndroid() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyHomepage");
        //->NOT NEEDED homePage.clickProAppButton();
        //accountPage.signOutProApp();
        //Check Logo
        Assert.assertTrue(homePage.isSherwinLogoDisplayed(), "SherwinLogo is not displayed");

        //Check Account Summary
        Assert.assertTrue(homePage.getAccountSummary().isDisplayed(),
                "Account Summary is not displayed");

        //Account summary details displayed e.g job
        Assert.assertTrue(homePage.elementExistsByText("XXXX-X021-5 - EFFICIENT CONTRACTORS INC.","english"), "account details does not match");
        Assert.assertTrue(homePage.elementExistsByText(": EFFICIENT CONTRACTORS INC.","english"), "account job does not match");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");


        //Check Account Summary Details
        Assert.assertTrue(homePage.getAccountSummaryDetails().isDisplayed(),
                "Account Summary Details is not displayed");

        //Check Store info
        //->NOT NEEDED Assert.assertTrue(homePage.getStoreInfo().isDisplayed(), "Store is not displayed");

        //Check Frequently Purchased
        try {
            Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
                    "Frequently purchased is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
                    "Frequently purchased is not displayed");
        }
        //Carrousel items present
        Assert.assertTrue(homePage.getNumberOfDisplayedCarrouselItems()>0,"the carrousels items are not present");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");


        //Check Order History
        try {
            Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
        }
        String orderHistoryDetails= homePage.getTextByLanguage("Reorder products from past purchases and view invoices here.",
                homePage.languageBase);

        homePage.scrollToElementByText("Reorder products from past purchases and view invoices here.", homePage.languageBase);
        //Order History Details
        try {
            Assert.assertTrue(homePage.elementExistsByText("Reorder products from past purchases and view invoices here.", homePage.languageBase), "Recent Online Orders is not displayed");
        } catch (Exception e) {
            Assert.assertEquals(homePage.getOrderHistoryDetailsElement().getText(),orderHistoryDetails,"the order history details does not match");
        }
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");

        homePage.scrollToElementByText("RECENT ONLINE ORDERS", homePage.languageBase);


        //Check Recent Online Orders
        try {
            Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");
        }
        //Recent orders online details
        Assert.assertTrue(homePage.elementExistsByText("ORDER BEING FILLED", homePage.languageBase),"recent order online label not displayed");
        //Assert.assertTrue(homePage.elementExistsByText("CUPERTINO #", homePage.languageBase),"recent order online label not displayed");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");

        //Check How are we doing
        homePage.scrollToElementByText("HOW ARE WE DOING?", homePage.languageBase);

        try {
            Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");
        }

        //Top Supplies
        Assert.assertTrue(homePage.getTopSuppliesSection().isDisplayed(),
                "Top Supplies is not displayed");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");


        homePage.clickElementByText("Tools", homePage.languageBase);
        //Project BIDS
        Assert.assertTrue(homePage.getProjectBidsSection().isDisplayed(), "ProjectBids  is not displayed");

        //Check Pro Advantages
        /*NOT NEEDED-> try {
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        }
        homePage.scrollDown3();
        Assert.assertEquals(homePage.getProAdvantagesSections().size(), 8,
                "Pro advantages sections mismatched");
         */
        //PRO ALERTS
        //->NOT NEEDED Assert.assertTrue(homePage.getProAlerts_Section().isDisplayed(),
        //        "PRO ALERTS is not displayed");
        Assert.assertTrue(homePage.elementExistsByText("PROJECT BIDS", homePage.languageBase), "ProjectBids  is not displayed");//getProjectBidsSection().isDisplayed()

        try {
            Assert.assertTrue(homePage.elementExistsByText("PAINT CALCULATOR", homePage.languageBase),
                    "PAINT CALCULATOR is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.elementExistsByText("PAINT CALCULATOR", homePage.languageBase),
                    "PAINT CALCULATOR is not displayed");
        }
        homePage.clickElementByText("PAINT CALCULATOR", homePage.languageBase);
        //Validate paint calculator page is displayed
        Assert.assertTrue(homePage.elementExistsByText("HOW TO CALCULATE",homePage.languageBase),"The paint calculator page is not displayed");
        homePage.clickBackButton();
        homePage.scrollDown3();
        // PRO ALERTS
        Assert.assertTrue(homePage.elementExistsByText("PRO ALERTS", homePage.languageBase), "PRO ALERTS is not displayed");//getProAlerts_Section().isDisplayed()
        // S-W.COM PRODUCT INFO
        Assert.assertTrue(
                homePage.elementExistsByText("S-W.COM PRODUCT INFO", homePage.languageBase),
                "S-W.COM PRODUCT INFO is not displayed");
        // S-W.COM DATA SHEETS
        // getSwdotcomDataSheetsSection().isDisplayed()
        Assert.assertTrue(homePage.elementExistsByText("S-W.COM DATA SHEETS", homePage.languageBase),
                "S-W.COM DATA SHEETS is not displayed");
        // COLORSNAP APP
        Assert.assertTrue(homePage.elementExistsByText("COLORSNAP APP", homePage.languageBase),
                "COLORSNAP APP is not displayed");
        // NOT NEEDED accountPage.signOutProApp();
    }

    @Step()
    public void verifyHomePageIOS() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyHomepage");
        //NOT NEEDED-> homePage.clickProAppButton();
        //Check Logo
        Assert.assertTrue(homePage.isSherwinLogoDisplayed(), "SherwinLogo is not displayed");

        //Check Account Summary
        Assert.assertTrue(homePage.getAccountSummary().isDisplayed(), "Account Summary is not displayed");

        //Check Account Summary Details
        Assert.assertTrue(homePage.getAccountSummaryDetails().isDisplayed(), "Account Summary Details is not displayed");

        //Account summary details displayed e.g job
        Assert.assertTrue(homePage.elementExistsByText("XXXX-X021-5 - EFFICIENT CONTRACTORS INC.","english"), "account details does not match");
        Assert.assertTrue(homePage.elementExistsByText("1: EFFICIENT CONTRACTORS INC.","english"), "account job does not match");

        //Check Store info
        // -> NOT NEEDED ->Assert.assertTrue(homePage.getStoreInfo().isDisplayed(), "Store is not displayed");

        //Check Frequently Purchased
        Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(), "Frequently purchased is not displayed");
        //bids section moved as per new tools windows
        homePage.scrollToElementByText("ORDER HISTORY", homePage.languageBase);


        //Check Order History
        Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
        String orderHistoryDetails= homePage.getTextByLanguage("Reorder products from past purchases and view invoices here.",
                homePage.languageBase);

       // homePage.scrollToElementByText("Reorder products from past purchases and view invoices here.", homePage.languageBase);
        homePage.scrollDown3();
        try {
            Assert.assertTrue(homePage.elementExistsByText("Reorder products from past purchases and view invoices here.", homePage.languageBase), "Recent Online Orders is not displayed");
        } catch (Exception e) {
            Assert.assertEquals(homePage.getOrderHistoryDetailsElement().getText(),orderHistoryDetails,"the order history details does not match");
        }
        //homePage.scrollDown3();
        homePage.scrollToElementByText("RECENT ONLINE ORDERS", homePage.languageBase);
        //Check Recent Online Orders
        Assert.assertTrue(homePage.getRecentOnlineOrder().isDisplayed(), "Recent Online Orders is not displayed");

        //Recent orders online details
        Assert.assertTrue(homePage.elementExistsByText("ORDER BEING FILLED", homePage.languageBase),"recent order online label not displayed");
        //Assert.assertTrue(homePage.elementExistsByText("CUPERTINO #", homePage.languageBase),"recent order online label not displayed");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");


        //Check How are we doing
        Assert.assertTrue(homePage.getHowAreWeDoing().isDisplayed(), "How are we doing is not displayed");

        //Check Pro Advantages
        /*CODE NOT NEEDED ->try {
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        } catch (Exception e) {
            homePage.scrollDown3();
            Assert.assertTrue(homePage.getProAdvantages().isDisplayed(),
                    "Pro Advantages is not displayed");
        }*/
        //Assert.assertEquals(homePage.getProAdvantagesSections().size(), 8, "Pro advantages sections mismatched");

        //Top Supplies
        Assert.assertTrue(homePage.getTopSuppliesSection().isDisplayed(),
                "Top Supplies is not displayed");

        //Moving to Tools window
        //BIDS
        homePage.clickElementByText("Tools", homePage.languageBase);
        String projectBids = homePage.getTextByLanguage("PROJECT BIDS", homePage.languageBase);
        homePage.iOSWaitForElementByText(projectBids, "unable to find project bids");
        Assert.assertTrue(homePage.getProjectBidsSection().isDisplayed(), "ProjectBids  is not displayed");
        //Paint Calculator
        //Calculadora de Pintura
        String paintCalculatorLabel= homePage.getTextByLanguage("Paint Calculator", homePage.languageBase);
        Assert.assertTrue(homePage.elementExistsByText(paintCalculatorLabel,"english"),
                "Paint Calulator is not displayed");
        homePage.clickElementByText("Paint Calculator", homePage.languageBase);
        //Validate paint calculator page is displayed
        Assert.assertTrue(homePage.elementExistsByText("HOW TO CALCULATE",homePage.languageBase),"The paint calculator page is not displayed");
        Assert.assertFalse(homePage.elementExistsByText("error", "english", false), "error displayed on screen");
        homePage.clickElementByTextExact("back", "english");
        //PRO ALERTS
        //"Alertas Pro"
        String proAlertLabel= homePage.getTextByLanguage("Pro Alerts", homePage.languageBase);
        Assert.assertTrue(homePage.elementExistsByText(proAlertLabel,"english"),
                "PRO ALERTS is not displayed");
        homePage.scrollDown3();
        //S-W.COM PRODUCT INFO
        //S-W.com Información del Producto
        String swInfoLabel= homePage.getTextByLanguage("S-W.com Product Info", homePage.languageBase);
        Assert.assertTrue(homePage.elementExistsByText(swInfoLabel,"english"),
                "S-W.COM PRODUCT INFO is not displayed");
        //S-W.COM DATA SHEETS
        //"S-W.com Hojas de Datos"
        //STATEMENTS & PAYMENTS <-NOT NEEDED
        //  Assert.assertTrue(homePage.elementExistsByText("STATEMENTS & PAYMENTS", "english"),
        //         "STATEMENTS & PAYMENTS is not displayed");
        //COLORSNAP APP
        Assert.assertTrue(homePage.elementExistsByText("ColorSnap App","english"),
                "COLORSNAP APP is not displayed");
        homePage.scrollDown3();
        String swDataSheetLabel= homePage.getTextByLanguage("S-W.com Data Sheets", homePage.languageBase);

        Assert.assertTrue(homePage.elementExistsByText(swDataSheetLabel,"english"),
                "S-W.COM DATA SHEETS is not displayed");
        // NOT NEEDED accountPage.signOutProApp();
    }


}