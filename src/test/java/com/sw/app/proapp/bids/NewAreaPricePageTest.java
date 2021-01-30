package com.sw.app.proapp.bids;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.bids.pages.BidSummaryPage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaImagePage;
import com.sw.proapp.bids.pages.NewAreaNamePage;
import com.sw.proapp.bids.pages.NewAreaNotesPage;
import com.sw.proapp.bids.pages.NewAreaPricePage;
import com.sw.proapp.bids.pages.NewAreaProductsSearchPage;
import com.sw.proapp.bids.pages.NewAreaTasksPage;
import com.sw.proapp.bids.pages.NewAreaTypePage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.bids.pages.ProjectPricePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class NewAreaPricePageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
	private ProjectPricePage projectPricePage = null;
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

	public NewAreaPricePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		projectPricePage = new ProjectPricePage(getCommon());
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
		homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		getCommon().sleepFor(3);
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area prices are correct")
	public void verifyNewAreaPriceScreenAndroid() throws Exception {
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		projectBidsHomePage.clickElementByText("Area Subtotals°Subtotales de área", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 456768754 + 1);
		createNewBidPage.inputBidName("qa" + randomNum);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		bidSummaryPage.scrollToElementByText("New Area°ÁREA NUEVA", language);
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName("area" + randomNum);
		newAreaNamePage.clickContinueCta();
		getCommon().sleepFor(2);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		getCommon().sleepFor(2);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);

		projectPricePage.clickUnitDropDown();
		newAreaPricePage.clickElementByText("Hours°Horas", language);
		newAreaPricePage.enterTextToElementByText("$0.00", "3");
		newAreaPricePage.enterTextToElementByText("0.0", "3");

		Assert.assertTrue(newAreaPricePage.elementExistsByText("$9.00", language));//
		Assert.assertTrue(newAreaPricePage.saveCtaIsVisible(), "Save & Go to Summary");
		Assert.assertTrue(newAreaPricePage.saveCtaIsVisible(), "DONE");

	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area prices are correct")
	public void verifyNewAreaPriceScreenIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		newAreaTasksPage.clickSaveCta();
		newAreaProductsSearchPage.clickContinueCta();
		newAreaNotesPage.clickContinueCta();
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.costUnitCtaIsVisible(), "Cost Unit CTA is not displayed");
		newAreaPricePage.clickCostInputCta();
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickHoursUnit();
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Hours", "Hours")); // displayed");
		newAreaPricePage.clickCostInputCta();
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickSquareFeetUnit();
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Square Feet", "Square Feet")); // displayed");
		newAreaPricePage.clickCostInputCta();
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickLinearFootUnit();
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Linear Feet", "Linear Feet")); // displayed");
		newAreaPricePage.clickCostInputCta();
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickEachUnit();
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Each", "Each")); // displayed");
		Assert.assertTrue(newAreaPricePage.elementExistsByText("0.0", language));
		newAreaPricePage.enterNumberOfUnits("10");
		newAreaPricePage.enterCost("1");
		newAreaPricePage.swipeToDismissKeyboard();
		getCommon().sleepFor(1);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("$", "10.00")); // displayed");
		System.out.print("---------->");
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Go to Summary", "Save"));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Done", "Done"));
	}
}