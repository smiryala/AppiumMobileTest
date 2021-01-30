package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.ProjectDurationPageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ProjectDurationPage extends ProjectDurationPageLocators {

    public ProjectDurationPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public ProjectDurationPage inputDurationNumber(String input) {
        getCommon().type(projectDurationNumberInput, input);
        return this;
    }

    public ProjectDurationPage selectDurationAmount() {
        getCommon().click(projectDurationAmountDropdown, "Unable to click amount dropdown as expected");
        getCommon().click(projectDurationDropdownDoneButton, "Unable to click dropdown done button as expected");
        return this;
    }
    
    public boolean isSaveButtonDisplayed() {
        return getCommon().isDisplayed(projectDurationSaveButton);
    }

    public ProjectDurationPage clickSave() {
        getCommon().click(projectDurationSaveButton, "Unable to click save button as expected");
        return this;
    }

    public ProjectDurationPage clickDiscard() {
        getCommon().click(projectDurationDiscardButton, "Unable to click discard button as expected");
        return this;
    }
}