package com.zyy.service.impl;

import com.zyy.dao.UserMapper;
import com.zyy.dao.WarehouseMapper;
import com.zyy.entity.Warehouse;
import com.zyy.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean createNewWarehouse(Warehouse warehouse, Integer userID) {
        int cnt = warehouseMapper.createNewWarehouse(warehouse, userID);
        return cnt == 1;
    }

    @Override
    public ArrayList<Warehouse> getMyWarehouseList(Integer userID){
        return warehouseMapper.getWarehouseListByID(userID);
    }

    @Override
    @Transactional
    public ArrayList<Warehouse> getWarehouseList(){
        ArrayList<Warehouse> warehouseArrayList = warehouseMapper.getWarehouseList();

        for (Warehouse warehouse : warehouseArrayList) {
            warehouse.setCompanyInfo(userMapper.getUserInfoByID(warehouse.getUserID()));
            warehouse.setUserID(null);
        }
        return warehouseArrayList;
    }

    @Override
    @Transactional
    public Warehouse editWarehouse(Warehouse warehouse) {
        int num = warehouseMapper.editWarehouse(warehouse);
        if (num == 1)
            return warehouseMapper.getWarehouseByID(warehouse.getWarehouseID());
        return null;
    }

    @Override
    @Transactional
    public Warehouse editVisibility(Integer warehouseID, String option){
        int num = -1;
        if (option.equals("show")) {
            num = warehouseMapper.editVisibilityYes(warehouseID);
        }
        if (option.equals("hidden")) {
            num = warehouseMapper.editVisibilityNo(warehouseID);
        }

        if (num == 1)
            return warehouseMapper.getWarehouseByID(warehouseID);
        return null;
    }
}
