package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaTasksPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PricingMethodPage extends NewAreaTasksPageLocators {

    public PricingMethodPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }
    
    public void selectTaskPriceOption(String language) {
    	if (getCommon().getDriver() instanceof IOSDriver) {
    		clickElementByText("Task Prices°Precios por trabajo", language);
    	
		} else {
			clickElementByText("Task Price°Precios por trabajo", language);
		}
    }
}