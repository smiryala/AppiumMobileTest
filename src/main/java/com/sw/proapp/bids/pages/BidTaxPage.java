package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidTaxPageLocators;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BidTaxPage extends BidTaxPageLocators {

	public BidTaxPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public void enterValueOnTaxField(String value) {
		getCommon().click(bidTAxField, "unable to click tax field");
		getCommon().typeAndVerifyIsTrue(bidTAxField, value, "Could not type value on tax field");
		getDriver().navigate().back();
	}
}