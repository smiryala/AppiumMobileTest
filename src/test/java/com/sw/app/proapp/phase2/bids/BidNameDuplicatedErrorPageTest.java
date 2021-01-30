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

public class BidNameDuplicatedErrorPageTest extends MobileCoreBaseTest {
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

	public BidNameDuplicatedErrorPageTest() {
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
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		getCommon().sleepFor(3);
		// createNewBidPage.inputBidName("donoterase");
		// createNewBidPage.enterTextToElementByText("Bid Name","donoterase");
		// createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR",
		// language);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify flat rate tab exists")
	public void verify1ErrorMessageDisplayedAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		createNewBidPage.inputBidName("donoterase");
		// createNewBidPage.enterTextToElementByText("Bid Name","donoterase");
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText(
				"Sorry, there's already a bid with this name. Please rename your bid to continue.°Porfavor", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify flat rate tab exists")
	public void verify1ErrorMessageDisplayedIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		// createNewBidPage.enterTextToElementByText("Bid Name", "donoterase");
		createNewBidPage.inputBidName("donoterase");
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		// Assert.assertTrue(newAreaPricePage.elementExistsByText("Sorry, there's
		// already a bid with this name. Please rename your bid to continue.°Porfavor",
		// language));
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextValueAndSpaceIOS("Sorry,",
				"Sorry, there's already a bid with this name. Please rename your bid to continue."));
	}
}
