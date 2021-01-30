package com.sw.proapp.bids.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.data.ProjectPricePageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProjectPricePageLocators extends MobileBasePage {

    public ProjectPricePageLocators(Common common) {
        super(common);
    }
    
    @AndroidFindBy(id = ProjectPricePageData.UNIT_DROPDOWN)
    protected WebElement projectUnitsDropDown;
}