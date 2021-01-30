package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.locators.RegistrationPageLocators;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends RegistrationPageLocators {

    public RegistrationPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public LoginPage navigateBack() {
        ExtentTestManager.getTest().log(Status.INFO, "click Navigate Back");
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(navigateBack, "Unable to click Navigate Back");
        return new LoginPage(getCommon());
    }

    @Step()
    public String createAccountHeader() {
        return getCommon().getText(createAccountHeader, "Unable to get Create Account Header Text");
    }

    @Step()
    public String currentCustomer() {
        return getCommon().getText(currentCustomer, "Unable to get Current Customer Text");
    }

    @Step()
    public String description() {
//        if (getCommon().getDriver() instanceof IOSDriver) {
//            return getCommon().getText(description_1_ios, "Unable to get Description Text") + " " + getCommon().getText(description_2_ios, "Unable to get Description Text");
//        } else 
        if (getCommon().getDriver() instanceof AndroidDriver) {
            return getCommon().getText(description, "Unable to get Description Text");
        } else {
            return "UNKNOWN DRIVER";
        }
    }

    @Step()
    public String newToSW() {
        return getCommon().getText(newToSW, "Unable to get Footer Text");
    }

    @Step()
    public String visitLocal() {
//        if (getCommon().getDriver() instanceof IOSDriver) {
//            return getCommon().getText(visitLocal_1_ios, "Unable to get Footer Text") + " " +getCommon().getText(visitLocal_2_ios, "Unable to get Footer Text");
//        } else 
        if (getCommon().getDriver() instanceof AndroidDriver) {
            return getCommon().getText(visitLocal, "Unable to get Footer Text");
        } else {
            return "UNKNOWN DRIVER";
        }
    }

    @Step()
    public void clickSherwinWillamscomPro() {
        if (getDriver() instanceof IOSDriver) {
            getCommon().sleepFor(3);
            getCommon().clickAndVerifyIsTrue(description_2_ios, "Unable to click the link");
            if (existsElement(getLocationAllowButton())) {
                getCommon().sleepFor(3);
                getCommon().clickAndVerifyIsTrue(getLocationAllowButton(),
                        "Unable to click the allow button");
            }
            if (existsElement(getPopup_ok())) {
                getCommon().sleepFor(3);
                getCommon().clickAndVerifyIsTrue(getPopup_ok(), "Unable to click the allow button");
            }
            if (existsElement(getPrivacySetting_OK())) {
                getCommon().sleepFor(3);
                getCommon().clickAndVerifyIsTrue(getPrivacySetting_OK(),
                        "Unable to click the allow button");
            }
            if (existsElement(getClose_popup_ok())) {
                getCommon().sleepFor(3);
                getCommon().clickAndVerifyIsTrue(getClose_popup_ok(),
                        "Unable to click the Close popup button");
            }
            getCommon().sleepFor(3);
        } else {
            //Samsung Tab
            if (getMobileCapability("testobject_device_name").equals("Android Emulator")) {
                new TouchAction(getDriver()).tap(PointOption.point(524, 609)).perform();
            }

            if (getMobileCapability("testobject_device_name").equals("Samsung Galaxy Note 9")) {
                new TouchAction(getDriver()).tap(PointOption.point(970, 1276)).perform();
                getCommon().sleepFor(3);
                try {
                    getCommon().getDriver().findElement(By.xpath("//*[@text='Allow']")).click();
                    new TouchAction(getDriver()).tap(PointOption.point(280, 2651)).perform();
                } catch (Exception e) {
                }
            }
            if (getMobileCapability("testobject_device_name").equals("Google Pixel 3")) {
                new TouchAction(getDriver()).tap(PointOption.point(689, 927)).perform();
                getCommon().sleepFor(3);
                try {
                    getCommon().getDriver().findElement(By.xpath("//*[@text='Allow']")).click();
                    new TouchAction(getDriver()).tap(PointOption.point(213, 1917)).perform();
                } catch (Exception e) {
                }
            }
        }
    }

    @Step("getting MobileCapability {0}")
    private String getMobileCapability(String key) {

        String keyValue = null;
        RemoteWebDriver remoteWebDriver = (RemoteWebDriver) getCommon().getDriver();
        keyValue = remoteWebDriver.getCapabilities().getCapability(key).toString();
        ConsoleLog.info("getMobileCapability :" + key + " " + keyValue);
        return keyValue;
    }

    @Step()
    public void clickRepContactYou() {
        if (getDriver() instanceof IOSDriver) {
            getCommon().clickAndVerifyIsTrue(visitLocal_2_ios, "Unable to click the link");
            getCommon().clickAndVerifyIsTrue(getPopup_ok(), "Unable to click the allow button");
            getCommon().sleepFor(3);
        } else {
            //Samsung Tab
            if (getMobileCapability("testobject_device_name").equals("Android Emulator")) {
                new TouchAction(getDriver()).tap(PointOption.point(636, 1127)).perform();
            }

            if (getMobileCapability("testobject_device_name").equals("Samsung Galaxy Note 9"))
                new TouchAction(getDriver()).tap(PointOption.point(250, 2667)).perform();

            if (getMobileCapability("testobject_device_name").equals("Google Pixel 3"))
                new TouchAction(getDriver()).tap(PointOption.point(851, 1792)).perform();
        }
    }

    @Step()
    public String getBrowserUlrText() {
        String url;
        if (getDriver() instanceof IOSDriver) {
            //  getCommon().getDriver().findElement(By.xpath("//*[@text='Allow']")).click();
            // return getCommon().getText(browserURL, "Unable to get text from URL");
            return getCommon().getAttribute(browserURL, "value");
        } else {
            if (getCommon().isElementPresent(By.xpath("//*[@text='Allow' or @text='ALLOW']")))
                getCommon().getDriver().findElement(By.xpath("//*[@text='Allow' or @text='ALLOW']")).click();
            getCommon().clickAndVerifyIsTrue(browserURL, "Unable to click te url address");
            if(existsElement(browserURLEdit))
                getCommon().clickAndVerifyIsTrue(browserURLEdit, "Unable to click te url address");
            getCommon().sleepFor(2);
            url = getCommon().getText(browserURL, "Unable to get  the url");
            pressEnter();
            if (getDriver().getPageSource().contains("Touch and hold these icons to see your browsing history"))
                clickBackButton();
            return url;
        }
    }

    @Step()
    public String getiOSYourSherwinPro() {

        return getCommon().getAttribute(youSherwinPro,"label");//, "Unable to get the text from webpage");
    }

    @Step()
    public String getLocalRep() {
        return getCommon().getAttribute(localRep,"label"); //"Unable to get the text from webpage");
    }

    @Step()
    public void clickDone() {
        getCommon().clickAndVerifyIsTrue(doneLink, "Unable to click the done link");
    }
}