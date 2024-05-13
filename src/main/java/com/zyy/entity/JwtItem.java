package com.zyy.entity;

public class JwtItem {
    private final Integer userID;
    private final String username;
    private final String role;
    private final String status;
    private final String token;

    public Integer getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public JwtItem(Integer userID, String username, String role, String status, String token) {
        this.userID = userID;
        this.username = username;
        this.role = role;
        this.status = status;
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtItem{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
