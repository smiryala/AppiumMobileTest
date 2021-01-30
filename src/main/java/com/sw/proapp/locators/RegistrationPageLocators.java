package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.RegistrationPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationPageLocators extends MobileBasePage {

    public RegistrationPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = RegistrationPageData.CREATE_ACCOUNT_HEADER_IOS)
    @AndroidFindBy(id = RegistrationPageData.CREATE_ACCOUNT_HEADER)
    protected WebElement createAccountHeader;

    @iOSXCUITFindBy(xpath = RegistrationPageData.CURRENT_CUSTOMER_IOS)
    @AndroidFindBy(xpath = RegistrationPageData.CURRENT_CUSTOMER)
    protected WebElement currentCustomer;

    @iOSXCUITFindBy(xpath = RegistrationPageData.NEW_TO_SW_IOS)
    @AndroidFindBy(xpath = RegistrationPageData.NEW_TO_SW)
    protected WebElement newToSW;

    @iOSXCUITFindBy(xpath = RegistrationPageData.NAVIGATE_BACK_IOS)
    @AndroidFindBy(xpath = RegistrationPageData.NAVIGATE_BACK)
    protected WebElement navigateBack;

    @AndroidFindBy(xpath = RegistrationPageData.DESCRIPTION)
    protected WebElement description;

    @iOSXCUITFindBy(xpath = RegistrationPageData.DESCRIPTION_2_IOS)
    protected WebElement description_2_ios;

    @iOSXCUITFindBy(xpath = RegistrationPageData.DESCRIPTION_1_IOS)
    protected WebElement description_1_ios;

    @AndroidFindBy(xpath = RegistrationPageData.VISIT_LOCAL)
    protected WebElement visitLocal;

    @iOSXCUITFindBy(xpath = RegistrationPageData.VISIT_LOCAL_1_IOS)
    protected WebElement visitLocal_1_ios;

    @iOSXCUITFindBy(xpath = RegistrationPageData.VISIT_LOCAL_2_IOS)
    protected WebElement visitLocal_2_ios;

    @iOSXCUITFindBy(xpath = RegistrationPageData.BROWSER_URL_IOS)
    @AndroidFindBy(xpath = RegistrationPageData.BROWSER_URL)
    protected WebElement browserURL;

    @iOSXCUITFindBy(xpath = RegistrationPageData.DONE_IOS)
    protected WebElement doneLink;

    @AndroidFindBy(xpath = RegistrationPageData.BROWSER_URL_EDIT)
    protected WebElement browserURLEdit;

    @iOSXCUITFindBy(xpath = RegistrationPageData.YOURSHERWIN_IOS)
    protected WebElement youSherwinPro;

    @iOSXCUITFindBy(xpath = RegistrationPageData.LOCALREP_IOS)
    protected WebElement localRep;
    
    @iOSXCUITFindBy(xpath =  RegistrationPageData.LOCATION_ALLOW)
    protected WebElement locationAllowButton;
    
    @iOSXCUITFindBy(xpath =  RegistrationPageData.POPUP_OK)
    protected WebElement popup_ok;
    
    @iOSXCUITFindBy(xpath =  RegistrationPageData.PRIVACY_SETTINGS_OK)
    protected WebElement privacySetting_OK;
    
    
    //pros need pro popup
    @iOSXCUITFindBy(xpath =  RegistrationPageData.PROS_NEED_PRO_POPUP)
    protected WebElement close_popup_ok;
    
	   
    public WebElement getClose_popup_ok() {
		return close_popup_ok;
	}

	public void setClose_popup_ok(WebElement close_popup_ok) {
		this.close_popup_ok = close_popup_ok;
	}

	public WebElement getPopup_ok() {
		return popup_ok;
	}

	public void setPopup_ok(WebElement popup_ok) {
		this.popup_ok = popup_ok;
	}
	public WebElement getPrivacySetting_OK() {
		return privacySetting_OK;
	}

	public void setPrivacySetting_OK(WebElement privacySetting_OK) {
		this.privacySetting_OK = privacySetting_OK;
	}

	public WebElement getLocationAllowButton() {
		return locationAllowButton;
	}

	public void setLocationAllowButton(WebElement locationAllowButton) {
		this.locationAllowButton = locationAllowButton;
	}

	public WebElement getCreateAccountHeader() {
		return createAccountHeader;
	}

	public void setCreateAccountHeader(WebElement createAccountHeader) {
		this.createAccountHeader = createAccountHeader;
	}

	public WebElement getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(WebElement currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public WebElement getNewToSW() {
		return newToSW;
	}

	public void setNewToSW(WebElement newToSW) {
		this.newToSW = newToSW;
	}

	public WebElement getNavigateBack() {
		return navigateBack;
	}

	public void setNavigateBack(WebElement navigateBack) {
		this.navigateBack = navigateBack;
	}

	public WebElement getDescription() {
		return description;
	}

	public void setDescription(WebElement description) {
		this.description = description;
	}

	public WebElement getDescription_2_ios() {
		return description_2_ios;
	}

	public void setDescription_2_ios(WebElement description_2_ios) {
		this.description_2_ios = description_2_ios;
	}

	public WebElement getDescription_1_ios() {
		return description_1_ios;
	}

	public void setDescription_1_ios(WebElement description_1_ios) {
		this.description_1_ios = description_1_ios;
	}

	public WebElement getVisitLocal() {
		return visitLocal;
	}

	public void setVisitLocal(WebElement visitLocal) {
		this.visitLocal = visitLocal;
	}

	public WebElement getVisitLocal_1_ios() {
		return visitLocal_1_ios;
	}

	public void setVisitLocal_1_ios(WebElement visitLocal_1_ios) {
		this.visitLocal_1_ios = visitLocal_1_ios;
	}

	public WebElement getVisitLocal_2_ios() {
		return visitLocal_2_ios;
	}

	public void setVisitLocal_2_ios(WebElement visitLocal_2_ios) {
		this.visitLocal_2_ios = visitLocal_2_ios;
	}

	public WebElement getBrowserURL() {
		return browserURL;
	}

	public void setBrowserURL(WebElement browserURL) {
		this.browserURL = browserURL;
	}

	public WebElement getDoneLink() {
		return doneLink;
	}

	public void setDoneLink(WebElement doneLink) {
		this.doneLink = doneLink;
	}

	public WebElement getBrowserURLEdit() {
		return browserURLEdit;
	}

	public void setBrowserURLEdit(WebElement browserURLEdit) {
		this.browserURLEdit = browserURLEdit;
	}

	public WebElement getYouSherwinPro() {
		return youSherwinPro;
	}

	public void setYouSherwinPro(WebElement youSherwinPro) {
		this.youSherwinPro = youSherwinPro;
	}
}