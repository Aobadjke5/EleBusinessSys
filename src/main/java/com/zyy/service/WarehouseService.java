package com.zyy.service;

import com.zyy.entity.Warehouse;
import java.util.ArrayList;

public interface WarehouseService {
    boolean createNewWarehouse(Warehouse warehouse, Integer userID);

    ArrayList<Warehouse> manageList(Integer userID);

    ArrayList<Warehouse> list();

    boolean edit(Warehouse warehouse, Integer userID);

    boolean editVisibility(Integer warehouseID, String option);
}
