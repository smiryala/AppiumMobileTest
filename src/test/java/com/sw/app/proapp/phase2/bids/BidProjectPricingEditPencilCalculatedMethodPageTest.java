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

public class BidProjectPricingEditPencilCalculatedMethodPageTest extends MobileCoreBaseTest {
	private ProjectPricePage projectPricePage = null;
	private PricingMethodPage pricingMethodPage = null;
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

	public BidProjectPricingEditPencilCalculatedMethodPageTest() {
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
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void aaverify_ProjectPricingCalculatedMethodAndroid() throws Exception {
		projectPricePage.clickUnitDropDown();
		createNewBidPage.clickElementByText("Hours°Horas", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "2");
		newAreaPricePage.enterTextToElementByText("0.0", "2");
		createNewBidPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("Estimated Total:", language);
		if (editAreaPage.elementExistsByText("£4.00", language)) {
			Assert.assertTrue(editAreaPage.elementExistsByText("£4.00", language));
		} else {
			Assert.assertTrue(editAreaPage.elementExistsByText("$4.00", language));
		}
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verify_SkipProjectPricingAndroid() throws Exception {

		Assert.assertTrue(editAreaPage.elementExistsByText(
				"If you skip this step now, you can add a price from the bid summary screen later.", language));
		Assert.assertTrue(editAreaPage.elementExistsByText("PROJECT PRICE", language));
		createNewBidPage.clickElementByText("Skip for now", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("BID SUMMARY", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void aaverify_EditProjectPricingAndroid() throws Exception {
		createNewBidPage.clickElementByText("Skip for now", language);
		bidSummaryPage.scrollToElementByText("Project Subtotal:", language);
		createNewBidPage.clickElementByText("Project Subtotal:", language);
		projectPricePage.clickUnitDropDown();
		createNewBidPage.clickElementByText("Hours°Horas", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "3");
		newAreaPricePage.enterTextToElementByText("0.0", "3");
		createNewBidPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("Estimated Total:", language);
		if (editAreaPage.elementExistsByText("£9.00", language)) {
			Assert.assertTrue(editAreaPage.elementExistsByText("£9.00", language));
		} else {
			Assert.assertTrue(editAreaPage.elementExistsByText("$9.00", language));
		}
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify_ProjectPricingCalculatedMethodIOS() throws Exception {
		// projectPricePage.clickUnitDropDown();
		// projectPricePage.selectFromIosWheelerByText("Hours°Horas", language);
		// projectPricePage.clickElementByText("Hours°Horas", language);
		projectPricePage.enterTextToElementByText("Cost ($)", "2");
		projectPricePage.enterTextToElementByText("NUMBER", "2");
		createNewBidPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("PREVIEW", language);
		// Assert.assertTrue(editAreaPage.elementExistsByText("$4.00", language));
		Assert.assertTrue(editAreaPage.elementExistsByContainTextAndSpaceIOS("4.00", "$"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify_SkipProjectPricingIOS() throws Exception {
		Assert.assertTrue(editAreaPage.elementExistsByText(
				"If you skip this step now, you can add a price from the bid summary screen later.", language));
		Assert.assertTrue(editAreaPage.elementExistsByText("PROJECT PRICE", language));
		createNewBidPage.clickElementByText("Skip for now", language);
		Assert.assertTrue(editAreaPage.elementExistsByText("BID SUMMARY", "BID SUMMARY"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verify_EditProjectPricingIOS() throws Exception {
		// createNewBidPage.clickElementByElementName("Skip for now", language);
		projectPricePage.enterTextToElementByText("Cost ($)", "1");
		projectPricePage.enterTextToElementByText("NUMBER", "3");
		createNewBidPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("Project Subtotal:", language);
		createNewBidPage.clickElementByElementName("editBlue", language);
		// projectPricePage.clickUnitDropDown();
		// createNewBidPage.clickElementByText("Hours°Horas", language);
		projectPricePage.enterTextToElementByText("3.0", "1");
		projectPricePage.enterTextToElementByText("1.0", "5");
		createNewBidPage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("Project Subtotal:", language);
		Assert.assertTrue(editAreaPage.elementExistsByContainTextAndSpaceIOS("5.00", "$"));
	}

}
