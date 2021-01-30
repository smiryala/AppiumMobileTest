package com.sw.colorsnap.locators;


import com.sw.colorsnap.data.ResourcesPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ResourcesPageLocators extends MobileBasePage {

    public ResourcesPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_MYIDEAS_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_MYIDEAS)
    protected WebElement myIdeas;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_PAINTCALC_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_PAINTCALC)
    protected WebElement paintCalc;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_STOREFINDER_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_STOREFINDER)
    protected WebElement storeFinder;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_LOGIN_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_LOGIN)
    protected WebElement login;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_TELLUS_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_TELLUS)
    protected WebElement tellUs;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_ABOUT_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_ABOUT)
    protected WebElement about;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_LEARNMORE_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_LEARNMORE)
    protected WebElement learnMore;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_SCREENTITLE_IOS)
    @AndroidFindBy(id = ResourcesPageData.RESOURCES_SCREENTITLE)
    protected WebElement screenTitle;

    @iOSXCUITFindBy(iOSClassChain = ResourcesPageData.RESOURCES_SIGNOUT_IOS)
    @AndroidFindBy(xpath = ResourcesPageData.RESOURCES_SIGNOUT)
    protected WebElement signOut;

    @AndroidFindBy(id = ResourcesPageData.WHY_REGISTER)
    protected WebElement whyRegister;

    public WebElement getLoginSW() {
        return login;
    }

    public WebElement getLogOut() {
        return signOut;
    }

    public WebElement getWhyRegister() {
        return whyRegister;
    }

}