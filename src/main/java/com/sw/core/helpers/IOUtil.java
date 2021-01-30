package com.sw.core.helpers;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class IOUtil {
    protected static final String FULL_PAGE_SCREENSHOT = "webDriver.fullPageScreenshot";
    protected static final String SCREENSHOT_SCROLL_TIME = "screenshot.scrollTime";
    private static final String SAUCE_ENABLED = "sauce.isEnabled";
    private static final String TASKLIST = "tasklist";
    private static final String TASKLIST_UNIX = "ps -ef";
    private static final String KILL = "taskkill /F /IM ";
    private static final String KILL_UNIX = "pkill ";
    private static Common common;

    public static void setLogDirectory() {
        // currentProjectName
        String currentProjectName = new File("").getAbsoluteFile().getName();

        // environment
        currentProjectName = currentProjectName.concat("/Reports/" + System.getProperty("webDriver.environment"));

        // Date
        currentProjectName = currentProjectName.concat("/" + new SimpleDateFormat("MM-dd-yyyy").format(new GregorianCalendar().getTime()));

        // Time
        currentProjectName = currentProjectName.concat("/" + new SimpleDateFormat("HH.mm.ss").format(new GregorianCalendar().getTime()));

        // screenShotLocation
        String screenShotLocation = System.getProperty("user.home").replace('\\', '/') + "/Selenium/Ant/" + currentProjectName;

        String ssl = System.getProperty("screenshotLocation");
        if (StringUtils.isBlank(ssl)) {
            ConsoleLog.info("Currently not running through ANT. Rosetta custom report will not be generated.");

            File screenshotFile = new File(screenShotLocation);
            if (!screenshotFile.exists()) {
                ConsoleLog.info("Making Directory at " + screenShotLocation);
                System.setProperty("screenshotLocation", screenShotLocation);
                screenshotFile.mkdirs();
            }
        } else {
            ConsoleLog.info("Currently running ANT. Sherwin report will be generated at: " + ssl);
        }
    }

    public static void setCommon(Common com) {
        common = com;
    }

    public static File highlightAndTakeScreenshot(WebDriver driver, List<WebElement> failedElements) {
        if (failedElements == null) {
            failedElements = new LinkedList<>();
        }
        File failureImageFileName = null;
        List<String> oldStyles = new ArrayList<>();
        List<WebElement> alteredElements = new ArrayList<>();

        // set statement for style
        String args = "arguments[0].setAttribute('style', arguments[1]);";

        // new css style, with border
        String highlightStyle = "border: 3px solid red;display:block;z-index:99999;";

        // declare javascript executor
        if (driver != null) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            try {
                for (WebElement failedElement : failedElements) {
                    boolean isBody = false;

                    if (failedElement == null) {
                        failedElement = driver.findElement(By.tagName("body"));
                        isBody = true;
                    } else {
                        try {
                            if (common.getElements(failedElement, By.xpath(".")).size() == 0) {
                                failedElement = driver.findElement(By.tagName("body"));
                                isBody = true;
                            }
                        } catch (Exception | Error e) {
                            failedElement = driver.findElement(By.tagName("body"));
                            isBody = true;
                        }
                    }

                    if (!isBody) {
                        // store element's current css style
                        String oldStyle = failedElement.getAttribute("style");
                        oldStyles.add(oldStyle);

                        // highlight active element
                        //We use this normal way, because the common method will reset the cause exception.
                        try {
                            js.executeScript("arguments[0].scrollIntoView(true);", failedElement);
                        } catch (Exception | Error e) {
                            ConsoleLog.error("Unable to move to element.", e);
                        }
                        js.executeScript(args, failedElement, highlightStyle);
                        Thread.sleep(1000);
                        alteredElements.add(failedElement);
                    }
                }
            } catch (Exception | Error e) {
                ConsoleLog.error("Failed to highlight the last known element - this should not impact the result of the test", e);
            }
            try {
                if (alteredElements.size() > 0) {
                    // take screenShot
                    failureImageFileName = takeScreenShot(driver);

                    //Revert
                    for (int i = 0; i < alteredElements.size(); i++) {
                        //s de-highlight active element
                        js.executeScript(args, alteredElements.get(i), oldStyles.get(i));
                    }
                }
            } catch (Exception | Error e) {
                ConsoleLog.error("Error taking screenshot - Will not fail test by itself, but this exception could be same as what caused failure.", e);
            }

            if (failureImageFileName == null) {
                // take screenShot
                failureImageFileName = takeScreenShot(driver);
            }
        }
        return failureImageFileName;
    }

    private static File takeScreenShot(WebDriver driver) {
        File newFile = null;
        if (driver != null) {
            boolean shouldTakeFullPageScreenshot = Boolean.parseBoolean(System.getProperty(FULL_PAGE_SCREENSHOT));
            String failureImageFileName = System.getProperty("lastSreenshotFileName");

            if (StringUtils.isBlank(failureImageFileName)) {
                failureImageFileName = setScreenShotFileName();
            }

            AShot captureImage = new AShot();
            WebDriver webDriver = driver;
            List<WebElement> floatingElements = new LinkedList<>();
            String originalBodyOverflow = "";

            // handler for identifying page overlay's
            if (!(webDriver instanceof AppiumDriver) && webDriver.findElement(By.xpath("/html/body")).getAttribute("class").contains("dialog")) {
                shouldTakeFullPageScreenshot = false;
            }

            if (shouldTakeFullPageScreenshot) {
                if (!(webDriver instanceof AppiumDriver)) {
                    // hide browser scrollbar
                    originalBodyOverflow = (String) ((JavascriptExecutor) webDriver).executeScript("return document.body.style.overflow;");
                    ((JavascriptExecutor) webDriver).executeScript("document.body.style.overflow = 'hidden';");
                }
                // check if driver instance is a mobile emulator
                if (((RemoteWebDriver) webDriver).getCapabilities().is("mobileEmulationEnabled")) {
                    // hide any floating elements for the mobile emulator
                    String query = "var aResult=new Array();var a=document.evaluate('//*',document,null,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,null);for(var i=0;i<a.snapshotLength;i++){var style=document.defaultView.getComputedStyle(a.snapshotItem(i),'');if(!style){continue;}if(style.getPropertyValue(\"position\")==\"fixed\"){aResult.push(a.snapshotItem(i));}}return aResult;";
                    floatingElements = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(query);
                    if (!floatingElements.isEmpty()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].forEach(item=>item.style.position=arguments[1]);", floatingElements, "absolute");
                    }
                    captureImage = captureImage.shootingStrategy(ShootingStrategies.viewportRetina(Integer.parseInt(System.getProperty(SCREENSHOT_SCROLL_TIME)) + 1000, 0, 0, 2.0f));
                } else if (webDriver instanceof AppiumDriver) {
                    // place holder for mobile device shooting strategy
                } else {
                    captureImage = captureImage.shootingStrategy(ShootingStrategies.viewportPasting(Integer.parseInt(System.getProperty(SCREENSHOT_SCROLL_TIME))));
                }
            }

            if (Boolean.parseBoolean(System.getProperty(SAUCE_ENABLED))) {
                // augment RemoteWebDriver, to be able to take screenShot remotely
                webDriver = new Augmenter().augment(driver);
            }

            Screenshot screenshot = captureImage.takeScreenshot(webDriver);
            if (shouldTakeFullPageScreenshot) {
                if (!(webDriver instanceof AppiumDriver)) {
                    ((JavascriptExecutor) webDriver).executeScript("document.body.style.overflow = '" + originalBodyOverflow + "';");
                }
                if (!floatingElements.isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].forEach(item=>item.style.position=arguments[1]);", floatingElements, "fixed");
                }
            }
            try {
                String ssl = System.getProperty("screenshotLocation");
                ConsoleLog.info("Copying screenshot '" + failureImageFileName + "' to: " + ssl);
                ImageIO.write(screenshot.getImage(), "PNG", new File(ssl + File.separator + failureImageFileName));
                newFile = new File(ssl + File.separator + failureImageFileName);
            } catch (IOException e) {
                ConsoleLog.error("Unable to take screenshot of driver", e);
            }
        }
        return newFile;
    }

    private static boolean isProcessRunning(String serviceName) throws Exception {
        ConsoleLog.info("Checking for running process for service name = " + serviceName);
        String taskList;
        if (SystemUtils.IS_OS_WINDOWS) {
            taskList = TASKLIST;
        } else {
            taskList = TASKLIST_UNIX;
        }
        Process p = Runtime.getRuntime().exec(taskList);
        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(serviceName)) {
                return true;
            }
        }
        return false;
    }

    private static void killProcess(String serviceName) throws Exception {
        ConsoleLog.info("Killing running process for service name = " + serviceName);
        String kill;
        if (SystemUtils.IS_OS_WINDOWS) {
            kill = KILL;
        } else {
            kill = KILL_UNIX;
        }
        Runtime.getRuntime().exec(kill + serviceName);
    }

    public static void killPendingProcesses(String... processNames) throws Exception {
        String os = System.getProperty("os.name").toUpperCase();

        if (os.contains("WINDOWS") || os.contains("VISTA") || SystemUtils.IS_OS_UNIX) {
            for (String processName : processNames) {
                if (SystemUtils.IS_OS_UNIX) {
                    processName = processName.replace(".exe", "");
                }
                if (isProcessRunning(processName)) {
                    killProcess(processName);
                }
            }
        }
    }

    public static String setScreenShotFileName() {
        String screenShotLocation = System.getProperty("screenshotLocation");

        File screenShotFile = new File(screenShotLocation);

        if (!screenShotFile.exists()) {
            ConsoleLog.info("Recreating the screenshot's files location");
            screenShotFile.mkdirs();
        }

        int lastScreenshotPrefix = 0;
        for (File fileOrDirectory : Objects.requireNonNull(screenShotFile.listFiles())) {
            if (fileOrDirectory.isFile()) {
                String name = fileOrDirectory.getName();
                if (name.endsWith(".png") && name.contains("_")) {
                    name = name.substring(0, name.lastIndexOf("."));
                    String suffixName = name.substring(name.lastIndexOf('_') + 1);

                    if (Integer.parseInt(suffixName) > lastScreenshotPrefix) {
                        lastScreenshotPrefix = Integer.parseInt(suffixName);
                    }
                }
            }
        }

        String lastScreenShotFileName = "screenshot_" + ++lastScreenshotPrefix + ".png";
        System.setProperty("lastSreenshotFileName", lastScreenShotFileName);
        return lastScreenShotFileName;
    }

    public static String updateErrorMessage(String errorMessage) {
        return IOUtil.updateErrorMessage(errorMessage, "", "");
    }

    public static String updateErrorMessage(String errorMessage, Object actual, Object expected) {
        int separatorCount = 0;
        for (int i = 0; i < errorMessage.length(); i++) {
            if (errorMessage.charAt(i) == ';') {
                separatorCount++;
            }
        }
        if (separatorCount < 4) {
            errorMessage = IOUtil.formatForXSLProcessing(errorMessage, actual.toString(), expected.toString());
        }
        return errorMessage;
    }

    /**
     * Format the provided parameters for the XSLT processor. A last string value will be appended to the list representing a HTML tag id.
     *
     * @param errorMessage  The message that should be shown on the final report
     * @param actualValue   The actual value that should be shown on the final report
     * @param expectedValue The expected value that should be shown on the final report
     * @return A semi-colon string values that will be passed to the XSLT processor
     */
    public static String formatForXSLProcessing(String errorMessage, String actualValue, String expectedValue) {
        return (StringUtils.isBlank(errorMessage) ? "-" : errorMessage) + " - ;" +
                (StringUtils.isBlank(actualValue) ? "-" : actualValue) + ";" +
                (StringUtils.isBlank(expectedValue) ? "-" : expectedValue) + ";" +
                setScreenShotFileName() + ";" +
                UUID.randomUUID().toString().substring(20).replace("-", "");
    }
}