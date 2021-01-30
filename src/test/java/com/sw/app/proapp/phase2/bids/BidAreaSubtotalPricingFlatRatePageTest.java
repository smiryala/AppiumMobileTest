package com.sw.app.proapp.phase2.bids;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.core.testsuites.MobileDriverFactory;
import com.sw.proapp.bids.pages.*;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BidAreaSubtotalPricingFlatRatePageTest extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private String bidToUuse = "";
	private String area = null;
	private boolean firstRun = true;
	private HomePage homePage = null;
	private EditAreaPage editAreaPage = null;
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
	private NewAreaProductsSearchPage newAreaProductsSearchPage = null;
	private NewAreaNotesPage newAreaNotesPage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public BidAreaSubtotalPricingFlatRatePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		pricingMethodPage = new PricingMethodPage(getCommon());
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
		newAreaProductsSearchPage = new NewAreaProductsSearchPage(getCommon());
		newAreaNotesPage = new NewAreaNotesPage(getCommon());
		newAreaPricePage = new NewAreaPricePage(getCommon());
		editAreaPage = new EditAreaPage(getCommon());
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
		// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
		// ESPECIALES");
		// firstRun=false;
		if (firstRun) {
			firstRun = false;
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
			int randomNum = ThreadLocalRandom.current().nextInt(678, 456768754 + 1);
			// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
			bidToUuse = "qa" + randomNum;
			createNewBidPage.inputBidName("qa" + randomNum);
			createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
			bidSummaryPage.clickNewAreaCTA();
			newAreaTypePage.clickInteriorCta();
			newAreaNamePage.enterAreaName("area" + randomNum);
			getCommon().sleepFor(3);
			newAreaNamePage.clickContinueCta();
			getCommon().sleepFor(3);
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaPricePage.clickFlatRateTabString(language);
			getCommon().sleepFor(3);
			newAreaPricePage.enterFlatRateValue("99999878");

			// newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo",
			// language);

			// newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "99999878");
			newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
			newAreaPricePage.clickElementByElementName("Done°GUARDAR", language);
		} else {
			// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
			// ESPECIALES");
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
			homePage.clickElementByText(bidToUuse, language);
		}
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
		for (int i = 0; i < 1; i++) {
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify flat rate tab exists")
	public void verify2NewAreaPriceScreenFlatRateTabExistsAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		/*
		 * newAreaNamePage.clickContinueCta(); newAreaImagePage.clickSaveCta();
		 * newAreaImagePage.clickElementByText("CONTINUE°CONTINUAR", language);
		 * newAreaProductsSearchPage.clickContinueCta();
		 * newAreaNotesPage.clickContinueCta();
		 */
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(2);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Flat Rate°Costo Fijo", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify flat rate tab exists")
	public void verify1NewAreaPriceScreenFlatRateTabExistsIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(newAreaPricePage.getAttributyeByText("flatRateLabel", "label", language).equals("Flat Rate"));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify1NewAreaPriceScreenFlatRateFieldDoesNotAcceptLessThanCeroAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		/*
		 * newAreaNamePage.clickContinueCta(); newAreaImagePage.clickSaveCta();
		 * newAreaImagePage.clickElementByText("CONTINUE°CONTINUAR", language);
		 * newAreaProductsSearchPage.clickContinueCta();
		 * newAreaNotesPage.clickContinueCta();
		 */
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
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		// newAreaPricePage.enterTextToElementByText("Price", "2");
		newAreaPricePage.enterFlatRateValue("-2");
		// will only type positive numbers
		Assert.assertTrue(newAreaPricePage.elementExistsByText("2", language));
		// negative number does not exists
		Assert.assertFalse(newAreaPricePage.elementExistsByText("-2", language, false));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify2NewAreaPriceScreenFlatRateFieldDoesNotAcceptLessThanCeroIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("-2");
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$2.00", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void averify3NewAreaPriceScreenFlatRateFieldDoesNotAcceptMoreThanMaxAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		/*
		 * newAreaNamePage.clickContinueCta(); newAreaImagePage.clickSaveCta();
		 * newAreaImagePage.clickElementByText("CONTINUE°CONTINUAR", language);
		 * newAreaProductsSearchPage.clickContinueCta();
		 * newAreaNotesPage.clickContinueCta();
		 */
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
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		// newAreaPricePage.enterTextToElementByText("$0.00", "999999.01");
		// newAreaPricePage.enterTextToElementByText("Price", "999999.999");
		newAreaPricePage.enterFlatRateValue("999999.999");
		// will accept max value
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$999,999.99", language));
		// more than max is not accepted on field
		// Assert.assertFalse(newAreaPricePage.elementExistsByText("999999.0", language,
		// false));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify3NewAreaPriceScreenFlatRateFieldDoesNotAcceptMoreThanMaxIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("99999901");
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$999,999.01", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify4NewAreaPriceScreenFlatRateFieldPlaceholderFormatDollarAndCentAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		/*
		 * newAreaNamePage.clickContinueCta(); newAreaImagePage.clickSaveCta();
		 * newAreaImagePage.clickElementByText("CONTINUE°CONTINUAR", language);
		 * newAreaProductsSearchPage.clickContinueCta();
		 * newAreaNotesPage.clickContinueCta();
		 */
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
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Price", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify4NewAreaPriceScreenFlatRateFieldSavedSuccessfullyFormatAutoFormattingIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$0.00 (USD)", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void aaverify5NewAreaPriceScreenFlatRateFieldSavedSuccessfullyFormatAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		area = areaName;
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		/*
		 * newAreaNamePage.clickContinueCta(); newAreaImagePage.clickSaveCta();
		 * newAreaImagePage.clickElementByText("CONTINUE°CONTINUAR", language);
		 * newAreaProductsSearchPage.clickContinueCta();
		 * newAreaNotesPage.clickContinueCta();
		 */
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
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		// newAreaPricePage.enterTextToElementByText("$0.00", "999998.78");
		// newAreaPricePage.enterTextToElementByText("Price", "999998.78");
		newAreaPricePage.enterFlatRateValue("999998.78");
		Assert.assertTrue(newAreaPricePage.elementExistsByText("999,998.78", language));
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByText("DONE°LISTO", language);
		String summaryFlatRateValidation = "$" + myFormat.format(999998.78);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		if (newAreaPricePage.elementExistsByText("$999,998.78", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(newAreaPricePage.elementExistsByText("£999,998.78", language));

		}

	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void aaverify5NewAreaPriceScreen1FlatRateFieldSavedSuccessfullyFormatAutoFormattingIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		area = areaName;
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "99999878");
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		newAreaPricePage.clickElementByText("Done°GUARDAR", language);
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("999,998.78", "$"));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void aaaaverify6NewAreaPriceScreen2UpdateFlatRateFieldSavedSuccessfullyFormatAutoFormattingAndroid()
			throws Exception {
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		bidSummaryPage.clickElementByText("Area Subtotal", language);
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByText("$999,998.00", "8765");
		String updatedFlatRateValidation = "$" + myFormat.format(randomNum) + ".00";
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$8,765.00", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify6NewAreaPriceScreen2UpdateFlatRateFieldSavedSuccessfullyFormatAutoFormattingIOS()
			throws Exception {
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		newAreaPricePage.clickElementByElementName("Edit°Editar", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		editAreaPage.clickElementByText("Area Subtotal", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByContainsTextForIOS("$", "8678");
		// newAreaPricePage.clickElementByText("Done°Listo", language);
		newAreaPricePage.clickElementByText("Done°GUARDAR", language);
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("86.78", "$"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify7EditArea_AreaSubtotalIOS() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		newAreaPricePage.clickElementByElementName("Edit°Editar", language);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("Area Subtotal", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify7EditArea_AreaSubtotalAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("Area Subtotal", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify8EditArea_TitleAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("EDIT AREA°EDITAR ÁREA", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify8AreaPriceScreenEditArea_TitleIOS() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		newAreaPricePage.clickElementByElementName("Edit°Editar", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("EDIT AREA DETAILS°EDITAR DETALLES DEL ÁREA", language));
	}
}
