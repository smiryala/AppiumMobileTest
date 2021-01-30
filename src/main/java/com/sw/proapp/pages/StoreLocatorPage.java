package com.sw.proapp.pages;

import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.proapp.data.StoreLocatorPageData;
import com.sw.proapp.locators.StoreLocatorPageLocators;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreLocatorPage extends StoreLocatorPageLocators {

    public StoreLocatorPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("locationPermission perm is {0}")
    public StoreLocatorPage locationPermission(String perm) {
        getCommon().sleepFor(2);
        switch (perm.toLowerCase()) {
            case ("allow while using the app"):
                if (existsElement(allowWhileUsingApp))
                    getCommon().clickAndVerifyIsTrue(allowWhileUsingApp, "Unable to click" + perm);
                else
                    ConsoleLog.info("No Permission required");
                break;
            case ("allow once"):
                elementClickByContainTextAndSpaceIOS("una vez", "Permitir");
                elementClickByContainTextAndSpaceIOS("once", "Allow");
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

    @Step()
    public int getNearestStoresSizeForiOS() {
        return getCommon().getDriver()
                .findElements(MobileBy.iOSNsPredicateString(StoreLocatorPageData.NEAREST_STORE_IOS))
                .size();
    }

    @Step()
    public String getNearestStore() {
        getCommon().sleepFor(10);
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getAttribute(nearestStore, "value");
        } else {
            return getCommon().getText(nearestStore, "Store not avaialble");
        }

    }

    @Step("searchStore is {0}")
    public StoreLocatorPage searchStore(String keyWord) {

        getCommon().typeAndVerifyIsTrue(searchStore, keyWord, "unable to type");
        if (getDriver() instanceof AndroidDriver)
            getCommon().clickAndVerifyIsTrue(searchStore, "Unable to click the text box");
        pressEnter();
        return this;

    }

    @Step()
    public void clickListOfStore() {
        getCommon().clickAndVerifyIsTrue(switchStoreList, "unable to click the store list");
        this.clickElementByTextIfExists("APLICAR");
    }

    @Step()
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

    @Step()
    public void selectSecondItemFromTheStoresList() {
        String xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeStaticText[1]/";

        if (getDriver() instanceof IOSDriver) {
            this.clickElementbyXpath(xpath+"XCUIElementTypeButton[2]");
        } else {
            getDriver().findElementsById("storeSelectionButton").get(1).click();
        }

    }

    @Step()
    public String getDisplayedStoreElementsInformationBy(String name) {
        String xpath = "//XCUIElementTypeTable/XCUIElementTypeCell[2]/";
        if (getDriver() instanceof IOSDriver) {
            switch (name)
            {
                case "selectstore":
                    return getTextByXpath(xpath+"XCUIElementTypeButton[3]");
                case "name":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[1]");
                case "storeclosttime":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[2]");
                case "distance":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[4]");
                case "address":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[5]");
                case "cityzipcode":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[6]");
                case "storemanager":
                    return getTextByXpath(xpath+"XCUIElementTypeStaticText[7]");
                case "callbutton":
                    return getDriver().findElementByXPath(xpath+"XCUIElementTypeButton[4]").getAttribute("label");
                case "directionsbutton":
                    return getDriver().findElementByXPath(xpath+"XCUIElementTypeButton[1]").getAttribute("label");
                default:
                    return "not found element";
            }
        } else {
            switch (name)
            {
                case "selectstore":
                    return getDriver().findElementsById("storeSelectionButton").get(1).getText();
                case "name":
                    return getDriver().findElementsById("name").get(1).getText();
                case "storeclosttime":
                    return getDriver().findElementsById("todaysHours").get(1).getText();
                case "distance":
                    return getDriver().findElementsById("distance").get(1).getText();
                case "address":
                    return getDriver().findElementsById("address").get(1).getText();
                case "cityzipcode":
                    return getDriver().findElementsById("cityStateZip").get(1).getText();
                case "storemanager":
                    return getDriver().findElementsById("storeManagerName").get(1).getText();
                case "callbutton":
                    return getDriver().findElementsById("phoneButton").get(1).getAttribute("content-desc");
                case "directionsbutton":
                    return getDriver().findElementsById("directionsButton").get(1).getAttribute("content-desc");
                default:
                    return "not found element";
            }

        }

    }
}