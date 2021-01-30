package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.JavaScriptUtil;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.locators.HomePageLocators;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends HomePageLocators {

    public HomePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public HomePage clickProAppButton() {
//        ExtentTestManager.getTest().log(Status.INFO, "clickProAppButton");
        getCommon().sleepFor(2);
        getCommon().clickAndVerifyIsTrue(getProAppHomeButton(), "Unable to click ProAppHomeButton.");
        return this;
    }

    @Step("search and select product {0}")
    public HomePage clickSearchButton(String productName) {
        ExtentTestManager.getTest().log(Status.INFO, "clickSearchButton with productName " + productName);
        getCommon().clickAndVerifyIsTrue(getSearchButton(), "Unable to click Search Button.");
        if (getDriver() instanceof IOSDriver) {
            ExtentTestManager.getTest().log(Status.INFO, "clickSearchButton with productName " + productName);
            String label=getTextByLanguage("Search to start an order°Buscar para iniciar un pedido", languageBase);

            //enterTextToElementByContainsTextForIOS(label, productName.substring(0, productName.length() - 1), true);
            enterTextToElementByTextLabel(label,
                    productName.substring(0, productName.length() - 1));
            getCommon().sleepFor(3);
            clickElementByText(productName, "english");
            getCommon().sleepFor(5);
            //getCommon().clickAndVerifyIsTrue(getDriver().findElement(By.xpath("//*[@text='" + productName + "']")), "Unable to click on " + productName);
        } else {
            //driver.findElement(By.xpath("//*[@id='search_src_text']")).sendKeys(productName);
            String label = getTextByLanguage("Search to start an order°Busca para iniciar un pedido", languageBase);
            enterTextToElementByTextLabel(label, productName.substring(0, productName.length() - 1));
            getCommon().sleepFor(3);
            clickElementByText(productName, "english");
            clickElementByTextValue(productName, "english");
            getCommon().sleepFor(5);
            //*[@text='Emerald Exterior Acrylic Latex Paint']
        }
        return this;
    }

    @Step()
    public HomePage clickFrequentlyPurchasedExpand() {
        ExtentTestManager.getTest().log(Status.INFO, "clickFrequentlyPurchasedExpand");
        JavaScriptUtil.mobileSwipeToLeft(getDriver(), getFrequentlyPurchasedSection());
        JavaScriptUtil.mobileSwipeToLeft(getDriver(), getFrequentlyPurchasedSection());
        getCommon().clickAndVerifyIsTrue(getFrequentlyPurchasedExpand(), "Unable to click on Frequently Purchased Expand Button");
        return this;
    }

    @Step()
    public HomePage selectFrequentlyPurchasedItemIndex(int itemIndex) {
        ExtentTestManager.getTest().log(Status.INFO, "click Items");
        getCommon().clickAndVerifyIsTrue(getFrequentlyPurchasedItems().get(itemIndex), "Unable to click on Frequently Purchased Item");
        return this;
    }

    @Step()
    public HomePage clickCart() {
        ExtentTestManager.getTest().log(Status.INFO, "click Cart");
        getCommon().clickAndVerifyIsTrue(getCart(), "Unable to click on Cart");
        return this;
    }

    @Step()
    public HomePage emptyCart() throws IOException {
        clickCart();
        CartPage cartPage = new CartPage(getCommon(), this.languageBase);
        getCommon().sleepFor(2);
        Boolean empty = true;
        if (getDriver() instanceof IOSDriver) {
            empty=cartPage.elementClickByContainTextAndSpaceIOS("(0)", "(0)");
        }
        else {
            empty=cartPage.elementExistsByText("(0)", "english");
        }
        if (empty) {
            ExtentTestManager.getTest().log(Status.INFO, "0 Items in Cart");
            if (getDriver() instanceof IOSDriver) {
                cartPage.clickElementByTextExact("close", "english");
                cartPage.clickElementByTextExact("cerrar", "english");
            }
            else {
                cartPage.clickBackButton();
            }
            return this;
        }else {
            ExtentTestManager.getTest().log(Status.INFO, "Removing the Existing Cart Items");
            if (getDriver() instanceof IOSDriver) {
                cartPage.removeItemsForIOS();
            } else {
                cartPage.removeItems();
            }
        }

        return this;
    }

    @Step()
    public String emptyCartMessage() {
        ExtentTestManager.getTest().log(Status.INFO, "get Cart Message");
        return getRemoveMessage().getText();
    }

    @Step()
    public HomePage clickMore() {
        ExtentTestManager.getTest().log(Status.INFO, "click More");
        getCommon().clickAndVerifyIsTrue(getFrequentlyPurchasedMore(), "Unable to click on More");
        return this;
    }

    @Step()
    public boolean isLoggedInHomepageDispalyed() {
        getCommon().sleepFor(10);
       // System.out.println("PageSource "+getCommon().getDriver().getPageSource().toLowerCase());
        String accountSummary= getTextByLanguage("account summary°resumen de cuenta", languageBase);
        return getCommon().getDriver().getPageSource().toLowerCase().contains(accountSummary);
    }

    @Step()
    public boolean isSherwinLogoDisplayed() {
        return (getCommon().isDisplayed(proSherwinLogo) && (sherwinLogo().toLowerCase().equals("SHERWIN‑WILLIAMS® PRO".toLowerCase()) || sherwinLogo().toLowerCase().equals("SHERWIN-WILLIAMS® PRO".toLowerCase())));
    }

    @Step()
    public String sherwinLogo() {
        return getCommon().getText(proSherwinLogo, "Unable to get text");
    }

    @Step()
    public List<WebElement> getProAdvantagesSections() {
        return proAdvantagesSections;
    }

    @Step()
    public HomePage clickOnCancelSearch() {
        getCommon().clickAndVerifyIsTrue(search_cancel, "Unable to click the cancel button");
        return this;
    }

    @Step()
    public HomePage clickStores() {
        getCommon().clickAndVerifyIsTrue(find_store, "Unable to click the store button");
        return this;
    }

    @Step()
    public HomePage clickAcceptTermsOfUse() {
        getCommon().sleepFor(2);
        if (getCommon().isDisplayed(acceptTermsOfUse)) {
            getCommon().clickAndVerifyIsTrue(acceptTermsOfUse, "Unable to click accept terms of use button");
        }
        return this;
    }

    @Step()
    public HomePage clickNotificationsRemindMeLater() {
        getCommon().sleepFor(2);
        if (getCommon().isDisplayed(notificationsRemindMeLater)) {
            getCommon().clickAndVerifyIsTrue(notificationsRemindMeLater, "Unable to click remind me later for notifications");
        }
        return this;
    }

    @Step()
    public HomePage clickFirstProduct() {
        getCommon().click(firstFrequentlyPurchasedItem, "Unable to click first frequently purchased item");
        sleepFor(5);
        return this;
    }

}