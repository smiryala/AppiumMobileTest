package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.ProjectBidsHomePageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ProjectBidsHomePageLocators extends MobileBasePage {

    public ProjectBidsHomePageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = ProjectBidsHomePageData.CREATE_NEW_BID_BUTTON_IOS)
    @AndroidFindBy(id = ProjectBidsHomePageData.CREATE_NEW_BID_BUTTON)
    protected WebElement createNewBidButton;

    @iOSXCUITFindBy(xpath = ProjectBidsHomePageData.VIEW_ALL_BIDS_BUTTON_IOS)
    @AndroidFindBy(id = ProjectBidsHomePageData.VIEW_ALL_BIDS_BUTTON)
    protected WebElement viewAllBidsButton;

    @iOSXCUITFindBy(xpath = ProjectBidsHomePageData.PROJECT_BIDS_CARD_HEADER_IOS)
    @AndroidFindBy(id = ProjectBidsHomePageData.PROJECT_BIDS_CARD_HEADER)
    protected WebElement projectBidsCardHeader;
}