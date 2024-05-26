package com.zyy.service.impl;

import com.zyy.dao.WarehouseMapper;
import com.zyy.entity.Warehouse;
import com.zyy.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public boolean createNewWarehouse(Warehouse warehouse, Integer userID) {
        int cnt = warehouseMapper.createNewWarehouse(warehouse, userID);
        return cnt == 1;
    }

    @Override
    public ArrayList<Warehouse> manageList(Integer userID){
        ArrayList<Warehouse> result;
        result = warehouseMapper.manageList(userID);
        return result;
    }

    @Override
    public ArrayList<Warehouse> list(){
        ArrayList<Warehouse> result;
        result = warehouseMapper.list();
        return result;
    }

    @Override
    public boolean edit(Warehouse warehouse, Integer userID) {
        int cnt = warehouseMapper.edit(warehouse, userID);
        return cnt == 1;
    }

    @Override
    public boolean editVisibility(Integer warehouseID, String option){
        int cnt = warehouseMapper.editVisibility(warehouseID, option);
        return cnt == 1;
    }
}
