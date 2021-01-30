package com.sw.app.proapp.bids;

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
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.bids.pages.ProjectNotesPage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidNotesPageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	String language = "";
	private CreateNewBidPage createNewBidPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private ProjectNotesPage projectNotesPage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	private String testStringLong = "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";
	private String testStringShort = "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";

	public BidNotesPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
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
		projectNotesPage = new ProjectNotesPage(getCommon());
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_BidNotesPageTest() {
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
		getCommon().sleepFor(3);
		homePage.clickElementByText("VIEW ALL BIDS", language);
		// projectBidsHomePage.clickViewAllBidsButton();
		getCommon().sleepFor(3);
		bidsPage.clickOnFirstBidOfTheList(language);

		homePage.scrollToElementByText("Project Notes°Notas Del Proyecto", language);
		homePage.clickElementByText("Project Notes", language);
		getCommon().sleepFor(3);
		/*
		 * if (bidsPage.isNoResultsFoundDisplayed()) {
		 * bidsPage.clickCreateNewBidButtonLarge();
		 * createNewBidPage.inputBidName("bidName");
		 * createNewBidPage.clickSaveBidButton(); } else {
		 * bidsPage.clickBidCardHeader(); }
		 * 
		 * while (!getCommon().isDisplayed(bidSummaryPage.getProjectNotesCard())) {
		 * 
		 * bidSummaryPage.scrollDown3(); } bidSummaryPage.clickProjectNotesCard();
		 */

	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid notes screen loads correctly.")
	public void verify1BidNotesScreenLoadsIOS() throws Exception {
		Assert.assertTrue(projectNotesPage.isLoogedInProjectNotesPageDispalyed(), "Project Notes page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"ios" }, description = "Verify bid notes screen max input and save functionality.")
	public void verifyBidNotesMaxCharacterInputIOS() throws Exception {
		projectNotesPage.typeNote(testStringLong);

		Assert.assertEquals(testStringLong.length(), 2001, "Test string is not correct length");
		Assert.assertTrue(projectNotesPage.getFullCharacterCount().isDisplayed(),
				"Full character count label not displayed");

		projectNotesPage.clickSaveButton();

		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary screen not shown as expected");
		Assert.assertFalse((bidSummaryPage.isProjectNotesPlaceholderTextDisplayed()),
				"Notes placeholder text unexpectedly displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid notes screen discard functionality.")
	public void verify1AAAAABidNotesDiscardButtonIOS() throws Exception {
		String notesInputText = projectNotesPage.getNotesInput().getText();
		// add a note if none exists
		if (notesInputText.contains("Enter any additional project-level notes")
				|| notesInputText.contains("Agregar notas adicionales")) {
			projectNotesPage.typeNote("Sample note");
			projectNotesPage.clickElementByText("DONE", language);
			getCommon().sleepFor(2);
			bidSummaryPage.scrollToElementByText("Project Notes", language);
			bidSummaryPage.clickElementByText("Project Notes", language);// notesInputText);//clickProjectNotesCard();
		}
		getCommon().sleepFor(2);
		projectNotesPage.clickElementByText("Discard Notes", language);
		getCommon().sleepFor(2);
		projectNotesPage.elementClickByContainTextAndSpaceIOS(", Discard", language);// ("Yes, Discard", language);
		getCommon().sleepFor(2);
		// Assert.assertTrue(bidSummaryPage.getProjectNotesPlaceholderText().isDisplayed(),
		// "Notes placeholder text not displayed as expected");
		Assert.assertTrue(bidSummaryPage.isProjectNotesPlaceholderTextDisplayed(),
				"Notes placeholder text not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid notes screen loads correctly.")
	public void verifyBidNotesScreenLoadsAndroid() throws Exception {
		Assert.assertTrue(projectNotesPage.isLoogedInProjectNotesPageDispalyed(), "Project Notes page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify bid notes screen max input and save functionality.")
	public void verifyBidNotesMaxCharacterInputAndroid() throws Exception {
		projectNotesPage.typeNote(testStringLong);

		Assert.assertEquals(testStringLong.length(), 2001, "Test string is not correct length");
		Assert.assertTrue(projectNotesPage.getNotesInput().getText().length() < 2001, "Input text is too long");

		projectNotesPage.typeNote(testStringShort);
		// projectNotesPage.getCharacterCount().getText()
		if (projectNotesPage.elementExistsByText("2000/2000", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(projectNotesPage.elementExistsByText("2000 / 2000", language));
		} // , , "Full character count
			// label not displayed");

		Assert.assertTrue(projectNotesPage.isSaveButtonDisplayed(), "Save button not displayed");
		projectNotesPage.clickSaveButton();

		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary screen not shown as expected");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify bid notes screen max input and save functionality.")
	public void verifyAAABidNotesDiscardAndroid() throws Exception {

		projectNotesPage.typeNote("note");
		projectNotesPage.clickElementByText("DONE", language);
		// projectNotesPage.getCharacterCount().getText()
		bidSummaryPage.scrollToElementByText("Project Notes", language);
		bidSummaryPage.clickProjectNotesCard();
		String currentNote = projectNotesPage.getNotesInput().getText();
		projectNotesPage.clickElementByText("Discard Notes", language);
		Assert.assertTrue(projectNotesPage.elementExistsByText(
				"Are you sure you’d like to discard this information? This action can’t be undone", language));
		Assert.assertTrue(projectNotesPage.elementExistsByText("NO, GO BACK", language));
		Assert.assertTrue(projectNotesPage.elementExistsByText("YES, DISCARD", language));
		projectNotesPage.clickElementByText("YES, DISCARD", language);
		bidSummaryPage.scrollToElementByText("Project Notes", language);
		bidSummaryPage.clickProjectNotesCard();
		currentNote = projectNotesPage.getNotesInput().getText();
		Assert.assertTrue(currentNote.equals("Enter any additional project-level notes or requests here."));
	}

	@Test(groups = { "mobile_regression",
			"ios" }, description = "Verify bid notes screen max input and save functionality.")
	public void verify1AAAABidNotesDiscardIOS() throws Exception {
		projectNotesPage.clickElementByText("Discard Notes", language);
		Assert.assertTrue(projectNotesPage.elementExistsByText(
				"Are you sure you’d like to discard this information? This action can’t be undone.", language));
		Assert.assertTrue(projectNotesPage.elementExistsByContainTextAndSpaceIOS("No,", "Go Back"));// ("No, Go Back",
																									// language));
		Assert.assertTrue(projectNotesPage.elementExistsByContainTextAndSpaceIOS("Yes,", "Discard"));
		projectNotesPage.elementClickByContainTextAndSpaceIOS("Yes,", language);// ("Yes, Discard", language);
		homePage.clickElementByText("Project Notes", language);
		getCommon().sleepFor(2);
		projectNotesPage.enterTextToElementByContainsTextForIOS("Enter any additional", "note123");
		projectNotesPage.clickElementByText("DONE", language);// ("Enter any additional", "note");
		getCommon().sleepFor(2);
		Assert.assertTrue(projectNotesPage.elementExistsByText("note123", language));
		bidSummaryPage.scrollToElementByText("Project Notes", language);
		projectNotesPage.clickElementByText("Project Notes", language);
		projectNotesPage.clickElementByText("Discard Notes", language);
		projectNotesPage.elementClickByContainTextAndSpaceIOS("Yes,", language);// projectNotesPage.clickElementByText("Yes,
																				// Discard", language);
		Assert.assertFalse(projectNotesPage.elementExistsByText("note123", language));
	}
}