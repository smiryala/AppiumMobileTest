package com.sw.proapp.data;

public class AccountPageData {

    // ANDROID
    //public static final String ACCOUNT_HOME_BUTTON = "navigationAccount";
    public static final String ACCOUNT_HOME_BUTTON = "//*[@text='Mi cuenta' or @text='My Account']";//navigationAccount";



    public static final String ACCOUNT_HOME_PAGE_ICON = "//*[@text='ACCOUNT']";

    public static final String ACCOUNT_SETTING_BUTTON = "action_settings";

    public static final String QA_DASHBOARD_BUTTON = "qaDashboardCTA";

    public static final String SERVICE_ENDPOINTS = "servicesEndpointSpinner";

    public static final String REVTRAX_SERVICE_ENDPOINTS = "revTraxServicesEndpointSpinner";

    public static final String QAX_TEST_ENDPOINT = "//*[@text='QAX_TEST_SERVICES']";

    public static final String SIGNOUT = "signOutButton";

   // public static final String SIGNOUT_IOS = "settingsSignOutButton";
   public static final String SIGNOUT_IOS = "//XCUIElementTypeStaticText[@name=\"Cerrar sesión\" or @name=\"Sign Out\"]";

    public static final String BACK_BUTTON = "//*[@contentDescription='Navigate up']";

//    public static final String ACCOUNT_HOME_BUTTON_IOS = "Account";

    //public static final String ACCOUNT_HOME_BUTTON_IOS = "//XCUIElementTypeButton[@name='gear' or @name='Cuenta']";
    public static final String ACCOUNT_HOME_BUTTON_IOS = "name == 'Mi cuenta' or name == 'My Account'";

   // public static final String ACCOUNT_HOME_PAGE_ICON_IOS = "//*[@text='ACCOUNT']";

//    public static final String ACCOUNT_SETTING_BUTTON_IOS = "(//*[@accessibilityLabel='ACCOUNT']/*[@class='UIAButton'])[1]";

   // public static final String ACCOUNT_SETTING_BUTTON_IOS = "//XCUIElementTypeNavigationBar[@name='ACCOUNT' or @name='CUENTA']/XCUIElementTypeButton[1]";
   public static final String ACCOUNT_SETTING_BUTTON_IOS = "//XCUIElementTypeButton[@name=\"gear\"]";

    public static final String QA_DASHBOARD_BUTTON_IOS = "Debug";

    public static final String API_ENVIRONMENTS = "API Environment";

    public static final String QAX_SELECT_IOS = "QA";

//    public static final String OK_ENVIRONMENT_IOS = "//*[@text='OK']";

    public static final String OK_ENVIRONMENT_IOS = "//XCUIElementTypeButton[@name='OK' or @name='ACEPTAR']";

    public static final String BACK_IOS = "back";

    public static final String CLOSE_SETTINGS_IOS = "close";
}