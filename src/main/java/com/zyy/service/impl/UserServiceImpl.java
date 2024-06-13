package com.zyy.service.impl;

import com.zyy.dao.UserMapper;
import com.zyy.entity.User;
import com.zyy.service.UserService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ArrayList<User> getList(String keyWord, String role, Integer pageSize, Integer page) {
        Integer start = (page - 1) * pageSize;
        return userMapper.getUserList(start, pageSize, keyWord, role);
    }

    @Override
    public ArrayList<User> getWaitingList() {
        return userMapper.getWaitingUserList();
    }

    @Override
    public boolean verify(Integer userID, String option){
        int num = -1;
        if (option.equals("forbidden"))
            num = userMapper.verifyForbidden(userID);
        if (option.equals("pass"))
            num = userMapper.verifyPass(userID);
        return num == 1;
    }
}

