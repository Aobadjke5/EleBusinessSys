package com.zyy.service;

import com.zyy.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account getAccountByUsername(String username);

    Account createNewAccount(String username, String password, String role);
}
