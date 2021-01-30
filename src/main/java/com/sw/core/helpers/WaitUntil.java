package com.sw.core.helpers;

import com.sw.core.testsuites.MobileDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntil {
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected Common common;
    private long implicitTimeout;

    @SuppressWarnings("unused")
    private long pageTimeout;

    @SuppressWarnings("unused")
    private long scriptTimeout;

    public WaitUntil(WebDriver driver) {
        this.driver = driver;

        setTimeout(Integer.parseInt(System.getProperty(MobileDriverFactory.BROWSER_TIME_OUT)));
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;

        setTimeout(Integer.parseInt(System.getProperty(MobileDriverFactory.BROWSER_TIME_OUT)));
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public void setTimeoutValues(long implicitTimeout, long pageTimeout, long scriptTimeout) {
        this.implicitTimeout = implicitTimeout;
        this.pageTimeout = pageTimeout;
        this.scriptTimeout = scriptTimeout;
        wait = new WebDriverWait(driver, implicitTimeout, (long) .5);
    }

    public void setTimeout(long timeoutInSeconds) {
        wait = new WebDriverWait(driver, timeoutInSeconds, (long) .5);
    }

    /**
     * Waits until the given element is visible.
     *
     * @param element      WebElement
     * @param errorMessage custom error message
     * @return true or false
     */
    public boolean visibilityOfElements(WebElement element, String errorMessage) {
        boolean success = true;
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        try {
            common.sleepFor(2);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for element to become visible did not work as expected. -- " + errorMessage, e);
            common.setTestResultFalse(errorMessage);
            success = false;
        }
        return success;
    }


    public boolean visibilityOfAllElementsLocatedBy(By locator, String errorMessage) {
        boolean success = true;
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for elements to become visible did not work as expected. -- " + errorMessage, e);
            common.setTestResultFalse(errorMessage);
            success = false;
        }
        return success;
    }

    /**
     * Waits for the presence of an element with the given By locator.
     *
     * @param locator      By locator for the element to look for
     * @param errorMessage String for the error message to report.
     * @return true or false
     */
    public boolean presenceOfElement(By locator, String errorMessage) {
        boolean success = true;
        try {
            errorMessage = IOUtil.updateErrorMessage(errorMessage);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for element to exist did not work as expected. -- " + errorMessage, e);
            common.setTestResultFalse("An Exception has occurred.  Have an engineer check this test. - " + errorMessage);
            success = false;
        }
        return success;
    }

    /**
     * Waits for an element to be clickable, which means visible, and enabled.
     *
     * @param object       Object for the WebElement or a By Locator
     * @param errorMessage String for the Error message to report.
     * @return WebElement
     */
    public WebElement elementToBeClickable(Object object, String errorMessage) {
        WebElement webElement = null;
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        try {
            if (object instanceof By) {
                webElement = wait.until(ExpectedConditions.elementToBeClickable((By) object));
            } else {
                webElement = wait.until(ExpectedConditions.elementToBeClickable((WebElement) object));
            }
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for element to become clickable did not work as expected. -- " + errorMessage, e);
            common.setTestResultFalse("An Exception has occured.  Have an engineer check this test. - " + errorMessage);
        }
        return webElement;
    }

    /**
     * Waits for the document.readyState to change to complete.  If it is a xml document, it will automatically flip to complete.
     * <br>
     * Explanation: This is used mostly for IE, because WebDriver should automatically wait for Page Load.
     * But for some situations, this can help wait for a page to be ready to be worked on.
     */
    public boolean pageLoad() {
        common.getSoftAssert().setCause(null);
        try {
            wait.until((ExpectedCondition<Boolean>) webDriver -> {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                String state;
                try {
                    state = js.executeScript("return document.readyState").toString();
                } catch (Exception e) {
                    state = "no";
                }
                if (webDriver.getCurrentUrl().endsWith("xml")) {
                    state = "complete";
                }
                return state.equalsIgnoreCase("complete");
            });
            return true;
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for the page load did not work as expected.", e);
            common.getSoftAssert().setCause(e);
            return false;
        }
    }
    /**
     * An expectation for checking that an element is either invisible or not present on the DOM.
     *
     * @param locator used to find the element
     * @return true if element is invisible or not in DOM, false otherwise
     */
    public boolean invisibilityOfElements(By locator, String errorMessage) {
        boolean success = true;
        errorMessage = IOUtil.updateErrorMessage(errorMessage);
        try {
            success = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception | Error e) {
            ConsoleLog.error("Waiting for element to become invisible did not work as expected. -- " + errorMessage, e);
            common.setTestResultFalse(errorMessage);
            success = false;
        }
        return success;
    }
}