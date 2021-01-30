package com.sw.proapp.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.locators.PaintCalculatorPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PaintCalculatorPage extends PaintCalculatorPageLocators {

    public PaintCalculatorPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    public String getTitle() {
        return pageTitle.getText();
    }

}