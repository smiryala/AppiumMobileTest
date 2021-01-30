package com.sw.app.proapp.phase2.bids;

import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.PricingMethodPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidNameMethodTypeUpdatePageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private PricingMethodPage pricingMethodPage = null;
	private AccountPage accountPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private String language = "";
	String proAppTestCase;
	private Login login = null;
	AppDataFromDB appDataFromDB = null;

	public BidNameMethodTypeUpdatePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidsPageTest_class() {
		pricingMethodPage = new PricingMethodPage(getCommon());
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		createNewBidPage = new CreateNewBidPage(getCommon());
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
	public void before_BidsPageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (loginPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("CREATE NEW BID°CREAR COTIZACIÓN", "SPECIAL OFFERS°OFERTAS ESPECIALES",
				language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyBidNameProjectTotalAndroid() throws Exception {
		createNewBidPage.clickElementByText("Project Total", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Project Total", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void verifyBidNameProjectTotalIOS() throws Exception {
		pricingMethodPage.clickElementByElementName("project_pricing_card", language);
		Assert.assertTrue(
				pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Pricing Method:", "Pricing Method:"));// °Tipo
																												// de
																												// Precio:",
																												// language));
		Assert.assertTrue(pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Project Total", "Project Total"));// °Project
																														// Total",
																														// language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyAreaSubtotalsTotalAndroid() throws Exception {
		createNewBidPage.clickElementByText("Area Subtotals°Precios por trabajo", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Area Subtotal", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void verifyAreaSubtotalsTotalIOS() throws Exception {
		createNewBidPage.clickElementByElementName("area_pricing_card", language);
		Assert.assertTrue(
				pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Pricing Method:", "Pricing Method:"));// ("Pricing
																												// Method:°Tipo
																												// de
																												// Precio:",
																												// language));
		Assert.assertTrue(pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Area Subtotal", "Area Subtotal"));// ;°Subtotal
																														// del
																														// área",
																														// language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyTasksPriceAndroid() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		createNewBidPage.clickElementByText("Task Price°Precios por trabajo", language);
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Task Prices°Tipo de Precio: Precios de tareas", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void verifyTasksPriceIOS() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		createNewBidPage.clickElementByElementName("task_pricing_card", language);
		Assert.assertTrue(
				pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Pricing Method:", "Pricing Method:"));// °Tipo
																												// de
																												// Precio:",
																												// language));
		Assert.assertTrue(pricingMethodPage.elementExistsByContainTextAndSpaceIOS("Task Prices", "Task Prices"));// °Precios
																													// por
																													// trabajo",
																													// language));
	}
}