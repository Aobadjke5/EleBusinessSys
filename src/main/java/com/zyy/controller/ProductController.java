package com.zyy.controller;

import com.zyy.entity.*;
import com.zyy.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Resource
    ProductService productService;

    public static class ProductListRestBean {
        public ArrayList<Product> productList;
        public Integer totalCnt;

        public ProductListRestBean() {
        }

        public ProductListRestBean(ArrayList<Product> productList) {
            this.productList = productList;
        }

        public ProductListRestBean(ArrayList<Product> productList, Integer totalCnt) {
            this.productList = productList;
            this.totalCnt = totalCnt;
        }
    }

    public static class ProductRestBean {
        public Product product;

        public ProductRestBean() {
        }

        public ProductRestBean(Product newProduct) {
            this.product = newProduct;
        }
    }

    @RequestMapping("/create")
    public RestBean<String> create(@RequestBody Product product, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!product.verifyParam())
            return RestBean.wrongPara();
        if (!account.getRole().equals("Supplier") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (productService.createNewProduct(product, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/edit")
    public RestBean<ProductRestBean> edit(@RequestBody Product product, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Supplier") && account.getStatus().equals("Verified")))
            return RestBean.unauthorized();

        Product product1 = productService.editProduct(product);
        return RestBean.success(new ProductRestBean(product1));
    }

    public static class RequestParam2 {
        public Integer page;
        public Integer pageSize;
        public String keyWord;

        public RequestParam2() {
        }
    }

    @RequestMapping("/list")
    public RestBean<ProductListRestBean> list(@RequestBody RequestParam2 requestParam2, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Dealer") && account.getStatus().equals("Verified")))
            return RestBean.unauthorized();

        Integer page = requestParam2.page;
        Integer pageSize = requestParam2.pageSize;
        String keyWord = requestParam2.keyWord;

        ArrayList<Product> productLists = productService.getProductList(keyWord, pageSize, page);

        Integer cnt = productService.getProductListCnt(keyWord);

        return RestBean.success(new ProductListRestBean(productLists, cnt));
    }

    @RequestMapping("/manageList")
    public RestBean<ProductListRestBean> manageList(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Supplier") && account.getStatus().equals("Verified")))
            return RestBean.unauthorized();

        ArrayList<Product> myProductList = productService.getMyProductList(account.getUserID());
        return RestBean.success(new ProductListRestBean(myProductList));
    }

    public static class RequestParam1 {
        public Integer productID;
        public String option;

        public RequestParam1() {
        }
    }

    @RequestMapping("/editVisibility")
    public RestBean<ProductRestBean> editVisibility(@RequestBody RequestParam1 requestParam1, HttpServletRequest request){
        Integer productID = requestParam1.productID;
        String option = requestParam1.option;

        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Supplier") && account.getStatus().equals("Verified")))
            return RestBean.unauthorized();

        if (!option.equals("show") && !option.equals("hidden")) {
            return RestBean.wrongPara();
        }

        Product product = productService.editVisibility(productID, option);
        if (product != null)
            return RestBean.success(new ProductRestBean(product));
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/purchase")
    public RestBean<String> purchase(@RequestBody Order purchase, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Dealer") && account.getStatus().equals("Verified")))
            return RestBean.unauthorized();

        if (productService.purchaseProduct(purchase, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }
}
