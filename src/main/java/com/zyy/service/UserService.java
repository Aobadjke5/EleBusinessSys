package com.zyy.service;

import com.zyy.entity.User;

import java.util.ArrayList;

public interface UserService {

    ArrayList<User> getList();

    ArrayList<User> getWaitingList();

    boolean verify(Integer userID, String option);
}

