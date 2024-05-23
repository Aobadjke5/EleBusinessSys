package com.zyy.dao;

import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.entity.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO T_Product(ProductName, ProductImage, UserID, WarehouseID, Status) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{p.userID}, #{p.warehouseID}, #{p.status})")
    @Options(useGeneratedKeys = true, keyProperty = "productID")
    int insertProduct(@Param("p") Product product);

    @Insert("INSERT INTO T_ProDetail(ProductName, ProductImage, ProductCnt, ProductPrice, ProductID) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{p.productCnt}, #{p.productPrice}, #{productID})")
    int insertProductDetail(@Param("p") ProductDetail productDetail, @Param("productID") Integer productID);

    @Insert("INSERT INTO T_Order(ProDetailName, ProductCnt, TotalPrice, Createtime, AddressID) " +
            "VALUES(#{proDetailName}, #{productCnt}, #{totalPrice}, #{Createtime}, #{addressID})")
    @Options(useGeneratedKeys = true, keyProperty = "productName")
    int insertOrder(Product product);
}
