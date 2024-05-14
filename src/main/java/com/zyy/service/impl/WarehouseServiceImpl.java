package com.zyy.service.impl;

import com.zyy.dao.WarehouseMapper;
import com.zyy.entity.Warehouse;
import com.zyy.service.WarehouseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Resource
    private WarehouseMapper warehouseMapper;

    @Override
    public boolean createNewWarehouse(Warehouse warehouse, Integer userID) {
        int cnt = warehouseMapper.createNewWarehouse(warehouse, userID);
        return cnt == 1;
    }
}
