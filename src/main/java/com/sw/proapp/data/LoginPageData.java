package com.sw.proapp.data;

public class LoginPageData {

    private static final String XPATHIGNORECASEUPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String XPATHIGNORECASELOWER = "abcdefghijklmnopqrstuvwxyz";

    //public static final String SIGNIN_BUTTON = "signInButton";
    public static final String SIGNIN_BUTTON = "//*[@value=\"INICIAR SESIÓN\" or @value=\"SIGN IN\"]";

    public static final String USERNAME = "emailTextInputView";
    public static final String PASSWORD = "passwordTextInputView";

    public static final String POPUP_OK = "//*[translate(@text,'" + XPATHIGNORECASEUPPER + "','" + XPATHIGNORECASELOWER + "')" + "='ok']";

    public static final String CHECKOUT_PASSWORD = "inputEditText";
    public static final String CHECKOUT_PASSWORD_OK = "okButton";

    public static final String SHERWIN_WILLIAMS_PRO = "//*[@text='SHERWIN-WILLIAMS® PRO']";
    //    public static final String SHERWIN_WILLIAMS_PRO_IOS = "//*[@id='SHERWIN‑WILLIAMS® PRO' and @class='UIAStaticText']";
    public static final String SHERWIN_WILLIAMS_PRO_IOS = "/XCUIElementTypeStaticText[@name=\"SHERWIN‑WILLIAMS® PRO\"]";
    public static final String NO_THANKS_IOS = "//*[@text='No Thanks']";

    public static final String DONT_ALLOW_IOS = "//*[@id='Don’t Allow']";

    //public static final String SIGNIN_BUTTON_IOS = "//XCUIElementTypeButton[@name=\"proSignInButton\"]";
    public static final String SIGNIN_BUTTON_IOS = "//*[@name=\"proSignInButton\"]";
    //public static final String SIGNIN_BUTTON_IOS = "proSignInButton";

    //public static final String USERNAME_IOS = "//XCUIElementTypeOther[@name=\"loginEmailLabel\"]/XCUIElementTypeTextField";
    public static final String USERNAME_IOS = "//XCUIElementTypeApplication[@name=\"S-W Pro\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeTextField";

    // public static final String PASSWORD_IOS = "//XCUIElementTypeOther[@name=\"loginPasswordLabel\"]/XCUIElementTypeSecureTextField";
    public static final String PASSWORD_IOS = "//XCUIElementTypeApplication[@name=\"S-W Pro\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField";


    public static final String NEED_A_LOGIN_IOS = "//XCUIElementTypeButton[@name=\"NEED A LOGIN?\" or @name=\"Need a login?\"]";
    public static final String NEED_A_LOGIN = "//*[@text='NEED A LOGIN?' or @text='Need a login?' ]";

    //public static final String EMAIL_ERROR_IOS = "//XCUIElementTypeStaticText[@name=\"invalidEmailMessage\"]";
    public static final String EMAIL_ERROR_IOS = "//XCUIElementTypeApplication[@name=\"S-W Pro Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
    public static final String EMAIL_ERROR = "textinput_error";

    //public static final String SIGNIN_ERROR_HEADER_IOS = "//XCUIElementTypeStaticText[@name=\"Incorrect email or password.\"]";
    public static final String SIGNIN_ERROR_HEADER_IOS = "//XCUIElementTypeAlert[@name=\"Correo electrónico o contraseña incorrecta\" or @name=\"Incorrect email or password.\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]";
    //public static final String SIGNIN_ERROR_HEADER = "//*[@text='Incorrect email or password.']";
    public static final String SIGNIN_ERROR_HEADER = "//*[@text='NEED A LOGIN?' or @text='Need a login?' ]";//*[@text='Incorrect email or password.']";////*[@name='Incorrect email or password.' or @name='Contraseña o correo electrónico incorrecto.']";


    // public static final String SIGNIN_ERROR_IOS = "//XCUIElementTypeStaticText[@name=\"Please enter a valid email address or password to sign in.\"]";
    public static final String SIGNIN_ERROR_IOS = "//XCUIElementTypeStaticText[@name=\"Please enter a valid email address or password to sign in.\" or @name=\"Favor de ingresar un correo electrónico o contraseña válida para iniciar sesión.\"]";
    // public static final String SIGNIN_ERROR = "//*[@text='Please enter a valid email address or password to sign in.']";
    public static final String SIGNIN_ERROR = "//*[@text='Please enter a valid email address or password to sign in.' or @text='Favor de ingresar correo electrónico o contraseña válida para iniciar sesión.']";

    // public static final String TRY_AGAIN_IOS = "//XCUIElementTypeButton[@name=\"Try Again\"]";
    //public static final String TRY_AGAIN = "//*[@class='android.widget.Button'][position()=2]";
    public static final String TRY_AGAIN_IOS = "//*[@name=\"Try Again\" or @name=\"Intentar de nuevo\"]";
    public static final String TRY_AGAIN = "//*[@text='TRY AGAIN' or @text='INTENTA DE NUEVO']";

    //public static final String ACK_IOS = "I Acknowledge";
    public static final String ACK_IOS = "//XCUIElementTypeButton[@name=\"Reconozco\" or @name=\"I Acknowledge\"]";
    //public static final String ACK = "//*[@text= 'I ACKNOWLEDGE']";
    public static final String ACK = "//*[@text= 'I ACKNOWLEDGE' or @text= 'RECONOZCO']";

    // public static final String NOTIFICATION_NO_THANKS_IOS = "//XCUIElementTypeButton[@name=\"No Thanks\"]";
    public static final String NOTIFICATION_NO_THANKS_IOS = "//XCUIElementTypeButton[@name=\"No Thanks\" or @name=\"No gracias\"]";
    //public static final String REMAINDER_ME_LATER_IOS = "//XCUIElementTypeButton[@name=\"REMIND ME LATER\"]";
    public static final String REMAINDER_ME_LATER_IOS = "//XCUIElementTypeButton[@name=\"REMIND ME LATER\" or @name=\"RECORDARME MÁS TARDE\"]";
    public static final String TERMS_OF_USE = "//*[@id='We have updated our Terms of Use.' and @top='true']";
    //public static final String LOGIN_SUBMIT_BUTTON_IOS = "//XCUIElementTypeButton[@name=\"loginSubmitButton\"]";
    public static final String LOGIN_SUBMIT_BUTTON_IOS = "//*[@name=\"INICIAR SESIÓN\" or @name=\"SIGN IN\"]";



    //public static final String SIGN_IN_LATER_IOS = "SIGN IN LATER";
    public static final String SIGN_IN_LATER_IOS = "onBoardingSignInLaterButton";
    public static final String SIGN_IN_LATER = "signInLaterButton";

}