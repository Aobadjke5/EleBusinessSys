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
    @Transactional
    public Address editAddress(Address address, Integer userID) {
        int num = addressMapper.hadAddressUsed(address.getAddressID());
        if (num == 0) {
            int cnt = addressMapper.editAddress(address);
            if (cnt == 1)
                return address;
            return null;
        } else {
            int cnt = addressMapper.deleteAddress(address.getAddressID());
            if (cnt == 0)
                return null;
            return this.createNewAddress(address, userID);
        }
    }

    @Override
    public Address getDefaultAddress(Integer userID) {
        return addressMapper.getDefaultAddress(userID);
    }
}
