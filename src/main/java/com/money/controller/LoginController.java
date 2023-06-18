package com.money.controller;

import com.money.entity.UserLoginBean;
import com.money.service.UserLoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@CrossOrigin
public class LoginController {

    //将Service注入Web层
    @Resource
    UserLoginService userLoginService;

    // 实现登录
    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // 处理登录请求
        return userLoginService.login(username,password);
    }
    @PostMapping(value = "/register")
    public String register(@RequestBody UserLoginBean user){
        return userLoginService.register(user);
    }

}

