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
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaImagePage;
import com.sw.proapp.bids.pages.NewAreaNamePage;
import com.sw.proapp.bids.pages.NewAreaTasksPage;
import com.sw.proapp.bids.pages.NewAreaTypePage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class NewAreaTasksPageTest extends MobileCoreBaseTest {
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
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public NewAreaTasksPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaTasksPageTest_class() {
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
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_NewAreaTasksPageTest() {
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify internal task screen is correct")
	public void aaverifyNewAreaInternalTasksScreenAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		bidSummaryPage.scrollToElementByText("New Area", language);
		/*
		 * while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
		 * bidSummaryPage.scrollDown3(); }
		 */

		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);

		// Assert.assertTrue(newAreaTasksPage.elementExistsByText("Body", language));//
		// ().isDisplayed(), "Body label is
		// not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Ceiling", language));// , "Trim label is not
																						// displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Trim", language));// "Shutters label is not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Windows", language));// , "Windows label is not
																						// displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Doors", language));// "Doors label is not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Walls", language));// "Gutters label is not
																					// displayed");
		Assert.assertTrue(newAreaTasksPage.elementEnabledByText("SAVE & CONTINUE", language));// ().isEnabled(), "Save
																								// CTA is not enabled");
		Assert.assertTrue(newAreaTasksPage.elementEnabledByText("Save & Go to Summary", language));// , "Discard CTA is
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify external task screen is correct")
	public void verifyNewAreaExternalTasksScreenAndroid() throws Exception {
		WebElement bidCard = bidsPage.getBidCard();
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}

		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickExteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);

		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Body", language));// ().isDisplayed(), "Body label is
																					// not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Shutter", language));// , "Trim label is not
																						// displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Trim", language));// "Shutters label is not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Windows", language));// , "Windows label is not
																						// displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Doors", language));// "Doors label is not displayed");
		Assert.assertTrue(newAreaTasksPage.elementExistsByText("Gutters", language));// "Gutters label is not
																						// displayed");
		Assert.assertTrue(newAreaTasksPage.elementEnabledByText("SAVE & CONTINUE", language));// ().isEnabled(), "Save
																								// CTA is not enabled");
		Assert.assertTrue(newAreaTasksPage.elementEnabledByText("Save & Go to Summary", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify interior task screen is correct")
	public void verifyNewAreaInteriorTasksScreenIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		// Assert.assertTrue(newAreaTasksPage.getBodyLabel().isDisplayed(), "Body label
		// is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalTrimLabel().isDisplayed(), "Trim label is not displayed");
		// Assert.assertTrue(newAreaTasksPage.getShuttersLabel().isDisplayed(),
		// "Shutters label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalWindowsLabel().isDisplayed(), "Windows label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalDoorsLabel().isDisplayed(), "Doors label is not displayed");
		// Assert.assertTrue(newAreaTasksPage.getGuttersLabel().isDisplayed(), "Gutters
		// label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getSaveCta().isEnabled(), "Save CTA is not enabled");
		Assert.assertTrue(newAreaTasksPage.elementExistsByContainTextAndSpaceIOS("Go to Summary", "Save"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify exterior task screen is correct")
	public void verifyNewAreaExteriorTasksScreenIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickExteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		Assert.assertTrue(newAreaTasksPage.getBodyLabel().isDisplayed(), "Body label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalTrimLabel().isDisplayed(), "Trim label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getShuttersLabel().isDisplayed(), "Shutters label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalWindowsLabel().isDisplayed(), "Windows label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getExternalDoorsLabel().isDisplayed(), "Doors label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getGuttersLabel().isDisplayed(), "Gutters label is not displayed");
		Assert.assertTrue(newAreaTasksPage.getSaveCta().isEnabled(), "Save CTA is not enabled");
		Assert.assertTrue(newAreaTasksPage.elementExistsByContainTextAndSpaceIOS("Go to Summary", "Save"));
	}
}