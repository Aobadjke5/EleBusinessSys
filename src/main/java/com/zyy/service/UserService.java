package com.zyy.service;

import com.zyy.entity.Address;
import com.zyy.entity.User;

import java.util.ArrayList;

public interface UserService {

    ArrayList<User> list();

    ArrayList<User> waitingList();

    boolean verify(Integer userID, String option);

    ArrayList<User> personInfo(Integer userID);

    boolean edit(User user);

    boolean addAddress(Address address);

    boolean delAddress(Integer addressID);

    boolean editAddress(Address address);

    ArrayList<Address> addressList(Integer userID);
}
