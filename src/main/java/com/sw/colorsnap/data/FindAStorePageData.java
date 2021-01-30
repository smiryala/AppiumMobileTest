package com.sw.colorsnap.data;

public class FindAStorePageData {
    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    public static final String LOCATION_SET_AWUA = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='allow while using app']";
    public static final String LOCATION_SET_AO = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='allow once' or translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='allow only while using the app' or translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='allow']";
    public static final String LOCATION_SET_DA = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='dont't allow']";
    //    public static final String LOCATION_SET_A = "//*[translate(@text,'"+ XPATHIGNORECASEUPPER +"','" +XPATHIGNORECASELOWER+ "')='allow']";
    public static final String LOCATION_SET_DDA = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='deny & don’t ask again']";
    //    public static final String LOCATION_SET_AOUA = "//*[translate(@text,'"+ XPATHIGNORECASEUPPER +"','" +XPATHIGNORECASELOWER+ "')='allow only while using the app']";
    public static final String LOCATION_SET_AAT = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='allow all the time']";

    public static final String IN_PROGRESS_IOS = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='in progress']";
    public static final String PLEASE_WAIT = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')='please wait']";
    public static final String NEAREST_STORE_IOS = "type == 'XCUIElementTypeStaticText' AND value CONTAINS '#' AND visible == 1";
    public static final String NEAREST_STORE = "//*[contains(@text,'Open')]/parent::*/parent::*/*[2]";
    public static final String SEARCH_STORES_IOS = "//XCUIElementTypeSearchField[@name=\"City, State or ZIP Code\"]";
    public static final String SEARCH_STORES = "//*[@class='android.widget.EditText']";
    public static final String STORE_SEARCH_BUTTON = "//*[@text='search']";

    public static final String SWITCH_STORE_LIST_IOS = "list";
    public static final String SWITCH_STORE_LIST = "//*[@class='android.widget.EditText']/parent::*//*[last()]";

    public static final String SWITCH_STORE_MAP_IOS = "map";
    public static final String SWITCH_STORE_MAP = "//*[@class='android.widget.EditText']/parent::*//*[last()]";

    public static final String STORE_LIST_IOS = "//*[@class='UIAView']/*[@class='UIATable']/*[@class='UIAView']//*[contains(@text,'Open')]/parent::*";
    public static final String STORE_LIST = "//*[contains(@text,'Open')]/parent::*";
	public static final String LOCATION_SET_AO_IOS = "Allow Once";

	//NavigationBar
    public static final String STORE_BACK_IOS = "//XCUIElementTypeButton[@name='Back']";
    public static final String STORE_TITLE_IOS = "//XCUIElementTypeStaticText[@name='Find a Store']";

    public static final String STORE_BACK = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public static final String STORE_TITLE = "//android.widget.TextView[@text='Find a Store']";

    //Filter
    public static final String STORE_FILTER = "filterButton";
    public static final String STORE_COMMERCIAL_FILTER = "commercialStoreFilterContainer";
    public static final String STORE_COMMERCIAL_CHECKED="commercialStoreSelectedIndicator";
    public static final String STORE_PAINT_CHECKED="paintStoreSelectedIndicator";
    public static final String STORE_PAINT_FILTER="paintStoreFilterContainer";

    public static final String STORE_FILTER_IOS = "//XCUIElementTypeButton[@name='filter']";
    public static final String STORE_COMMERCIAL_FILTER_IOS = "**/XCUIElementTypeWindow[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton";;
    public static final String STORE_COMMERCIAL_CHECKED_IOS="**/XCUIElementTypeImage[`name == \"check-small\"`][2]";
    public static final String STORE_PAINT_CHECKED_IOS="**/XCUIElementTypeImage[`name == \"check-small\"`]";
    public static final String STORE_PAINT_FILTER_IOS="**/XCUIElementTypeWindow[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton";
    public static final String STORE_COMMERCIAL_FILTER_TEXT_IOS ="Commercial Paint Stores";
    public static final String STORE_PAINT_FILTER_TEXT_IOS="Paint Stores";
    public static final String STORE_APPLY="Apply";

    // Store Info
    public static final String STORE_CALL_IOS="**/XCUIElementTypeButton[`label == \"Call\"`]";
    public static final String STORE_DIRECTIONS_IOS="**/XCUIElementTypeButton[`label == \"Directions\"`]";
    public static final String STORE_HOURS_IOS="**/XCUIElementTypeButton[`label == \"Hours\"`]";

    public static final String STORE_CALL="//android.widget.Button[@text='Call']";
    public static final String STORE_DIRECTIONS="//android.widget.Button[@text='Directions']";
    public static final String STORE_HOURS="//android.widget.Button[@text='Hours']";
}