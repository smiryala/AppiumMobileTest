package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidSummaryPageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BidPreviewPage extends BidSummaryPageLocators {

	public BidPreviewPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public void clickComposeEmailFraftButton(String language) {
		clickElementByText("COMPOSE EMAIL DRAFT°CREAR BORRADOR DEL EMAIL", language);
	}
}