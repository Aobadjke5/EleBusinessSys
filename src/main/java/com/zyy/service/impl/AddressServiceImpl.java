package com.zyy.service.impl;

import com.zyy.dao.AddressMapper;
import com.zyy.entity.Address;
import com.zyy.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public ArrayList<Address> getAddressList(Integer userID) {
        return addressMapper.getAddressList(userID);
    }

    @Override
    @Transactional
    public Address createNewAddress(Address address, Integer userID) {
        int num = addressMapper.createNewAddress(address, userID);
        if(num == 1) {
            return address;
        }
        return null;
    }

    @Override
    public boolean deleteAddress(Integer addressID) {
        int num = addressMapper.deleteAddress(addressID);
        return num == 1;
    }

    @Override
    public Address editAddress(Address address) {
        int num = addressMapper.editAddress(address);
        if (num == 1)
            return address;
        return null;
    }
}
