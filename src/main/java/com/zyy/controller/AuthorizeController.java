package com.zyy.controller;

import com.zyy.entity.Account;
import com.zyy.entity.JwtItem;
import com.zyy.entity.RestBean;
import com.zyy.service.AccountService;
import com.zyy.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    @Resource
    AccountService accountService;

    @Resource
    JwtUtils jwtUtils;

    public static class RegisterParams {
        public String username;
        public String password;

        @Override
        public String toString() {
            return "RegisterParams{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @RequestMapping("/register")
    public RestBean<JwtItem> register(@RequestBody RegisterParams registerParams) {
        if(registerParams.password == null || registerParams.username == null)
            return RestBean.wrongPara();
        Account account = accountService.createNewAccount(registerParams.username, registerParams.password, "Dealer");
        if(account == null)
            return RestBean.failure(400, "The username has been registered");
        JwtItem jwtItem = jwtUtils.createJWT(account.getUserID(), account.getUsername(), account.getRole(), account.getStatus());
        return RestBean.success(jwtItem);
    }

    public static class RequestParam2 {
        public String oldPassword;
        public String newPassword;

        public RequestParam2() {
        }
    }

    @RequestMapping("/changePw")
    public RestBean<String> changePw(@RequestBody RequestParam2 requestParam2, HttpServletRequest request) {
        String oldPassword = requestParam2.oldPassword;
        String newPassword = requestParam2.newPassword;

        Account account = (Account) request.getAttribute("accountInfo");
        if (!account.getStatus().equals("Verified"))
            return RestBean.unauthorized();
        if (oldPassword == null || newPassword == null)
            return RestBean.wrongPara();

        String password = accountService.getAccountByUserID(account.getUserID()).getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPassword, password)) {
            if (accountService.changePassword(newPassword, account.getUserID())) {
                return RestBean.success();
            }
            return RestBean.failure(400, "Operation failure");
        }
        return RestBean.failure(40002, "Old password error");
    }
}
