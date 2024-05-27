package com.zyy.dao;

import com.zyy.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class WarehouseTest {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Test
    public void getWarehouseListByIDTest() {
        ArrayList<Warehouse> warehouses = warehouseMapper.getWarehouseListByID(16);
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
    }

    @Test
    public void getWarehouseListTest() {
        ArrayList<Warehouse> warehouses = warehouseMapper.getWarehouseList();
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
    }

    @Test
    public void createNewWarehouseTest() {
        Integer userID = 1;
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("仓库1");
        warehouse.setWarehouseAddress("address");
        warehouse.setWarehouseImage("/testUrl");
        warehouse.setStatus("Yes");
        warehouse.setTotalCapacity(100);
        warehouse.setCurrentCapacity(21);

        int num = warehouseMapper.createNewWarehouse(warehouse, userID);
        System.out.println(num);
    }

    @Test
    public void editWarehouseTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("新仓库");
        warehouse.setWarehouseAddress("1");
        warehouse.setWarehouseImage("1");
        warehouse.setTotalCapacity(100);
        warehouse.setCurrentCapacity(21);
        warehouse.setWarehouseID(14);

        int num = warehouseMapper.editWarehouse(warehouse);
        System.out.println(num);
    }

    @Test
    public void getWarehouseByIDTest() {
        Integer warehouseID = 14;
        Warehouse warehouse = warehouseMapper.getWarehouseByID(warehouseID);
        System.out.println(warehouse);
    }

    @Test
    public void editVisibilityYesTest() {
        Integer warehouseID = 14;
        int num = warehouseMapper.editVisibilityYes(warehouseID);
        System.out.println(num);
    }

    @Test
    public void editVisibilityNoTest() {
        Integer warehouseID = 14;
        int num = warehouseMapper.editVisibilityNo(warehouseID);
        System.out.println(num);
    }
}
