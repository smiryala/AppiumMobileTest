package com.sw.colorsnap.locators;

import com.sw.colorsnap.data.RegisterPageData;
import com.sw.proapp.pages.MobileBasePage;
import com.sw.core.helpers.Common;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegisterPageLocators extends MobileBasePage {

    public RegisterPageLocators(Common common) {
        super(common);
    }

    @AndroidFindBy(id = RegisterPageData.HEADER)
    protected WebElement header;

    @AndroidFindBy(xpath = RegisterPageData.BACK_BUTTON)
    protected WebElement backButton;

    @AndroidFindBy(id = RegisterPageData.CONTENT)
    protected WebElement content;

    @AndroidFindBy(id = RegisterPageData.REGISTER_BUTTON)
    protected WebElement registerButton;

    public WebElement getHeader() {
        return header;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getContent() {
        return content;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}