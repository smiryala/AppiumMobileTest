package com.sw.core.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.testng.Reporter;

public class AppDataFromDB {

    static String databaseError;
    protected InsertResults results;
    MobileTestCases mobileTestCases = null;

    public AppDataFromDB(InsertResults insertResults) {
        this.results = insertResults;
    }

    public void failTest(String errorSource, String dbError) {
        if (!"WARN".equals(results.getPassFail())) {
            this.results.setPassFail("FAILED");
        }
        this.results.setDatabaseError(dbError);
    }

    public Login getLoginDetailsFromDB(String testCaseID) {
        SelectLoginDetails sldDa = new SelectLoginDetails();
        mobileTestCases = getNewTestCaseFROMDB(testCaseID);
        sldDa.setCustomer(mobileTestCases.getCustomer());
        setResults(testCaseID);
        if (!sldDa.execute()) {
            databaseError = "Login details are not available for the Customer: " +
                    sldDa.getCustomer() +
                    " Unable to continue test...";
            Reporter.log(databaseError);
            results.setDatabaseError(databaseError);
            failTest(databaseError, databaseError);
        }
        return sldDa.getLogin();
    }

    public void setResults(String testCaseID) {
        results.setTestCaseID(testCaseID);
        results.setTestCaseDescription(getNewTestCaseFROMDB(testCaseID).getTestCaseDescription());
        results.setTimeStamp(getDateTime());
        results.setEnvironment("QA");
        results.setReleaseInfo("MobileAutomationRelease");
        results.setPoNumber("PROAPP_MOBILE_SMOKE");
    }

    public Payments getPaymentDetailsFromDB(String testCaseID) {
        SelectPayments selectPayments = new SelectPayments();
        mobileTestCases = getNewTestCaseFROMDB(testCaseID);
        selectPayments.setTestCaseID(mobileTestCases.getTestCaseID());
        if (!selectPayments.execute()) {
            databaseError = "Payment details are not available for the Customer: " +
                    selectPayments.getPayments() +
                    " Unable to continue test...";
            Reporter.log(databaseError);
            results.setDatabaseError(databaseError);
            failTest(databaseError, databaseError);
        }
        return selectPayments.getPayments();
    }

    public MobileTestCases getNewTestCaseFROMDB(String testCaseID) {
        SelectMobileTestCases selectMobileTestCases = new SelectMobileTestCases();
        selectMobileTestCases.setTestCaseId(testCaseID);
        if (!selectMobileTestCases.execute()) {
            databaseError = "The TestCase ID " + testCaseID +
                    " does not exist in the datbase.  Unable to continue test ...";
            Reporter.log(databaseError);
            results.setDatabaseError(databaseError);
            failTest(databaseError, databaseError);
        }
        return selectMobileTestCases.getMobileTestCases();
    }

    public ArrayList<Products> getProductsFROMDB(String testCaseID) {
        SelectProducts selectProducts = new SelectProducts();
        mobileTestCases = getNewTestCaseFROMDB(testCaseID);
        selectProducts.setTestCaseID(mobileTestCases.getTestCaseID());
        if (!selectProducts.execute()) {
            databaseError = "The Products for TestCaseNumber " +
                    testCaseID +
                    " does not exist in the datbase.  Unable to continue test ...";
            Reporter.log(databaseError);
            results.setDatabaseError(databaseError);
            failTest(databaseError, databaseError);
        }
        return selectProducts.getProductList();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}