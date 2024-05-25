package com.zyy.dao;

import com.zyy.entity.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WarehouseMapper {
    @Insert("INSERT INTO T_Warehouse(WarehouseName, WarehouseAddress, WarehouseImage, Status, " +
            "CurrentCapacity, TotalCapacity, UserID) " +
            "VALUES(#{w.warehouseName}, #{w.warehouseAddress}, #{w.warehouseImage}, #{w.status}, " +
            "#{w.currentCapacity}, #{w.totalCapacity}, #{userID})")
    int createNewWarehouse(@Param("w") Warehouse warehouse, @Param("userID") Integer userID);

    @Select("select WarehouseID as warehouseID, WarehouseName as warehouseName, " +
            "WarehouseAddress as WarehouseAddress, WarehouseImage as warehouseImage, " +
            "Status as status, TotalCapacity as totalCapacity, CurrentCapacity as currentCapacity " +
            "from T_Warehouse where UserID = #{userID};" )
    ArrayList<Warehouse> manageList(@Param("userID") Integer userID);

    @Select("select WarehouseID as warehouseID, WarehouseName as warehouseName, " +
            "WarehouseAddress as warehouseAddress, WarehouseImage as warehouseImage, " +
            "Status as status, TotalCapacity as totalCapacity, CurrentCapacity as currentCapacity " +
            "from T_Warehouse;" )
    ArrayList<Warehouse> list();

    @Update("update T_Warehouse set WarehouseName = #{w.warehouseName}, WarehouseAddress = #{w.warehouseAddress}, " +
            "WarehouseImage = #{w.warehouseImage}, Status = #{w.status}, TotalCapacity = #{w.totalCapacity}, " +
            "CurrentCapacity = #{w.currentCapacity} " +
            "where WarehouseID = #{w.warehouseID} and UserID = #{userID};")
    int edit(@Param("w") Warehouse warehouse, @Param("userID") Integer userID);

    @Update("UPDATE T_Warehouse set Status = #{option} " +
            "where WarehouseID = #{warehouseID};")
    int editVisibility(@Param("warehouseID")Integer warehouseID,@Param("option") String option);
}
