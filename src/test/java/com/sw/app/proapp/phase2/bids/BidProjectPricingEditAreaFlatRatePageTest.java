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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BidProjectPricingEditAreaFlatRatePageTest extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private ProjectPricePage projectPricePage = null;
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

	public BidProjectPricingEditAreaFlatRatePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
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
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.clickElementByText("Project Total°Total del Proyecto", language);
		getCommon().sleepFor(3);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 456798 + 1);
		// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("99999878");
		projectPricePage.clickDone(language);
		getCommon().sleepFor(3);
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		area = areaName;
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		getCommon().sleepFor(3);
		bidSummaryPage.clickNewAreaCTA();
		getCommon().sleepFor(3);
		newAreaTypePage.clickInteriorCta();
		getCommon().sleepFor(3);
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta(language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(3);
		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
		// newAreaNotesPage.clickElementByElementName("DONE°LISTO", language);
		// newAreaNotesPage.clickContinueCta(language);
		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByText("DONE°LISTO", language);
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
		for (int i = 0; i < 2; i++) {
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify_EditArea_AreaSubtotal_HiddenAndroid() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		bidSummaryPage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertFalse(editAreaPage.elementExistsByText("Area Subtotal", language));
		Assert.assertTrue(editAreaPage.elementExistsByText("SAVE", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify_ProjectPricingFlatRateAndroid() throws Exception {
		bidSummaryPage.scrollToElementByText("Estimated Total:", language);
		if (editAreaPage.elementExistsByText("£999,998.00", language)) {
			Assert.assertTrue(editAreaPage.elementExistsByText("£999,998.00", language));
		} else {
			Assert.assertTrue(editAreaPage.elementExistsByText("$999,998.00", language));
		}
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify_EditArea_AreaSubtotal_HiddenIOS() throws Exception {
		// bidsPage.clickOnFirstBidOfTheList(language);
		// bidSummaryPage.scrollToElementByText(area, language);
		bidSummaryPage.clickLastElementByTextIOSANDROID("NOTES:°NOTAS:", "moreIcon", language);
		bidSummaryPage.clickElementByElementName("Edit°Editar", language);
		bidSummaryPage.scrollToElementByText("Area Subtotal", language);
		Assert.assertFalse(editAreaPage.elementExistsByText("Area Subtotal", language));
		Assert.assertTrue(editAreaPage.elementExistsByText("SAVE", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify_ProjectPricingFlatRateIOS() throws Exception {
		bidSummaryPage.scrollToElementByText("Project Subtotal:", language);
		// Assert.assertTrue(editAreaPage.elementExistsByText("$999,998.00", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextAndSpaceIOS("999,998.78", "$"));
	}
}
