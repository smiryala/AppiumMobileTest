package com.sw.app.proapp.bids;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.*;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class NewAreaProductsSearchPageTest extends MobileCoreBaseTest {

    private HomePage homePage = null;
    private String language = "";
    private AccountPage accountPage = null;
    private BidsPage bidsPage = null;
    private CreateNewBidPage createNewBidPage = null;
    private BidSummaryPage bidSummaryPage = null;
    private LoginPage loginPage = null;
    private ProjectBidsHomePage projectBidsHomePage = null;
    private NewAreaTypePage newAreaTypePage = null;
    private NewAreaNamePage newAreaNamePage = null;
    private NewAreaImagePage newAreaImagePage = null;
    private NewAreaTasksPage newAreaTasksPage = null;

    private NewAreaProductsSearchPage newAreaProductsSearchPage = null;
    private Login login = null;
    String proAppTestCase;
    AppDataFromDB appDataFromDB = null;

    private String currentArea;

    public NewAreaProductsSearchPageTest() {
        this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
    }

    @BeforeClass(alwaysRun = true)
    public void before_NewAreaProductsSearchPageTest_class() {
        loginPage = new LoginPage(getCommon());
        homePage = new HomePage(getCommon());
        accountPage = new AccountPage(getCommon());
        bidsPage = new BidsPage(getCommon());
        createNewBidPage = new CreateNewBidPage(getCommon());
        bidSummaryPage = new BidSummaryPage(getCommon());
        projectBidsHomePage = new ProjectBidsHomePage(getCommon());
        newAreaTypePage = new NewAreaTypePage(getCommon());
        newAreaNamePage = new NewAreaNamePage(getCommon());
        newAreaImagePage = new NewAreaImagePage(getCommon());
        newAreaTasksPage = new NewAreaTasksPage(getCommon());
        newAreaProductsSearchPage = new NewAreaProductsSearchPage(getCommon());
        if (homePage.checkFirstRun()) {
            loginPage.clickSignIn();
            homePage.clickProAppButton();
            accountPage.clickAccountButton();
            accountPage.clickAccountSettingsButton();
            accountPage.verifyAndSetQADashboard();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void before_NewAreaProductsSearchPageTest() {
        getCommon().setInsertResults(insertResults);
        appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
        login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
        language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
        if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
            loginPage.doLogin(login.getUserName(), login.getPassword());
            homePage.clickAcceptTermsOfUse();
            homePage.clickNotificationsRemindMeLater();
        }
        homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
        homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
        getCommon().sleepFor(3);
        bidsPage.clickOnFirstBidOfTheList(language);
        bidSummaryPage.scrollToElementByText("New Area", language);
        bidSummaryPage.clickNewAreaCTA();
        newAreaTypePage.clickInteriorCta();
        int randomNum = ThreadLocalRandom.current().nextInt(678, 456723546 + 1);
        currentArea = "productTest" + randomNum;
        newAreaNamePage.enterAreaName(currentArea);
        newAreaNamePage.clickContinueCta();
        newAreaImagePage.clickSaveCta();
        newAreaTasksPage.clickSaveCta();
    }

    @Test(groups = {"mobile_regression", "android"}, description = "Verify product add and delete")
    public void verifyProductAddAndDeleteAndroid() throws Exception {
        newAreaProductsSearchPage.clickSearchProductsCta();
        newAreaProductsSearchPage.enterProductName("Paint");
        newAreaProductsSearchPage.clickAddFirstProduct();
        Assert.assertEquals(newAreaProductsSearchPage.getDeleteButtons().size(), 1);
        newAreaProductsSearchPage.clickFirstProductDelete();
        newAreaProductsSearchPage.clickDeleteConfirmYes();
        Assert.assertEquals(newAreaProductsSearchPage.getDeleteButtons().size(), 0);
        newAreaProductsSearchPage.clickSaveAndGo();
        bidSummaryPage.scrollToElementByText(currentArea, language);
        bidSummaryPage.deleteLastAreaCard();
    }

    @Test(groups = {"mobile_regression", "ios"}, description = "Verify product add and delete")
    public void verifyProductAddAndDeleteIOS() throws Exception {
        newAreaProductsSearchPage.clickSearchProductsCta();
        newAreaProductsSearchPage.enterProductName("Paint");
        newAreaProductsSearchPage.clickAddFirstProduct();
        Assert.assertEquals(newAreaProductsSearchPage.getDeleteButtons().size(), 1);
        newAreaProductsSearchPage.clickFirstProductDelete();
        newAreaProductsSearchPage.clickDeleteConfirmYes();
        Assert.assertEquals(newAreaProductsSearchPage.getDeleteButtons().size(), 0);
        newAreaProductsSearchPage.clickSaveAndGo();
        bidSummaryPage.scrollToElementByText(currentArea, language);
        //bidSummaryPage.deleteLastAreaCard();
        bidSummaryPage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByElementName("Delete°Borrar", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.elementClickByContainTextAndSpaceIOS("Confirm", "");
		getCommon().sleepFor(3);
    }
}
