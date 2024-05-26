package com.zyy.service.impl;

import com.zyy.dao.UserMapper;
import com.zyy.entity.Address;
import com.zyy.entity.User;
import com.zyy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ArrayList<User> list(){
        ArrayList<User> users;
        users = userMapper.list();
        return users;
    }

    @Override
    public ArrayList<User> waitingList(){
        ArrayList<User> users;
        users = userMapper.waitingList();
        return users;
    }

    @Override
    public boolean verify(Integer userID, String option){
        int cnt = userMapper.verify(userID, option);
        return cnt == 1;
    }

    @Override
    public ArrayList<User> personInfo(Integer userID){
        ArrayList<User> personinfo ;
        personinfo = userMapper.personInfo(userID);
        return personinfo;
    }
    @Override
    public boolean edit(User user){
        int cnt = userMapper.edit(user);
        return cnt == 1;
    }
    @Override
    public boolean addAddress(Address address){
        int cnt = userMapper.addAddress(address);
        return cnt == 1;
    }
    @Override
    public boolean delAddress(Integer addressID){
        int cnt = userMapper.delAddress(addressID);
        return cnt == 1;
    }
    @Override
    public boolean editAddress(Address address){
        int cnt = userMapper.editAddress(address);
        return cnt == 1;
    }
    @Override
    public ArrayList<Address> addressList(Integer userID){
        ArrayList<Address> addresses;
        addresses = userMapper.addressList(userID);
        return addresses;
    }
}
