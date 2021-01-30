package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.ColorNumberPageData;
import com.sw.colorsnap.data.PaintPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ColorNumberPageLocators extends MobileBasePage {

    public ColorNumberPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = ColorNumberPageData.PAINT_COLORNUM_BACKBUTTON)
    protected WebElement paintColorNumBackButton;

    @AndroidFindBy(xpath = ColorNumberPageData.PAINT_COLORNUM_TITLE)
    protected WebElement paintColorNumTitle;

    @AndroidFindBy(id = ColorNumberPageData.PAINT_COLORNUM_IMAGE)
    protected WebElement paintColorNumImage;

    @AndroidFindBy(id = ColorNumberPageData.PAINT_COLORNUM_CONTENT)
    protected WebElement paintColorNumContent;

    @AndroidFindBy(id = ColorNumberPageData.PAINT_COLORNUM_SCANBUTTON)
    protected WebElement paintColorNumScanButton;

    public WebElement getPaintColorNumBackButton() {
        return paintColorNumBackButton;
    }

    public WebElement getPaintColorNumContent() {
        return paintColorNumContent;
    }

    public WebElement getPaintColorNumImage() {
        return paintColorNumImage;
    }

    public WebElement getPaintColorNumScanButton() {
        return paintColorNumScanButton;
    }

    public WebElement getPaintColorNumTitle() {
        return paintColorNumTitle;
    }

}