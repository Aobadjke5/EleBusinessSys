package com.zyy.controller;


import com.zyy.dao.UserMapper;
import com.zyy.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.User;
import com.zyy.entity.Visibility;
import com.zyy.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/verify")
    public RestBean<String> verify(@RequestBody Visibility visibility, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Admin") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (Objects.equals(visibility.getOption(), "pass")) {
            if (userService.verify(visibility.getUserID(), "Verified"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
        else {
            if (userService.verify(visibility.getUserID(), "None"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
    }
  
  @GetMapping("/list")
    public List<User> getVerifiedUsers() {
        return userMapper.getVerifiedUsers();
    }

    @GetMapping("/waitinglist")
    public List<User> getNoneUsers() {
        return userMapper.getNoneUsers();
    }
}
