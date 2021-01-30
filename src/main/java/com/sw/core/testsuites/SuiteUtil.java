package com.sw.core.testsuites;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.google.gson.Gson;
import com.sw.core.helpers.*;
import com.sw.core.reporting.ExtentTestManager;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


public class SuiteUtil {
    public static final String WEB_RESOURCE_PATH = System.getProperty("user.dir")
            + "\\src\\test\\resources\\config\\web\\";
    public static final String NATIVE_RESOURCE_PATH = System.getProperty("user.dir")
            + "\\src\\test\\resources\\config\\native\\";

    /**
     * Reusable bit of code that wasn't separated before. Creates a new Common class
     * with the given driver and wait timeouts set properly.
     *
     * @param driver WebDriver
     * @return instance of the Common
     */
    public static Common createNewCommon(RemoteWebDriver driver) {
        Common common = new Common(driver, new SoftAssert(driver), new WaitUntil(driver));
        common.getWaitUntil().setCommon(common);
        common.setTimeoutValues(Long.parseLong(System.getProperty(MobileDriverFactory.BROWSER_TIME_OUT)),
                Long.parseLong(System.getProperty(MobileDriverFactory.PAGE_LOAD_TIME)),
                Long.parseLong(System.getProperty(MobileDriverFactory.SCRIPT_TIME_OUT)));
        return common;
    }

    public static Common createNewMobileCommon(WebDriver driver) {
        Common common = new Common(driver, new SoftAssert(driver), new WaitUntil(driver));
        common.getWaitUntil().setCommon(common);
        common.setTimeoutValues(Long.parseLong(System.getProperty(MobileDriverFactory.BROWSER_TIME_OUT)),
                Long.parseLong(System.getProperty(MobileDriverFactory.PAGE_LOAD_TIME)),
                Long.parseLong(System.getProperty(MobileDriverFactory.SCRIPT_TIME_OUT)));
        return common;
    }

    public static Properties createPropertiesInstance(String env_resources) {
        Properties props = null;
        try {
            FileInputStream fs;
            props = new Properties();
            ArrayList<File> dirfiles = new ArrayList<>();
            dirfiles = listf(env_resources, dirfiles);
            for (File file : dirfiles) {
                fs = new FileInputStream(file.getAbsoluteFile());
                props.load(fs);
            }
        } catch (Exception e) {
            System.out.println("Can not create property object" + e);
        }
        return props;
    }

    public static void loadPropertyFiles(String resourcePath) {
        try {
            ArrayList<File> dirfiles = new ArrayList<>();
            dirfiles = SuiteUtil.listf(System.getProperty("user.dir") + "/src/test/resources/data/", dirfiles);
            MobileDriverFactory.getConfiguration().setEncoding("UTF-8");
            for (File file : dirfiles) {
                if (file.getName().endsWith("xml") || file.getName().contains(".xml.")) {
                    MobileDriverFactory.getConfiguration().load(new FileInputStream(file));
                } else {
                    PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
                    propertiesConfiguration.setEncoding("UTF-8");
                    propertiesConfiguration.load(new FileInputStream(file));
                }
            }
            // Load common properties in system
            InputStream input = new FileInputStream(resourcePath + "commonConfig.properties");
            Properties prop = new Properties();
            prop.load(input);
            Enumeration<Object> enuKeys = prop.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = prop.getProperty(key);
                System.getProperties().setProperty(key, value);
            }
            input.close();
        } catch (ConfigurationException | IOException e) {
            e.getMessage();
        }
    }

    public static ArrayList<File> listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
        return files;
    }

    public static Properties getProps() {
        if (MobileDriverFactory.props.get() == null) {
            System.out.println("no properties object found........");
        }
        return MobileDriverFactory.props.get();
    }

    /*
     * Get Property string with key
     */
    public static String getProp(String propertyKey) {
        if (!isEmpty(propertyKey))
            return getProps().getProperty(propertyKey);
        else
            return propertyKey;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equals("");
    }

    public static void waitFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> splitProperty(String propStr) {
        return new Gson().fromJson(propStr, HashMap.class);
    }

    public static void setTunnel(DesiredCapabilities desiredCapabilities) {
        try {
            String activeTunnel = SauceUtil.getActiveTunnel();
            if (activeTunnel != null) {
                desiredCapabilities.setCapability("tunnelIdentifier", "SherwinSharedTunnel");
            }
        } catch (Exception e) {
            ConsoleLog.info(" >>>>>>>> Trying again to get active tunnel");
            try {
                String activeTunnel = SauceUtil.getActiveTunnel();
                if (activeTunnel != null) {
                    desiredCapabilities.setCapability("tunnelIdentifier", "SherwinSharedTunnel");
                }
            } catch (Exception e1) {
                ConsoleLog.info(">>>>>>>> problem while getting active tunnel");
                e1.printStackTrace();
            }
        }
    }

    /**
     * Reports logger in extent report with screenshot
     *
     * @throws Exception exception
     */
    public void logReportWithScreenShot(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotName = createFailedTestScreenShot();
            String screenshotPathForTestNG = "../target/" + screenshotName;
            String fromJenkins = System.getProperties().getProperty("jenkinsBuildId");
            if (fromJenkins != null && !fromJenkins.trim().isEmpty()) {
               // MediaEntityModelProvider mediaEntityModelProviderJenkins = MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build();
                Media media = MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build();
                ExtentTestManager.getTest().fail(result.getTestName(), media);
            } else if (fromJenkins == null || fromJenkins.trim().isEmpty()) {
                //MediaEntityModelProvider mediaEntityModelProviderTestNG = MediaEntityBuilder.createScreenCaptureFromPath(screenshotPathForTestNG).build();
                Media media = MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build();
                ExtentTestManager.getTest().fail(result.getTestName(), media);
            }

            ExtentTestManager.getTest().log(Status.FAIL, ExceptionUtils.getStackTrace(result.getThrowable()));
            ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(Status.SKIP, result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().log(Status.PASS, result.getTestName() + " test case is passed ");
        }
    }

    public String createFailedTestScreenShot() {
        // For Extent Report
        File screenshot = ((TakesScreenshot) MobileDriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        String destination;
        String buildIdJenkins = System.getProperties().getProperty("jenkinsBuildId");
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String screenShotName = screenshot.getName().replace(".png", "") + "_" + dateName + ".png";
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
        return screenShotName;
    }
}