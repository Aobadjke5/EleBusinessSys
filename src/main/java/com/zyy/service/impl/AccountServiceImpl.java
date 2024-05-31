package com.zyy.service.impl;

import com.zyy.dao.AccountMapper;
import com.zyy.entity.Account;
import com.zyy.entity.security.SecurityUser;
import com.zyy.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.getAccountByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException("Username didn't found");
        return new SecurityUser(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountMapper.getAccountByUsername(username);
    }

    @Override
    @Transactional
    public Account createNewAccount(String username, String password, String role) {
        password = new BCryptPasswordEncoder().encode(password);
        role = role.equals("Admin") ? "Admin" : "Dealer";
        String status = role.equals("Admin") ? "Verified" : "None";
        Account newAccount = new Account(username, password, role, status);
        try {
            accountMapper.createNewAccount(newAccount);
            return this.getAccountByUsername(username);
        } catch (DuplicateKeyException e) {
            return null;
        }
    }

    @Override
    public Account getAccountByUserID(Integer userID) {
        return accountMapper.getAccountByUserID(userID);
    }

    @Override
    public Boolean changePassword(String newPassword, Integer userID) {
        newPassword = new BCryptPasswordEncoder().encode(newPassword);
        int num = accountMapper.changePassword(newPassword, userID);
        return num == 1;
    }
}
