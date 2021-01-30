package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.AccountPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBys;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
public class AccountPageLocators extends MobileBasePage {

    public AccountPageLocators(Common common) {
        super(common);
    }

    //@iOSXCUITFindBy(xpath = AccountPageData.ACCOUNT_HOME_BUTTON_IOS)
    //@AndroidFindBy(id = AccountPageData.ACCOUNT_HOME_BUTTON)
    @iOSXCUITFindBy(iOSNsPredicate =  AccountPageData.ACCOUNT_HOME_BUTTON_IOS)
    @AndroidFindBy(xpath = AccountPageData.ACCOUNT_HOME_BUTTON)
    private WebElement accountHomeButton;

   // @iOSXCUITFindBy(xpath = AccountPageData.ACCOUNT_SETTING_BUTTON_IOS)
   @iOSXCUITFindAll({
           @iOSXCUITBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"MY ACCOUNT\" or @name=\"MI CUENTA\"]/XCUIElementTypeButton[1]"),
           @iOSXCUITBy(xpath = AccountPageData.ACCOUNT_SETTING_BUTTON_IOS)
   })
    @AndroidFindBy(id = AccountPageData.ACCOUNT_SETTING_BUTTON)
    private WebElement accountSettingsButton;

    @iOSXCUITFindBy(accessibility = AccountPageData.QA_DASHBOARD_BUTTON_IOS)
    @AndroidFindBy(id = AccountPageData.QA_DASHBOARD_BUTTON)
    private WebElement qa_dashboard_button;

    @iOSXCUITFindBy(id = AccountPageData.SERVICE_ENDPOINTS)
    @AndroidFindBy(id = AccountPageData.SERVICE_ENDPOINTS)
    protected WebElement service_endpoint_select;

    @iOSXCUITFindBy(accessibility = AccountPageData.API_ENVIRONMENTS)
    @AndroidFindBy(id = AccountPageData.API_ENVIRONMENTS)
    protected WebElement api_environment;

    @AndroidFindBy(xpath = AccountPageData.QAX_TEST_ENDPOINT)
    protected WebElement qax_EndPoint;

    @iOSXCUITFindBy(accessibility =  AccountPageData.QAX_SELECT_IOS)
    @AndroidFindBy(id = AccountPageData.QAX_SELECT_IOS)
    protected WebElement qa_select_ios;

    @iOSXCUITFindBy(xpath = AccountPageData.OK_ENVIRONMENT_IOS)
    protected WebElement ok_environment_button_ios;

    @iOSXCUITFindBy(xpath = AccountPageData.REVTRAX_SERVICE_ENDPOINTS)
    @AndroidFindBy(xpath = AccountPageData.REVTRAX_SERVICE_ENDPOINTS)
    protected WebElement revtrax_service_endpoint_select;

    @iOSXCUITFindBy(id = AccountPageData.BACK_IOS)
    @AndroidFindBy(xpath = AccountPageData.BACK_BUTTON)
    private WebElement backButton;

    @iOSXCUITFindBy(xpath = AccountPageData.SIGNOUT_IOS)
    @AndroidFindBy(id = AccountPageData.SIGNOUT)
    private WebElement signOut;

    @iOSXCUITFindBy(id = AccountPageData.CLOSE_SETTINGS_IOS)
    @AndroidFindBy(id = AccountPageData.CLOSE_SETTINGS_IOS)
    protected WebElement close_settings_ios;

    public WebElement getClose_settings_ios() {
        return close_settings_ios;
    }

    public void setClose_settings_ios(WebElement close_settings_ios) {
        this.close_settings_ios = close_settings_ios;
    }

    public WebElement getApi_environment() {
        return api_environment;
    }

    public void setApi_environment(WebElement api_environment) {
        this.api_environment = api_environment;
    }

    public WebElement getQa_select_ios() {
        return qa_select_ios;
    }

    public void setQa_select_ios(WebElement qa_select_ios) {
        this.qa_select_ios = qa_select_ios;
    }

    public WebElement getQax_EndPoint() {
        return qax_EndPoint;
    }

    public void setQax_EndPoint(WebElement qax_EndPoint) {
        this.qax_EndPoint = qax_EndPoint;
    }

    public WebElement getSignOut() {
        return signOut;
    }

    public void setSignOut(WebElement signOut) {
        this.signOut = signOut;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public void setBackButton(WebElement backButton) {
        this.backButton = backButton;
    }

    public WebElement getService_endpoint_select() {
        return service_endpoint_select;
    }

    public void setService_endpoint_select(WebElement service_endpoint_select) {
        this.service_endpoint_select = service_endpoint_select;
    }

    public WebElement getRevtrax_service_endpoint_select() {
        return revtrax_service_endpoint_select;
    }

    public void setRevtrax_service_endpoint_select(WebElement revtrax_service_endpoint_select) {
        this.revtrax_service_endpoint_select = revtrax_service_endpoint_select;
    }

    public WebElement getQa_dashboard_button() {
        return qa_dashboard_button;
    }

    public void setQa_dashboard_button(WebElement qa_dashboard_button) {
        this.qa_dashboard_button = qa_dashboard_button;
    }

    public WebElement getAccountSettingsButton() {
        return accountSettingsButton;
    }

    public void setAccountSettingsButton(WebElement accountSettingsButton) {
        this.accountSettingsButton = accountSettingsButton;
    }

    public WebElement getAccountHomeButton() {
        return accountHomeButton;
    }

    public void setAccountHomeButton(WebElement accountHomeButton) {
        this.accountHomeButton = accountHomeButton;
    }

    public WebElement getOk_environment_button_ios() {
        return ok_environment_button_ios;
    }
}