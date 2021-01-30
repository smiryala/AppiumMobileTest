package com.sw.core.testsuites;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.helpers.TestObjectUtil;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobileDriverFactory {

    public static final String SAUCE_ENABLED = "sauce.isEnabled";
    public static final String SAUCE_USER_NAME = "sauce.UserName";
    public static final String SAUCE_KEY = "sauce.key";
    public static final String SAUCE_CONNECT_ENABLED = "sauce.isConnectEnabled";
    public static final String BROWSER_TIME_OUT = "webDriver.timeOut";
    public static final String PAGE_LOAD_TIME = "page.loadTime";
    public static final String SCRIPT_TIME_OUT = "script.timeOut";
    public static final String APP_INSTALL = "app.install";
    public static final String APP_PATH = "app.path";
    public static final String APP_NAME = "app.name";
    public static final String APP_TYPE = "app.type";
    public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String TESTOBJECT_USER_NAME = "testobject.username";
	public static final String TESTOBJECT_KEY = "testobject.api.key";
	public static final String TESTOBJECT_API_BASEURL = "testobject.api.baseUrl";
	public static final String TESTOBJECT_API_ENDPOINT_AVAI_DEVICES = "/devices/available";
    public static MobileDriverFactory driverPage = null;
    public static XMLConfiguration xmlConfiguration = null;
    public static boolean isWebDriver = true;
    protected static ThreadLocal<Properties> props = new ThreadLocal<>();
    private Common common;
    private WebDriver driver;

    private static final Logger LOGGER = Logger.getLogger(MobileDriverFactory.class);

    
    public MobileDriverFactory() {
    }

    public static MobileDriverFactory getInstance() {
        if (driverPage == null) {
            driverPage = new MobileDriverFactory();
        }
        return driverPage;
    }

    public static XMLConfiguration getConfiguration() {
        if (xmlConfiguration == null) {
            xmlConfiguration = new XMLConfiguration();
        }
        return xmlConfiguration;
    }

    public static Properties getProps() {
        return props.get();
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public WebDriver createInstance(String driverName, String executionMode, String language,
            String testCaseName, String configFile) {
        WebDriver driver = null;

        try {
            if (driverName.contains("android")) {
                ConsoleLog.info("=========== Creating Android driver =============");
                driver = connectionToDevice(driverName, executionMode, language, testCaseName,
                        configFile);
                ConsoleLog.info("=========== Android Driver Created =============");
            } else if (driverName.contains("ios")) {
                ConsoleLog.info("=========== Creating iOS driver =============");
                driver = connectionToDevice(driverName, executionMode, language, testCaseName,
                        configFile);
                ConsoleLog.info("=========== iOS Driver Created =============");
            }
        } catch (Exception e) {
        	catchExceptionMethod(e,"createInstance");
        }
        setDriver(driver);
        setCommon(SuiteUtil.createNewMobileCommon(driver));

        return driver;
    }
    
    public void catchExceptionMethod(Exception e,String methodName)
    {
    	ConsoleLog.info("=========== " + e.getMessage() + " ERROR in this method: "+methodName+"  =============");
    	LOGGER.error(" ERROR in this method: "+methodName+"  =============", e);
    }

    public RemoteWebDriver createRemoteWebDriverInstance(String driverName, String testCaseName,
                                                         String propertyToLoad) {
        RemoteWebDriver driver = null;
        WebUtils webUtils = new WebUtils();
        webUtils.loadProperties(driverName, propertyToLoad);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String allCapabilities = null;

        try {
            ConsoleLog.info("=========== Creating " + driverName + " driver =============");
            if (getProps().getProperty("sauce.isEnabled") != null
                    && getProps().getProperty("sauce.isEnabled").equalsIgnoreCase("true")) {
                if (driverName.toLowerCase().contains("chrome")) {
                    allCapabilities = SuiteUtil.getProp("chrome.additional.capabilities");
                } else if (driverName.toLowerCase().contains("firefox")) {
                    allCapabilities = SuiteUtil.getProp("firefox.additional.capabilities");
                } else if (driverName.toLowerCase().contains("internet")) {
                    desiredCapabilities = DesiredCapabilities.internetExplorer();
                    allCapabilities = SuiteUtil.getProp("internetexplorer.additional.capabilities");
                } else if (driverName.toLowerCase().contains("safari")) {
                    allCapabilities = SuiteUtil.getProp("safari.additional.capabilities");
                } else if (driverName.toLowerCase().contains("edge")) {
                    allCapabilities = SuiteUtil.getProp("edge.additional.capabilities");
                }

                if (allCapabilities != null) {
                    desiredCapabilities = setAllCapabilities(allCapabilities);
                }

                SuiteUtil.setTunnel(desiredCapabilities);
                synchronized (this) {
                    desiredCapabilities.setCapability("name", testCaseName);
                }
                driver = new RemoteWebDriver(
                        new URL("http://" + getProps().getProperty("sauce.UserName") + ":"
                                + getProps().getProperty("sauce.key") + "@ondemand.saucelabs.com:80/wd/hub/"),
                        desiredCapabilities);
            } else {
                driver = (RemoteWebDriver) webUtils.createDriverInstance(driverName);
            }
            ConsoleLog.info("=========== " + driverName + " Driver Created =============");
        } catch (Exception e) {
        	catchExceptionMethod(e,"createRemoteWebDriverInstance");
        }

        setDriver(driver);
        setCommon(SuiteUtil.createNewCommon(driver));
        Objects.requireNonNull(driver).get(System.getProperty("targetUrl"));
        driver.manage().window().maximize();
        isWebDriver = true;
        return driver;
    }

    private DesiredCapabilities setAllCapabilities(String capabilities) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Map<String, String> propMap = SuiteUtil.splitProperty(capabilities);
        try {
            Set<String> keySetProps = propMap.keySet();
            for (String key : keySetProps) {
                desiredCapabilities.setCapability(key, propMap.get(key));
            }
        } catch (NullPointerException e) {
            catchExceptionMethod(e,"setAllCapabilities-Check that additional capabilities are defined properly");
        }
        return desiredCapabilities;
    }

    public DesiredCapabilities setCapabilities() {
        DesiredCapabilities mobileDesireCapabilities = new DesiredCapabilities();
        Properties mobileProp;
        try {
            mobileProp = System.getProperties();
            mobileProp.forEach((key, value) -> {
                if (key.toString().contains("cap."))
                    mobileDesireCapabilities.setCapability(key.toString().replace("cap.", ""), value);
            });
            ConsoleLog.info("Desired Capabilities for "
                    + mobileDesireCapabilities.getCapability("platformName")
                    + " : "
                    + mobileDesireCapabilities.getCapability("deviceName"));
            ConsoleLog.info(mobileDesireCapabilities.toString());
        } catch (Exception e) {
            catchExceptionMethod(e,
                    "setCapabilities-Check that additional capabilities are defined properly");
        }
        return mobileDesireCapabilities;
    }


    private String getAvailableAndroidDevice(String language) throws Exception {
        List<String> devices = null;
        boolean availableDevice = false;
        String deviceName = null;
        devices = TestObjectUtil.getAvailableDevices(getProps().getProperty(TESTOBJECT_USER_NAME),
                getProps().getProperty(TESTOBJECT_KEY),
                getProps().getProperty(TESTOBJECT_API_BASEURL),
                TESTOBJECT_API_ENDPOINT_AVAI_DEVICES);

        if (language.equalsIgnoreCase("spanish")) {
            availableDevice = devices.stream()
                    .anyMatch(device -> device.contains("Samsung_Galaxy_Note_9_sw_us"));
            if (availableDevice) {
                deviceName = "Samsung Galaxy Note 9";
            }
        } else {
            availableDevice = devices.stream()
                    .anyMatch(device -> device.contains("Google_Pixel_3_sw_us"));
            if (availableDevice) {
                deviceName = "Google Pixel 3";
            } else {
                availableDevice = devices.stream()
                        .anyMatch(device -> device.contains("Samsung_Galaxy_Tab_S5e_sw_us"));
                if (availableDevice) {
                    deviceName = "Samsung Galaxy Tab S5e";
                } else {
                    availableDevice = devices.stream()
                            .anyMatch(device -> device.contains("Asus_Zenpad_Z10_sw_us"));
                    if (availableDevice) {
                        deviceName = "Asus Zenpad Z10";
                    }
                }
            }
        }
        return deviceName;
    }

    private String getAvailableiOSDevice(String language) throws Exception {
        List<String> devices = null;
        boolean availableDevice = false;
        String deviceName = null;
        devices = TestObjectUtil.getAvailableDevices(getProps().getProperty(TESTOBJECT_USER_NAME),
                getProps().getProperty(TESTOBJECT_KEY),
                getProps().getProperty(TESTOBJECT_API_BASEURL),
                TESTOBJECT_API_ENDPOINT_AVAI_DEVICES);

        if (language.equalsIgnoreCase("spanish")) {
            availableDevice = devices.stream()
                    .anyMatch(device -> device.contains("iPhone_XR_sw_us"));
            if (availableDevice) {
                deviceName = "iPhone XR";
            }
        } else {
            availableDevice = devices.stream()
                    .anyMatch(device -> device.contains("iPhone_XS_Max_sw_us"));
            if (availableDevice) {
                deviceName = "iPhone XS Max";
            } else {
                availableDevice = devices.stream()
                        .anyMatch(device -> device.contains("iPhone_11_sw_us"));
                if (availableDevice) {
                    deviceName = "iPhone 11";
                } else {
                    availableDevice = devices.stream()
                            .anyMatch(device -> device.contains("iPhone_11_Pro_sw_us"));
                    if (availableDevice) {
                        deviceName = "iPhone 11 Pro";
                    }
                }
            }
        }
        return deviceName;
    }

    private WebDriver connectionToDevice(String driverName, String executionMode, String language,
            String testCaseName, String configFile) throws MalformedURLException {
        WebDriver driver;
        String deviceName = null;

        if (executionMode.equalsIgnoreCase("remote")) {

            if (driverName.equalsIgnoreCase("Android") || (driverName
                    .equalsIgnoreCase("android.web"))) {
                try {
                    deviceName = getAvailableAndroidDevice(language);
                } catch (Exception e) {
                    catchExceptionMethod(e, "connectionToDevice- Android calling test api call");
                }

            } else {
                try {
                    deviceName = getAvailableiOSDevice(language);
                } catch (Exception e) {
                    catchExceptionMethod(e, "connectionToDevice-iOS calling test api call");
                }
            }
            if(!(StringUtils.isNotBlank(deviceName))) {
    			ExtentTestManager.getTest().log(Status.FAIL, "Devices are not available to execute the scripts");
    		}
        }
		
        DesiredCapabilities desiredCapabilities = setCapabilities();

        if (deviceName == null && executionMode.equalsIgnoreCase("local")) {
            deviceName = desiredCapabilities.getCapability("deviceName").toString();
        }

        synchronized (this) {
            desiredCapabilities.setCapability("name", testCaseName);
            desiredCapabilities.setCapability("deviceName", deviceName);
        }

        if (executionMode.equalsIgnoreCase("local")) {
            if (Boolean.parseBoolean(System.getProperty(APP_INSTALL))) {
                desiredCapabilities.setCapability("app",
                        Paths.get(System.getProperty("user.dir"),getProps().getProperty(APP_PATH), getProps().getProperty(APP_NAME) + "." + getProps().getProperty(APP_TYPE)).toString());
            }
            if (driverName.contains("android"))
                driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
            else
                driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } else {

            String sauceLabsCredentials;

            if (desiredCapabilities.getCapability("deviceName").toString().matches("(?i).*Emulator.*|.*Simulator.*")) {
                sauceLabsCredentials = getProps().getProperty("sauce.UserName") + ":" + getProps().getProperty("sauce.key") + getProps().getProperty("sauce.URL");

            } else {
                sauceLabsCredentials = getProps().getProperty("testobject.host") + getProps().getProperty("testobject.endPoint");
            }
            ConsoleLog.info("Driver URL :" + "https://" + sauceLabsCredentials);
            
            //Added to wait longer to get the device, need a api call to check if the device is available or not and then create driver
            if (driverName.equalsIgnoreCase("Android")|| (driverName.equalsIgnoreCase("android.web")) ) {
            	
                driver = new AndroidDriver<>(new URL("https://" + sauceLabsCredentials), desiredCapabilities);
            }else {
                driver = new IOSDriver<>(new URL("https://" + sauceLabsCredentials), desiredCapabilities);
            }
        }
        return driver;
    }

	
}