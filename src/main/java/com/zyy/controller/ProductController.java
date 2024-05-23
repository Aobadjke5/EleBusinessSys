package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.Product;
import com.zyy.entity.RestBean;
import com.zyy.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}