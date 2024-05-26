package com.zyy.entity;

import java.util.ArrayList;

public class ProductList {
    private int productID;
    private String productName;
    private String productImage;
    private ArrayList<ProductDetail> productDetails;
    private int userID;
    private String companyName;
    private String companyIcon;
    private String companyAddress;
    private String peopleName;
    private String peopleTel;
    private String peopleMail;
    private int warehouseID;
    private String warehouseName;
    private String warehouseAddress;
    private String warehouseImage;

    public ProductList() {
    }

    public ProductList(int productID, String productName, String productImage, int userID, String companyName, String companyIcon, String companyAddress, String peopleName, String peopleTel, String peopleMail, int warehouseID, String warehouseName, String warehouseAddress, String warehouseImage) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.userID = userID;
        this.companyName = companyName;
        this.companyIcon = companyIcon;
        this.companyAddress = companyAddress;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
        this.peopleMail = peopleMail;
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.warehouseAddress = warehouseAddress;
        this.warehouseImage = warehouseImage;
    }

    public ProductList(ArrayList<ProductDetail> productDetails, int productID, String productName, String productImage, int userID, String companyName, String companyIcon, String companyAddress, String peopleName, String peopleTel, String peopleMail, int warehouseID, String warehouseName, String warehouseAddress, String warehouseImage) {
        this.productDetails = productDetails;
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.userID = userID;
        this.companyName = companyName;
        this.companyIcon = companyIcon;
        this.companyAddress = companyAddress;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
        this.peopleMail = peopleMail;
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.warehouseAddress = warehouseAddress;
        this.warehouseImage = warehouseImage;
    }

    public ProductList(int productID, String productName, String productImage, int warehouseID, String warehouseName, String warehouseAddress, String warehouseImage) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.warehouseAddress = warehouseAddress;
        this.warehouseImage = warehouseImage;
    }

    public ArrayList<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ArrayList<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(String companyIcon) {
        this.companyIcon = companyIcon;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleTel() {
        return peopleTel;
    }

    public void setPeopleTel(String peopleTel) {
        this.peopleTel = peopleTel;
    }

    public String getPeopleMail() {
        return peopleMail;
    }

    public void setPeopleMail(String peopleMail) {
        this.peopleMail = peopleMail;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseImage() {
        return warehouseImage;
    }

    public void setWarehouseImage(String warehouseImage) {
        this.warehouseImage = warehouseImage;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDetails=" + productDetails +
                ", userID=" + userID +
                ", companyName='" + companyName + '\'' +
                ", companyIcon='" + companyIcon + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleTel='" + peopleTel + '\'' +
                ", peopleMail='" + peopleMail + '\'' +
                ", warehouseID=" + warehouseID +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAddress='" + warehouseAddress + '\'' +
                ", warehouseImage='" + warehouseImage + '\'' +
                '}';
    }
}
