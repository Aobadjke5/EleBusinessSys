package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.Warehouse;
import com.zyy.service.WarehouseService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Resource
    WarehouseService warehouseService;

    @RequestMapping("/create")
    public RestBean<String> create(@RequestBody Warehouse warehouse, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!warehouse.verifyParam())
            return RestBean.wrongPara();
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if(warehouseService.createNewWarehouse(warehouse, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(500, "Server error, creation failed");
    }
}
