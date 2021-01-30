package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.PaintPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class PaintPage extends PaintPageLocators {

    public PaintPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Click on Instant Paint")
    public PaintPage clickInstantPaint() {
        getCommon().getWaitUntil().visibilityOfElements(instantPaintTitle,"instant Paint Tile not displayed");
        getCommon().clickAndVerifyIsTrue(instantPaintTitle, "Unable to click exploreMenu");
        return this;
    }

    @Step("Click on Paint a picture" )
    public PaintPage clickPaintaPicture() {
        scrollDown3();
        getCommon().getWaitUntil().visibilityOfElements(paintaPhotoTitle,"instant Paint Tile not displayed");
        getCommon().clickAndVerifyIsTrue(paintaPhotoTitle, "Unable to click exploreMenu");
        return this;
    }

    @Step("Validate Instant paint Title Link")
    public String getInstantPaintTitle() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(instantPaintTitle, "Instant paint title not visible");
        } else {
            return getCommon().getText(instantPaintTitle, "Instant paint title is not visible");
        }
    }

    @Step("Validate Paint page screen Title")
    public String getScreenTitle() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(paintScreenTitle, "Screen Title is not visible");
        } else {
            return getCommon().getText(paintScreenTitle, "Screen Title is not visible");
        }
    }

    @Step("Validate Paint a Picture Description")
    public String getPaintaPicDesc() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(paintaPhotoContent, "Paint a pic desc not visible");
        } else {
            return getCommon().getText(paintaPhotoContent, "Paint a pic desc not visible");
        }
    }

    @Step("Validate Instant Paint Description")
    public String getInstantPaintDesc() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(instantPaintDesc, "Instant Paint desc not visible");
        } else {
            return getCommon().getText(instantPaintDesc, "Instant Paint desc not visible");
        }
    }

    @Step("Validate Paint a Picture Title")
    public String getPaintaPicTitle() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(paintaPhotoTitle, "Paint a pic title not visible");
        } else {
            return getCommon().getText(paintaPhotoTitle, "Paint a pic title not visible");
        }
    }


    @Step
    public PaintPage clickOnMatchaPhotoTitle() {
        if (existsElement(getMatchaPhotoTitle())) {
            getCommon().clickAndVerifyIsTrue(getMatchaPhotoTitle(),
                    "Unable to click on Match a Photo");
        }
        return this;
    }


    @Step()
    public boolean isMatchaPhotoTitleDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getMatchaPhotoTitle(),"Match a Photo title");
        if (!getCommon().isDisplayed(getMatchaPhotoTitle()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isMatchaPhotoContentDisplayed() {
        if (!getCommon().isDisplayed(getMatchaPhotoContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isMatchaPhotoImageDisplayed() {
        if (!getCommon().isDisplayed(getMatchaPhotoImage()))
            return false;
        else
            return true;
    }

    @Step
    public PaintPage clickOnPaintaPhotoTitle() {
        if (existsElement(getPaintaPhotoTitle())) {
            getCommon().clickAndVerifyIsTrue(getPaintaPhotoTitle(),
                    "Unable to click on Paint a Photo");
        }
        return this;
    }

    @Step()
    public boolean isPaintaPhotoTitleDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getPaintaPhotoTitle(),"Paint a Photo title is not displayed");
        if (!getCommon().isDisplayed(getPaintaPhotoTitle()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isPaintaPhotoContentDisplayed() {
        if (!getCommon().isDisplayed(getPaintaPhotoContent()))
            return false;
        else
            return true;
    }

    public String getScanColorText() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(scanColor, "Scan Color is not available");
        } else {
            getCommon().getWaitUntil().visibilityOfElements(scanColor, "Scan Color Tile not avaialble");
            return getCommon().getText(scanColor, "Scan Color Tile not avaialble");
        }
    }

    @Step
    public PaintPage clickOnScanColorNumber() {
        if (existsElement(getPaintScanColorNumTitle())) {
            getCommon().clickAndVerifyIsTrue(getPaintScanColorNumTitle(),
                    "Unable to click on Scan Color Number");
        }
        return this;
    }


    @Step()
    public boolean isScanColorNumberDisplayed() {
        getCommon().getWaitUntil().visibilityOfElements(getPaintScanColorNumTitle(),"Scan Color number title");
        if (!getCommon().isDisplayed(getPaintScanColorNumTitle()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isScanColorNumberContentDisplayed() {
        if (!getCommon().isDisplayed(getPaintScanColorNumContent()))
            return false;
        else
            return true;
    }

    @Step()
    public boolean isScanColorNumberImageDisplayed() {
        if (!getCommon().isDisplayed(getPaintScanColorNumImage()))
            return false;
        else
            return true;
    }
}