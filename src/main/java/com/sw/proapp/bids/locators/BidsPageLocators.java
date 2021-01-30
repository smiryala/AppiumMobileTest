package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidSummaryPageData;
import com.sw.proapp.bids.data.BidsPageData;
import com.sw.proapp.data.HomePageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BidsPageLocators extends MobileBasePage {

    public BidsPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = BidsPageData.BID_CARD_HEADER_IOS)
    @AndroidFindBy(id = BidsPageData.BID_CARD_HEADER)
    protected WebElement bidCardHeader;

    @iOSXCUITFindBy(xpath = BidsPageData.CREATE_NEW_BID_BUTTON_IOS)
    @AndroidFindBy(id = BidsPageData.CREATE_NEW_BID_BUTTON)
    protected WebElement createNewBidButton;

    @iOSXCUITFindBy(xpath = BidsPageData.CREATE_NEW_BID_BUTTON_LARGE_IOS)
    protected WebElement createNewBidButtonLarge;

    @iOSXCUITFindBy(xpath = BidsPageData.NO_RESULTS_FOUND_IOS)
    protected WebElement noResultsFound;

    @AndroidFindBy(id = BidsPageData.STATUS_DROPDOWN)
    protected WebElement statusDropDown;

    @AndroidFindBy(xpath = BidsPageData.VIEW_ALL_STATUS)
    protected WebElement viewAllStatus;

    @AndroidFindBy(xpath = BidsPageData.DRAFTS_STATUS)
    protected WebElement draftsStatus;

    @AndroidFindBy(xpath = BidsPageData.PENDING_STATUS)
    protected WebElement pendingStatus;

    @AndroidFindBy(xpath = BidsPageData.ACCEPTED_STATUS)
    protected WebElement acceptedStatus;

    @AndroidFindBy(xpath = BidsPageData.REJECTED_STATUS)
    protected WebElement rejectedStatus;

    @AndroidFindBy(xpath = BidsPageData.CLOSED_STATUS)
    protected WebElement closedStatus;
    
    @AndroidFindBy(xpath = BidsPageData.EDIT_MENU_CTA)
    protected WebElement editMenuCta;

    @AndroidFindBy(xpath = BidsPageData.DUPLICATE_MENU_CTA)
    protected WebElement duplicateMenuCta;

    @AndroidFindBy(xpath = BidsPageData.DELETE_MENU_CTA)
    protected WebElement deleteMenuCta;

    @AndroidFindBy(xpath = BidsPageData.BID_HEADER_MENU)
    protected List<WebElement> bidHeaderMenuCta;

    @AndroidFindBy(xpath = BidsPageData.BID_HEADER_TITLE)
    protected List<WebElement> bidHeaderTitles;

    @AndroidFindBy(xpath = BidsPageData.CONFIRM_DELETE_CTA)
    protected WebElement confirmDeleteButton;

    @AndroidFindBy(id = BidsPageData.CANCEL_DELETE_CTA)
    protected WebElement cancelDeleteButton;
    
   
}