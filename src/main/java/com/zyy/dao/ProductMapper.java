package com.zyy.dao;

import com.zyy.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO T_Product(ProductName, ProductImage, UserID, WarehouseID, Status) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{p.userID}, #{p.warehouseID}, #{p.status});")
    @Options(useGeneratedKeys = true, keyProperty = "productID")
    int insertProduct(@Param("p") Product product);

    @Insert("INSERT INTO T_ProDetail(ProductName, ProductImage, ProductCnt, ProductPrice, ProductID) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{p.productCnt}, #{p.productPrice}, #{productID});")
    int insertProductDetail(@Param("p") ProductDetail productDetail, @Param("productID") Integer productID);

    @Update("update T_Product set ProductName = #{p.productName}, ProductImage = #{p.productImage}, " +
            "WarehouseID = #{p.warehouseID} " +
            "where ProductID = #{p.productID} ;")
    int editProduct(@Param("p") Product product);

    @Update("UPDATE T_ProDetail SET ProductName = #{pd.productName}, " +
            "ProductImage = #{pd.productImage}, " +
            "ProductCnt = #{pd.productCnt}, " +
            "ProductPrice = #{pd.productPrice} " +
            "WHERE ProDetailID = #{pd.proDetailID} ;")
    int editProductDetail(@Param("pd") ProductDetail productDetail);

    @Select("SELECT COUNT(*) FROM T_ProDetail WHERE ProductName = #{productName} AND ProductID = #{productID}")
    int checkProductDetailExists(@Param("productName") String productName, @Param("productID") Integer productID);

    @Select("SELECT p.ProductID AS productID, p.ProductName AS productName, p.ProductImage AS productImage, " +
            "u.UserID AS userID, u.CompanyName AS companyName, u.CompanyIcon AS companyIcon, " +
            "u.CompanyAddress AS companyAddress, u.PeopleName AS peopleName, u.PeopleTel AS peopleTel, u.PeopleMail AS peopleMail, " +
            "w.WarehouseID AS warehouseID, w.WarehouseName AS warehouseName, " +
            "w.WarehouseAddress AS warehouseAddress, w.WarehouseImage AS warehouseImage " +
            "FROM T_Product p " +
            "JOIN T_User u ON p.UserID = u.UserID " +
            "JOIN T_Warehouse w ON p.WarehouseID = w.WarehouseID")
    ArrayList<ProductList> list();



    @Select("SELECT ProDetailID AS proDetailID, ProductID AS productID, ProductName AS productName, ProductImage AS productImage, ProductCnt AS productCnt, ProductPrice AS productPrice " +
            "FROM T_ProDetail WHERE ProductID = #{productID}")
    ArrayList<ProductDetail> selectProductDetails(Integer productID);

    @Select("SELECT p.ProductID AS productID, p.ProductName AS productName, p.ProductImage AS productImage, " +
            "w.WarehouseID AS warehouseID, w.WarehouseName AS warehouseName, w.WarehouseAddress AS warehouseAddress, w.WarehouseImage AS warehouseImage " +
            "FROM T_Product p " +
            "JOIN T_Warehouse w ON p.WarehouseID = w.WarehouseID " +
            "where p.UserID = #{userID}")
    ArrayList<ProductList> mylist(@Param("userID") Integer userID);

    @Update("UPDATE T_Product set Status = #{option} " +
            "where ProductID = #{productID};")
    int editVisibility(@Param("productID")Integer productID,@Param("option") String option);
}
