package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.InstantPaintPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class InstantPaintPageLocators extends MobileBasePage {

    public InstantPaintPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSNsPredicate = InstantPaintPageData.INSTANTPAINT_STARTPAINT_BUTTON_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_STARTPAINT_BUTTON)
    protected WebElement startPaintButton;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_ALLOW_BUTTON_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_ALLOW_BUTTON)
    protected WebElement allowButton;

    @iOSXCUITFindBy(iOSClassChain = InstantPaintPageData.INSTANTPAINT_TITLE_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_TITLE)
    protected WebElement pageTitle;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_DONEBUTTON_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_BACKBUTTON)
    protected WebElement backButton;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_FINDCOLORS_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_FINDCOLORS)
    protected WebElement findColorsTab;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_MYIDEAS_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_MYIDEAS)
    protected WebElement myIdeasTab;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_EXPERTCOLORS_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_EXPERTCOLORS)
    protected WebElement expertColorsTab;

    @iOSXCUITFindBy(accessibility = InstantPaintPageData.INSTANTPAINT_ELIPSIS_IOS)
    @AndroidFindBy(xpath = InstantPaintPageData.INSTANTPAINT_ELIPSIS)
    protected WebElement elipsis;


    public WebElement getStartPaintButton() {
        return startPaintButton;
    }

    public WebElement getAllowButton() {
        return allowButton;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getFindColorsTab() {
        return findColorsTab;
    }

    public WebElement getMyIdeasTab() {
        return myIdeasTab;
    }

    public WebElement getExpertColorsTab() {
        return expertColorsTab;
    }

    public WebElement getElipsis() {
        return elipsis;
    }

}