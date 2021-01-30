package com.sw.proapp.contentValidation.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.contentValidation.data.ContentValidationData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ContentValidationLocators extends MobileBasePage {

    public ContentValidationLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(xpath = ContentValidationData.FIRST_RECYCLER_VIEW_ANDROID)
    protected MobileElement firstRecyclerView;

    @AndroidFindBy(xpath = ContentValidationData.BIDS_SCROLL_CONTENT_ANDROID)
    protected MobileElement bidsScrollable;

    @AndroidFindBy(xpath = ContentValidationData.STORE_LOCATOR_STORE_LIST_ANDROID)
    protected MobileElement storeLocatorStoreListScrollable;
}
