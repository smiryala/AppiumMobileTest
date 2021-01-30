package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.BidExpirationPageData;
import com.sw.proapp.bids.data.BidSummaryPageData;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.data.HomePageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BidSummaryPageLocators extends MobileBasePage {

    public BidSummaryPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BID_NAME_CARD_IOS)
    @AndroidFindBy(id = BidSummaryPageData.BID_NAME_CARD)
    protected WebElement bidNameCard;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_INFO_CARD_IOS)
    @AndroidFindBy(id = BidSummaryPageData.PROJECT_INFO_CARD)
    protected WebElement projectInfoCard;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_INFO_NAME_LABEL_IOS)
    protected WebElement projectInfoNameLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_INFO_EMAIL_LABEL_IOS)
    protected WebElement projectInfoEmailLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_INFO_PHONE_LABEL_IOS)
    protected WebElement projectInfoPhoneLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_INFO_ADDRESS_LABEL_IOS)
    protected WebElement projectInfoAddressLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_NOTES_CARD_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.PROJECT_NOTES_CARD)
    protected WebElement projectNotesCard;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_NOTES_PLACEHOLDER_TEXT_IOS)
    @AndroidFindBy(id = BidSummaryPageData.PROJECT_NOTES_PLACEHOLDER_TEXT)
    protected WebElement projectNotesPlaceholderText;

    @AndroidFindBy(xpath = BidSummaryPageData.NAVIGATE_UP_BUTTON)
    protected WebElement navigateUp;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.CREATED_BY_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.CREATED_BY_VALUE)
    protected WebElement createdByValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PRO_EMAIL_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.PRO_EMAIL_VALUE)
    protected WebElement proEmailValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PHONE_NUMBER_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.PHONE_NUMBER_VALUE)
    protected WebElement phoneNumberValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BID_NUMBER_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.BID_NUMBER_VALUE)
    protected WebElement bidNumberValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.CREATED_ON_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.CREATED_ON_VALUE)
    protected WebElement createdOnValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.LAST_MODIFIED_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.LAST_MODIFIED_VALUE)
    protected WebElement lastModifiedValue;

    @AndroidFindBy(id = BidSummaryPageData.PRICING_METHOD_TAG)
    protected WebElement pricingMethodTag;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BUSINESS_NAME_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.BUSINESS_NAME_VALUE)
    protected WebElement businessNameValue;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_DURATION_CARD_IOS)
    @AndroidFindBy(id = BidSummaryPageData.PROJECT_DURATION_CARD)
    protected WebElement projectDurationCard;

    @AndroidFindBy(id = BidSummaryPageData.DURATION_CARD_DATA)
    protected WebElement durationCardData;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.NEW_AREA_CTA_IOS)
    @AndroidFindBy(id = BidSummaryPageData.NEW_AREA_CTA)
    protected WebElement newAreaCTA;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BID_EXPIRATION_CTA_IOS)
    @AndroidFindBy(id = BidSummaryPageData.BID_EXPIRATION_CTA)
    protected WebElement bidExpirationCTA;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BID_EXPIRATION_PLACEHOLDER_IOS)
    @AndroidFindBy(id = BidSummaryPageData.BID_EXPIRATION_PLACEHOLDER)
    protected WebElement bidExpirationPlaceholder;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.BID_PRICING_METHOD_PILL_IOS)
    protected WebElement bidPricingMethodPill;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_DURATION_CARD_PLACEHOLDER_TEXT_IOS)
    protected WebElement projectDurationPlaceholderText;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.AREA_TITLE_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.AREA_TITLE)
    protected List<WebElement> areaTitles;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.AREA_SUBTOTAL_VALUE_IOS)
    protected List<WebElement> areaSubtotalValues;
    
 
    @iOSXCUITFindBy(xpath = BidSummaryPageData.AREA_SUBTOTAL_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.AREA_SUBTOTAL)
    protected List<WebElement> areaSubtotals;
    
    @AndroidFindBy(id = BidSummaryPageData.PROJECT_SUBTOTAL)
    protected WebElement projectSubtotal;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.DISCOUNT_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.DISCOUNT_VALUE)
    protected WebElement discountValue;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.TAX_VALUE_IOS)
    @AndroidFindBy(id = BidSummaryPageData.TAX_VALUE)
    protected WebElement taxValue;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.TOTAL_COST_IOS)
    @AndroidFindBy(id = BidSummaryPageData.TOTAL_COST)
    protected WebElement totalCost;
    
 
    @iOSXCUITFindBy(xpath = BidSummaryPageData.TASKS_LABEL_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.TASKS_LABEL)
    protected WebElement tasksLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PRODUCTS_LABEL_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.PRODUCTS_LABEL)
    protected WebElement productsLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.NOTES_LABEL_IOS)
    @AndroidFindBy(xpath = BidSummaryPageData.NOTES_LABEL)
    protected WebElement notesLabel;
    
    @AndroidFindBy(id = BidSummaryPageData.CREATED_BY_LABEL)
    protected WebElement createdByLabel;

    @AndroidFindBy(id = BidSummaryPageData.PRO_EMAIL_LABEL)
    protected WebElement proEmailLabel;

    @AndroidFindBy(id = BidSummaryPageData.PHONE_NUMBER_LABEL)
    protected WebElement phoneNumberLabel;
    
    @AndroidFindBy(id = BidSummaryPageData.BID_NUMBER_LABEL)
    protected WebElement bidNumberLabel;
    
    
    @AndroidFindBy(id = BidSummaryPageData.CREATED_ON_LABEL)
    protected WebElement createdOnLabel;
    
    	
    @AndroidFindBy(id = BidSummaryPageData.LAST_MODIFIED_LABEL)
    protected WebElement lastModifiedLabel;
    
   // @AndroidFindBy(id = BidSummaryPageData.PROJECT_SUBTOTAL_VALUE)
   // protected WebElement projectSubtotalValue;
    
    @iOSXCUITFindBy(xpath = BidSummaryPageData.PROJECT_SUBTOTAL_VALUE_IOS)
    protected WebElement projectSubtotalValue;
    

    @AndroidFindBy(id = BidSummaryPageData.DISCOUNT_LABEL)
    protected WebElement discountLabel;
    
    @AndroidFindBy(id = BidSummaryPageData.TAX_LABEL)
    protected WebElement taxLabel;
    
 //   @iOSXCUITFindBy(xpath = BidSummaryPageData.TOTAL_COST_LABEL_IOS)
//  protected WebElement totalCostLabel;

    
    @AndroidFindBy(id = BidSummaryPageData.TOTAL_COST_LABEL)
     protected WebElement totalCostLabel;

    @AndroidFindBy(id = BidSummaryPageData.TOTAL_COST_VALUE)
    protected WebElement totalCostValue;
    
    @AndroidFindBy(id = BidSummaryPageData.SAVE_CTA)
    protected WebElement saveButton;

    @AndroidFindBy(id = BidSummaryPageData.PREVIEW_CTA)
    protected WebElement previewButton;
    
    @AndroidFindBy(xpath = BidSummaryPageData.AREA_MENU)
    protected List<WebElement> areaMenus;
    
    @AndroidFindBy(id = BidSummaryPageData.PROJECT_SUBTOTAL_LABEL)
    protected WebElement projectSubtotalLabel;
    
    @AndroidFindBy(id = BidSummaryPageData.ADD_LOGO_CTA)
    protected WebElement addLogoCta;

    @AndroidFindBy(xpath = BidSummaryPageData.DELETE_MENU_CTA)
    protected WebElement deleteMenuCta;

    @AndroidFindBy(xpath = BidSummaryPageData.CONFIRM_DELETE_CTA)
    protected WebElement confirmDeleteButton;

    @iOSXCUITFindBy(accessibility = BidSummaryPageData.PRODUCTS_CARD_IOS)
    protected WebElement productsCard;

    @iOSXCUITFindBy(accessibility = BidSummaryPageData.PRODUCTS_CARD_TITLE_LABEL_IOS)
    protected WebElement productsCardTitleLabel;

    @iOSXCUITFindBy(accessibility = BidSummaryPageData.PRODUCTS_CARD_DETAIL_LABEL_IOS)
    protected WebElement productsCardDetailLabel;

    @iOSXCUITFindBy(xpath = BidSummaryPageData.PRODUCTS_CARD_VIEW_ALL_PRODUCTS_IOS)
    protected WebElement productsCardViewAllProductsCta;

    @iOSXCUITFindBy(accessibility = BidSummaryPageData.PRODUCTS_CARD_PRODUCT_NAME_LABEL_IOS)
    protected WebElement productsCardProductNameLabel;
}