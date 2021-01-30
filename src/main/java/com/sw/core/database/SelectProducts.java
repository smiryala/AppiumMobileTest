package com.sw.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectProducts extends DBAccessorBase {


    private ArrayList<Products> productList = new ArrayList<Products>();
    private int testCaseID;

    public SelectProducts() {
    }

    public void setSelectStatement() {
        setSIUDStatement("SELECT PRODUCT_NAME, PRODUCT_SIZE_INDEX, QUANITY, PRODUCT_TYPE ");
    }

    public void setFromStatement() {
        fromStatement = " FROM PRODUCTS";
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
        Products products = null;
        while (rs.next()) {
            products = new Products();
            products.setProductName(rs.getString("PRODUCT_NAME"));
            products.setProductType(rs.getString("PRODUCT_TYPE"));
            products.setProductSizeIndex(rs.getInt("PRODUCT_SIZE_INDEX"));
            products.setQuantity(rs.getString("QUANITY"));
            productList.add(products);
            sucess = true;
        }
        return sucess;
    }


    public ArrayList<Products> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Products> productList) {
        this.productList = productList;
    }

    public int getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }
}