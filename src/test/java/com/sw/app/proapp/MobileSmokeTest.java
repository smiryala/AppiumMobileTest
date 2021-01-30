package com.sw.app.proapp;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Mobile Automation")
@Feature(value = "ProApp Smoke Tests")
public class MobileSmokeTest extends MobileCoreBaseTest {

	String proAppTestCase;
	String language;
	private LoginPage loginPage = null;
	private HomePage homePage = null;
	private AccountPage accountPage = null;
	private AppDataFromDB appDataFromDB = null;
	private Login login = null;
	private boolean isAndroid = false;
	private int counter=0;

	public MobileSmokeTest() {
		this.proAppTestCase = loadTestCaseIds.getProperty(this.getClass().getName());
	}

	@BeforeClass(alwaysRun = true)
	public void setQAEnv() {
		loginPage = new LoginPage(getCommon());
		homePage = new HomePage(getCommon());
		accountPage = new AccountPage(getCommon());
		ExtentTestManager.createTest("MobileSmokeTest");
		ExtentTestManager.createLabel("PROAPP MobileSmokeTests", ExtentColor.PURPLE);
		loginPage.clickSignIn();
		//homePage.clickProAppButton();
		language = loginPage.checkIfEnglishOrSpanish("SIGN IN°INICIAR SESIÓN", language);
		loginPage.languageBase=language;
		loginPage.clickElementByTextExact("SIGN IN°INICIAR SESIÓN", language);
		accountPage.clickAccountButton();
		accountPage.clickAccountSettingsButton();
		accountPage.verifyAndSetQADashboard();
		if (accountPage.getDriver() instanceof AndroidDriver) {
			isAndroid = true;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void before_MobileSmokeTest() {
		getCommon().setInsertResults(insertResults);
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		ArrayList<String> credentials=loginPage.getCredentials(proAppTestCase);
		counter++;
		if (!loginPage.elementExistsByText("ACCOUNT SUMMARY°RESUMEN DE CUENTA", language) && counter>1){
			loginPage.doLogin(credentials.get(0), credentials.get(1), language);
		}
		ExtentTestManager.getTest().log(Status.INFO, "Starting Script verifyHomepage");

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("this is to validate the PROAPP Order Creation functionality in Android and iOS Device")
	@Story("Verify the Order is created SW PRO APP")
	@Test(dataProvider = "verifyUserCreateOrderData", groups = {
			"mobile_smoke",
			"verifyUserCreateOrder"}, description = "Verify the Order is created in SW PRO APP", priority = 2)
	public void verifyUserCreateOrder(String productName, String color) throws Exception {
		ProAppCreateOrderTest proAppCreateOrderTest = new ProAppCreateOrderTest(getCommon(),proAppTestCase, language);
		productName = loginPage.getTextByLanguage("Resilience Exterior Acrylic Latex°Látex acrílico para exteriores Resilience", language);
		if (isAndroid) {
			proAppCreateOrderTest.verifyUserCreateOrderAndroid(productName, color);
		} else {
			proAppCreateOrderTest.verifyUserCreateOrderIOS(productName, color,language);
		}
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("this is to validate the PROAPP homepage functionality in Android and iOS Device")
	@Story("Verify the HomePage in SW PRO APP")
	@Test(groups = {
			"mobile_smoke",
			"verifyHomePage"}, description = "Verify the HomePage created in SW PRO APP", priority = 3)
	public void verifyHomePage() {

		ProAppHomePageTest proAppHomePageTest = new ProAppHomePageTest(getCommon(), language);
		if (isAndroid) {
			//restartAndroidApp();
			proAppHomePageTest.verifyHomePageAndroid();
		} else {
			proAppHomePageTest.verifyHomePageIOS();
		}
	}



	@Severity(SeverityLevel.NORMAL)
	@Description("this is to validate the PROAPP loginpage functionality in Android and iOS Device")
	@Story("Verify the loginpage in SW PRO APP")
	@Test(groups = {
			"mobile_smoke",
			"verifyLoginPage"}, description = "Verify the Login Page in SW PRO APP", priority = 1)
	public void verifyLoginPage() {
		ProAppLoginPageTest appLoginPageTest = new ProAppLoginPageTest(getCommon(),proAppTestCase, language);
		if (isAndroid) {
			//restartAndroidApp();
			appLoginPageTest.verifyLoginPageAndroid();
		} else {
			appLoginPageTest.verifyLoginPageIOS();
		}
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("this is to validate the PROAPP RegistrationPage functionality in Android and iOS Device")
	@Story("Verify the RegistrationPage in SW PRO APP-Android")
	@Test(groups = {
			"mobile_smoke",
			"verifyRegistrationPage"}, description = "Verify the Registration Page in SW PRO APP", priority = 5)
	public void verifyRegistrationPage() {
		ProAppRegistrationTest proAppRegistrationTest = new ProAppRegistrationTest(getCommon(), language);
		if (isAndroid) {
			//restartAndroidApp();
			proAppRegistrationTest.verifyRegistrationPageAndroid();
		} else {
			proAppRegistrationTest.verifyRegistrationPageIOS();
		}
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("this is to validate the PROAPP ProjectBid functionality in Android and iOS Device")
	@Story("Verify the ProjectBid in SW PRO APP-Android")
	@Test(groups = {"mobile_smoke",
			"verifyProjectBid"}, description = "Verify the ProjectBid Page in SW PRO APP", priority = 4)
	public void verifyProjectBid() {
		ProAppProjectBidTest proAppProjectBidTest = new ProAppProjectBidTest(getCommon(), language);
		try {
			if (isAndroid) {
				//restartAndroidApp();
				proAppProjectBidTest.verifyProjectBidAndroid();
			} else {
				proAppProjectBidTest.verifyProjectBidIOS();
			}
		} catch (Exception e) {
			Assert.assertTrue(false,
					"Failed in Project Bid Testing for more info" + e.getMessage());
		}
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("this is to validate the PROAPP StoreLocatorPage functionality in Android and iOS Device")
	@Story("Verify the StoreLocatorPage in SW PRO APP")
	@Test(groups = {
			"mobile_smoke",
			"verifyStoreLocatorPage"}, description = "Verify the Store Locator Page in SW PRO APP", priority = 5)
	public void verifyStoreLocatorPage() {

		ProAppStoreLocatorTest proAppStoreLocatorTest = new ProAppStoreLocatorTest(getCommon(), language);
		if (isAndroid) {
			//restartAndroidApp();
			proAppStoreLocatorTest.verifyStoreLocatorPageAndroid();
		} else {
			proAppStoreLocatorTest.verifyStoreLocatorPageIOS();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void after_MobileSmokeTest() {
		ConsoleLog.info("Executing after method");
		appDataFromDB.setResults(proAppTestCase);
		afterExecutionReports();
	}

	@DataProvider(name = "verifyUserCreateOrderData")
	private Object[][] verifyUserCreateOrderData() {
		return new Object[][]{{"Emerald Exterior Acrylic Latex Paint", "SW 6856"},};
	}

}
