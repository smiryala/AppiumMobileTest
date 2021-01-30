package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NavigationPageLocators;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

public class NavigationPage extends NavigationPageLocators {

    private final String bottomNavXpathTemplate = "//*[contains(@resource-id,'bottomNavigationView')]//*[@content-desc='%s']";

    public NavigationPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public void clickBottomNavStoresButton() {
        WebElement bottomNavTarget = getDriver().findElementByXPath(String.format(bottomNavXpathTemplate, "Stores"));
        getCommon().clickAndVerifyIsTrue(bottomNavTarget, "Unable to click Bottom Navigation Stores Button");
        sleepFor(2);
    }

    public void clickBottomNavOrderButton() {
        WebElement bottomNavTarget = getDriver().findElementByXPath(String.format(bottomNavXpathTemplate, "Order"));
        getCommon().clickAndVerifyIsTrue(bottomNavTarget, "Unable to click Bottom Navigation Order Button");
        sleepFor(2);
    }

    public void clickBottomNavMyAccountButton() {
        WebElement bottomNavTarget = getDriver().findElementByXPath(String.format(bottomNavXpathTemplate, "My Account"));
        getCommon().clickAndVerifyIsTrue(bottomNavTarget, "Unable to click Bottom Navigation My Account Button");
        sleepFor(2);
    }

    public void clickBottomNavMoreButton() {
        WebElement bottomNavTarget = getDriver().findElementByXPath(String.format(bottomNavXpathTemplate, "More"));
        getCommon().clickAndVerifyIsTrue(bottomNavTarget, "Unable to click Bottom Navigation More Button");
        sleepFor(2);
    }

    public void verifyGoBackFunction() {
        int randomNum = ThreadLocalRandom.current().nextInt(678, 456723546 + 1);
        WebElement currentFirstEditText = firstEditText;
        getCommon().type(currentFirstEditText, "TestText" + randomNum, true);
            getCommon().click(goBackArrow, "Unable to click Go Back Arrow");
            sleepFor(2);
            getCommon().click(goBackCancel, "Unable to click Go Back Cancel");
            sleepFor(1);
            getCommon().click(goBackArrow, "Unable to click Go Back Arrow");
            sleepFor(2);
            getCommon().click(goBackConfirm, "Unable to click Go Back Confirm");
            sleepFor(2);
            Assert.assertFalse(getCommon().isDisplayed(currentFirstEditText));
    }
}
