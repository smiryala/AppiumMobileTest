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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BidNameDiscardPageTest extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private String duplicatedName = "";
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

	public BidNameDiscardPageTest() {
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
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.clickElementByText("Area Subtotals°Area Subtotales de área", language);
		getCommon().sleepFor(3);

	}

	@Test(groups = { "mobile_regression", "android" }, description = "discard bid")
	public void verifyDiscardBidPopUpModalAndroid() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText(
				"Are you sure you’d like to discard this information? This action can’t be undone°¿Estas seguro que quieres borrar esta información? Esta acción no puede deshacerse.",
				language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("NO, GO BACK°NO, REGRESAR", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("YES, DISCARD°SI, BORRAR", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "discard bid")
	public void verifyDiscardBid_ClickingNoOptionAndroid() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		newAreaPricePage.clickElementByText("NO, GO BACK°NO, REGRESAR", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("BID NAME°NOMBRE DEL ESTIMADO", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "discard bid")
	public void verifyDiscardBid_ClickingYesOptionAndroid() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		newAreaPricePage.clickElementByText("YES, DISCARD°SI, BORRAR", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("PROJECT BIDS°COTIZACIONES", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "discard bid")
	public void verifyDiscardBidPopUpModalIOS() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText(
				"Are you sure you’d like to discard this information? This action can’t be undone.°¿Estas seguro que quieres borrar esta información? Esta acción no puede deshacerse.",
				language));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("No", ", Go Back"));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Yes", ", Discard"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "discard bid")
	public void verifyDiscardBid_ClickingNoOptionIOS() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		newAreaPricePage.clickElementByElementName("No, Go Back°No, regresar", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("BID NAME°NOMBRE DEL ESTIMADO", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "discard bid")
	public void verifyDiscardBid_ClickingYesOptionIOS() throws Exception {
		createNewBidPage.clickElementByText("Discard Bid°Descartar Cotización", language);
		newAreaPricePage.elementClickByContainTextAndSpaceIOS("Yes,", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("PROJECT BIDS°COTIZACIONES", language));
	}

}
