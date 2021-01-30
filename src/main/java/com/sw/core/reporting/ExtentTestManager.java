package com.sw.core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import java.util.List;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extent = ExtentManager.getInstance();

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String description, String category) {
        ExtentTest test = extent.createTest(name, description);

        if (category != null && !category.isEmpty())
            test.assignCategory(category);

        extentTest.set(test);

        return getTest();
    }

    public synchronized static ExtentTest createTest(String name, String description) {
        return createTest(name, description, null);
    }

    public synchronized static ExtentTest createTest(String name) {
        return createTest(name, null);
    }

    public synchronized static void log(String message) {
        getTest().info(message);
    }

    public synchronized static void logException(Throwable stackTrace) {
        logStackTrace(ExceptionUtil.getStackTrace(stackTrace));
    }
    private synchronized static void logStackTrace(String stackTrace) {
        getTest().info("<details><summary><b>Stacktrace</b></summary><br/><pre><pre>" + stackTrace
                + "</pre></pre></details>");
    }

    /*Extend Report version 5 start*/

    public synchronized static void logStatus(Status status, String message) {
        getTest().log(status, message);
    }

    public synchronized static void logInfo(String message) {

        getTest().info(message);
    }

    public synchronized static void logPass(String message) {

        getTest().pass(message);
    }

    public synchronized static void logWarning(String message) {

        getTest().warning(message);
    }

    public synchronized static void logSkip(String message) {

        getTest().skip(message);
    }

    public synchronized static void logFail(String message) {

        getTest().fail(message);
    }


    public synchronized static void assignDevice(String message) {
        getTest().assignDevice(message);
    }

    public synchronized static void assignCategory(String message) {
        getTest().assignCategory(message);
    }


    public synchronized static void assignAuthor(String message) {
        getTest().assignAuthor(message);
    }

    public synchronized static void createChildTest(String testCase, String testDescription) {
        getTest().createNode(testCase, testDescription);
    }

    public synchronized static void addScreenCaptureFromPath(String screenshotPathForTestNG) {
        getTest().addScreenCaptureFromPath(screenshotPathForTestNG);

    }

    public synchronized static void addScreenCaptureFromPathLogs(String screenshotPathForTestNG,
            String failMessage) {
        getTest().fail(failMessage,
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPathForTestNG).build());

    }


    public synchronized static void addBase64ScreenshotsTests() {
        getTest().addScreenCaptureFromBase64String("base64String");

    }

    public synchronized static void addBase64ScreenshotsTestsLogs(String failMessage) {
        getTest().fail(failMessage,
                MediaEntityBuilder.createScreenCaptureFromBase64String("base64String").build());
    }


    public synchronized static void insertHTMLInReport(Status status, String html) {
        Media media = null;
        getTest().log(status, "HTML", media);

    }

    /*Markup Helpers start*/

    public synchronized static void createLabel(String message, ExtentColor color) {
        getTest().info(MarkupHelper.createLabel(message, color));
    }

    public synchronized static void insertJSONInReport(Status status, String json) {
        Markup m = MarkupHelper.createCodeBlock(json, CodeLanguage.XML);
        getTest().log(status, m);

    }

    public synchronized static void insertXMLInReport(Status status, String xml) {
        Markup m = MarkupHelper.createCodeBlock(xml, CodeLanguage.XML);
        getTest().log(status, m);

    }

    public synchronized static void insertMultipleCodeInReport(Status status,
            String expected, String actual) {
        Markup m = MarkupHelper.createCodeBlocks(new String[]{expected, actual});
        getTest().log(status, m);

    }

    public synchronized static void insertListInReport(Status status,
            List<Object> items) {

        getTest().info(MarkupHelper.createOrderedList(items));
    }


    /**
     * String[][] data = { { "Header1", "Header2", "Header3" }, { "Content.1.1", "Content.2.1",
     * "Content.3.1" }, { "Content.1.2", "Content.2.2", "Content.3.2" }, { "Content.1.3",
     * "Content.2.3", "Content.3.3" }, { "Content.1.4", "Content.2.4", "Content.3.4" } };
     *
     * @param status
     */
    public synchronized static void insertTableInReport(Status status, String[][] data) {
        Markup m = MarkupHelper.createTable(data);
        getTest().log(status, m);

    }
    /*Markup Helpers end*/

    /*Extend Report version 5 end*/
}