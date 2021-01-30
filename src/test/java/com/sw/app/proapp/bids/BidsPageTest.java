package com.sw.app.proapp.bids;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidsPageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	String language = "";
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private LoginPage loginPage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public BidsPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidsPageTest_class() {
		bidSummaryPage = new BidSummaryPage(getCommon());
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
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		getCommon().sleepFor(3);
		projectBidsHomePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tapping bid goes to bid summary.")
	public void verifyBidTappedAndroid() throws Exception {

		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(), "Bid Summary page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify tapping create new bid button goes to new screen.")
	public void verifyCreateNewBidButtonAndroid() throws Exception {
		bidsPage.clickCreateNewBidButton();
		getCommon().sleepFor(2);
		Assert.assertTrue(createNewBidPage.elementExistsByText("PRICING METHOD", language));// , "Create New Bid page
																							// not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify status filter can be changed.")
	public void verifyStatusFiltersAndroid() throws Exception {
		bidsPage.clickStatusDropdown();
		Assert.assertTrue(bidsPage.isViewAllStatusVisable(), "View All Status is not displayed");
		Assert.assertTrue(bidsPage.isDraftsStatusVisable(), "Draft Status is not displayed");
		Assert.assertTrue(bidsPage.isPendingStatusVisable(), "Pending Status is not displayed");
		Assert.assertTrue(bidsPage.isAcceptedStatusVisable(), "Accepted Status is not displayed");
		Assert.assertTrue(bidsPage.isRejectedStatusVisable(), "Rejected Status is not displayed");
		Assert.assertTrue(bidsPage.isClosedStatusVisable(), "Closed Status is not displayed");
		bidsPage.clickDraftsStatus();
		Assert.assertEquals(bidsPage.getStatusDropDown().getText(), "Drafts");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tapping bid goes to bid summary.")
	public void verify3BidTappedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(), "Bid Summary page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"ios" }, description = "Verify tapping create new bid button goes to new screen.")
	public void verify2CreateNewBidButtonIOS() throws Exception {
		bidsPage.clickCreateNewBidButton();
		Assert.assertTrue(createNewBidPage.elementExistsByContainTextValueAndSpaceIOS("How would you", "like to bid"));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tapping bid delete button deletes bid")
	public void verifyBidDeleteAndroid() throws Exception {
		List<WebElement> bidNames = bidsPage.getBidHeaderTitles();
		String titleToBeDeleted = bidNames.get(0).getText();
		getCommon().sleepFor(3);
		List<WebElement> bidHeaderMenus = bidsPage.getBidHeaderMenuCta();
		getCommon().sleepFor(3);

		if (bidNames.size() == 0) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
		}

		int originalNumBids = bidNames.size();
		getCommon().click(bidHeaderMenus.get(0), "Could not click bid header menu");
		bidsPage.clickDeleteCta();
		bidsPage.clickConfirmDeleteCta();
		getCommon().sleepFor(3);
		Assert.assertFalse(bidsPage.elementExistsByText(titleToBeDeleted, language));// getBidHeaderTitles().size() <
																						// originalNumBids, "The number
																						// of bids did not change");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify tapping bid duplicate button duplicates bid")
	public void verifyAABidDuplicateAndroid() throws Exception {
		List<WebElement> bidNames = bidsPage.getBidHeaderTitles();
		getCommon().sleepFor(3);
		List<WebElement> bidHeaderMenus = bidsPage.getBidHeaderMenuCta();
		getCommon().sleepFor(3);

		if (bidNames.size() == 0) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
		}

		String originalBidName = bidNames.get(0).getText();
		getCommon().click(bidHeaderMenus.get(0), "Could not click bid header menu");
		bidsPage.clickDuplicateCta();
		getCommon().sleepFor(3);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 456723546 + 1);
		if (originalBidName.length() > 15) {
			originalBidName = randomNum + "";
		}
		createNewBidPage.inputBidName("Copy" + originalBidName);
		getCommon().sleepFor(3);
		// bidsPage.enterTextToElementByText("Bid Name", "Copy"+originalBidName);
		bidsPage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		Assert.assertEquals(bidsPage.getBidHeaderTitles().get(0).getText(), "Copy" + originalBidName,
				"Bid names do not match");
	}
}