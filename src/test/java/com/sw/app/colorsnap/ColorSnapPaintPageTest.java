package com.sw.app.colorsnap;

import com.aventstack.extentreports.Status;
import com.sw.colorsnap.pages.*;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ColorSnapPaintPageTest {

    private PaintPage paintPage = null;
    private MenuNavigationPage menuNavigationPage = null;
    private MatchAPhotoPage matchPhotoPage = null;
    private PaintAPhotoPage paintAPhotoPage = null;
    private InstantPaintPage instantPaintPage = null;
    private ColorNumberPage colorNumberPage = null;

    public ColorSnapPaintPageTest(Common common) {
        paintPage = new PaintPage(common);
        menuNavigationPage = new MenuNavigationPage(common);
        matchPhotoPage = new MatchAPhotoPage(common);
        paintAPhotoPage = new PaintAPhotoPage(common);
        instantPaintPage = new InstantPaintPage(common);
        colorNumberPage = new ColorNumberPage(common);
    }

    @Step("verify Paint page Links")
    public void verifyPintPageLinks() {
        ExtentTestManager.getTest().log(Status.INFO, "verify Paint page Links");
        paintPage.assertEquals(paintPage.getInstantPaintTitle(), "Open Instant Paint", "Open Instant paint Title not displayed");
        paintPage.assertEquals(paintPage.getInstantPaintDesc(), "See any Sherwin-Williams color in your own space instantly.", "Open Instant paint Desc not displayed");
        paintPage.scrollDown3();
        paintPage.assertEquals(paintPage.getPaintaPicTitle(), "Paint a Photo", "Paint a Picture Title not displayed");
        paintPage.assertEquals(paintPage.getPaintaPicDesc(), "Take or choose a photo and see how our paint colors look in your space.", "Paint a Picture Desc not displayed");
        }

    @Step("Navigate to Paint Menu")
    public void navigateToPaint() {
        ExtentTestManager.getTest().log(Status.INFO, "Navigate to Paint menu");
        menuNavigationPage.clickOnPaint();
        paintPage.assertEquals(paintPage.getScreenTitle(), "Paint", "Paint Page  Title is not loaded");
    }


    @Step("Validate Match a Photo Page")
    public void validateMatchaPhotoPage() {
        ExtentTestManager.getTest().log(Status.INFO, "Validate Match a Photo Page");

        paintPage.scrollToElementByText("Match a Photo","English");
        Assert.assertTrue(paintPage.isMatchaPhotoTitleDisplayed(), "Match a Photo Title not displayed");
        Assert.assertTrue(paintPage.isMatchaPhotoContentDisplayed(), "Match a Photo Desc not displayed");
        Assert.assertTrue(paintPage.isMatchaPhotoImageDisplayed(), "Image is Not Displayed for Match a Photo");

        paintPage.clickOnMatchaPhotoTitle();
        Assert.assertTrue(matchPhotoPage.isBackButtonDisplayed(), "Back is Not Displayed on Match a Photo Page");
        Assert.assertTrue(matchPhotoPage.isElipsisDisplayed(), "Elipsis not displayed");
        Assert.assertTrue(matchPhotoPage.isImageDisplayed(), "Image is Not Displayed on Match a Photo Page");
        Assert.assertTrue(matchPhotoPage.isContentDisplayed(), "Match a Photo content not displayed");
        Assert.assertTrue(matchPhotoPage.isTakeaPhotoButtonDisplayed(), "Take a Photo button is not displayed");
        Assert.assertTrue(matchPhotoPage.isChooseaPhotoButtonDisplayed(), "Choose a Photo button is not displayed");
        matchPhotoPage.clickOnBackButton();
    }

    @Step("Validate Paint a Photo Page")
    public void validatePaintaPhotoPage() {
        ExtentTestManager.getTest().log(Status.INFO, "Validate Paint a Photo Page");

        if (paintPage.getCommon().getDriver() instanceof IOSDriver) {
            paintPage.scrollDown3();
            paintPage.assertTrue(paintPage.isPaintaPhotoTitleDisplayed(), "Paint a Photo Title");
            paintPage.assertTrue(paintPage.isPaintaPhotoContentDisplayed(), "Paint a Photo Desc");
            paintPage.clickOnPaintaPhotoTitle();
            paintPage.assertTrue(paintAPhotoPage.isTakeaPhotoButtonDisplayed(), "Take a Photo button displayed on Paint a Photo page");
            paintPage.assertTrue(paintAPhotoPage.isChooseaPhotoButtonDisplayed(), "Choose a Photo button displayed on Paint a Photo page");
            paintPage.assertTrue(paintAPhotoPage.isBackButtonDisplayed(), "Back button on Paint a Photo Page");
            paintAPhotoPage.clickOnBackButton();
        }else {
            paintPage.scrollToElementByText("Paint a Photo","English");
            paintPage.assertTrue(paintPage.isPaintaPhotoTitleDisplayed(), "Paint a Photo Title");
            paintPage.assertTrue(paintPage.isPaintaPhotoContentDisplayed(), "Paint a Photo Desc");
            paintPage.clickOnPaintaPhotoTitle();
            paintPage.assertTrue(paintAPhotoPage.isBackButtonDisplayed(), "Back button on Paint a Photo Page");
            paintPage.assertTrue(paintAPhotoPage.isElipsisDisplayed(), "Elipsis on Paint a Photo Page");
            paintPage.assertTrue(paintAPhotoPage.isImageDisplayed(), "Image on Paint a Photo Page");
            paintPage.assertTrue(paintAPhotoPage.isContentDisplayed(), "Paint a Photo content on Paint a Photo Page");
            paintPage.assertTrue(paintAPhotoPage.isTakeaPhotoButtonDisplayed(), "Take a Photo button displayed on Paint a Photo page");
            paintPage.assertTrue(paintAPhotoPage.isChooseaPhotoButtonDisplayed(), "Choose a Photo button displayed on Paint a Photo page");
            paintAPhotoPage.clickOnBackButton();
        }
    }

    @Step("Validate Instant Paint Page")
    public void validateInstantPaintPage() {
        ExtentTestManager.getTest().log(Status.INFO, "Instant Paint Page");

        paintPage.scrollToElementByText("Open Instant Paint","English");
        paintPage.assertEquals(paintPage.getInstantPaintTitle(), "Open Instant Paint", "Open Instant paint Title not displayed");
        paintPage.assertEquals(paintPage.getInstantPaintDesc(), "See any Sherwin-Williams color in your own space instantly.", "Open Instant paint Desc not displayed");
        paintPage.clickInstantPaint();

        instantPaintPage.clickOnStartPainting();

        try {
            if (instantPaintPage.getAllowButton().isDisplayed())
                instantPaintPage.getAllowButton().click();
        }catch(Exception e){  }

        Assert.assertTrue(instantPaintPage.isExpertsColorsTabDisplayed(), "Experts Colors Tab is not displayed");
        Assert.assertTrue(instantPaintPage.isFindColorsTabDisplayed(), "Find Colors Tab is not displayed");
        Assert.assertTrue(instantPaintPage.isMyIdeasTabDisplayed(), "Myideas Tab is not displayed");
        Assert.assertTrue(instantPaintPage.isElipsisDisplayed(), "Elipsis is not displayed");
        Assert.assertTrue(instantPaintPage.isBackButtonDisplayed(), "Back button is not displayed");
        instantPaintPage.clickOnBackButton();
    }
    @Test(groups = { "colorsnap_smoke", "android" }, description = "Validate Scan Color Number Page")
    @Step("Validate Scan Color Number Page")
    public void validateColorScanNumberPage() {
        ExtentTestManager.getTest().log(Status.INFO, "Validate Scan Color Number Page");

        paintPage.scrollToElementByText("Scan Color Number","English");
        Assert.assertTrue(paintPage.isScanColorNumberDisplayed(), "Scan Color Title not displayed");
        Assert.assertTrue(paintPage.isScanColorNumberContentDisplayed(), "Paint a Picture Desc not displayed");
        Assert.assertTrue(paintPage.isScanColorNumberImageDisplayed(), "Image is Not Displayed on Color Scan Number Page");

        paintPage.clickOnScanColorNumber();
        Assert.assertTrue(colorNumberPage.isBackDisplayed(), "Back is Not Displayed on Paint Page");
        Assert.assertTrue(colorNumberPage.isColorNumberTitleDisplayed(), "Scan Color Title not displayed");
        Assert.assertTrue(colorNumberPage.isColurNumberImageDisplayed(), "Image is Not Displayed on Color Scan Number Page");
        Assert.assertTrue(colorNumberPage.isColorNumberContentDisplayed(), "Scan color content not displayed");
        Assert.assertTrue(colorNumberPage.isScanButtonDisplayed(), "Scan button is not displayed");
        colorNumberPage.clickOnBackButton();
    }
}