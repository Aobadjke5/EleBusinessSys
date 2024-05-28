package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.RestBean;
import com.zyy.entity.User;
import com.zyy.service.RegisterService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Resource
    RegisterService registerService;

    @RequestMapping("/step1")
    public RestBean<String> step1(@RequestBody User user, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (account.getRole().equals("Admin") || account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        String role = user.getRole();
        String companyName = user.getCompanyName();
        String companyAddress = user.getCompanyAddress();
        if (role == null || companyName == null || companyAddress == null
                || !(role.equals("Warehouser") || role.equals("Supplier") || role.equals("Dealer")))
            return RestBean.wrongPara();

        if (registerService.setCompanyInfo(role, companyName, companyAddress, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/step2")
    public RestBean<String> step2(@RequestBody User user, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (account.getRole().equals("Admin") || account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        String companyIcon= user.getCompanyIcon();
        if (companyIcon == null)
            return RestBean.wrongPara();

        if (registerService.setCompanyIcon(companyIcon, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }

    @RequestMapping("/step3")
    public RestBean<String> step3(@RequestBody User user, HttpServletRequest request) {
        Account account = (Account) request.getAttribute("accountInfo");
        if (account.getRole().equals("Admin") || account.getStatus().equals("Verified"))
            return RestBean.unauthorized();

        String peopleName = user.getPeopleName();
        String peopleTel = user.getPeopleTel();
        String peopleMail = user.getPeopleMail();
        if (peopleName == null || peopleTel == null || peopleMail == null)
            return RestBean.wrongPara();

        if (registerService.setPeopleInfo(peopleName, peopleTel, peopleMail, account.getUserID()))
            return RestBean.success();
        return RestBean.failure(400, "Operation failure");
    }
}
