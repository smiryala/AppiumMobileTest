package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.data.CartPageData;
import com.sw.proapp.locators.CartPageLocators;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends CartPageLocators {

	public CartPage(Common common, String language) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
		this.languageBase=language;
	}

	@Step("Removing items from iOS app cart")
	public CartPage removeItems() throws IOException {
		boolean elementStatus = true;
		while (elementStatus) {
			//elementStatus = mobileMoveTo(getDriver(), getRemoveButtonBy(), getContinueShoppingBy());
			scrollToElementByText("REMOVE°ELIMINAR",languageBase);
			elementStatus = elementExistsByText("REMOVE°ELIMINAR", languageBase);
			ExtentTestManager.getTest().log(Status.INFO, "Emptying Cart");
			if (elementStatus) {
				for (WebElement item : getRemoveButton()) {
					clickElementByText("REMOVE°ELIMINAR", languageBase);
				}
			}
		}
		clickBackButton();
		return this;
	}

	@Step()
	public CartPage removeItemsForIOS() throws IOException {
		getCommon().sleepFor(5);
		int items = 0;
		while (items == 0) {
			List<WebElement> elements = getDriver()
					.findElements(MobileBy.iOSNsPredicateString(CartPageData.PRO_REMOVE_CART_LIST));
			System.out.println("elements" + elements.size());
			items = elements.size();
			ExtentTestManager.getTest().log(Status.INFO, "Emptying Cart");
			if (items > 1) {
				System.out.println("element " + elements.get(0));
				elements.get(0).click();
				getCommon().sleepFor(5);
				items = 0;
			} else {
				items = 1;
			}

		}
		getCommon().sleepFor(10);
		scrollDown3();
		if (existsElement(getRemoveButtonSingleElement())) {
			getCommon().clickAndVerifyIsTrue(getRemoveButtonSingleElement(),
					"Unable to click on Remove");
			getCommon().sleepFor(5);
		}
		for(int i= 0;i<10;i++) {
			String delete = getTextByLanguage("DELETE°ELIMINAR", languageBase);
			if(elementExistsByContainTextAndSpaceIOS(delete, delete)  ) {
				elementClickByContainTextAndSpaceIOS(delete, delete);
			}
			else {
				break;
			}
		}
		closeButtonInCartPage();
		return this;
	}

	@Step()
	public CartPage closeButtonInCartPage() {
		getCommon().sleepFor(2);
		getCommon().clickAndVerifyIsTrue(getClose(), "Unable to click close button");
		return this;
	}
}