package com.sw.app.proapp;

import com.aventstack.extentreports.Status;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.StoreLocatorPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class ProAppStoreLocatorTest {

    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;
    HomePage homePage = null;
    StoreLocatorPage storeLocatorPage = null;
    private AccountPage accountPage = null;

    public ProAppStoreLocatorTest(Common common,String language) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        storeLocatorPage = new StoreLocatorPage(common);
        homePage = new HomePage(common);
        accountPage = new AccountPage(common);
        storeLocatorPage.languageBase = language;
        homePage.languageBase = language;
        accountPage.languageBase = language;
    }

    //OLD Code
    /*@Step()
    public void verifyStoreLocatorPageAndroid() {
        accountPage.signOutProApp();
        homePage.clickProAppButton();
        homePage.clickStores();
        storeLocatorPage.mobileGrantPermission();
        storeLocatorPage.locationPermission("Allow");
        String storeBeforeZip = storeLocatorPage.getNearestStore();
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0);
        storeLocatorPage.searchStore("44320");
        storeLocatorPage.waitForLoading();
        String storeAfterZip = storeLocatorPage.getNearestStore();
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0);
        Assert.assertNotEquals(storeBeforeZip, storeAfterZip);
        storeLocatorPage.clickListOfStore();
        Assert.assertTrue(storeLocatorPage.getStoreInList().size() > 0);
        ConsoleLog.info("No of stores " + storeLocatorPage.getStoreInList().size());
        storeLocatorPage.clickMapOfStore();
        storeLocatorPage.waitForLoading();
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0);
    }

    @Step()
    public void verifyStoreLocatorPageIOS() {
        accountPage.signOutProApp();
        homePage.clickStores();
        storeLocatorPage.locationPermission("Allow Once");
        storeLocatorPage.searchStore("44136");
        String storeBeforeZip = storeLocatorPage.getNearestStore();

        ExtentTestManager.getTest().log(Status.INFO, "Store #1 " + storeBeforeZip);
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);

        Assert.assertEquals(storeBeforeZip, "Strongsville #1532");

        storeLocatorPage.searchStore("19701");
        storeLocatorPage.waitForLoading();
        String storeAfterZip = storeLocatorPage.getNearestStore();
        ExtentTestManager.getTest().log(Status.INFO, "Store #2 " + storeAfterZip);
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);
        Assert.assertEquals(storeAfterZip, "Bear #5482");

        storeLocatorPage.clickListOfStore();
        Assert.assertTrue(storeLocatorPage.getNearestStoresSizeForiOS() > 0);
        ConsoleLog.info("No of stores " + storeLocatorPage.getNearestStoresSizeForiOS());
        storeLocatorPage.clickMapOfStore();
        storeLocatorPage.waitForLoading();
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);
    }*/


    @Step()
    public void verifyStoreLocatorPageAndroid() {
        //NOT NEEDED accountPage.signOutProApp();
        //---NOT NEEDED homePage.clickProAppButton();
        homePage.hideKeyboard();
        homePage.clickElementByTextExact("Stores°Tiendas", homePage.languageBase);
        storeLocatorPage.mobileGrantPermission();
        storeLocatorPage.searchStore("95014");
        storeLocatorPage.iOSWaitForElementByText("wait page to load", "successful wait");
        storeLocatorPage.clickElementByText("SELECT STORE°SELECCIONAR TIENDA", storeLocatorPage.languageBase);

        //Validate store details displayed
        storeLocatorPage.clickListOfStore();
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("selectstore"), storeLocatorPage.getTextByLanguage("SELECT STORE°SELECCIONAR TIENDA", storeLocatorPage.languageBase), "select store button does exists");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("name"), storeLocatorPage.getTextByLanguage("Sunnyvale #708668", "english"), "name does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("storeclosttime"), storeLocatorPage.getTextByLanguage("Open until 7:00 PM°Abierto hasta 7:00 PM", storeLocatorPage.languageBase), "close time does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("distance"), storeLocatorPage.getTextByLanguage("4.3 miles°4.3 millas", storeLocatorPage.languageBase), "distance does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("address"), storeLocatorPage.getTextByLanguage("510a E El Camino Real", "english"), "address does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("cityzipcode"), storeLocatorPage.getTextByLanguage("Sunnyvale, CA 94087", "english"), "city and zipcode does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("storemanager"), storeLocatorPage.getTextByLanguage("Store Manager: Simone J Saslow°Gerente de tienda: Simone J Saslow", storeLocatorPage.languageBase), "the manager name does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("callbutton"), storeLocatorPage.getTextByLanguage("Call°Llamar", storeLocatorPage.languageBase), "the call button does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("directionsbutton"), storeLocatorPage.getTextByLanguage("Directions°Direcciones", storeLocatorPage.languageBase), "the call button does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("cityzipcode"), storeLocatorPage.getTextByLanguage("Sunnyvale, CA 94087", "english"), "city and zipcode does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("storemanager"), storeLocatorPage.getTextByLanguage("Store Manager: Simone J Saslow°Gerente de tienda: Simone J Saslow", storeLocatorPage.languageBase), "the manager name does not match");

        storeLocatorPage.selectSecondItemFromTheStoresList();
        Assert.assertTrue(storeLocatorPage.clickElementByText("YOUR STORE°TU TIENDA", storeLocatorPage.languageBase), "user was unable to select store");
        Assert.assertTrue(storeLocatorPage.clickElementByText("Sunnyvale #708668", "english"), "user was unable to select store");

        storeLocatorPage.androidWaitForElementByText("wait page to load", "successful wait");
        //select again cupertino store for testing purposes
        storeLocatorPage.clickListOfStore();
        storeLocatorPage.selectSecondItemFromTheStoresList();
        storeLocatorPage.clickBackButton();

        String storeBeforeZip = storeLocatorPage.getNearestStore();
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0,"the store 1 does not exist");
        storeLocatorPage.searchStore("44136");
        storeLocatorPage.androidWaitForElementByText("Nicholas", "not found");
        String storeAfterZip = storeLocatorPage.getNearestStore();
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0);
        Assert.assertNotEquals(storeBeforeZip, storeAfterZip, "stores are the same an error happen");
        storeLocatorPage.androidWaitForElementByText("Sunnyvale #708668", "unable to wait for store");
        storeLocatorPage.clickListOfStore();
        storeLocatorPage.androidWaitForElementByText("Sunnyvale #708668", "unable to wait for store");
        Assert.assertTrue(storeLocatorPage.getStoreInList().size() > 0, "no list displayed");
        storeLocatorPage.androidWaitForElementByText("Sunnyvale #708668", "unable to wait for store");
        storeLocatorPage.androidWaitForElementByText("Sunnyvale #708668", "unable to wait for store");
        ConsoleLog.info("No of stores " + storeLocatorPage.getStoreInList().size());
        storeLocatorPage.clickMapOfStore();
        storeLocatorPage.androidWaitForElementByText("Nicholas", "not found");
        Assert.assertNotEquals(storeLocatorPage.getNearestStore().length(), 0, "the store 2 does not exist");

    }

    @Step()
    public void verifyStoreLocatorPageIOS() {
        //NOT NEEDED accountPage.signOutProApp();
        homePage.clickElementByTextExact("Stores°Tiendas", homePage.languageBase);
        storeLocatorPage.searchStore("95014");
        storeLocatorPage.iOSWaitForElementByText("wait page to load", "successful wait");
        storeLocatorPage.clickElementByText("SELECT STORE°SELECCIONAR TIENDA", storeLocatorPage.languageBase);

        //Validate store details displayed
        storeLocatorPage.clickListOfStore();
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("selectstore"),
                storeLocatorPage.getTextByLanguage("SELECT STORE°SELECCIONAR TIENDA", storeLocatorPage.languageBase), "select store button does exists");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("name"),
                storeLocatorPage.getTextByLanguage("Sunnyvale #708668", "english"), "name does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("storeclosttime"),
                storeLocatorPage.getTextByLanguage("Open until 7:00 PM  •°Abierto hasta 7:00 PM  •", storeLocatorPage.languageBase), "close time does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("distance"),
                storeLocatorPage.getTextByLanguage("4.3 miles°4.3 millas", storeLocatorPage.languageBase), "distance does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("address"),
                storeLocatorPage.getTextByLanguage("510a E El Camino Real", "english"), "address does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("callbutton"),
                storeLocatorPage.getTextByLanguage("call°llamar", storeLocatorPage.languageBase), "the call button does not match");
        Assert.assertEquals(storeLocatorPage.getDisplayedStoreElementsInformationBy("directionsbutton"), storeLocatorPage.getTextByLanguage("directions°addresses", storeLocatorPage.languageBase), "the call button does not match");

        storeLocatorPage.selectSecondItemFromTheStoresList();
        Assert.assertTrue(storeLocatorPage.clickElementByText("YOUR STORE°TU TIENDA", storeLocatorPage.languageBase), "user was unable to select store");
        Assert.assertTrue(storeLocatorPage.clickElementByText("Sunnyvale #708668", "english"), "user was unable to select store");

        storeLocatorPage.iOSWaitForElementByText("wait page to load", "successful wait");
        //select again cupertino store for testing purposes
        storeLocatorPage.clickListOfStore();
        storeLocatorPage.iOSWaitForElementByText("wait page to load", "successful wait");
        storeLocatorPage.selectSecondItemFromTheStoresList();
        storeLocatorPage.iOSWaitForElementByText("wait page to load", "successful wait");
        storeLocatorPage.clickMapOfStore();

        storeLocatorPage.searchStore("44136");
        //homePage.iOSWaitForElementByText("wait for loading", "unable to wait for loading");
        String storeBeforeZip = storeLocatorPage.getNearestStore();
        homePage.iOSWaitForElementByText("wait for loading", "unable to wait for loading");
        ExtentTestManager.getTest().log(Status.INFO, "Store #1 " + storeBeforeZip);
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);
        Assert.assertTrue(storeBeforeZip.contains("Strongsville #7"), "No store displayed");

        storeLocatorPage.searchStore("19701");
        storeLocatorPage.waitForLoading();
        String storeAfterZip = storeLocatorPage.getNearestStore();
        //homePage.iOSWaitForElementByText("wait for loading", "unable to wait for loading");
        ExtentTestManager.getTest().log(Status.INFO, "Store #2 " + storeAfterZip);
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);
        //Assert.assertEquals(storeAfterZip, "Bear #5482");
        Assert.assertTrue(storeAfterZip.contains("Bear #"), "No new store displayed");

        storeLocatorPage.clickListOfStore();
        //Validate information of store
        //store distance
        Assert.assertTrue(homePage.elementExistsByText("3.2 miles", homePage.languageBase), "distance is not displayed");
        //store name
       // Assert.assertTrue(homePage.elementExistsByText("Bear #5482", "english"), "store name is not displayed");
        //store name
       // Assert.assertTrue(homePage.elementExistsByText("Bear #5482", "english"), "store name is not displayed");
        Assert.assertTrue(homePage.elementExistsByText("Bear #705482", "english"), "store name is not displayed");


        Assert.assertTrue(storeLocatorPage.getNearestStoresSizeForiOS() > 0);
        ConsoleLog.info("No of stores " + storeLocatorPage.getNearestStoresSizeForiOS());
        storeLocatorPage.clickMapOfStore();
        homePage.sleepFor(5);
        storeLocatorPage.waitForLoading();
        homePage.sleepFor(5);
        Assert.assertNotEquals(storeLocatorPage.getNearestStoresSizeForiOS(), 0);
    }


}