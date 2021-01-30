package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.ProjectNotesPageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProjectNotesPage extends ProjectNotesPageLocators {

    public ProjectNotesPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public boolean isLoogedInProjectNotesPageDispalyed() {
        getCommon().sleepFor(10);
        String pagesource = getCommon().getDriver().getPageSource().toLowerCase();
        return pagesource.contains("project notes") || pagesource.contains("notas del proyecto");
    }

    public WebElement getNotesInput() {
        return projectNotesInput;
    }

    public ProjectNotesPage typeNote(String input) {
        getCommon().type(getNotesInput(), input);
        return this;
    }

    public boolean isSaveButtonDisplayed() {
        return getCommon().isDisplayed(projectNotesSaveButton);
    }

    public WebElement getCharacterCount() {
        return projectNotesCharacterCount;
    }

    public WebElement getEmptyCharacterCount() {
        return projectNotesCharacterCountEmpty;
    }

    public WebElement getFullCharacterCount() {
        return projectNotesCharacterCountFull;
    }

    public ProjectNotesPage clickSaveButton() {
        getCommon().clickAndVerifyIsTrue(projectNotesSaveButton, "Unable to click Project Notes save button");
        return this;
    }

    public ProjectNotesPage clickDiscardButton() {
        getCommon().clickAndVerifyIsTrue(projectNotesDiscardButton, "Unable to click Project Notes discard button");
        return this;
    }
}