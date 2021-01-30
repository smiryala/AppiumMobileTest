package com.sw.proapp.data;

public class CartPageData {
    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    public static final String CLOSE_IOS = "close";
    public static final String CLOSE = "//*[@text='close']";

    public static final String PRO_HOME_BUTTON = "navigationPro";
    public static final String PRO_HOME_BUTTON_IOS = "Pro";

    public static final String PRO_REMOVE_BUTTON = "removeButton";
    public static final String PRO_REMOVE_BUTTON_IOS = "removeCartItemButton";

    public static final String PRO_REMOVE_MESSAGE = "//*[@text='There are 0 items in your Cart.']";
    public static final String PRO_REMOVE_MESSAGE_IOS = "tbd";

    public static final String PRO_CONTINUE_SHOPPING_IOS = "//*[@text='Continue Shopping']";
    public static final String PRO_CONTINUE_SHOPPING = "//*[@text='Continue Shopping']";
    
    public static final String PRO_REMOVE_CART_LIST = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'REMOVE' AND visible == 0";
}