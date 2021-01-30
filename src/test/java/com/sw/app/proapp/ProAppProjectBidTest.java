package com.sw.app.proapp;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.bids.pages.BidNamePage;
import com.sw.proapp.bids.pages.BidsPage;
import com.sw.proapp.bids.pages.CreateNewBidPage;
import com.sw.proapp.bids.pages.NewAreaPricePage;
import com.sw.proapp.bids.pages.PricingMethodPage;
import com.sw.proapp.bids.pages.ProjectBidsHomePage;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import io.qameta.allure.Step;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;

public class ProAppProjectBidTest {

	private BidsPage bidsPage = null;
	private CreateNewBidPage createNewBidPage = null;
	String language = null;
	String bidName = null;
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private ProjectBidsHomePage projectBidsHomePage = null;
	private LoginPage loginPage = null;
	private PricingMethodPage pricingMethodPage = null;
	private NewAreaPricePage newAreaPricePage = null;
	private BidNamePage bidNamePage = null;

	public ProAppProjectBidTest(Common common,String language){
		createNewBidPage = new CreateNewBidPage(common);
		homePage = new HomePage(common);
		accountPage = new AccountPage(common);
		projectBidsHomePage = new ProjectBidsHomePage(common);
		loginPage = new LoginPage(common);
		pricingMethodPage = new PricingMethodPage(common);
		newAreaPricePage = new NewAreaPricePage(common);
		bidNamePage = new BidNamePage(common);
		bidsPage = new BidsPage(common);

		createNewBidPage.languageBase = language;
		homePage.languageBase = language;
		accountPage.languageBase = language;
		projectBidsHomePage.languageBase = language;
		loginPage.languageBase = language;
		pricingMethodPage.languageBase = language;
		newAreaPricePage.languageBase = language;
		bidNamePage.languageBase = language;
		bidsPage.languageBase = language;

	}

	/*@Step()
	public void verifyProjectBidAndroid() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyProjectBidAndroid");
		homePage.clickProAppButton();
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES",
				"SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(),
				"Create new bid button is not correct");

		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		homePage.deviceOrientationToPrtrait();
		homePage.scrollDown3();
		int randomNum = ThreadLocalRandom.current().nextInt(700, 50000 + 1);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		Assert.assertTrue(createNewBidPage.isLoogedInCreateBidPageDispalyed(),
				"Create New Bid page not displayed");
		bidName = "smokebid" + randomNum;
		createNewBidPage.inputBidName(bidName);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		homePage.scrollDown3();
		homePage.scrollDown3();
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		homePage.clickBackButton();
		homePage.clickBackButton();
		homePage.clickBackButton();
		homePage.clickProAppButton();
		//homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickViewAllBidsButton();
		String textToWaitOnNewArea = bidNamePage
				.getTextByLanguage("CREATED ON°CREADA EL", language);
		String bidNameExpected = bidsPage.getFirstBidOnList(textToWaitOnNewArea);
		System.out.println("bidNameExpected" + bidNameExpected);
		homePage.assertEquals(bidName, bidNameExpected, "NOT able to find the created BID Name");

	}

	@Step()
	public void verifyProjectBidIOS() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyProjectBidAndroid");
		homePage.clickProAppButton();
		language = loginPage.checkIfEnglishOrSpanish("SPECIAL OFFERS°OFERTAS ESPECIALES");
		homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES",
				"SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(),
				"Create new bid button is not correct");

		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		homePage.deviceOrientationToPrtrait();
		homePage.scrollDown3();
		int randomNum = ThreadLocalRandom.current().nextInt(700, 50000 + 1);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		Assert.assertTrue(createNewBidPage.isLoogedInCreateBidPageDispalyed(),
				"Create New Bid page not displayed");
		bidName = "smokebid" + randomNum;
		createNewBidPage.inputBidName(bidName);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		homePage.scrollDown3();
		homePage.scrollDown3();
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		homePage.clickBackButton();
		homePage.clickBackButton();
		homePage.clickBackButton();
		homePage.clickProAppButton();
		//homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		projectBidsHomePage.clickViewAllBidsButton();
		String textToWaitOnNewArea = bidNamePage
				.getTextByLanguage("CREATED ON°CREADA EL", language);
		String bidNameExpected = bidsPage.getFirstBidOnList(textToWaitOnNewArea);
		System.out.println("bidNameExpected" + bidNameExpected);
		homePage.assertEquals(bidName, bidNameExpected, "NOT able to find the created BID Name");
	}*/

	@Step()
	public void verifyProjectBidAndroid() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyProjectBidAndroid");
		language = loginPage.checkIfEnglishOrSpanish("ACCOUNT SUMMARY°RESUMEN DE CUENTA");
		//NOT NEEDED---homePage.clickProAppButton();

		homePage.clickElementByTextExact("Tools°Herramientas", language);

		//homePage.scrollToElementByText("VIEW ALL BIDS°VER COTIZACIONES", "SPECIAL OFFERS°OFERTAS ESPECIALES", language);
		homePage.androidWaitForElementByText("CREATE NEW BID°CREAR COTIZACIÓN", "unable to wait for create new button");
		Assert.assertTrue(projectBidsHomePage.getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");
		Assert.assertTrue(projectBidsHomePage.isCreateNewBidButtonCorrect(), "Create new bid button is not correct");

		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		homePage.deviceOrientationToPrtrait();
		homePage.scrollDown3();
		int randomNum = ThreadLocalRandom.current().nextInt(700, 50000 + 1);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		Assert.assertTrue(createNewBidPage.isLoogedInCreateBidPageDispalyed(), "Create New Bid page not displayed");
		bidName = "smokebid" + randomNum;
		createNewBidPage.inputBidName(bidName);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		homePage.scrollDown3();
		homePage.scrollDown3();
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);
		homePage.clickBackButton();
		homePage.clickBackButton();
		homePage.clickBackButton();
		//homePage.clickProAppButton();
		homePage.clickElementByTextExact("Tools°Herramientas", language);

		projectBidsHomePage.clickViewAllBidsButton();
		homePage.clickElementByTextExact("VIEW ALL BIDS°VER COTIZACIONES", language);
		String textToWaitOnNewArea = bidNamePage.getTextByLanguage("CREATED ON°CREADA EL", language);
		String bidNameExpected = bidsPage.getFirstBidOnList(textToWaitOnNewArea);
		System.out.println("bidNameExpected" + bidNameExpected);
		homePage.assertEquals(bidName, bidNameExpected, "NOT able to find the created BID Name");

	}

	@Step()
	public void verifyProjectBidIOS() throws Exception {
		ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyProjectBidAndroid");
		//--NOT NEEDED homePage.clickProAppButton();
		language = loginPage.checkIfEnglishOrSpanish("ACCOUNT SUMMARY°RESUMEN DE CUENTA");

		String toolsLabel = homePage.getTextByLanguage("Tools°Herramientas", language);
		homePage.elementClickByContainTextAndSpaceIOS(toolsLabel, toolsLabel);
		String createBidButtonLabel = projectBidsHomePage.getTextByLanguage("CREATE NEW BID°CREAR COTIZACIÓN",
				language);
		Assert.assertTrue(
				projectBidsHomePage.elementExistsByContainTextAndSpaceIOS(createBidButtonLabel, createBidButtonLabel), // getCreateNewBidButton().isDisplayed(),
				"Create new bid button is not displayed");

		projectBidsHomePage.clickElementByText("CREATE NEW BID°CREAR COTIZACIÓN", language);
		// homePage.deviceOrientationToPrtrait();
		homePage.scrollDown3();
		int randomNum = ThreadLocalRandom.current().nextInt(700, 50000 + 1);
		pricingMethodPage.clickElementByText("Area Subtotals°Subtotales de área", language);
		Assert.assertTrue(createNewBidPage.isLoogedInCreateBidPageDispalyed(), "Create New Bid page not displayed");
		bidName = "smokebid" + randomNum;
		createNewBidPage.inputBidName(bidName);
		createNewBidPage.clickElementByText("SAVE & CONTINUE°GUARDAR Y CONTINUAR", language);
		homePage.scrollDown3();
		homePage.scrollDown3();
		newAreaPricePage.clickElementByElementName("SAVE°GUARDAR", language);

		homePage.assertTrue(homePage.elementExistsByText("PROJECT BIDS°COTIZACIONES", language), "PROJECT BIDS TITLE");
		homePage.assertTrue(homePage.elementExistsByText(bidName, "english"), "PROJECT BIDS Created");
	}

}