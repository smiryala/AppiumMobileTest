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

public class BidPDFEmalAttachmentPageTest extends MobileCoreBaseTest {
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

	public BidPDFEmalAttachmentPageTest() {
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
		if (homePage.checkFirstRun()) {
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
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		// projectBidsHomePage.clickViewAllBidsButton();
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyAndroidBid2PDFAttachedToEmail() throws Exception {
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.ClickPreviewButton(language);
		bidSummaryPage.scrollToElementByText("COMPOSE EMAIL DRAFT°CREAR BORRADOR DEL EMAIL",
				"Project Info°Información del Proyecto", language);
		bidPreviewPage.clickComposeEmailFraftButton(language);
		bidPreviewPage.clickElementByTextIfExists("OK");
		Assert.assertTrue(bidPreviewPage.elementExistsByText("bid-preview.pdf", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid preview title")
	public void verifyAndroidBid1PreviewBidTitle() throws Exception {
		String textToWaitOnNewArea = bidNamePage.getTextByLanguage("CREATED ON°CREADA EL", language);
		String bidName = bidsPage.getFirstBidOnList(textToWaitOnNewArea);
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.ClickPreviewButton(language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText(bidName, language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void verifyIOSBidPDFAttachedToEmail() throws Exception {
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.ClickPreviewButton(language);
		String youCanSendYourBidByEmailMessage = "You can send this bid to your default email application, where it will open as an email draft, or you can save this bid in the app now.°Puedes enviar este estimado a tu cuenta de email registrada como un borrador o puedes guardarlo en el app ahora mismo.";
		bidSummaryPage.scrollToElementByText(youCanSendYourBidByEmailMessage, language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText("COMPOSE EMAIL DRAFT°CREAR BORRADOR DEL EMAIL", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid preview title")
	public void verifyIOSBidPreviewBidTitle() throws Exception {
		String textToWaitOnNewArea = bidNamePage.getTextByLanguage("CREATED ON°CREADA EL", language);
		String bidName = bidsPage.getFirstBidOnList(textToWaitOnNewArea);
		bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.ClickPreviewButton(language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText(bidName, language));
	}
}
