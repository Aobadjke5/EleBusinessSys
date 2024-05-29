package com.zyy.dao;

import com.zyy.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class AddressTest {
    @Autowired
    AddressMapper addressMapper;

    @Test
    public void getAddressListTest() {
        Integer userID = 1;
        ArrayList<Address> addresses = addressMapper.getAddressList(userID);
        System.out.println(addresses);
    }

    @Test
    public void createNewAddressTest() {
        Address address = new Address("人民北路", "张三", "121213");
        int num = addressMapper.createNewAddress(address, 19);
        System.out.println(num);
        System.out.println(address);
    }

    @Test
    public void deleteAddressTest() {
        Integer addressID = 2;
        int num = addressMapper.deleteAddress(addressID);
        System.out.println(num);
    }

    @Test
    public void editAddressTest() {
        Address address = new Address(3, "1", "1", "1");
        int num = addressMapper.editAddress(address);
        System.out.println(num);
    }

    @Test
    public void getDefaultAddressTest() {
        Integer userID = 61;
        Address address = addressMapper.getDefaultAddress(userID);
        System.out.println(address);
    }
}
