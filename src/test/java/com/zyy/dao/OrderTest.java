package com.zyy.dao;

import com.zyy.entity.Order;
import com.zyy.utils.TimestampUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class OrderTest {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    TimestampUtils timestampUtils;

    @Test
    public void createNewOrderTest() {
        Integer userID = 63;
        Order order = new Order();
        order.setProductName("1");
        order.setProductImage("1");
        order.setProDetailName("1");
        order.setSupplierID(63);
        order.setWarehouseID(17);
        order.setProductPrice(1.3);
        order.setProductCnt(1);
        order.setTotalPrice(1.1);
        order.setAddressID(19);
        Timestamp createTime = timestampUtils.getTimestamp(1717221312128L);

        int num = orderMapper.createNewOrder(order, userID, createTime);
        System.out.println(num);
    }
}
