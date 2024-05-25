package com.zyy.entity;

public class Address {
    private String addressID;
    private String userid;
    private String addressDetail;
    private String peopleName;
    private String peopleTel;
    private String status;
    private Boolean isDeleted; // 逻辑删除标记字段

    // 全参构造方法
    public Address(String addressID, String userid, String addressDetail, String peopleName, String peopleTel, String status, Boolean isDeleted) {
        this.addressID = addressID;
        this.userid = userid;
        this.addressDetail = addressDetail;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    // 无参构造方法
    public Address() {
        this.isDeleted = false; // 默认设置为未删除状态
    }

    // Getter 和 Setter 方法
    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Override
    public String toString() {
        return "Address{" +
                "addressID='" + addressID + '\'' +
                ", userid='" + userid + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleTel='" + peopleTel + '\'' +
                ", status='" + status + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
