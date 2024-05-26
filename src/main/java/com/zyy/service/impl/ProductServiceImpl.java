package com.zyy.service.impl;

import com.zyy.dao.ProductMapper;
import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.entity.Order;
import com.zyy.entity.*;
import com.zyy.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;

    @Override
    @Transactional
    public boolean createNewProduct(Product product, Integer userID) {
        ArrayList<ProductDetail> productDetails = product.getProductDetails();
        product.setUserID(userID);

        productMapper.insertProduct(product);
        Integer productID = product.getProductID();
        for (ProductDetail productDetail : productDetails) {
            productMapper.insertProductDetail(productDetail, productID);
        }
        return true;
    }

    @Transactional
    public boolean createProductWithOrder(Product product, Order order, Integer orderID) {

        if (!createNewProduct(product, orderID)) {
            return false;
        }

        productMapper.insertOrder(order);

        return true;
    }


    @Override
    @Transactional
    public boolean editProduct(Product product) {
        // 更新 T_Product
        int result1 = productMapper.editProduct(product);

        // 插入或更新 T_ProDetail
        for (ProductDetail productDetail : product.getProductDetails()) {
            productDetail.setProductID(product.getProductID());  // 确保 productID 被设置
            int exist = productMapper.checkProductDetailExists(productDetail.getProductName(), productDetail.getProductID());
            if (exist != 0){
                productMapper.editProductDetail(productDetail);
            }
            else {
                productMapper.insertProductDetail(productDetail, product.getProductID());
            }
        }

        return result1 > 0;
    }

    @Override
    public ArrayList<ProductList> list(){
        ArrayList<ProductList> productLists = productMapper.list();
        for (ProductList productList :productLists){
            productList.setProductDetails(productMapper.selectProductDetails(productList.getProductID()));
        }
        return productLists;
    }

    @Override
    public ArrayList<ProductList> mylist(Integer userID){
        ArrayList<ProductList> mylists = productMapper.mylist(userID);
        for (ProductList productList :mylists){
            productList.setProductDetails(productMapper.selectProductDetails(productList.getProductID()));
        }
        return mylists;
    }

    @Override
    public boolean editVisibility(Integer productID, String option){
        int cnt = productMapper.editVisibility(productID, option);
        return cnt == 1;
    }
}

