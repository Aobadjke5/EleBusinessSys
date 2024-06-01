package com.zyy.dao;

import com.zyy.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO T_Order(ProductName, ProductImage, ProDetailName, DealerID, SupplierID, WarehouseID, " +
            "CreateTime, ProductPrice, ProductCnt, TotalPrice, Status, AddressID) " +
            "VALUES(#{o.productName}, #{o.productImage}, #{o.proDetailName}, #{userID}, " +
            "#{o.supplierID}, #{o.warehouseID}, #{createTime, jdbcType=TIMESTAMP}, #{o.productPrice}, " +
            "#{o.productCnt}, #{o.totalPrice}, 'Waiting', #{o.addressID});")
    int createNewOrder(@Param("o") Order order, @Param("userID") Integer userID, @Param("createTime") Timestamp createTime);
}
