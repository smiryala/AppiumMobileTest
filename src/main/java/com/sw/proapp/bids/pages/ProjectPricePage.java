package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.ProjectPricePageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProjectPricePage extends ProjectPricePageLocators {

    public ProjectPricePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


  public void clickUnitDropDown() {
	  if (getCommon().getDriver() instanceof IOSDriver) {
		  
	  }
	  else {
	  projectUnitsDropDown.click();
	  }
  }
  
  public void clickDone(String language) {
  	if (getCommon().getDriver() instanceof IOSDriver) {
  		clickElementByElementName("saveButton", language);
  	
		} else {
			clickElementByElementName("DONE°LISTO", language);
		}
  }
}