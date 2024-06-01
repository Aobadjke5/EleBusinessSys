package com.zyy.dao;


import com.zyy.entity.Order;
import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO T_Product(ProductName, ProductImage, UserID, WarehouseID, Status) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{userID}, #{p.warehouseID}, #{p.status});")
    @Options(useGeneratedKeys = true, keyProperty = "p.productID")
    int createProduct(@Param("p") Product product, @Param("userID") Integer userID);

    @Insert("INSERT INTO T_ProDetail(ProductName, ProductImage, ProductCnt, ProductPrice, ProductID) " +
            "VALUES(#{p.productName}, #{p.productImage}, #{p.productCnt}, #{p.productPrice}, #{productID});")
    int insertProductDetail(@Param("p") ProductDetail productDetail, @Param("productID") Integer productID);

    @Delete("DELETE FROM T_ProDetail WHERE ProductID = #{productID}")
    int deleteProductDetails(@Param("productID") Integer productID);

    @Update("update T_Product set ProductName = #{p.productName}, ProductImage = #{p.productImage}, " +
            "WarehouseID = #{p.warehouseID} " +
            "where ProductID = #{p.productID} ;")
    int editProduct(@Param("p") Product product);

    @Select("SELECT p.ProductID AS productID, p.ProductName AS productName, p.ProductImage AS productImage, " +
            "u.UserID AS userID, u.CompanyName AS companyName, u.CompanyIcon AS companyIcon, " +
            "u.CompanyAddress AS companyAddress, u.PeopleName AS peopleName, u.PeopleTel AS peopleTel, u.PeopleMail AS peopleMail, " +
            "w.WarehouseID AS warehouseID, w.WarehouseName AS warehouseName, " +
            "w.WarehouseAddress AS warehouseAddress, w.WarehouseImage AS warehouseImage " +
            "FROM T_Product p " +
            "JOIN T_User u ON p.UserID = u.UserID " +
            "JOIN T_Warehouse w ON p.WarehouseID = w.WarehouseID " +
            "WHERE p.Status = 'Yes'")
    @Results({
            @Result(property = "productID", column = "productID"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "productImage", column = "productImage"),
            @Result(property = "companyInfo.userID", column = "userID"),
            @Result(property = "companyInfo.companyName", column = "companyName"),
            @Result(property = "companyInfo.companyIcon", column = "companyIcon"),
            @Result(property = "companyInfo.companyAddress", column = "companyAddress"),
            @Result(property = "companyInfo.peopleName", column = "peopleName"),
            @Result(property = "companyInfo.peopleTel", column = "peopleTel"),
            @Result(property = "companyInfo.peopleMail", column = "peopleMail"),
            @Result(property = "warehouseInfo.warehouseID", column = "warehouseID"),
            @Result(property = "warehouseInfo.warehouseName", column = "warehouseName"),
            @Result(property = "warehouseInfo.warehouseAddress", column = "warehouseAddress"),
            @Result(property = "warehouseInfo.warehouseImage", column = "warehouseImage")
    })
    ArrayList<Product> getProductList();

    @Select("SELECT ProDetailID AS proDetailID, ProductName AS productName, ProductImage AS productImage, ProductCnt AS productCnt, ProductPrice AS productPrice " +
            "FROM T_ProDetail WHERE ProductID = #{productID}")
    ArrayList<ProductDetail> getProductDetails(@Param("productID") Integer productID);


    @Select("SELECT p.ProductID AS productID, p.ProductName AS productName, p.ProductImage AS productImage, p.Status AS status, " +
            "w.WarehouseID AS warehouseID, w.WarehouseName AS warehouseName, " +
            "w.WarehouseAddress AS warehouseAddress, w.WarehouseImage AS warehouseImage " +
            "FROM T_Product p " +
            "JOIN T_Warehouse w ON p.WarehouseID = w.WarehouseID " +
            "WHERE p.UserID = #{userID}")
    @Results({
            @Result(property = "productID", column = "productID"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "productImage", column = "productImage"),
            @Result(property = "status", column = "status"),
            @Result(property = "warehouseInfo.warehouseID", column = "warehouseID"),
            @Result(property = "warehouseInfo.warehouseName", column = "warehouseName"),
            @Result(property = "warehouseInfo.warehouseAddress", column = "warehouseAddress"),
            @Result(property = "warehouseInfo.warehouseImage", column = "warehouseImage")
    })
    ArrayList<Product> getMyProductList(@Param("userID") Integer userID);

    @Select("SELECT p.ProductID AS productID, p.ProductName AS productName, p.ProductImage AS productImage, p.Status AS status, " +
            "w.WarehouseID AS warehouseID, w.WarehouseName AS warehouseName, " +
            "w.WarehouseAddress AS warehouseAddress, w.WarehouseImage AS warehouseImage " +
            "FROM T_Product p " +
            "JOIN T_Warehouse w ON p.WarehouseID = w.WarehouseID " +
            "WHERE p.ProductID = #{productID}")
    @Results({
            @Result(property = "productID", column = "productID"),
            @Result(property = "productName", column = "productName"),
            @Result(property = "productImage", column = "productImage"),
            @Result(property = "status", column = "status"),
            @Result(property = "warehouseInfo.warehouseID", column = "warehouseID"),
            @Result(property = "warehouseInfo.warehouseName", column = "warehouseName"),
            @Result(property = "warehouseInfo.warehouseAddress", column = "warehouseAddress"),
            @Result(property = "warehouseInfo.warehouseImage", column = "warehouseImage")
    })
    Product getProductByID(@Param("productID") Integer productID);

    @Update("UPDATE T_Product set Status = 'Yes' " +
            "where ProductID = #{productID};")
    int editVisibilityYes(@Param("productID")Integer productID);

    @Update("UPDATE T_Product set Status = 'No' " +
            "where ProductID = #{productID};")
    int editVisibilityNo(@Param("productID")Integer productID);

    @Select("SELECT * " +
            "FROM (SELECT ProductName as productName, UserID as supplierID, " +
            "WarehouseID as warehouseID, ProductID FROM T_Product WHERE Status = 'Yes') p " +
            "RIGHT JOIN (SELECT ProductName as proDetailName, ProductPrice as productPrice, " +
            "ProductImage as productImage, ProductID FROM T_ProDetail " +
            "WHERE ProductCnt >= #{purchaseCnt} AND ProDetailID = #{proDetailID}) d " +
            "ON p.ProductID = d.ProductID ")
    Order purchaseProduct(@Param("proDetailID") Integer proDetailID, @Param("purchaseCnt") Integer purchaseCnt);

    @Update("UPDATE T_ProDetail set ProductCnt = ProductCnt - #{purchaseCnt} " +
            "where ProDetailID = #{proDetailID};")
    int purchaseHandleCnt(@Param("proDetailID") Integer proDetailID, @Param("purchaseCnt") Integer purchaseCnt);
}
