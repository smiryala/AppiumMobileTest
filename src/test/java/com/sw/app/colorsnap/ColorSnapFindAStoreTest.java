package com.sw.app.colorsnap;

import com.aventstack.extentreports.Status;
import com.sw.colorsnap.pages.FindAStorePage;
import com.sw.colorsnap.pages.ResourcesPage;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.reporting.ExtentTestManager;
import io.qameta.allure.Step;
import org.testng.Assert;

public class ColorSnapFindAStoreTest extends ColorSnapResourcesPageTest {

    ResourcesPage resourcesPage = null;
    FindAStorePage findAStorePage = null;

    public ColorSnapFindAStoreTest(Common common) {
        super(common);
        findAStorePage = new FindAStorePage(common);
        resourcesPage = new ResourcesPage(common);

    }

    @Step("Verify Find a Store for Android")
    public void verifyFindAStorePageAndroid() {
        resourcesPage.clickFindAStore();
        findAStorePage.mobileGrantPermission();
        findAStorePage.locationPermission("Allow");
        String storeBeforeZip = findAStorePage.getNearestStoreText();
        Assert.assertNotEquals(findAStorePage.getNearestStoreText().length(), 0);
        findAStorePage.searchStore("43240");
        findAStorePage.waitForLoading();
        String storeAfterZip = findAStorePage.getNearestStoreText();
        ConsoleLog.info("Store details : " + storeAfterZip);
        Assert.assertNotEquals(findAStorePage.getNearestStoreText().length(), 0);
        Assert.assertNotEquals(storeBeforeZip, storeAfterZip);
        Assert.assertTrue(storeAfterZip.contains("Lewis Center-Polaris"));

        //Validate List Store
        findAStorePage.clickListOfStore();
        Assert.assertTrue(findAStorePage.getStoreInList().size() > 0);
        ConsoleLog.info("No of stores " + findAStorePage.getStoreInList().size());
        findAStorePage.clickMapOfStore();
        findAStorePage.waitForLoading();
        Assert.assertNotEquals(findAStorePage.getNearestStoreText().length(), 0);
    }

    @Step("Verify Find a Store for iOS using Zipcode, State, Commercial Stores")
    public void verifyfindAStorePageIOS() {

        //search by zip code
        findAStorePage.searchStore("43240");
        String storeBeforeZip = findAStorePage.getNearestStoreText();
        ExtentTestManager.getTest().log(Status.INFO, "Store #1 " + storeBeforeZip);
        Assert.assertNotEquals(findAStorePage.getNearestStoresSizeForiOS(), 0);
        findAStorePage.assertEquals(storeBeforeZip, "Lewis Center-Polaris #701356", "Incorrect store is displayed");

        //Search by zipcode
        findAStorePage.searchStore("78758");
        findAStorePage.waitForLoading();
        String storeAfterZip = findAStorePage.getNearestStoreText();
        ExtentTestManager.getTest().log(Status.INFO, "Store #2 " + storeAfterZip);
        Assert.assertNotEquals(findAStorePage.getNearestStoresSizeForiOS(), 0);
        findAStorePage.assertEquals(storeAfterZip, "Austin-Burnet #707033", "Incorrect store is displayed");

        //search by state
        findAStorePage.searchStore("Wyoming");
        findAStorePage.waitForLoading();
        String storeByCity = findAStorePage.getNearestStoreText();
        ExtentTestManager.getTest().log(Status.INFO, "Store #3 " + storeByCity);
        Assert.assertNotEquals(findAStorePage.getNearestStoresSizeForiOS(), 0);
        findAStorePage.assertEquals(storeByCity, "Casper #703439", "Incorrect store is displayed");

        //click on Commercial filter store
        ExtentTestManager.getTest().log(Status.INFO, "Validating Store Filter Functionality");
        findAStorePage.clickFilter();
        findAStorePage.assertEquals(findAStorePage.getPaintStore(), "Paint Stores", "Paint Stores text is not displayed");
        findAStorePage.assertEquals(findAStorePage.getCommercialStore(), "Commercial Paint Stores", "Commercial Paint Stores text is not displayed");
        findAStorePage.clickCommFilter();
        findAStorePage.clickApplyFilter();

        //Validate Commercial Store
        findAStorePage.sleepFor(2);
        String storeByCityCommercial = findAStorePage.getNearestStoreText();
        ExtentTestManager.getTest().log(Status.INFO, "Store #4 " + storeByCityCommercial);
        Assert.assertNotEquals(findAStorePage.getNearestStoresSizeForiOS(), 0);
        findAStorePage.assertEquals(storeByCityCommercial, "Casper Commercial #708960", "Incorrect store is displayed");

        //validate List view and Map View
        ExtentTestManager.getTest().log(Status.INFO, "Validating list view and Map view");
        findAStorePage.clickListOfStore();
        Assert.assertTrue(findAStorePage.getNearestStoresSizeForiOS() > 0);
        ConsoleLog.info("No of stores " + findAStorePage.getNearestStoresSizeForiOS());
        findAStorePage.clickMapOfStore();
        findAStorePage.waitForLoading();
        Assert.assertNotEquals(findAStorePage.getNearestStoresSizeForiOS(), 0);
    }

    @Step("Verify the page elements in Find a Store")
    public void validatefindAStorePageData() {
        ExtentTestManager.getTest().log(Status.INFO, "Validating page elements in Find a Store - begin");
        resourcesPage.clickFindAStore();
        findAStorePage.locationPermission("Allow Once");
        findAStorePage.assertEquals(findAStorePage.getfindAStoreTitle(), "Find a Store", "Incorrect Screen Title displayed");
        findAStorePage.getCommon().isDisplayed(findAStorePage.getSearchStore());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getSwitchStoreList());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getStoreFilter());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getCall());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getDirections());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getStoreHours());
        ExtentTestManager.getTest().log(Status.INFO, "Validation of page elements in Find a Store  - Success");
    }

    @Step("Verify the page elements in Nearby Store")
    public void validateNearbyStoreData() {
        ExtentTestManager.getTest().log(Status.INFO, "Validating page elements in Find a Store - begin");
        resourcesPage.clickFindAStore();
        findAStorePage.locationPermission("Allow Once");
        findAStorePage.assertEquals(findAStorePage.getfindAStoreTitle(), "Find a Store", "Incorrect Screen Title displayed");

        //Search by zipcode
        findAStorePage.searchStore("43035");
        findAStorePage.waitForLoading();
        String storeAfterZip = findAStorePage.getNearestStoreText();
        Assert.assertNotEquals(storeAfterZip.length(), 0);
        findAStorePage.getCommon().isDisplayed(findAStorePage.getCall());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getDirections());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getStoreHours());

        //search by city
        findAStorePage.searchStore("Columbus");
        findAStorePage.waitForLoading();
        String storeByCity = findAStorePage.getNearestStoreText();
        Assert.assertNotEquals(storeByCity.length(), 0);
        findAStorePage.getCommon().isDisplayed(findAStorePage.getCall());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getDirections());
        findAStorePage.getCommon().isDisplayed(findAStorePage.getStoreHours());
        ExtentTestManager.getTest().log(Status.INFO, "Validation of page elements in Find a Store - Nearby Store  - Success");
    }
}