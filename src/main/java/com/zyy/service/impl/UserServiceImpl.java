package com.zyy.service.impl;

import com.zyy.dao.UserMapper;
import com.zyy.entity.User;
import com.zyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getVerifiedUsers() {
        return userMapper.getVerifiedUsers();
    }

    @Override
    public List<User> getNoneUsers() {
        return userMapper.getNoneUsers();
    }
}

