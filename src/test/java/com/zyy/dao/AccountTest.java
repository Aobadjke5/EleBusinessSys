package com.zyy.dao;

import com.zyy.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void getAccountByUsername() {
        Account account = accountMapper.getAccountByUsername("test");
        System.out.println(account);
    }

    @Test
    public void getAccountByUserIDTest() {
        Integer userID = 59;
        Account account = accountMapper.getAccountByUserID(userID);
        System.out.println(account);
    }

    @Test
    public void changePasswordTest() {
        String passwd = "test123";
        Integer userID = 85;
        int num = accountMapper.changePassword(passwd, userID);
        System.out.println(num);
    }

    @Test
    public void createNewAccount() {
        Account account = new Account();
        account.setUsername("test1");
        account.setPassword("123");
        account.setRole("Dealer");
        account.setStatus("None");
        try {
            int num = accountMapper.createNewAccount(account);
            System.out.println(num);
            System.out.println("插入成功");
        } catch (DuplicateKeyException e) {
            System.out.println("插入失败");
        }
    }
}
