package com.zyy.entity;

public class Address {
    private Integer addressID;
    private String addressDetail;
    private String peopleName;
    private String peopleTel;

    public Address() {
    }

    public Address(Integer addressID, String addressDetail, String peopleName, String peopleTel) {
        this.addressID = addressID;
        this.addressDetail = addressDetail;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
    }

    public Address(String addressDetail, String peopleName, String peopleTel) {
        this.addressDetail = addressDetail;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
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

    @Override
    public String toString() {
        return "Address{" +
                "addressID='" + addressID + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleTel='" + peopleTel + '\'' +
                '}';
    }
}
