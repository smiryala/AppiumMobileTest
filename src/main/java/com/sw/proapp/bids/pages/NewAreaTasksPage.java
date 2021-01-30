package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.NewAreaTasksPageLocators;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NewAreaTasksPage extends NewAreaTasksPageLocators {

    public NewAreaTasksPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isNewAreaTasksPageDisplayed() {
        getCommon().sleepFor(10);
        String pageSource = getCommon().getDriver().getPageSource().toLowerCase();
        return pageSource.contains("area tasks") || pageSource.contains("TRABAJOS DEL ÁREA".toLowerCase());
    }

    public WebElement getSaveCta() {
        return saveCta;
    }

    public WebElement getDiscardCta() {
        return discardCta;
    }

    public WebElement getWallsLabel() {
        return wallsLabel;
    }

    public WebElement getCeilingLabel() {
        return ceilingLabel;
    }

    public WebElement getInternalTrimLabel() {
        return internalTrimLabel;
    }

    public WebElement getInternalDoorsLabel() {
        return internalDoorsLabel;
    }

    public WebElement getInternalWindowsLabel() {
        return internalWindowsLabel;
    }

    public WebElement getInternalOtherCta() {
        return internalOtherCTA;
    }

    public WebElement getBodyLabel() {
        return bodyLabel;
    }

    public WebElement getExternalTrimLabel() {
        return externalTrimLabel;
    }

    public WebElement getShuttersLabel() {
        return shuttersLabel;
    }

    public WebElement getExternalWindowsLabel() {
        return externalWindowsLabel;
    }

    public WebElement getExternalDoorsLabel() {
        return externalDoorsLabel;
    }

    public WebElement getGuttersLabel() {
        return guttersLabel;
    }

    public WebElement getExternalOtherCta() {
        return externalOtherCta;
    }

    public NewAreaTasksPage clickSaveCta() {
        getCommon().clickAndVerifyIsTrue(saveCta, "Unable to click the save button");
        return this;
    }

    public NewAreaTasksPage clickDiscardCta() {
        getCommon().clickAndVerifyIsTrue(discardCta, "Unable to click the discard button");
        return this;
    }

    public NewAreaTasksPage clickExternalOtherCta() {
        getCommon().clickAndVerifyIsTrue(externalOtherCta, "Unable to click the external other button");
        return this;
    }

    public NewAreaTasksPage clickInternalOtherCta() {
        getCommon().clickAndVerifyIsTrue(internalOtherCTA, "Unable to click the internal other button");
        return this;
    }
    
    public void clickTaskByText(String text, String language) {
    	if (getCommon().getDriver() instanceof IOSDriver) {
    		text = getTextByLanguage(text, language);
    		iOSWaitForElementByText(text, "unable to find element with value: +"+text);
			clickTaskCheckBoxByNameIOS(text);
		} else {
			text = getTextByLanguage(text, language);
			clickElementByText(text, language);
		}
    }
}