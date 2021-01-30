package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.EnvSelectorPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class EnvSelectorPageLocators extends MobileBasePage {

    public EnvSelectorPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = EnvSelectorPageData.QAX_TEST_ENDPOINT_IOS)
    protected WebElement iOSQAXEndPoint;

    @AndroidFindBy(xpath = EnvSelectorPageData.QAX_TEST_ENDPOINT)
    protected WebElement colorsnapQATestEndPoint;

    @AndroidFindBy(id = EnvSelectorPageData.SERVICE_ENDPOINT)
    protected WebElement colorsnapServiceEndPoint;

    @AndroidFindBy(xpath = EnvSelectorPageData.BACKBUTTON)
    protected WebElement backButton;


    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getColorsnapServiceEndPoint() {
        return colorsnapServiceEndPoint;
    }

    public WebElement getColorsnapQATestEndPoint() {
        return colorsnapQATestEndPoint;
    }

    public WebElement getiOSQAXEndPoint() {
        return iOSQAXEndPoint;
    }
}