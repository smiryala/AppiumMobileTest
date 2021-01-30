package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidExpirationPageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BidExpirationPage extends BidExpirationPageLocators {

    public BidExpirationPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isBidExpirationPageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("bid expiration") || pageSource.contains("VENCIMIENTO DEL ESTIMADO".toLowerCase());
    }

    public boolean isEmptyBidExpirationDropdownDisplayed() {
        getCommon().sleepFor(3);
        return getCommon().isDisplayed(bidExpirationDropdownEmpty);
    }

    public boolean isPopulatedBidExpirationDropdownDisplayed() {
        return getCommon().isDisplayed(bidExpirationDropdownPopulated);
    }

    public boolean isBidExpiration7DaysDisplayed() {
        return getCommon().isDisplayed(bidExpiration7Days);
    }

    public boolean isBidExpiration15DaysDisplayed() {
        return getCommon().isDisplayed(bidExpiration15Days);
    }

    public boolean isBidExpiration30DaysDisplayed() {
        return getCommon().isDisplayed(bidExpiration30Days);
    }

    public boolean isBidExpiration45DaysDisplayed() {
        return getCommon().isDisplayed(bidExpiration45Days);
    }

    public boolean isBidNoExpirationDisplayed() {
        return getCommon().isDisplayed(bidNoExpiration);
    }

    public boolean isSaveButtonDisplayed() {
        return getCommon().isDisplayed(bidExpirationSaveButton);
    }

    public BidExpirationPage clickExpirationDropdown() {
        if (isEmptyBidExpirationDropdownDisplayed()) {
            getCommon().clickAndVerifyIsTrue(bidExpirationDropdownEmpty, "Unable to click bid expiration dropdown");
        } else if (isPopulatedBidExpirationDropdownDisplayed()) {
            getCommon().clickAndVerifyIsTrue(bidExpirationDropdownPopulated, "Unable to click bid expiration dropdown");
        }
        return this;
    }

    public BidExpirationPage clickDropdownDoneButton() {
        getCommon().clickAndVerifyIsTrue(bidExpirationDropdownButtonDone, "Unable to click dropdown done button");
        return this;
    }

    public BidExpirationPage clickSaveButton() {
        getCommon().clickAndVerifyIsTrue(bidExpirationSaveButton, "Unable to click save button as expected");
        return this;
    }

    public BidExpirationPage clickDiscardButton() {
        getCommon().clickAndVerifyIsTrue(bidExpirationDiscardButton, "Unable to click discard button as expected");
        return this;
    }

    public BidExpirationPage click7DaysCta() {
        getCommon().clickAndVerifyIsTrue(bidExpiration7Days, "Unable to click 7 days button as expected");
        return this;
    }
}