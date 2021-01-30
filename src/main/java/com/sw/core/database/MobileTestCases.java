package com.sw.core.database;

public class MobileTestCases {

    private int testCaseID;
    private String testCaseDescription;
    private String customer;
    private String procurementSystem;
    private String locationID;
    private String className;

    public int getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public String getProcurementSystem() {
        return procurementSystem;
    }

    public void setProcurementSystem(String procurementSystem) {
        this.procurementSystem = procurementSystem;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}