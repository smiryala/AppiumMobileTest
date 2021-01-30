package com.sw.app.proapp;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import io.appium.java_client.android.AndroidDriver;

public class ProAppLoginPageTest {

    private LoginPage loginPage = null;
    private AccountPage accountPage = null;
    private HomePage homePage = null;
    private Login login = null;
    AppDataFromDB appDataFromDB = null;
    String proAppTestCase= null;

    public ProAppLoginPageTest(Common common,String proAppTestCase, String language) {
        appDataFromDB = new AppDataFromDB(common.getInsertResults());
        loginPage = new LoginPage(common);
        homePage = new HomePage(common);
        accountPage = new AccountPage(common);
        this.proAppTestCase = proAppTestCase;
        login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
        loginPage.languageBase = language;
        homePage.languageBase = language;
        accountPage.languageBase = language;
    }

    /*@Step
    public void verifyLoginPageAndroid() {
        accountPage.signOutProApp();
        homePage.clickProAppButton();

//        logReportWithScreenShot("Home Page is displayed");
        loginPage.clickSignIn();

        //validate the Email accepts right email format


        loginPage.enterEmail("invalid_email");
        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Email Address is invalid");

        loginPage.enterEmail(login.getUserName());
        loginPage.enterLoginPassword("invalid Password");
        loginPage.clickSignIn();
        Assert.assertEquals(loginPage.getSignInErrorMessage(),
                "Incorrect email or password. Please enter a valid email address or password to sign in.");
//        logReportWithScreenShot("SignIn Error Message is displayed");
        loginPage.clickTryAgain();

        loginPage.enterEmail(login.getUserName());
        loginPage.enterLoginPassword(login.getPassword());
        loginPage.clickSignIn();
        loginPage.sleepFor(10);
        loginPage.getDriver().navigate().back();
        loginPage.clickLoginPopUpOK();
        loginPage.clickLoginTermsOfUser();
        accountPage.clickAccountButton();
        Assert.assertTrue(homePage.isLoggedInHomepageDispalyed(), "Not on HomePage");
//        logReportWithScreenShot("Home Page is displayed");
    }

    @Step()
    public void verifyLoginPageIOS() {
        accountPage.signOutProApp();
        homePage.clickProAppButton();
//      logReportWithScreenShot("Home Page is displayed");
        loginPage.clickSignIn();
        //validate the Email accepts right email format
        loginPage.enterEmail("invalid_email");
        loginPage.assertEquals(loginPage.getEmailErrorMessage(), "Email address is invalid");
//      logReportWithScreenShot("Error Message is displayed");
        //loginPage.enterEmail(login.getUserName());
        //loginPage.enterLoginPassword("invalid Password");
        loginPage.enterUserNamePwd(login.getUserName(), "invalid Password");
        loginPage.clickLoginSubmitSignInButton();
        loginPage.assertEquals(loginPage.getSignInErrorMessage(),
                "Incorrect email or password. Please enter a valid email address or password to sign in.");
//      logReportWithScreenShot("SignIn Error Message is displayed");
        loginPage.clickTryAgain();
        // loginPage.enterEmail(login.getUserName());
        // loginPage.enterLoginPassword(login.getPassword());
        loginPage.enterUserNamePwd(login.getUserName(), login.getPassword());
        loginPage.clickLoginSubmitSignInButton();
        loginPage.clickLoginTermsOfUser();
        homePage.clickNotificationsRemindMeLater();
        accountPage.clickAccountButton();
        loginPage.assertTrue(homePage.isLoggedInHomepageDispalyed(), "Not on HomePage");
//      logReportWithScreenShot("Home Page is displayed");
        accountPage.signOutProApp();
    }*/

    @Step
    public void verifyLoginPageAndroid() {
        //Not needed accountPage.signOutProApp();
        //---NOT NEEDED homePage.clickProAppButton();

//        logReportWithScreenShot("Home Page is displayed");
        //---loginPage.clickSignIn();

        /*validate the Email accepts right email format
         */
        String expectedError = loginPage.getTextByLanguage("Email Address is invalid°Correo electrónico incorrecto",
        loginPage.languageBase);
        loginPage.androidWaitForElementByText("wait for login page to be ready", "wait for login page to be ready successful");
        loginPage.enterEmail("invalid_email");
        Assert.assertEquals(loginPage.getEmailErrorMessage(), expectedError);

        loginPage.enterEmail(login.getUserName());
        loginPage.enterLoginPassword("invalid Password");
        loginPage.hideKeyboard();
        loginPage.clickElementByTextExact("SIGN IN°INICIAR SESIÓN", loginPage.languageBase);
        expectedError = loginPage.getTextByLanguage(
                					"Incorrect email or password. Please enter a valid email address or password to sign in.°Contraseña o correo electrónico incorrecto. Favor de ingresar correo electrónico o contraseña válida para iniciar sesión.",
                loginPage.languageBase);
        loginPage.androidWaitForElementByText("wait for error to load", "wait for error to load successful");
        Assert.assertEquals(loginPage.getSignInErrorMessage(),
                expectedError);
//        logReportWithScreenShot("SignIn Error Message is displayed");
        loginPage.clickTryAgain();

        loginPage.enterEmail(login.getUserName());
        loginPage.enterLoginPassword(login.getPassword());
        loginPage.hideKeyboard();
        loginPage.clickElementByTextExact("SIGN IN°INICIAR SESIÓN", loginPage.languageBase);
        loginPage.sleepFor(10);
        //--NOT NEEDEDloginPage.getDriver().navigate().back();
        loginPage.clickElementByText("OK", "english");
        loginPage.clickElementByText("ACKNOWLEDGE°RECONOZCO", loginPage.languageBase);
        Assert.assertTrue(homePage.isLoggedInHomepageDispalyed(), "Not on HomePage");
//        logReportWithScreenShot("Home Page is displayed");

    }

    @Step()
    public void verifyLoginPageIOS() {
        //Not needed accountPage.signOutProApp();
        //-> NOT NEEDED homePage.clickProAppButton();
//      logReportWithScreenShot("Home Page is displayed");
        //-> NOT NEEDED loginPage.clickSignIn();
        //validate the Email accepts right email format
        String emailLabel = loginPage.getTextByLanguage("Email°Correo Electrónico", loginPage.languageBase);
        loginPage.enterTextToElementByText(emailLabel,"invalid_email");
        loginPage.hideKeyboard();
        String expectedErrorMessage=loginPage.getTextByLanguage("Please enter a valid email address.°Favor de ingresar un correo electrónico válida.",loginPage.languageBase);
        Assert.assertTrue(loginPage.elementExistsByText("Please enter a valid email address.°Favor de ingresar un correo electrónico válida.",loginPage.languageBase));//      logReportWithScreenShot("Error Message is displayed");
        //loginPage.enterEmail(login.getUserName());
        //loginPage.enterLoginPassword("invalid Password");
        String passwordLabel = loginPage.getTextByLanguage("Password°Contraseña", loginPage.languageBase);
        loginPage.enterTextToElementByText("invalid_email",login.getUserName());
        loginPage.enterTextToElementByText(passwordLabel,"invalid Password");
        loginPage.clickElementByTextExact("SIGN IN", loginPage.languageBase);
        expectedErrorMessage=loginPage.getTextByLanguage("Incorrect email or password. Please enter a valid email address or password to sign in.°Correo electrónico o contraseña incorrecta Favor de ingresar un correo electrónico o contraseña válida para iniciar sesión.",loginPage.languageBase);
        loginPage.iOSWaitForElementByText("Incorrect email or password. Please enter a valid email address or password to sign in.°Correo electrónico o contraseña incorrecta Favor de ingresar un correo electrónico o contraseña válida para iniciar sesión.","unable to wait for error");
        loginPage.assertEquals(loginPage.getSignInErrorMessage(),
                expectedErrorMessage);
//      logReportWithScreenShot("SignIn Error Message is displayed");
        loginPage.clickTryAgain();
        // loginPage.enterEmail(login.getUserName());
        // loginPage.enterLoginPassword(login.getPassword());
        loginPage.enterTextToElementByText(emailLabel,login.getUserName());
        loginPage.enterTextToElementByText(passwordLabel, login.getPassword());
        loginPage.clickElementByTextExact("SIGN IN°INICIAR SESIÓN", loginPage.languageBase);
        //NOT NEEDED---loginPage.clickLoginTermsOfUser();
        //NOT NEEDED---homePage.clickNotificationsRemindMeLater();
        //NOT NEEDED---accountPage.clickAccountButton();
        String noThanks= loginPage.getTextByLanguage("Thanks°gracias", loginPage.languageBase);
        loginPage.elementClickByContainTextAndSpaceIOS("No "+noThanks, "No");
        loginPage.elementClickByContainTextAndSpaceIOS("No "+noThanks, "No");
        String acknowledge=loginPage.getTextByLanguage("I Acknowledge°Reconozco", loginPage.languageBase);
        loginPage.elementClickByContainTextAndSpaceIOS(acknowledge, acknowledge);
        String later= loginPage.getTextByLanguage("REMIND ME LATER°RECORDARME MÁS TARDE", loginPage.languageBase);
        loginPage.elementClickByContainTextAndSpaceIOS(later, later);
        loginPage.assertTrue(homePage.isLoggedInHomepageDispalyed(), "Not on HomePage");
//      logReportWithScreenShot("Home Page is displayed");
        // NOT NEEDED accountPage.signOutProApp();
    }

}