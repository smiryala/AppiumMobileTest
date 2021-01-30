package com.sw.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectLoginDetails extends DBAccessorBase {

    private Login login = null;
    private String customer;

    public SelectLoginDetails() {
    }

    public void setSelectStatement() {
        setSIUDStatement("SELECT USERNAME, PASS_WORD, CREDENTIAL_SET, CUSTOMER");
    }

    public void setFromStatement() {
        fromStatement = " FROM LOGINS";
    }

    public void setWhereClause() {
        whereClause = " WHERE CUSTOMER ='" + this.customer + "'";
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
        boolean success = false;
        while (rs.next()) {
            login = new Login();
            login.setUserName(rs.getString("USERNAME"));
            login.setPassword(rs.getString("PASS_WORD"));
            login.setCustomer(rs.getString("CUSTOMER"));
            login.setCredentialSet(rs.getString("CREDENTIAL_SET"));
            setLogin(login);
            success = true;
        }
        return success;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}