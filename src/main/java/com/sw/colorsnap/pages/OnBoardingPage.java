package com.sw.colorsnap.pages;

import com.sw.core.helpers.Common;
import com.sw.colorsnap.locators.OnBoardingPageLocators;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class OnBoardingPage extends OnBoardingPageLocators {

    public OnBoardingPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public OnBoardingPage clickColorSnapSkipButton() {

        if (getDriver() instanceof IOSDriver) {
            if (getCommon().getWaitUntil().visibilityOfElements(dontAllow, "Dont allow failed to display")) {
                getCommon().click(dontAllow, "failed to Click Dont allow");
            }
            if(((IOSDriver) getCommon().getDriver()).findElementsByAccessibilityId("Test").size()>0){
                ((IOSDriver)  getCommon().getDriver()).findElementByAccessibilityId("Test").click();
            }
        }
        getCommon().getWaitUntil().visibilityOfElements(skipButton,"Skip button not loaded");
        getCommon().click(skipButton, "Unable to click SkipButton.");
        return this;
    }


}