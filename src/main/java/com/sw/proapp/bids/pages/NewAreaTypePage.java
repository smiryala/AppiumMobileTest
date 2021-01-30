package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaTypePageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewAreaTypePage extends NewAreaTypePageLocators {

    public NewAreaTypePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isNewAreaTypePageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("area type") || pageSource.contains("TIPO DE ÁREA".toLowerCase());
    }

    public boolean isExteriorCtaVisable() {
        return getCommon().isDisplayed(exteriorCta);
    }

    public boolean isInteriorCtaVisable() {
        return getCommon().isDisplayed(interiorCta);
    }

    public NewAreaTypePage clickInteriorCta() {
        getCommon().clickAndVerifyIsTrue(interiorCta, "Unable to click the new interior button");
        return this;
    }

    public NewAreaTypePage clickExteriorCta() {
        getCommon().clickAndVerifyIsTrue(exteriorCta, "Unable to click the new exterior button");
        return this;
    }
}