package com.zyy.entity;

public class ProductDetail {
    private Integer proDetailID;
    private Integer productID;
    private String productName;
    private String productImage;
    private Integer productCnt;
    private Double productPrice;

    public boolean verifyParam() {
        return this.productName != null && this.productImage != null &&
                this.productCnt != null && this.productPrice != null;
    }

    public ProductDetail() {
    }

    public ProductDetail(Integer proDetailID, Integer productID, String productName, String productImage, Integer productCnt, Double productPrice) {
        this.proDetailID = proDetailID;
        this.productID = productID;
        this.productName = productName;
        this.productImage = productImage;
        this.productCnt = productCnt;
        this.productPrice = productPrice;
    }

    public Integer getProDetailID() {
        return proDetailID;
    }

    public void setProDetailID(Integer proDetailID) {
        this.proDetailID = proDetailID;
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

    public Integer getProductCnt() {
        return productCnt;
    }

    public void setProductCnt(Integer productCnt) {
        this.productCnt = productCnt;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "proDetailID=" + proDetailID +
                ", productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productCnt=" + productCnt +
                ", productPrice=" + productPrice +
                '}';
    }
}
