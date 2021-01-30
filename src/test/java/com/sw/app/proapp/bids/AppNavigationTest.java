package com.sw.app.proapp.bids;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.NavigationPage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppNavigationTest extends MobileCoreBaseTest {

    private String language = "";
    private LoginPage loginPage = null;
    private AccountPage accountPage = null;
    private HomePage homePage = null;
    private BidsPage bidsPage = null;
    private BidSummaryPage bidSummaryPage = null;

    private NavigationPage navigationPage = null;

    private Login login = null;

    String proAppTestCase;

    AppDataFromDB appDataFromDB = null;

    public AppNavigationTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeClass(alwaysRun = true)
    public void before_AppNavigationTest_class() {
        loginPage = new LoginPage(getCommon());
        homePage = new HomePage(getCommon());
        accountPage = new AccountPage(getCommon());
        bidsPage = new BidsPage(getCommon());
        bidSummaryPage = new BidSummaryPage(getCommon());

        navigationPage = new NavigationPage(getCommon());

        if (homePage.checkFirstRun()) {
            loginPage.clickSignIn();
            homePage.clickProAppButton();
            accountPage.clickAccountButton();
            accountPage.clickAccountSettingsButton();
            accountPage.verifyAndSetQADashboard();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void AppNavSetup() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
        language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
        if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
            loginPage.doLogin(login.getUserName(), login.getPassword());
            homePage.clickAcceptTermsOfUse();
            homePage.clickNotificationsRemindMeLater();
        }
        if (getCommon().getDriver() instanceof AndroidDriver) navigationPage.clickBottomNavMoreButton();
        homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
        homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
        getCommon().sleepFor(3);
        bidsPage.clickOnFirstBidOfTheList(language);
    }

    @Test(groups = {"mobile_regression", "android"}, description = "Verifying Back Button Logic")
    public void VerifyBackButtonLogicAndroid() {
        bidSummaryPage.clickBidNameCard();
        getCommon().sleepFor(2);
        navigationPage.verifyGoBackFunction();
    }

    @Test(groups = {"mobile_regression", "ios"}, description = "Verifying Back Button Logic")
    public void VerifyBackButtonLogicIOS() {
        bidSummaryPage.clickBidNameCard();
        getCommon().sleepFor(2);
        navigationPage.verifyGoBackFunction();
    }
}

