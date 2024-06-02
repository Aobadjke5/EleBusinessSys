package com.zyy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Order {
    private Integer orderID;
    private String productName;
    private String productImage;
    private String proDetailName;
    private Integer dealerID;
    private Integer supplierID;
    private Integer warehouseID;
    private Long createTime;
    private Timestamp createTimestamp;
    private Long deliveryTime;
    private Timestamp deliveryTimestamp;
    private Long completionTime;
    private Timestamp completionTimestamp;
    private Double productPrice;
    private Integer productCnt;
    private Double totalPrice;
    private String status;
    private Integer addressID;
    private Integer proDetailID;
    private Warehouse warehouseInfo;
    private Address addressInfo;

    public Order() {
    }

    public Order(Integer orderID, String productName, String productImage, String proDetailName, Integer dealerID, Integer supplierID, Integer warehouseID, long createTime, Timestamp createTimestamp, long deliveryTime, Timestamp deliveryTimestamp, long completionTime, Timestamp completionTimestamp, double productPrice, int productCnt, double totalPrice, String status, Integer addressID, Integer proDetailID, Warehouse warehouseInfo, Address addressInfo) {
        this.orderID = orderID;
        this.productName = productName;
        this.productImage = productImage;
        this.proDetailName = proDetailName;
        this.dealerID = dealerID;
        this.supplierID = supplierID;
        this.warehouseID = warehouseID;
        this.createTime = createTime;
        this.createTimestamp = createTimestamp;
        this.deliveryTime = deliveryTime;
        this.deliveryTimestamp = deliveryTimestamp;
        this.completionTime = completionTime;
        this.completionTimestamp = completionTimestamp;
        this.productPrice = productPrice;
        this.productCnt = productCnt;
        this.totalPrice = totalPrice;
        this.status = status;
        this.addressID = addressID;
        this.proDetailID = proDetailID;
        this.warehouseInfo = warehouseInfo;
        this.addressInfo = addressInfo;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public String getProDetailName() {
        return proDetailName;
    }

    public void setProDetailName(String proDetailName) {
        this.proDetailName = proDetailName;
    }

    public Integer getDealerID() {
        return dealerID;
    }

    public void setDealerID(Integer dealerID) {
        this.dealerID = dealerID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(long completionTime) {
        this.completionTime = completionTime;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCnt() {
        return productCnt;
    }

    public void setProductCnt(int productCnt) {
        this.productCnt = productCnt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public Integer getProDetailID() {
        return proDetailID;
    }

    public void setProDetailID(Integer proDetailID) {
        this.proDetailID = proDetailID;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getDeliveryTimestamp() {
        return deliveryTimestamp;
    }

    public void setDeliveryTimestamp(Timestamp deliveryTimestamp) {
        this.deliveryTimestamp = deliveryTimestamp;
    }

    public Timestamp getCompletionTimestamp() {
        return completionTimestamp;
    }

    public void setCompletionTimestamp(Timestamp completionTimestamp) {
        this.completionTimestamp = completionTimestamp;
    }

    public Warehouse getWarehouseInfo() {
        return warehouseInfo;
    }

    public void setWarehouseInfo(Warehouse warehouseInfo) {
        this.warehouseInfo = warehouseInfo;
    }

    public Address getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(Address addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", proDetailName='" + proDetailName + '\'' +
                ", dealerID=" + dealerID +
                ", supplierID=" + supplierID +
                ", warehouseID=" + warehouseID +
                ", createTime=" + createTime +
                ", createTimestamp=" + createTimestamp +
                ", deliveryTime=" + deliveryTime +
                ", deliveryTimestamp=" + deliveryTimestamp +
                ", completionTime=" + completionTime +
                ", completionTimestamp=" + completionTimestamp +
                ", productPrice=" + productPrice +
                ", productCnt=" + productCnt +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", addressID=" + addressID +
                ", proDetailID=" + proDetailID +
                ", warehouseInfo=" + warehouseInfo +
                ", addressInfo=" + addressInfo +
                '}';
    }
}
