package com.zyy.dao;

import com.zyy.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void list(){
        System.out.println(userMapper.list());
    }

    @Test
    public void waitingList(){
        System.out.println(userMapper.waitingList());
    }
    @Test
    public void verify(){
        Integer userID = 23;
        int num = userMapper.verify(userID,"Verified");
        System.out.println(num);
    }
    @Test
    public void personInfo(){
        System.out.println(userMapper.personInfo(18));
    }
    @Test
    public void edit(){
        if((userMapper.edit()) == 1)
            System.out.println("Success");
    }
}
