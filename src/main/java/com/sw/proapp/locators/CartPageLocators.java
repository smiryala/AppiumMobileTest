package com.sw.proapp.locators;


import com.sw.core.helpers.Common;
import com.sw.proapp.data.CartPageData;
import com.sw.proapp.pages.MobileBasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPageLocators extends MobileBasePage {

    public CartPageLocators(Common common) {
        super(common);
    }

    @iOSXCUITFindBy(accessibility = CartPageData.CLOSE_IOS)
    @AndroidFindBy(xpath = CartPageData.CLOSE)
    protected WebElement close;


	@iOSXCUITFindBy(id = CartPageData.PRO_HOME_BUTTON_IOS)
    @AndroidFindBy(id = CartPageData.PRO_HOME_BUTTON)
    protected WebElement proAppHomeButton;

    @iOSXCUITFindBy(xpath = CartPageData.PRO_REMOVE_MESSAGE_IOS)
    @AndroidFindBy(xpath = CartPageData.PRO_REMOVE_MESSAGE)
    protected WebElement removeMessage;

    @iOSXCUITFindBy(xpath = CartPageData.PRO_CONTINUE_SHOPPING_IOS)
    @AndroidFindBy(id = CartPageData.PRO_CONTINUE_SHOPPING)
    protected WebElement continueShopping;

//	List Elements
    @iOSXCUITFindBy(accessibility = CartPageData.PRO_REMOVE_BUTTON_IOS)
    @AndroidFindBy(id = CartPageData.PRO_REMOVE_BUTTON)
    protected List<WebElement> removeButton;
    
    @iOSXCUITFindBy(accessibility = CartPageData.PRO_REMOVE_BUTTON_IOS)
    @AndroidFindBy(id = CartPageData.PRO_REMOVE_BUTTON)
    protected WebElement removeButtonSingleElement;

    public WebElement getRemoveButtonSingleElement() {
		return removeButtonSingleElement;
	}

	public void setRemoveButtonSingleElement(WebElement removeButtonSingleElement) {
		this.removeButtonSingleElement = removeButtonSingleElement;
	}

	public WebElement getProAppHomeButton() {
        return proAppHomeButton;
    }

    public void setProAppHomeButton(WebElement proAppHomeButton) {
        this.proAppHomeButton = proAppHomeButton;
    }

    public List<WebElement> getRemoveButton() {
        return removeButton;
    }

    public WebElement getRemoveMessage() {
        return removeMessage;
    }

    public WebElement getContinueShopping() {
        return continueShopping;
    }


    /**
     * By function
     */
    public By getRemoveButtonBy() {
        return removeButtonBy;
    }

    public By getContinueShoppingBy() {
        return continueShoppingBy;
    }

    //protected By removeButtonBy = By.xpath("//*[translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='remove']");
    //protected By continueShoppingBy = By.xpath("//*[translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='continue shopping']");
    protected By removeButtonBy = By.xpath("//*[@text='REMOVE' or @text='ELIMINAR']");
    protected By continueShoppingBy = By.xpath("//*[@text='CONTINUE SHOPPING' or @text='CONTINUAR COMPRANDO']");

    public WebElement getClose() {
		return close;
	}

	public void setClose(WebElement close) {
		this.close = close;
	}
}