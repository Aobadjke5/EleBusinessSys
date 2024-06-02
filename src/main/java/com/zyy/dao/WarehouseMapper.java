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
    ArrayList<Warehouse> getWarehouseListByID(@Param("userID") Integer userID);

    @Select("select WarehouseID as warehouseID, WarehouseName as warehouseName, " +
            "WarehouseAddress as warehouseAddress, WarehouseImage as warehouseImage, " +
            "Status as status, TotalCapacity as totalCapacity, CurrentCapacity as currentCapacity," +
            "UserID as userID " +
            "from T_Warehouse WHERE Status = 'Yes';" )
    ArrayList<Warehouse> getWarehouseList();

    @Select("select WarehouseID as warehouseID, WarehouseName as warehouseName, " +
            "WarehouseAddress as WarehouseAddress, WarehouseImage as warehouseImage, " +
            "Status as status, TotalCapacity as totalCapacity, CurrentCapacity as currentCapacity " +
            "from T_Warehouse where WarehouseID = #{warehouseID};" )
    Warehouse getWarehouseByID(@Param("warehouseID") Integer warehouseID);

    @Update("update T_Warehouse set WarehouseName = #{w.warehouseName}, WarehouseAddress = #{w.warehouseAddress}, " +
            "WarehouseImage = #{w.warehouseImage}, TotalCapacity = #{w.totalCapacity}, " +
            "CurrentCapacity = #{w.currentCapacity} " +
            "where WarehouseID = #{w.warehouseID};")
    int editWarehouse(@Param("w") Warehouse warehouse);

    @Update("UPDATE T_Warehouse set Status = 'Yes' " +
            "where WarehouseID = #{warehouseID};")
    int editVisibilityYes(@Param("warehouseID")Integer warehouseID);
    @Update("UPDATE T_Warehouse set Status = 'No' " +
            "where WarehouseID = #{warehouseID};")
    int editVisibilityNo(@Param("warehouseID")Integer warehouseID);

    @Select("select WarehouseID as warehouseID, WarehouseName as warehouseName, " +
            "WarehouseAddress as WarehouseAddress " +
            "from T_Warehouse where WarehouseID = #{warehouseID};" )
    Warehouse getOrderWarehouseInfo(@Param("warehouseID")Integer warehouseID);
}
