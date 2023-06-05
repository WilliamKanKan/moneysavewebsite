package com.money.controller;

import com.money.entity.UserBean;
import com.money.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Slf4j
@RestController
public class LoginController {

    //将Service注入Web层
    @Resource
    UserService userService;

    //实现登录
    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String username,String password){
        UserBean userBean = userService.LoginIn(username, password);
        log.info("username:{}",username);
        log.info("password:{}",password);
        if(userBean!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @RequestMapping("/signup")
    public String disp(){
        return "signup";
    }

    //实现注册功能
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String signUp(@RequestBody UserBean user) {
            String username = user.getUsername();
            String password = user.getPassword();
            String confirmPassword = user.getConfirmPassword();
            String email = user.getEmail();
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println("email: " + email);
            System.out.println("confirmPassword: " + confirmPassword);
            if(password.equals(confirmPassword)){
                userService.Insert(username, password, email);
                System.out.println("注册成功了");
                return "success";
            }else if(!password.equals(confirmPassword)){
                return "fail";
            }else {
                return "error";
            }
    }
}

