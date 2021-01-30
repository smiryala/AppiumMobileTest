package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.PaintAPhotoPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class PaintAPhotoPageLocators extends MobileBasePage {

    public PaintAPhotoPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = PaintAPhotoPageData.PAINTAPHOTO_ELIPSIS)
    protected WebElement paintAPhotoElipsis;

    @AndroidFindBy(id = PaintAPhotoPageData.PAINTAPHOTO_IMAGE)
    protected WebElement paintAPhotImage;

    @iOSXCUITFindBy(accessibility = PaintAPhotoPageData.PAINTAPHOTO_CONTENT_IOS)
    @AndroidFindBy(xpath = PaintAPhotoPageData.PAINTAPHOTO_CONTENT)
    protected WebElement paintAPhotoContent;

    @iOSXCUITFindBy(accessibility = PaintAPhotoPageData.PAINTAPHOTO_TAKEPHOTO_IOS)
    @AndroidFindBy(xpath = PaintAPhotoPageData.PAINTAPHOTO_TAKEPHOTO)
    protected WebElement paintAPhotoTakePhoto;

    @iOSXCUITFindBy(accessibility = PaintAPhotoPageData.PAINTAPHOTO_CHOOSEPHOTO_IOS)
    @AndroidFindBy(xpath = PaintAPhotoPageData.PAINTAPHOTO_CHOOSEPHOTO)
    protected WebElement paintAPhotoChoosePhoto;

    @iOSXCUITFindBy(accessibility = PaintAPhotoPageData.PAINTAPHOTO_CANCELBUTTON_IOS)
    @AndroidFindBy(xpath = PaintAPhotoPageData.PAINTAPHOTO_BACKBUTTON)
    protected WebElement paintAPhotoBackButton;

    public WebElement getPaintAPhotoElipsis() { return paintAPhotoElipsis; }
    public WebElement getPaintAPhotImage() {
        return paintAPhotImage;
    }
    public WebElement getPaintAPhotoContent() {
        return paintAPhotoContent;
    }
    public WebElement getPaintAPhotoTakePhoto() {
        return paintAPhotoTakePhoto;
    }
    public WebElement getPaintAPhotoChoosePhoto() {
        return paintAPhotoChoosePhoto;
    }
    public WebElement getPaintAPhotoBackButton() {
        return paintAPhotoBackButton;
    }
}