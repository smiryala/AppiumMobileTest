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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
public class BidUpdateStatusTest extends MobileCoreBaseTest {
	private PricingMethodPage pricingMethodPage = null;
	private String area = null;
	private String bidName = "";
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
	public BidUpdateStatusTest() {
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
		if(homePage.checkFirstRun()) {
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
		if (loginPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		int randomNum = ThreadLocalRandom.current().nextInt(678, 456768754 + 1);
		// createNewBidPage.enterTextToElementByText("Bid Name","qa"+randomNum);
		createNewBidPage.inputBidName("qa" + randomNum);
		bidName="qa" + randomNum;
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
	}
	@AfterClass(alwaysRun = true)
	public void after_NewAreaPricePageTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°OFERTAS ESPECIALES", language);
		if (loginPage.elementExistsByText("SIGN IN", language)) {
			loginPage.doLogin(login.getUserName(), login.getPassword());
			homePage.clickAcceptTermsOfUse();
			homePage.clickNotificationsRemindMeLater();
		}
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		homePage.clickElementByText("VIEW ALL BIDS°VER COTIZACIONES", language);
		for (int i = 0; i < 1; i++) {
			if (getCommon().getDriver() instanceof IOSDriver) {
				newAreaPricePage.clickLastElementByTextIOSANDROID("CREATED ON°CREADO:", "moreIcon", language, true);
				getCommon().sleepFor(3);
				newAreaPricePage.clickElementByElementName("Delete°Borrar", language);
				getCommon().sleepFor(3);
				newAreaPricePage.elementClickByContainTextAndSpaceIOS(", Delete", "Yes");
				getCommon().sleepFor(3);
			}
			else {
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
	}
	@Test(groups = { "mobile_regression", "android" }, description = "Verify bid status updated")
	public void verifyUpdateBidStatusSavedSuccessfullyAndroid()
			throws Exception {
		newAreaPricePage.clickElementByText("Status: Drafts", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByText("Accepted°Aceptadas", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByText("Status: Accepted", language));
		newAreaTasksPage.scrollToElementByText("PREVIEW", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		getCommon().sleepFor(3);
		//bidName
		//Assert.assertTalse(newAreaPricePage.elementExistsByContainTextAndSpaceIOS(bidName, bidName));
		newAreaPricePage.clickElementByText("View All°Ver todas", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByText("Accepted°Aceptadas", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByText(bidName, language));
	}
	@Test(groups = { "mobile_regression", "ios" }, description = "Verify bid status updated")
	public void verifyUpdateBidStatusSavedSuccessfullyIOS()
			throws Exception {
		newAreaPricePage.elementClickByContainTextAndSpaceIOS(": Drafts", "Status");
		getCommon().sleepFor(3);
		newAreaPricePage.selectFromIosWheelerByText("Accepted°Aceptadas", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS("Status: Accepted", "Status: Accepted"));
		newAreaTasksPage.scrollToElementByText("PREVIEW", language);
		getCommon().sleepFor(3);
		newAreaPricePage.clickElementByText("SAVE°GUARDAR", language);
		getCommon().sleepFor(3);
		//bidName
		//Assert.assertTalse(newAreaPricePage.elementExistsByContainTextAndSpaceIOS(bidName, bidName));
		newAreaPricePage.clickElementByText("View All°Ver todas", language);
		getCommon().sleepFor(3);
		newAreaPricePage.selectFromIosWheelerByText("Accepted°Aceptadas", language);
		getCommon().sleepFor(3);
		Assert.assertTrue(newAreaPricePage.elementExistsByContainTextAndSpaceIOS(bidName, bidName));
}
}
