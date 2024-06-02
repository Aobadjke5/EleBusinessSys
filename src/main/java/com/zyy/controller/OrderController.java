package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.Order;
import com.zyy.entity.RestBean;
import com.zyy.service.OrderService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    OrderService orderService;

    public static class OrderListRestBean {
        public ArrayList<Order> orderList;

        public OrderListRestBean() {
        }

        public OrderListRestBean(ArrayList<Order> orderList) {
            this.orderList = orderList;
        }
    }

    @RequestMapping("/waitingList")
    public RestBean<OrderListRestBean> waitingList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getWaitingList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    @RequestMapping("/cancelledList")
    public RestBean<OrderListRestBean> cancelledList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getCancelledList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    @RequestMapping("/completedList")
    public RestBean<OrderListRestBean> completedList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getCompletedList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    @RequestMapping("/sendingList")
    public RestBean<OrderListRestBean> sendingList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getSendingList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    @RequestMapping("/returningList")
    public RestBean<OrderListRestBean> returningList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getReturningList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    @RequestMapping("/successList")
    public RestBean<OrderListRestBean> successList(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Order> orders = orderService.getSuccessList(account.getRole(), account.getUserID());
        return RestBean.success(new OrderListRestBean(orders));
    }

    public static class RequestParam1 {
        public Integer orderID;
        public Long cancelTime;
        public Long deliverTime;
        public Long completionTime;

        public RequestParam1() {
        }
    }

    @RequestMapping("/cancel")
    public RestBean<String> cancelOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Dealer"))
            return RestBean.unauthorized();

        if (orderService.cancelOrder(requestParam1.orderID, requestParam1.cancelTime))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/deliver")
    public RestBean<String> deliverOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Warehouser"))
            return RestBean.unauthorized();

        if (orderService.deliverOrder(requestParam1.orderID, requestParam1.deliverTime))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/receive")
    public RestBean<String> receiveOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Dealer"))
            return RestBean.unauthorized();

        if (orderService.receiveOrder(requestParam1.orderID, requestParam1.completionTime))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/refuse")
    public RestBean<String> refuseOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Dealer"))
            return RestBean.unauthorized();

        if (orderService.refuseOrder(requestParam1.orderID, requestParam1.completionTime))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/refuseReturn")
    public RestBean<String> refuseReturnOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Warehouser"))
            return RestBean.unauthorized();

        if (orderService.refuseReturnOrder(requestParam1.orderID))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/receiveReturn")
    public RestBean<String> receiveReturnOrder(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified") || !account.getRole().equals("Warehouser"))
            return RestBean.unauthorized();

        if (orderService.receiveReturnOrder(requestParam1.orderID, requestParam1.completionTime))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }
}
