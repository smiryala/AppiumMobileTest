package com.sw.proapp.data;

public class RegistrationPageData {

    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    //public static final String CREATE_ACCOUNT_HEADER_IOS = "HOW TO CREATE AN ACCOUNT";
    public static final String CREATE_ACCOUNT_HEADER_IOS = "//*[@name=\"CÓMO CREAR UNA CUENTA\" or @name=\"HOW TO CREATE AN ACCOUNT\"]";
    public static final String CREATE_ACCOUNT_HEADER = "toolbarTitle";

    public static final String CURRENT_CUSTOMER_IOS = "//XCUIElementTypeStaticText[@name=\"ARE YOU A CURRENT SHERWIN-WILLIAMS CUSTOMER?\"]";
    public static final String CURRENT_CUSTOMER = "//*[@class='android.widget.TextView'][position()=1]";

   // public static final String DESCRIPTION_1_IOS = "//XCUIElementTypeStaticText[@name=\"Contact your local store or rep to have a profile created for you.\"]";
    public static final String DESCRIPTION_1_IOS = "//XCUIElementTypeStaticText[@name=\"Contact your local store or rep to have a profile created for you.\" or @name=\"Ponte en contacto con tu tienda o representante local para crear tu perfil.\"]";
    //public static final String DESCRIPTION_2_IOS = "//XCUIElementTypeButton[@name=\"You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.\"]";
    public static final String DESCRIPTION_2_IOS = "//*[@name=\"You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.\" or @name=\"También puedes visitar Sherwin-Williams.com/pro y registrarte para obtener un perfil mySW; solo necesitaras tu número de cuenta de nueve dígitos.\"]";
    public static final String DESCRIPTION = "//*[@class='android.widget.TextView'][position()=2]";


    //public static final String NEW_TO_SW_IOS = "//XCUIElementTypeStaticText[@name=\"NEW TO  SHERWIN-WILLIAMS? \"]";
    public static final String NEW_TO_SW_IOS = "//XCUIElementTypeApplication[@name=\"S-W Pro Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText";

    //public static final String NEW_TO_SW = "//*[@class='android.widget.LinearLayout']/*[@class='android.widget.RelativeLayout']/*[@class='android.widget.LinearLayout']/*[position()=1]";
    public static final String NEW_TO_SW = "//*[@class='android.widget.LinearLayout']/*[@class='android.widget.RelativeLayout']/*[@class='android.widget.LinearLayout']/*[position()=1]";

   // public static final String VISIT_LOCAL_1_IOS = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='visit your local store or have']";
   // public static final String VISIT_LOCAL_2_IOS = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='a rep contact you.']";
    ///public static final String VISIT_LOCAL_1_IOS = "//XCUIElementTypeButton[@name=\"Visit your local store or have\"]";
    //public static final String VISIT_LOCAL_2_IOS = "//XCUIElementTypeButton[@name=\"a rep contact you.\"]";
    //public static final String VISIT_LOCAL = "//*[@class='android.widget.LinearLayout']/*[@class='android.widget.RelativeLayout']/*[@class='android.widget.LinearLayout']/*[position()=2]";
   public static final String VISIT_LOCAL_1_IOS = "//XCUIElementTypeButton[@name=\"Visit your local store or have\" or @name=\"Visita tu tienda local o pide que\"]";
    public static final String VISIT_LOCAL_2_IOS = "//XCUIElementTypeButton[@name=\"a rep contact you.\" or @name=\"un representante te contacte.\"]";
    public static final String VISIT_LOCAL = "//*[@class='android.widget.LinearLayout']/*[@class='android.widget.RelativeLayout']/*[@class='android.widget.LinearLayout']/*[position()=2]";
    public static final String NAVIGATE_BACK_IOS = "//*[@id='close']";
    public static final String NAVIGATE_BACK = "//*[@class='android.widget.ImageButton']";

    public static final String BROWSER_URL_IOS = "//XCUIElementTypeButton[@name=\"URL\"]";
    public static final String BROWSER_URL = "//*[@class='android.widget.EditText' and contains(@text,'sherwin-williams')]";

    public static final String DONE_IOS = "//XCUIElementTypeStaticText[@name=\"Done\"]";

    public static final String YOURSHERWIN_IOS = "//XCUIElementTypeOther[@name=\"Your Sherwin-Williams PRO Account Benefits\"]"; 
    public static final String LOCALREP_IOS = "//XCUIElementTypeOther[@name=\"Sherwin-Williams Sales Representative Inquiry Form\"]";


    public static final String DONT_SHOW_AGAIN = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "=\"don't show again\"]";


    public static final String BROWSER_URL_EDIT = "//*[@resource-id ='com.android.chrome:id/url_edit_icon']";
    public static final String LOCATION_ALLOW = "//XCUIElementTypeButton[@name=\"Allow\"]";
	public static final String POPUP_OK = "//XCUIElementTypeStaticText[@name=\"OK\"]";
	public static final String PRIVACY_SETTINGS_OK = "//XCUIElementTypeButton[@name=\"OK\"]";
	
	public static final String PROS_NEED_PRO_POPUP = "Close";
}