package com.zyy.controller;


import com.zyy.entity.Account;
import com.zyy.entity.Product;
import com.zyy.entity.RestBean;
import com.zyy.entity.Order;
import com.zyy.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.swing.border.Border;
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


    @RequestMapping("/purchase")
    public RestBean<Integer> purchase(@RequestBody Product product, HttpServletRequest request) {
        try {
            // 从请求中获取账户信息
            Account account = (Account) request.getAttribute("accountInfo");
            boolean success = createProductWithOrder(product, account);
            if (success) {
                // 购买成功，返回状态码200
                return RestBean.success(200);
            } else {
                // 购买失败，返回状态码400和失败信息
                return RestBean.failure(400, "购买失败");
            }
        } catch (Exception e) {
            // 处理异常，返回适当的错误响应
            return RestBean.failure(500, "服务器内部错误: " + e.getMessage());
        }
    }

    private boolean createProductWithOrder(Product product, Account account) {
        return true;
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




