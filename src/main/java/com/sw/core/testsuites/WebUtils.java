package com.sw.core.testsuites;

import com.sw.core.helpers.ConsoleLog;
import com.sw.core.helpers.PropsUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WebUtils {
    public static final String MOBILE_EMULATION_SWITCH = "TurnOnMobileEmulation";

    private static void setIECapabilities(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
    }

    private static void setFFCapabilities(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilities.setCapability("marionette", true);
    }

    public static void loadWebAdditionalCapability(ITestResult result, String _driverName) {
        String additionalCapability;
        if (_driverName.toLowerCase().contains("chrome")) {
            additionalCapability = result.getTestContext().getCurrentXmlTest()
                    .getParameter("chrome.additional.capabilities");
            if (additionalCapability != null && !additionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("chrome.additional.capabilities", additionalCapability);
            }
        } else if (_driverName.toLowerCase().contains("firefox")) {
            additionalCapability = result.getTestContext().getCurrentXmlTest()
                    .getParameter("firefox.additional.capabilities");
            if (additionalCapability != null && !additionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("firefox.additional.capabilities", additionalCapability);
            }
        } else if (_driverName.toLowerCase().contains("internetexplorer")) {
            additionalCapability = result.getTestContext().getCurrentXmlTest()
                    .getParameter("internetexplorer.additional.capabilities");
            if (additionalCapability != null && !additionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("internetexplorer.additional.capabilities", additionalCapability);
            }
        } else if (_driverName.toLowerCase().contains("safari")) {
            additionalCapability = result.getTestContext().getCurrentXmlTest()
                    .getParameter("safari.additional.capabilities");
            if (additionalCapability != null && !additionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("safari.additional.capabilities", additionalCapability);
            }
        } else if (_driverName.toLowerCase().contains("edge")) {
            additionalCapability = result.getTestContext().getCurrentXmlTest()
                    .getParameter("edge.additional.capabilities");
            if (additionalCapability != null && !additionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("edge.additional.capabilities", additionalCapability);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public WebDriver createDriverInstance(String driverName) {
        WebDriver driver;
        String userHome = System.getProperty("user.home").replace('\\', '/') + "/";
        String driverPath;
        try {
            switch (driverName.toUpperCase()) {
                case "INTERNETEXPLORER":
                    ConsoleLog.info("Setting up Internet Explorer Local Browser");
                    WebDriverManager.iedriver().clearPreferences().setup();

                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                    setIECapabilities(capabilities);
                    driver = new InternetExplorerDriver(capabilities);
                    break;
                case "CHROME":
                    ConsoleLog.info("Setting up Chrome Local Browser");
                    WebDriverManager.chromedriver().clearPreferences().setup();

                    capabilities = DesiredCapabilities.chrome();
                    if (Boolean.parseBoolean(System.getProperty(MOBILE_EMULATION_SWITCH))) {
                        ChromeOptions options = new ChromeOptions();
                        Map<String, Object> mobileEmulation = new HashMap<>();
                        mobileEmulation.put("deviceName", System.getProperty("webDriver.deviceName"));
                        options.setExperimentalOption("mobileEmulation", mobileEmulation);
                        options.setExperimentalOption("mobileEmulation", mobileEmulation);
                        driver = new ChromeDriver(options);
                    } else {
                        driver = new ChromeDriver(capabilities);
                    }
                    break;
                case "SAFARI":
                    ConsoleLog.info("Setting up Safari Local Browser");
                    capabilities = DesiredCapabilities.safari();
                    driver = new SafariDriver(capabilities);
                    break;
                case "FIREFOX":
                    ConsoleLog.info("Setting up Default Local Browser(FireFox)");
                    WebDriverManager.firefoxdriver().clearPreferences().setup();

                    capabilities = DesiredCapabilities.firefox();
                    setFFCapabilities(capabilities);
                    driver = new FirefoxDriver(capabilities);
                    break;
                default:
                    ConsoleLog.info("Setting up Default Local Browser(Chrome)");
                    WebDriverManager.chromedriver().clearPreferences().setup();
                    capabilities = DesiredCapabilities.chrome();
                    driver = new ChromeDriver(capabilities);
                    break;
            }
        } catch (Exception e) {
            ConsoleLog.error("Unable to create a local instance of the " + driverName + " browser.", e);
            driver = null;
            e.printStackTrace();
        }
        return driver;
    }

    public Properties loadProperties(String driverName, String propertyToLoad) {
        Properties applicationProps = new Properties();
        FileInputStream fs;

        //Load common configuration files
        PropsUtil.setSystemProperties(SuiteUtil.WEB_RESOURCE_PATH + "commonConfig.properties");

        // Load locator properties for specific channel
        Properties props = new Properties();

        try {
            if (!propertyToLoad.endsWith(".properties"))
                propertyToLoad = propertyToLoad + ".properties";
            fs = new FileInputStream(SuiteUtil.WEB_RESOURCE_PATH + "//" + propertyToLoad);
            applicationProps.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in Loading application properties");
        }
        props.putAll(applicationProps);

        // Load channel specific application properties
        try {
            props.put("driver.name", driverName);
            MobileDriverFactory.props.set(props);

            InputStream input = new FileInputStream(SuiteUtil.WEB_RESOURCE_PATH + "//" + propertyToLoad);
            Properties prop = new Properties();
            prop.load(input);
            Enumeration<Object> enuKeys = prop.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = prop.getProperty(key);
                System.getProperties().setProperty(key, value);
            }
            input.close();
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in Loading application properties");
        }
        return null;
    }
}
