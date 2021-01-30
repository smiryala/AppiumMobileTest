package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaNamePageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewAreaNamePage extends NewAreaNamePageLocators {

    public NewAreaNamePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isNewAreaNamePageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("area name") || pageSource.contains("NOMBRE DEL ÁREA".toLowerCase());
    }

    public WebElement getAreaNameField() {
        return nameInput;
    }

    public WebElement getContinueCta() {
        return continueCta;
    }

    public NewAreaNamePage enterAreaName(String input) {
        getCommon().type(getAreaNameField(), input);
        return this;
    }

    public NewAreaNamePage clickContinueCta() {
        getCommon().clickAndVerifyIsTrue(continueCta, "Unable to click the continue button");
        return this;
    }
    
    public NewAreaNamePage clickContinueCta(String language) {
    	if (getCommon().getDriver() instanceof IOSDriver) {
    	clickElementByElementName("done_button", language);
    		return this;
    	}
    	else {
        getCommon().clickAndVerifyIsTrue(continueCta, "Unable to click the continue button");
        return this;
    	}
    }

    public NewAreaNamePage clickDiscardCta() {
        getCommon().clickAndVerifyIsTrue(discardCTA, "Unable to click the continue button");
        return this;
    }
}