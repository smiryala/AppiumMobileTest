package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidSummaryPageData;
import com.sw.proapp.bids.data.CreateNewBidPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class CreateNewBidPageLocators extends MobileBasePage {

    public CreateNewBidPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = CreateNewBidPageData.BID_NAME_FIELD_IOS)
    @AndroidFindBy(id = CreateNewBidPageData.BID_NAME_FIELD)
    protected WebElement bidNameInput;

    @iOSXCUITFindBy(xpath = CreateNewBidPageData.SAVE_BID_BUTTON_IOS)
    @AndroidFindBy(id = CreateNewBidPageData.SAVE_BID_BUTTON)
    protected WebElement saveBidButton;
  
    @iOSXCUITFindBy(accessibility = CreateNewBidPageData.PROJECT_TOTAL_CARD_IOS)
    protected WebElement projectTotalCard;
}