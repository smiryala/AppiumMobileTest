package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidDiscountPageLocators;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BidDiscountPage extends BidDiscountPageLocators {

	public BidDiscountPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public void enterValueOnDiscountField(String value) {
		getCommon().click(bidDiscountField, "unable to click discount field");
		getCommon().typeAndVerifyIsTrue(bidDiscountField, value, "Could not type value on discount field");
		getDriver().navigate().back();
	}

	public void clickDiscountUnitElement(String language) {
		String text = "BID DISCOUNT°DESCUENTO EN ESTIMADO";
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to wait for element with text" + text);
				getCommon().click(getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")),
						"unable to click element with text" + text);
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to wait for element with text" + text);
				getCommon().click(bidDiscountUnit, "unable to click discount unit element");
			}
	}
}