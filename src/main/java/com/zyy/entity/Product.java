package com.zyy.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Product {
    private Integer productID;
    private String productName;
    private String productImage;
    private Integer userID;
    private Integer warehouseID;
    private String status;
    private ArrayList<ProductDetail> productDetails;

    public boolean verifyParam() {
        if (this.productName == null || this.productImage == null || this.warehouseID == null ||
                this.status == null || this.productDetails == null || this.productDetails.size() == 0)
            return false;

        for (ProductDetail productDetail : this.productDetails) {
            if (!productDetail.verifyParam())
                return false;
        }

        return Objects.equals(this.status, "Yes") || Objects.equals(this.status, "No");
    }

    public Product() {
    }

    public Product(Integer productID, String productName, String productImage, Integer userID, Integer warehouseID, String status, ArrayList<ProductDetail> productDetails) {
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.userID = userID;
        this.warehouseID = warehouseID;
        this.status = status;
        this.productDetails = productDetails;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ArrayList<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", userID=" + userID +
                ", warehouseID=" + warehouseID +
                ", status='" + status + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
