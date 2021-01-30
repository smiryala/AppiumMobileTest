package com.sw.core.helpers;

import com.aventstack.extentreports.Status;
import com.sw.core.reporting.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class SoftAssert extends Assertion implements ITestListener {

    //Trying ThreadLocal variables to help with the scalability of the framework.
    //There should only be one of these variables per browser.
    private static ThreadLocal<Throwable> cause = new ThreadLocal<>();
    private static ThreadLocal<List<WebElement>> webElements = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public SoftAssert() {
        cause.remove();
        webElements.remove();
        driver.remove();
    }

    public SoftAssert(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public void setCause(Throwable newCause) {
        cause.set(newCause);
    }

    public void setWebElements(List<WebElement> elements) {
        webElements.set(new LinkedList<>());
        webElements.set(elements);
    }

    public void setWebElements(WebElement... element) {
        try {
            List<WebElement> elements = new LinkedList<>(Arrays.asList(element));
            webElements.set(elements);
        } catch (Exception | Error e) {
            ConsoleLog.error("Unable to set WebElements for reporting purposes.", e);
        }
    }

    @Override
    public void executeAssert(IAssert assertion) {
        assertion.doAssert();
    }

    // from ITestListener interface
    @Override
    public void onStart(ITestContext context) {
        ConsoleLog.info("** Starting Test Case " + context.getName() + " **");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ConsoleLog.info("Starting Test Method " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ConsoleLog.info("Test Method = " + result.getName() + " has PASSED.");
    }

    /**
     * Takes a screenshot of the failed page, as well as handles the
     * creation of the JIRA ticket if it is enabled.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ConsoleLog.info("Test Method = " + result.getName() + " has FAILED.");
        File screenshot = IOUtil.highlightAndTakeScreenshot(driver.get(), webElements.get());

        //For Extent Report
        String buildIdJenkins = System.getProperties().getProperty("jenkinsBuildId");
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String screenShotName = screenshot.getName().replace(".png", "") + "_" + dateName + ".png";
        System.getProperties().setProperty("failurescreenshot", screenShotName);
        String destination;
        if (buildIdJenkins != null && !buildIdJenkins.trim().isEmpty()) {
            destination = System.getProperty("user.dir") + "/target/" + buildIdJenkins + "/" + screenShotName;
        } else {
            destination = System.getProperty("user.dir") + "/target/" + screenShotName;
        }
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(screenshot, finalDestination);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String message = result.getThrowable().getMessage();
        Throwable oldThrow = result.getThrowable();
        StackTraceElement[] stack = oldThrow.getStackTrace();

        Throwable newThrow = new AssertionError(message, cause.get());
        newThrow.setStackTrace(stack);
        result.setThrowable(newThrow);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ConsoleLog.info("Test Method = " + result.getName() + " was SKIPPED.");
        ExtentTestManager.getTest().log(Status.SKIP, result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String name = result.getName();
        int percentage = result.getMethod().getSuccessPercentage();
        ConsoleLog.info("TEST NAME = " + name + " FAILED BUT STILL WITHIN SUCCESS PERCENTAGE OF " + percentage);
    }

    @Override
    public void onFinish(ITestContext context) {
        ConsoleLog.info("** Finishing Test Case " + context.getName() + " **");
    }
}