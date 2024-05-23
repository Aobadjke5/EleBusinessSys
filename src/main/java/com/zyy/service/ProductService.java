package com.zyy.service;

import com.zyy.entity.Order;
import com.zyy.entity.Product;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);

    boolean createProductWithOrder(Product product, Order order, Integer orderID);
}
