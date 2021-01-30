package com.sw.app.proapp.phase2.bids;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidDiscountPage;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidTaxPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaImagePage;
import com.sw.proapp.bids.pages.NewAreaNamePage;
import com.sw.proapp.bids.pages.NewAreaNotesPage;
import com.sw.proapp.bids.pages.NewAreaPricePage;
import com.sw.proapp.bids.pages.NewAreaProductsSearchPage;
import com.sw.proapp.bids.pages.NewAreaTasksPage;
import com.sw.proapp.bids.pages.NewAreaTypePage;
import com.sw.proapp.bids.pages.PricingMethodPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidSummaryEditPencilsDiscountTaxPageTest extends MobileCoreBaseTest {
	private boolean firstRun = true;
	private NewAreaTasksPage newAreaTasksPage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private NewAreaNamePage newAreaNamePage = null;
	private NewAreaImagePage newAreaImagePage = null;
	private NewAreaNotesPage newAreaNotesPage = null;
	private NewAreaProductsSearchPage newAreaProductsSearchPage = null;
	private HomePage homePage = null;
	private BidDiscountPage bidDiscountPage = null;
	private BidTaxPage bidTaxPage = null;
	private String language = "";
	private AccountPage accountPage = null;
	private BidsPage bidsPage = null;
	private BidSummaryPage bidSummaryPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private PricingMethodPage pricingMethodPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private NewAreaTypePage newAreaTypePage = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;
	private Login login = null;

	public BidSummaryEditPencilsDiscountTaxPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		newAreaTasksPage = new NewAreaTasksPage(getCommon());
		newAreaPricePage = new NewAreaPricePage(getCommon());
		newAreaNamePage = new NewAreaNamePage(getCommon());
		newAreaImagePage = new NewAreaImagePage(getCommon());
		newAreaNotesPage = new NewAreaNotesPage(getCommon());
		newAreaProductsSearchPage = new NewAreaProductsSearchPage(getCommon());
		newAreaTypePage = new NewAreaTypePage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
		pricingMethodPage = new PricingMethodPage(getCommon());
		loginPage = new LoginPage(getCommon());
		bidDiscountPage = new BidDiscountPage(getCommon());
		bidTaxPage = new BidTaxPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		bidsPage = new BidsPage(getCommon());
		bidSummaryPage = new BidSummaryPage(getCommon());
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
	public void before_NewAreaPricePageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (bidSummaryPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		firstRun = false;
		// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
		// ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.selectTaskPriceOption(language);
		getCommon().sleepFor(3);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		newAreaTypePage.clickInteriorCta();
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("areaname");
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(3);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickBlueEditPencilBYTaskName("Walls", language);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("6");
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		newAreaPricePage.clickElementByText("Done°Hecho", language);
		getCommon().sleepFor(3);
	}

	@AfterClass(alwaysRun = true)
	public void after_NewAreaPricePageTest() {
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
		for (int i = 0; i < 3; i++) {
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
		}
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify dollars discount functionality")
	public void verifyBidSummaryEditPencilsDiscountDollarsAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickDiscountUnitElement(language);
		bidDiscountPage.clickElementByText("Dollars°Dólares", language);
		bidDiscountPage.enterValueOnDiscountField("3");
		bidDiscountPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		if (bidSummaryPage.elementExistsByText("- $3.00", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("- £3.00", language));
		}

	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify dollars discount functionality")
	public void verifyBidSummaryEditPencilsDiscountDollarsIOS() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue", 1, language);
		bidDiscountPage.clickElementByText("Discount Type", language);
		bidDiscountPage.selectFromIosWheelerByText("Dollars°Dólares", language);
		bidDiscountPage.enterTextToElementByTextForIOS("NUMBER", "3");
		bidDiscountPage.clickElementByText("Done°GUARDAR", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		// Assert.assertTrue(bidSummaryPage.elementDisplayedByText("-", "- $ 3.00",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("3.00", "-"));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify discount percent functionality")
	public void verifyBidSummaryEditPencilsDiscountPercentageAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickDiscountUnitElement(language);
		bidDiscountPage.clickElementByText("Percent°Por ciento", language);
		bidDiscountPage.enterValueOnDiscountField("3");
		bidDiscountPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Discount (3%)", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify discount percent functionality")
	public void verifyBidSummaryEditPencilsDiscountPercentageIOS() throws Exception {
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		// bidSummaryPage.clickEditPencilBidsSummaryByText("Discount°Descuento",
		// language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue", 1, language);
		bidDiscountPage.clickElementByText("Discount Type", language);
		bidDiscountPage.selectFromIosWheelerByText("Percent°Por ciento", language);
		bidDiscountPage.enterTextToElementByTextForIOS("NUMBER", "3");
		bidDiscountPage.clickElementByText("Done°GUARDAR", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		// Assert.assertTrue(bidSummaryPage.elementDisplayedByText("-", "- $ 0.18",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("0.18", "-"));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify tax edit functionality")
	public void verifyBidSummaryEditPencilsTaxPercentageAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		bidTaxPage.enterValueOnTaxField("7");
		bidTaxPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Tax (7.0%)", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify tax edit functionality")
	public void a1verifyBidSummaryEditPencilsTaxPercentageIOS() throws Exception {
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		// bidSummaryPage.clickEditPencilBidsSummaryByText("Tax°Impuestos", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue", 2, language);
		bidDiscountPage.enterTextToElementByTextForIOS("PERCENT", "3000");
		bidDiscountPage.clickElementByText("Done°GUARDAR", language);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		// Assert.assertTrue(bidSummaryPage.elementDisplayedByText("+", "+ $ 0.18",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("0.18", "+"));
	}

}