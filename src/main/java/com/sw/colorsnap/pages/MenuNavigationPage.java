package com.sw.colorsnap.pages;


import com.sw.colorsnap.locators.MenuNavigationPageLocators;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

public class MenuNavigationPage extends MenuNavigationPageLocators {

    public MenuNavigationPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step("Click on Explore Menu")
    public MenuNavigationPage clickOnExplore() {
        getCommon().clickAndVerifyIsTrue(exploreMenu, "Unable to click exploreMenu");
        return this;
    }

    @Step("Click on Resources Menu")
    public MenuNavigationPage clickOnResources() {
        getCommon().clickAndVerifyIsTrue(resourcesMenu, "Unable to click Resources Menu");
        return this;
    }

    @Step("Click on Paint Menu")
    public MenuNavigationPage clickOnPaint() {
        getCommon().clickAndVerifyIsTrue(paintMenu, "Unable to click Paint Menu");
        return this;
    }


}