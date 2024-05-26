package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.Warehouse;
import com.zyy.entity.Visibility;
import com.zyy.service.WarehouseService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

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

    @RequestMapping("/manageList")
    public RestBean<ArrayList<Warehouse>> manageList(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Warehouse> warehouses = warehouseService.manageList(account.getUserID());
        return RestBean.success(warehouses);
    }

    @RequestMapping("/list")
    public RestBean<ArrayList<Warehouse>> list(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Supplier") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Warehouse> warehouses = warehouseService.list();
        return RestBean.success(warehouses);
    }

    @RequestMapping("/edit")
    public RestBean<String> edit(@RequestBody Warehouse warehouse, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (warehouseService.edit(warehouse,account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Error, edition failed");
    }

    @RequestMapping("/editVisibility")
    public RestBean<String> editVisibility(@RequestBody Visibility visibility, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (Objects.equals(visibility.getOption(), "show")) {
            if (warehouseService.editVisibility(visibility.getWarehouseID(), "Yes"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
        else {
            if (warehouseService.editVisibility(visibility.getWarehouseID(), "No"))
                return RestBean.success();
            else
                return RestBean.failure(400, "edition failure");
        }
    }
}
