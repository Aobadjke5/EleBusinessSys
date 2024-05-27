package com.zyy.service.impl;

import com.zyy.dao.CenterMapper;
import com.zyy.entity.User;
import com.zyy.service.CenterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CenterServiceImpl implements CenterService {
    @Resource
    CenterMapper centerMapper;

    @Override
    public User getUserInfoByID(Integer userID) {
        return centerMapper.getUserInfoByID(userID);
    }

    @Override
    @Transactional
    public User editUserInfo(User userInfo, Integer userID) {
        int num = centerMapper.editUserInfo(userInfo, userID);
        if (num == 1) {
            return this.getUserInfoByID(userID);
        }
        return null;
    }
}
