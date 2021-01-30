package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.CreateNewBidPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class CreateNewBidPage extends CreateNewBidPageLocators {

    public CreateNewBidPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public boolean isLoogedInCreateBidPageDispalyed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("bid name") || pageSource.contains("nombre del estimado");
    }
    
    @Step()
    public CreateNewBidPage clickProjectTotal() {
        getCommon().clickAndVerifyIsTrue(projectTotalCard, "Could not click Project Total");
        return this;
    }

    @Step("inputBidName is {0}")
    public CreateNewBidPage inputBidName(String bidName) {

    	if (getCommon().getDriver() instanceof IOSDriver) {
            String label= getTextByLanguage("Bid Name°Nombre del Estimado", languageBase);
            enterTextToElementByText(label, bidName);
    		return this;
    	}
    	 getCommon().sleepFor(3);
         getCommon().click(bidNameInput, "Could not click bid name");
         getCommon().typeAndVerifyIsTrue(bidNameInput, bidName, "Could not type bid name");
         getDriver().navigate().back();
         return this;
    }

    @Step()
    public CreateNewBidPage clickSaveBidButton() {
        getCommon().clickAndVerifyIsTrue(saveBidButton, "Could not click save bid button");
        return this;
    }
}