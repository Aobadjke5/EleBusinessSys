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
    public void createNewWarehouse() {
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
    public void manageList(){
        ArrayList<Warehouse> temp;
        temp = warehouseMapper.manageList(16);
        System.out.println(temp);
    }

    @Test
    public void list(){
        ArrayList<Warehouse> temp;
        temp = warehouseMapper.list();
        System.out.println(temp);
    }
    @Test
    public void edit(){
        Integer UserID = 16;
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseID(7);
        warehouse.setWarehouseName("上海市第一仓库");
        warehouse.setWarehouseAddress("test_address");
        warehouse.setWarehouseImage("/testUrl");
        warehouse.setStatus("Yes");
        warehouse.setTotalCapacity(123);
        warehouse.setCurrentCapacity(123);
        int num = warehouseMapper.edit(warehouse,UserID);
        System.out.println(num);
    }
}
