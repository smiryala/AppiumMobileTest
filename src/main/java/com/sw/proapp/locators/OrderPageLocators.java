package com.sw.proapp.locators;

import com.sw.core.helpers.Common;
import com.sw.proapp.data.CartPageData;
import com.sw.proapp.data.OrderData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class OrderPageLocators extends MobileBasePage {

    public OrderPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(xpath = OrderData.CONTINUE_SHOPPING_IOS)
    @AndroidFindBy(id = OrderData.CONTINUE_SHOPPING)
    private WebElement continue_shopping;

    //@iOSXCUITFindBy(xpath =  OrderData.ORDER_NUMBER_VIEW_IOS)
    @AndroidFindBy(id = OrderData.ORDER_NUMBER_VIEW)
    private WebElement orderNumberView;

    @iOSXCUITFindBy(xpath = OrderData.EMAIL_ORDER_CONFIRMATION_VIEW_IOS)
    @AndroidFindBy(id = OrderData.EMAIL_ORDER_CONFIRMATION_VIEW)
    private WebElement emailOrderConfView;

    @iOSXCUITFindBy(xpath = OrderData.NOT_NOW_RATING_IOS)
    @AndroidFindBy(xpath = OrderData.NOT_NOW_RATING_IOS)
    protected WebElement notNowRatingIOS;

    @iOSXCUITFindBy(xpath = OrderData.ORDER_EMAIL_TEXT_IOS)
    @AndroidFindBy(xpath = OrderData.ORDER_EMAIL_TEXT_IOS)
    protected WebElement orderEmailTextIOS;

    @iOSXCUITFindBy(accessibility = CartPageData.CLOSE_IOS)
    @AndroidFindBy(xpath = CartPageData.CLOSE)
    protected WebElement closeInSerachFeild;


	/**
     * @return the orderNumberView
     */
    public WebElement getOrderNumberView() {
        return orderNumberView;
    }

    /**
     * @param orderNumberView the orderNumberView to set
     */
    public void setOrderNumberView(WebElement orderNumberView) {
        this.orderNumberView = orderNumberView;
    }

    /**
     * @return the emailOrderConfView
     */
    public WebElement getEmailOrderConfView() {
        return emailOrderConfView;
    }

    /**
     * @param emailOrderConfView the emailOrderConfView to set
     */
    public void setEmailOrderConfView(WebElement emailOrderConfView) {
        this.emailOrderConfView = emailOrderConfView;
    }

    /**
     * @return the continue_shopping
     */
    public WebElement getContinue_shopping() {
        return continue_shopping;
    }

    /**
     * @param continue_shopping the continue_shopping to set
     */
    public void setContinue_shopping(WebElement continue_shopping) {
        this.continue_shopping = continue_shopping;
    }

    public WebElement getNotNowRatingIOS() {
        return notNowRatingIOS;
    }

    public void setNotNowRatingIOS(WebElement notNowRatingIOS) {
        this.notNowRatingIOS = notNowRatingIOS;
    }

    public WebElement getCloseInSerachFeild() {
		return closeInSerachFeild;
	}

	public void setCloseInSerachFeild(WebElement closeInSerachFeild) {
		this.closeInSerachFeild = closeInSerachFeild;
	}
}