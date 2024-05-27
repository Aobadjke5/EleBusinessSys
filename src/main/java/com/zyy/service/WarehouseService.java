package com.zyy.service;

import com.zyy.entity.Warehouse;
import java.util.ArrayList;

public interface WarehouseService {
    boolean createNewWarehouse(Warehouse warehouse, Integer userID);

    ArrayList<Warehouse> getMyWarehouseList(Integer userID);

    ArrayList<Warehouse> getWarehouseList();

    Warehouse editWarehouse(Warehouse warehouse);

    Warehouse editVisibility(Integer warehouseID, String option);
}
