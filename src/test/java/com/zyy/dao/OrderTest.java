package com.zyy.dao;

import com.zyy.entity.Order;
import com.zyy.utils.TimestampUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;

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

    @Test
    public void getDealerOrderListTest() {
        Integer userID = 611;
        String status = "Cancelled";
        ArrayList<Order> orderArrayList = orderMapper.getDealerOrderList(userID, status);
        System.out.println(orderArrayList);
        for (Order order : orderArrayList) {
            System.out.println(order);
        }
    }

    @Test
    public void getSupplierOrderListTest() {
        Integer userID = 61;
        String status = "Cancelled";
        ArrayList<Order> orderArrayList = orderMapper.getSupplierOrderList(userID, status);
        System.out.println(orderArrayList);
        for (Order order : orderArrayList) {
            System.out.println(order);
        }
    }

    @Test
    public void getWarehouserOrderListTest() {
        Integer userID = 63;
        String status = "Cancelled";
        ArrayList<Order> orderArrayList = orderMapper.getWarehouserOrderList(userID, status);
        System.out.println(orderArrayList);
        for (Order order : orderArrayList) {
            System.out.println(order);
        }
    }

    @Test
    public void cancelOrderTest() {
        long cancelTime = 1717315206532L;
        Timestamp cancelTimestamp = timestampUtils.getTimestamp(cancelTime);
        Integer orderID = 6;
        int num = orderMapper.cancelOrder(orderID, cancelTimestamp);
        System.out.println(num);
    }

    @Test
    public void deliverOrderTest() {
        long deliverTime = 1717315206532L;
        Timestamp deliverTimestamp = timestampUtils.getTimestamp(deliverTime);
        Integer orderID = 16;
        int num = orderMapper.deliverOrder(orderID, deliverTimestamp);
        System.out.println(num);
    }

    @Test
    public void receiveOrderTest() {
        long completionTime = 1717315206532L;
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        Integer orderID = 15;
        int num = orderMapper.receiveOrder(orderID, completionTimestamp);
        System.out.println(num);
    }

    @Test
    public void refuseOrderTest() {
        long completionTime = 1717315206532L;
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        Integer orderID = 15;
        int num = orderMapper.refuseOrder(orderID, completionTimestamp);
        System.out.println(num);
    }

    @Test
    public void refuseReturnOrderTest() {
        Integer orderID = 18;
        int num = orderMapper.refuseReturnOrder(orderID);
        System.out.println(num);
    }

    @Test
    public void receiveReturnOrderTest() {
        long completionTime = 1717340959579L;
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        Integer orderID = 19;
        int num = orderMapper.receiveReturnOrder(orderID, completionTimestamp);
        System.out.println(num);
    }
}
