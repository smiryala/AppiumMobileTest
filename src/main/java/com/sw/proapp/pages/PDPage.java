package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.reporting.ExtentTestManager;
import com.sw.proapp.locators.PDPageLocators;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class PDPage extends PDPageLocators {
    public PDPage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public PDPage doLogin() {
        return this;
    }

    @Step("checkOutProduct qty is {0} ")
    public PDPage checkOutProduct(int quantity) {
        return this;
    }

    public PDPage enterAndSelectColor(String color) {

        ExtentTestManager.getTest().log(Status.INFO, "enterAndSelectColor " + color);
        getCommon().sleepFor(2);
        progressBarLoading();
        if (!existsElement(getAddAColor()))
            scrollDown3();
        getCommon().clickAndVerifyIsTrue(getAddAColor(), "Unable to click on AddAColor Button");
//		getCommon().sleepFor(10);
        getCommon().clickAndVerifyIsTrue(getEnterColorNumber(), "Unable to click on EnterColorNumber Button");
        getCommon().typeAndVerifyIsTrue(getEnterColorNumber(), color, "Unable to send keys to the Enter Color Number field.");
        getCommon().sleepFor(10);
        if (getDriver() instanceof IOSDriver) {
        	getCommon().clickAndVerifyIsTrue(getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name=\""+color+"\"]")), "Unable to click on " + color);
        }else {
            getCommon().clickAndVerifyIsTrue(getDriver().findElements(By.xpath("//*[@text='" + color + "']")).get(0), "Unable to click on " + color);        	
        }
        return this;
    }

    @Step()
    public PDPage clickAddToCart() {
        getCommon().sleepFor(2);
        ExtentTestManager.getTest().log(Status.INFO, "clickAddToCart");
        getCommon().clickAndVerifyIsTrue(getAddToCartButton(), "Unable to click on AddToCart Button");
        return this;
    }

    @Step()
    public PDPage clickGoToCart() {
        getCommon().sleepFor(2);
        ExtentTestManager.getTest().log(Status.INFO, "clickGoToCart");
        getCommon().clickAndVerifyIsTrue(getGoToCartButton(), "Unable to click on CheckOut Button");
        return this;
    }

    @Step()
    public PDPage clickGoToCheckOut() {
        getCommon().sleepFor(5);
        //if (existsElement(getCheckoutOk())) {
        if (this.elementExistsByText("OK", this.languageBase)) {// existsElement(getCheckoutOk())) {
            ExtentTestManager.getTest().log(Status.INFO, "click OK");
            getCommon().clickAndVerifyIsTrue(getCheckoutOk(), "Unable to click OK.");
        }

//		getCommon().sleepFor(4);
        ExtentTestManager.getTest().log(Status.INFO, "clickGoToCheckOut");
        getCommon().clickAndVerifyIsTrue(getCheckoutButton(), "Unable to click on CheckOut Button");
        return this;
    }

    @Step()
    public PDPage clickSubmitOrder() {
        getCommon().sleepFor(2);
        ExtentTestManager.getTest().log(Status.INFO, "clickSubmitOrder");
        getCommon().clickAndVerifyIsTrue(getSubmitOrder(), "Unable to click on SubmitOrder Button");
        getCommon().sleepFor(5);
        if (getDriver() instanceof IOSDriver) {
            if (existsElement(getNotNow_IOS()))
                getCommon().clickAndVerifyIsTrue(getNotNow_IOS(), "Unable to click on NOT NOW Rating Button");
        }
        return this;
    }


    @Step()
    public PDPage clickDontShowAgain() {
        getCommon().sleepFor(5);
        if (existsElement(getDontShowAgain()))
            getCommon().clickAndVerifyIsTrue(getDontShowAgain(), "Unable to click on SubmitOrder Button");
        return this;
    }

    @Step("enterAndSelectQuantity qty {0}")
    public PDPage enterAndSelectQuantity(int qty) {
        scrollToElementByText("QUANTITY", "english");
        getCommon().sleepFor(5);
        for (int i = 0; i < qty; i++) {
            getCommon().clickAndVerifyIsTrue(getIncrementButton(),
                    "Unable to click on Increment Button");
        }

        return this;
    }

    @Step("selectProductInHomePage productName {0}")
    public PDPage selectProductInHomePage(String productName) {
        getCommon().sleepFor(5);
        getDriver().findElement(By.xpath(
                "//*[@id='productImageView' and (./preceding-sibling::* | ./following-sibling::*)[@text='"
                        + productName + "']]")).click();
        return this;
    }

    @Step("fillJobName jobName {0}")
    public PDPage fillJobName(String jobName) {
        getCommon().sleepFor(2);
        getCommon().typeAndVerifyIsTrue(getJobName(), jobName, "Unable to type in the field");

        return this;
    }
}