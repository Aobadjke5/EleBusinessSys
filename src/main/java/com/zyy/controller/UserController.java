package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.User;
import com.zyy.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    public static class UserListRestBean {
        public ArrayList<User> userList;

        public UserListRestBean() {
        }

        public UserListRestBean(ArrayList<User> userList) {
            this.userList = userList;
        }
    }

    public static class RequestParam1 {
        public Integer userID;
        public String option;

        public RequestParam1() {
        }
    }

    @RequestMapping("/verify")
    public RestBean<String> verify(@RequestBody RequestParam1 requestParam1, HttpServletRequest request) {
        Integer userID = requestParam1.userID;
        String option = requestParam1.option;

        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Admin") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        if (!option.equals("forbidden") && !option.equals("pass"))
            return RestBean.wrongPara();

        if (userService.verify(userID, option)) {
            return RestBean.success();
        } else {
            return RestBean.failure(400, "Operation failure");
        }
    }

    public static class RequestParam2 {
        public Integer page;
        public Integer pageSize;
        public String keyWord;

        public RequestParam2() {
        }
    }

    @RequestMapping("/list")
    public RestBean<UserListRestBean> getVerifiedUsers(@RequestBody RequestParam2 requestParam2,  HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Admin") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        Integer page = requestParam2.page;
        Integer pageSize = requestParam2.pageSize;
        String keyWord = requestParam2.keyWord;

        ArrayList<User> userList = userService.getList(keyWord, pageSize, page);
        return RestBean.success(new UserListRestBean(userList));
    }

    @RequestMapping("/waitingList")
    public RestBean<UserListRestBean> getNoneUsers(HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getRole().equals("Admin") || !account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        ArrayList<User> userList = userService.getWaitingList();
        return RestBean.success(new UserListRestBean(userList));
    }
}
