package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.ExplorePageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class ExplorePage extends ExplorePageLocators {

    public ExplorePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public ExplorePage clickonLivingWellLink() {
        getCommon().sleepFor(2);
        getCommon().click(lwHeader, "Unable to click Living Well title.");
        return this;
    }

    @Step("Validate Explore Page Title")
    public String getScreenTitleText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(screenTitle, "Explore header not found");
        } else {
            return getCommon().getText(screenTitle, "Explore Header not avaialble");
        }
    }

    @Step("Click on Explore Menu")
    public ExplorePage clickOnAlert() {
        getCommon().getWaitUntil().visibilityOfElements(acknowledge, "Acknowledge Alert is not displayed");
        getCommon().clickAndVerifyIsTrue(acknowledge, "Acknowledge Alert to click exploreMenu");
        return this;
    }

    public String getLWHeaderText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(lwHeader, "Living Well is not available");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(lwHeader, "Living Well Tile not avaialble");
            return getCommon().getText(lwHeader, "Living Well Tile not avaialble");
        }
    }

    public String getColorIDHeaderText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(promoColorID, "Color Id is not available");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(promoColorID, "Color Id Tile not avaialble");
            return getCommon().getText(promoColorID, "Color Id Tile not avaialble");
        }
    }

    public String getColorOfTheYearText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(cotyTitle, "Color Of the Year is not available");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(cotyTitle, "Color of the Year Tile not avaialble");
            return getCommon().getText(cotyTitle, "Color of the Year Tile not avaialble");
        }
    }

    public String getColorChipsText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(colorChips, "Color Chips is not available");
        } else {
            //getCommon().getWaitUntil().visibilityOfElements(colorChips,"Color Chips Tile not avaialble");
            return getCommon().getText(colorChips, "Color Chips Tile not avaialble");
        }
    }

    public String getOrderPaintOnlineText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(orderPaintOnline, "Order Paint Online is not available");
        } else {
            return getCommon().getText(orderPaintOnline, "Order Paint Online Tile not avaialble");
        }
    }

    public String getRequestExpertAdviceText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(requestExpertAdvice, "Request Expert Advice is not available");
        } else {
            return getCommon().getText(requestExpertAdvice, "Request Expert Advice Tile not avaialble");
        }
    }

    public String getDCWText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(dcwHeader, "Digital Color Wall is not visible");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(dcwHeader, "Digital Color Wall is not visible");
            return getCommon().getText(dcwHeader, "Digital Color Wall is not visible");
        }
    }

    public String getColorCollectionsText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(colorCollections, "Color Collections is not visible");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(colorCollections, "Color Collections is not visible");
            return getCommon().getText(colorCollections, "Color Collections not available");
        }
    }

    public String getMatchPhotoText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(matchPhoto, "Match a Photo is not visible ");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(matchPhoto, "Match a Photo is not visible");
            return getCommon().getText(matchPhoto, "Match a Photo is not visible");
        }
    }

    public ExplorePage clickOnColorCollectionsLink() {
        getCommon().sleepFor(2);
        getCommon().click(colorCollections, "Unable to click Color Collections link");
        return this;
    }
}
