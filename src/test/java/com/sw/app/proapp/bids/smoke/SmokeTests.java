package com.sw.app.proapp.bids.smoke;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.AddLogoPage;
import com.sw.proapp.bids.pages.BidExpirationPage;
import com.sw.proapp.bids.pages.BidNamePage;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaImagePage;
import com.sw.proapp.bids.pages.NewAreaNamePage;
import com.sw.proapp.bids.pages.NewAreaNotesPage;
import com.sw.proapp.bids.pages.NewAreaPricePage;
import com.sw.proapp.bids.pages.NewAreaProductsSearchPage;
import com.sw.proapp.bids.pages.NewAreaTasksPage;
import com.sw.proapp.bids.pages.NewAreaTypePage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.bids.pages.ProjectDurationPage;
import com.sw.proapp.bids.pages.ProjectInfoPage;
import com.sw.proapp.bids.pages.ProjectNotesPage;
import com.sw.proapp.bids.pages.ProjectPricePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class SmokeTests extends MobileCoreBaseTest {
	private String language = "";
	private String bid;
	private boolean firstRun = true;
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private BidNamePage bidNamePage = null;
	private ProjectInfoPage projectInfoPage = null;
	private ProjectNotesPage projectNotesPage = null;
	private NewAreaTypePage newAreaTypePage = null;
	private NewAreaNamePage newAreaNamePage = null;
	private NewAreaImagePage newAreaImagePage = null;
	private NewAreaTasksPage newAreaTasksPage = null;
	private NewAreaProductsSearchPage newAreaProductsSearchPage = null;
	private NewAreaNotesPage newAreaNotesPage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private BidExpirationPage bidExpirationPage = null;
	private ProjectDurationPage projectDurationPage = null;
	private ProjectPricePage projectPricePage = null;
	private Login login = null;
	private AddLogoPage addLogoPage = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public SmokeTests() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidSummaryPageTest_class() {
		loginPage = new LoginPage(getCommon());
		projectPricePage = new ProjectPricePage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
		bidSummaryPage = new BidSummaryPage(getCommon());
		projectBidsHomePage = new ProjectBidsHomePage(getCommon());
		bidNamePage = new BidNamePage(getCommon());
		projectInfoPage = new ProjectInfoPage(getCommon());
		projectNotesPage = new ProjectNotesPage(getCommon());
		newAreaTypePage = new NewAreaTypePage(getCommon());
		newAreaNamePage = new NewAreaNamePage(getCommon());
		newAreaImagePage = new NewAreaImagePage(getCommon());
		newAreaTasksPage = new NewAreaTasksPage(getCommon());
		newAreaProductsSearchPage = new NewAreaProductsSearchPage(getCommon());
		newAreaNotesPage = new NewAreaNotesPage(getCommon());
		newAreaPricePage = new NewAreaPricePage(getCommon());
		bidExpirationPage = new BidExpirationPage(getCommon());
		projectDurationPage = new ProjectDurationPage(getCommon());
		addLogoPage = new AddLogoPage(getCommon());
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_BidSummaryPageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		// firstRun=false;
		if (firstRun) {
			firstRun = false;
			language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			createNewBidPage.clickElementByText("Area Subtotals°Subtotales de área", language);
			int randomNum = ThreadLocalRandom.current().nextInt(678, 456723546 + 1);
			// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
			bid = "qa" + randomNum;
			createNewBidPage.inputBidName("qa" + randomNum);
			createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
			bidSummaryPage.clickNewAreaCTA();
			newAreaTypePage.clickInteriorCta();
			newAreaNamePage.enterAreaName("area" + randomNum);
			newAreaNamePage.clickContinueCta();
			getCommon().sleepFor(2);
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(2);
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(2);
			newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(2);
			newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(2);
			newAreaPricePage.clickFlatRateTabString(language);
			newAreaPricePage.enterFlatRateValue("99999878");

			// newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo",
			// language);

			// newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "99999878");
			newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		} else {
			// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
			// ESPECIALES");
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		}
	}

	public void createNewArea(Boolean shouldAddProducts) {
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName("area" + UUID.randomUUID().getMostSignificantBits());
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		newAreaTasksPage.clickSaveCta();
		if (shouldAddProducts) {
			newAreaProductsSearchPage.clickSearchProductsCta();
			getCommon().typeAndVerifyIsTrue(newAreaProductsSearchPage.getSearchField(), "Paint",
					"Unable to type in products search field");
			newAreaProductsSearchPage.clickAddFirstProduct();
		}
		newAreaProductsSearchPage.clickContinueCta();
		newAreaNotesPage.clickContinueCta();
		if (!bidSummaryPage.isLoggedInBidSummaryPageDispalyed()) {
			newAreaPricePage.clickCostInputCta();
			newAreaPricePage.clickHoursUnit();
			newAreaPricePage.enterNumberOfUnits("10");
			newAreaPricePage.enterCost("1");
			newAreaPricePage.hideKeyboard();
			newAreaPricePage.swipeToDismissKeyboard();
			newAreaPricePage.clickSaveCta();
		}
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tapping bid name changes screens.")
	public void verifyBidNameTappedAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		bidSummaryPage.clickBidNameCard();
		Assert.assertTrue(bidNamePage.isLoogedInBidNamePageDispalyed(), "Bid Name page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify tapping project notes card changes screens.")
	public void verifyAA4ProjectNotesTappedAndroid() throws Exception {

		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		homePage.scrollToElementByText("Project Notes°Notas Del Proyecto", "SPECIAL OFFERS°OFERTAS ESPECIALES",
				language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickProjectNotesCard();
		getCommon().sleepFor(3);
		Assert.assertTrue(projectNotesPage.isLoogedInProjectNotesPageDispalyed(), "Project Notes page not displayed");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify tapping project info card changes screens.")
	public void verifyAA2AAProjectInfoTappedAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();

		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		bidSummaryPage.clickProjectInfoCard();
		getCommon().sleepFor(3);
		Assert.assertTrue(projectInfoPage.isLoogedInProjectInfoPageDispalyed(), "Project Info page not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify pro information section shows the screen.")
	public void verifyAA2ProInformationSectionAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		// bidSummaryPage.scrollDown3();
//CREATED ON
		homePage.scrollToElementByText("CREATED ON", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);

		// homePage.scrollToElementByText("PREVIEW°VISTA PREVIA", "SPECIAL
		// OFFERS°OFERTAS ESPECIALES", language);
		/*
		 * while (!getCommon().isDisplayed(bidSummaryPage.getSaveButton())) {
		 * bidSummaryPage.scrollDown3(); }
		 */
		Assert.assertTrue(bidSummaryPage.isBidNumberLabelDisplayed(), "Bid number label is not displayed");
		Assert.assertTrue(bidSummaryPage.isBidNumberValueDisplayed(), "Bid number value is not displayed");

		Assert.assertTrue(bidSummaryPage.isCreatedByLabelDisplayed(), "Created by label is not displayed");
		Assert.assertTrue(bidSummaryPage.isCreatedByValueDisplayed(), "Created by value is not displayed");

		Assert.assertTrue(bidSummaryPage.isCreatedOnLabelDisplayed(), "Created on label is not displayed");
		Assert.assertTrue(bidSummaryPage.elementExistsByText("CREATED ON", language));

		Assert.assertTrue(bidSummaryPage.isLastModifiedLabelDisplayed(), "Last modified label is not displayed");
		Assert.assertTrue(bidSummaryPage.isLastModifiedValueDisplayed(), "Last modified value is not displayed");

		Assert.assertTrue(bidSummaryPage.isPhoneNumberLabelDisplayed(), "Phone number label is not displayed");
		Assert.assertTrue(bidSummaryPage.isPhoneNumberValueDisplayed(), "Phone number value is not displayed");

		Assert.assertTrue(bidSummaryPage.isProEmailLabelDisplayed(), "Pro email label is not displayed");
		Assert.assertTrue(bidSummaryPage.isProEmailValueDisplayed(), "Pro email value is not displayed");

		Assert.assertTrue(bidSummaryPage.isSaveButtonDisplayed(), "Save button is not displayed");
		Assert.assertTrue(bidSummaryPage.isPreviewButtonDisplayed(), "Preview button is not displayed");
		// homePage.scrollToElementByText("GENERAL PAINT SERVICE", "SPECIAL
		// OFFERS°OFERTAS ESPECIALES", language);
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("GENERAL PAINT SERVICE",
		// language));

		// Assert.assertTrue(bidSummaryPage.isBusinessNameValueDisplayed(), "Business
		// name value is not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tapping new area CTA changes screens.")
	public void verifyAA2ANewAreaCTATappedAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("New Area", language);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaNamePage.elementExistsByText("Where are you working?", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area subtotals add correctly")
	public void verifyAA1_SubtotalsAndroid() throws Exception {

		getCommon().sleepFor(3);
		/*
		 * if (bidSummaryPage.getAreaTitles().size() == 0) { createNewArea(); }
		 */
		if (bidSummaryPage.elementExistsByText("$999,998.00", language)) {
			Assert.assertTrue(true);// , total);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("£999,998.00", language));// , total);
		}
		homePage.scrollToElementByText("PREVIEW°VISTA PREVIA", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("$999,998.00",
		// language));//, total);
		if (bidSummaryPage.elementExistsByText("$999,998.00", language)) {
			Assert.assertTrue(true);// , total);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("£999,998.00", language));// , total);
		}
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area subtotals add correctly")
	public void verifyAreaSummaryAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		/*
		 * if (bidSummaryPage.getAreaTitles().size() == 0) { createNewArea(); }
		 */

		Assert.assertTrue(bidSummaryPage.getAreaTitles().size() > 0, "Could not find area title");
		Assert.assertTrue(bidSummaryPage.isTasksLabelDisplayed(), "Tasks label is not displayed");
		Assert.assertTrue(bidSummaryPage.isProductsLabelDisplayed(), "Products label is not displayed");
		Assert.assertTrue(bidSummaryPage.isNotesLabelDisplayed(), "Notes label is not displayed");
		Assert.assertTrue(bidSummaryPage.getAreaSubtotals().size() > 0, "Could not find area subtotals");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify pricing method tag is displayed.")
	public void verifyPricingMethodTagAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);

		Assert.assertTrue(bidSummaryPage.isPricingMethodTagDisplayed(), "Pricing method tag is not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify project duration data shows.")
	public void verifyAA2_ProjectDurationDataAndroid() throws Exception {
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		homePage.scrollToElementByText("Ex: 10 Weeks", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);

		Assert.assertTrue(bidSummaryPage.elementExistsByText("ESTIMATED PROJECT DURATION", language));// (), "Duration
																										// card data is
																										// not
																										// displayed");
	}

	@Test(groups = { "mobile_regression",
			"android" }, description = "Verify selecting bid expiration displays selection.")
	public void verifyBidExpirationDropdownSelectedAndroid() throws Exception {
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		while (!getCommon().isDisplayed(bidSummaryPage.getBidExpirationCta())) {
			bidSummaryPage.scrollDown3();
		}

		bidSummaryPage.clickBidExpirationCTA();
		Assert.assertTrue(bidExpirationPage.elementExistsByText("Amount of Time", language));// , waitOrNot));//
		// Assert.assertTrue(bidExpirationPage.isEmptyBidExpirationDropdownDisplayed(),
		// "Empty bid expiration dropdown not displayed");

		bidExpirationPage.clickDiscardButton();
		// bidExpirationPage.clickElementByText("Discard Bid Expiration", language);
		bidExpirationPage.clickElementByText("YES, DISCARD", language);
		getCommon().sleepFor(2);
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary page not displayed as expected");

		while (!bidSummaryPage.isBidExpirationCardDisplayed()) {
			homePage.scrollDown3();
		}
		bidSummaryPage.clickBidExpirationCTA();
		bidExpirationPage.clickElementByText("Amount of Time", language);
		// bidExpirationPage.clickExpirationDropdown();

		Assert.assertTrue(bidExpirationPage.isBidExpiration7DaysDisplayed(),
				"No 7 day bid expiration dropdown not displayed");
		Assert.assertTrue(bidExpirationPage.isBidExpiration15DaysDisplayed(),
				"No 15 day bid expiration dropdown not displayed");
		Assert.assertTrue(bidExpirationPage.isBidExpiration30DaysDisplayed(),
				"No 30 day bid expiration dropdown not displayed");
		Assert.assertTrue(bidExpirationPage.isBidExpiration45DaysDisplayed(),
				"No 45 day bid expiration dropdown not displayed");
		Assert.assertTrue(bidExpirationPage.isBidNoExpirationDisplayed(), "No bid expiration not displayed");

		bidExpirationPage.click7DaysCta();
		Assert.assertTrue(bidExpirationPage.isSaveButtonDisplayed(), "Save button is not displayed.");
		bidExpirationPage.clickSaveButton();
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary page not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tapping project notes card changes screens.")
	public void verifyProjectNotesTappedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickProjectNotesCard();
		Assert.assertTrue(projectNotesPage.isLoogedInProjectNotesPageDispalyed(), "Project Notes page not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tapping project info card changes screens.")
	public void verifyAA1_ProjectInfoTappedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.clickProjectInfoCard();
		Assert.assertTrue(projectInfoPage.isLoogedInProjectInfoPageDispalyed(), "Project Info page not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tapping new area CTA changes screens.")
	public void verifyNewAreaCTATappedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		bidSummaryPage.clickNewAreaCTA();
		Assert.assertTrue(newAreaTypePage.isNewAreaTypePageDisplayed(), "Create New Area page not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tapping bid expiration CTA changes screens.")
	public void verifyBidExpirationCTATappedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		homePage.scrollDown3();
		bidSummaryPage.clickBidExpirationCTA();
		Assert.assertTrue(bidExpirationPage.isBidExpirationPageDisplayed(), "Bid expiration page not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify selecting bid expiration displays selection.")
	public void verifyBidExpirationDropdownSelectedIOS() throws Exception {
		// Create new bid
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
		} else {
			bidsPage.clickCreateNewBidButton();
		}
		createNewBidPage.inputBidName("NewBid" + LocalDateTime.now().toString());
		createNewBidPage.clickSaveBidButton();

		homePage.scrollDown3();
		bidSummaryPage.clickBidExpirationCTA();
		Assert.assertTrue(bidExpirationPage.isEmptyBidExpirationDropdownDisplayed(),
				"Empty bid expiration dropdown not displayed");

		bidExpirationPage.clickDiscardButton();
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary page not displayed as expected");

		bidSummaryPage.clickBidExpirationCTA();
		bidExpirationPage.clickExpirationDropdown();
		bidExpirationPage.clickDropdownDoneButton();
		Assert.assertTrue(bidExpirationPage.isPopulatedBidExpirationDropdownDisplayed(),
				"Populated bid expiration dropdown not displayed");

		bidExpirationPage.clickSaveButton();
		Assert.assertTrue(bidSummaryPage.isLoggedInBidSummaryPageDispalyed(),
				"Bid Summary page not displayed as expected");
		Assert.assertFalse(bidSummaryPage.isBidExpirationPlaceholderDisplayed(),
				"Unexpectedly found bid expiration placeholder");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify the bid pricing method pill is displayed.")
	public void verifyBidPricingMethodPillDisplayedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		Assert.assertTrue(bidSummaryPage.isBidPricingMethodPillDisplayed(), "Bid pricing method pill not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify pro information section shows the screen.")
	public void verifyProInformationSectionIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		bidSummaryPage.scrollDown3();
		Assert.assertTrue(bidSummaryPage.isBidNumberValueDisplayed(), "Bid number value is not displayed");
		Assert.assertTrue(bidSummaryPage.isCreatedByValueDisplayed(), "Created by value is not displayed");
		Assert.assertTrue(bidSummaryPage.isCreatedOnValueDisplayed(), "Created on value is not displayed");
		Assert.assertTrue(bidSummaryPage.isLastModifiedValueDisplayed(), "Last modified value is not displayed");
		Assert.assertTrue(bidSummaryPage.isPhoneNumberValueDisplayed(), "Phone number value is not displayed");
		Assert.assertTrue(bidSummaryPage.isProEmailValueDisplayed(), "Pro email value is not displayed");
		Assert.assertTrue(bidSummaryPage.isBusinessNameValueDisplayed(), "Business name value is not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify project info summary")
	public void verifyProjectInfoSummaryIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		while (!bidSummaryPage.isProjectDurationCardDisplayed()) {
			homePage.scrollDown3();
		}
		if (!bidSummaryPage.isProjectInfoNameLabelDisplayed()) {
			bidSummaryPage.clickProjectInfoCard();
			projectInfoPage.enterProjectInfo();
		}

		Assert.assertTrue(bidSummaryPage.isProjectInfoNameLabelDisplayed(), "Project Info name label not displayed");
		Assert.assertTrue(bidSummaryPage.isProjectInfoEmailLabelDisplayed(), "Project Info email label not displayed");
		Assert.assertTrue(bidSummaryPage.isProjectInfoPhoneLabelDisplayed(), "Project Info phone label not displayed");
		Assert.assertTrue(bidSummaryPage.isProjectInfoAddressLabelDisplayed(),
				"Project Info address label not displayed");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify project duration is displayed.")
	public void verifyProjectDurationDisplayedIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		if (!bidSummaryPage.isProjectDurationCardDisplayed()) {
			homePage.scrollDown3();
		}
		if (bidSummaryPage.isProjectDurationPlaceholderDisplayed()) {
			bidSummaryPage.clickProjectDurationCard();
			projectDurationPage.inputDurationNumber("3");
			projectDurationPage.selectDurationAmount();
			projectDurationPage.clickSave();
		}

		Assert.assertFalse(bidSummaryPage.isProjectDurationPlaceholderDisplayed(),
				"Project Duration value not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify project duration is displayed.")
	public void verifyProjectDurationDisplayedAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		if (!bidSummaryPage.isProjectDurationCardDisplayed()) {
			homePage.scrollDown3();
		}
		if (bidSummaryPage.isProjectDurationPlaceholderDisplayed()) {
			bidSummaryPage.clickProjectDurationCard();
			projectDurationPage.inputDurationNumber("3");
			projectDurationPage.selectDurationAmount();
			Assert.assertTrue(projectDurationPage.isSaveButtonDisplayed(), "Save button not displayed");
			projectDurationPage.clickSave();
		}

		Assert.assertFalse(bidSummaryPage.isProjectDurationPlaceholderDisplayed(),
				"Project Duration value not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area summary")
	public void verifyAreaSummaryIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		if (bidSummaryPage.getAreaTitles().size() == 0) {
			createNewArea(false);
		}

		Assert.assertTrue(bidSummaryPage.getAreaTitles().size() > 0, "Could not find area title");
		Assert.assertTrue(bidSummaryPage.isTasksLabelDisplayed(), "Tasks label is not displayed");
		Assert.assertTrue(bidSummaryPage.isProductsLabelDisplayed(), "Products label is not displayed");
		Assert.assertTrue(bidSummaryPage.isNotesLabelDisplayed(), "Notes label is not displayed");
		Assert.assertTrue(bidSummaryPage.getAreaSubtotals().size() > 0, "Could not find area subtotals");
	}

	/**
	 * None of the price labels are being found by the framework despite being
	 * displayed. The app will need to be refactored to include an id for these
	 * values so that they can be tested properly.
	 */
	/*
	 * @Test(groups = {"mobile_regression", "ios"}, description =
	 * "Verify area subtotals add correctly") public void verifySubtotalsIOS()
	 * throws Exception { // create a new bid if
	 * (bidsPage.isNoResultsFoundDisplayed()) {
	 * bidsPage.clickCreateNewBidButtonLarge(); createNewBidPage.inputBidName("Bid"
	 * + UUID.randomUUID().getMostSignificantBits());
	 * createNewBidPage.clickSaveBidButton(); } else {
	 * bidsPage.clickCreateNewBidButton(); createNewBidPage.inputBidName("Bid" +
	 * UUID.randomUUID().getMostSignificantBits());
	 * createNewBidPage.clickSaveBidButton(); }
	 * 
	 * // create two new areas createNewArea(); createNewArea(); double total = 0.0;
	 * while (!bidSummaryPage.totalCostIsVisible()) { List<WebElement> elements =
	 * bidSummaryPage.getAreaSubtotalValues(); for (WebElement element : elements) {
	 * double subtotal = Double.parseDouble(element.getText().replace("$", ""));
	 * total += subtotal; } bidSummaryPage.scrollDown3(); } double projectSubtotal =
	 * Double.parseDouble(bidSummaryPage.getProjectSubtotalValue().getText().replace
	 * ("$", "")); double actualTotal =
	 * Double.parseDouble(bidSummaryPage.getTotalCost().getText().replace("$", ""));
	 * double discountValue =
	 * Double.parseDouble(bidSummaryPage.getDiscountValue().getText().replace("-$",
	 * "")); double taxValue =
	 * Double.parseDouble(bidSummaryPage.getTaxValue().getText().replace("+$", ""));
	 * double adjustedTotal = total - discountValue + taxValue;
	 * Assert.assertEquals(projectSubtotal, total); Assert.assertEquals(actualTotal,
	 * adjustedTotal); }
	 */
	/**
	 * This test will currently fail due to a bug with the app that allows 26
	 * characters instead of the required 25
	 */
//    @Test(groups = {"mobile_regression", "ios"}, description = "Verify area name field has a max of 25 characters")
//    public void verifyAreaNameFieldIOS() throws Exception {
//        if (bidsPage.isNoResultsFoundDisplayed()) {
//            bidsPage.clickCreateNewBidButtonLarge();
//            createNewBidPage.inputBidName("bidName");
//            createNewBidPage.clickSaveBidButton();
//        } else {
//            bidsPage.clickBidCardHeader();
//        }
//
//        while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
//            bidSummaryPage.scrollDown3();
//        }
//
//        bidSummaryPage.clickNewAreaCTA();
//        newAreaTypePage.clickInteriorCta();
//
//        String char27 = "abcdefghijklmnopqrstuvwxyzz";
//        newAreaNamePage.enterAreaName(char27);
//        Assert.assertEquals(newAreaNamePage.getAreaNameField().getText().length(), 26,
//                "Area name field is not correct length");
//        Assert.assertTrue(newAreaNamePage.getContinueCta().isEnabled());
//        newAreaNamePage.clickDiscardCta();
//    }

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tapping add logo changes screens")
	public void verifyAddLogoTappedAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		bidSummaryPage.clickAddLogoCta();
		Assert.assertTrue(addLogoPage.isLoggedInAddLogoPageDispalyed(), "Bid Name page not displayed");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area summary is correct")
	public void zverifyDeleteArea() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}
		getCommon().sleepFor(3);
		List<WebElement> areaTitles = bidSummaryPage.getAreaTitles();
		List<WebElement> areaMenus = bidSummaryPage.getAreaMenus();
		getCommon().sleepFor(3);
		/*
		 * if (areaTitles.size() == 0) { createNewArea(); }
		 */
		int originalNumAreas = areaTitles.size();
		getCommon().click(areaMenus.get(0), "Could not click area menu");
		bidSummaryPage.clickDeleteCta();
		bidSummaryPage.clickConfirmDeleteCta();
		getCommon().sleepFor(3);

		Assert.assertTrue(bidSummaryPage.getAreaTitles().size() < originalNumAreas,
				"The number of areas did not change");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify empty product card")
	public void verifyEmptyProductTileIOS() throws Exception {
		String language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
		} else {
			bidsPage.clickCreateNewBidButton();
		}

		createNewBidPage.clickProjectTotal();
		createNewBidPage.inputBidName("bidName" + Math.random());
		createNewBidPage.clickSaveBidButton();
		projectPricePage.clickDone(language);

		if (bidSummaryPage.getAreaTitles().size() == 0) {
			createNewArea(false);
		}

		if (!bidSummaryPage.isProductsCardDisplayed()) {
			homePage.scrollDown3();
		}

		Assert.assertTrue(bidSummaryPage.isProductsCardDisplayed(), "Products Card not displayed as expected");
		Assert.assertTrue(bidSummaryPage.isProductsCardTitleDisplayed(),
				"Products Card Title label not displayed as expected");
		Assert.assertTrue(bidSummaryPage.isProductsCardDetailDisplayed(),
				"Products Card detail label not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify expanded product card")
	public void verifyExpandedProductTileIOS() throws Exception {
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}

		if (!bidSummaryPage.isNewAreaCTADisplayed()) {
			homePage.scrollDown3();
		}

		createNewArea(true);

		if (!bidSummaryPage.isProductsCardDisplayed()) {
			homePage.scrollDown3();
		}

		bidSummaryPage.clickViewAllProducts();

		Assert.assertTrue(bidSummaryPage.isProductNameLabelDisplayed(), "Product Name Label not displayed as expected");
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify all field values are displayed")
	public void verifyAccountFieldValuesAndoid() throws Exception {
		getCommon().sleepFor(4);
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.scrollToElementByText("Sherwin-Williams Products", language);
		homePage.scrollDown3();
		boolean createdBy = bidSummaryPage.validateAccountInformationFieldIsDisplayed("CREATED BY");
		Assert.assertTrue(createdBy);
		boolean proEmail = bidSummaryPage.validateAccountInformationFieldIsDisplayed("PRO EMAIL");
		Assert.assertTrue(proEmail);
		boolean proPhoneNumber = bidSummaryPage.validateAccountInformationFieldIsDisplayed("PRO PHONE NUMBER");
		Assert.assertTrue(proPhoneNumber);
		boolean createdOn = bidSummaryPage.validateAccountInformationFieldIsDisplayed("CREATED ON");
		Assert.assertTrue(createdOn);
		boolean lastModified = bidSummaryPage.validateAccountInformationFieldIsDisplayed("LAST MODIFIED");
		Assert.assertTrue(lastModified);
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify all field values are displayed")
	public void verifyAccountFieldValuesIOS() throws Exception {
		getCommon().sleepFor(4);
		if (bidsPage.isNoResultsFoundDisplayed()) {
			bidsPage.clickCreateNewBidButtonLarge();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
		} else {
			bidsPage.clickBidCardHeader();
		}
		bidSummaryPage.scrollToElementByText("Sherwin-Williams Products", language);
		homePage.scrollDown3();
		boolean createdBy = bidSummaryPage.validateAccountInformationFieldIsDisplayed("CREATED BY");
		Assert.assertTrue(createdBy);
		boolean proEmail = bidSummaryPage.validateAccountInformationFieldIsDisplayed("PRO EMAIL");
		Assert.assertTrue(proEmail);
		boolean proPhoneNumber = bidSummaryPage.validateAccountInformationFieldIsDisplayed("PRO PHONE NUMBER");
		Assert.assertTrue(proPhoneNumber);
		boolean createdOn = bidSummaryPage.validateAccountInformationFieldIsDisplayed("CREATED ON");
		Assert.assertTrue(createdOn);
		boolean lastModified = bidSummaryPage.validateAccountInformationFieldIsDisplayed("LAST MODIFIED");
		Assert.assertTrue(lastModified);
	}
}