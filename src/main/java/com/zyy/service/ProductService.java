package com.zyy.service;

import com.zyy.entity.Product;

import java.util.ArrayList;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);

    ArrayList<Product> getProductList();

    Product editProduct(Product product);

    ArrayList<Product> getMyProductList(Integer userID);

    Product editVisibility(Integer productID, String option);
}
