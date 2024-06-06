package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.service.AdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    public static class AdminInfo {
        public String adminImage;
        public String adminName;
        public String adminNickname;

        public AdminInfo() {
        }
    }

    @RequestMapping("/addAdmin")
    public RestBean<String> addAddress(@RequestBody AdminInfo adminInfo, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Admin") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (adminService.createNewAdmin(adminInfo))
            return RestBean.success();
        return RestBean.failure(400, "The username has been registered");
    }
}
