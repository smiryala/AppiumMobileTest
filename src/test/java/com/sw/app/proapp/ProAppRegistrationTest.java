package com.sw.app.proapp;

import com.sw.core.helpers.Common;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.LoginPage;
import com.sw.proapp.pages.RegistrationPage;
import io.qameta.allure.Step;
import java.util.ArrayList;
import org.testng.Assert;

public class ProAppRegistrationTest {

    private LoginPage loginPage = null;
    private RegistrationPage registrationPage = null;
    private AccountPage accountPage = null;

    public ProAppRegistrationTest(Common common,String language) {
        loginPage = new LoginPage(common);
        registrationPage = new RegistrationPage(common);
        accountPage = new AccountPage(common);

        loginPage.languageBase = language;
        registrationPage.languageBase = language;
        accountPage.languageBase = language;
    }

    /*@Step()
    public void verifyRegistrationPageAndroid() {
        accountPage.signOutProApp();
        //Assert.assertTrue(loginPage.getNeedALogin().isDisplayed(), "Need a login is NOT displayed");
        loginPage.clickSignIn();
        registrationPage = loginPage.clickNeedALogin();

        //Check Create Account Header
        Assert.assertEquals(registrationPage.createAccountHeader(), "HOW TO CREATE AN ACCOUNT");

        //Check Registration Text
        Assert.assertEquals(
                registrationPage.currentCustomer().replaceAll("\n", " ").replaceAll("\\s+", " ")
                        .toLowerCase(),
                "Are you a Current Sherwin-Williams Customer?".toLowerCase());
        Assert.assertEquals(registrationPage.description().replaceAll("\n", " ")
                        .replaceAll("[^\\x00-\\x7F]", " ").replaceAll("\\s+", " ").toLowerCase(),
                "Contact your local store or rep to have a profile created for you. you can also visit Sherwin-Williams.com/pro and register for a mysw profile all you need is your nine-digit account number. "
                        .toLowerCase());

        //Check Footer
        Assert.assertEquals(
                registrationPage.newToSW().replaceAll("\n", " ").replaceAll("\u2028", " ")
                        .replaceAll("\\s+", " ").toLowerCase(),
                "New to Sherwin-Williams?".toLowerCase());
        Assert.assertEquals(
                registrationPage.visitLocal().replaceAll("\n", " ").replaceAll("\\s+", " ")
                        .toLowerCase(),
                "Visit your local store or have a rep contact you.".toLowerCase());
        registrationPage.clickSherwinWillamscomPro();

        //BLOCKED due to inspect elements
        registrationPage.sleepFor(3);
        Assert.assertEquals(registrationPage.getBrowserUlrText(),
                "https://www.sherwin-williams.com/painting-contractors/pro-offers");
        registrationPage.clickBackButton();

        registrationPage.sleepFor(3);
        registrationPage.clickRepContactYou();
        registrationPage.sleepFor(3);

        Assert.assertEquals(registrationPage.getBrowserUlrText(),
                "https://www.sherwin-williams.com/painting-contractors/contact-us/sales");
        registrationPage.clickBackButton();

        registrationPage.sleepFor(3);

        loginPage = registrationPage.navigateBack();
    }

    @Step()
    public void verifyRegistrationPageIOS() {
        //loginPage.clickSignInLater();
        accountPage.signOutProApp();
        //Assert.assertTrue(loginPage.getNeedALogin().isDisplayed(), "Need a login is NOT displayed");
        loginPage.clickSignIn();
        registrationPage = loginPage.clickNeedALogin();

        //Check Create Account Header
        //Assert.assertEquals(registrationPage.createAccountHeader(), "HOW TO CREATE AN ACCOUNT");
        Assert.assertTrue(registrationPage.getCreateAccountHeader().isDisplayed(),
                "CreateAccountHeader is NOT displayed");

        //Check Registration Text
        //Assert.assertEquals(registrationPage.currentCustomer().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Are you a Current Sherwin-Williams Customer?".toLowerCase());
        Assert.assertTrue(registrationPage.getCurrentCustomer().isDisplayed(),
                "'Are you a Current Sherwin-Williams Customer?' is NOT displayed");
        //Assert.assertEquals(registrationPage.description().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Contact your local store or rep to have a profile created for you. You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.".toLowerCase());

        //Contact your local store or rep to have a profile created for you.
        Assert.assertTrue(registrationPage.getDescription_1_ios().isDisplayed(),
                "'Contact your local store or rep to have a profile created for you' is NOT displayed");

        //You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.
        Assert.assertTrue(registrationPage.getDescription_2_ios().isDisplayed(),
                "'You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.' is NOT displayed");

        //Check Footer
        // Assert.assertEquals(registrationPage.newToSW().replaceAll("\n", " ").replaceAll("\u2028", " ").replaceAll("\\s+", " ").toLowerCase(), "New to Sherwin-Williams? ".toLowerCase());
        //Assert.assertEquals(registrationPage.visitLocal().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Visit your local store or have a rep contact you.".toLowerCase());

        Assert.assertTrue(registrationPage.getNewToSW().isDisplayed(),
                "'NEW TO  SHERWIN-WILLIAMS?' is NOT displayed");
        Assert.assertTrue(registrationPage.getVisitLocal_1_ios().isDisplayed(),
                "'Visit your local store or have' is NOT displayed");
        Assert.assertTrue(registrationPage.getVisitLocal_2_ios().isDisplayed(),
                "'a rep contact you.' is NOT displayed");

        //registrationPage.clickSherwinWillamscomPro();

        //Assert.assertTrue(registrationPage.getBrowserUlrText().contains("‎sherwin-williams.com"));

        //BLOCKED due to inspect elements
        // Assert.assertEquals(registrationPage.getiOSYourSherwinPro().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Your Sherwin-Williams PRO Account".toLowerCase());

        //registrationPage.clickDone();

       // registrationPage.clickRepContactYou();

       // Assert.assertEquals(registrationPage.getLocalRep().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Sales Representative Inquiry Form".toLowerCase());

       // registrationPage.clickDone();

       // logReportWithScreenShot("Registration details are  displayed");

    }*/

    @Step()
    public void verifyRegistrationPageAndroid() {
        accountPage.signOutProApp();
        //Assert.assertTrue(loginPage.getNeedALogin().isDisplayed(), "Need a login is NOT displayed");
        ///NOT NEEDED ---loginPage.clickSignIn();
        //NOT NEEDED ---registrationPage = loginPage.clickNeedALogin();

        loginPage.hideKeyboard();
        loginPage.clickElementByTextExact("SIGN UP", loginPage.languageBase);
        // Check Create Account Header
        String title = registrationPage.getTextByLanguage("HOW TO CREATE AN ACCOUNT", loginPage.languageBase);
        Assert.assertEquals(registrationPage.createAccountHeader(), title);
        // Check Registration Text
        String existingClient = registrationPage.getTextByLanguage("Are you a Current Sherwin-Williams Customer?", loginPage.languageBase);
        Assert.assertEquals(
                registrationPage.currentCustomer().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(),
                existingClient.toLowerCase());
        String contactLabel = registrationPage.getTextByLanguage("Contact your local store or rep to have a profile created for you. you can also visit Sherwin-Williams.com/pro and register for a mysw profile all you need is your nine-digit account number. ",
                loginPage.languageBase);
        boolean exists = registrationPage.elementExistsByText("You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.",
                loginPage.languageBase);
        Assert.assertTrue(exists);
        // Check Footer
        String newSW = registrationPage.getTextByLanguage("New to Sherwin-Williams?",
                loginPage.languageBase);
        Assert.assertEquals(registrationPage.newToSW().replaceAll("\n", " ").replaceAll("\u2028", " ")
                .replaceAll("\\s+", " ").toLowerCase(), newSW.toLowerCase());
        String visitYourNewLocal = registrationPage.getTextByLanguage("Visit your local store or have a rep contact you.",
                loginPage.languageBase);
        Assert.assertEquals(registrationPage.visitLocal().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(),				visitYourNewLocal.toLowerCase());
        registrationPage.clickSherwinWillamscomPro();

        //BLOCKED due to inspect elements
        /*registrationPage.sleepFor(3);
        Assert.assertEquals(registrationPage.getBrowserUlrText(),
                "https://www.sherwin-williams.com/painting-contractors/pro-offers");
        registrationPage.clickBackButton();
        registrationPage.sleepFor(3);
        registrationPage.clickRepContactYou();
        registrationPage.sleepFor(3);
        Assert.assertEquals(registrationPage.getBrowserUlrText(),
                "https://www.sherwin-williams.com/painting-contractors/contact-us/sales");
        registrationPage.clickBackButton();
        registrationPage.sleepFor(3);
        loginPage = registrationPage.navigateBack();*/
    }

    @Step()
    public void verifyRegistrationPageIOS() {
        //loginPage.clickSignInLater();
        accountPage.signOutProApp();
        loginPage.clickElementByTextExact("SIGN UP", registrationPage.languageBase);
        //Assert.assertTrue(loginPage.getNeedALogin().isDisplayed(), "Need a login is NOT displayed");
        //----NOT NEEDED loginPage.clickSignIn();
        //----NOT NEEDEDregistrationPage = loginPage.clickNeedALogin();
        String howtoCreate = registrationPage.getTextByLanguage("HOW TO CREATE AN ACCOUNT", registrationPage.languageBase);
        registrationPage.androidWaitForElementByText(howtoCreate, "unable to wait for how to create");
        //Check Create Account Header
        //Assert.assertEquals(registrationPage.createAccountHeader(), "HOW TO CREATE AN ACCOUNT");
        Assert.assertTrue(registrationPage.getCreateAccountHeader().isDisplayed(),
                "CreateAccountHeader is NOT displayed");

        //Check Registration Text
        //Assert.assertEquals(registrationPage.currentCustomer().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Are you a Current Sherwin-Williams Customer?".toLowerCase());
        String titleLabel = registrationPage.getTextByLanguage("ARE YOU A CURRENT CUSTOMER?", registrationPage.languageBase);
        if(registrationPage.languageBase.equals("english")) {
            ArrayList<String> words = new ArrayList<String>();
            words.add("ARE YOU A CURRENT");
            words.add("SHERWIN-WILLIAMS");
            words.add("CUSTOMER?");
            Assert.assertTrue(registrationPage.elementExistsByContainAllWordsIOS(words),"'Are you a Current Sherwin-Williams Customer?' is NOT displayed");
        }
        else {
            Assert.assertTrue(registrationPage.elementExistsByText("ARE YOU A CURRENT\r\nSHERWIN-WILLIAMS\r\nCUSTOMER?", registrationPage.languageBase),"'Are you a Current Sherwin-Williams Customer?' is NOT displayed");
        }

        // Assert.assertTrue(registrationPage.elementExistsByText("ARE YOU A CURRENT\r\nSHERWIN-WILLIAMS\r\nCUSTOMER?°¿ERES CLIENTE ACTUAL DE SHERWIN-WILLIAMS?", registrationPage.languageBase),"'Are you a Current Sherwin-Williams Customer?' is NOT displayed");
        //Assert.assertEquals(registrationPage.description().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Contact your local store or rep to have a profile created for you. You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.".toLowerCase());

        //Contact your local store or rep to have a profile created for you.
        Assert.assertTrue(registrationPage.getDescription_1_ios().isDisplayed(),
                "'Contact your local store or rep to have a profile created for you' is NOT displayed");

        //You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.
        Assert.assertTrue(registrationPage.getDescription_2_ios().isDisplayed(),
                "'You can also visit Sherwin-Williams.com/pro and register for a mySW profile—all you need is your nine-digit account number.' is NOT displayed");

        //Check Footer
        // Assert.assertEquals(registrationPage.newToSW().replaceAll("\n", " ").replaceAll("\u2028", " ").replaceAll("\\s+", " ").toLowerCase(), "New to Sherwin-Williams? ".toLowerCase());
        //Assert.assertEquals(registrationPage.visitLocal().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Visit your local store or have a rep contact you.".toLowerCase());

        String newToSWLabel2 = registrationPage.getTextByLanguage("NEW TO SHERWIN-WILLIAMS?", registrationPage.languageBase);

        if(registrationPage.languageBase.equals("english")) {
            ArrayList<String> words = new ArrayList<String>();
            words.add("NEW TO");
            //SHERWIN-
            words.add("SHERWIN-");
            words.add("WILLIAMS?");
            Assert.assertTrue(registrationPage.elementExistsByContainAllWordsIOS(words),"'NEW TO  SHERWIN-WILLIAMS?' is NOT displayed");
        }
        else {
            Assert.assertTrue(registrationPage.elementExistsByText("ARE YOU A CURRENT\r\nSHERWIN-WILLIAMS\r\nCUSTOMER?°¿ERES CLIENTE ACTUAL DE SHERWIN-WILLIAMS?", registrationPage.languageBase),"’NEW TO  SHERWIN-WILLIAMS?' is NOT displayed");
        }
        Assert.assertTrue(registrationPage.getVisitLocal_1_ios().isDisplayed(),
                "'Visit your local store or have' is NOT displayed");
        Assert.assertTrue(registrationPage.getVisitLocal_2_ios().isDisplayed(),
                "'a rep contact you.' is NOT displayed");
        //registrationPage.clickSherwinWillamscomPro();

        //Assert.assertTrue(registrationPage.getBrowserUlrText().contains("‎sherwin-williams.com"));
        //BLOCKED due to inspect elements
        // Assert.assertEquals(registrationPage.getiOSYourSherwinPro().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Your Sherwin-Williams PRO Account".toLowerCase());

        //registrationPage.clickDone();

        // registrationPage.clickRepContactYou();

        // Assert.assertEquals(registrationPage.getLocalRep().replaceAll("\n", " ").replaceAll("\\s+", " ").toLowerCase(), "Sales Representative Inquiry Form".toLowerCase());

        // registrationPage.clickDone();

        // logReportWithScreenShot("Registration details are  displayed");

    }

}