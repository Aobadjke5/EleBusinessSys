package com.zyy.service.impl;

import com.zyy.dao.AddressMapper;
import com.zyy.dao.OrderMapper;
import com.zyy.dao.WarehouseMapper;
import com.zyy.entity.Order;
import com.zyy.service.OrderService;
import com.zyy.utils.TimestampUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;

    @Resource
    AddressMapper addressMapper;

    @Resource
    WarehouseMapper warehouseMapper;

    @Resource
    TimestampUtils timestampUtils;

    @Override
    @Transactional
    public ArrayList<Order> getWaitingList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Waiting");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Waiting");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Waiting");
        }

        for (Order order : orderArrayList) {
            order.setAddressInfo(addressMapper.getOrderAddressInfo(order.getAddressID()));
            order.setWarehouseInfo(warehouseMapper.getOrderWarehouseInfo(order.getWarehouseID()));
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public ArrayList<Order> getCancelledList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Cancelled");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Cancelled");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Cancelled");
        }

        for (Order order : orderArrayList) {
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public ArrayList<Order> getCompletedList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Completed");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Completed");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Completed");
        }

        for (Order order : orderArrayList) {
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public ArrayList<Order> getSuccessList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Success");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Success");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Success");
        }

        for (Order order : orderArrayList) {
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public ArrayList<Order> getSendingList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Sending");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Sending");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Sending");
        }

        for (Order order : orderArrayList) {
            order.setAddressInfo(addressMapper.getOrderAddressInfo(order.getAddressID()));
            order.setWarehouseInfo(warehouseMapper.getOrderWarehouseInfo(order.getWarehouseID()));
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public ArrayList<Order> getReturningList(String userRole, Integer userID) {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        if (userRole.equals("Dealer")) {
            orderArrayList = orderMapper.getDealerOrderList(userID, "Returning");
        } else if (userRole.equals("Supplier")) {
            orderArrayList = orderMapper.getSupplierOrderList(userID, "Returning");
        } else if (userRole.equals("Warehouser")) {
            orderArrayList = orderMapper.getWarehouserOrderList(userID, "Returning");
        }

        for (Order order : orderArrayList) {
            order.setAddressInfo(addressMapper.getOrderAddressInfo(order.getAddressID()));
            order.setWarehouseInfo(warehouseMapper.getOrderWarehouseInfo(order.getWarehouseID()));
            order.setDealerID(null);
            order.setSupplierID(null);
            order.setWarehouseID(null);
            order.setAddressID(null);
        }
        return orderArrayList;
    }

    @Override
    @Transactional
    public Boolean cancelOrder(Integer orderID, Long cancelTime) {
        Timestamp cancelTimestamp = timestampUtils.getTimestamp(cancelTime);
        int num = orderMapper.cancelOrder(orderID, cancelTimestamp);
        return num == 1;
    }

    @Override
    @Transactional
    public Boolean deliverOrder(Integer orderID, Long deliverTime) {
        Timestamp deliverTimestamp = timestampUtils.getTimestamp(deliverTime);
        int num = orderMapper.deliverOrder(orderID, deliverTimestamp);
        return num == 1;
    }

    @Override
    @Transactional
    public Boolean receiveOrder(Integer orderID, Long completionTime) {
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        int num = orderMapper.receiveOrder(orderID, completionTimestamp);
        return num == 1;
    }

    @Override
    @Transactional
    public Boolean refuseOrder(Integer orderID, Long completionTime) {
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        int num = orderMapper.refuseOrder(orderID, completionTimestamp);
        return num == 1;
    }

    @Override
    @Transactional
    public Boolean refuseReturnOrder(Integer orderID) {
        int num = orderMapper.refuseReturnOrder(orderID);
        return num == 1;
    }

    @Override
    public Boolean receiveReturnOrder(Integer orderID, Long completionTime) {
        Timestamp completionTimestamp = timestampUtils.getTimestamp(completionTime);
        int num = orderMapper.receiveReturnOrder(orderID, completionTimestamp);
        return num == 1;
    }
}
