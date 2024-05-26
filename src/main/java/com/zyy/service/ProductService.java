package com.zyy.service;

import com.zyy.entity.Order;
import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.entity.ProductList;

import java.util.ArrayList;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);


    boolean createProductWithOrder(Product product, Order order, Integer orderID);

    ArrayList<ProductList> list();

    boolean editProduct(Product product);

    ArrayList<ProductList> mylist(Integer userID);

    boolean editVisibility(Integer productID, String option);

}
