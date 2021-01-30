package com.sw.proapp.bids.data;

public class NewAreaProductsSearchPageData {
	
	public static final String CONFIRM_DELETE_CTA = "//*[@text='YES, DELETE']";

	public static final String AREA_MENU = "//*[contains(@resource-id,'area_menu')]";
	
	public static final String SAVE_AND_GO_ANDROID = "//*[@text='Save & Go to Summary']";

    public static final String SAVE_AND_GO_IOS = "//XCUIElementTypeStaticText[@text='Save & Go to Summary']";
	
	public static final String ITEM_DELETE_BUTTON_ANDROID = "item_delete_button";

	public static final String ITEM_DELETE_BUTTON_IOS = "delete";
	
	public static final String DELETE_CONFIRM_YES_ANDROID = "android:id/button1";

	public static final String DELETE_CONFIRM_YES_IOS = "YES, DELETE";
	
    public static final String CONTINUE_CTA = "productContinueButton";

    public static final String CONTINUE_CTA_IOS = "//XCUIElementTypeButton[@name='SAVE & CONTINUE' or @name='GUARDAR Y CONTINUAR']";

    public static final String SEARCH_PRODUCTS_CTA_IOS = "//XCUIElementTypeButton[@name='Search more products' or @name='Buscar más productos']";

    public static final String SEARCH_PRODUCTS_SEARCH_FIELD = "//XCUIElementTypeSearchField[@name='Search for products']";

    public static final String PRODUCT_SEARCH_FIRST_ITEM_ADD_CTA_IOS = "(//XCUIElementTypeStaticText[@name='ADD' or @name='AGREGAR'])[1]";
}