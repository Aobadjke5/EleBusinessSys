package com.zyy.service;

import com.zyy.entity.Product;

public interface ProductService {
    boolean createNewProduct(Product product, Integer userID);
}
