package com.sw.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMobileTestCases extends DBAccessorBase {

    private String testCaseID = null;
    private MobileTestCases mobileTestCases = null;

    public SelectMobileTestCases() {
    }

    public SelectMobileTestCases(Statement userStatement) {
        statement = userStatement;
    }

    public void setSelectStatement() {
		this.siudStatement = "SELECT TEST_CASEID, TEST_CASE_DESCRIPTION, PROCUREMENT_SYSTEM, CUSTOMER, LOCATIONID, CLASS_NAME ";
    }

    public void setFromStatement() {
        fromStatement = " FROM ECOMMERCE_TESTCASES";
    }

    public void setWhereClause() {
        whereClause = " WHERE TEST_CASEID='" + this.testCaseID + "'";
    }

    public boolean execute() {
        if (this.siudStatement == null) {
            setSelectStatement();
        }
        if (this.fromStatement == null) {
            setFromStatement();
        }
        if (this.whereClause == null) {
            setWhereClause();
        }
        return super.execute();
    }

    public String getTestCaseId() {
        return testCaseID;
    }

    public void setTestCaseId(String testCaseID) {
        this.testCaseID = testCaseID;
    }

    @Override
    protected boolean processResult(final ResultSet rs) throws SQLException {
        boolean success = false;
        while (rs.next()) {
            mobileTestCases = new MobileTestCases();
            mobileTestCases.setTestCaseID(rs.getInt("TEST_CASEID"));
            mobileTestCases.setTestCaseDescription(rs.getString("TEST_CASE_DESCRIPTION"));
            mobileTestCases.setProcurementSystem(rs.getString("PROCUREMENT_SYSTEM"));
            mobileTestCases.setCustomer(rs.getString("CUSTOMER"));
            mobileTestCases.setLocationID(rs.getString("LOCATIONID"));
            mobileTestCases.setClassName(rs.getString("CLASS_NAME"));
            setMobileTestCases(mobileTestCases);
            success = true;
        }
        return success;
    }

    public MobileTestCases getMobileTestCases() {
        return mobileTestCases;
    }

    public void setMobileTestCases(MobileTestCases mobileTestCases) {
        this.mobileTestCases = mobileTestCases;
    }
}