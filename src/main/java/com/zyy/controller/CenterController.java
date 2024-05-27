package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.User;
import com.zyy.service.CenterService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/center")
public class CenterController {
    @Resource
    CenterService centerService;

    @RequestMapping("/personInfo")
    public RestBean<User> personInfo(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        User userInfo = centerService.getUserInfoByID(account.getUserID());
        return RestBean.success(userInfo);
    }

    @RequestMapping("/edit")
    public RestBean<User> edit(@RequestBody User user, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        String role = account.getRole();
        if (!account.getStatus().equals("Verified") ||
                !(role.equals("Warehouser") || role.equals("Supplier") || role.equals("Dealer")))
            return RestBean.unauthorized();

        User newUser = centerService.editUserInfo(user, account.getUserID());
        if (newUser != null) {
            return RestBean.success(newUser);
        }
        return RestBean.failure(400, "Operation failure");
    }
}
