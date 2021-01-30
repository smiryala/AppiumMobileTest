package com.sw.colorsnap.locators;

import com.sw.core.helpers.Common;
import com.sw.colorsnap.data.LoginPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPageLocators extends MobileBasePage {

    public LoginPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(id = LoginPageData.SIGNIN_BUTTON)
    private WebElement signInButton;

    @AndroidFindBy(id = LoginPageData.USERNAME)
    protected WebElement username;

    @AndroidFindBy(id = LoginPageData.PASSWORD)
    protected WebElement password;

    @AndroidFindBy(id = LoginPageData.FORGOTPASSWORD)
    protected WebElement forgotPassword;

    @AndroidFindBy(xpath = LoginPageData.HEADERTITLE)
    protected WebElement headerTitle;

    @AndroidFindBy(xpath = LoginPageData.BACKBUTTON)
    protected WebElement backButton;

    @AndroidFindBy(xpath = LoginPageData.IMAGE)
    protected WebElement image;

    @AndroidFindBy(id = LoginPageData.EMAILTEXT)
    protected WebElement emailText;

    @AndroidFindBy(id = LoginPageData.PASSWORDTEXT)
    protected WebElement passwordText;

    @AndroidFindBy(id = LoginPageData.REMEMBERMECHECKBOX)
    protected WebElement rememberCheckbox;

    @AndroidFindBy(id = LoginPageData.REGISTER)
    protected WebElement registerButton;

    @AndroidFindBy(id = LoginPageData.WHYREGISTER)
    protected WebElement whyRegisterButton;

    @AndroidFindBy(id = LoginPageData.SENDVERIFICATIONEMAIL)
    protected WebElement sendVerificationEmail;

    @AndroidFindBy(id = LoginPageData.CONTACTUS)
    protected WebElement contactUs;

    public WebElement getUserName() {
        return username;
    }

    public WebElement getPassWord() {
        return password;
    }

    public WebElement getLogInButton() {
        return signInButton;
    }

    public WebElement getHeaderTitle() {
        return headerTitle;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getImage() {
        return image;
    }

    public WebElement getEmailText() {
        return emailText;
    }

    public WebElement getPasswordText() {
        return passwordText;
    }

    public WebElement getRememberCheckbox() {
        return rememberCheckbox;
    }

    public WebElement getForgotPassword() {
        return forgotPassword;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public WebElement getWhyRegisterButton() {
        return whyRegisterButton;
    }
    public WebElement getSendVerificationEmail() {
        return sendVerificationEmail;
    }
    public WebElement getContactUs() {
        return contactUs;
    }
}