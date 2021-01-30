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

public class BidSummaryProductsPageTest extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private BidPreviewPage bidPreviewPage = null;
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

	public BidSummaryProductsPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		pricingMethodPage = new PricingMethodPage(getCommon());
		loginPage = new LoginPage(getCommon());
		bidPreviewPage = new BidPreviewPage(getCommon());
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
		if (firstRun) {
			language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			getCommon().sleepFor(3);
			pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
			getCommon().sleepFor(3);
			int randomNum = ThreadLocalRandom.current().nextInt(678, 456765432 + 1);
			// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
			createNewBidPage.inputBidName("qa" + randomNum);
			createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			getCommon().sleepFor(3);
			bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
			bidSummaryPage.clickNewAreaCTA();
			getCommon().sleepFor(3);
			newAreaTypePage.clickInteriorCta();
			getCommon().sleepFor(3);
			newAreaNamePage.enterAreaName("area" + randomNum);
			newAreaNamePage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);// clickContinueCta();
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaImagePage.clickElementByElementName("Search more products", language);
			newAreaImagePage.clickElementByElementName("Search products", language);
			newAreaImagePage.enterTextToElementByTextLabel("Search for products", "Macropoxy 920 Pre-");
			newAreaImagePage.enterTextToElementByTextLabel("Search products", "Macropoxy 920 Pre-");
			getCommon().sleepFor(20);
			newAreaImagePage.clickElementByTextValue("Macropoxy 920 Pre-Prime", language);
			getCommon().sleepFor(5);
			newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaNotesPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaPricePage.clickFlatRateTabString(language);
			newAreaPricePage.enterFlatRateValue("99999878");
			newAreaPricePage.clickElementByText("Done°Listo", language);
			newAreaPricePage.clickElementByElementName("DONE°LISTO", language);
			getCommon().sleepFor(3);
			// bidSummaryPage.scrollToElementByText("PREVIEW°VISTA PREVIA", language);
			// bidSummaryPage.clickElementByText("PREVIEW°VISTA PREVIA", language);
			firstRun = false;
		} else {
			// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
			// ESPECIALES");
			homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES",
					language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			getCommon().sleepFor(3);
			pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
			getCommon().sleepFor(3);
			int randomNum = ThreadLocalRandom.current().nextInt(678, 456765432 + 1);
			// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
			createNewBidPage.inputBidName("qa" + randomNum);
			createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			getCommon().sleepFor(3);
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify product is displayed")
	public void verify1ProductsExpandedStateAndroid() throws Exception {
		bidSummaryPage.scrollToElementByText("View All Products", language);

		Assert.assertTrue(bidPreviewPage.elementExistsByText("View All Products", language));
		Assert.assertTrue(bidPreviewPage.elementExistsByText("1 Product has been added to this bid", language));
		bidPreviewPage.clickElementByText("View All Products", language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		Assert.assertTrue(bidPreviewPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify product is displayed")
	public void verify2ProductsEmptyStateAndroid() throws Exception {
		bidSummaryPage.scrollToElementByText("Products display once added to an area.", language);

		Assert.assertTrue(bidPreviewPage.elementExistsByText("Products display once added to an area.", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify product is displayed")
	public void verify1ProductsExpandedStateIOS() throws Exception {
		bidSummaryPage.scrollToElementByText("View All Products", language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText("View All Products", language));
		Assert.assertTrue(bidPreviewPage.elementExistsByText("1 products have been added to this bid", language));
		bidPreviewPage.clickElementByText("View All Products", language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText("Macropoxy 920 Pre-Prime", language));
		Assert.assertTrue(bidPreviewPage.elementExistsByText("Hide Products", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify product is displayed")
	public void verify2ProductsEmptyStateIOS() throws Exception {
		bidSummaryPage.scrollToElementByText("Products display once added to an area", language);
		Assert.assertTrue(bidPreviewPage.elementExistsByText("Products display once added to an area", language));
	}

}