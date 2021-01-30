package com.sw.core.testsuites;

import com.sw.core.helpers.ConsoleLog;
import com.sw.core.helpers.PropsUtil;
import org.testng.ITestContext;

import java.util.Properties;

public class NativeUtils {

    public static void loadNativeAdditionalCapability(ITestContext context, String _driverName) {
        if (_driverName.equalsIgnoreCase("ios")) {
            String iosAdditionalCapability = context.getCurrentXmlTest()
                    .getParameter("ios.additional.capabilities");
            if (iosAdditionalCapability != null && !iosAdditionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("ios.additional.capabilities", iosAdditionalCapability);
            }
        } else {
            String androidAdditionalCapability = context.getCurrentXmlTest()
                    .getParameter("android.additional.capabilities");
            if (androidAdditionalCapability != null && !androidAdditionalCapability.trim().isEmpty()) {
                System.getProperties().setProperty("android.additional.capabilities", androidAdditionalCapability);
            }
        }
    }

    public static Properties loadProperties(String driverName,String configFile) {
        Properties applicationProps = null;
        String platformPropertiespath = "config/native/" + driverName + ".application.properties";
        try {
        //Load configuration files
        applicationProps = PropsUtil.setSystemProperties("config/commonConfig.properties",platformPropertiespath,configFile);
            ConsoleLog.info("properties laod :-> " + applicationProps.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in Loading application properties");
        }

        try {
            applicationProps.put("driver.name", driverName);
            MobileDriverFactory.props.set(applicationProps);
            return applicationProps;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed in Loading application properties");
        }
        return null;
    }
}
