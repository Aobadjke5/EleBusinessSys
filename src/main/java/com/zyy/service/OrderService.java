package com.zyy.service;

import com.zyy.entity.Order;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<Order> getWaitingList(String userRole, Integer userID);

    ArrayList<Order> getCancelledList(String userRole, Integer userID);

    ArrayList<Order> getSendingList(String userRole, Integer userID);

    ArrayList<Order> getCompletedList(String userRole, Integer userID);

    ArrayList<Order> getReturningList(String userRole, Integer userID);

    ArrayList<Order> getSuccessList(String userRole, Integer userID);

    Boolean cancelOrder(Integer orderID, Long cancelTime);

    Boolean deliverOrder(Integer orderID, Long deliverTime);

    Boolean receiveOrder(Integer orderID, Long completionTime);

    Boolean refuseOrder(Integer orderID, Long completionTime);

    Boolean refuseReturnOrder(Integer orderID);

    Boolean receiveReturnOrder(Integer orderID, Long completionTime);
}
