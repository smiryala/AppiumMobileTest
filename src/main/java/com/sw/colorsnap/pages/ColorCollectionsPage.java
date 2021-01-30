package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.ColorCollectionsPageLocators;
import com.sw.colorsnap.locators.ColorNumberPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class ColorCollectionsPage extends ColorCollectionsPageLocators {

    public ColorCollectionsPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    @Step()
    public boolean isColorCollectionsHeaderDisplayed() {
        if (!getCommon().isDisplayed(getHeader()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isBackDisplayed() {
        if (!getCommon().isDisplayed(getBackButton()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isTrendingTileDisplayed() {
        if (!getCommon().isDisplayed(getTrendingTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isColorForeCastTileDisplayed() {
        if (!getCommon().isDisplayed(getColorForecastTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isColorIdTileisplayed() {
        if (!getCommon().isDisplayed(getColorIdTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isEmeraldDesignTileDisplayed() {
        if (!getCommon().isDisplayed(getEmeraldDesignTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isKidsColorTileDisplayed() {
        if (!getCommon().isDisplayed(getKidsColorTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isLifeStyleTileDisplayed() {
        if (!getCommon().isDisplayed(getLifeStyleTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isLivingWellTileDisplayed() {
        if (!getCommon().isDisplayed(getLivingWellTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isOurFinestWhitesTileisplayed() {
        if (!getCommon().isDisplayed(getOurFinestWhitesTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isPotteryBarnTileDisplayed() {
        if (!getCommon().isDisplayed(getPotteryBarnTile()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isWestElmTileisplayed() {
        if (!getCommon().isDisplayed(getWestElmTile()))
            return false;
        else
            return true;
    }

    @Step
    public ColorCollectionsPage clickOnBackButton() {
        if (existsElement(getBackButton())) {
            getCommon().clickAndVerifyIsTrue(getBackButton(),
                    "Unable to click Back Button.");
        }
        return this;
    }

}