package com.sw.proapp.bids.data;

public class NavigationPageData {

    public static final String FIRST_EDIT_TEXT_IOS = "//XCUIElementTypeTextField[1]";
    public static final String FIRST_EDIT_TEXT_ANDROID = "//*[@class='android.widget.EditText'][1]";

    public static final String GO_BACK_ARROW_IOS = "//XCUIElementTypeButton[@name='back']";
    public static final String GO_BACK_ARROW_ANDROID = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public static final String GO_BACK_CONFIRM_IOS = "//XCUIElementTypeButton[@name='Yes, Go Back']";
    public static final String GO_BACK_CONFIRM_ANDROID = "//*[@text='YES, GO BACK']";

    public static final String GO_BACK_CANCEL_IOS = "//XCUIElementTypeButton[@name='No, Stay Put']";
    public static final String GO_BACK_CANCEL_ANDROID = "//*[@text='NO, STAY PUT']";
}
