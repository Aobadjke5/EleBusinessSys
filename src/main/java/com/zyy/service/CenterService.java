package com.zyy.service;

import com.zyy.entity.User;

public interface CenterService {
    User getUserInfoByID(Integer userID);

    User editUserInfo(User userInfo, Integer userID);
}
