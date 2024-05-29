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

    private Product getProduct() {
        Product product = new Product();
        product.setProductName("测试产品");
        product.setProductImage("test");
        product.setWarehouseID(1);
        product.setStatus("Yes");

        ProductDetail productDetail1 = new ProductDetail();
        productDetail1.setProductName("111");
        productDetail1.setProductImage("/api/img/testImage");
        productDetail1.setProductCnt(1);
        productDetail1.setProductPrice(9.9);
        ProductDetail productDetail2 = new ProductDetail();
        productDetail2.setProductName("222");
        productDetail2.setProductImage("/api/img/testImage");
        productDetail2.setProductCnt(1);
        productDetail2.setProductPrice(0.3);

        ArrayList<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail1);
        productDetails.add(productDetail2);

        product.setProductDetails(productDetails);
        return product;
    }

    private Product getProduct2(Integer productID) {
        Product product = new Product();
        product.setProductName("测试产品2222222");
        product.setProductImage("test");
        product.setWarehouseID(1);
        product.setProductID(productID);

        ProductDetail productDetail1 = new ProductDetail();
        productDetail1.setProductName("------");
        productDetail1.setProductImage("/api/img/testImage");
        productDetail1.setProductCnt(1);
        productDetail1.setProductPrice(9.9);
        ProductDetail productDetail2 = new ProductDetail();
        productDetail2.setProductName("++++++");
        productDetail2.setProductImage("/api/img/testImage");
        productDetail2.setProductCnt(1);
        productDetail2.setProductPrice(0.3);

        ArrayList<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail1);
        productDetails.add(productDetail2);

        product.setProductDetails(productDetails);
        return product;
    }

    @Test
    public void createProductTest() {
        Product product = this.getProduct();
        Integer userID = 1;

        int num = productMapper.createProduct(product, userID);
        System.out.println(num);
        System.out.println("productID:" + product.getProductID());
    }

    @Test
    public void insertProductDetailTest() {
        Product product = this.getProduct();
        ArrayList<ProductDetail> productDetails = product.getProductDetails();

        Integer productID = 29;
        for (ProductDetail productDetail : productDetails) {
            int num = productMapper.insertProductDetail(productDetail, productID);
            System.out.println(num);
        }
    }

    @Test
    public void create() {
        Product product = this.getProduct();
        Integer userID = 1;

        boolean bool = productService.createNewProduct(product, userID);
        System.out.println(bool);
    }

    @Test
    public void editProductTest() {
        Product product = this.getProduct2(31);
        int num = productMapper.editProduct(product);
        System.out.println(num);
    }

    @Test
    public void deleteProductDetailsTest() {
        Integer productID = 31;
        int num = productMapper.deleteProductDetails(productID);
        System.out.println(num);
    }

    @Test
    public void getProductByIDTest() {
        Integer productID = 32;
        Product product = productMapper.getProductByID(productID);
        System.out.println(product);
    }

    @Test
    public void getProductDetailsTest() {
        Integer productID = 32;
        ArrayList<ProductDetail> productDetails = productMapper.getProductDetails(productID);
        for (ProductDetail productDetail : productDetails) {
            System.out.println(productDetail);
        }
    }

    @Test
    public void edit() {
        Product product = this.getProduct2(32);
        Product newProduct = productService.editProduct(product);
        System.out.println(newProduct);
    }

    @Test
    public void editVisibilityYesTest() {
        Integer productID = 33;
        int num = productMapper.editVisibilityYes(productID);
        System.out.println(num);
    }

    @Test
    public void editVisibilityNoTest() {
        Integer productID = 33;
        int num = productMapper.editVisibilityNo(productID);
        System.out.println(num);
    }

    @Test
    public void getMyProductListTest() {
        Integer userID = 19;
        ArrayList<Product> products = productMapper.getMyProductList(userID);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void getProductListTest() {
        ArrayList<Product> products = productMapper.getProductList();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
