package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.LoginPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPageLocators extends MobileBasePage {

    public LoginPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = LoginPageData.SIGNIN_BUTTON_IOS)
    @AndroidFindBy(xpath = LoginPageData.SIGNIN_BUTTON)
    private WebElement signInButton;
    
    @iOSXCUITFindBy(xpath = LoginPageData.LOGIN_SUBMIT_BUTTON_IOS)
    @AndroidFindBy(xpath = LoginPageData.SIGNIN_BUTTON)
    private WebElement loginSubmitButton;



	@iOSXCUITFindBy(xpath = LoginPageData.USERNAME_IOS)
    @AndroidFindBy(id = LoginPageData.USERNAME)
    protected WebElement username;

    @iOSXCUITFindBy(xpath = LoginPageData.PASSWORD_IOS)
    @AndroidFindBy(id = LoginPageData.PASSWORD)
    protected WebElement password;

    @iOSXCUITFindBy(xpath = LoginPageData.NO_THANKS_IOS)
    protected WebElement noThanks_IOS;

    @iOSXCUITFindBy(xpath = LoginPageData.DONT_ALLOW_IOS)
    protected WebElement dontAllow_IOS;

    @AndroidFindBy(xpath = LoginPageData.SHERWIN_WILLIAMS_PRO)
    @iOSXCUITFindBy(xpath = LoginPageData.SHERWIN_WILLIAMS_PRO_IOS)
    private WebElement homePageName;

    @iOSXCUITFindBy(xpath = LoginPageData.EMAIL_ERROR_IOS)
    @AndroidFindBy(id = LoginPageData.EMAIL_ERROR)
    protected WebElement emailErrorMessage;

   // @iOSXCUITFindBy(xpath = LoginPageData.SIGNIN_ERROR_HEADER_IOS)
   // @AndroidFindBy(xpath = LoginPageData.SIGNIN_ERROR_HEADER)
   // protected WebElement signInErrorHeader;

    @iOSXCUITFindBy(xpath = LoginPageData.SIGNIN_ERROR_IOS)
    @AndroidFindBy(xpath = LoginPageData.SIGNIN_ERROR)
    protected WebElement signInErrorMessage;

    @iOSXCUITFindBy(xpath = LoginPageData.TRY_AGAIN_IOS)
    @AndroidFindBy(xpath = LoginPageData.TRY_AGAIN)
    protected WebElement tryAgain;

    @iOSXCUITFindBy(xpath = LoginPageData.NEED_A_LOGIN_IOS)
    @AndroidFindBy(xpath = LoginPageData.NEED_A_LOGIN)
    protected WebElement needALogin;

    @iOSXCUITFindBy(accessibility = LoginPageData.SIGN_IN_LATER_IOS)
    @AndroidFindBy(id = LoginPageData.SIGN_IN_LATER)
    protected WebElement signInLater;



	@AndroidFindBy(xpath = LoginPageData.POPUP_OK)
    protected WebElement pop_ok;

    @AndroidFindBy(id = LoginPageData.CHECKOUT_PASSWORD)
    protected WebElement checkoutPassword;

    @AndroidFindBy(id = LoginPageData.CHECKOUT_PASSWORD_OK)
    protected WebElement checkoutPasswordOK;

    @iOSXCUITFindBy(xpath = LoginPageData.ACK_IOS)
    @AndroidFindBy(xpath = LoginPageData.ACK)
    protected WebElement acknowledge;

   

	@iOSXCUITFindBy(xpath = LoginPageData.NOTIFICATION_NO_THANKS_IOS)
    protected WebElement notification_NoThanks_IOS;
    
    @iOSXCUITFindBy(xpath = LoginPageData.REMAINDER_ME_LATER_IOS)
    protected WebElement remaindMeLater_IOS;
    
    public WebElement getNotification_NoThanks_IOS() {
		return notification_NoThanks_IOS;
	}

	public void setNotification_NoThanks_IOS(WebElement notification_NoThanks_IOS) {
		this.notification_NoThanks_IOS = notification_NoThanks_IOS;
	}

	public WebElement getRemaindMeLater_IOS() {
		return remaindMeLater_IOS;
	}

	public void setRemaindMeLater_IOS(WebElement remaindMeLater_IOS) {
		this.remaindMeLater_IOS = remaindMeLater_IOS;
	}

	public WebElement getCheckoutPasswordOK() {
        return checkoutPasswordOK;
    }

    public void setCheckoutPasswordOK(WebElement checkoutPasswordOK) {
        this.checkoutPasswordOK = checkoutPasswordOK;
    }

    /**
     * @return the checkoutPassword
     */
    public WebElement getCheckoutPassword() {
        return checkoutPassword;
    }

    /**
     * @param checkoutPassword the checkoutPassword to set
     */
    public void setCheckoutPassword(WebElement checkoutPassword) {
        this.checkoutPassword = checkoutPassword;
    }

    public WebElement getPop_ok() {
        return pop_ok;
    }

    public void setPop_ok(WebElement pop_ok) {
        this.pop_ok = pop_ok;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public void setSignInButton(WebElement signInButton) {
        this.signInButton = signInButton;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getNoThanks_IOS() {
        return noThanks_IOS;
    }

    public void setNoThanks_IOS(WebElement noThanks_IOS) {
        this.noThanks_IOS = noThanks_IOS;
    }

    public WebElement getDontAllow_IOS() {
        return dontAllow_IOS;
    }

    public void setDontAllow_IOS(WebElement dontAllow_IOS) {
        this.dontAllow_IOS = dontAllow_IOS;
    }

    public WebElement getHomePageName() {
        return homePageName;
    }

    public boolean isHomePageDisplayed() {
        String pageSource = getCommon().getDriver().getPageSource();
        return pageSource.contains("SHERWIN‑WILLIAMS® PRO");
    }

    public void setHomePageName(WebElement homePageName) {
        this.homePageName = homePageName;
    }
    
    public WebElement getAcknowledge() {
		return acknowledge;
	}

	public void setAcknowledge(WebElement acknowledge) {
		this.acknowledge = acknowledge;
	}

	public WebElement getLoginSubmitButton() {
		return loginSubmitButton;
	}

	public void setLoginSubmitButton(WebElement loginSubmitButton) {
		this.loginSubmitButton = loginSubmitButton;
	}
	

    public WebElement getNeedALogin() {
		return needALogin;
	}

	public void setNeedALogin(WebElement needALogin) {
		this.needALogin = needALogin;
	}


	public WebElement getSignInLater() {
		return signInLater;
	}

	public void setSignInLater(WebElement signInLater) {
		this.signInLater = signInLater;
	}
}