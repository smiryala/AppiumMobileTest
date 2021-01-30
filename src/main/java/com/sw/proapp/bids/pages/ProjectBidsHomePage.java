package com.sw.proapp.bids.pages;

import com.sw.core.helpers.Common;
import com.sw.proapp.bids.locators.ProjectBidsHomePageLocators;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProjectBidsHomePage extends ProjectBidsHomePageLocators {

    public ProjectBidsHomePage(Common common) {
        super(common);
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    public boolean isCreateNewBidButtonCorrect() {
        return createNewBidButtonText().toLowerCase().equals("Create New Bid".toLowerCase()) || createNewBidButtonText().toLowerCase().equals("Crear Cotización".toLowerCase());
    }

    public String createNewBidButtonText() {
        return getCommon().getText(createNewBidButton, "Unable to get text");
    }

    public boolean isViewAllBidsButtonCorrect() {
        return viewAllBidsButtonText().toLowerCase().equals("View All Bids".toLowerCase()) || viewAllBidsButtonText().toLowerCase().equals("Ver Cotizaciones".toLowerCase());
    }

    public String viewAllBidsButtonText() {
        return getCommon().getText(viewAllBidsButton, "Unable to get text");
    }

    public boolean isProjectBidsCardDisplayed() {
        return (getCommon().isDisplayed(projectBidsCardHeader) && projectBidsCardHeaderText().toLowerCase().equals("Project Bids".toLowerCase()));
    }

    public String projectBidsCardHeaderText() {
        return getCommon().getText(projectBidsCardHeader, "Unable to get text");
    }

    public ProjectBidsHomePage clickViewAllBidsButton() {
        getCommon().clickAndVerifyIsTrue(viewAllBidsButton, "Unable to click the view all bids button");
        return this;
    }

    public ProjectBidsHomePage clickCreateNewBidButton() {
        getCommon().clickAndVerifyIsTrue(createNewBidButton, "Unable to click the create new bid button");
        return this;
    }

    public WebElement getCreateNewBidButton() {
        return createNewBidButton;
    }

    public WebElement getViewAllBidsButton() {
        return viewAllBidsButton;
    }

    public WebElement getProjectBidsCardHeader() {
        return projectBidsCardHeader;
    }
}