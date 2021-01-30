package com.sw.core.testsuites;

import static com.sw.core.helpers.PropsUtil.getBrowserType;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.google.common.collect.ImmutableMap;
import com.sw.core.database.InsertResults;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.helpers.IOUtil;
import com.sw.core.helpers.PropsUtil;
import com.sw.core.helpers.SoftAssert;
import com.sw.core.reporting.AllureEnvironmentWriter;
import com.sw.core.reporting.AllureExecutorJson;
import com.sw.core.reporting.AllureHtmlWriter;
import com.sw.core.reporting.ExtentManager;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Listeners({SoftAssert.class})
public class MobileCoreBaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Properties loadTestCaseIds;
    private static Properties loadConfig;
    private static boolean addedToReport = false;
    private static String testConfigFile;
    protected String buildIdJenkins;
    protected boolean hasToModifyJSessionId = false;
    protected boolean fail = false;
    String driverName = "";
    String executionMode = "";
    private ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();
    private ThreadLocal<Common> common = new ThreadLocal<>();
    private ThreadLocal<WebDriver> appiumDriver = new ThreadLocal<>();
    protected InsertResults insertResults;
    private static String testTargetURL;

    public static Properties getProperties() {
        return loadConfig;
    }

    static {
        loadTestCaseIds = PropsUtil.setSystemProperties("config/testcaseid.properties");
    }

    public static String getTargetUrl() {
        return testTargetURL;
    }

    protected boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.trim().isEmpty();
    }

    private void configureLogLevel(String logLevel) {

        String consoleMessage;

        String jenkinsBuildParameter = System.getProperties().getProperty("logLevel");
        // If 'logLevel' is a build parameter from jenkins, use that
        if (!isNullOrEmpty(jenkinsBuildParameter) && !jenkinsBuildParameter.equalsIgnoreCase("VALUE NOT DEFINED IN JENKINS")) {
            logLevel = jenkinsBuildParameter;
            consoleMessage = "Using the logLevel from jenkins build parameter --> " + jenkinsBuildParameter;
        }
        // if running outside jenkins and 'logLevel' from testNG xml is not null or empty, use that
        else if (!isNullOrEmpty(logLevel)) {
            consoleMessage = "Using the logLevel from testNG xml --> " + logLevel;
        }
        // default to 'INFO', if building outside jenkins and testNG xml doesn't have logLevel defined or the value is NULL or empty
        else {
            logLevel = "INFO";
            consoleMessage = "Using the default log Level --> INFO";
        }

        // Configure log4j
        PropertyConfigurator.configure("log4j.xml");
        DOMConfigurator.configure("log4j.xml");
        ConsoleLog.setLogLevel(logLevel);
        ConsoleLog.info(consoleMessage);
    }

    public SoftAssert getSoftAssert() {
        return softAssert.get();
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }


    /**
     * @param actual
     * @param expected
     * @param successMessage
     * @param errorMessage
     */
    public void assertEquals(String actual, String expected, String successMessage,
            String errorMessage) {
        try {
            Assert.assertEquals(actual, expected);
            ExtentTestManager.getTest().log(Status.PASS,
                    "Verified the Excepted and Actual assertEquals: " + successMessage
                            + ",<b> Actual:</b>" + actual + " <b>Expected:</b> " + expected + "");
            // ExtentTestManager.insertMultipleCodeInReport(Status.PASS,actual,expected);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL,
                    "Error in assertEquals: " + errorMessage + ", <b> Actual:</b>" + actual
                            + " <b>Expected:</b> " + expected);
            ExtentTestManager.insertMultipleCodeInReport(Status.FAIL, actual, expected);
        }
    }


    /**
     * @param flag
     * @param successMessage
     * @param errorMessage
     */
    public void assertTrue(boolean flag, String successMessage, String errorMessage) {
        try {
            Assert.assertTrue(flag, errorMessage);
            ExtentTestManager.getTest()
                    .log(Status.PASS, "Verified in assertTrue: <b>" + successMessage + "</b>");
        } catch (Exception e) {
            ExtentTestManager.getTest()
                    .log(Status.FAIL, "Error in assertTrue: <b> " + errorMessage + "</b>");
        }
    }

    /**
     * @param flag
     * @param successMessage
     * @param errorMessage
     */
    public void assertFalse(boolean flag, String successMessage, String errorMessage) {
        try {
            Assert.assertFalse(flag, errorMessage);
            ExtentTestManager.getTest()
                    .log(Status.PASS, "Verified in assertFalse: <b>" + successMessage + "</b>");
        } catch (Exception e) {
            ExtentTestManager.getTest()
                    .log(Status.FAIL, "Error in assertFalse: <b>" + errorMessage + "</b>");
        }
    }


    @Attachment(value = "{0}", type = "text/html")
    public static byte[] saveHtmlAttach(String key, String value, String fileName) {
        try {
            File allureResultsDir = new File(System.getProperty("user.dir") + "/target/");
            if (allureResultsDir.exists()) {
                AllureHtmlWriter.getHtmlReport(key, value, fileName);
            }
            return toByteArray(new File(allureResultsDir + File.separator + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private static byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    public Common getCommon() {
        return common.get();
    }

    /**
     * Sets up the system for the upcoming tests. This setups the information that will be used
     * across the entire suite. This does not contain any test specific information.
     *
     * @param logLevel   The logging level for the suite
     * @param configFile The Config file for the suite
     */
    @Parameters({"driver.name", "logLevel", "configFile", "targetUrl"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional String driverName, @Optional String logLevel, @Optional String configFile, @Optional String targetUrl) {

        // set the log level
        configureLogLevel(logLevel);

        // Set the configuration file
        setConfigFile(configFile);

        loadConfig = NativeUtils.loadProperties(driverName, getConfigFile());

        // Set the configuration file
        setTargetUrl(targetUrl);
        // Set log directory
        IOUtil.setLogDirectory();

        // Extent Report Directory and Report Name
        if (ExtentManager.getInstance() == null) {
            buildIdJenkins = System.getProperties().getProperty("jenkinsBuildId");
            if (isNullOrEmpty(buildIdJenkins)) {
                ExtentManager.createInstance(
                        System.getProperty("user.dir") + "/target/SWMobileReport.html");
            } else {
                ExtentManager.createInstance(
                        System.getProperty("user.dir") + "/target/" + buildIdJenkins
                                + "/SWMobileReport.html");
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Object[] testArgs, Method method) {
        insertResults = new InsertResults();
        String testName = (testArgs.length == 0) ? ""
                : Arrays.deepToString(Stream.of(testArgs).flatMap(Stream::of).toArray());
        Test test = method.getAnnotation(Test.class);
        ExtentTestManager.createTest(method.getName() + testName, test.description());
        ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
        String jenkinsBuildParameter = System.getProperty("jSessionID");
        if (!isNullOrEmpty(jenkinsBuildParameter) && !jenkinsBuildParameter
                .equalsIgnoreCase("VALUE NOT DEFINED IN JENKINS")) {
            ConsoleLog.info("Found build parameter for modifying jSessionID to --> "
                    + jenkinsBuildParameter);
            hasToModifyJSessionId = true;
        } else {
            ConsoleLog
                    .info("jSessionID is ignored as jenkins build either doesn't have the 'jenkins_jSessionID' parameter defined or is empty");
            hasToModifyJSessionId = false;
        }
    }

    public void logInfo() {
        if (!addedToReport) {
            // ExtentManager.getInstance().setSystemInfo("Browser", ((RemoteWebDriver) dynamicWebDriver.getDriver()).getCapabilities().getBrowserName());
            ExtentManager.getInstance().setSystemInfo("Browser", getBrowserType());
            ExtentManager.getInstance().setSystemInfo("Environment", "Lower_ENV");
            ExtentManager.getInstance().setSystemInfo("Release", "Test");
            addedToReport = true;
        }
    }

    private String getConfigFile() {
        return testConfigFile;
    }

    public void setConfigFile(String givenConfigFile) {
        String jenkinsBuildParameter = System.getProperties().getProperty("configFile");
        // If 'configFile' is a build parameter from jenkins, use that
        if (!isNullOrEmpty(jenkinsBuildParameter) && !jenkinsBuildParameter.equalsIgnoreCase("VALUE NOT DEFINED IN JENKINS")) {
            testConfigFile = jenkinsBuildParameter;
            ConsoleLog.info("Using the configuration file from jenkins build parameter --> " + testConfigFile);
        }
        // if running outside jenkins and 'configFile' from testNG xml is not null or empty, use that
        else if (!isNullOrEmpty(givenConfigFile)) {
            testConfigFile = givenConfigFile;
            ConsoleLog.info("Using the configuration file from testNG xml --> " + testConfigFile);
        }
        // use the default property file if config file is either missing or null or empty
        else {
            testConfigFile = "config/commonConfig.properties";
            ConsoleLog.info("Using the default config file --> " + testConfigFile);
        }
    }

    protected boolean addJSessionID(String expectedJSessionID) {
        Cookie oldCookieJSessionID = getCurrentJSessionCookie();
        // if browser session doesn't have a cookie names JSESSIONID, report failure
        if (oldCookieJSessionID == null) {
            ConsoleLog.error("NO jSessionID found for this browser session");
            return false;
        } else {
            ConsoleLog.info("Found the JSESSIONID cookie");
        }

        String oldJSessionID = getJSessionIDValue(oldCookieJSessionID.getValue());
        // if browser session already has the requested JSESSIONID, ignore modifying the cookie
        if (oldJSessionID.equalsIgnoreCase(expectedJSessionID)) {
            ConsoleLog.info("Browser session already using the expected JSession ID --> " + expectedJSessionID);
            return true;
        } else {
            ConsoleLog.info("jSessionID BEFORE modifying --> " + oldJSessionID);
        }

        if (isNullOrEmpty(expectedJSessionID)) {
            ConsoleLog.error("The expected jSessionID is either null or empty");
            return false;
        }
        Cookie newCookieJSessionID = new Cookie(
                "JSESSIONID",
                oldCookieJSessionID.getValue().replaceFirst("^(.*:).*$", "$1" + expectedJSessionID),
                oldCookieJSessionID.getDomain(),
                oldCookieJSessionID.getPath(),
                oldCookieJSessionID.getExpiry(),
                oldCookieJSessionID.isSecure(),
                oldCookieJSessionID.isHttpOnly()
        );
        driver.get().manage().deleteCookie(oldCookieJSessionID);
        driver.get().manage().addCookie(newCookieJSessionID);
        driver.get().navigate().refresh();
        getCommon().getWaitUntil().pageLoad();
        Cookie latestJSessionCookie = getCurrentJSessionCookie();
        if (latestJSessionCookie == null) {
            ConsoleLog.error("Browser has no JSESSIONID cookie. Something went wrong after modifying the jSessionID to --> " + expectedJSessionID);
            return false;
        }
        String newJSessionID = getJSessionIDValue(latestJSessionCookie.getValue());
        if (newJSessionID.equalsIgnoreCase(oldJSessionID)) {
            ConsoleLog.info("After modifying and refreshing the page, the JSessionID's remained same \n old JSession ID --> " + oldJSessionID + "\n new JSession ID --> " + newJSessionID);
        } else {
            ConsoleLog.info("jSessionID AFTER modifying --> " + newJSessionID);
        }
        return true;
    }

    private String getJSessionIDValue(String jSessionCookieValue) {
        return jSessionCookieValue.replaceAll("^.*:(.*)$", "$1");
    }

    private boolean hasJSessionIDCookie() {
        return driver.get().manage().getCookieNamed("JSESSIONID") != null;
    }

    private Cookie getCurrentJSessionCookie() {
        return (hasJSessionIDCookie()) ? driver.get().manage().getCookieNamed("JSESSIONID") : null;
    }

    private void reportJSessionInfo() {
        if (hasToModifyJSessionId) {
            Cookie browserJSessionCookie = getCurrentJSessionCookie();
            if (browserJSessionCookie == null) {
                ExtentTestManager.getTest().log(Status.FAIL,
                        "Error : At the time of failure, NO JSessionID was found, however jSessionID parameter used from jenkins is --> " + System.getProperty(
                                "jSessionID"));
            } else {
                ExtentTestManager.getTest().log(Status.FAIL, "Error : JSessionID at the failure of the test : " + getJSessionIDValue(browserJSessionCookie.getValue()));
            }
        }
    }

    /**
     * Reports logger in extent report with screenshot
     */
    public void logReportWithScreenShot(String msgToAdd) {

        String screenShotName = "";
        String errorMessage = "";
        try {
            File driverScreenshot = IOUtil.highlightAndTakeScreenshot(driver.get(), null);

            //For Extent Report
            String dateTimeStamp = String.format("%td%1$tb%1$tY_%1$tH%1$tM%1$tS", new Date());
            screenShotName = driverScreenshot.getName().replace(".png", "") + "_" + dateTimeStamp + ".png";

            String buildIdJenkins = System.getProperties().getProperty("jenkinsBuildId");
            String destination = System.getProperty("user.dir") + "/target/";
            if (!isNullOrEmpty(buildIdJenkins)) {
                destination = destination + buildIdJenkins + "/" + screenShotName;
            } else {
                destination = destination + screenShotName;
            }

            FileUtils.copyFile(driverScreenshot, new File(destination));

            if (isNullOrEmpty(buildIdJenkins)) {
                screenShotName = "../target/" + screenShotName;
            }
        } catch (Exception exception) {
            errorMessage = Arrays.toString(exception.getStackTrace());
        }

        if (isNullOrEmpty(screenShotName)) {
            ExtentTestManager.getTest().log(Status.FAIL, msgToAdd + "<br> Got below exception while taking the screenshot : <br>" + errorMessage);
        } else {
            //MediaEntityModelProvider mediaEntityModelProvider = null;
            try {
                //mediaEntityModelProvider = MediaEntityBuilder.createScreenCaptureFromPath(screenShotName).build();
                MediaEntityBuilder.createScreenCaptureFromPath(screenShotName).build();
            } catch (Exception ignored) {
                errorMessage = Arrays.toString(ignored.getStackTrace());
            }
            ExtentTestManager.getTest()
                    .log(Status.INFO, msgToAdd + "<br>" +errorMessage+"</br>");
            IOUtil.setScreenShotFileName();
        }
    }

    /**
     * If a test method has failed, then the whole test has failed, so this marks this for the Sauce
     * util. <br> If a test has failed, it will fail the job in sauce, if sauce is enabled.
     *
     * @param result      testNG result
     * @param testContext testNG context
     * @throws Exception exceptions
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, ITestContext testContext) throws Exception {
        ExtentTestManager.getTest().assignCategory(this.getClass().getSimpleName());
        if (!result.isSuccess()) {
            fail = true;
        }
        ConsoleLog.info("Insert Result Values before inserting in Results table: afterMethod> "
                + insertResults.toString());
        try {
            insertResults.execute();
        } catch (Exception e) {
            ConsoleLog.error("Problem inserting result in database...");
        }
        // Report logger with Screenshot
        logSmokeReportWithScreenShot(result);

        if (getCommon() != null) {
            ((AppiumDriver) getCommon().getDriver()).closeApp();
            ((AppiumDriver) getCommon().getDriver()).launchApp();
        }
    }

    /**
     * After suite, cleans up the system property, and closes the Sauce Connect connection if it is
     * enabled. <br> Also handles cleaning up an errant processes if they are still alive.
     * <p>
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        PropsUtil.clearSystemProperties(getConfigFile());
        PropsUtil.clearSystemProperties("config/commonConfig.properties");
        ExtentManager.getInstance().flush();
    }

    private void setTargetUrl(String givenTargetUrl) {
        String jenkinsBuildParameter = System.getProperties().getProperty("targetUrl");
        // If 'targetUrl' is a build parameter from jenkins, use that
        if (!isNullOrEmpty(jenkinsBuildParameter) && !jenkinsBuildParameter
                .equalsIgnoreCase("VALUE NOT DEFINED IN JENKINS")) {
            testTargetURL = jenkinsBuildParameter;
            ConsoleLog.info("Using the application target URL from jenkins build parameter --> "
                    + testTargetURL);
        }
        // if running outside jenkins and 'targetUrl' from testNG xml is not null or empty, use that
        else if (!isNullOrEmpty(givenTargetUrl)) {
            testTargetURL = givenTargetUrl;
            ConsoleLog
                    .info("Using the application target URL from testNG xml --> " + testTargetURL);
        }
        // use the default target URL from common config file if targetUrl is either missing or null or empty
        else {
            testTargetURL = System.getProperty("webDriver.targetUrl");
            ConsoleLog.info("Using the default application target URL from common config file --> "
                    + testTargetURL);
        }
    }

    /**
     * This sets up the test specific information such as sauce connection, the webdriver, and
     * common objects.
     *
     * @param testContext test context from TestNG
     */
    @Parameters({"driver.name", "logLevel", "configFile", "targetUrl", "executionMode", "language"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext testContext, @Optional String driverName,
            @Optional String logLevel, @Optional String configFile, @Optional String targetUrl,
            @Optional String executionMode, @Optional String language) {
        try {
            System.getProperties().setProperty("webDriver.testCaseName",
                    testContext.getAllTestMethods()[0].getDescription());
        } catch (Exception e) {
            System.getProperties().setProperty("webDriver.testCaseName", testContext.getName());
        }

        MobileDriverFactory mobileDriverFactory = MobileDriverFactory.getInstance();

        appiumDriver.set(mobileDriverFactory
                .createInstance(driverName, executionMode, language, this.getClass().getName(),
                        getConfigFile()));

        if (driverName.contains("android.web") || driverName.contains("ios.web")) {
            SuiteUtil.loadPropertyFiles(SuiteUtil.WEB_RESOURCE_PATH);
            appiumDriver.get().get(targetUrl);

        } else if (driverName.contains("android") || driverName.contains("ios")) {

            SuiteUtil.loadPropertyFiles(SuiteUtil.NATIVE_RESOURCE_PATH);

        }
        common.set(mobileDriverFactory.getCommon());
        driver.set(mobileDriverFactory.getDriver());
        IOUtil.setCommon(getCommon());
        softAssert.set(getCommon().getSoftAssert());
        ConsoleLog.info(testContext.getName() + "Test starts here");
    }

    public void closeAndQuitWebDriver() {
        if (getCommon() != null) {
            if (getCommon().getDriver() != null) {
                try {
                    getCommon().getDriver().quit();
                } catch (Exception e) {
                    System.out.println("Exception in closing driver" + e);
                }
            }
        }
    }

    public void restartAndroidApp() {
        String packageName = ((AndroidDriver) getCommon().getDriver()).getCurrentPackage();
        ((AndroidDriver) getCommon().getDriver()).terminateApp(packageName);
        ((AndroidDriver) getCommon().getDriver()).activateApp(packageName);
    }

    /**
     * Checks if any test has failed, and if they haven't then it will pass the job on Sauce. This
     * also quits the current driver for this class.
     */
    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext testContext) {
        String _executionMode =
                !isNullOrEmpty(testContext.getCurrentXmlTest().getParameter("executionMode"))
                        ? testContext.getCurrentXmlTest().getParameter("executionMode")
                        : System.getProperty("executionMode");
        if (_executionMode.equalsIgnoreCase("remote")) {
            if (!fail) {
//                SauceUtil.passJob(driver.get());
            } else {
//                SauceUtil.failJob(driver.get());
            }
        }
        //Set Environment
        setAllureEnvironment();

        String URL = getMobileCapability("testobject_test_report_url");

        saveHtmlAttach("Test report: ", "<a target='_blank' rel='noopener noreferrer' href=" + URL
                + ">Sauce Lab Report URL</a>", "saucereport.html");

        Allure.step(testContext.getName() + " Script executed on :" + getMobileCapability(
                "testobject_device_name"));
        AllureExecutorJson.allureExecutorWriter();

        //Allure.step(testContext.getFailedTests().getAllMethods().stream().toString());

        closeAndQuitWebDriver();
    }

    /**
     * Reports logger in extent report with screenshot
     *
     * @throws Exception exceptions
     */
    public void logSmokeReportWithScreenShot(ITestResult result) throws Exception {
        String screenshotName = "";
        String testName = result.getTestName();
        String testDescription = result.getMethod().getDescription();
        if (!isNullOrEmpty(System.getProperties().getProperty("failurescreenshot"))) {
            screenshotName = System.getProperties().getProperty("failurescreenshot");
        } else {
            ConsoleLog
                    .info(">>>>>>>>>>>>>> Screenshot Name is NULL as Test is SUCCESS>>>>>>>>>>>>>");
        }
        String screenshotPathForTestNG = "../target/" + screenshotName;
        String fromJenkins = System.getProperties().getProperty("jenkinsBuildId");

        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(Status.FAIL, testName + " -->" + testDescription);
            // Report JSESSIONID details to Extent reporter
            reportJSessionInfo();
            if (!isNullOrEmpty(fromJenkins)) {
//                MediaEntityModelProvider mediaEntityModelProviderJenkins = MediaEntityBuilder
//                        .createScreenCaptureFromPath(screenshotName).build();
                //System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
                Media media = MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build();
                saveScreenshotPNG(getCommon().getDriver());
                ExtentTestManager.getTest().fail(testDescription,media);
                saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
            } else if (isNullOrEmpty(fromJenkins)) {
//                MediaEntityModelProvider mediaEntityModelProviderTestNG = MediaEntityBuilder
//                        .createScreenCaptureFromPath(screenshotPathForTestNG).build();
//                ExtentTestManager.getTest().fail(testDescription, mediaEntityModelProviderTestNG);
                saveScreenshotPNG(getCommon().getDriver());
                Media media = MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build();
                ExtentTestManager.getTest().fail(testDescription,media);
            }
            if (result.getThrowable() != null) {
                ExtentTestManager.logException(result.getThrowable());
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(Status.SKIP, testName + " -->" + testDescription);
            // Report JSESSIONID details to Extent reporter
            reportJSessionInfo();
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest()
                    .log(Status.PASS, "Test case : " + testDescription + " --> " + testDescription);
        } else if (result.getStatus() == ITestResult.CREATED) {
            ExtentTestManager.getTest()
                    .log(Status.FAIL, "Test case : " + testDescription + " --> " + testDescription);
        }
        logInfo();
    }

    public String getMobileCapability(String key) {

        String keyValue = null;
        RemoteWebDriver remoteWebDriver = (RemoteWebDriver) getCommon().getDriver();
        keyValue = remoteWebDriver.getCapabilities().getCapability(key).toString();
        return keyValue;
    }

    @Step()
    public void afterExecutionReports() {
        if (getCommon() != null) {
            //System.out.println("Test live view: " + remoteWebDriver.getCapabilities().getCapability("testobject_test_live_view_url"));
            //System.out.println("Test report: " + remoteWebDriver.getCapabilities().getCapability("testobject_test_report_url"));
            //ExtentTestManager.getTest().log(Status.INFO, "Test live View: "+remoteWebDriver.getCapabilities().getCapability("testobject_test_live_view_url"));
            String URL = getMobileCapability("testobject_test_report_url");
            ExtentTestManager.getTest().log(Status.INFO,
                    "Test report: <a target=\"_blank\" rel=\"noopener noreferrer\" href=\"" + URL
                            + "\">Sauce Lab Report URL</a> ");
        }

    }

    void setAllureEnvironment() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().putAll(getAllMobileCapabilities())
                        .build(), System.getProperty("user.dir")
                        + "/target/allure-results/");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public Map<String, String> getAllMobileCapabilities() {

        RemoteWebDriver remoteWebDriver = (RemoteWebDriver) getCommon().getDriver();
        //remoteWebDriver.getCapabilities().getCapabilityNames().parallelStream().forEach(System.out::println);
        List<String> aList = remoteWebDriver.getCapabilities().getCapabilityNames().parallelStream()
                .collect(Collectors.toList());
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < aList.size(); i++) {
            //System.out.println("Key "+aList.get(i)+" Value:"+remoteWebDriver.getCapabilities().getCapability(aList.get(i)).toString());
            map.put(aList.get(i),
                    remoteWebDriver.getCapabilities().getCapability(aList.get(i)).toString());
        }

        return map;
    }


    public static WebDriver getDriver() {
        return driver.get();
    }
}
    