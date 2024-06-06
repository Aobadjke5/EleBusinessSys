package com.zyy.service;

import com.zyy.controller.AdminController;

public interface AdminService {
    Boolean createNewAdmin(AdminController.AdminInfo adminInfo);
}
