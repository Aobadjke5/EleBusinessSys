package com.zyy.service.impl;

import com.zyy.controller.AdminController;
import com.zyy.dao.AccountMapper;
import com.zyy.service.AdminService;
import com.zyy.utils.EncipherUtils;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AccountMapper accountMapper;

    @Resource
    EncipherUtils encipherUtils;

    private final String defaultAdminPasswd = "admin123";

    @Override
    public Boolean createNewAdmin(AdminController.AdminInfo adminInfo) {
        String password = encipherUtils.getMD5(defaultAdminPasswd);
        password = new BCryptPasswordEncoder().encode(password);
        try {
            int num = accountMapper.createNewAdminAccount(adminInfo, password);
            return num == 1;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }
}
