package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.locators.LoginPageLocators;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends LoginPageLocators {

    public LoginPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Verify login with username {0} and password {1}")
    public LoginPage doLogin(String username, String password, String language) {
        ExtentTestManager.getTest().log(Status.INFO, "Login ProApp");
        String emailLabel = getTextByLanguage("Email°Correo Electrónico", language);
        String passwordLabel = getTextByLanguage("Password°Contraseña", language);
        if (getDriver() instanceof AndroidDriver) {
            androidWaitForElementByText(emailLabel, "unable to find sign in element");
        }
        else {
            iOSWaitForElementByText(emailLabel, "unable to find sign in element");
        }
        hideKeyboard();
        clickElementByText("My Account°Mi cuenta", language);
        if (getDriver() instanceof AndroidDriver) {
            androidWaitForElementByText(emailLabel, "unable to find sign in element");
        }
        else {
            iOSWaitForElementByText(emailLabel, "unable to find sign in element");
        }
        hideKeyboard();
        if (getDriver() instanceof AndroidDriver) {
            clickElementByTextExact("SIGN IN°INICIAR SESIÓN", language);
        }
        if (getDriver() instanceof AndroidDriver) {
            androidWaitForElementByText(emailLabel, "unable to find sign in element");
        }
        else {
            iOSWaitForElementByText(emailLabel, "unable to find sign in element");

        }
        enterTextToElementByText(emailLabel, username);
        enterTextToElementByText(passwordLabel, password);
        clickElementByTextExact("SIGN IN°INICIAR SESIÓN", language);
        if (getDriver() instanceof AndroidDriver) {
        }
        else {
            if (elementExistsByText("ACCOUNT SUMMARY°RESUMEN DE CUENTA", language, false)){
                return this;
            }
            String noThanks= getTextByLanguage("Thanks°gracias", language);
            elementClickByContainTextAndSpaceIOS("No "+noThanks, "No");
            String acknowledge= getTextByLanguage("I Acknowledge°Reconozco", language);
            elementClickByContainTextAndSpaceIOS(acknowledge, acknowledge);
            String later= getTextByLanguage("REMIND ME LATER°RECORDARME MÁS TARDE", language);
            elementClickByContainTextAndSpaceIOS(later, later);
            if (elementExistsByText("ACCOUNT SUMMARY°RESUMEN DE CUENTA", language, false)){
                return this;
            }
        }
        if (!elementExistsByText("ACCOUNT SUMMARY°RESUMEN DE CUENTA", language, false)){
            if (getDriver() instanceof AndroidDriver) {
                clickElementByText("OK", "english");
                clickElementByText("ACKNOWLEDGE°RECONOZCO", language);
            }
        }
        return this;
    }



    @Step("Verify login with username {0} and password {1}")
    public LoginPage doLogin(String username, String password) {
        ExtentTestManager.getTest().log(Status.INFO, "Login ProApp");
        getCommon().sleepFor(2);
        if (!(isHomePageDisplayed() || existsElement(getHomePageName()))) {
            getCommon().clickAndVerifyIsTrue(getSignInButton(), "Unable to click SignIn/Register.");
            getCommon().sleepFor(2);
            if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
                getCommon().typeAndVerifyIsTrue(getUsername(), username, "Unable to send keys to the Logon field as expected.");
                getCommon().typeAndVerifyIsTrue(getPassword(), password, "Unable to send keys to the Logon field as expected.");
            }
            getCommon().clickAndVerifyIsTrue(getLoginSubmitButton(), "Unable to click LoginSubmitButton.");
            if (getDriver() instanceof AndroidDriver) {
                getCommon().sleepFor(10);
                if (existsElement(getPop_ok())) {
                    getCommon().clickAndVerifyIsTrue(getPop_ok(), "Unable to click SignIn/Register.");
                }
                getCommon().sleepFor(3);
                if (existsElement(getAcknowledge())) {
                    getCommon().clickAndVerifyIsTrue(getAcknowledge(), "Unable to click Acknowledge");
                }

            }
        }
        if (getDriver() instanceof IOSDriver) {
//            getCommon().sleepFor(3);
//            if (existsElement(getAcknowledge())) {
//                getCommon().clickAndVerifyIsTrue(getAcknowledge(), "Unable to click Acknowledge");
//            }
//            getCommon().sleepFor(3);
//            if (existsElement(getNotification_NoThanks_IOS())) {
//                getCommon().sleepFor(3);
//                getCommon().clickAndVerifyIsTrue(getNotification_NoThanks_IOS(), "Unable to click No Thanks in IOS");
//                if (existsElement(getDontAllow_IOS())) {
//                    getCommon().sleepFor(3);
//                    getCommon().clickAndVerifyIsTrue(getDontAllow_IOS(), "Unable to click Dont Allow in IOS");
//                }
//            }
            afterSignIniOSAPP();

        }
        return this;
    }

    @Step()
    public LoginPage afterSignIniOSAPP() {

        getCommon().sleepFor(5);
      /*  if (existsElement(getNotification_NoThanks_IOS())) {
            getCommon().sleepFor(5);
            getCommon().clickAndVerifyIsTrue(getNotification_NoThanks_IOS(),
                    "Unable to click No Thanks in IOS");
        }*/
        getCommon().sleepFor(5);
        if (existsElement(getAcknowledge())) {
            getCommon().sleepFor(5);
            getCommon().clickAndVerifyIsTrue(getAcknowledge(), "Unable to click Acknowledge");
        }
        getCommon().sleepFor(5);
        if (existsElement(getNotification_NoThanks_IOS())) {
            getCommon().sleepFor(5);
            getCommon().clickAndVerifyIsTrue(getNotification_NoThanks_IOS(),
                    "Unable to click No Thanks in IOS");

        }
        getCommon().sleepFor(5);
        //if (existsElement(getDontAllow_IOS())) {
        if (existsElement(getRemaindMeLater_IOS())) {
            getCommon().sleepFor(5);
            getCommon().clickAndVerifyIsTrue(getRemaindMeLater_IOS(),
                    "Unable to click RemaindMeLater in IOS");

        }
       /* WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = getDriver().switchTo().alert().getText();
            if (alertMessage.contains("Notifications")) {
                getDriver().switchTo().alert().dismiss();
            }
        } catch (Exception e) {
            System.err.println("no alert visible after 10 sec.");
        }*/
        getCommon().sleepFor(5);
		
		return this;
    }

    @Step
    public LoginPage clickSignInLater() {
        if (existsElement(getSignInLater())) {
            getCommon().sleepFor(2);
            getCommon().clickAndVerifyIsTrue(getSignInLater(), "Unable to click SignInLater.");
        }
        return this;
    }

    @Step
    public LoginPage clickSignIn() {
        if (getDriver() instanceof AndroidDriver) {
            if (existsElement(getSignInButton())) {
                getCommon().clickAndVerifyIsTrue(getSignInButton(),
                        "Unable to click SignIn/Register.");
            }
        } else {
            if (existsElement(getSignInLater())) {
                getCommon().sleepFor(2);
                getCommon().clickAndVerifyIsTrue(getSignInLater(), "Unable to click SignInLater.");
            } else if (existsElement(getSignInButton())) {
                getCommon().sleepFor(2);
                getCommon().clickAndVerifyIsTrue(getSignInButton(),
                        "Unable to click SignIn/Register.");
            }
        }
        return this;
    }

    @Step
    public LoginPage clickLoginSubmitSignInButton() {
        if (!(existsElement(getHomePageName()))) {
            ExtentTestManager.getTest().log(Status.INFO, "clickLoginSubmitSignInButton");
            getCommon().clickAndVerifyIsTrue(getLoginSubmitButton(),
                    "Unable to click SignIn Submbit.");
        }
        return this;
    }

    @Step
    public LoginPage enterPassword(String password) {
        if (existsElement(getCheckoutPassword())) {
            getCommon().sleepFor(10);
            if (existsElement(getCheckoutPassword())) {
                getCommon().clickAndVerifyIsTrue(getCheckoutPassword(),
                        "Unable to click checkout password.");
                getCommon().typeAndVerifyIsTrue(getCheckoutPassword(), password,
                        "Unable to send keys to the checkout password");
            }
            getCommon().clickAndVerifyIsTrue(getCheckoutPasswordOK(),
                    "Unable to click checkout password.");
        }
        getCommon().sleepFor(2);
        return this;
    }

    @Step("enterUserNamePwd username {0} and password {1}")
    public LoginPage enterUserNamePwd(String username, String password) {
        getCommon().sleepFor(2);
        if (username != null && password != null && !username.trim().isEmpty() && !password.trim()
                .isEmpty()) {
            getCommon().typeAndVerifyIsTrue(getUsername(), username,
                    "Unable to send keys to the Logon field as expected.");
            getCommon().typeAndVerifyIsTrue(getPassword(), password,
                    "Unable to send keys to the Logon field as expected.");
        }
        return this;
    }

    @Step()
    public LoginPage enterLoginPassword(String password) {
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getPassword(), "Unable to click checkout password.");
        getCommon().typeAndVerifyIsTrue(getPassword(), password,
                "Unable to send keys to the checkout password");
        return this;
    }

    @Step()
    public void verifyHomePageIsLaunched(String titleName) {
        if (getHomePageName().getText().contains(titleName)) {
        }
    }

    @Step()
    public RegistrationPage clickNeedALogin() {
        ExtentTestManager.getTest().log(Status.INFO, "click needALogin");
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getNeedALogin(), "Unable to click Need A Login.");
        getCommon().sleepFor(2);
        return new RegistrationPage(getCommon());
    }

    @Step("Entered email is {0}")
    public LoginPage enterEmail(String email) {
        this.clickElementByText("Email°Correo Electrónico",languageBase);
        getCommon().typeAndVerifyIsTrue(username, email, "Unable to send keys to the email ");
        getCommon().clickAndVerifyIsTrue(username, "Unable to click on username");
        getCommon().sleepFor(2);
        pressEnter();
        return this;
    }

    @Step()
    public String getEmailErrorMessage() {
        getCommon().sleepFor(2);
        if (existsElement(emailErrorMessage)) {
            if (getDriver() instanceof IOSDriver) {
                return getCommon().getAttribute(emailErrorMessage, "value");
            } else {
                return getCommon().getText(emailErrorMessage, "Unable to error message");
            }
        } else {
            return "Error Message Doesn't exist";
        }

    }

    @Step()
    public String getSignInErrorMessage() {
        //if (getCommon().getWaitUntil()
        //      .visibilityOfElements(signInErrorHeader, "Error Message not displayed")
        //   && existsElement(signInErrorMessage)) {
        if (getDriver() instanceof IOSDriver) {
            WebElement element = getDriver().findElementByXPath("//XCUIElementTypeAlert[@name=\"Correo electrónico o contraseña incorrecta\" or @name=\"Incorrect email or password.\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]");
            return getCommon().getAttribute(element, "value") + " " +
                    getCommon().getAttribute(signInErrorMessage, "value").replace("\n", " ")
                            .replace("\\s+", " ");

        } else {
            WebElement element = getDriver().findElementByXPath("//*[@text='Incorrect email or password.' or @text='Contraseña o correo electrónico incorrecto.']");
            return getCommon().getText(element, "Unable to error message") + " " +
                    getCommon().getText(signInErrorMessage, "Unable to error message")
                            .replace("\n", " ").replace("\\s+", " ");
            //}
        } //else {
        //return "Error Message Doesn't exist";
        //}
    }

    @Step()
    public void clickTryAgain() {
        getCommon().sleepFor(2);
        if (existsElement(tryAgain)) {
            getCommon().clickAndVerifyIsTrue(tryAgain, "Unable to click Try Again");
        }
    }

    @Step()
    public void clickLoginPopUpOK() {
        getCommon().sleepFor(10);
        if (existsElement(getPop_ok())) {
            getCommon().clickAndVerifyIsTrue(getPop_ok(), "Unable to click ok popup after SignIn/Register.");
        }
    }

    @Step()
    public void clickLoginTermsOfUser() {
        getCommon().sleepFor(5);
        if (existsElement(acknowledge)) {
            getCommon().clickAndVerifyIsTrue(acknowledge, "Unable to click I acknowledge after SignIn/Register.");
        }
    }
}