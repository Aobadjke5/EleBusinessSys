package com.zyy.entity;


import java.util.Date;

public class Order {
    private String proDetailName; // 替换了原来的proDetailID
    private Integer productCnt;
    private Double totalPrice;
    private Date createTime;
    private Integer addressID;

    // 默认构造器
    public Order() {
    }

    public Order(String proDetailName, Integer productCnt, Double totalPrice, Date createTime, Integer addressID) {
        this.proDetailName = proDetailName;
        this.productCnt = productCnt;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.addressID = addressID;
    }

    public String getProDetailName() {
        return proDetailName;
    }

    public void setProDetailName(String proDetailName) {
        this.proDetailName = proDetailName;
    }

    public Integer getProductCnt() {
        return productCnt;
    }

    public void setProductCnt(Integer productCnt) {
        this.productCnt = productCnt;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "proDetailName='" + proDetailName + '\'' +
                ", productCnt=" + productCnt +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                ", addressID=" + addressID +
                '}';
    }
}
