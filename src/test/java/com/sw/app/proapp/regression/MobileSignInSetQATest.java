package com.sw.app.proapp.regression;

import com.aventstack.extentreports.Status;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.core.testsuites.MobileCoreBaseTest;
import com.sw.proapp.pages.AccountPage;
import com.sw.proapp.pages.HomePage;
import com.sw.proapp.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MobileSignInSetQATest extends MobileCoreBaseTest {

    private LoginPage loginPage = null;
    private HomePage homePage = null;
    private AccountPage accountPage = null;

    public MobileSignInSetQATest() {
    }

    @BeforeMethod(alwaysRun = true)
    public void before_MobileSignInSetQATest() {
        loginPage = new LoginPage(getCommon());
        homePage = new HomePage(getCommon());
        accountPage = new AccountPage(getCommon());
        ExtentTestManager.getTest().log(Status.INFO, "Executing Script " + this.getClass().getName());
    }

    @Test(groups = {"regression"}, description = "Verify the Environment set to QA in SW PRO APP")
    public void verifyUserSignInSetQAEnv() {

        try {
            ExtentTestManager.getTest().log(Status.INFO, "verifyUserSignIn");
            loginPage.clickSignIn();
            homePage.clickProAppButton();
            accountPage.clickAccountButton();
            accountPage.clickAccountSettingsButton();
            accountPage.verifyAndSetQADashboard();
        } catch (Exception exp) {
            ExtentTestManager.getTest().log(Status.FAIL, "Error in Executing verifyUserCreateOrder in IOS");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void after_MobileSignInSetQATest() {
        System.out.println("Executing after method");
    }
}