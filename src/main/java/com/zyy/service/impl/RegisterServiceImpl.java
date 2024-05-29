package com.zyy.service.impl;

import com.zyy.dao.UserMapper;
import com.zyy.service.RegisterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    UserMapper userMapper;

    @Override
    public Boolean setCompanyInfo(String role, String companyName, String companyAddress, Integer userID) {
        int num = userMapper.updateCompanyInfo(role, companyName, companyAddress, userID);
        return num == 1;
    }

    @Override
    public Boolean setCompanyIcon(String companyIcon, Integer userID) {
        int num = userMapper.updateCompanyIcon(companyIcon, userID);
        return num == 1;
    }

    @Override
    @Transactional
    public Boolean setPeopleInfo(String peopleName, String peopleTel, String peopleMail, Integer userID) {
        int num = userMapper.updatePeopleInfo(peopleName, peopleTel, peopleMail, userID);
        if (num == 1)
            num = userMapper.getVerify(userID);
        return num == 1;
    }
}
