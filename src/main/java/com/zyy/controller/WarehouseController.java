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

import java.util.ArrayList;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Resource
    WarehouseService warehouseService;

    public static class WarehouseListRestBean {
        public ArrayList<Warehouse> warehouseList;

        public WarehouseListRestBean() {
        }

        public WarehouseListRestBean(ArrayList<Warehouse> warehouseList) {
            this.warehouseList = warehouseList;
        }
    }

    public static class WarehouseRestBean {
        public Warehouse warehouse;

        public WarehouseRestBean() {
        }

        public WarehouseRestBean(Warehouse warehouse) {
            this.warehouse = warehouse;
        }
    }

    @RequestMapping("/create")
    public RestBean<String> create(@RequestBody Warehouse warehouse, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!warehouse.verifyParam())
            return RestBean.wrongPara();
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if(warehouseService.createNewWarehouse(warehouse, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/manageList")
    public RestBean<WarehouseListRestBean> manageList(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Warehouse> warehouses = warehouseService.getMyWarehouseList(account.getUserID());
        return RestBean.success(new WarehouseListRestBean(warehouses));
    }

    @RequestMapping("/list")
    public RestBean<WarehouseListRestBean> list(HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<Warehouse> warehouses = warehouseService.getWarehouseList();
        return RestBean.success(new WarehouseListRestBean(warehouses));
    }

    @RequestMapping("/edit")
    public RestBean<WarehouseRestBean> edit(@RequestBody Warehouse warehouse, HttpServletRequest request){
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        Warehouse warehouse1 = warehouseService.editWarehouse(warehouse);
        if (warehouse1 != null)
            return RestBean.success(new WarehouseRestBean(warehouse1));
        return RestBean.failure(400, "Operation failure");
    }

    public static class RequestParam1 {
        public Integer warehouseID;
        public String option;

        public RequestParam1() {
        }
    }

    @RequestMapping("/editVisibility")
    public RestBean<WarehouseRestBean> editVisibility(@RequestBody RequestParam1 requestParam1, HttpServletRequest request){
        Integer warehouseID = requestParam1.warehouseID;
        String option = requestParam1.option;

        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Warehouser") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (!option.equals("show") && !option.equals("hidden"))
            return RestBean.wrongPara();


        Warehouse warehouse = warehouseService.editVisibility(warehouseID, option);
        if(warehouse != null)
            return RestBean.success(new WarehouseRestBean(warehouse));
        return RestBean.failure(400, "Operation failure");
    }
}
