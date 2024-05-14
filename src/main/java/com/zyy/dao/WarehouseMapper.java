package com.zyy.dao;

import com.zyy.entity.Warehouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WarehouseMapper {
    @Insert("INSERT INTO T_Warehouse(WarehouseName, WarehouseAddress, WarehouseImage, Status, " +
            "CurrentCapacity, TotalCapacity, UserID) " +
            "VALUES(#{w.warehouseName}, #{w.warehouseAddress}, #{w.warehouseImage}, #{w.status}, " +
            "#{w.currentCapacity}, #{w.totalCapacity}, #{userID})")
    int createNewWarehouse(@Param("w") Warehouse warehouse, @Param("userID") Integer userID);
}
