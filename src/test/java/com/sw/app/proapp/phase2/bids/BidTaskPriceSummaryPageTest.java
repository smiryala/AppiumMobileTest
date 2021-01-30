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

public class BidTaskPriceSummaryPageTest extends MobileCoreBaseTest {
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

	public BidTaskPriceSummaryPageTest() {
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
		pricingMethodPage.selectTaskPriceOption(language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 4567 + 1);
		getCommon().sleepFor(3);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		getCommon().sleepFor(3);
		firstRun = false;
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify Task Price Summary page labels")
	public void verify4TaskPriceSummaryLabelsExistsAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		newAreaImagePage.clickElementByText("Windows", language);
		newAreaImagePage.clickElementByText("Walls", language);
		newAreaImagePage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);

		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaNotesPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Windows", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Walls", language));
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("TASK PRICE SUMMARY°RESUMEN DE PRECIO POR TRABAJO", language));
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("Estimated Area Subtotal:°Subtotal estimado del área", language));
		Assert.assertTrue(newAreaPricePage
				.elementExistsByText("Confirm the task prices°Confirmar los precios del trabajo", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify Task Price Summary page labels")
	public void verify4TaskPriceSummaryLabelsExistsIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		;
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);

		Assert.assertTrue(newAreaPricePage.elementExistsByText("Windows", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Walls", language));
		// TASK PRICE SUMMARY
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("TASK PRICE SUMMARY°RESUMEN DE PRECIO POR TRABAJO", language));
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("Estimated Area Subtotal:°Subtotal estimado del área", language));
		Assert.assertTrue(newAreaPricePage
				.elementExistsByText("Confirm the task prices°Confirmar los precios del trabajo", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify ADD TASK PRICE editing task")
	public void verify2AddTaskPricePageForEditingTaskAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaNotesPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaImagePage.clickElementByText("Windows", language);
		newAreaImagePage.clickElementByText("Walls", language);
		newAreaImagePage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaNotesPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaImagePage.clickFollowingSiblingbyXpath("Walls", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("ADD TASK PRICE°AGREGAR PRECIO DEL TRABAJO", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify ADD TASK PRICE editing task")
	public void verify2AddTaskPricePageForEditingTaskIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		;
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickBlueEditPencilBYTaskName("Windows", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Add Task Price°AGREGAR PRECIO DEL TRABAJO", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify Update Flat Rate On Task Price Summary")
	public void aaverify1TaskPriceSummaryCalculationDisplayedInBidSummaryAndroid() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		newAreaImagePage.clickElementByText("Windows", language);
		newAreaImagePage.clickElementByText("Walls", language);
		newAreaImagePage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaNotesPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		newAreaImagePage.clickFollowingSiblingbyXpath("Walls", language);
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByExactText("Price", "3");
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		// will accept max value

		newAreaImagePage.clickFollowingSiblingbyXpath("Windows", language);
		newAreaPricePage.clickElementByText("Flat Rate°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByExactText("Price", "5");
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("TASK PRICE SUMMARY°RESUMEN DE PRECIO POR TRABAJO", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$3.00", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$5.00", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByText("$8.00", language));
		newAreaPricePage.clickElementByText("DONE°LISTO", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		// £
		if (newAreaPricePage.elementExistsByText("$8.00", language)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(newAreaPricePage.elementExistsByText("£8.00", language));
		}

	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify Update Flat Rate On Task Price Summary")
	public void verify1TaskPriceSummaryCalculationDisplayedInBidSummaryIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		// bidsPage.clickOnFirstBidOfTheList(language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		;
		newAreaTasksPage.clickTaskByText("Windows", language);
		newAreaTasksPage.clickTaskByText("Walls", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickBlueEditPencilBYTaskName("Walls", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "300");
		newAreaPricePage.clickElementByText("Done°GUARDAR", language);
		// will accept max value
		newAreaImagePage.clickBlueEditPencilBYTaskName("Windows", language);
		newAreaPricePage.clickElementByElementName("flatRateLabel°Costo Fijo", language);
		newAreaPricePage.enterTextToElementByText("$0.00 (USD)", "500");
		newAreaPricePage.clickElementByText("Done°GUARDAR", language);
		Assert.assertTrue(
				newAreaPricePage.elementExistsByText("TASK PRICE SUMMARY°RESUMEN DE PRECIO POR TRABAJO", language));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("3.00", "$"));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("5.00", "$"));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("8.00", "$"));
		newAreaPricePage.clickElementByText("Done°LISTO", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("8.00", "$"));
	}

}