package com.zyy.entity;

public class Visibility {
    private Integer warehouseID;
    private Integer productID;
    private Integer userID;
    private String option;

    public Visibility(Integer warehouseID, String option) {
        this.warehouseID = warehouseID;
        this.option = option;
    }

    public Visibility() {
    }

    public Visibility(Integer warehouseID, Integer productID, Integer userID, String option) {
        this.warehouseID = warehouseID;
        this.productID = productID;
        this.userID = userID;
        this.option = option;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Visibility{" +
                "warehouseID=" + warehouseID +
                ", productID=" + productID +
                ", userID=" + userID +
                ", option='" + option + '\'' +
                '}';
    }
}
