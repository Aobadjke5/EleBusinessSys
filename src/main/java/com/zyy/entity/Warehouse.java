package com.zyy.entity;

public class Warehouse {
    private Integer warehouseID;
    private String warehouseName;
    private String warehouseAddress;
    private String warehouseImage;
    private String status;
    private Integer currentCapacity;
    private Integer totalCapacity;
    private User companyInfo;
    private Integer userID;

    public boolean verifyParam() {
        if (this.warehouseName == null || this.warehouseAddress == null || this.warehouseImage == null ||
                this.status == null || this.totalCapacity == null || this.currentCapacity == null)
            return false;
        return this.status.equals("Yes") || this.status.equals("No");
    }

    public Warehouse() {
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseImage() {
        return warehouseImage;
    }

    public void setWarehouseImage(String warehouseImage) {
        this.warehouseImage = warehouseImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public User getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(User companyInfo) {
        this.companyInfo = companyInfo;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseID=" + warehouseID +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAddress='" + warehouseAddress + '\'' +
                ", warehouseImage='" + warehouseImage + '\'' +
                ", status='" + status + '\'' +
                ", currentCapacity=" + currentCapacity +
                ", totalCapacity=" + totalCapacity +
                ", companyInfo=" + companyInfo +
                ", userID=" + userID +
                '}';
    }
}
