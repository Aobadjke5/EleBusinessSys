package com.zyy.service;

import com.zyy.entity.Order;
import com.zyy.entity.Product;

import java.util.ArrayList;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);

    ArrayList<Product> getProductList(String keyWord, Integer pageSize, Integer page);

    Integer getProductListCnt(String keyWord);

    Product editProduct(Product product);

    ArrayList<Product> getMyProductList(Integer userID);

    Product editVisibility(Integer productID, String option);

    Boolean purchaseProduct(Order order, Integer userID);
}
