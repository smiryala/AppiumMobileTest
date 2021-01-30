package com.sw.colorsnap.pages;

import com.sw.colorsnap.locators.ResourcesPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;


public class ResourcesPage extends ResourcesPageLocators {

    public ResourcesPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }
    @Step("Validate My Ideas Link")
    public String getmyIdeas() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(myIdeas, "My Ideas link is not visible");
        } else {
            return getCommon().getText(myIdeas, "My Ideas link is not visible");
        }
    }

    @Step("Validate Paint Calculator Link")
    public String getpaintCalc() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(paintCalc, "Paint Calculator link is not available");
        } else {
            return getCommon().getText(paintCalc, "Paint Calculator link not avaialble");
        }
    }

    @Step("Validate Store Finder Link")
    public String getstoreFinder() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(storeFinder, "Store Finder Link is not available");
        } else {
            return getCommon().getText(storeFinder, "Store Finder Link Tile not avaialble");
        }
    }

    @Step("Validate Login Link")
    public String getLogin() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(login, "Login link is not displayed");
        } else {
            return getCommon().getText(login, "Login link is not displayed");
        }
    }

    @Step("Validate Tell Us What you Think Link")
    public String getTellUsLink() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(tellUs, "Tell us what you think link is not displayed");
        } else {
            return getCommon().getText(tellUs, "Tell us what you think link is not displayed");
        }
    }

    @Step("Validate About Link")
    public String getAbout() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(about, "About link is not displayed");
        } else {
            return getCommon().getText(about, "About link is not displayed");
        }
    }

    @Step("Validate LearnMore Link")
    public String getLearnMore() {
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(learnMore, "Learn More link is not displayed");
        } else {
            return getCommon().getText(learnMore, "Learn More link is not displayed");
        }
    }

    @Step("Validate Resources Page Title")
    public String getScreenTitle() {
        getCommon().getWaitUntil().visibilityOfElements(screenTitle,"Resource Title is not visible");
        if (getDriver() instanceof IOSDriver) {
            return getCommon().getText(screenTitle, "Resource Title is not displayed");
        } else {
            return getCommon().getText(screenTitle, "Resource Title is not displayed");
        }
    }


    @Step()
    public boolean isLogintoSWisNotDispalyed() {
        try {
            getLoginSW();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Step()
    public boolean isLogOutDispalyed() {
        getCommon().getWaitUntil().visibilityOfElements(getLogOut(),"LogOut Title is not visible");
        if (!getCommon().isDisplayed(getLogOut()))
            return false;
        else
            return true;
    }

    @Step()
    public void clickLogOut() {
        if (existsElement(getLogOut())) {
            getCommon().clickAndVerifyIsTrue(getLogOut(),
                    "Unable to click LogOut");
        }
    }

    @Step()
    public void clickLogintoSW() {
        if (existsElement(getLoginSW())) {
            getCommon().clickAndVerifyIsTrue(getLoginSW(),
                    "Unable to click LoginToSW");
        }
    }

    @Step
    public void clickWhyRegister() {
        if (existsElement(getWhyRegister())) {
            getCommon().clickAndVerifyIsTrue(getWhyRegister(),
                    "Unable to click Why Register.");
        }
    }

    @Step()
    public void clickOnAbout() {
        if (existsElement(about)) {
            getCommon().clickAndVerifyIsTrue(about,
                    "Unable to click About");
        }
    }

    @Step("Click on Find a Store")
    public ResourcesPage clickFindAStore() {
        getCommon().clickAndVerifyIsTrue(storeFinder, "Unable to Click on FInd a Store Link");
        return this;
    }

    @Step("Click on LearnMore Link")
    public ResourcesPage clickLearnMore() {
        getCommon().clickAndVerifyIsTrue(learnMore, "Unable to Click on LearnMore Link");
        return this;
    }
    @Step("Click on About")
    public ResourcesPage clickAbout() {
        getCommon().clickAndVerifyIsTrue(about, "Unable to Click on About Link");
        return this;
    }
    @Step("Click on Paint Calculator Link")
    public ResourcesPage clickPaintCalc() {
        getCommon().clickAndVerifyIsTrue(paintCalc, "Unable to Click on Paint Calculator Link");
        return this;
    }
    @Step("Click on My Ideas Link")
    public ResourcesPage clickMyIdeas() {
        getCommon().clickAndVerifyIsTrue(myIdeas, "Unable to Click on My Ideas Link");
        return this;
    }
}