package com.money.controller;

import com.money.entity.UserLoginBean;
import com.money.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LoginController {

    //将Service注入Web层
    @Autowired
    UserLoginService userLoginService;

   // 实现登录
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserLoginBean userBean = userLoginService.userLogin(username, password);
        if(userBean!=null){
            return "success";
        }else {
            return "error";
        }
    }

    //实现注册功能
    @PostMapping(value = "/register")
    public String signUp(@RequestBody UserLoginBean user) {
            String username = user.getUsername();
            String password = user.getPassword();
            String confirmPassword = user.getConfirmPassword();
            String email = user.getEmail();
            if(password.equals(confirmPassword)){
                userLoginService.userRegister(username, password, email);
                System.out.println("注册成功了");
                return "success";
            }else {
                return "fail";
            }
    }
}

