package com.zyy.entity;

public class Account {
    private Integer userID;
    private String username;
    private String password;
    private String role;
    private String status;

    public Account() {
    }

    public Account(Integer userID, String username, String password, String role, String status) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Account(String username, String password, String role, String status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Account(Integer userID, String username, String role, String status) {
        this.userID = userID;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}