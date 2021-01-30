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

public class BidSummaryEmailAddressPhoneActionablePageTest extends MobileCoreBaseTest {
	private ProjectInfoPage projectInfoPage = null;
	private String createdBid;
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

	public BidSummaryEmailAddressPhoneActionablePageTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void before_NewAreaPricePageTest_class() {
		projectInfoPage = new ProjectInfoPage(getCommon());
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

		if (firstRun) {
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
			getCommon().sleepFor(3);
			pricingMethodPage.clickElementByText("Project Total°Total del Proyecto", language);
			getCommon().sleepFor(3);
			int randomNum = ThreadLocalRandom.current().nextInt(67875, 456798567 + 1);
			createdBid = "qa" + randomNum;
			createNewBidPage.inputBidName("qa" + randomNum);
			createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
			getCommon().sleepFor(3);
			newAreaPricePage.clickElementByText("Skip for now", language);
			// newAreaPricePage.clickFlatRateTabString(language);
			// newAreaPricePage.enterFlatRateValue("99999878");
			// projectPricePage.clickDone(language);
			String areaName = String.valueOf(UUID.randomUUID().getMostSignificantBits());
			area = areaName;
			getCommon().sleepFor(3);
			bidSummaryPage.clickElementByText("Project Info°Información Del Proyecto", language);
			projectInfoPage.enterProjectInfo(language);
			getCommon().sleepFor(3);
			firstRun = false;
		} else {
			homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
			// homePage.scrollToElementByText(createdBid, createdBid, language);
			getCommon().sleepFor(3);
			homePage.clickElementByText(createdBid, language);
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
		for (int i = 0; i < 1; i++) {
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

	@Test(groups = { "mobile_regression", "android" }, description = "Verify email is actionable")
	public void a1verify_BidSummary_ProjectInfo_EmailIsActionable() throws Exception {
		bidSummaryPage.clickElementByElementName("some@where.com", language);
		getCommon().sleepFor(20);
		bidSummaryPage.elementExistsByText("Got it", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("From", language));
		bidSummaryPage.clickElementByTextIfExists("OK");
		// Assert.assertTrue(bidSummaryPage.elementExistsByText("memecont10@gmail.com",
		// language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("To", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("some@where.com", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify address is actionable")
	public void verify_BidSummary_ProjectInfo_AddressIsActionable() throws Exception {
		bidSummaryPage.clickElementByElementName("8302 Lincoln ln", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Directions", language));
	}

	@Test(groups = { "mobile_regression", "android" }, description = "Verify address is actionable")
	public void verify_BidSummary_ProjectInfo_PhoneIsActionable() throws Exception {
		bidSummaryPage.clickElementByElementName("505-465-9277", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("CANCEL", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("CALL", language));
		Assert.assertTrue(bidSummaryPage.elementExistsByText("SEND TEXT MESSAGE", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify email is actionable")
	public void verify_BidSummary_ProjectInfo_EmailIsActionableIOS() throws Exception {
		bidSummaryPage.elementClickByContainTextAndSpaceIOS("where", "some");// ("some@where.com", language);
		getCommon().sleepFor(2);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextValueAndSpaceIOS("make sure to set up your",
				"is not available"));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify address is actionable")
	public void verify_BidSummary_ProjectInfo_AddressIsActionableIOS() throws Exception {
		bidSummaryPage.elementClickByContainTextAndSpaceIOS("Lincoln ln", "8302");// ("some@where.com", language);
		getCommon().sleepFor(20);
		// bidSummaryPage.clickElementByText("8302 Lincoln ln", language);
		Assert.assertTrue(bidSummaryPage.elementExistsByText("Directions", language));
	}

	@Test(groups = { "mobile_regression", "ios" }, description = "Verify address is actionable")
	public void verify_BidSummary_ProjectInfo_PhoneIsActionableIOS() throws Exception {
		// bidSummaryPage.clickElementByText("505-465-9277", language);
		bidSummaryPage.elementClickByContainTextAndSpaceIOS("465", "9277");// ("some@where.com", language);
		getCommon().sleepFor(2);
		Assert.assertTrue(bidSummaryPage.elementExistsByContainTextValueAndSpaceIOS("Unable", "to text or call"));
	}
}