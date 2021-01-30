package com.sw.core.helpers;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import io.appium.java_client.AppiumDriver;

public class JavaScriptUtil {

    public static void mobileSwipeToLeft(AppiumDriver driver, WebElement element) {
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("direction", "left");
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        driver.executeScript("mobile: swipe", scrollObject);
    }

    public static void mobileScrollDown(AppiumDriver driver) {
        try {
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("direction", "down");
            driver.executeScript("mobile: scroll", scrollObject);
        } catch (Exception ignored) {
        }
    }
    /**
     * This method sets the browser scroll mode to auto
     *
     * @param driver WebDriver instance
     * @return true if able to set the browser scroll type to auto else false
     */
    public static boolean setBrowserScrollToAuto(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("return document.querySelector('html').style.scrollBehavior = 'auto';");
            return true;
        } catch (Exception exception) {
            ConsoleLog.info("Exception occurred while setting browser scroll to 'AUTO', see below for the exception details " + exception);
            return false;
        }
    }

    /**
     * Hides the given element
     *
     * @param selector CSS selector
     * @param driver   WebDriver instance
     */
    public static void makeElementInvisible(String selector, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "var invisibleElements = document.querySelectorAll('" + selector + "');" +
                        "invisibleElements.forEach(function(elem) {elem.style.visibility = 'hidden';});");
    }

}
