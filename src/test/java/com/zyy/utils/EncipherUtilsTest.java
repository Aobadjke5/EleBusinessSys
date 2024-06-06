package com.zyy.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncipherUtilsTest {
    @Autowired
    EncipherUtils encipherUtils;

    @Test
    public void md5Test() {
        String md5 = encipherUtils.getMD5("123456");
        System.out.println(md5);
    }
}
