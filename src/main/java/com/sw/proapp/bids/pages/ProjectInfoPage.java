package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.ProjectInfoPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ProjectInfoPage extends ProjectInfoPageLocators {

	public ProjectInfoPage(Common common) {
		super(common);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	public boolean isLoogedInProjectInfoPageDispalyed() {
		getCommon().sleepFor(10);
		String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
		return pageSource.contains("project info") || pageSource.contains("información del proyecto");
	}

	public ProjectInfoPage enterProjectInfo() {
		getCommon().type(firstNameInput, "Rick");
		getCommon().type(lastNameInput, "James");
		getCommon().type(emailInput, "some@where.com");
		getCommon().type(phoneInput, "1234567890");
		getCommon().click(nextButton, "Unable to click next button");

		getCommon().type(address1Input, "123 Main St");
		getCommon().type(address2Input, "Apt 21");
		getCommon().type(cityInput, "Flavortown");
		getCommon().click(stateDropdown, "Unable to click state dropdown");
		getCommon().click(dropdownDoneButton, "Unable to click dropdown done button");
		getCommon().type(zipInput, "12345");
		getCommon().click(nextButton, "Unable to click next button");

		return this;
	}

	public ProjectInfoPage enterProjectInfo(String language) {
		String firstName = getTextByLanguage("First Name°Nombre", language);
		String lastName = getTextByLanguage("Last Name°Apellido", language);
		String email = getTextByLanguage("Email°Correo eléctronico", language);
		String phone = getTextByLanguage("phone°Teléfono", language);
		enterTextToElementByText(firstName, "Rick");
		enterTextToElementByText(lastName, "James");
		enterTextToElementByText("Email", "some@where.com");
		if (getCommon().getDriver() instanceof IOSDriver) {
			enterTextToElementByText("Phone Number", "5054659277");
			clickElementByElementName("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			String address = getTextByLanguage("Address°Dirección", language);
			String city = getTextByLanguage("City°Ciudad", language);
			String zip = getTextByLanguage("Zip°Código Postal", language);
			String state = getTextByLanguage("State°Estado", language);
			enterTextToElementByText(address, "8302 Lincoln ln");
			enterTextToElementByText(city, "Mclean");
			enterTextToElementByText(zip, "22102");
			clickElementByText(state, language);
			selectFromIosWheelerByText("VA", language);
		} else {
			enterTextToElementByText(phone, "5054659277");
			clickElementByElementName("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			String address = getTextByLanguage("address°Dirección", language);
			String city = getTextByLanguage("city°Ciudad", language);
			String zip = getTextByLanguage("zip°Código Postal", language);
			String state = getTextByLanguage("State°Estado", language);
			enterTextToElementByText(address, "8302 Lincoln ln");
			enterTextToElementByText(city, "Mclean");
			enterTextToElementByText(zip, "22102");
			clickElementByElementName(state, language);
			scrollToElementByText("Virginia", "Arkansas", language);
			clickElementByElementName("Virginia", language);
		}
		clickElementByElementName("DONE°LISTO", language);
		//clickElementByElementName("DONE°LISTO", language);
		//clickElementByElementName("DONE°LISTO", language);
		return this;
	}
}