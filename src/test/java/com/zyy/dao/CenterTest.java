package com.zyy.dao;

import com.zyy.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CenterTest {
    @Autowired
    CenterMapper centerMapper;

    @Test
    public void getUserInfoByIDTest() {
        Integer userID = 1;
        User userInfo = centerMapper.getUserInfoByID(userID);
        System.out.println(userInfo);
    }

    @Test
    public void editUserInfoTest() {
        User user = new User();
        user.setCompanyName("1");
        user.setCompanyIcon("1");
        user.setCompanyAddress("1");
        user.setPeopleName("1");
        user.setPeopleTel("1");
        user.setPeopleMail("1");

        int num = centerMapper.editUserInfo(user, 28);
        System.out.println(num);
    }

    @Test
    public void editAdminInfoTest() {
        User user = new User();
        user.setCompanyName("测试Admin");
        user.setCompanyIcon("/api/img/company/538dee83aa13df9ea31879d9c58b43bb");
        int num = centerMapper.editAdminInfo(user, 59);
        System.out.println(num);
    }
}
