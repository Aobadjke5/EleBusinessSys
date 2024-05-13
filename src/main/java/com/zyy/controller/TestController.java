package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public static class Item {
        public String a;

        @Override
        public String toString() {
            return "Item{" +
                    "a='" + a + '\'' +
                    '}';
        }
    }

    @RequestMapping("/test")
    public RestBean<Account> test(@RequestBody Item item, HttpServletRequest request) {
        System.out.println(item);
        Account account = (Account) request.getAttribute("accountInfo");
        return RestBean.success(account);
    }
}
