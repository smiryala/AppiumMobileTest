package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.ProjectNotesPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProjectNotesPageLocators extends MobileBasePage {

    public ProjectNotesPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = ProjectNotesPageData.BID_NOTES_INPUT_IOS)
    @AndroidFindBy(id = ProjectNotesPageData.BID_NOTES_INPUT)
    protected WebElement projectNotesInput;

    @AndroidFindBy(id = ProjectNotesPageData.BID_NOTES_CHARACTER_COUNT)
    protected WebElement projectNotesCharacterCount;

    @iOSXCUITFindBy(xpath = ProjectNotesPageData.BID_NOTES_CHARACTER_COUNT_EMPTY_IOS)
    protected WebElement projectNotesCharacterCountEmpty;

    @iOSXCUITFindBy(xpath = ProjectNotesPageData.BID_NOTES_CHARACTER_COUNT_FULL_IOS)
    protected WebElement projectNotesCharacterCountFull;

    @iOSXCUITFindBy(xpath = ProjectNotesPageData.BID_NOTES_SAVE_BUTTON_IOS)
    @AndroidFindBy(id = ProjectNotesPageData.BID_NOTES_SAVE_BUTTON)
    protected WebElement projectNotesSaveButton;

    @iOSXCUITFindBy(xpath = ProjectNotesPageData.BID_NOTES_DISCARD_BUTTON_IOS)
    @AndroidFindBy(id = ProjectNotesPageData.BID_NOTES_DISCARD_BUTTON)
    protected WebElement projectNotesDiscardButton;
}