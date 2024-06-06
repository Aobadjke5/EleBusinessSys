package com.zyy.service.impl;

import com.zyy.dao.OrderMapper;
import com.zyy.dao.ProductMapper;
import com.zyy.entity.Order;
import com.zyy.entity.Product;
import com.zyy.entity.ProductDetail;
import com.zyy.service.ProductService;
import com.zyy.utils.TimestampUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    TimestampUtils timestampUtils;

    @Override
    @Transactional
    public boolean createNewProduct(Product product, Integer userID) {
        ArrayList<ProductDetail> productDetails = product.getProductDetails();

        productMapper.createProduct(product, userID);
        Integer productID = product.getProductID();
        for (ProductDetail productDetail : productDetails) {
            productMapper.insertProductDetail(productDetail, productID);
        }
        return true;
    }

    @Override
    @Transactional
    public Product editProduct(Product product) {
        Integer productID = product.getProductID();
        ArrayList<ProductDetail> productDetails = product.getProductDetails();

        productMapper.editProduct(product);
        productMapper.deleteProductDetails(productID);
        for (ProductDetail productDetail : productDetails) {
            productMapper.insertProductDetail(productDetail, productID);
        }

        Product newProduct = productMapper.getProductByID(productID);
        newProduct.setProductDetails(productMapper.getProductDetails(productID));
        return newProduct;
    }

    @Override
    @Transactional
    public ArrayList<Product> getProductList(String keyWord, Integer pageSize, Integer page){
        Integer start = (page - 1) * pageSize;
        ArrayList<Product> productLists = productMapper.getProductList(start, pageSize, keyWord);

        for (Product productList : productLists){
            productList.setProductDetails(productMapper.getProductDetails(productList.getProductID()));
        }
        return productLists;
    }

    @Override
    public Integer getProductListCnt(String keyWord) {
        return productMapper.getProductListCnt(keyWord);
    }

    @Override
    @Transactional
    public ArrayList<Product> getMyProductList(Integer userID){
        ArrayList<Product> productLists = productMapper.getMyProductList(userID);

        for (Product productList : productLists){
            productList.setProductDetails(productMapper.getProductDetails(productList.getProductID()));
        }
        return productLists;
    }

    @Override
    @Transactional
    public Product editVisibility(Integer productID, String option){
        int num = -1;
        if (option.equals("show")) {
            num = productMapper.editVisibilityYes(productID);
        } else {
            num = productMapper.editVisibilityNo(productID);
        }

        if (num == 1) {
            Product product = productMapper.getProductByID(productID);
            product.setProductDetails(productMapper.getProductDetails(productID));
            return product;
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean purchaseProduct(Order order, Integer userID) {
        Order newOrder = productMapper.purchaseProduct(order.getProDetailID(), order.getProductCnt());
        if (newOrder == null)
            return false;

        if (Math.round(newOrder.getProductPrice() * order.getProductCnt() * 100.0) / 100.0 != order.getTotalPrice())
            return false;

        newOrder.setAddressID(order.getAddressID());
        newOrder.setProductCnt(order.getProductCnt());
        newOrder.setTotalPrice(order.getTotalPrice());
        Timestamp createTime = timestampUtils.getTimestamp(order.getCreateTime());
        int num = orderMapper.createNewOrder(newOrder, userID, createTime);

        if (num != 1)
            return false;
        num = productMapper.purchaseHandleCnt(order.getProDetailID(), order.getProductCnt());
        return num == 1;
    }
}
