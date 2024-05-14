package com.zyy.service;

import com.zyy.entity.Warehouse;

public interface WarehouseService {
    boolean createNewWarehouse(Warehouse warehouse, Integer userID);
}
