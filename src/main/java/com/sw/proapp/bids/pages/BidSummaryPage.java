package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.BidSummaryPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BidSummaryPage extends BidSummaryPageLocators {

	public BidSummaryPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public boolean isLoggedInBidSummaryPageDispalyed() {
		getCommon().sleepFor(10);
		String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
		return pageSource.contains("bid summary") || pageSource.contains("resumen de cotización");
	}

	public BidSummaryPage clickBidNameCard() {
		getCommon().clickAndVerifyIsTrue(bidNameCard, "Unable to click the bid name card");
		return this;
	}
	
	public BidSummaryPage deleteLastAreaCard() {
        getCommon().clickAndVerifyIsTrue(areaMenus.get(areaMenus.size() - 1), "Unable to click last Area Menu");
        getCommon().clickAndVerifyIsTrue(deleteMenuCta, "Unable to click Area Menu Delete");
        getCommon().clickAndVerifyIsTrue(confirmDeleteButton, "Unable to click Delete Confirm Yes");
        sleepFor(1);
        return this;
    }

	public WebElement getBidNameCard() {
		return bidNameCard;
	}

	public boolean isBidNamePresent(String newName) {
		return getCommon().getDriver().getPageSource().contains(newName);
	}

	public BidSummaryPage clickAddLogoCta() {
		getCommon().clickAndVerifyIsTrue(addLogoCta, "Unable to click the add logo button");
		return this;
	}

	public boolean isProjectNotesPlaceholderTextDisplayed() {
		return getCommon().isDisplayed(projectNotesPlaceholderText);
	}

	public BidSummaryPage clickDeleteCta() {
		getCommon().clickAndVerifyIsTrue(deleteMenuCta, "Unable to click the delete menu item");
		return this;
	}

	public BidSummaryPage clickConfirmDeleteCta() {
		getCommon().clickAndVerifyIsTrue(confirmDeleteButton, "Unable to click the confirm delete button");
		return this;
	}

	public WebElement getTotalCostElement() {
		return totalCost;
	}

	public List<WebElement> getAreaSubtotalValues() {
		return areaSubtotalValues;
	}

	public WebElement getProjectSubtotalValue() {
		return projectSubtotalValue;
	}

	public WebElement getProjectNotesCard() {
		return projectNotesCard;
	}

	public WebElement getProjectNotesPlaceholderText() {
		return projectNotesPlaceholderText;
	}

	public boolean isBidExpirationCardDisplayed() {
		return getCommon().isDisplayed(bidExpirationCTA);
	}

	public BidSummaryPage clickProjectInfoCard() {
		getCommon().clickAndVerifyIsTrue(projectInfoCard, "Unable to click the project info card");
		return this;
	}

	public BidSummaryPage clickProjectNotesCard() {
		getCommon().clickAndVerifyIsTrue(projectNotesCard, "Unable to click the project notes card");
		return this;
	}

	public BidSummaryPage clickNavigateUp() {
		getCommon().clickAndVerifyIsTrue(navigateUp, "Unable to click the navigate up button");
		return this;
	}

	public boolean totalCostIsVisible() {
		return getCommon().isDisplayed(totalCostValue);
	}

	public List<WebElement> getAreaTitles() {
		return areaTitles;
	}

	public List<WebElement> getAreaSubtotals() {
		return areaSubtotals;
	}

	public List<WebElement> getAreaMenus() {
		return areaMenus;
	}

	public WebElement getProjectSubtotal() {
		return projectSubtotalValue;
	}

	public WebElement getDiscountValue() {
		return discountValue;
	}

	public WebElement getTaxValue() {
		return taxValue;
	}

	public WebElement getTotalCost() {
		return totalCostValue;
	}

	public WebElement getNewAreaCTA() {
		return newAreaCTA;
	}

	public boolean isCreatedByValueDisplayed() {
		return getCommon().isDisplayed(createdByValue);
	}

	public boolean isProEmailValueDisplayed() {
		return getCommon().isDisplayed(proEmailValue);
	}

	public boolean isPhoneNumberValueDisplayed() {
		return getCommon().isDisplayed(phoneNumberValue);
	}

	public boolean isBidNumberValueDisplayed() {
		return getCommon().isDisplayed(bidNumberValue);
	}

	public boolean isCreatedOnValueDisplayed() {
		return getCommon().isDisplayed(createdOnValue);
	}

	public boolean isLastModifiedValueDisplayed() {
		return getCommon().isDisplayed(lastModifiedValue);
	}

	public boolean isPricingMethodTagDisplayed() {
		return getCommon().isDisplayed(pricingMethodTag);
	}

	public boolean isBusinessNameValueDisplayed() {
		return getCommon().isDisplayed(businessNameValue);
	}

	public boolean isDurationCardDataDisplayed() {
		return getCommon().isDisplayed(durationCardData);
	}

	public boolean isTasksLabelDisplayed() {
		return getCommon().isDisplayed(tasksLabel);
	}

	public boolean isProductsLabelDisplayed() {
		return getCommon().isDisplayed(productsLabel);
	}

	public boolean isNotesLabelDisplayed() {
		return getCommon().isDisplayed(notesLabel);
	}

	public BidSummaryPage clickNewAreaCTA() {
		getCommon().clickAndVerifyIsTrue(newAreaCTA, "Unable to click the New Area button");
		return this;
	}

	public BidSummaryPage clickBidExpirationCTA() {
		getCommon().clickAndVerifyIsTrue(bidExpirationCTA, "Unable to click the bid expiration button");
		return this;
	}

	public boolean isBidExpirationPlaceholderDisplayed() {
		return getCommon().isDisplayed(bidExpirationPlaceholder);
	}

	public boolean isBidPricingMethodPillDisplayed() {
		getCommon().sleepFor(3);
		return getCommon().isDisplayed(bidPricingMethodPill);
	}

	public boolean isProjectInfoNameLabelDisplayed() {
		return getCommon().isDisplayed(projectInfoNameLabel);
	}

	public boolean isProjectInfoEmailLabelDisplayed() {
		return getCommon().isDisplayed(projectInfoEmailLabel);
	}

	public boolean isProjectInfoPhoneLabelDisplayed() {
		return getCommon().isDisplayed(projectInfoPhoneLabel);
	}

	public boolean isProjectInfoAddressLabelDisplayed() {
		return getCommon().isDisplayed(projectInfoAddressLabel);
	}

	public boolean isProjectDurationCardDisplayed() {
		return getCommon().isDisplayed(projectDurationCard);
	}

	public BidSummaryPage clickProjectDurationCard() {
		getCommon().clickAndVerifyIsTrue(projectDurationCard, "Unable to click project duration card as expected");
		return this;
	}

	public boolean isProjectDurationPlaceholderDisplayed() {
		return getCommon().isDisplayed(projectDurationPlaceholderText);
	}

	public WebElement getBidExpirationCta() {
		return bidExpirationCTA;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public boolean isCreatedByLabelDisplayed() {
		return getCommon().isDisplayed(createdByLabel);
	}

	public boolean isProEmailLabelDisplayed() {
		return getCommon().isDisplayed(proEmailLabel);
	}

	public boolean isPhoneNumberLabelDisplayed() {
		return getCommon().isDisplayed(phoneNumberLabel);
	}

	public boolean isBidNumberLabelDisplayed() {
		return getCommon().isDisplayed(bidNumberLabel);
	}

	public boolean isCreatedOnLabelDisplayed() {
		return getCommon().isDisplayed(createdOnLabel);
	}

	public boolean isSaveButtonDisplayed() {
		return getCommon().isDisplayed(saveButton);
	}

	public boolean isPreviewButtonDisplayed() {
		return getCommon().isDisplayed(previewButton);
	}

	public boolean isLastModifiedLabelDisplayed() {
		return getCommon().isDisplayed(lastModifiedLabel);
	}

	public void ClickPreviewButton(String language) {
		scrollToElementByText("PREVIEW°VISTA PREVIA", "Project Info", language);
		clickElementByText("PREVIEW°VISTA PREVIA", language);
	}

	public void clickEditPencilBidName(String language) {

		if (getCommon().getDriver() instanceof IOSDriver) {
			clickElementByElementName("editWhite", language);
		} else {
			clickElementbyXpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]");
		}
	}

	public boolean isNewAreaCTADisplayed() {
		return getCommon().isDisplayed(newAreaCTA);
	}

	public boolean isProductsCardDisplayed() {
		return getCommon().isDisplayed(productsCard);
	}

	public boolean isProductsCardTitleDisplayed() {
		return getCommon().isDisplayed(productsCardTitleLabel);
	}

	public boolean isProductsCardDetailDisplayed() {
		return getCommon().isDisplayed(productsCardDetailLabel);
	}

	public boolean isViewAllProductsCtaDisplayed() {
		return getCommon().isDisplayed(productsCardViewAllProductsCta);
	}

	public BidSummaryPage clickViewAllProducts() {
		getCommon().clickAndVerifyIsTrue(productsCardViewAllProductsCta, "Unable to click view all products cta");
		return this;
	}

	public boolean isProductNameLabelDisplayed() {
		return getCommon().isDisplayed(productsCardProductNameLabel);
	}

	public boolean clickOnImageOnGoogleDriver() {
		if (getCommon().getDriver() instanceof IOSDriver) {

			return true;
		} else {
			clickElementbyXpath(
					"//android.widget.LinearLayout[@content-desc=\"image.jpg, 17.65 kB, Oct 13\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView");
			return true;
		}
	}

	public boolean clickOnImageLogoInSummaryPage() {
		if (getCommon().getDriver() instanceof IOSDriver) {

			return true;
		} else {
			clickElementbyXpath("//android.widget.ImageView[@content-desc=\"Image\"]");
			return true;
		}
	}

	public boolean clickOnNewImageOnGoogleDriver() {
		if (getCommon().getDriver() instanceof IOSDriver) {

			return true;
		} else {
			System.out.println(getDriver().findElementsByXPath(
					"//android.widget.LinearLayout[@content-desc=\"2b9527google-logo.jpg, 56.50 kB, Oct 28\"]/android.widget.RelativeLayout")
					.size());
			clickElementbyXpath(
					"//android.widget.LinearLayout[@content-desc=\"2b9527google-logo.jpg, 56.50 kB, Oct 28\"]/android.widget.RelativeLayout");
			return true;
		}
	}

	public boolean imageDisplayedInSummary() {
		if (getCommon().getDriver() instanceof IOSDriver) {

			return true;
		} else {
			if (getDriver().findElementsByXPath("//android.widget.ImageView[@content-desc=\"Image\"]").size() == 2) {// ("com.sherwin.probuyplus.debug:id/bid_logo_image").click();)
				System.out.println("-----------------------number of images in summary: " + getDriver()
						.findElementsByXPath("//android.widget.ImageView[@content-desc=\"Image\"]").size());
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean validateAccountInformationFieldIsDisplayed(String text) {
		if (getCommon().getDriver() instanceof IOSDriver) {
			String value = "";
			switch (text) {
			case "CREATED BY":
				value = getDriver().findElementById("com.sherwin.probuyplus.debug:id/createdByValue").getText();
				if (value.replace(" ", "").equals("")) {
					return false;
				} else {
					return true;
				}
			case "PRO EMAIL":
				value = getDriver().findElementById("com.sherwin.probuyplus.debug:id/proEmailValue").getText();
				if (value.replace(" ", "").equals("")) {
					return false;
				} else {
					return true;
				}
			case "PRO PHONE NUMBER":
				value = getDriver().findElementById("com.sherwin.probuyplus.debug:id/phoneNumberValue").getText();
				if (value.replace(" ", "").equals("")) {
					return false;
				} else {
					return true;
				}
			case "CREATED ON":
				value = getDriver().findElementById("com.sherwin.probuyplus.debug:id/createdOnValue").getText();
				if (value.replace(" ", "").equals("")) {
					return false;
				} else {
					return true;
				}

			case "LAST MODIFIED":
				value = getDriver().findElementById("com.sherwin.probuyplus.debug:id/lastModifiedValue").getText();
				if (value.replace(" ", "").equals("")) {
					return false;
				} else {
					return true;
				}
			default:
				return false;
			}

		} else {
			String value = getTextByXpath(
					"/XCUIElementTypeStaticText[@name=\"" + text + "\"]/following-sibling::XCUIElementTypeStaticText");
			if (value.replace(" ", "").equals("")) {
				return false;
			} else {
				return true;
			}
		}
	}
}