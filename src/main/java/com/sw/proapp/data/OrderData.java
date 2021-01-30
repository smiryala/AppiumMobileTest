package com.sw.proapp.data;

public class OrderData {

    public static final String CONTINUE_SHOPPING = "continueShoppingButton";
    //public static final String CONTINUE_SHOPPING_IOS = "//*[@text='CONTINUE SHOPPING' and @class='UIAButton']";
   // public static final String CONTINUE_SHOPPING_IOS = "//XCUIElementTypeButton[@name=\"CONTINUE SHOPPING\"]";
    public static final String CONTINUE_SHOPPING_IOS = "//XCUIElementTypeButton[@name=\"CONTINUE SHOPPING\" or @name=\"CONTINÚA COMPRANDO\"]";

    public static final String ORDER_NUMBER_VIEW = "orderNumberTextView";

    public static final String EMAIL_ORDER_CONFIRMATION_VIEW = "emailOrderConfirmationTextView";
    public static final String NOT_NOW_RATING_IOS = "//*[@text='Not Now']";
    public static final String ORDER_EMAIL_TEXT_IOS = "//*[@class='UIAView' and ./*[@text='email_black']]";
    public static final String ORDER_NUMBER_VIEW_IOS ="name BEGINSWITH[s] 'Order #'";// AND visible == 0 type == 'XCUIElementTypeStaticText' AND
            //"type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[s]  'Order #' AND visible == 0";
    public static final String EMAIL_ORDER_CONFIRMATION_VIEW_IOS = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[s]  'An order email confirmation will be sent to' AND visible == 1";

    
}