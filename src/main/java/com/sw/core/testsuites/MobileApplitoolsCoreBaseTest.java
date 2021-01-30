package com.sw.core.testsuites;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.Region;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.appium.Eyes;
import com.applitools.eyes.appium.Target;
import com.aventstack.extentreports.Status;
import com.sw.core.helpers.ApplitoolsTestResultsHandler;
import com.sw.core.helpers.JavaScriptUtil;
import com.sw.core.helpers.PropsUtil;
import com.sw.core.helpers.ResultStatus;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class MobileApplitoolsCoreBaseTest extends MobileCoreBaseTest {
    private static ThreadLocal<Eyes> appliEyes = new ThreadLocal<>();
    private static ThreadLocal<ApplitoolsTestResultsHandler> applitoolsTestResultsHandler = new ThreadLocal<>();

    private static String APPLITOOLS_VIEW_API_KEY = "visualValidation.viewApiKey";
    private static String APPLITOOLS_API_KEY = "visualValidation.apiKey";

    public static final String APPLITOOLS_BRANCH_NAME = "mobileContentValidation";
    public static final String APPLITOOLS_SERVER_URL = "https://sherwineyesapi.applitools.com";
    public static final String APPLITOOLS_PROXY_URL = "http://proxy.proxysherwin.com:39000";
    public static final String APPLITOOLS_TEST_FAIL = "Applitools content validation has either failed and/or unresolved steps. Please see html report for more details";

    int MAX_SCREENS = 20;

    protected static Eyes getEyeInstance() {
        return appliEyes.get();
    }

    private static synchronized Eyes createEyeInstance(String batchName) {
        Eyes eyes = null;
        try {
            eyes = new Eyes();
            eyes.setBranchName(APPLITOOLS_BRANCH_NAME);
            eyes.setServerUrl(new URI(APPLITOOLS_SERVER_URL));
//            eyes.setProxy(new ProxySettings(APPLITOOLS_PROXY_URL)); // TODO: Switch on when final merge.
            eyes.setApiKey(System.getProperty(APPLITOOLS_API_KEY));
            eyes.setMatchTimeout(10000);
            eyes.setForceFullPageScreenshot(true);
            eyes.setIgnoreCaret(true);
            BatchInfo batch = new BatchInfo(batchName + "_" + String.format("%td%1$tb%1$tY_%1$tH%1$tM%1$tS", new Date()));
            eyes.setEnvName(URI.create(getTargetUrl()).getHost());
            eyes.setBatch(batch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eyes;
    }

    private static synchronized TestResults closeEyes() {
        Eyes existingEye = getEyeInstance();
        if (existingEye != null) {
            try {
                if (existingEye.getIsOpen()) {
                    return existingEye.close(false);
                }
            } catch (Exception e) {
                existingEye.abortIfNotClosed();
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void checkElement(WebElement webElement, String stepName) {
        checkElement(webElement, new ArrayList<>(), stepName, false);
    }

    public static void checkElement(WebElement webElement, List<By> ignoreRegionList, String stepName) {
        checkElement(webElement, ignoreRegionList, stepName, false);
    }

    public static void checkElement(WebElement webElement, String stepName, boolean change_stitchMode_to_scroll) {
        checkElement(webElement, new ArrayList<>(), stepName, change_stitchMode_to_scroll);
    }

    public static void checkElement(WebElement webElement, List<By> ignoreRegionList, String stepName, boolean change_stitchMode_to_scroll) {
        By[] ignoreElements = ignoreRegionList.toArray(new By[ignoreRegionList.size()]);

        getEyeInstance().check(stepName, Target.region(getLocator(webElement)).ignore((WebElement[]) ignoreElements));
    }

    public static void checkWindow(String stepName) {
        getEyeInstance().check(stepName, Target.window().fully());
    }

    public static void checkCurrentWindow(String stepName) {
        getEyeInstance().check(stepName, Target.window());
    }

    public static void checkWindow(String stepName, List<Region> ignoreRegionList) {
        Region[] ignoreRegions = ignoreRegionList.toArray(new Region[ignoreRegionList.size()]);
        getEyeInstance().check(stepName, Target.window().ignore(ignoreRegions));
    }


    public static void checkRecyclerView(String stepName, WebElement scrollableElement) {
        getEyeInstance().check(stepName, Target.window().scrollRootElement(scrollableElement).fully());
    }

    public static void checkRecyclerView(String stepName, WebElement scrollableElement, List<Region> ignoreRegionList) {
        Region[] ignoreRegions = ignoreRegionList.toArray(new Region[ignoreRegionList.size()]);
        getEyeInstance().check(stepName, Target.window().scrollRootElement(scrollableElement).ignore(ignoreRegions).fully());
    }

    public static Region getElementRegion(WebElement webElement) {
        SuiteUtil.createNewCommon((AppiumDriver) getDriver()).getWaitUntil().visibilityOfElements(webElement, "Unable to find the element");
        return new Region(webElement.getLocation().x, webElement.getLocation().y, (webElement.getSize().width + 10), (webElement.getSize().height + 5));
    }

    public static List<Region> getElementRegion(List<WebElement> webElementsList) {
        ArrayList<Region> newElemLst = new ArrayList<>();
        Region region;

        for (WebElement webElement : webElementsList) {
            region = getElementRegion(webElement);
            newElemLst.add(region);
        }
        return newElemLst;
    }

    private static By getLocator(WebElement element) {
        try {
            By findBy = (By) FieldUtils.readField(FieldUtils.readField(FieldUtils.readField(element, "h", true), "locator", true), "by", true);
            if (findBy != null) {
                return findBy;
            }
        } catch (IllegalAccessException ignored) {
        }
        return null;
    }

    private static ApplitoolsTestResultsHandler getApplitoolsTestResultsHandler() {
        return applitoolsTestResultsHandler.get();
    }

    private void setApplitoolsTestResultsHandler(ApplitoolsTestResultsHandler applitoolsTestResults) {
        applitoolsTestResultsHandler.set(applitoolsTestResults);
    }

    /**
     * Loops through the unwanted element to be made invisible
     */
    private static void removeUnwantedElement() {
        for (String elemSelector : getElementsToBeIgnored()) {
            if (isElementPresent(By.cssSelector(elemSelector))) {
                JavaScriptUtil.makeElementInvisible(elemSelector, getDriver());
            }
        }
    }

    /**
     * ONLY GIVE THE CSS SELECTOR TYPE
     *
     * @return list of elements to ignore
     */
    private static String[] getElementsToBeIgnored() {
        return new String[]{
                "#oo_tab"
        };
    }

    /**
     * @param by element locator
     * @return true if element exists on page
     */
    public static boolean isElementPresent(By by) {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return getDriver().findElements(by).size() != 0;
    }

    private static void loadVisualValidationCommonProperties() {
        PropsUtil.setSystemProperties("config/common.visualvalidation.properties");
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite_ApplitoolsCoreBaseTest() {
        loadVisualValidationCommonProperties();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest_ApplitoolsCoreBaseTest(ITestContext iTestContext) {
        appliEyes.set(createEyeInstance(iTestContext.getCurrentXmlTest().getName()));
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass_VisualBaseTest(ITestContext iTestContext, XmlTest xmlTest) {
        // if a config file is given at class level override it from suite level
        if (!isNullOrEmpty(System.getProperty(APPLITOOLS_API_KEY))) {
            loadVisualValidationCommonProperties();
        }
        System.getProperties().setProperty("browser.Version", ((RemoteWebDriver) getDriver()).getCapabilities().getVersion());
    }

    @BeforeMethod(alwaysRun = true)
    public void before_VisualBaseTest(Object[] testArgs, Method method) {
        getCommon().setInsertResults(insertResults);
        String testName = (testArgs.length == 0) ? "" : Arrays.deepToString(Stream.of(testArgs).toArray());
        getEyeInstance().open(getDriver(), method.getDeclaringClass().getSimpleName() + "-" + URI.create(getTargetUrl()).getHost(), method.getName() + testName);
        Test test = method.getAnnotation(Test.class);
        ExtentTestManager.createTest(method.getName(), test.description());
    }

    @AfterMethod(alwaysRun = true)
    public void after_ApplitoolsCoreBaseTest(ITestResult result, ITestContext testContext) {
        closeEyes();
        logApplitoolsReport();
    }

    private void logApplitoolsReport() {
        ApplitoolsTestResultsHandler applitoolsTestResults = getApplitoolsTestResultsHandler();
        if (applitoolsTestResults == null) {
            ExtentTestManager.getTest().fail("Test was either stopped by user or terminated abruptly");
        } else {
            int index = 0;
            for (ResultStatus tr : applitoolsTestResults.calculateStepResults()) {
                switch (tr.toString()) {
                    case "UNRESOLVED":
                    case "MISSING":
                        ExtentTestManager.getTest().log(Status.FAIL, applitoolsTestResults.getStepsNames()[index] + "---  <a href='" + applitoolsTestResults.getLinkToStep(index + 1) + "' style='color:red'>Link To Failed Step In Applitools Dashboard</a>");
                        break;
                    case "NEW":
                        ExtentTestManager.getTest().log(Status.INFO, applitoolsTestResults.getStepsNames()[index] + "---  <a href='" + applitoolsTestResults.getLinkToStep(index + 1) + "' >Link To New Step In Applitools Dashboard</a>");
                        break;
                    case "ABORTED":
                        ExtentTestManager.getTest().log(Status.INFO, applitoolsTestResults.getStepsNames()[index] + "---  <a href='" + applitoolsTestResults.getLinkToStep(index + 1) + "' style='color:orange'>Link To Aborted Step In Applitools Dashboard</a>");
                        break;
                    default:
                        ExtentTestManager.getTest().log(Status.PASS, applitoolsTestResults.getStepsNames()[index] + "---  <a href='" + applitoolsTestResults.getLinkToStep(index + 1) + "' >Link To Passed Step In Applitools Dashboard</a>");
                        break;
                }
                index++;
            }
        }
    }

    protected boolean isApplitoolsTestPassed() {
        TestResults appliTestResults = closeEyes();
        if (appliTestResults == null) {
            return false;
        } else {
            try {
                setApplitoolsTestResultsHandler(new ApplitoolsTestResultsHandler(appliTestResults, System.getProperty(APPLITOOLS_VIEW_API_KEY)));
            } catch (Exception ignored) {
            }
            return appliTestResults.isPassed();
        }
    }

    protected void verifyEyesTest() {
        Assert.assertTrue(isApplitoolsTestPassed(), APPLITOOLS_TEST_FAIL);
    }

    public void scrollDownScreen(MobileElement scrollableElement) {
        MobileDriver driver = (MobileDriver) getCommon().getDriver();
        Point scrollableOrigin = scrollableElement.getLocation();
        int bottomY = scrollableElement.getSize().height;
        TouchAction scrollScreen = new TouchAction(driver)
                .press(new PointOption().withCoordinates(scrollableOrigin.x, bottomY))
                .moveTo(new PointOption().withCoordinates(scrollableOrigin))
                .release();
        scrollScreen.perform();
    }

    public MobileDriver scrollUpScreen(MobileElement scrollableElement) {
        MobileDriver driver = (MobileDriver) getCommon().getDriver();
        Point scrollableOrigin = scrollableElement.getLocation();
        int bottomY = scrollableElement.getSize().height;
        TouchAction scrollScreen = new TouchAction(driver)
                .press(new PointOption().withCoordinates(scrollableOrigin))
                .moveTo(new PointOption().withCoordinates(scrollableOrigin.x, bottomY))
                .release();
        scrollScreen.perform();
        return driver;
    }

    public void validateRecyclerView(MobileElement recyclerViewElement) {
        String STORE_LOCATOR_STORE_LIST_LAST_ITEM_NAME_ANDROID = "//*[@resource-id='com.sherwin.probuyplus.debug:id/name' and @text='%s']";
        String RECYCLER_VIEW_LAST_ITEM_ANDROID = "(//androidx.recyclerview.widget.RecyclerView//android.widget.FrameLayout)[last()]";
        String lastCardName = getLastCardName(recyclerViewElement);
        int step = 1;
        checkCurrentWindow(String.format("Screen %s", step));
        while (!getCommon().exists(
                new By.ByXPath(String.format(STORE_LOCATOR_STORE_LIST_LAST_ITEM_NAME_ANDROID, lastCardName)))
                || (getCommon().exists(
                new By.ByXPath(String.format(STORE_LOCATOR_STORE_LIST_LAST_ITEM_NAME_ANDROID, lastCardName)))
                && (getCommon().getDriver().findElement(
                new By.ByXPath(RECYCLER_VIEW_LAST_ITEM_ANDROID)).getLocation().y +
                getCommon().getDriver().findElement(
                        new By.ByXPath(RECYCLER_VIEW_LAST_ITEM_ANDROID)).getSize().height
                >= recyclerViewElement.getLocation().y + recyclerViewElement.getSize().height))
        ) {
            scrollDownScreen(recyclerViewElement);
            if (step == MAX_SCREENS) break;
            else step++;
            checkCurrentWindow(String.format("Screen %s", step));
        }
    }

    public String getLastCardName(MobileElement recyclerViewElement) {
        String RECYCLER_VIEW_FIRST_NAME_ANDROID = "(//androidx.recyclerview.widget.RecyclerView//*[@resource-id='com.sherwin.probuyplus.debug:id/name'])[1]";
        String RECYCLER_VIEW_LAST_NAME_ANDROID = "(//androidx.recyclerview.widget.RecyclerView//*[@resource-id='com.sherwin.probuyplus.debug:id/name'])[last()]";

        String firstName = getCommon().getText(getCommon().getDriver().findElement(new By.ByXPath(RECYCLER_VIEW_FIRST_NAME_ANDROID)), "Unable to locate first RecyclerView Item");
        String lastName = getCommon().getText(getCommon().getDriver().findElement(new By.ByXPath(RECYCLER_VIEW_LAST_NAME_ANDROID)), "Unable to locate last RecyclerView Item");
        String newName = "";

        do {
            scrollDownScreen(recyclerViewElement);
            scrollDownScreen(recyclerViewElement);
            newName = getCommon().getText(getCommon().getDriver().findElement(new By.ByXPath(RECYCLER_VIEW_LAST_NAME_ANDROID)), "Unable to locate last RecyclerView Item");
            if (lastName.equals(newName)) {
                do {
                    scrollUpScreen(recyclerViewElement);
                    scrollUpScreen(recyclerViewElement);
                    newName = getCommon().getText(getCommon().getDriver().findElement(new By.ByXPath(RECYCLER_VIEW_FIRST_NAME_ANDROID)), "Unable to locate first RecyclerView Item");
                } while (!firstName.equals(newName));
                break;
            } else lastName = newName;
        } while (true);
        return lastName;
    }
}