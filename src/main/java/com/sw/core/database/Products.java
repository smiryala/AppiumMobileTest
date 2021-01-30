package com.sw.core.database;

public class Products {

    private int testCaseID;
    private String productName;
    private String productSize;
    private String productType;
    private int productSizeIndex;
    private String quantity;

    public int getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductSizeIndex() {
        return productSizeIndex;
    }

    public void setProductSizeIndex(int productSizeIndex) {
        this.productSizeIndex = productSizeIndex;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}