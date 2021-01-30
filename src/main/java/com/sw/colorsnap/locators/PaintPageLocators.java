package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.ExplorePageData;
import com.sw.colorsnap.data.PaintPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class PaintPageLocators extends MobileBasePage {

    public PaintPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSClassChain = PaintPageData.PAINT_SCREENTITLE_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_SCREENTITLE)
    protected WebElement paintScreenTitle;

    @iOSXCUITFindBy(id = PaintPageData.PAINT_OPEN_IPTITLE_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_OPEN_IPTITLE)
    protected WebElement instantPaintTitle;

    @iOSXCUITFindBy(id = PaintPageData.PAINT_OPEN_IPDESC_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_OPEN_IPDESC)
    protected WebElement instantPaintDesc;

    @iOSXCUITFindBy(id = PaintPageData.PAINT_PICTURE_TITLE_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_PICTURE_TITLE)
    protected WebElement paintaPicTitle;

    @iOSXCUITFindBy(id = PaintPageData.PAINT_PICTURE_DESC_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_PICTURE_DESC)
    protected WebElement paintaPicDesc;

    @AndroidFindBy(xpath = PaintPageData.PAINT_MATCHAPHOTO_TITLE)
    protected WebElement matchaPhotoTitle;

    @AndroidFindBy(xpath = PaintPageData.PAINT_MATCHAPHOTO_CONTENT)
    protected WebElement matchaPhotoContent;

    @AndroidFindBy(id = PaintPageData.PAINT_MATCHAPHOTO_IMAGE)
    protected WebElement matchaPhotoImage;

    @iOSXCUITFindBy(accessibility = PaintPageData.PAINT_PICTURE_TITLE_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_PAINTAPHOTO_TITLE)
    protected WebElement paintaPhotoTitle;

    @iOSXCUITFindBy(accessibility = PaintPageData.PAINT_PICTURE_DESC_IOS)
    @AndroidFindBy(xpath = PaintPageData.PAINT_PAINTAPHOTO_CONTENT)
    protected WebElement paintaPhotoContent;

    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_SCAN_COLOR)
    @AndroidFindBy(xpath = PaintPageData.EXPLORE_SCAN_COLOR)
    protected WebElement scanColor;
    @iOSXCUITFindBy(iOSClassChain = ExplorePageData.EXPLORE_IOS_SCAN_COLOR_DESC)
    @AndroidFindBy(xpath = PaintPageData.EXPLORE_SCAN_COLOR_DESC)
    protected WebElement scanColorDesc;
    @AndroidFindBy(xpath = PaintPageData.PAINT_SCANCOLORNUM_TITLE)
    protected WebElement paintScanColorNumTitle;

    @AndroidFindBy(xpath = PaintPageData.PAINT_SCANCOLORNUM_CONTENT)
    protected WebElement paintScanColorNumContent;

    @AndroidFindBy(id = PaintPageData.PAINT_SCANCOLORNUM_IMAGE)
    protected WebElement paintScanColorNumImage;

    public WebElement getMatchaPhotoTitle() {
        return matchaPhotoTitle;
    }
    public WebElement getMatchaPhotoContent() {
        return matchaPhotoContent;
    }
    public WebElement getMatchaPhotoImage() {
        return matchaPhotoImage;
    }
    public WebElement getPaintaPhotoTitle() {
        return paintaPhotoTitle;
    }
    public WebElement getPaintaPhotoContent() {
        return paintaPhotoContent;
    }
    public WebElement getScanColor() {
        return scanColor;
    }
    public WebElement getScanColorDesc() {
        return scanColorDesc;
    }
    public WebElement getPaintScanColorNumImage() {
        return paintScanColorNumImage;
    }

    public WebElement getPaintScanColorNumContent() {
        return paintScanColorNumContent;
    }

    public WebElement getPaintScanColorNumTitle() {
        return paintScanColorNumTitle;
    }
}