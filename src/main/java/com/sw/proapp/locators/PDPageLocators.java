package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.PDPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class PDPageLocators extends MobileBasePage {

    public PDPageLocators(Common common) {
        super(common);
    }

    public static final String PRODUCT_DETAILS_HOME_PAGE = "//*[@text='PRODUCT DETAILS']";

    @iOSXCUITFindBy(accessibility = PDPageData.ADD_TO_CART_BUTTON_IOS)
    @AndroidFindBy(id = PDPageData.ADD_TO_CART_BUTTON)
    protected WebElement addToCartButton;

    @iOSXCUITFindBy(accessibility = PDPageData.GO_TO_CHECKOUT_BUTTON_IOS)
    @AndroidFindBy(id = PDPageData.GO_TO_CHECKOUT_BUTTON)
    protected WebElement goToCartButton;

    @iOSXCUITFindBy(accessibility = PDPageData.CHECKOUT_BUTTON_IOS)
    @AndroidFindBy(id = PDPageData.CHECKOUT_BUTTON)
    protected WebElement checkoutButton;

    @iOSXCUITFindBy(xpath = PDPageData.JOB_NAME_IOS)
    @AndroidFindBy(xpath = PDPageData.JOB_NAME)
    protected WebElement jobName;

    @iOSXCUITFindBy(xpath = PDPageData.PRODUCT_DETAILS_HOME_PAGE)
    @AndroidFindBy(xpath = PDPageData.PRODUCT_DETAILS_HOME_PAGE)
    protected WebElement pdhomePage;

    @iOSXCUITFindBy(xpath = PDPageData.GO_TO_CHECKOUT_POP_UP)
    @AndroidFindBy(xpath = PDPageData.GO_TO_CHECKOUT_POP_UP)
    protected WebElement goToCartPopUP;

    @iOSXCUITFindBy(xpath = PDPageData.ADD_A_COLOR_IOS)
    @AndroidFindBy(xpath = PDPageData.ADD_A_COLOR)
    protected WebElement addAColor;

    @iOSXCUITFindBy(xpath = PDPageData.ENTER_COLOR_NUMBER_IOS)
    @AndroidFindBy(xpath = PDPageData.ENTER_COLOR_NUMBER)
    protected WebElement enterColorNumber;

    @iOSXCUITFindBy(xpath = PDPageData.ENTER_QUANTITY_IOS)
    @AndroidFindBy(xpath = PDPageData.ENTER_QUANTITY)
    protected WebElement enterQuantity;

    @iOSXCUITFindBy(accessibility = PDPageData.INCREMENT_BUTTON_IOS)
    @AndroidFindBy(xpath = PDPageData.INCREMENT_BUTTON)
    protected WebElement incrementButton;

    @iOSXCUITFindBy(xpath = PDPageData.DECREMENT_BUTTON)
    @AndroidFindBy(xpath = PDPageData.DECREMENT_BUTTON)
    protected WebElement decrementButton;

    @iOSXCUITFindBy(accessibility = PDPageData.SUBMIT_ORDER_IOS)
    @AndroidFindBy(id = PDPageData.SUBMIT_ORDER)
    protected WebElement submitOrder;

    @iOSXCUITFindBy(xpath = PDPageData.CHECKOUT_OK_IOS)
    @AndroidFindBy(xpath = PDPageData.CHECKOUT_OK)
    protected WebElement checkoutOK;

    @iOSXCUITFindBy(xpath = PDPageData.DONT_SHOW_AGAIN)
    @AndroidFindBy(xpath = PDPageData.DONT_SHOW_AGAIN)
    protected WebElement dontShowAgain;

    @iOSXCUITFindBy(accessibility = PDPageData.NOT_NOW_IOS)
    @AndroidFindBy(xpath = PDPageData.NOT_NOW_IOS)
    protected WebElement notNow_IOS;

    public WebElement getIncrementButton() {
        return incrementButton;
    }

    public void setIncrementButton(WebElement incrementButton) {
        this.incrementButton = incrementButton;
    }

    public WebElement getDecrementButton() {
        return decrementButton;
    }

    public void setDecrementButton(WebElement decrementButton) {
        this.decrementButton = decrementButton;
    }

    public WebElement getNotNow_IOS() {
        return notNow_IOS;
    }

    public void setNotNow_IOS(WebElement notNow_IOS) {
        this.notNow_IOS = notNow_IOS;
    }


    /**
     * @return the dontShowAgain
     */
    public WebElement getDontShowAgain() {
        return dontShowAgain;
    }


    /**
     * @param dontShowAgain the dontShowAgain to set
     */
    public void setDontShowAgain(WebElement dontShowAgain) {
        this.dontShowAgain = dontShowAgain;
    }


    /**
     * @return the submitOrder
     */
    public WebElement getSubmitOrder() {
        return submitOrder;
    }


    /**
     * @param submitOrder the submitOrder to set
     */
    public void setSubmitOrder(WebElement submitOrder) {
        this.submitOrder = submitOrder;
    }


    /**
     * @return the enterQuantity
     */
    public WebElement getEnterQuantity() {
        return enterQuantity;
    }


    /**
     * @param enterQuantity the enterQuantity to set
     */
    public void setEnterQuantity(WebElement enterQuantity) {
        this.enterQuantity = enterQuantity;
    }


    /**
     * @return the enterColorNumber
     */
    public WebElement getEnterColorNumber() {
        return enterColorNumber;
    }


    /**
     * @param enterColorNumber the enterColorNumber to set
     */
    public void setEnterColorNumber(WebElement enterColorNumber) {
        this.enterColorNumber = enterColorNumber;
    }


    /**
     * @return the addToCartButton
     */
    public WebElement getAddToCartButton() {
        return addToCartButton;
    }


    /**
     * @param addToCartButton the addToCartButton to set
     */
    public void setAddToCartButton(WebElement addToCartButton) {
        this.addToCartButton = addToCartButton;
    }


    /**
     * @return the goToCartButton
     */
    public WebElement getGoToCartButton() {
        return goToCartButton;
    }


    /**
     * @param goToCartButton the goToCartButton to set
     */
    public void setGoToCartButton(WebElement goToCartButton) {
        this.goToCartButton = goToCartButton;
    }


    /**
     * @return the checkoutButton
     */
    public WebElement getCheckoutButton() {
        return checkoutButton;
    }


    /**
     * @param checkoutButton the checkoutButton to set
     */
    public void setCheckoutButton(WebElement checkoutButton) {
        this.checkoutButton = checkoutButton;
    }


    /**
     * @return the pdhomePage
     */
    public WebElement getPdhomePage() {
        return pdhomePage;
    }


    /**
     * @param pdhomePage the pdhomePage to set
     */
    public void setPdhomePage(WebElement pdhomePage) {
        this.pdhomePage = pdhomePage;
    }


    /**
     * @return the goToCartPopUP
     */
    public WebElement getGoToCartPopUP() {
        return goToCartPopUP;
    }


    /**
     * @param goToCartPopUP the goToCartPopUP to set
     */
    public void setGoToCartPopUP(WebElement goToCartPopUP) {
        this.goToCartPopUP = goToCartPopUP;
    }


    /**
     * @return the addAColor
     */
    public WebElement getAddAColor() {
        return addAColor;
    }


    /**
     * @param addAColor the addAColor to set
     */
    public void setAddAColor(WebElement addAColor) {
        this.addAColor = addAColor;
    }


    /**
     * @return jobName
     */
    public WebElement getJobName() {
        return jobName;
    }


    /**
     * @return checkoutOk
     */
    public WebElement getCheckoutOk() {
        return checkoutOK;
    }
}