package com.sw.proapp.data;

public class PDPageData {

    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    public static final String PRODUCT_DETAILS_HOME_PAGE = "//*[@text='PRODUCT DETAILS']";

    public static final String ADD_TO_CART_BUTTON = "addToCartButton";

    public static final String ADD_TO_CART_BUTTON_IOS = "addToCartButton";

    public static final String CHECKOUT_BUTTON = "checkoutButton";
    public static final String CHECKOUT_BUTTON_IOS = "gotoCheckoutButton";

    public static final String GO_TO_CHECKOUT_BUTTON = "goToCartButton";
    public static final String GO_TO_CHECKOUT_BUTTON_IOS = "goToCartButton";

    public static final String GO_TO_CHECKOUT_POP_UP = "//*[@text='Go to Checkout']";

    //public static final String ADD_A_COLOR = "//*[@text='Add A Color']";
   // public static final String ADD_A_COLOR_IOS = "Add A Color";
    public static final String ADD_A_COLOR = "//*[@text='Add A Color' or @text='Agregar un Color']";
    public static final String ADD_A_COLOR_IOS = "//*[@name='Add A Color' or @name='Agregar un Color']";

    //public static final String ENTER_COLOR_NUMBER = "//*[@text='Enter SW color name or number']";
    public static final String ENTER_COLOR_NUMBER = "//*[@text='Enter SW color name or number' or @text='Ingresa el nombre o número de color SW']";
    public static final String ENTER_QUANTITY = "//*[@id='valueEditTextView']";

    public static final String ENTER_QUANTITY_IOS = "//*[@accessibilityLabel='countDisplayLabel']";


    public static final String SUBMIT_ORDER = "submitOrderButton";
    public static final String SUBMIT_ORDER_IOS = "agreePayButton";

   //public static final String DONT_SHOW_AGAIN = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "=\"don't show again\"]";
    public static final String DONT_SHOW_AGAIN = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "=\"don't show again\" or translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "=\"no mostrar nuevamente.\"]";



    public static final String NOT_NOW_IOS = "Not Now";

    public static final String INCREMENT_BUTTON = "//*[@id='incrementButton']";

    public static final String DECREMENT_BUTTON = "//*[@id='decrementButton']";

    public static final String JOB_NAME = "tbd";
    public static final String JOB_NAME_IOS = "//XCUIElementTypeApplication[@name=\"S-W Pro Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField";

    public static final String CHECKOUT_OK_IOS = "//*[@text='OK']";
    public static final String CHECKOUT_OK = "//*[@text='OK']";
	//public static final String ENTER_COLOR_NUMBER_IOS = "Enter SW color name or number";
    public static final String ENTER_COLOR_NUMBER_IOS ="//*[@name='Enter SW color name or number' or @name='Ingresa el nombre o número de color SW']";
            //"//*[@name='Enter SW color name or number' or @name='Ingresa el nombre o número de color SW']";
	public static final String INCREMENT_BUTTON_IOS = "incrementButton";

}