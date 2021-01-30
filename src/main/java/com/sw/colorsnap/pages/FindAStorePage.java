package com.sw.colorsnap.pages;

import com.sw.colorsnap.data.FindAStorePageData;
import com.sw.colorsnap.locators.FindAStorePageLocators;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FindAStorePage extends FindAStorePageLocators {

    public FindAStorePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("locationPermission perm is {0}")
    public FindAStorePage locationPermission(String perm) {
        getCommon().sleepFor(2);
        switch (perm.toLowerCase()) {
            case ("allow while using the app"):
                if (existsElement(allowWhileUsingApp))
                    getCommon().clickAndVerifyIsTrue(allowWhileUsingApp, "Unable to click" + perm);
                else
                    ConsoleLog.info("No Permission required");
                break;
            case ("allow once"):
            case ("allow"):
                if (existsElement(allowOnce))
                    getCommon().clickAndVerifyIsTrue(allowOnce, "Unable to click" + perm);
                else
                    ConsoleLog.info("No Permission required");
                break;
            case ("don't allow"):
                if (existsElement(dontAllow)) {
                    getCommon().clickAndVerifyIsTrue(dontAllow, "Unable to click" + perm);
                } else {
                    ConsoleLog.info("No Permission required");
                }
                break;
        }
        getCommon().sleepFor(2);
        waitForLoading();
        return this;
    }

    @Step("Get the nearest Store Size")
    public int getNearestStoresSizeForiOS() {
        return getCommon().getDriver()
                .findElements(MobileBy.iOSNsPredicateString(FindAStorePageData.NEAREST_STORE_IOS))
                .size();
    }

    @Step("Get the nearest Store value")
    public String getNearestStoreText() {
        getCommon().sleepFor(10);
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getAttribute(nearestStore, "value");
        } else {
            return getCommon().getText(nearestStore, "Store not available");
        }

    }

    @Step("Navigating to filter")
    public FindAStorePage clickFilter() {
        getCommon().clickAndVerifyIsTrue(storeFilter, "Unable to click storefilter");
        return this;
    }

    @Step("Navigating to Store paint filter")
    public FindAStorePage clickPaintFilter() {
        getCommon().clickAndVerifyIsTrue(storePaintFilter, "Unable to click storefilter");
        return this;
    }

    @Step("Navigating to paint Store")
    public String getPaintStore() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getAttribute(storePaintFilterText, "value");
        } else {
            return getCommon().getText(storePaintFilterText, "paint filternot available");
        }
    }

    @Step("Navigating to Commercial paint Store")
    public String getCommercialStore() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getAttribute(storeCommFilterText, "value");
        } else {
            return getCommon().getText(storeCommFilterText, "Commercial paint filter not available");
        }
    }

    @Step("Navigating to Find a Store")
    public String getfindAStoreTitle() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getAttribute(findAStoreTitle, "value");
        } else {
            return getCommon().getText(findAStoreTitle, "paint filter not available");
        }
    }


    @Step("Change commercial store filter")
    public FindAStorePage clickCommFilter() {
        getCommon().clickAndVerifyIsTrue(storeCommFilter, "Unable to click storefilter");
        return this;
    }

    @Step("Apply Filter Changes")
    public FindAStorePage clickApplyFilter() {
        getCommon().clickAndVerifyIsTrue(apply, "Unable to click on Apply");
        return this;
    }

    @Step("searchStore is {0}")
    public FindAStorePage searchStore(String keyWord) {
        getCommon().typeAndVerifyIsTrue(searchStore, keyWord, "unable to type");
        if (getDriver() instanceof AndroidDriver)
            getCommon().clickAndVerifyIsTrue(searchStore, "Unable to click the text box");
        pressEnter();
        return this;

    }

    @Step("Click on Store List view")
    public void clickListOfStore() {
        getCommon().clickAndVerifyIsTrue(switchStoreList, "unable to click the store list");
    }

    @Step("Click on Store Map view")
    public void clickMapOfStore() {
        getCommon().clickAndVerifyIsTrue(switchStoreMap, "unable to click the store Map");
    }

    @Step()
    public List<WebElement> getStoreInList() {
        return storeList;
    }

    @Step()
    public void waitForLoading() {
        try {
            if (existsElement(inProgress)) {
                ConsoleLog.info("Waiting for the loading icon to disappear...");
                new WebDriverWait(getDriver(), 5).until(ExpectedConditions.invisibilityOfElementLocated(inProgressBy));
            }
        } catch (Exception e) {
            ConsoleLog.info("The loading icon was not encountered...");
        }
    }
}