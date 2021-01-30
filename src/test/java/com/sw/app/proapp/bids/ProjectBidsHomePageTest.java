package com.sw.app.proapp.bids;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class ProjectBidsHomePageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	String language = "";
	private CreateNewBidPage createNewBidPage = null;
	private Login login = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public ProjectBidsHomePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidsHomePageTest_class() {
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
		projectBidsHomePage = new ProjectBidsHomePage(getCommon());
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_BidsHomePageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (loginPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify the Create New Bid button works.")
	public void verify2CreateNewBidButtonAndroid() throws Exception {
		homePage.clickProAppButton();
		// homePage.scrollDown3();
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);

		Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(), "Create new bid button is not correct");
		projectBidsHomePage.clickCreateNewBidButton();
		Assert.assertTrue(createNewBidPage.elementExistsByText("PRICING METHOD", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify the View All Bids button works.")
	public void verify3ViewAllBidsButtonAndroid() throws Exception {
		homePage.clickProAppButton();
		// homePage.scrollDown3();
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);

		Assert.assertTrue(projectBidsHomePage.getViewAllBidsButton().isDisplayed(),
				"View all bids button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isViewAllBidsButtonCorrect(), "View all bids button is not correct");
		projectBidsHomePage.clickViewAllBidsButton();
		Assert.assertTrue(bidsPage.isLoogedInBidsPageDispalyed(), "Project Bids page not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify the HomePage created in SW PRO APP")
	public void verify1HomePageAndroid() throws Exception {
		homePage.clickProAppButton();

		// Check Frequently Purchased
		try {
			homePage.scrollToElementByText("PROJECT BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);

			Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
					"Frequently purchased is not displayed");
		} catch (Exception e) {
			// homePage.scrollDown3();
			homePage.scrollToElementByText("PROJECT BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);

			Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
					"Frequently purchased is not displayed");
		}

		// Check Project Bids Section
		try {
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);

			Assert.assertTrue(projectBidsHomePage.getProjectBidsCardHeader().isDisplayed(),
					"Project Bids card is not displayed");
			Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
					"Create new bid button is not displayed");
			Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(),
					"Create new bid button is not correct");
			Assert.assertTrue(projectBidsHomePage.getViewAllBidsButton().isDisplayed(),
					"View all bids button is not displayed");
			Assert.assertTrue(projectBidsHomePage.isViewAllBidsButtonCorrect(), "View all bids button is not correct");
		} catch (Exception e) {
			homePage.scrollDown3();
			Assert.assertTrue(projectBidsHomePage.getProjectBidsCardHeader().isDisplayed(),
					"Project Bids card is not displayed");
			Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
					"Create new bid button is not displayed");
			Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(),
					"Create new bid button is not correct");
			Assert.assertTrue(projectBidsHomePage.getViewAllBidsButton().isDisplayed(),
					"View all bids button is not displayed");
			Assert.assertTrue(projectBidsHomePage.isViewAllBidsButtonCorrect(), "View all bids button is not correct");
		}

		// Check Order History
		try {
			Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
		} catch (Exception e) {
			homePage.scrollDown3();
			Assert.assertTrue(homePage.getOrderHistory().isDisplayed(), "Order History  is not displayed");
		}
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify the Create New Bid button works.")
	public void verifyCreateNewBidButtonIOS() throws Exception {
		homePage.clickProAppButton();
		homePage.scrollDown3();
		Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(), "Create new bid button is not correct");
		projectBidsHomePage.clickCreateNewBidButton();
		Assert.assertTrue(
				createNewBidPage.elementExistsByContainTextAndSpaceIOS("you like to bid this project", "How would"));// isLoogedInCreateBidPageDispalyed(),
																														// "Create
																														// New
																														// Bid
																														// page
																														// not
																														// displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify the View All Bids button works.")
	public void aaaverifyViewAllBidsButtonIOS() throws Exception {
		homePage.clickProAppButton();
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("VIEW ALL BIDS", language);// clickViewAllBidsButton();
		Assert.assertTrue(bidsPage.elementExistsByText("PROJECT BIDS", language));
		}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify the HomePage created in SW PRO APP")
	public void verifyHomePageIOS() throws Exception {
		homePage.clickProAppButton();
		try {
			Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
					"Frequently purchased is not displayed");
		} catch (Exception e) {
			homePage.scrollDown3();
			Assert.assertTrue(homePage.getFrequentlyPurchasedSection().isDisplayed(),
					"Frequently purchased is not displayed");
		}
		homePage.scrollToElementByText("PROJECT BIDS", language);
		Assert.assertTrue(projectBidsHomePage.elementExistsByContainTextAndSpaceIOS("PROJECT BIDS", "PROJECT BIDS"));// getProjectBidsCardHeader().isDisplayed(),
		Assert.assertTrue(
				projectBidsHomePage.elementExistsByContainTextAndSpaceIOS("CREATE NEW BID", "CREATE NEW BID"));// getCreateNewBidButton().isDisplayed(),
		Assert.assertTrue(projectBidsHomePage.elementExistsByContainTextAndSpaceIOS("VIEW ALL BIDS", "VIEW ALL BIDS"));// getViewAllBidsButton().isDisplayed(),
		Assert.assertTrue(homePage.elementExistsByContainTextAndSpaceIOS("ORDER HISTORY", "ORDER HISTORY"));																									// displayed");
	}
}