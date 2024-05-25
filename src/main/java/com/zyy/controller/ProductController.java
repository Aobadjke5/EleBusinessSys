package com.zyy.controller;

import com.zyy.entity.*;
import com.zyy.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Resource
    ProductService productService;

    @RequestMapping("/create")
    public RestBean<String> create(@RequestBody Product product, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!product.verifyParam())
            return RestBean.wrongPara();
        if (!account.getRole().equals("Supplier") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (productService.createNewProduct(product, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(500, "Server error, creation failed");
    }

    @RequestMapping("/edit")
    public RestBean<String> edit(@RequestBody Product product, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Supplier") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (productService.editProduct(product))
            return RestBean.success();
        return RestBean.failure(400, "edition failure");
    }

    @RequestMapping("/list")
    public RestBean<ArrayList<ProductList>> list(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Supplier") || account.getRole().equals("Admin") )|| ! account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<ProductList> productLists = productService.list();
        return RestBean.success(productLists);
    }

    @RequestMapping("/mylist")
    public RestBean<ArrayList<ProductList>> mylist(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!(account.getRole().equals("Supplier") || account.getRole().equals("Admin") )|| ! account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<ProductList> mylists = productService.mylist(account.getUserID());
        return RestBean.success(mylists);
    }

    @RequestMapping("/editVisibility")
    public RestBean<String> editVisibility(@RequestBody Visibility visibility, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Supplier") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (Objects.equals(visibility.getOption(), "show")) {
            if (productService.editVisibility(visibility.getProductID(), "Yes"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
        else {
            if (productService.editVisibility(visibility.getProductID(), "No"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
    }
}
