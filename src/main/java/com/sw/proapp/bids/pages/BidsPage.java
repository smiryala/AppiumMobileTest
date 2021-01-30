package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidsPageLocators;
import com.sw.proapp.locators.HomePageLocators;
import com.sw.proapp.pages.HomePage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BidsPage extends BidsPageLocators {

	public BidsPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public boolean isLoogedInBidsPageDispalyed() {
		getCommon().sleepFor(10);
		String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
		return pageSource.contains("project bids") || pageSource.contains("cotizaciones");
	}

	public BidsPage clickBidCardHeader() {
		getCommon().clickAndVerifyIsTrue(bidCardHeader, "Unable to click bid card.");
		return this;
	}

	public BidsPage clickCreateNewBidButton() {
		getCommon().clickAndVerifyIsTrue(createNewBidButton, "Unable to click create new bid button.");
		return this;
	}

	public BidsPage clickCreateNewBidButtonLarge() {
		getCommon().clickAndVerifyIsTrue(createNewBidButtonLarge, "Unable to click large create new bid button");
		return this;
	}

	public BidsPage clickStatusDropdown() {
		getCommon().clickAndVerifyIsTrue(statusDropDown, "Unable to click the status dropdown");
		return this;
	}

	public BidsPage clickDraftsStatus() {
		getCommon().clickAndVerifyIsTrue(draftsStatus, "Unable to click the draft status");
		return this;
	}

	public WebElement getBidCard() {
		return bidCardHeader;
	}

	public WebElement getStatusDropDown() {
		return statusDropDown;
	}

	public boolean isViewAllStatusVisable() {
		return getCommon().isDisplayed(viewAllStatus);
	}

	public boolean isDraftsStatusVisable() {
		return getCommon().isDisplayed(draftsStatus);
	}

	public boolean isPendingStatusVisable() {
		return getCommon().isDisplayed(pendingStatus);
	}

	public boolean isAcceptedStatusVisable() {
		return getCommon().isDisplayed(acceptedStatus);
	}

	public boolean isRejectedStatusVisable() {
		return getCommon().isDisplayed(rejectedStatus);
	}

	public boolean isClosedStatusVisable() {
		return getCommon().isDisplayed(closedStatus);
	}

	public boolean isNoResultsFoundDisplayed() {
		try {
			noResultsFound.isDisplayed();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public BidsPage clickEditCta() {
		getCommon().clickAndVerifyIsTrue(editMenuCta, "Unable to click the edit menu item");
		return this;
	}

	public BidsPage clickDuplicateCta() {
		getCommon().clickAndVerifyIsTrue(duplicateMenuCta, "Unable to click the duplicate menu item");
		return this;
	}

	public BidsPage clickDeleteCta() {
		getCommon().clickAndVerifyIsTrue(deleteMenuCta, "Unable to click the delete menu item");
		return this;
	}

	public BidsPage clickConfirmDeleteCta() {
		getCommon().clickAndVerifyIsTrue(confirmDeleteButton, "Unable to click the confirm delete button");
		return this;
	}

	public List<WebElement> getBidHeaderMenuCta() {
		return bidHeaderMenuCta;
	}

	public List<WebElement> getBidHeaderTitles() {
		return bidHeaderTitles;
	}

	public String getFirstBidOnList(String textToWait) {
		if (getCommon().getDriver() instanceof IOSDriver) {
			iOSWaitForElementByText(textToWait, "unable to find element with text: " + textToWait);
			String bidName = getElementByTypeAndIndex("XCUIElementTypeStaticText", 3).getText();
			return bidName;
		} else {
			androidWaitForElementByText(textToWait, "unable to find element with text: " + textToWait);
			String bidName = getElementByTypeAndIndex("android.widget.TextView", 2).getText();
			return bidName;
		}
	}

	public void clickOnFirstBidOfTheList(String language) {
		String firstBidLabels = "CREATED ON°CREADA EL";
		clickFirstElementByText(firstBidLabels, language);
	}
}