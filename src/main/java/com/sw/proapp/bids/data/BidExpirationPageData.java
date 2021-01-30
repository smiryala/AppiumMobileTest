package com.sw.proapp.bids.data;

public class BidExpirationPageData {

    public static final String BID_EXPIRATION_DROPDOWN_EMPTY = "duration_unit_input";
    public static final String BID_EXPIRATION_DROPDOWN_EMPTY_IOS = "//XCUIElementTypeStaticText[@name='Amount of Time' or @name='Plazo Ofrecido']";

    public static final String BID_EXPIRATION_DROPDOWN_POPULATED_IOS = "//XCUIElementTypeStaticText[@name = '15 days' or @name='15 días']";

    public static final String DROPDOWN_DONE_BUTTON_IOS = "//XCUIElementTypeButton[@name='Done' or @name='Elegir']";

    public static final String SAVE_BUTTON = "savebutton";
    public static final String SAVE_BUTTON_IOS = "//XCUIElementTypeButton[@name='SAVE' or @name='GUARDAR']";

    public static final String DISCARD_BUTTON = "discardbutton";
    public static final String DISCARD_BUTTON_IOS = "//XCUIElementTypeButton[@name='Discard' or @name='Descartar']";

    public static final String BID_EXPIRATION_15_DAYS = "//*[@text='15 days']";

    public static final String BID_EXPIRATION_30_DAYS = "//*[@text='30 days']";

    public static final String BID_EXPIRATION_45_DAYS = "//*[@text='45 days']";

    public static final String BID_EXPIRATION_7_DAYS = "//*[@text='7 days']";

    public static final String BID_NO_EXPIRATION = "//*[@text='Does Not Expire']";
}