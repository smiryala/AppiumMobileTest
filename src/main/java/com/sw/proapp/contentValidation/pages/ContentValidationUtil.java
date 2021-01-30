package com.sw.proapp.contentValidation.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.contentValidation.locators.ContentValidationLocators;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ContentValidationUtil extends ContentValidationLocators {

    public ContentValidationUtil(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public MobileElement getFirstRecyclerView() {
        return firstRecyclerView;
    }

    public MobileElement getBidsScrollable() {
        return bidsScrollable;
    }

    public MobileElement getStoreLocatorStoreListScrollable() {
        return storeLocatorStoreListScrollable;
    }

}
