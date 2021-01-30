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
import com.sw.proapp.bids.pages.NewAreaPricePage;
import com.sw.proapp.bids.pages.PricingMethodPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.bids.pages.ProjectPricePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class BidTypeSelectionPageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private ProjectPricePage projectPricePage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private PricingMethodPage pricingMethodPage = null;
	private AccountPage accountPage = null;
	private CreateNewBidPage createNewBidPage = null;
	private LoginPage loginPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private String language = "";
	String proAppTestCase;
	private Login login = null;
	AppDataFromDB appDataFromDB = null;

	public BidTypeSelectionPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_BidsPageTest_class() {
		projectPricePage = new ProjectPricePage(getCommon());
		pricingMethodPage = new PricingMethodPage(getCommon());
		newAreaPricePage = new NewAreaPricePage(getCommon());
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
		// language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS
		// ESPECIALES");
		homePage.scrollToElementByText("CREATE NEW BID°CREAR COTIZACIÓN", "SPECIAL OFFERS°OFERTAS ESPECIALES",
				language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);

	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void aaaverifyAndroidTypeSelectionPageLabels() throws Exception {
		Assert.assertTrue(pricingMethodPage.elementExistsByText("PRICING METHOD°MÉTODO DE COSTEO", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText(
				"How would you like to bid this project?°Cómo quieres cotizar este proyecto?", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText(
				"Pick one of three pricing methods. You won't be able to change methods without starting a new bid. Each option has the same level of detail and capabilities.°Escoje uno de los tres métodos de costeo. No podrás cambiar el método sin hacer una nueva cotización. Las tres opciones tienen la misma capacidad y nivel de detalle.",
				language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Project Total°Total del Proyecto", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Area Subtotals°Subtotales de área", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Task Prices°Precios por trabajo", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void verifyAndroidTypeSelectionPageLabelsIOS() throws Exception {
		Assert.assertTrue(pricingMethodPage.elementExistsByText("PRICING METHOD°MÉTODO DE COSTEO", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText(
				"How would you like to bid this project?°Cómo quieres cotizar este proyecto?", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByContainTextValueAndSpaceIOS("Pick",
				"one of three pricing methods. You won't be able to change methods without starting a new bid. Each option has the same level of detail and capabilities."));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Project Total°Total del Proyecto", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Area Subtotals°Subtotales de área", language));
		Assert.assertTrue(pricingMethodPage.elementExistsByText("Task Prices°Precios por trabajo", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyAndroidTypeSelectionPageRedirectProjectTotal() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
//createNewBidPage.inputBidName("qa"+randomNum);
		createNewBidPage.clickElementByText("NEXT°SIGUIENTE", language);
		pricingMethodPage.clickElementByText("Project Total°Total del Proyecto", language);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("PROJECT PRICE°PRECIO DEL PROYECTO", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void aaaaverifyAndroidTypeSelectionPageRedirectProjectTotalIOS() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45677538 + 1);
		pricingMethodPage.clickElementByText("Project Total", language);
		createNewBidPage.enterTextToElementByText("Bid Name", "qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
		newAreaPricePage.clickFlatRateTabString(language);
		newAreaPricePage.enterFlatRateValue("99999878");
		projectPricePage.clickElementByText("DONE", language);// (language);
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Project Total°Tipo de Precio: Proyecto total", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyAndroidTypeSelectionPageRedirectAreaSubtotalsTotal() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		createNewBidPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("BID SUMMARY°RESUMEN DE COTIZACIÓN", language));
//Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Task Prices°Tipo de Precio: Subtotal de area", language));
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Area Subtotal°Tipo de Precio: Subtotal de area", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void aaverifyAndroidTypeSelectionPageRedirectAreaSubtotalsTotalIOS() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45677538 + 1);
		// createNewBidPage.clickElementByElementName("area_pricing_card", language);
		createNewBidPage.clickElementByText("Area Subtotals", language);
		createNewBidPage.enterTextToElementByText("Bid Name", "qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("BID SUMMARY°RESUMEN DE COTIZACIÓN", language));
//Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Task Prices°Tipo de Precio: Subtotal de area", language));
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Area Subtotal°Tipo de Precio: Subtotal de area", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid attached to email is a pdf")
	public void verifyAndroidTypeSelectionPageRedirectTasksPrice() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		createNewBidPage.clickElementByText("Task Price°Precios por trabajo", language);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("BID SUMMARY°RESUMEN DE COTIZACIÓN", language));
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Task Prices°Tipo de Precio: Precios de tareas", language));
//Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Area Subtotal°Tipo de Precio: Subtotal de area", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid attached to email is a pdf")
	public void aaverifyAndroidTypeSelectionPageRedirectTasksPriceIOS() throws Exception {
		int randomNum = ThreadLocalRandom.current().nextInt(678, 45677538 + 1);
		// createNewBidPage.clickElementByElementName("task_pricing_card", language);
		createNewBidPage.clickElementByText("Task Prices", language);
		createNewBidPage.enterTextToElementByText("Bid Name", "qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°CONTINUAR", language);
		Assert.assertTrue(pricingMethodPage.elementExistsByText("BID SUMMARY°RESUMEN DE COTIZACIÓN", language));
		Assert.assertTrue(pricingMethodPage
				.elementExistsByText("Pricing Method: Task Prices°Tipo de Precio: Precios de tareas", language));
//Assert.assertTrue(pricingMethodPage.elementExistsByText("Pricing Method: Area Subtotal°Tipo de Precio: Subtotal de area", language));
	}
}