package com.sw.app.colorsnap;

import com.aventstack.extentreports.Status;
import com.sw.colorsnap.pages.*;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.core.testsuites.MobileCoreBaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Mobile Automation")
@Feature(value = "Color Snap Smoke scenarios")
public class ColorSnapSmokeTest extends MobileCoreBaseTest {

    String colorsnapTestCase;
    private OnBoardingPage onBoardingPage = null;
    private AppDataFromDB appDataFromDB = null;
    private HomePage homePage = null;
    private AboutPage aboutPage = null;
    private EnvSelectorPage envSelectorPage = null;
    public static Login login = null;
    private MenuNavigationPage menuNavigationPage = null;
    private ResourcesPage resourcesPage = null;
    private DebugPage debugPage = null;
    public ColorSnapSmokeTest() {
        this.colorsnapTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeClass(alwaysRun = true)
    public void setQAEnv() {
        onBoardingPage = new OnBoardingPage(getCommon());
        homePage = new HomePage(getCommon());
        menuNavigationPage = new MenuNavigationPage(getCommon());
        resourcesPage = new ResourcesPage(getCommon());
        aboutPage = new AboutPage(getCommon());
        envSelectorPage = new EnvSelectorPage(getCommon());
        debugPage = new DebugPage(getCommon());
        onBoardingPage.clickColorSnapSkipButton();
        menuNavigationPage.clickOnResources();
        resourcesPage.clickOnAbout();
        if (getCommon().getDriver() instanceof AndroidDriver) {
            aboutPage.clickQADshboard();
            envSelectorPage.clickServiceEndPoint();
            envSelectorPage.setQADashboard();
        }else{
            aboutPage.clickDebugPage();
            debugPage.clickSetupServers();
            envSelectorPage.clickiOSQAEndPoint();
            ((AppiumDriver) getCommon().getDriver()).launchApp();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void before_ColorSnapSmokeTest() {
        ExtentTestManager.getTest().log(Status.INFO, "Before Method");
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        if (getCommon().getDriver() instanceof IOSDriver) {
            if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Allow").size()>0)
                    onBoardingPage.clickColorSnapSkipButton();
            if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
                ((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
            }
        }
        if (getCommon().getDriver() instanceof IOSDriver) {
        	if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
        		((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
        	}
        }
        else {
        	onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to ExplorePage and validate it contents")
    @Story("Verify the Page Elements of Explore Menu option")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyExplorePage"}, description = "Verify the Explore page in ColorSnap", priority = 1)
    public void verifyExplorePage() {
        ColorSnapExplorePageTest colorSnapExplorePageTest = new ColorSnapExplorePageTest(getCommon());
        colorSnapExplorePageTest.navigateToExplore();
        colorSnapExplorePageTest.verifyExplorePagePromotile();
        colorSnapExplorePageTest.verifyExplorePageMaintile();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Resources Page and validate it contents")
    @Story("Verify the Page Elements of Resources Menu option")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyResourcePage"}, description = "Verify the Resource page in ColorSnap", priority = 1)
    public void verifyResourcesPage() {
        ColorSnapResourcesPageTest colorSnapResourcesPageTestPageTest = new ColorSnapResourcesPageTest(getCommon());
        colorSnapResourcesPageTestPageTest.navigateToResources();
        colorSnapResourcesPageTestPageTest.verifyResourcesPageLinks();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Paint Page and validate it contents")
    @Story("Verify the Page Elements of Paint Menu option")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyPaintPage"}, description = "Verify the paint page in ColorSnap", priority = 1)
    public void verifyPaintPage() {
        ColorSnapPaintPageTest colorSnapPaintPageTestPageTest = new ColorSnapPaintPageTest(getCommon());
        colorSnapPaintPageTestPageTest.navigateToPaint();
        colorSnapPaintPageTestPageTest.verifyPintPageLinks();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to Validate Find a Store Functionality ")
    @Story("Validate and search for a store")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyFindAStore"}, description = "Verify the Find a Store in ColorSnap", priority = 1)
    public void verifyFindStore() {
        ColorSnapFindAStoreTest colorSnapFindAStoreTest = new ColorSnapFindAStoreTest(getCommon());
        colorSnapFindAStoreTest.navigateToResources();
        if (getCommon().getDriver() instanceof IOSDriver){
            colorSnapFindAStoreTest.validatefindAStorePageData();
            colorSnapFindAStoreTest.verifyfindAStorePageIOS();
        }else{
            colorSnapFindAStoreTest.verifyFindAStorePageAndroid();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to Validate Nearby Store Functionality")
    @Story("Validate and search for near by store")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyNearbyStore"}, description = "Validate Nearby Store Functionality", priority = 1)
    public void verifyNearbyStore() {
        ColorSnapFindAStoreTest colorSnapFindAStoreTest = new ColorSnapFindAStoreTest(getCommon());
        colorSnapFindAStoreTest.navigateToResources();
        if (getCommon().getDriver() instanceof IOSDriver){
            colorSnapFindAStoreTest.validateNearbyStoreData();
        }else{
            colorSnapFindAStoreTest.validateNearbyStoreData();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Login Page and validate it contents")
    @Story("Verify the Page Elements of Login Page option")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyLoginPage"}, description = "Verify the Login page in ColorSnap", priority = 1)
    public void verifyLoginPage() {
        ColorsnapAppLoginPageTest colorsnapAppLoginTestPageTest = new ColorsnapAppLoginPageTest(getCommon());
        if (getCommon().getDriver() instanceof IOSDriver) {
        	if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
        		((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
        	}
        }
        else {
        	onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }
        if (getCommon().getDriver() instanceof AndroidDriver) {
            colorsnapAppLoginTestPageTest.verifyLoginPageAndroid(login);
        }else{
            colorsnapAppLoginTestPageTest.verifyLoginPageIOS(login);
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Color Scan Number Page and validate it contents")
    @Story("Verify the Page Elements of COlor Scan Number Page")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyScanColorNumberPage"}, description = "Verify the scan color number page in ColorSnap", priority = 1)
    public void verifyScanColorNumberPage() {
        ColorSnapPaintPageTest colorSnapPaintPageTestPageTest = new ColorSnapPaintPageTest(getCommon());
        if (getCommon().getDriver() instanceof IOSDriver) {
            if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
                ((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
            }
        }
        else {
            onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }
        colorSnapPaintPageTestPageTest.navigateToPaint();
        colorSnapPaintPageTestPageTest.validateColorScanNumberPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Match a Photo Page and validate it contents")
    @Story("Verify the Page Elements of Match a Photo Page")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyMatchAPhotoPage"}, description = "Verify the match a photo page in ColorSnap", priority = 1)
    public void verifyMatchAPhotoPage() {
        ColorSnapPaintPageTest colorSnapPaintPageTestPageTest = new ColorSnapPaintPageTest(getCommon());
        if (getCommon().getDriver() instanceof IOSDriver) {
        	if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
        		((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
        	}
        }
        else {
        	onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }
        colorSnapPaintPageTestPageTest.navigateToPaint();
        colorSnapPaintPageTestPageTest.validateMatchaPhotoPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to Instant Paint Page and validate it contents")
    @Story("Verify the Page Elements of Instant Paint Page")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyInstantPaintPage"}, description = "Verify the instant paint page in ColorSnap", priority = 1)
    public void verifyInstantPaintPage() {
        ColorSnapPaintPageTest colorSnapPaintPageTestPageTest = new ColorSnapPaintPageTest(getCommon());
        if (getCommon().getDriver() instanceof IOSDriver) {
        	if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
        		((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
        	}
        }
        else {
        	onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }
        colorSnapPaintPageTestPageTest.navigateToPaint();
        colorSnapPaintPageTestPageTest.validateInstantPaintPage();
    }

    @Severity(SeverityLevel.CRITICAL)

    @Description("This scenario is to navigate the app to ExplorePage and validate promo titles")
    @Story("Verify the Page Elements of Explore Menu option")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyExplorePagePromoTiles"}, description = "Verify the Explore page Promo Tiles in ColorSnap", priority = 1)
    public void verifyExplorePagePromoTiles() {
        ColorSnapExplorePageTest colorSnapExplorePageTest = new ColorSnapExplorePageTest(
                getCommon());
        colorSnapExplorePageTest.navigateToExplore();
        colorSnapExplorePageTest.validateExplorePagePromoTiles();
    }

    @Description("This scenario is to navigate the app to Paint A Photo Page and validate its contents")
    @Story("Verify the Page Elements of Paint A Photo Page")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyPaintAPhotoPage"}, description = "Verify the paint a photo page in ColorSnap", priority = 1)
    public void verifyPaintAPage() {
        ColorSnapPaintPageTest colorSnapPaintPageTestPageTest = new ColorSnapPaintPageTest(getCommon());
        if (getCommon().getDriver() instanceof IOSDriver) {
        	if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Maybe later").size()>0){
        		((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Maybe later").click();
        	}
        }
        else {
        	onBoardingPage.clickElementByText("MAYBE LATER", "english");
        }
        colorSnapPaintPageTestPageTest.navigateToPaint();
        colorSnapPaintPageTestPageTest.validatePaintaPhotoPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This scenario is to navigate the app to ExplorePage and validate Color Collections page tiles")
    @Story("Verify the Color Collections Page tiles")
    @Test(groups = {
            "colorsnap_smoke",
            "verifyColorCollectionsPage"}, description = "Verify the Color Collections page tiles in ColorSnap", priority = 1)
    public void verifyColorCollectionsPage() {
        ColorSnapExplorePageTest colorSnapExplorePageTest = new ColorSnapExplorePageTest(
                getCommon());
        colorSnapExplorePageTest.navigateToExplore();
        colorSnapExplorePageTest.verifyColorCollectionsPage();
    }

    @AfterMethod(alwaysRun = true)
    public void after_ColorSnapSmokeTest() {
        ExtentTestManager.getTest().log(Status.INFO, "After Method");
        appDataFromDB.setResults(colorsnapTestCase);
        afterExecutionReports();
    }

}
