package com.zyy.dao;

import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ProductTest {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;

    @Test
    public void createNewProducts() {
        Product product = new Product();
        product.setProductName("产品1");
        product.setProductImage("/api/img/testImage");
        product.setWarehouseID(1);
        product.setStatus("Yes");
        Integer userID = 1;

        ProductDetail productDetail1 = new ProductDetail();
        productDetail1.setProductName("111");
        productDetail1.setProductImage("/api/img/testImage");
        productDetail1.setProductCnt(1);
        productDetail1.setProductPrice(1);
        ProductDetail productDetail2 = new ProductDetail();
        productDetail2.setProductName("111");
        productDetail2.setProductImage("/api/img/testImage");
        productDetail2.setProductCnt(1);
        productDetail2.setProductPrice(1);

        ArrayList<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail1);
        productDetails.add(productDetail2);

        product.setProductDetails(productDetails);

        productService.createNewProduct(product, userID);
    }
}
