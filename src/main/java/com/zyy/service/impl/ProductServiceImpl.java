package com.zyy.service.impl;

import com.zyy.dao.ProductMapper;
import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
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
}
