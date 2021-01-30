package com.sw.proapp.bids.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaPricePageLocators;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class NewAreaPricePage extends NewAreaPricePageLocators {

    public NewAreaPricePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }
    
    public void enterFlatRateValue(String value) {
    	 if (getCommon().getDriver() instanceof IOSDriver) {
    		 enterTextToElementByText("$0.00 (USD)", value+"00");
    		
    		} else {
    			enterTextToElementByText("$0.00", value);
    			enterTextToElementByExactText("Price", value);
    		}    
    	
    }
    
    public void clickFlatRateTabString(String language) {
    if (getCommon().getDriver() instanceof IOSDriver) {
    	clickElementByElementName("flatRateLabel°Costo Fijo", language);
	
	} else {
		clickElementByText("Flat Rate", language);
	}    
}

    public boolean isNewAreaPricePageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("area price") || pageSource.contains("PRESUPUESTO  DEL ÁREA".toLowerCase());
    }

    public WebElement getCostUnitInput() {
        return costInput;
    }

    public boolean costUnitCtaIsVisible() {
        return getCommon().isDisplayed(costUnitCta);
    }

    public boolean costInputIsVisible() {
        return getCommon().isDisplayed(costInput);
    }

    public boolean discardCtaIsVisible() {
        return getCommon().isDisplayed(discardCta);
    }

    public boolean hoursUnitIsVisible() {
        return getCommon().isDisplayed(hoursUnit);
    }

    public boolean squareFeetUnitIsVisible() {
        return getCommon().isDisplayed(squareFeetUnit);
    }

    public boolean linearFeetUnitIsVisible() {
        return getCommon().isDisplayed(linearFeetUnit);
    }

    public boolean eachUnitIsVisible() {
        return getCommon().isDisplayed(eachUnit);
    }

    public boolean unitCountInputIsVisible() {
        return getCommon().isDisplayed(numberUnitInput);
    }

    public boolean totalPriceIsVisible() {
        return getCommon().isDisplayed(totalPrice);
    }

    public boolean saveCtaIsVisible() {
        return getCommon().isDisplayed(saveCta);
    }

    public NewAreaPricePage clickCostInputCta() {
        getCommon().clickAndVerifyIsTrue(costUnitCta, "Unable to click the continue button");
        return this;
    }

    public NewAreaPricePage clickHoursUnit() {
    	  if (getCommon().isDisplayed(costUnitDropdownDoneButton)) {
    		  costUnitPickerWheel.sendKeys("Hours");
              getCommon().clickAndVerifyIsTrue(costUnitDropdownDoneButton, "Unable to click dropdown done button as expected");
          } else {
              getCommon().clickAndVerifyIsTrue(hoursUnit, "Unable to click the continue button");
          }
        return this;
    }
    
    public NewAreaPricePage swipeToDismissKeyboard() {
        PointOption pressOptions = new PointOption().withCoordinates((getDriver().manage().window().getSize().getWidth()) / 2, 100);
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(100));
        PointOption pressOptions1 = new PointOption().withCoordinates((getDriver().manage().window().getSize().getWidth()) / 2, 0);
        new TouchAction(getDriver()).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release().perform();
        return this;
    }

    public NewAreaPricePage clickSquareFeetUnit() {
    	if (getCommon().isDisplayed(costUnitDropdownDoneButton)) {
            costUnitPickerWheel.sendKeys("Square Feet");
            getCommon().clickAndVerifyIsTrue(costUnitDropdownDoneButton, "Unable to click dropdown done button as expected");
        } else {
            getCommon().clickAndVerifyIsTrue(squareFeetUnit, "Unable to click the continue button");
        }
        return this;
    }

    public NewAreaPricePage clickLinearFootUnit() {
    	if (getCommon().isDisplayed(costUnitDropdownDoneButton)) {
            costUnitPickerWheel.sendKeys("Linear Feet");
            getCommon().clickAndVerifyIsTrue(costUnitDropdownDoneButton, "Unable to click dropdown done button as expected");
        } else {
            getCommon().clickAndVerifyIsTrue(linearFeetUnit, "Unable to click the continue button");
        }
        return this;
    }

    public NewAreaPricePage clickEachUnit() {
    	if (getCommon().isDisplayed(costUnitDropdownDoneButton)) {
            costUnitPickerWheel.sendKeys("Each");
            getCommon().clickAndVerifyIsTrue(costUnitDropdownDoneButton, "Unable to click dropdown done button as expected");
        } else {
            getCommon().clickAndVerifyIsTrue(eachUnit, "Unable to click the continue button");
        }
        return this;
    }

    public NewAreaPricePage clickSaveCta() {
        getCommon().clickAndVerifyIsTrue(saveCta, "Unable to click the continue button");
        return this;
    }

    public NewAreaPricePage clickDiscardCta() {
        getCommon().clickAndVerifyIsTrue(discardCta, "Unable to click the discard button");
        return this;
    }

    public NewAreaPricePage enterNumberOfUnits(String numUnits) {
        getCommon().typeAndVerifyIsTrue(numberUnitInput, numUnits, "Could not enter number of units");
        return this;
    }
    
    public NewAreaPricePage enterNumberOfUnits_(String numUnits, String language) {
    	typeOnElementbyXpath("//XCUIElementTypeApplication[@name=\"S-W Pro Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField", numUnits);
        //getCommon().typeAndVerifyIsTrue(numberUnitInput, numUnits, "Could not enter number of units");
       clickElementByElementName("calculatedSubtotalLabel", language);
    	return this;
    }
    public NewAreaPricePage enterCost(String cost) {
        getCommon().typeAndVerifyIsTrue(costInput, cost, "Could not enter unit cost");
        return this;
    }
    
    public NewAreaPricePage enterCost_(String cost, String language) {
        typeOnElementbyXpath("//XCUIElementTypeApplication[@name=\"S-W Pro Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTextField", cost);
        clickElementByElementName("calculatedSubtotalLabel", language);
        return this;
    }
    
    public WebElement getCostUnitPickerWheel() { return costUnitPickerWheel; }

    public boolean costUnitPickerWheelVisible() {
        return getCommon().isDisplayed(costUnitPickerWheel);
    }
}