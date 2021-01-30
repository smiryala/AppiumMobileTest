package com.sw.core.helpers;

import com.sw.core.testsuites.MobileCoreBaseTest;

import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {
    //Browser Information
    public static final String CONFIG = "webDriver.config";
    public static final String TARGET_URL = "webDriver.targetUrl";
    public static final String HUB_URL = "webDriver.hubUrl";
    public static final String ENVIRONMENT_TYPE = "webDriver.environment";
    public static final String BROWSER_TYPE = "webDriver.browserType";
    public static final String BROWSER_PLATFORM = "webDriver.browserPlatform";
    public static final String BROWSER_VERSION = "webDriver.browserVersion";
    public static final String SELENIUM_VERSION = "selenium.version";
    public static final String GRID_ENABLED = "grid.isEnabled";
    //SAUCE LABS
    public static final String SAUCE_ENABLED = "sauce.isEnabled";
    public static final String SAUCE_CONNECT_ENABLED = "sauce.isConnectEnabled";
    public static final String SAUCE_USER_NAME = "sauce.UserName";
    public static final String SAUCE_KEY = "sauce.key";

    //JIRA
    public static final String JIRA_ENABLED = "jira.isJiraEnabled";
    public static final String TIMETRACKING_ENABLED = "jira.timeTrackingEnabled";
    public static final String FIX_VERSION_ENABLED = "jira.fixVersionEnabled";
    public static final String FIX_VERSION = "jira.fixVersion";
    public static final String JIRA_PROJECT_CODE = "jira.code";
    public static final String JIRA_USERNAME = "jira.userName";
    public static final String JIRA_PASSWORD = "jira.password";
    public static final String JIRA_ASSIGNEE = "jira.assignee";
    public static final String JIRA_URI = "jira.uri";

    //TIMEOUTS
    public static final String PAGE_LOAD_TIME = "page.loadTime";
    public static final String BROWSER_TIME_OUT = "webDriver.timeOut";
    public static final String SCRIPT_TIME_OUT = "script.timeOut";
    public static final String BROWSER_MAX_DURATION = "browser.maxDuration";

    //Mobile
    public static final String USER_AGENT_SWTICHER = "userAgent.switcher";
    public static final String MOBILE_EMULATION_SWITCH = "TurnOnMobileEmulation";
    public static final String MOBILE_DEVICE_NAME = "mobileEmulation.deviceName";

    public static Properties setSystemProperties(String... configFiles) {
        Properties config = null;

        for (String configFile : configFiles) {
            try {
                config = PropsUtil.loadConfigProps(configFile, config);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String propertyName : config.stringPropertyNames()) {
                System.setProperty(propertyName, config.getProperty(propertyName));
            }
        }
        return config;
    }

    public static Properties loadConfigProps(String configFile, Properties def) throws Exception {
        Properties config;
        if (def != null) {
            config = new Properties(def);
        } else {
            config = new Properties();
        }

        try (InputStream stream = Common.class.getClassLoader().getResourceAsStream(configFile)) {

            if (stream != null) {
                config.load(stream);
            }
        } catch (Exception e) {
            throw new Exception("An error occurs while reading from the input stream or the input stream contains a malformed Unicode escape sequence", e);
        }

        return config;
    }

    public static void clearSystemProperties(String configFile) {
        Properties config = null;

        try {
            config = loadConfigProps(configFile, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SecurityManager appsm = System.getSecurityManager();
        for (String propertyName : config.stringPropertyNames()) {
            System.clearProperty(propertyName);
        }
    }

    public static String getBrowserType() {
        return MobileCoreBaseTest.getProperties().getProperty(BROWSER_TYPE);
    }

    public static boolean isSauceEnabled() {
        return Boolean.parseBoolean(MobileCoreBaseTest.getProperties().getProperty(SAUCE_ENABLED));
    }

    public static boolean isGridEnabled() {
        return Boolean.parseBoolean(MobileCoreBaseTest.getProperties().getProperty(GRID_ENABLED));
    }
}