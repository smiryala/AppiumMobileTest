package com.sw.proapp.data;

public class HomePageData {
    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    public static final String SEARCH_BUTTON = "action_search";
    public static final String SEARCH_FOR_PRODUCTS_SEARCH = "search_src_text";
    public static final String SEARCH_FOR_PRODUCTS_SEARCH_IOS = "Search for products";

    public static final String PRO_HOME_BUTTON = "navigationPro";
    public static final String PRO_HOME_BUTTON_IOS = "Pro";

  //  public static final String SEARCH_BUTTON_IOS = "Search for products";
  public static final String SEARCH_BUTTON_IOS = "label == 'Search to start an order' or label == 'Buscar para iniciar un pedido'";

    //public static final String PRO_FREQUENTLY_PURCHASED_EXPAND = "cta";
    public static final String PRO_FREQUENTLY_PURCHASED_EXPAND_IOS = "tbd";

    public static final String PRO_FREQUENTLY_PURCHASED_ITEM = "titleTextView";
    public static final String PRO_FREQUENTLY_PURCHASED_ITEM_IOS = "tbd";

    public static final String PRO_CART = "cartIconImageView";
    //public static final String PRO_CART_IOS = "//*[contains(@text,'cart')]";
    public static final String PRO_CART_IOS = "//XCUIElementTypeButton[@name=\"cartNavigationButton\"]";
    
    public static final String CART_ITEMS_0_IOS = "ITEMS (0)";
    

    public static final String PRO_REMOVE_BUTTON = "removeButton";
    public static final String PRO_REMOVE_BUTTON_IOS = "REMOVE";

    public static final String PRO_REMOVE_MESSAGE = "//*[@text='There are 0 items in your Cart.']";
    public static final String PRO_REMOVE_MESSAGE_IOS = "tbd";

    public static final String PRO_FREQUENTLY_PURCHASED_SECTION = "productsRecyclerView";
//    public static final String PRO_FREQUENTLY_PURCHASED_SECTION_IOS = "//*[@class='UIAView' and ./*[@text='FREQUENTLY PURCHASED']]";

    public static final String PRO_FREQUENTLY_PURCHASED_SECTION_IOS = "//XCUIElementTypeStaticText[@name='FREQUENTLY PURCHASED' or @name='FRECUENTEMENTE COMPRADO']";

    public static final String PRO_FREQUENTLY_PURCHASED_MORE = "moreCTAButton";
    public static final String PRO_FREQUENTLY_PURCHASED_MORE_IOS = "tbd";

    //public static final String PRO_ACCOUNT_SUMMARY = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='account summary']";
    public static final String PRO_ACCOUNT_SUMMARY = "//*[@text='ACCOUNT SUMMARY' or @text='RESUMEN DE CUENTA']";
//    public static final String PRO_ACCOUNT_SUMMARY_IOS = "ACCOUNT SUMMARY";
    public static final String PRO_ACCOUNT_SUMMARY_IOS = "//XCUIElementTypeStaticText[@name='ACCOUNT SUMMARY' or @name='RESUMEN DE CUENTA']";

    public static final String PRO_ACCOUNT_SUMMARY_DETAILS = "accountSummaryContentContainer";
    //public static final String PRO_ACCOUNT_SUMMARY_DETAILS_IOS = "//*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@text='ACCOUNT']]]]";
   // public static final String PRO_ACCOUNT_SUMMARY_DETAILS_IOS = "ACCOUNT";
    public static final String PRO_ACCOUNT_SUMMARY_DETAILS_IOS = "//XCUIElementTypeStaticText[@name=\"CUENTA\" or @name=\"ACCOUNT\"]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther";////XCUIElementTypeApplication[@name=\"S-W Pro\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]";

    public static final String PRO_STORE_INFO = "storeInfoView";
    //public static final String PRO_STORE_INFO_IOS = "//*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@text='ACCOUNT SUMMARY']]]]]/*[2]";
    public static final String PRO_STORE_INFO_IOS = "type == 'XCUIElementTypeStaticText' AND value CONTAINS  '#' AND visible == 1";

    //public static final String PRO_ORDER_HISTORY = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='order history']/parent::*";
    public static final String PRO_ORDER_HISTORY = "//*[@text='ORDER HISTORY' or @text='HISTORIAL DE COMPRAS']";
//    public static final String PRO_ORDER_HISTORY_IOS = "//*[@class='UIAView' and ./*[@text='ORDER HISTORY']]";

    public static final String PRO_ORDER_HISTORY_IOS = "//XCUIElementTypeStaticText[@name='ORDER HISTORY' or @name='HISTORIAL DE COMPRAS']";
   // public static final String PRO_ORDER_HISTORY_IOS = "ORDER HISTORY";

    //public static final String PRO_RECENT_ONLINE_ORDER = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='recent online orders']/parent::*";
    public static final String PRO_RECENT_ONLINE_ORDER = "//*[@text='RECENT ONLINE ORDERS' or @text='ÓRDENES RECIENTES EN LÍNEA']";
    //public static final String PRO_RECENT_ONLINE_ORDER_IOS = "//*[@class='UIAView' and @height>0 and ./*[@text='RECENT ONLINE ORDERS']]";
   // public static final String PRO_RECENT_ONLINE_ORDER_IOS = "RECENT ONLINE ORDERS";
    public static final String PRO_RECENT_ONLINE_ORDER_IOS = "//XCUIElementTypeStaticText[@name=\"ÓRDENES RECIENTES EN LÍNEA\" or @name=\"RECENT ONLINE ORDERS\"]";

    //public static final String PRO_DOING = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='how are we doing?']";
    public static final String PRO_DOING = "//*[@text='HOW ARE WE DOING?' or @text='¿CÓMO VAMOS?']";
    public static final String PRO_DOING_IOS = "//XCUIElementTypeStaticText[@name=\"HOW ARE WE DOING?\"]";

    public static final String PRO_ADVANTAGES = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='everyday pro advantages']";

    public static final String PRO_ADVANTAGES_IOS = "//XCUIElementTypeStaticText[@name=\"EVERYDAY PRO ADVANTAGES\"]";

    public static final String PRO_CONTINUE_SHOPPING_IOS = "//*[@text='Continue Shopping']";
    public static final String PRO_CONTINUE_SHOPPING = "//*[@text='Continue Shopping']";

    public static final String PRO_LOGO_IOS = "//XCUIElementTypeStaticText[@name=\"SHERWIN‑WILLIAMS® PRO\"]";
  
    public static final String PRO_LOGO = "toolbarTitle";

    public static final String PRO_ADVANTAGES_SECTIONS_IOS = "//*[@text='EVERYDAY PRO ADVANTAGES']/parent::*/following-sibling::*/*[@class='UIAView']/*[@class='UIAView']/*[@class='UIAView']/*[@class='UIAView']/child::*[1]";
    public static final String PRO_ADVANTAGES_SECTIONS = "//*[contains(@resource-id,'proCardContainer')]//*[@class='android.widget.LinearLayout']//*[@class='android.widget.FrameLayout']/*[@class='android.widget.RelativeLayout']/child::*[1]";

    public static final String SEARCH_CANCEL_IOS = "//XCUIElementTypeButton[@name=\"Cancel\"]";

    public static final String FIND_STORES_IOS = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='stores' and @class='UIAButton']";
    public static final String FIND_STORES = "//*[@content-desc='Stores']";
    //public static final String STORE_LOGO_IOS = "//XCUIElementTypeButton[@name=\"Stores\"]";
    public static final String STORE_LOGO_IOS = "//XCUIElementTypeButton[@name=\"Stores\" or @name=\"Tienda\"]";
    

    public static final String ACCEPT_TERMS_OF_USE_IOS = "//XCUIElementTypeButton[@name='I Acknowledge' or @name='Reconozco']";

    public static final String NOTIFICATIONS_REMIND_ME_LATER_IOS = "//XCUIElementTypeButton[@name='REMIND ME LATER' or @name='RECORDARME MÁS TARDE']";
	//public static final String PROJECTS_BIDS_SECTION = "PROJECT BIDS";
    public static final String PROJECTS_BIDS_SECTION = "//*[@name='PROJECT BIDS' or @name='COTIZACIONES']";
	public static final String TOP_SUPPLIES_SECTION = "Top Supplies"; 
			//"PROBUY® DEALS";
	public static final String PRO_ALERTS_SECTION = "PRO ALERTS";
	public static final String SW_DOCOM_PRODUCT_SECTION = "S-W.COM PRODUCT INFO";
	public static final String SW_DOTCOM_DATSHEETS_SECTION = "S-W.COM DATA SHEETS";
	public static final String STATEMENTS_PAYMENTS_SECTION = "STATEMENTS & PAYMENTS";
	public static final String COLOR_SNAP_APP_SECTION = "COLORSNAP APP";
	public static final String PAINT_CALCULATOR_SECTION = "PAINT CALCULATOR";
	public static final String PPC_MAGZINE_SECTION = "PPC MAGAZINE";
	
	//public static final String PROJECTS_BIDS_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='project bids']";;
    public static final String PROJECTS_BIDS_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='project bids' or translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='cotizaciones']";
	//public static final String TOP_SUPPLIES_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='top supplies']";;
    public static final String TOP_SUPPLIES_SECTION_ANDRIOID = "//*[@text='Top Supplies' or @text='Materiales de Primerab']";
	public static final String PRO_ALERTS_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='pro alerts']";;
	public static final String SW_DOCOM_PRODUCT_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='s-w.com product info']";;
	public static final String SW_DOTCOM_DATSHEETS_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='s-w.com data sheets']";;
	public static final String STATEMENTS_PAYMENTS_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='statements & payments']";;
	public static final String COLOR_SNAP_APP_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='colorsnap app']";;
	public static final String PAINT_CALCULATOR_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='paint calculator']";;
	public static final String PPC_MAGZINE_SECTION_ANDRIOID = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='ppc magazine']";;

    public static final String FIRST_FREQUENTLY_PURCHASED_ITEM_ANDROID = "//*[@resource-id='com.sherwin.probuyplus.debug:id/productsRecyclerView']/android.widget.FrameLayout[1]";

    public static final String PRO_FREQUENTLY_CARROUSELS_ITEMS = "productNameTextView";
    public static final String PRO_FREQUENTLY_CARROUSELS_ITEMS_IOS = "//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText";

    public static final String PRO_FREQUENTLY_PURCHASED_EXPAND = "purchaseHistorySubHeaderTextView";

    public static final String PRO_ORDERHISTORY_DETAILS = "SubHeaderTextView";
    public static final String PRO_ORDERHISTORY_DETAIL_IOS = "//XCUIElementTypeStaticText[@name=‘ORDER HISTORY’ or @name=‘HISTORIAL DE COMPRAS’ ] //parent::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText";


}