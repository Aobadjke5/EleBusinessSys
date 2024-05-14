package com.zyy.dao;

import com.zyy.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
