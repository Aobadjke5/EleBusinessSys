package com.zyy.service.impl;

import com.zyy.dao.OrderMapper;
import com.zyy.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;

}
