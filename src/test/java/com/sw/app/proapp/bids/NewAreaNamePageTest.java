package com.sw.app.proapp.bids;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaNamePage;
import com.sw.proapp.bids.pages.NewAreaTypePage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class NewAreaNamePageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private String language="";
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private NewAreaTypePage newAreaTypePage = null;
	private NewAreaNamePage newAreaNamePage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public NewAreaNamePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaNamePageTest_class() {
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
		bidSummaryPage = new BidSummaryPage(getCommon());
		projectBidsHomePage = new ProjectBidsHomePage(getCommon());
		newAreaTypePage = new NewAreaTypePage(getCommon());
		newAreaNamePage = new NewAreaNamePage(getCommon());
		if(homePage.checkFirstRun()) {
		loginPage.clickSignIn();
		homePage.clickProAppButton();
		accountPage.clickAccountButton();
		accountPage.clickAccountSettingsButton();
		accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_NewAreaNamePageTest() {
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
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify area name field has a max of 25 characters")
	public void verifyAreaNameField() throws Exception {
		getCommon().sleepFor(2);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(2);
		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}

		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();

		String char26 = "abcdefghijklmnopqrstuvwxyz";
		String char25 = "abcdefghijklmnopqrstuvwxy";
		Assert.assertFalse(newAreaNamePage.getContinueCta().isEnabled());
		newAreaNamePage.enterAreaName(char26);
		Assert.assertTrue(newAreaNamePage.getAreaNameField().getText().length() < 26, "Area name field is too long");
		newAreaNamePage.enterAreaName(char25);
		Assert.assertEquals(newAreaNamePage.getAreaNameField().getText().length(), 25,
				"Area name field text was wrong");
		Assert.assertTrue(newAreaNamePage.getContinueCta().isEnabled());
		newAreaNamePage.clickDiscardCta();
	}
}