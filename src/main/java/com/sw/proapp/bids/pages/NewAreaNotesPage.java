package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaNotesPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewAreaNotesPage extends NewAreaNotesPageLocators {

	public NewAreaNotesPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public boolean isNewAreaNotesPageDisplayed() {
		getCommon().sleepFor(10);
		String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
		return pageSource.contains("area notes") || pageSource.contains("NOTAS DEL ÁREA".toLowerCase());
	}

	public WebElement getContinueCta() {
		return continueCta;
	}

	public NewAreaNotesPage clickContinueCta(String language) {
		if (getCommon().getDriver() instanceof IOSDriver) {
			clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			return this;
		} else {
			clickElementByElementName("DONE°LISTO", language);
			return this;
		}
	}

	public WebElement getDiscardCta() {
		return discardCta;
	}

	public WebElement getNotesInputField() {
		return notesInputField;
	}

	public NewAreaNotesPage clickContinueCta() {
		getCommon().clickAndVerifyIsTrue(continueCta, "Unable to click the continue button");
		return this;
	}

	public NewAreaNotesPage clickDiscardCta() {
		getCommon().clickAndVerifyIsTrue(discardCta, "Unable to click the discard button");
		return this;
	}

	public NewAreaNotesPage enterNotes(String notes) {
		getCommon().typeAndVerifyIsTrue(notesInputField, notes, "Could not enter notes.");
		return this;
	}
}