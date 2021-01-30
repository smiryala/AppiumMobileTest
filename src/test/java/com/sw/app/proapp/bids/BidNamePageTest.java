package com.sw.app.proapp.bids;

import java.util.UUID;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidNamePage;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidNamePageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	String language = "";
	private BidsPage bidsPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private BidNamePage bidNamePage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	private String testStringLong = "qQjpKvG6lfWPNp1kGmRZNCnjyl";
	private String testStringShort = "qQjpKvG6lfWPNp1kGmRZNCnjy";

	public BidNamePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	public String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidNamePageTest_class() {
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
		bidSummaryPage = new BidSummaryPage(getCommon());
		projectBidsHomePage = new ProjectBidsHomePage(getCommon());
		bidNamePage = new BidNamePage(getCommon());
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_BidNamePageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
		// ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("VIEW ALL BIDS", language);// clickViewAllBidsButton();
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid name screen loads correctly.")
	public void verifyBidNameScreenLoadsAndroid() throws Exception {
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickBidNameCard();
		Assert.assertTrue(bidNamePage.getBidNameInput().isDisplayed(), "Bid Name Input not displayed");
		Assert.assertTrue(bidNamePage.isSaveButtonDisplayed(), "Save button is not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid name max character input.")
	public void verifyBidNameMaxCharacterInputAndroid() throws Exception {
		getCommon().sleepFor(3);
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		bidSummaryPage.clickBidNameCard();
		getCommon().sleepFor(3);
		bidNamePage.typeBidName(getAlphaNumericString(26));
		getCommon().sleepFor(3);
		Assert.assertTrue(bidNamePage.getBidNameInput().getText().length() < 26, "Input text not exepected length");

		getCommon().sleepFor(3);
		bidNamePage.typeBidName(getAlphaNumericString(25));
		getCommon().sleepFor(3);
		Assert.assertEquals(bidNamePage.getBidNameInput().getText().length(), 25, "Input text not exepected length");

		getCommon().sleepFor(3);
		bidNamePage.clickSave();
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed());
	}

	/*
	 * @Test(groups = { "mobile_regression", "android" }, description =
	 * "Verify discard button.") public void verifyDiscardButtonAndroid() throws
	 * Exception { if (bidsPage.isNoResultsFoundDisplayed()) {
	 * bidsPage.clickCreateNewBidButtonLarge();
	 * createNewBidPage.inputBidName("bidName");
	 * createNewBidPage.clickSaveBidButton(); } else {
	 * bidsPage.clickBidCardHeader(); } bidSummaryPage.clickBidNameCard();
	 * bidNamePage.clickDiscard();
	 * Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed()); }
	 */
	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid name screen loads correctly.")
	public void verify2BidNameScreenLoadsIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickBidNameCard();
		Assert.assertTrue(bidNamePage.getBidNameInput().isDisplayed(), "Bid Name Input not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid name max character input.")
	public void verify1BidNameMaxCharacterInputIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickBidNameCard();
		bidNamePage.typeBidName(getAlphaNumericString(26));
		Assert.assertEquals(getAlphaNumericString(26).length(), 26, "Test string not correct length");
		Assert.assertEquals(bidNamePage.getBidNameInput().getText().length(), 25, "Input text not exepected length");
		bidNamePage.clickElementByText("DONE", language);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed());
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify discard button.")
	public void verify3Discard_DONE_ButtonIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickBidNameCard();
		bidNamePage.clickElementByText("DONE", language);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed());
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid name saved.")
	public void verify1AABidNameSavedIOS() throws Exception {
		String bidName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickBidNameCard();
		bidNamePage.typeBidName(bidName);
		bidNamePage.clickElementByText("DONE", language);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed());
		Assert.assertTrue(bidSummaryPage.isBidNamePresent(bidName), "Bid name not changed as expected");
	}
}