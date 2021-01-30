package com.sw.app.proapp.bids.regression;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class EndToEndTests extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private String area = null;
	private boolean firstRun = true;
	private BidTaxPage bidTaxPage = null;
	private HomePage homePage = null;
	private BidDiscountPage bidDiscountPage = null;
	private ProjectPricePage projectPricePage = null;
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

	public EndToEndTests() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		bidTaxPage = new BidTaxPage(getCommon());
		bidDiscountPage = new BidDiscountPage(getCommon());
		projectPricePage = new ProjectPricePage(getCommon());
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

	@AfterClass(alwaysRun = true)
	public void after_NewAreaPricePageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (loginPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		for (int i = 0; i < 2; i++) {
			if (getCommon().getDriver() instanceof IOSDriver) {
				newAreaPricePage.clickLastElementByTextIOSANDROID("CREATED ON°CREADO:", "moreIcon", language, true);
				getCommon().sleepFor(3);
				newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
				getCommon().sleepFor(3);
				newAreaPricePage.elementClickByContainTextAndSpaceIOS(", Delete", "Yes");
				getCommon().sleepFor(3);
			}
			else {
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

	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify end to end scenario tasks")
	public void aa1verifyEndToEndScenarioProjectAndroid() throws Exception {
		//language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		projectBidsHomePage.scrollToElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		pricingMethodPage.clickElementByText("Project Total°Total del Proyecto", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 456798 + 1);
		// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("1225");
		projectPricePage.clickDone(language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("ADD LOGO", language));
		
		bidSummaryPage.clickElementByText("ADD LOGO", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("ADD IMAGE", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("Gallery", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickOnImageOnGoogleDriver();
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		
		Assert.assertFalse(bidSummaryPage.elementExistsByText("ADD LOGO", language));
		
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		newAreaTypePage.clickInteriorCta();
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("area" + randomNum);
		area = "area" + randomNum;
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("ADD IMAGE", language));
		newAreaImagePage.clickElementByText("ADD IMAGE", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByText("I CONFIRM CLIENT PERMISSION RECEIVED", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByText("Gallery", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickOnImageOnGoogleDriver();
		getCommon().sleepFor(3);
		
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search products", language);
		newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
		getCommon().sleepFor(5);
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Advanced Protective Products Rust Destroye");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Advanced Protective Products Rust Destroyer", language);
		getCommon().sleepFor(3);
		
		
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Advanced Protective Products Rust Destroyer", language));
		newAreaProductsSearchPage.clickDeleteButtonByIndex(2);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.clickElementByText(", DELETE", language);
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("Advanced Protective Products Rust Destroyer", language));
		
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
		newAreaNotesPage.enterNotes(note);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		//projectPricePage.clickUnitDropDown();
		newAreaProductsSearchPage.clickElementByText("DONE°LISTO", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.imageDisplayedInSummary());
		
		
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		bidSummaryPage.scrollToElementByText(note, language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		
		
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,225.00", language));
		
		newAreaPricePage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language, true);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Duplicate°Duplicar", language);
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("deletearea");
		getCommon().sleepFor(3);
		newAreaNamePage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		newAreaNamePage.scrollToElementByText("deletearea", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("deletearea", language));
		getCommon().sleepFor(3);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language, true);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.clickElementByText(", DELETE", language);
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("deletearea", language));
		
		
		
		
		
		bidSummaryPage.scrollToElementByText("Bid Expiration", language);
		newAreaPricePage.clickElementByElementName("Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));
		newAreaPricePage.clickElementByElementName("Amount of Time", language);
		newAreaPricePage.clickElementByElementName("30 days", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Estimated Project Duration", language);
		newAreaPricePage.enterTextToElementByText("0", "5");
		projectPricePage.clickElementByElementName("Unit", language);
		newAreaPricePage.clickElementByElementName("Weeks", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickDiscountUnitElement(language);
		bidDiscountPage.clickElementByText("Percent°Porcentaje", language);
		bidDiscountPage.enterValueOnDiscountField("10");
		newAreaPricePage.clickElementByElementName("DONE", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("- $122.50", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));
		bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		bidTaxPage.enterValueOnTaxField("18.5");
		bidTaxPage.clickElementByText("DONE°GUARDAR", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("+ $203.96", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,306.46", language));
		bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

		bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

		bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("30 Days", language));
		bidSummaryPage.scrollToElementByText("COMPOSE EMAIL DRAFT", language);
		newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("bid-preview.pdf", language));
		bidSummaryPage.clickBackButton();
		bidSummaryPage.closeAndLaunchApp();
	}

	

	@Test(groups = { "mobile_regression", "android" }, description = "Verify end to end scenario tasks")
	public void verifyEndToEndScenarioTasksAndroid() throws Exception {
		//language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.clickElementByText("Task Prices°Subtotales de área", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45676546 + 1);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("ADD LOGO", language));
		
		bidSummaryPage.clickElementByText("ADD LOGO", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("ADD IMAGE", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("Gallery", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickOnImageOnGoogleDriver();
		getCommon().sleepFor(3);
		bidSummaryPage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		
		Assert.assertFalse(bidSummaryPage.elementExistsByText("ADD LOGO", language));
		
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		newAreaTypePage.clickInteriorCta();
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("area" + randomNum);
		area = "area" + randomNum;
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("ADD IMAGE", language));
		newAreaImagePage.clickElementByText("ADD IMAGE", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByText("I CONFIRM CLIENT PERMISSION RECEIVED", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByText("Gallery", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickOnImageOnGoogleDriver();
		getCommon().sleepFor(3);
		
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search products", language);
		newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
		getCommon().sleepFor(5);
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Advanced Protective Products Rust Destroye");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Advanced Protective Products Rust Destroyer", language);
		getCommon().sleepFor(3);
		
		
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Advanced Protective Products Rust Destroyer", language));
		newAreaProductsSearchPage.clickDeleteButtonByIndex(2);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.clickElementByText(", DELETE", language);
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("Advanced Protective Products Rust Destroyer", language));
		
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
		newAreaNotesPage.enterNotes(note);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		//projectPricePage.clickUnitDropDown();
		newAreaImagePage.clickFollowingSiblingbyXpath("Walls", language);
		//newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		//newAreaPricePage.enterTextToElementByExactText("Price", "3");
		//newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		// will accept max value
		//createNewBidPage.clickElementByText("Square Feet°Horas", language);
		//createNewBidPage.enterTextToElementByExactText("Unit", "Square Feet");
		projectPricePage.clickUnitDropDown();
		createNewBidPage.clickElementByText("Square Feet°Horas", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "24.5");
		newAreaPricePage.enterTextToElementByText("0.0", "25");
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		getCommon().sleepFor(3);
		
		newAreaImagePage.clickFollowingSiblingbyXpath("Windows", language);
		//newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		//newAreaPricePage.enterTextToElementByExactText("Price", "3");
		//newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		// will accept max value
		projectPricePage.clickUnitDropDown();
		createNewBidPage.clickElementByText("Square Feet°Horas", language);
	//	createNewBidPage.enterTextToElementByExactText("Unit", "Square Feet");
		
		//createNewBidPage.clickElementByText("Square Feet°Horas", language);
		//createNewBidPage.clickElementByText("Unit°Unidado", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "24.5");
		newAreaPricePage.enterTextToElementByText("0.0", "25");
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		getCommon().sleepFor(3);
		
		newAreaPricePage.clickElementByElementName("SAVE",language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.imageDisplayedInSummary());
		
		
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		bidSummaryPage.scrollToElementByText(note, language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		
		
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,225.00", language));
		
		newAreaPricePage.scrollToElementByText(area, language);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language, true);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Duplicate°Duplicar", language);
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("deletearea");
		getCommon().sleepFor(3);
		newAreaNamePage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		newAreaNamePage.scrollToElementByText("deletearea", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("deletearea", language));
		getCommon().sleepFor(3);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language, true);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.clickElementByText(", DELETE", language);
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("deletearea", language));
		
		
		
		
		
		bidSummaryPage.scrollToElementByText("Bid Expiration", language);
		newAreaPricePage.clickElementByElementName("Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));
		newAreaPricePage.clickElementByElementName("Amount of Time", language);
		newAreaPricePage.clickElementByElementName("30 days", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Estimated Project Duration", language);
		newAreaPricePage.enterTextToElementByText("0", "5");
		projectPricePage.clickElementByElementName("Unit", language);
		newAreaPricePage.clickElementByElementName("Weeks", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickDiscountUnitElement(language);
		bidDiscountPage.clickElementByText("Percent°Porcentaje", language);
		bidDiscountPage.enterValueOnDiscountField("10");
		newAreaPricePage.clickElementByElementName("DONE", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("- $122.50", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));
		bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		bidTaxPage.enterValueOnTaxField("18.5");
		bidTaxPage.clickElementByText("DONE°GUARDAR", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("+ $203.96", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,306.46", language));
		bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

		bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

		bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("30 Days", language));
		bidSummaryPage.scrollToElementByText("COMPOSE EMAIL DRAFT", language);
		newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("bid-preview.pdf", language));
		bidSummaryPage.clickBackButton();
		bidSummaryPage.closeAndLaunchApp();
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void aaverifyEndToEndScenarioTasksIOS() throws Exception {
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		pricingMethodPage.clickElementByText("Task Prices°Precios por trabajo", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45676546 + 1);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName("area" + randomNum);
		area = "area" + randomNum;
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search products", language);
		newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
		getCommon().sleepFor(5);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
		newAreaNotesPage.enterNotes(note);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		
		newAreaImagePage.clickBlueEditPencilBYTaskName("Walls", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "122500");
		newAreaPricePage.clickElementByText("Done°Listo", language);
		
		newAreaPricePage.clickElementByText("Done°Listo", language);
		
		
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
		}
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("$", "1,225.00"));// ("$1,225.00",
		
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("Walls, Windows",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Duplicate°Duplicar", language);
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("deletearea");
		getCommon().sleepFor(3);
		newAreaNamePage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		
		//bidSummaryPage.scrollToElementByText("Area Subtotal", language);

				
		
		
		
		
		
		
		newAreaNamePage.scrollToElementByText("deletearea", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("deletearea", language));
		getCommon().sleepFor(3);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.elementClickByContainTextAndSpaceIOS("Confirm", "YES");
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("deletearea", language));
		
		
		
		
		
		
		// language));
		bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		newAreaPricePage.clickElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));

		newAreaPricePage.clickElementByText("Amount of Time", language);
		// newAreaPricePage.clickElementByElementName("30 days", language);
		newAreaPricePage.selectFromIosWheelerByText("30 days", language);
		newAreaPricePage.clickElementByElementName("DONE", language);

		newAreaPricePage.clickElementByText("Estimated Project Duration", language);
		newAreaPricePage.enterTextToElementByText("Number", "5");
		projectPricePage.clickElementByText("Amount of Time", language);
		newAreaPricePage.selectFromIosWheelerByText("Weeks", language);
		newAreaPricePage.clickElementByText("DONE", language);

		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",1, language);
		// bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickElementByText("Discount Type", language);
		bidDiscountPage.selectFromIosWheelerByText("Percent°Porcentaje", language);
		bidDiscountPage.enterTextToElementByText("NUMBER", "10");
		newAreaPricePage.clickElementByText("Done", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextValueAndSpaceIOS("- ", "122.50"));// 2,110.95
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,102.50", "$"));

		// bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		//bidSummaryPage.clickEditPencilBidsSummaryByText("Tax°Impuestos", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",2, language);
		bidTaxPage.enterTextToElementByText("PERCENT", "18.500");
		bidTaxPage.clickElementByText("Done°Listo", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("+", "203.96"));// 2,110.95
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,306.46", "$"));

		bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
		}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

		// bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

		// bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("30 days", language));
		bidSummaryPage.scrollToElementByText("Next Steps", language);
		newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
		//Assert.assertTrue(bidSummaryPage.elementExistsByText("OK", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("email account", "Email is not available"));
	}
	@Test(groups = { "mobile_regression", "android" }, description = "Verify end to end scenario area")
	public void verifyEndToEndScenarioAreaAndroid() throws Exception {
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		getCommon().sleepFor(3);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45676546 + 1);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		newAreaTypePage.clickInteriorCta();
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("area" + randomNum);
		area = "area" + randomNum;
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search products", language);
	//	newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");

		getCommon().sleepFor(20);
		newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
		getCommon().sleepFor(5);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
		newAreaNotesPage.enterNotes(note);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		projectPricePage.clickUnitDropDown();
		createNewBidPage.clickElementByText("Square Feet°Horas", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "24.5");
		newAreaPricePage.enterTextToElementByText("0.0", "50");
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);

		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,225.00", language));
		bidSummaryPage.scrollToElementByText("Bid Expiration", language);
		newAreaPricePage.clickElementByElementName("Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));

		newAreaPricePage.clickElementByElementName("Amount of Time", language);
		newAreaPricePage.clickElementByElementName("30 days", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);

		newAreaPricePage.clickElementByElementName("Estimated Project Duration", language);
		newAreaPricePage.enterTextToElementByText("0", "5");
		projectPricePage.clickElementByElementName("Unit", language);
		newAreaPricePage.clickElementByElementName("Weeks", language);
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);

		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickDiscountUnitElement(language);
		bidDiscountPage.clickElementByText("Percent°Porcentaje", language);
		bidDiscountPage.enterValueOnDiscountField("10");
		newAreaPricePage.clickElementByElementName("DONE", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("- $122.50", language));// 2,110.95
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));

		bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		bidTaxPage.enterValueOnTaxField("18.5");
		bidTaxPage.clickElementByText("DONE°GUARDAR", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("+ $203.96", language));// 2,110.95
		Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,306.46", language));// 2,110.95

		bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if(bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
			
			}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));;
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

		bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

		bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("30 Days", language));
		bidSummaryPage.scrollToElementByText("COMPOSE EMAIL DRAFT", language);
		newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("bid-preview.pdf", language));
		bidSummaryPage.clickBackButton();
		bidSummaryPage.closeAndLaunchApp();
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verifyEndToEndScenarioAreaIOS() throws Exception {
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45676546 + 1);
		// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName("area" + randomNum);
		area = "area" + randomNum;
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search products", language);
		newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
		getCommon().sleepFor(10);
		newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
		getCommon().sleepFor(5);
		newAreaImagePage.clickElementByElementName("Search more products", language);
		newAreaImagePage.clickElementByElementName("Search Products", language);
		//newAreaImagePage.clickElementByText("Search Products", language);
		newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Advance Protective Products Rust Destroye");
		newAreaImagePage.enterTextToElementByTextLabel("Search products", "Paint SCENTsation");
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByTextValue("Advance Products Flat Seam Roller", language);
		

		Assert.assertTrue(bidSummaryPage.elementExistsByText("Advance Products Flat Seam Roller", language));
		newAreaProductsSearchPage.clickDeleteButtonByIndex(2);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.elementClickByContainTextAndSpaceIOS(", DELETE", "YES");
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("Advance Products Flat Seam Roller", language));

		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		//newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
		newAreaNotesPage.enterNotes(note);
		newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		projectPricePage.clickUnitDropDown();
		// createNewBidPage.clickElementByText("Square Feet°Horas", language);
		getCommon().sleepFor(3);
		newAreaPricePage.enterCost_("50", language);
		newAreaPricePage.enterNumberOfUnits_("24.5", language);
		// newAreaPricePage.enterTextToElementByText("$0.00", "24.5");
		// newAreaPricePage.enterTextToElementByText("0.0", "50");
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		newAreaPricePage.clickElementByText("Done", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
		}
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("$", "1,225.00"));// ("$1,225.00",
		
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("Walls, Windows",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Duplicate°Duplicar", language);
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName("deletearea");
		getCommon().sleepFor(3);
		newAreaNamePage.clickElementByText("DONE", language);
		getCommon().sleepFor(3);
		
		//bidSummaryPage.scrollToElementByText("Area Subtotal", language);

				
		
		
		
		
		
		
		newAreaNamePage.scrollToElementByText("deletearea", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("deletearea", language));
		getCommon().sleepFor(3);
		newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.elementClickByContainTextAndSpaceIOS("Confirm", "YES");
		getCommon().sleepFor(3);
		Assert.assertFalse(bidSummaryPage.elementExistsByText("deletearea", language));
		
		
		
		
		
		
		// language));
		bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		newAreaPricePage.clickElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));

		newAreaPricePage.clickElementByText("Amount of Time", language);
		// newAreaPricePage.clickElementByElementName("30 days", language);
		newAreaPricePage.selectFromIosWheelerByText("30 days", language);
		newAreaPricePage.clickElementByElementName("DONE", language);

		newAreaPricePage.clickElementByText("Estimated Project Duration", language);
		newAreaPricePage.enterTextToElementByText("Number", "5");
		projectPricePage.clickElementByText("Amount of Time", language);
		newAreaPricePage.selectFromIosWheelerByText("Weeks", language);
		newAreaPricePage.clickElementByText("DONE", language);

		bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",1, language);
		// bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
		bidDiscountPage.clickElementByText("Discount Type", language);
		bidDiscountPage.selectFromIosWheelerByText("Percent°Porcentaje", language);
		bidDiscountPage.enterTextToElementByText("NUMBER", "10");
		newAreaPricePage.clickElementByText("Done", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextValueAndSpaceIOS("- ", "122.50"));// 2,110.95
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,102.50", "$"));

		// bidSummaryPage.clickElementByText("Tax°Impuestos", language);
		//bidSummaryPage.clickEditPencilBidsSummaryByText("Tax°Impuestos", language);
		bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",2, language);
		bidTaxPage.enterTextToElementByText("PERCENT", "18.500");
		bidTaxPage.clickElementByText("Done°Listo", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("+", "203.96"));// 2,110.95
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,306.46", "$"));

		bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
		if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
		}
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

		// bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

		// bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("30 days", language));
		bidSummaryPage.scrollToElementByText("Next Steps", language);
		newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
		//Assert.assertTrue(bidSummaryPage.elementExistsByText("OK", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("email account", "Email is not available"));
	}
	
	@Test(groups = { "mobile_regression", "ios" }, description = "Verify end to end scenario tasks")
	public void verifyEndToEndScenarioProjectIOS() throws Exception {
	//language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
	projectBidsHomePage.scrollToElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
	projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
	pricingMethodPage.clickElementByText("Project Total°Total del Proyecto", language);
	int randomNum = ThreadLocalRandom.current().nextInt(678, 456798 + 1);
	createNewBidPage.inputBidName("qa" + randomNum);
	createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
	newAreaPricePage.clickFlatRateTabString(language);
	newAreaPricePage.enterFlatRateValue("1225");
	projectPricePage.clickDone(language);
	getCommon().sleepFor(3);
	
	bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
	bidSummaryPage.clickNewAreaCTA();
	getCommon().sleepFor(3);
	newAreaTypePage.clickInteriorCta();
	getCommon().sleepFor(3);
	newAreaNamePage.enterAreaName("area" + randomNum);
	area = "area" + randomNum;
	newAreaNamePage.clickContinueCta();
	getCommon().sleepFor(3);

	newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
	getCommon().sleepFor(3);
	newAreaTasksPage.clickTaskByText("Windows", language);
	newAreaTasksPage.clickTaskByText("Walls", language);
	getCommon().sleepFor(3);
	newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
	getCommon().sleepFor(3);
	newAreaImagePage.clickElementByElementName("Search more products", language);
	newAreaImagePage.clickElementByElementName("Search products", language);
	newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
	newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
	getCommon().sleepFor(10);
	newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
	getCommon().sleepFor(5);
	newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
	
	//newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
	getCommon().sleepFor(3);
	String note = "8cUop91nQvOoldNmYzlPZWbTORyYzgIWgSqyvzwDz6psYMf14fSEC3fRPw2LPTrCz58g4HZQCmZina2b4LN5MNS0xWlgU9XaiNq";
	newAreaNotesPage.enterNotes(note);
	newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
	getCommon().sleepFor(3);
	//projectPricePage.clickUnitDropDown();
	newAreaProductsSearchPage.clickElementByText("DONE°LISTO", language);
	getCommon().sleepFor(3);
	

	newAreaPricePage.clickElementByText("Done", language);
	getCommon().sleepFor(3);
	Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
	Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
	if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
		Assert.assertTrue(true);
	} else {
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
	}
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("$", "1,225.00"));// ("$1,225.00",
	
	// Assert.assertTrue(bidSummaryPage.elementExistsByText("Walls, Windows",
	// language));
	Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
	newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
	getCommon().sleepFor(3);
	newAreaPricePage.clickElementByElementName("Duplicate°Duplicar", language);
	getCommon().sleepFor(3);
	newAreaNamePage.enterAreaName("deletearea");
	getCommon().sleepFor(3);
	newAreaNamePage.clickElementByText("DONE", language);
	getCommon().sleepFor(3);
	
	//bidSummaryPage.scrollToElementByText("Area Subtotal", language);

			
	
	
	
	
	
	
	newAreaNamePage.scrollToElementByText("deletearea", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByText("deletearea", language));
	getCommon().sleepFor(3);
	newAreaPricePage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
	getCommon().sleepFor(3);
	newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
	getCommon().sleepFor(3);
	newAreaProductsSearchPage.elementClickByContainTextAndSpaceIOS("Confirm", "YES");
	getCommon().sleepFor(3);
	Assert.assertFalse(bidSummaryPage.elementExistsByText("deletearea", language));
	
	
	
	
	
	
	// language));
	bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
	newAreaPricePage.clickElementByText("Project Bid Expiration", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByText("How long will the bid price be valid?", language));

	newAreaPricePage.clickElementByText("Amount of Time", language);
	// newAreaPricePage.clickElementByElementName("30 days", language);
	newAreaPricePage.selectFromIosWheelerByText("30 days", language);
	newAreaPricePage.clickElementByElementName("DONE", language);

	newAreaPricePage.clickElementByText("Estimated Project Duration", language);
	newAreaPricePage.enterTextToElementByText("Number", "5");
	projectPricePage.clickElementByText("Amount of Time", language);
	newAreaPricePage.selectFromIosWheelerByText("Weeks", language);
	newAreaPricePage.clickElementByText("DONE", language);

	bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
	bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",2, language);
	// bidSummaryPage.clickElementByText("DISCOUNT°DESCUENTO", language);
	bidDiscountPage.clickElementByText("Discount Type", language);
	bidDiscountPage.selectFromIosWheelerByText("Percent°Porcentaje", language);
	bidDiscountPage.enterTextToElementByText("NUMBER", "10");
	newAreaPricePage.clickElementByText("Done", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextValueAndSpaceIOS("- ", "122.50"));// 2,110.95
	// Assert.assertTrue(bidSummaryPage.elementExistsByText("$1,102.50", language));
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,102.50", "$"));

	// bidSummaryPage.clickElementByText("Tax°Impuestos", language);
	//bidSummaryPage.clickEditPencilBidsSummaryByText("Tax°Impuestos", language);
	bidSummaryPage.clickEditPencilBidsSummaryByText("editBlue",3, language);
	bidTaxPage.enterTextToElementByText("PERCENT", "18.500");
	bidTaxPage.clickElementByText("Done°Listo", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("+", "203.96"));// 2,110.95
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("1,306.46", "$"));

	bidTaxPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByText(area, language));
	Assert.assertTrue(bidSummaryPage.elementExistsByText(note, language));
	if (bidSummaryPage.elementExistsByText("Walls, Windows", language)) {
		Assert.assertTrue(true);
	} else {
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Windows, Walls", language));
	}
	Assert.assertTrue(bidSummaryPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));

	// bidSummaryPage.scrollToElementByText("Estimated Project Duration", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByText("5 Weeks", language));

	// bidSummaryPage.scrollToElementByText("Project Bid Expiration", language);
	Assert.assertTrue(bidSummaryPage.elementExistsByText("30 days", language));
	bidSummaryPage.scrollToElementByText("Next Steps", language);
	newAreaPricePage.clickElementByElementName("COMPOSE EMAIL DRAFT", language);
	//Assert.assertTrue(bidSummaryPage.elementExistsByText("OK", language));
	Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("email account", "Email is not available"));
	
	}


}
