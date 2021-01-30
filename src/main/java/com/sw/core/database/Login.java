package com.sw.core.database;

public class Login {

    private String userName;
    private String password;
    private String customer;
    private String credentialSet;

    public String getCredentialSet() {
        return credentialSet;
    }

    public void setCredentialSet(String credentialSet) {
        this.credentialSet = credentialSet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}