package com.zyy.dao;

import com.zyy.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List; // 导入 List 接口
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper; //

    @Test
    public void testGetVerifiedUsers() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole("Dealer");
        user.setStatus("Verified");
        user.setUserID(16);
        user.setCompanyIcon("jsjsjs");
        user.setCompanyAddress("台湾省");
        user.setPeopleMail("1234@qq.com");
        user.setCompanyName("清华公司");
        user.setPeopleTel("123");



        List<User> verifiedUserList = userMapper.getVerifiedUsers();


        Assertions.assertFalse(verifiedUserList.isEmpty(), "Verified user list should not be empty");

        // 输出用户列表
        System.out.println("Verified Users:");

            System.out.println(user);

    }
    @Test
    public void testGetNoneUsers() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole("Dealer");
        user.setStatus("Verified");
        user.setUserID(16);
        user.setCompanyIcon("jsjsjs");
        user.setCompanyAddress("台湾省");
        user.setPeopleMail("1234@qq.com");
        user.setCompanyName("清华公司");
        user.setPeopleTel("123");


        // 调用 getVerifiedUsers 方法获取已验证用户列表
        List<User> verifiedUserList = userMapper.getVerifiedUsers();

        // 输出用户列表
        System.out.println("Verified Users (should be empty):");

            System.out.println(user); //
    }

}




