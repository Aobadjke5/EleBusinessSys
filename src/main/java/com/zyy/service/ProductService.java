package com.zyy.service;

import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.entity.ProductList;

import java.util.ArrayList;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);

    ArrayList<ProductList> list();

    boolean editProduct(Product product);

    ArrayList<ProductList> mylist(Integer userID);

    boolean editVisibility(Integer productID, String option);
}
