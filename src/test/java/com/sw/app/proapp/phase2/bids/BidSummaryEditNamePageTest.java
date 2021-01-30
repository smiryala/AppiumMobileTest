package com.sw.app.proapp.phase2.bids;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidNamePage;
import com.sw.proapp.bids.pages.BidPreviewPage;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

import io.appium.java_client.ios.IOSDriver;

public class BidSummaryEditNamePageTest extends MobileCoreBaseTest {
	private boolean firstRun = true;
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private BidNamePage bidNamePage = null;
	private BidPreviewPage bidPreviewPage = null;
	private String language = "";
	String proAppTestCase;
	private Login login = null;
	AppDataFromDB appDataFromDB = null;

	public BidSummaryEditNamePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidsPageTest_class() {
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		bidSummaryPage = new BidSummaryPage(getCommon());
		projectBidsHomePage = new ProjectBidsHomePage(getCommon());
		bidNamePage = new BidNamePage(getCommon());
		bidPreviewPage = new BidPreviewPage(getCommon());
		if(homePage.checkFirstRun()) {
		loginPage.clickSignIn();
		homePage.clickProAppButton();
		accountPage.clickAccountButton();
		accountPage.clickAccountSettingsButton();
		accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_BidsPageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			loginPage.doLogin("protest7@gmail.com" , "sherwin123");
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		// projectBidsHomePage.clickViewAllBidsButton();
		projectBidsHomePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify discard bid button is hidden")
	public void verifyDiscardBidIsHiddenAndroid() throws Exception {
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.clickEditPencilBidName(language);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("Discard Bid°Descartar Cotización", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("DONE°LISTO", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify discard bid button is hidden")
	public void verifyDiscardBidIsHiddenIOS() throws Exception {
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.clickEditPencilBidName(language);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("Discard Bid°Descartar Cotización", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("DONE°LISTO", language));
	}

}
