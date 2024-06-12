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
    public void updateCompanyInfoTest() {
        String companyName = "1";
        String companyAddress = "1";
        String role = "Warehouser";
        Integer userID = 42;
        int num = userMapper.updateCompanyInfo(role, companyName, companyAddress, userID);
        System.out.println(num);
    }

    @Test
    public void updateCompanyIconTest() {
        String companyIcon = "1";
        Integer userID = 42;
        int num = userMapper.updateCompanyIcon(companyIcon, userID);
        System.out.println(num);
    }

    @Test
    public void updatePeopleInfoTest() {
        String peopleName = "1";
        String peopleTel = "2";
        String peopleMail = "3";
        Integer userID = 42;
        int num = userMapper.updatePeopleInfo(peopleName, peopleTel, peopleMail, userID);
        System.out.println(num);
    }

    @Test
    public void getVerifyTest() {
        Integer userID = 42;
        int num = userMapper.getVerify(userID);
        System.out.println(num);
    }

    @Test
    public void getUserListTest() {
        String kw = "1";
        ArrayList<User> users = userMapper.getUserList(0, 10, kw);
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
