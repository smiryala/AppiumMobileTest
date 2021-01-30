package com.sw.app.colorsnap;

import com.aventstack.extentreports.Status;
import com.sw.colorsnap.pages.*;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.JavaScriptUtil;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ColorSnapExplorePageTest {

    private ExplorePage explorePage = null;
    private MenuNavigationPage menuNavigationPage = null;
    private ColorCollectionsPage colorCollectionsPage = null;

    public ColorSnapExplorePageTest(Common common) {
        explorePage = new ExplorePage(common);
        menuNavigationPage = new MenuNavigationPage(common);
        colorCollectionsPage = new ColorCollectionsPage(common);
    }

    @Step("verify Explore page Promo tiles")
    public void verifyExplorePagePromotile() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script On verify promo tile");
        explorePage.assertEquals(explorePage.getColorChipsText(), "COLOR CHIPS DELIVERED", "Color Chips  Tile is not displayed");

    }

    @Step("verify Explore page Main tiles")
    public void verifyExplorePageMaintile() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script On verify main tiles");
        explorePage.assertEquals(explorePage.getDCWText(), "Digital Color Wall", "Digital Color Wall Title is not displayed");
        explorePage.scrollDown3();
        explorePage.scrollDown3();
        explorePage.assertEquals(explorePage.getColorCollectionsText(), "Color Collections", "Color Collections Title is not displayed");
    }

    @Step("Navigate to Explore Menu")
    public void navigateToExplore() {
        ExtentTestManager.getTest().log(Status.INFO, " Navigate to  Resources menu");
        menuNavigationPage.clickOnExplore();
        if (explorePage.getDriver() instanceof IOSDriver) {
            explorePage.clickOnAlert();
        }
        explorePage.assertEquals(explorePage.getScreenTitleText(), "Explore", "Explore Page Title is not loaded");
    }

    @Step("Validate Explore Screen")
    public void validateExplorePagePromoTiles() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script On verify promo tiles");
        explorePage.scrollDown3();

        explorePage.assertEquals(explorePage.getColorChipsText(), "COLOR CHIPS DELIVERED", "Color Chips delivered  Tile is not displayed");
        explorePage.swipeLeft();

        explorePage.assertEquals(explorePage.getOrderPaintOnlineText(), "ORDER PAINT AND SUPPLIES ONLINE", "Order paint Online Tile is not displayed");
        explorePage.swipeLeft();

        explorePage.assertEquals(explorePage.getLWHeaderText(), "LIVING WELL™", "Living Well  Tile is not displayed");
        explorePage.swipeLeft();

        explorePage.assertEquals(explorePage.getRequestExpertAdviceText(), "REQUEST EXPERT COLOR ADVICE", "Request expert color advice Tile is not displayed");
        explorePage.swipeLeft();

        explorePage.assertEquals(explorePage.getColorIDHeaderText(), "INTRODUCING COLORSNAP® COLOR ID", "Color Id  Tile is not displayed");
        explorePage.swipeLeft();

        explorePage.assertEquals(explorePage.getColorOfTheYearText(), "2021 COLOR OF THE YEAR", "Color of the year Tile is not displayed");
    }

    @Step("Validate Color Collections Page")
    public void verifyColorCollectionsPage() {
        ExtentTestManager.getTest().log(Status.INFO, "Starting Script On Validate Color Collections Page");
        explorePage.scrollDown3();
        explorePage.scrollDown3();
        explorePage.assertEquals(explorePage.getColorCollectionsText(), "Color Collections", "Color Collections Title is not displayed");
        explorePage.clickOnColorCollectionsLink();

        Assert.assertTrue(colorCollectionsPage.isColorCollectionsHeaderDisplayed(),"Color Collections Header not dispalyed");
        Assert.assertTrue(colorCollectionsPage.isBackDisplayed(),"Back button is not displayed");

        Assert.assertTrue(colorCollectionsPage.isTrendingTileDisplayed(),"Trending Tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Emerald Designer Edition","English");
        Assert.assertTrue(colorCollectionsPage.isEmeraldDesignTileDisplayed(),"Emerald Design tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Color ID","English");
        Assert.assertTrue(colorCollectionsPage.isColorIdTileisplayed(),"Color Id tile is not dispalyed");

        colorCollectionsPage.scrollToElementByText("Living Well","English");
        Assert.assertTrue(colorCollectionsPage.isLivingWellTileDisplayed(),"Living Well tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Our Finest Whites","English");
        Assert.assertTrue(colorCollectionsPage.isOurFinestWhitesTileisplayed(),"Out Finest Whites tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Color Forecast","English");
        Assert.assertTrue(colorCollectionsPage.isColorForeCastTileDisplayed(),"Color Forecast tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Pottery Barn","English");
        Assert.assertTrue(colorCollectionsPage.isPotteryBarnTileDisplayed(),"Pottery Barn tile is not displayed");

        colorCollectionsPage.scrollToElementByText("West Elm","English");
        Assert.assertTrue(colorCollectionsPage.isWestElmTileisplayed(),"West Elm tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Lifestyle'","English");
        Assert.assertTrue(colorCollectionsPage.isLifeStyleTileDisplayed(),"Life style tile is not displayed");

        colorCollectionsPage.scrollToElementByText("Kids' Colors","English");
        Assert.assertTrue(colorCollectionsPage.isKidsColorTileDisplayed(),"Kids color tile is not displayed");

        colorCollectionsPage.clickOnBackButton();

    }
}