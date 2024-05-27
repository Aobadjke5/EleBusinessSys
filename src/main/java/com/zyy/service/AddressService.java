package com.zyy.service;

import com.zyy.entity.Address;

import java.util.ArrayList;

public interface AddressService {
    ArrayList<Address> getAddressList(Integer userID);

    Address createNewAddress(Address address, Integer userID);

    boolean deleteAddress(Integer addressID);

    Address editAddress(Address address);
}
