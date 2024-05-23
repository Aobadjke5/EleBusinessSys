package com.zyy.controller;

import com.zyy.dao.UserMapper;
import com.zyy.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("/api/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/list")
    public List<User> getVerifiedUsers() {
        return userMapper.getVerifiedUsers();
    }

    @GetMapping("/waitinglist")
    public List<User> getNoneUsers() {
        return userMapper.getNoneUsers();
    }
}