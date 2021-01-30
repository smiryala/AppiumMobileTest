package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.MenuNavigationPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MenuNavigationPageLocators extends MobileBasePage {

    public MenuNavigationPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(iOSClassChain = MenuNavigationPageData.NAVIGATE_PAINT_IOS)
    @AndroidFindBy(id = MenuNavigationPageData.NAVIGATE_PAINT)
    protected WebElement paintMenu;

    @iOSXCUITFindBy(iOSClassChain = MenuNavigationPageData.NAVIGATE_RESOURCES_IOS)
    @AndroidFindBy(id = MenuNavigationPageData.NAVIGATE_RESOURCES)
    protected WebElement resourcesMenu;

    @iOSXCUITFindBy(iOSClassChain = MenuNavigationPageData.NAVIGATE_EXPLORE_IOS)
    @AndroidFindBy(id = MenuNavigationPageData.NAVIGATE_EXPLORE)
    protected WebElement exploreMenu;

}