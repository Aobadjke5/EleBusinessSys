package com.zyy.service;

import com.zyy.entity.User;
import java.util.List;

public interface UserService {

    List<User> getVerifiedUsers();

    List<User> getNoneUsers();


}