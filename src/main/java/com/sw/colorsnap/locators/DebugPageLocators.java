package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.DebugPageData;
import com.sw.core.helpers.Common;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DebugPageLocators extends MobileBasePage {

    @iOSXCUITFindBy(xpath = DebugPageData.DEBUG_SETUPSERVER)
    protected WebElement setupServer;

    public DebugPageLocators(Common common) {
        super(common);
    }

    public WebElement getSetupServer() {
        return setupServer;
    }
}