package com.sw.core.database;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsertResults extends DBAccessorBase {

    protected String testCaseID = null;
    protected String testCaseDescription = null;
    protected String passFail = null;
    protected String poNumber = null;
    protected String ecommerceID = null;
    protected String databaseError = null;
    protected String timeStamp = null;
    protected String environment = null;
    protected String scriptError = null;
    protected String browser = null;
    protected String releaseInfo = null;
    protected String executedBy = null;

    public InsertResults() {
        setUpdate();
    }

    public InsertResults(final String testCaseID) {
        this.testCaseID = testCaseID;
        setUpdate();
    }

    public InsertResults(final Statement userStatement) {
        this.statement = userStatement;
        setUpdate();
    }

    public InsertResults(final String testCaseID, final Statement userStatement) {
        this.testCaseID = testCaseID;
        this.statement = userStatement;
        setUpdate();
    }

    public void setInsertStatement() {
        String insertStatement = "INSERT INTO RESULTS" +
                " (ID, TEST_CASEID, TEST_CASE_DESCRIPTION, RESULT, EXECUTION_TIME, PO_NUMBER, ECOMMERCE_ID, ERROR_MESSAGE, " +
                " DB_ERROR, ENVIRONMENT, BROWSER, RELEASE_INFO, EXECUTED_BY)" +
                " VALUES(RESULTS_SEQ.NEXTVAL," +
                this.testCaseID +
                ",'" +
                this.testCaseDescription +
                "','" +
                this.getPassFail() +
                "','" +
                this.timeStamp +
                "','" +
                this.poNumber +
                "','" +
                this.ecommerceID +
                "','" +
                this.scriptError +
                "','" +
                this.databaseError +
                "','" +
                this.environment +
                "','" +
                this.browser +
                "','" +
                this.releaseInfo +
                "','" +
                System.getProperty("user.name").toUpperCase() +
                "')";
        setSIUDStatement(insertStatement);
    }

    public boolean execute() {
        if (this.siudStatement == null) {
            setInsertStatement();

        }
        if (this.fromStatement == null) {
            setFromStatement();
        }
        if (this.whereClause == null) {
            setWhereClause();
        }
        return super.execute();
    }

    public void setFromStatement() {
        this.fromStatement = "";
    }

    public void setWhereClause() {
        this.whereClause = "";
    }

    public String getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(String testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public String getPassFail() {
        return passFail;
    }

    public void setPassFail(String passFail) {
        this.passFail = passFail;
    }

    public String getDatabaseError() {
        return databaseError;
    }

    public void setDatabaseError(String databaseError) {
        if (this.databaseError != null && this.databaseError.trim().length() > 0) {
            if (databaseError.contains(this.databaseError)) {
                this.databaseError = databaseError;
            } else if (!this.databaseError.contains(databaseError)) {
                this.databaseError = this.databaseError + ";" + databaseError;
            }
        } else {
            this.databaseError = databaseError;
        }
        this.databaseError = databaseError;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getScriptError() {
        return scriptError;
    }

    public void setScriptError(String scriptError) {
        if (this.scriptError != null && this.scriptError.trim().length() > 0) {
            if (scriptError.contains(this.scriptError)) {
                this.scriptError = scriptError;
            } else if (!this.scriptError.contains(scriptError)) {
                this.scriptError = this.scriptError + scriptError;
            }
        } else {
            this.scriptError = scriptError;
        }
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    @Override
    public String toString() {
        return "InsertResults{" +
                "testCaseID='" + testCaseID + '\'' +
                ", testCaseDescription='" + testCaseDescription + '\'' +
                ", passFail='" + passFail + '\'' +
                ", poNumber='" + poNumber + '\'' +
                ", ecommerceID='" + ecommerceID + '\'' +
                ", databaseError='" + databaseError + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", environment='" + environment + '\'' +
                ", scriptError='" + scriptError + '\'' +
                ", browser='" + browser + '\'' +
                ", releaseInfo='" + releaseInfo + '\'' +
                ", executedBy='" + executedBy + '\'' +
                '}';
    }

    public void setExecutedBy(String executedBy) {
        this.executedBy = executedBy;
    }

    @Override
    protected boolean processResult(final ResultSet rs) {
        return super.execute();
    }


}