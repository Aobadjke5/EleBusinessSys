package com.zyy.dao;

import com.zyy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserListTest() {
        ArrayList<User> users = userMapper.getUserList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getWaitingUserListTest() {
        ArrayList<User> users = userMapper.getWaitingUserList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void verifyPassTest() {
        Integer userID = 16;
        int num = userMapper.verifyPass(userID);
        System.out.println(num);
    }

    @Test
    public void ForbiddenPassTest() {
        Integer userID = 18;
        int num = userMapper.verifyForbidden(userID);
        System.out.println(num);
    }

    @Test
    public void getUserInfoByIDTest() {
        Integer userID = 16;
        User user = userMapper.getUserInfoByID(userID);
        System.out.println(user);
    }
}
