package com.sw.app.proapp.bids;

import java.util.UUID;

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
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;

public class NewAreaNotesPageTest extends MobileCoreBaseTest {
	private HomePage homePage = null;
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
	private NewAreaNotesPage newAreaNotesPage = null;
	private NewAreaProductsSearchPage newAreaProductsSearchPage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private Login login = null;
	String proAppTestCase;
	AppDataFromDB appDataFromDB = null;

	public NewAreaNotesPageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	private String char501 = "g722PPy23tHZBRry9kXouBMCEXpDSS7u49beBZzcdHqqPUFvPTYUsvtkTE72R2ToalHCmg5YYsPspeRsOBC0lswVTOEdGG7CiSifKXTxj4f308Syce25mLvEJLkzw3YmgQ7C3LHIb5eKXuoloIo0jSxn4EjzXFhKwgqv9dD6HeuOBGkMWIgzAmJmbgqsSNZNiA1sJFpbSpPltg2VCuZtV6EKen8X6C4BdXOHXJlp3DWMbzH1RnkAWYtai18r3FLx1Meh9nwBuiHflk6L3FChs56e7LzxbbSBUOgADDpRxzJAXCrRUZdBVQMQVqPxakast87hok4al1hTYx0ujhwDhNb4sAwg1vuAGSiwHpYhIfcdQVUBepgCYlBeykgyqffSBHRFBTalhq0pH4c4pN2bOLVUjfibi60o7MYJW09rP40cGRnfV0EbGtvbfhOWgK5S4wUsCBZQ9dILgL0mYiMUdh3WWwTjVU7DhL5NEX6xrxUY9vuw3hlvz";

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaNotesPageTest_class() {
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
		newAreaNotesPage = new NewAreaNotesPage(getCommon());
		newAreaPricePage = new NewAreaPricePage(getCommon());
		newAreaProductsSearchPage = new NewAreaProductsSearchPage(getCommon());
		if (homePage.checkFirstRun()) {
			loginPage.clickSignIn();
			homePage.clickProAppButton();
			accountPage.clickAccountButton();
			accountPage.clickAccountSettingsButton();
			accountPage.verifyAndSetQADashboard();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void before_NewAreaNotesPageTest() {
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify area notes screen is correct")
	public void verifyNewAreaNotesScreenAndroid() throws Exception {
		getCommon().sleepFor(3);
		WebElement bidCard = bidsPage.getBidCard();
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		if (!getCommon().click(bidCard, "Unable to click bid")) {
			bidsPage.clickCreateNewBidButton();
			createNewBidPage.inputBidName("bidName");
			createNewBidPage.clickSaveBidButton();
			bidSummaryPage.clickNavigateUp();
			bidsPage.clickBidCardHeader();
		}

		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}

		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		/*
		 * newAreaNamePage.enterAreaName(areaName); newAreaNamePage.clickContinueCta();
		 * newAreaImagePage.clickSaveCta(); newAreaTasksPage.clickSaveCta();
		 * newAreaProductsSearchPage.clickContinueCta();
		 */

		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaImagePage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);
		newAreaProductsSearchPage.clickElementByElementName("SAVE & CONTINUE°CONTINUAR", language);

		Assert.assertTrue(newAreaNotesPage.getContinueCta().isEnabled(), "Continue CTA is not enabled");
		Assert.assertTrue(newAreaNotesPage.getDiscardCta().isEnabled(), "Discard CTA is not enabled");

		String char500 = "g722PPy23tHZBRry9kXouBMCEXpDSS7u49beBZzcdHqqPUFvPTYUsvtkTE72R2ToalHCmg5YYsPspeRsOBC0lswVTOEdGG7CiSifKXTxj4f308Syce25mLvEJLkzw3YmgQ7C3LHIb5eKXuoloIo0jSxn4EjzXFhKwgqv9dD6HeuOBGkMWIgzAmJmbgqsSNZNiA1sJFpbSpPltg2VCuZtV6EKen8X6C4BdXOHXJlp3DWMbzH1RnkAWYtai18r3FLx1Meh9nwBuiHflk6L3FChs56e7LzxbbSBUOgADDpRxzJAXCrRUZdBVQMQVqPxakast87hok4al1hTYx0ujhwDhNb4sAwg1vuAGSiwHpYhIfcdQVUBepgCYlBeykgyqffSBHRFBTalhq0pH4c4pN2bOLVUjfibi60o7MYJW09rP40cGRnfV0EbGtvbfhOWgK5S4wUsCBZQ9dILgL0mYiMUdh3WWwTjVU7DhL5NEX6xrxUY9vuw3hlv";
		String char501 = "g722PPy23tHZBRry9kXouBMCEXpDSS7u49beBZzcdHqqPUFvPTYUsvtkTE72R2ToalHCmg5YYsPspeRsOBC0lswVTOEdGG7CiSifKXTxj4f308Syce25mLvEJLkzw3YmgQ7C3LHIb5eKXuoloIo0jSxn4EjzXFhKwgqv9dD6HeuOBGkMWIgzAmJmbgqsSNZNiA1sJFpbSpPltg2VCuZtV6EKen8X6C4BdXOHXJlp3DWMbzH1RnkAWYtai18r3FLx1Meh9nwBuiHflk6L3FChs56e7LzxbbSBUOgADDpRxzJAXCrRUZdBVQMQVqPxakast87hok4al1hTYx0ujhwDhNb4sAwg1vuAGSiwHpYhIfcdQVUBepgCYlBeykgyqffSBHRFBTalhq0pH4c4pN2bOLVUjfibi60o7MYJW09rP40cGRnfV0EbGtvbfhOWgK5S4wUsCBZQ9dILgL0mYiMUdh3WWwTjVU7DhL5NEX6xrxUY9vuw3hlvz";

		newAreaNotesPage.enterNotes(char501);
		Assert.assertTrue(newAreaNotesPage.getNotesInputField().getText().length() < 501, "Text is too long");

		newAreaNotesPage.enterNotes(char500);
		Assert.assertEquals(newAreaNotesPage.getNotesInputField().getText().length(), 500, "Text is incorrect");
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify area notes screen is correct")
	public void verify2NewAreaNotesScreenIOS() throws Exception {
		String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		while (!getCommon().isDisplayed(bidSummaryPage.getNewAreaCTA())) {
			bidSummaryPage.scrollDown3();
		}
		bidSummaryPage.clickNewAreaCTA();
		newAreaTypePage.clickInteriorCta();
		newAreaNamePage.enterAreaName(areaName);
		newAreaNamePage.clickContinueCta();
		newAreaImagePage.clickSaveCta();
		newAreaTasksPage.clickSaveCta();
		newAreaProductsSearchPage.clickContinueCta();
		newAreaNotesPage.enterNotes(char501);
		Assert.assertEquals(newAreaNotesPage.getNotesInputField().getText().length(), 500, "Text is too long");
		// newAreaNotesPage.clickDiscardCta();
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
		// newAreaPricePage.clickElementByText("Square Feet", language);
		newAreaPricePage.clickCostInputCta();
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickHoursUnit();
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Hours", "Hours"));// hoursUnitIsVisible(),
																									// "Hours Unit CTA
																									// is not
																									// displayed");
		newAreaPricePage.clickCostInputCta();
		// newAreaPricePage.elementClickByContainTextAndSpaceIOS("Hours",
		// "Hours");//("Hours", language);
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickSquareFeetUnit();
		// Assert.assertTrue(newAreaPricePage.squareFeetUnitIsVisible(), "Square Feet
		// Unit CTA is not displayed");
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Square Feet", "Square Feet"));// hoursUnitIsVisible(),
																												// "Hours
																												// Unit
																												// CTA
																												// is
																												// not
																												// displayed");
		newAreaPricePage.clickCostInputCta();
		// newAreaPricePage.clickElementByText("Square Feet", language);
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickLinearFootUnit();
		// Assert.assertTrue(newAreaPricePage.linearFeetUnitIsVisible(), "Linear Feet
		// Unit CTA is not displayed");
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Linear Feet", "Linear Feet"));// hoursUnitIsVisible(),
																												// "Hours
																												// Unit
																												// CTA
																												// is
																												// not
																												// displayed");
		newAreaPricePage.clickCostInputCta();
		// newAreaPricePage.clickElementByText("Linear Feet", language);
		Assert.assertTrue(newAreaPricePage.costUnitPickerWheelVisible(),
				"Cost Unit picker wheel not displayed as expected");
		newAreaPricePage.clickEachUnit();
		// Assert.assertTrue(newAreaPricePage.eachUnitIsVisible(), "Each Unit CTA is not
		// displayed");
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Each", "Each"));// hoursUnitIsVisible(),
																									// "Hours Unit CTA
																									// is not
																									// displayed");
		Assert.assertTrue(newAreaPricePage.elementExistsByText("0.0", language));// costInputIsVisible(), "Cost Per Unit
																					// Input is not displayed");
		// Assert.assertTrue(newAreaPricePage.unitCountInputIsVisible(), "Unit Count
		// Input is not displayed");
		newAreaPricePage.enterNumberOfUnits("10");
		newAreaPricePage.enterCost("1");
		newAreaPricePage.swipeToDismissKeyboard();
		getCommon().sleepFor(1);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("$", "10.00"));// totalPriceIsVisible(),
																								// "Total Price is not
																								// displayed");
		System.out.print("---------->");
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Go to Summary", "Save"));
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Done", "Done"));
		// Assert.assertTrue(newAreaPricePage.discardCtaIsVisible(), "Discard CTA is not
		// displayed");
		// Assert.assertTrue(newAreaPricePage.saveCtaIsVisible(), "Save CTA is not
		// displayed");
	}
}