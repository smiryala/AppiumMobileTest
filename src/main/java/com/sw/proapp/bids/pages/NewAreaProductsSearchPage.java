package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaProductsSearchPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewAreaProductsSearchPage extends NewAreaProductsSearchPageLocators {

	public NewAreaProductsSearchPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}
	
	public NewAreaProductsSearchPage clickDeleteConfirmYes() throws InterruptedException {
		getCommon().clickAndVerifyIsTrue(deleteConfirmYes, "Unable to click delete confirm yes");
		sleepFor(2);
		return this;
	}
	
	public NewAreaProductsSearchPage clickSaveAndGo() {
		if (getCommon().getDriver() instanceof IOSDriver)
			elementClickByContainTextAndSpaceIOS("Save & Go to Summary", "Save");
		else
			getCommon().clickAndVerifyIsTrue(saveAndGo, "Unable to click Save & Go to Summary");
		return this;
	}
	
	public List<WebElement> getDeleteButtons() {
		return itemDeleteButtons;
	}
	
	public NewAreaProductsSearchPage clickFirstProductDelete() {
		getCommon().clickAndVerifyIsTrue(itemDeleteButtons.get(0), "Unable to click first product delete");
		return this;
	}
	
	public void enterProductName(String input) {
		getCommon().type(getSearchField(), input, true);
	}

	public boolean isNewAreaProductsSearchPageDisplayed() {
		getCommon().sleepFor(10);
		String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
		return pageSource.contains("area product search")
				|| pageSource.contains("BÚSQUEDA DE PRODUCTOS PARA ÁREA".toLowerCase());
	}

	public NewAreaProductsSearchPage clickContinueCta() {
		getCommon().clickAndVerifyIsTrue(continueCta, "Unable to click the continue button");
		return this;
	}

	public NewAreaProductsSearchPage clickSearchProductsCta() {
		getCommon().clickAndVerifyIsTrue(searchProductsCta, "Unable to click search products cta");
		return this;
	}

	public WebElement getSearchField() {
		return searchProductsSearchField;
	}

	public NewAreaProductsSearchPage clickDeleteButtonByIndex(int index) {
		if (getCommon().getDriver() instanceof IOSDriver) {
			clickElementbyXpath("//XCUIElementTypeButton[@name=\"delete\"]", index);
			return this;
		} else {
			clickElementbyXpath("//android.widget.ImageButton[@content-desc=\"Delete\"]", index);
			return this;
		}
	}

	public NewAreaProductsSearchPage clickAddFirstProduct() {
		getCommon().clickAndVerifyIsTrue(productSearchFirstItemAddCta,
				"Unable to click add cta for first product search item");
		return this;
	}
}