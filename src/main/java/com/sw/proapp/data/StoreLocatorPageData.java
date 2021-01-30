package com.sw.proapp.data;

public class StoreLocatorPageData {
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
    //public static final String NEAREST_STORE = "//*[contains(@text,'Open')]/parent::*/parent::*/*[2]";
    public static final String NEAREST_STORE = "name";
    public static final String SEARCH_STORES_IOS = "//XCUIElementTypeSearchField[@name=\"City, State or Zipcode\" or @name=\"Ciudad, Estado ó Código postal\"]";

   // public static final String SEARCH_STORES_IOS = "//XCUIElementTypeSearchField[@name=\"City, State or Zipcode\"]";
    public static final String SEARCH_STORES = "//*[@class='android.widget.EditText']";

    public static final String STORE_SEARCH_BUTTON = "//*[@text='search']";

    public static final String SWITCH_STORE_LIST_IOS = "list";
   // public static final String SWITCH_STORE_LIST = "//*[@class='android.widget.EditText']/parent::*//*[last()]";
    public static final String SWITCH_STORE_LIST = "//*[@resource-id=\"com.sherwin.probuyplus.debug:id/listButton\" or @class='android.widget.EditText']/parent::*//*[last()]";


    public static final String SWITCH_STORE_MAP_IOS = "map";
    public static final String SWITCH_STORE_MAP = "//*[@class='android.widget.EditText']/parent::*//*[last()]";

    public static final String STORE_LIST_IOS = "//*[@class='UIAView']/*[@class='UIATable']/*[@class='UIAView']//*[contains(@text,'Open')]/parent::*";
    //public static final String STORE_LIST = "//*[contains(@text,'Open')]/parent::*";
	public static final String LOCATION_SET_AO_IOS = "Allow Once";

    public static final String STORE_LIST = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout";
}