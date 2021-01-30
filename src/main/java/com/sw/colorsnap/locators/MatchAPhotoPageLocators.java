package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.ColorNumberPageData;
import com.sw.colorsnap.data.MatchAPhotoPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MatchAPhotoPageLocators extends MobileBasePage {

    public MatchAPhotoPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = MatchAPhotoPageData.MATCHAPHOTO_ELIPSIS)
    protected WebElement matchAPhotoElipsis;

    @AndroidFindBy(id = MatchAPhotoPageData.MATCHAPHOTO_IMAGE)
    protected WebElement matchAPhotImage;

    @AndroidFindBy(xpath = MatchAPhotoPageData.MATCHAPHOTO_CONTENT)
    protected WebElement matchAPhotoContent;

    @AndroidFindBy(xpath = MatchAPhotoPageData.MATCHAPHOTO_TAKEPHOTO)
    protected WebElement matchAPhotoTakePhoto;

    @AndroidFindBy(xpath = MatchAPhotoPageData.MATCHAPHOTO_CHOOSEPHOTO)
    protected WebElement matchAPhotoChoosePhoto;

    @AndroidFindBy(xpath = MatchAPhotoPageData.MATCHAPHOTO_BACKBUTTON)
    protected WebElement matchAPhotoBackButton;

    public WebElement getMatchAPhotoElipsis() {
        return matchAPhotoElipsis;
    }
    public WebElement getMatchAPhotImage() {
        return matchAPhotImage;
    }
    public WebElement getMatchAPhotoContent() {
        return matchAPhotoContent;
    }
    public WebElement getMatchAPhotoTakePhoto() {
        return matchAPhotoTakePhoto;
    }
    public WebElement getMatchAPhotoChoosePhoto() {
        return matchAPhotoChoosePhoto;
    }
    public WebElement getMatchAPhotoBackButton() {
        return matchAPhotoBackButton;
    }



}