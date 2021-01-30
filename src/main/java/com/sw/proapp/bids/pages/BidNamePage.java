package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidNamePageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BidNamePage extends BidNamePageLocators {

    public BidNamePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isLoogedInBidNamePageDispalyed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("bid name") || pageSource.contains("nombre del estimado");
    }

    public WebElement getBidNameInput() {
        return bidNameInput;
    }

    public BidNamePage typeBidName(String input) {
        getCommon().type(bidNameInput, input, true);
        return this;
    }

    public BidNamePage clickSave() {
        getCommon().clickAndVerifyIsTrue(saveButton, "Unable to click save button as expected");
        return this;
    }

    public BidNamePage clickDiscard() {
        getCommon().clickAndVerifyIsTrue(discardButton, "Unable to click discard button as expected");
        return this;
    }

    public boolean isSaveButtonDisplayed() {
        return getCommon().isDisplayed(saveButton);
    }
}