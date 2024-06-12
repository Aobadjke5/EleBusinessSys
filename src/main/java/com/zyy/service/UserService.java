package com.zyy.service;

import com.zyy.entity.User;

import java.util.ArrayList;

public interface UserService {

    ArrayList<User> getList(String keyWord, Integer pageSize, Integer page);

    ArrayList<User> getWaitingList();

    boolean verify(Integer userID, String option);
}

