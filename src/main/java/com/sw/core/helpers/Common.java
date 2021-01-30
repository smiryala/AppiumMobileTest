package com.sw.core.helpers;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.sw.core.database.InsertResults;
import com.sw.core.reporting.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.testng.util.Strings.isNullOrEmpty;

public class Common {
    public static final String BROWSER_TYPE = "webDriver.browserType";
    protected WebDriver driver;
    private WaitUntil waitUntil;
    private SoftAssert softAssert;
    private long implicitTimeout;
    private long pageTimeout;
    private long scriptTimeout;
    private InsertResults insertResults;


    public Common(WebDriver driver, SoftAssert softAssert, WaitUntil waitUntil) {
        this.driver = driver;
        this.waitUntil = waitUntil;
        this.softAssert = softAssert;
    }

    public void setTimeout(long timeoutInSeconds) {
        waitUntil.setTimeout(timeoutInSeconds);
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
    }

    /**
     * Sets the timeout variables for this and the waitUntil object stored in
     * the common.
     *
     * @param implicitTimeout implicit time value in long
     */
    public void setTimeoutValues(long implicitTimeout, long pageTimeout, long scriptTimeout) {
        this.implicitTimeout = implicitTimeout;
        this.pageTimeout = pageTimeout;
        this.scriptTimeout = scriptTimeout;
        waitUntil.setTimeoutValues(this.implicitTimeout, this.pageTimeout, this.scriptTimeout);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitUntil getWaitUntil() {
        return waitUntil;
    }

    /**
     * Simple clicks on a given {@link WebElement} method.
     *
     * @param element The WebElement to click on
     * @return boolean value
     */
    public boolean click(WebElement element, String errorMessage) {
        softAssert.setCause(null);

        boolean success = true;
        try {
            softAssert.setWebElements(element);
            Thread.sleep(1000);
            element.click();

        } catch (Exception | Error e) {
            e.printStackTrace();
            ConsoleLog.error("Click element did not work as expected.", e);
            softAssert.setCause(e);
            success = false;
        }
        setResultStatus(errorMessage, success);
        return success;
    }

    /**
     * <b>**TRY TO NOT USE THIS! IT IS BAD PRACTICE! BUT IF YOU NEED TO CHEAT,
     * IT IS HERE! **</b>
     * </p>
     * <p>
     * Causes the currently executing thread to sleep (temporarily cease
     * execution) for the specified number of milliseconds, subject to the
     * precision and accuracy of system timers and schedulers. The thread does
     * not lose ownership of any monitors.
     *
     * @param seconds the length of time to sleep in seconds
     * @return boolean value
     */
    public boolean sleepFor(long seconds) {
        softAssert.setCause(null);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            ConsoleLog.error("Attempt to sleep thread did not work as expected.", e);
            softAssert.setCause(e);
            return false;
        }
        return true;
    }

    /**
     * Clears the given {@link WebElement} of all text, and then sends the given
     * string to the given {@link WebElement}.
     *
     * @param element The WebElement to send the string to
     * @param query   The String to send
     * @return boolean value
     */
    public boolean type(WebElement element, String query) {
        return type(element, query, true);
    }

    /**
     * Based on the given boolean, will clear the given {@link WebElement}, and
     * then sendKeys to the given {@link WebElement}.
     *
     * @param element    The WebElement to send to string to
     * @param query      The String to send
     * @param clearField boolean for if the WebElement should be cleared first
     * @return boolean value
     */
    public boolean type(WebElement element, String query, boolean clearField) {
        softAssert.setCause(null);
        try {
            if (clearField) {
                element.clear();
            }
            element.sendKeys(query);
        } catch (Exception | Error e) {
            ConsoleLog.error("Typing into an element did not work as expected.", e);
            softAssert.setCause(e);
            return false;
        }
        return true;
    }

    /**
     * Checks if given {@link WebElement} is displayed.
     *
     * @param element The {@link WebElement} to check
     * @return boolean based on if the given {@link WebElement} is displayed or
     * not.
     */
    public boolean isDisplayed(WebElement element) {
        softAssert.setCause(null);
        try {
            return element.isDisplayed();
        } catch (Exception | Error e) {
            ConsoleLog.error("Checking if an element is displayed did not work as expected.", e);
            softAssert.setCause(e);
            return false;
        }
    }

    /**
     * Returns a list of {@link WebElement} based on the given {@link By}
     * locator.
     *
     * @param locator ElementLocator
     * @return A list of {@link WebElement}s
     */
    public List<WebElement> getElements(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);

        return list;
    }

    /**
     * Returns a list of {@link WebElement} based on the given {@link By}
     * locator, but only searches for the locator in the given
     * {@link WebElement}.
     *
     * @param element {@link WebElement} to search inside of for other
     *                {@link WebElement}s using the given By locator.
     * @param locator {@link By} locator to search for inside of the given
     *                {@link WebElement}
     * @return A list of {@link WebElement}s based on the described method
     * above.
     */
    public List<WebElement> getElements(WebElement element, By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = element.findElements(locator);
        driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);

        return list;
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    /**
     * Checks if a By locator exists on the current page. Returns true if it
     * does.
     *
     * @param locator By locator to check if it exists
     * @return boolean for whether the given By locator exists
     */
    public boolean exists(By locator) {
        return getElements(locator).size() > 0;
    }

    /**
     * Click on the specified element and verifies that the element located by
     * 'locator' is displayed.
     *
     * @param webElement              The WebElement to be clicked
     * @param errorMessage            The error message to be shown if the operation failed
     * @param expectedElementLocators A locator for the new element to be displayed
     * @return true is operation succeed, false otherwise.
     */
    public boolean clickAndVerifyIsTrue(WebElement webElement, String errorMessage, By... expectedElementLocators) {
        boolean success = waitUntil.visibilityOfElements(webElement, errorMessage);

        if (success) {
            webElement = waitUntil.elementToBeClickable(webElement, errorMessage);
            if (webElement != null) {
                success = click(webElement, errorMessage);

                if (success && expectedElementLocators != null) {
                    for (By locator : expectedElementLocators) {
                        waitUntil.presenceOfElement(locator, errorMessage);
                        success = success && getElements(locator).size() > 0;
                    }
                }
            } else {
                success = false;
            }
        }
        setResultStatus(errorMessage, success);
        Verify.isTrue(softAssert, success, errorMessage, createList(webElement));
        return success;
    }

    /**
     * Sends the specified text into the specified WebElement and verifies that
     * the operation succeeds.
     *
     * @param webElement   The WebElement into which the text is entered
     * @param text         The text
     * @param errorMessage The error message to be shown if the operation failed
     * @return true is operation succeed, false otherwise.
     */
    public boolean typeAndVerifyIsTrue(WebElement webElement, String text, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage);

        boolean success = waitUntil.visibilityOfElements(webElement, errorMessage);

        success = success && type(webElement, text, true);

        setResultStatus(errorMessage, success);
        Verify.isTrue(softAssert, success, errorMessage, createList(webElement));
        return success;
    }

    /**
     * Verifies whether element Text is Empty
     *
     * @param elementText  The WebElement Text
     * @param errorMessage The error message to be shown if element is not displayed
     * @return true is element Text is not Empty, false otherwise.
     */
    public boolean isElementTextValueEmpty(String elementText, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        boolean success = !elementText.equals("");

        setResultStatus(errorMessage, success);
        Verify.isTrue(softAssert, success, errorMessage);
        return success;
    }

    /**
     * Gets the text from a given {@link WebElement}, but waits for visibility
     * of the element first. <br>
     * If the elemnt is not visible, it will return a blank.
     *
     * @param element      WebElement
     * @param errorMessage error message
     * @return boolean value
     */
    public String getText(WebElement element, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        return waitUntil.visibilityOfElements(element, errorMessage) ? element.getText() : "";
    }

    /**
     * Not sure why Abdou didn't just use the isTrue from the verify class, but
     * this is here and does the same thing. <br>
     * Verifies that the given boolean is true
     *
     * @param success      boolean for to check if it is true
     * @param errorMessage String for the error message to report if it fails
     * @return boolean value
     */
    public boolean verifyIsTrue(boolean success, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage);

        Verify.isTrue(softAssert, success, errorMessage);

        return success;
    }

    /**
     * Automatically fails the current test.
     *
     * @param errorMessage String for the error message to print
     */
    public void setTestResultFalse(String errorMessage) {
        verifyIsTrue(false, errorMessage);
    }

    /**
     * Create a list of WebElements out of the given WebElements.
     *
     * @param webElements An array of WebElements that we are going to turn into a list
     * @return A List of WebElements from the given WebElements.
     */
    public List<WebElement> createList(WebElement... webElements) {
        List<WebElement> elements = new ArrayList<>();
        Collections.addAll(elements, webElements);
        return elements;
    }

    public void setResultStatus(String errorMessage, boolean success) {
        if (!success) {
            ExtentTestManager.getTest().log(Status.FAIL, errorMessage);
        }
    }

    /**
     * Method to update below to the Extent html report
     * -- update test result for each test step
     * -- attach screenshot to the test step
     * -- attach exception to the test step, if failed to take the screenshot
     *
     * @param result      to set the result to pass or fail
     * @param description to set the step description in the report
     */
    public void htmlReporter(boolean result, String description) {
        if (result) {
            ExtentTestManager.getTest().log(Status.PASS, description);
            ConsoleLog.info("PASS --> " + description);
        } else {
            ConsoleLog.error("FAIL --> " + description);

            String screenShotName = null;
            String errorMessage = null;
            try {
                // Take screenshot
                boolean shouldTakeFullPageScreenshot_OnTestFailure = Boolean.parseBoolean(System.getProperty(IOUtil.FULL_PAGE_SCREENSHOT));
                if (shouldTakeFullPageScreenshot_OnTestFailure) {
                    System.setProperty(IOUtil.FULL_PAGE_SCREENSHOT, "false");
                }
                File driverScreenshot = IOUtil.highlightAndTakeScreenshot(driver, new LinkedList<>());
                if (shouldTakeFullPageScreenshot_OnTestFailure != Boolean.parseBoolean(System.getProperty(IOUtil.FULL_PAGE_SCREENSHOT))) {
                    System.setProperty(IOUtil.FULL_PAGE_SCREENSHOT, String.valueOf(shouldTakeFullPageScreenshot_OnTestFailure));
                }

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

                File finalDestination = new File(destination);
                FileUtils.copyFile(driverScreenshot, finalDestination);

                if (isNullOrEmpty(buildIdJenkins)) {
                    screenShotName = "../target/" + screenShotName;
                }
            } catch (Exception exception) {
                errorMessage = Arrays.toString(exception.getStackTrace());
            }

            if (isNullOrEmpty(screenShotName)) {
                ExtentTestManager.getTest().log(Status.FAIL, description + "<br> Got below exception while taking the screenshot : <br>" + errorMessage);
            } else {
                //MediaEntityModelProvider mediaEntityModelProvider = null;
                try {
                    //mediaEntityModelProvider = MediaEntityBuilder.createScreenCaptureFromPath(screenShotName).build();
                    MediaEntityBuilder.createScreenCaptureFromPath(screenShotName).build();
                } catch (Exception ignored) {
                    errorMessage = Arrays.toString(ignored.getStackTrace());
                }
                ExtentTestManager.getTest().log(Status.FAIL, description + "<br>"+ errorMessage +"</br>");
                IOUtil.setScreenShotFileName();
            }
        }
    }

    /**
     * Gets the given attribute from an element. Returns "Error" if there was an
     * error thrown.
     *
     * @param element   The WebElement to get the given attribute of
     * @param attribute String for the attribute name to get from the WebElement
     * @return A String for the given attribute from the given WebElement,
     * returns "Error" if an error or exception was thrown.
     */
    public String getAttribute(WebElement element, String attribute) {
        softAssert.setCause(null);
        try {
            return element.getAttribute(attribute);
        } catch (Exception | Error e) {
            ConsoleLog.error("Unable to get attribute from attribute", e);
            softAssert.setCause(e);
            return "Error";
        }
    }

    /**
     * author Sree
     * Checks if given {by element} is present.
     **/

    public boolean isElementPresent(By by) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElements(by).size() != 0;
    }

    public void setInsertResults(InsertResults insertResults) {
        this.insertResults = insertResults;
    }

    public InsertResults getInsertResults() {
        return insertResults;
    }
}