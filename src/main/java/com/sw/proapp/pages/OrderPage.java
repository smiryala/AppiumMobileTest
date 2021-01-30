package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.data.OrderData;
import com.sw.proapp.locators.OrderPageLocators;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends OrderPageLocators {

    public OrderPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @Step()
    public OrderPage clickContinueShopping() {
        ExtentTestManager.getTest().log(Status.INFO, "clickContinueShopping");
        getCommon().clickAndVerifyIsTrue(getContinue_shopping(),
                "Unable to click on ContinueShopping Button");
        return this;
    }

    @Step()
    public OrderPage clickCloseButtonInSearch() {
        ExtentTestManager.getTest().log(Status.INFO, "clickCloseButtonInSearch");
        getCommon().clickAndVerifyIsTrue(getCloseInSerachFeild(),
                "Unable to click on ContinueShopping Button");
        return this;
    }

    @Step()
    public String getOrderNumberText() {
        String orderNo = null;
        if (getCommon().getDriver() instanceof IOSDriver) {
            androidWaitForElementByText("get source", "get source");
            WebElement element = getDriver()
                    .findElements(MobileBy.iOSNsPredicateString(OrderData.ORDER_NUMBER_VIEW_IOS))
                    .get(0);
            orderNo = getCommon().getAttribute(element, "name");
            int finalOrderNoIndex = orderNo.indexOf("# ");
            String finalOrderNo = orderNo.substring(finalOrderNoIndex + 2, orderNo.length());
            return finalOrderNo.trim();
        } else {
            orderNo = getCommon().getText(getOrderNumberView(), "Unable to get OrderNumber Text");

        }
        ExtentTestManager.getTest().log(Status.INFO, "Order No: " + orderNo);
        return orderNo.replace("Order Number - ", "");

    }

    @Step()
    public OrderPage getEmailConfNumberText() {

        String emailOrder = null;
        if (getCommon().getDriver() instanceof IOSDriver) {
            WebElement element = getDriver().findElement(
                    MobileBy.iOSNsPredicateString(OrderData.EMAIL_ORDER_CONFIRMATION_VIEW_IOS));
            emailOrder = getCommon().getAttribute(element, "value");
        } else {
            emailOrder = getCommon()
                    .getText(getEmailOrderConfView(), "Unable to get EmailConfNumberText ");

        }
        //System.out.println("email Order "+emailOrder);
        ExtentTestManager.getTest().log(Status.INFO, "Order Email Sent text: " + emailOrder);
        return this;
    }

    @Step()
    public OrderPage clickRatingButton() {
        ExtentTestManager.getTest().log(Status.INFO, "clickRatingButton");
        getCommon().sleepFor(5);
        if (existsElement(getNotNowRatingIOS()))
            getCommon().clickAndVerifyIsTrue(getNotNowRatingIOS(), "Unable to click on ContinueShopping Button");
        return this;
    }

    @Step()
    public String getOrderAndEmailInfo() {
        getCommon().sleepFor(3);
        return getCommon().getText(getOrderNumberView(), "Unable to get OrderNumber Text");
    }
}