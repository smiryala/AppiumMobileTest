package com.sw.proapp.bids.data;

public class BidsPageData {

    public static final String BID_CARD_HEADER = "header_title";
    public static final String BID_CARD_HEADER_IOS = "//XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell";


    public static final String CREATE_NEW_BID_BUTTON = "CreateButton";
    public static final String CREATE_NEW_BID_BUTTON_IOS = "//XCUIElementTypeButton[@name='CREATE NEW BID' or @name='CREAR COTIZACIÓN']";

    public static final String CREATE_NEW_BID_BUTTON_LARGE_IOS = "(//XCUIElementTypeButton[@name='CREATE NEW BID' or @name='CREAR COTIZACIÓN'])[2]";

    public static final String NO_RESULTS_FOUND_IOS = "//XCUIElementTypeStaticText[@name='No results found']";

    public static final String STATUS_DROPDOWN = "Viewall";

    public static final String VIEW_ALL_STATUS = "//*[@class='android.widget.TextView'][@text='View All']";

    public static final String DRAFTS_STATUS = "//*[@text='Drafts']";

    public static final String PENDING_STATUS = "//*[@text='Pending']";

    public static final String ACCEPTED_STATUS = "//*[@text='Accepted']";
    
    public static final String REJECTED_STATUS = "//*[@text='Rejected']";

    public static final String CLOSED_STATUS = "//*[@text='Closed']";
    
    public static final String EDIT_MENU_CTA = "//*[@text='Edit']";

    public static final String DELETE_MENU_CTA = "//*[@text='Delete']";

    public static final String DUPLICATE_MENU_CTA = "//*[@text='Duplicate']";

    public static final String BID_HEADER_MENU = "//*[contains(@resource-id,'header_menu')]";

    public static final String BID_HEADER_TITLE = "//*[contains(@resource-id,'header_title')]";

    public static final String CONFIRM_DELETE_CTA = "//*[@text='YES, DELETE']";

    public static final String CANCEL_DELETE_CTA = "button2";
}