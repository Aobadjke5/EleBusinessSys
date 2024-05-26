package com.zyy.entity;

public class User {
    private Integer userID;
    private String userName;
    private String role;
    private String Status;
    private String companyName;
    private String companyIcon;
    private String companyAddress;
    private String peopleName;
    private String peopleTel;
    private String peopleMail;

    public User() {
    }

    public User(Integer userID, String username, String role, String companyName, String companyIcon, String companyAddress, String peopleName, String peopleTel, String peopleMail) {
        this.userID = userID;
        this.userName = username;
        this.role = role;
        this.companyName = companyName;
        this.companyIcon = companyIcon;
        this.companyAddress = companyAddress;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
        this.peopleMail = peopleMail;
    }

    public User(Integer userID, String userName, String role, String status, String companyName, String companyIcon, String companyAddress, String peopleName, String peopleTel, String peopleMail) {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
        Status = status;
        this.companyName = companyName;
        this.companyIcon = companyIcon;
        this.companyAddress = companyAddress;
        this.peopleName = peopleName;
        this.peopleTel = peopleTel;
        this.peopleMail = peopleMail;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", Status='" + Status + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyIcon='" + companyIcon + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", peopleTel='" + peopleTel + '\'' +
                ", peopleMail='" + peopleMail + '\'' +
                '}';
    }
}
