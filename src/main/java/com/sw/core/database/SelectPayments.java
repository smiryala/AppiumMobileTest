package com.sw.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectPayments extends DBAccessorBase {

    private Payments payment = null;
    private int testCaseID;

    public SelectPayments() {
    }

    public void setSelectStatement() {
        setSIUDStatement(" SELECT PAYMENT_TYPE");
    }

    public void setFromStatement() {
        fromStatement = " FROM PAYMENTS";
    }

    public void setWhereClause() {
        whereClause = " WHERE TEST_CASEID ='" + this.testCaseID + "'";
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

    @Override
    protected boolean processResult(final ResultSet rs) throws SQLException {
        boolean sucess = false;
        Payments payment = null;
        while (rs.next()) {
            payment = new Payments();
            payment.setPaymentType(rs.getString("PAYMENT_TYPE"));
            setPayments(payment);
            sucess = true;
        }
        return sucess;
    }

    public Payments getPayments() {
        return payment;
    }

    public void setPayments(Payments payment) {
        this.payment = payment;
    }

    public int getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }
}