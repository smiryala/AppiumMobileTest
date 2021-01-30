package com.sw.app.colorsnap;

import com.aventstack.extentreports.Status;
import com.sw.colorsnap.pages.MenuNavigationPage;
import com.sw.colorsnap.pages.ResourcesPage;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import io.qameta.allure.Step;

public class ColorSnapResourcesPageTest {

    private ResourcesPage resourcesPage = null;
    private MenuNavigationPage menuNavigationPage = null;

    public ColorSnapResourcesPageTest(Common common) {
        resourcesPage = new ResourcesPage(common);
        menuNavigationPage = new MenuNavigationPage(common);
    }

    @Step("verify Resource page Links")
    public void verifyResourcesPageLinks() {
        ExtentTestManager.getTest().log(Status.INFO, " verify Resources page elements");
        resourcesPage.assertEquals(resourcesPage.getmyIdeas(), "My Ideas", "My Ideas Link not displayed");
        resourcesPage.assertEquals(resourcesPage.getpaintCalc(), "Paint Calculator", "Paint Calculator Link not displayed");
        resourcesPage.assertEquals(resourcesPage.getstoreFinder(), "Find a Store", "Find a Store Link not displayed");
        resourcesPage.assertEquals(resourcesPage.getLogin(), "Log In To mySW", "Log In To mySW Link not displayed");
        resourcesPage.assertEquals(resourcesPage.getTellUsLink(), "Tell Us What You Think", "Tell Us What You Think link not displayed");
        resourcesPage.assertEquals(resourcesPage.getAbout(), "About", "About Link not displayed");
        resourcesPage.assertEquals(resourcesPage.getLearnMore(), "Learn More", "Learn More Link not displayed");
    }

    @Step("Navigate to Resources Menu")
    public void navigateToResources() {
        ExtentTestManager.getTest().log(Status.INFO, " Navigate t0  Resources menu");
        menuNavigationPage.clickOnResources();
        resourcesPage.assertEquals(resourcesPage.getScreenTitle(), "Resources", "Resources Page Title is not loaded");
    }

}