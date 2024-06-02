package com.zyy.dao;

import com.zyy.entity.Order;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO T_Order(ProductName, ProductImage, ProDetailName, DealerID, SupplierID, WarehouseID, " +
            "CreateTime, ProductPrice, ProductCnt, TotalPrice, Status, AddressID) " +
            "VALUES(#{o.productName}, #{o.productImage}, #{o.proDetailName}, #{userID}, " +
            "#{o.supplierID}, #{o.warehouseID}, #{createTime, jdbcType=TIMESTAMP}, #{o.productPrice}, " +
            "#{o.productCnt}, #{o.totalPrice}, 'Waiting', #{o.addressID});")
    int createNewOrder(@Param("o") Order order, @Param("userID") Integer userID, @Param("createTime") Timestamp createTime);

    @Select("SELECT OrderID as orderID, ProductName as productName, ProductImage as productImage, " +
            "ProDetailName as proDetailName, DealerID as dealerID, SupplierID as supplierID, " +
            "WarehouseID as warehouseID, CreateTime as createTimestamp, DeliveryTime as deliveryTimestamp, " +
            "CompletionTime as completionTimestamp, ProductPrice as productPrice, ProductCnt as productCnt, " +
            "TotalPrice as totalPrice, Status as status, AddressID as addressID FROM T_Order " +
            "WHERE DealerID = #{userID} AND Status = #{status}")
    ArrayList<Order> getDealerOrderList(@Param("userID") Integer userID, @Param("status") String status);

    @Select("SELECT OrderID as orderID, ProductName as productName, ProductImage as productImage, " +
            "ProDetailName as proDetailName, DealerID as dealerID, SupplierID as supplierID, " +
            "WarehouseID as warehouseID, CreateTime as createTimestamp, DeliveryTime as deliveryTimestamp, " +
            "CompletionTime as completionTimestamp, ProductPrice as productPrice, ProductCnt as productCnt, " +
            "TotalPrice as totalPrice, Status as status, AddressID as addressID FROM T_Order " +
            "WHERE SupplierID = #{userID} AND Status = #{status}")
    ArrayList<Order> getSupplierOrderList(@Param("userID") Integer userID, @Param("status") String status);

    @Select("SELECT o.OrderID as orderID, o.ProductName as productName, o.ProductImage as productImage, " +
            "o.ProDetailName as proDetailName, o.DealerID as dealerID, o.SupplierID as supplierID, " +
            "o.WarehouseID as warehouseID, o.CreateTime as createTimestamp, o.DeliveryTime as deliveryTimestamp, " +
            "o.CompletionTime as completionTimestamp, o.ProductPrice as productPrice, o.ProductCnt as productCnt, " +
            "o.TotalPrice as totalPrice, o.Status as status, o.AddressID as addressID FROM T_Order o " +
            "LEFT JOIN (SELECT WarehouseID, UserID as WarehouserID FROM T_Warehouse " +
            "WHERE UserID = #{userID}) w " +
            "ON w.WarehouseID = o.WarehouseID " +
            "WHERE w.WarehouserID = #{userID} AND o.Status = #{status}")
    ArrayList<Order> getWarehouserOrderList(@Param("userID") Integer userID, @Param("status") String status);

    @Update("UPDATE T_Order set CompletionTime = #{cancelTime, jdbcType=TIMESTAMP}, Status = 'Cancelled' " +
            "where OrderID = #{orderID} AND Status = 'Waiting';")
    int cancelOrder(@Param("orderID") Integer orderID, @Param("cancelTime") Timestamp cancelTimestamp);

    @Update("UPDATE T_Order set DeliveryTime = #{deliverTime, jdbcType=TIMESTAMP}, Status = 'Sending' " +
            "where OrderID = #{orderID} AND Status = 'Waiting';")
    int deliverOrder(@Param("orderID") Integer orderID, @Param("deliverTime") Timestamp deliverTimestamp);

    @Update("UPDATE T_Order set CompletionTime = #{completionTime, jdbcType=TIMESTAMP}, Status = 'Completed' " +
            "where OrderID = #{orderID} AND Status = 'Sending';")
    int receiveOrder(@Param("orderID") Integer orderID, @Param("completionTime") Timestamp completionTimestamp);

    @Update("UPDATE T_Order set CompletionTime = #{completionTime, jdbcType=TIMESTAMP}, Status = 'Returning' " +
            "where OrderID = #{orderID} AND Status = 'Sending';")
    int refuseOrder(@Param("orderID") Integer orderID, @Param("completionTime") Timestamp completionTimestamp);

    @Update("UPDATE T_Order set Status = 'Sending' " +
            "where OrderID = #{orderID} AND Status = 'Returning';")
    int refuseReturnOrder(@Param("orderID") Integer orderID);

    @Update("UPDATE T_Order set CompletionTime = #{completionTime, jdbcType=TIMESTAMP}, Status = 'Success' " +
            "where OrderID = #{orderID} AND Status = 'Returning';")
    int receiveReturnOrder(@Param("orderID") Integer orderID, @Param("completionTime") Timestamp completionTimestamp);
}
