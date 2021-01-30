package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.ExplorePageData;
import com.sw.colorsnap.data.PaintPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ExplorePageLocators extends MobileBasePage {

    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_SCREENTITLE)
    @AndroidFindBy(id = ExplorePageData.EXPLORE_SCREENTITLE)
    protected WebElement screenTitle;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_LIVINGWELL_HEADER)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_LIVINGWELL_HEADER)
    protected WebElement lwHeader;
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_DCW_TILE)
    protected WebElement dcwTile;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_DCW_HEADER)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_DCW_HEADER)
    protected WebElement dcwHeader;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_PROMO_COLORID)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_PROMO_COLORID)
    protected WebElement promoColorID;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_PROMO_COLORID_DESC)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_PROMO_COLORID_DESC)
    protected WebElement promoColorDesc;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLOR_CHIPS)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLOR_CHIPS)
    protected WebElement colorChips;
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_ORDER_ONLINE)
    protected WebElement orderPaintOnline;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLOR_OF_YEAR_TITLE)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLOR_OF_YEAR_TITLE)
    protected WebElement cotyTitle;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLOR_OF_YEAR_NAME)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLOR_OF_YEAR_NAME)
    protected WebElement cotyName;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLOR_OF_YEAR_ID)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLOR_OF_YEAR_ID)
    protected WebElement cotyID;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_PAINT_FEATURES)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_PAINT_FEATURES)
    protected WebElement paintFeaturesTitle;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_PAINT_FEATURES_DESC)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_PAINT_FEATURES_DESC)
    protected WebElement paintFeaturesDesc;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_DCW_DESC)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_DCW_DESC)
    protected WebElement EXPLORE_DCW_DESC;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLOR_COLLECTIONS)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLOR_COLLECTIONS)
    protected WebElement colorCollections;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_COLORCOLLEC_DESC)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_COLORCOLLEC_DESC)
    protected WebElement colorCollectionsDesc;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_MATCH_PHOTO)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_MATCH_PHOTO)
    protected WebElement matchPhoto;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_MATCH_PHOTO_DESC)
    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_MATCH_PHOTO_DESC)
    protected WebElement matchPhotoDesc;

    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_PROMO_TILE_VIEW)
    protected WebElement promotileView;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_ACK_ALERT)
    protected WebElement acknowledge;

    @AndroidFindBy(xpath = ExplorePageData.EXPLORE_EXPERT_COLOR_ADVICE)
    protected WebElement requestExpertAdvice;

    public ExplorePageLocators(Common common) {
        super(common);
    }

    public WebElement getAcknowledge() {
        return acknowledge;
    }

    public WebElement getScreenTitle() {
        return screenTitle;
    }

    public WebElement getLwHeader() {
        return lwHeader;
    }

    public WebElement getDcwHeader() {
        return dcwHeader;
    }

    public WebElement getPromoColorID() {
        return promoColorID;
    }

    public WebElement getPromoColorDesc() {
        return promoColorDesc;
    }

    public WebElement getColorChips() {
        return colorChips;
    }

    public WebElement getOrderPaintOnline() {
        return orderPaintOnline;
    }

    public WebElement getRequestExpertAdvice() {
        return requestExpertAdvice;
    }

    public WebElement getCotyTitle() {
        return cotyTitle;
    }

    public WebElement getCotyName() {
        return cotyName;
    }

    public WebElement getCotyID() {
        return cotyID;
    }

    public WebElement getPaintFeaturesTitle() {
        return paintFeaturesTitle;
    }

    public WebElement getDcwTile() {
        return dcwTile;
    }

    public WebElement getEXPLORE_DCW_DESC() {
        return EXPLORE_DCW_DESC;
    }

    public WebElement getColorCollections() {
        return colorCollections;
    }

    public WebElement getColorCollectionsDesc() {
        return colorCollectionsDesc;
    }

    public WebElement getMatchPhoto() {
        return matchPhoto;
    }

    public WebElement getMatchPhotoDesc() {
        return matchPhotoDesc;
    }

    public WebElement getPromotileView() {
        return promotileView;
    }

    public WebElement getPaintFeaturesDesc() {
        return paintFeaturesDesc;
    }


}