package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.ColorCollectionsPageData;
import com.sw.colorsnap.data.ColorNumberPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ColorCollectionsPageLocators extends MobileBasePage {

    public ColorCollectionsPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_HEADER)
    protected WebElement header;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_BACKBUTTON)
    protected WebElement backButton;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_COLORFORECAST)
    protected WebElement colorForecastTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_COLORID)
    protected WebElement colorIdTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_EMERALDDESIGN)
    protected WebElement EmeraldDesignTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_KIDSCOLORS)
    protected WebElement kidsColorTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_LIFESTYLE)
    protected WebElement lifeStyleTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_LIVINGWELL)
    protected WebElement livingWellTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_OURFINESTWHITES)
    protected WebElement ourFinestWhitesTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_POTTERYBARN)
    protected WebElement potteryBarnTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_TRENDING)
    protected WebElement trendingTile;

    @AndroidFindBy(xpath = ColorCollectionsPageData.COLORCOLLECTIONS_TILE_WESTELM)
    protected WebElement westElmTile;

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getColorForecastTile() {
        return colorForecastTile;
    }

    public WebElement getColorIdTile() {
        return colorIdTile;
    }

    public WebElement getEmeraldDesignTile() {
        return EmeraldDesignTile;
    }

    public WebElement getHeader() {
        return header;
    }

    public WebElement getKidsColorTile() {
        return kidsColorTile;
    }

    public WebElement getLifeStyleTile() {
        return lifeStyleTile;
    }

    public WebElement getLivingWellTile() {
        return livingWellTile;
    }

    public WebElement getOurFinestWhitesTile() {
        return ourFinestWhitesTile;
    }

    public WebElement getPotteryBarnTile() {
        return potteryBarnTile;
    }

    public WebElement getTrendingTile() {
        return trendingTile;
    }

    public WebElement getWestElmTile() {
        return westElmTile;
    }

}