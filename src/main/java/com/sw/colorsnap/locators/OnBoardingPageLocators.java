package com.sw.colorsnap.locators;

import com.sw.core.helpers.Common;
import com.sw.colorsnap.data.OnBoardingPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;


public class OnBoardingPageLocators extends MobileBasePage {

    public OnBoardingPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSClassChain = OnBoardingPageData.ONBOARDING_LOGIN_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_LOGIN)
    protected WebElement loginButton;

    @iOSXCUITFindBy(iOSClassChain = OnBoardingPageData.ONBOARDING_SKIP_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_SKIP)
    protected WebElement skipButton;

    @iOSXCUITFindBy(iOSClassChain = OnBoardingPageData.ONBOARDING_PAGEINDICATOR_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_PAGEINDICATOR)
    protected WebElement pageIndicatorButton;

    @iOSXCUITFindBy(accessibility = OnBoardingPageData.ONBOARDING_IMG_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_IMG)
    protected WebElement imageIcon;

    @iOSXCUITFindBy(accessibility = OnBoardingPageData.ONBOARDING_PAP_TITLE_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_PAP_TITLE)
    protected WebElement titleContent;

    @iOSXCUITFindBy(iOSClassChain = OnBoardingPageData.ONBOARDING_PAP_DESC_IOS)
    @AndroidFindBy(id = OnBoardingPageData.ONBOARDING_PAP_DESC)
    protected WebElement descContent;

    @iOSXCUITFindBy(xpath = OnBoardingPageData.ONBOARDING_ALLOW_IOS)
    protected WebElement allow;

    @iOSXCUITFindBy(xpath = OnBoardingPageData.ONBOARDING_DALLOW_IOS)
    protected WebElement dontAllow;

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(WebElement loginButton) {
        this.loginButton = loginButton;
    }

    public WebElement getSkipButton() {
        return skipButton;
    }

    public void setSkipButton(WebElement skipButton) {
        this.skipButton = skipButton;
    }

    public WebElement getPageIndicatorButton() {
        return pageIndicatorButton;
    }

    public void setPageIndicatorButton(WebElement pageIndicatorButton) {
        this.pageIndicatorButton = pageIndicatorButton;
    }

    public WebElement getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(WebElement imageIcon) {
        this.imageIcon = imageIcon;
    }

    public WebElement getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(WebElement titleContent) {
        this.titleContent = titleContent;
    }

    public WebElement getDescContent() {
        return descContent;
    }

    public void setDescContent(WebElement descContent) {
        this.descContent = descContent;
    }
}