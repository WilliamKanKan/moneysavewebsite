package com.money.controller;

import com.money.entity.UserLoginBean;
import com.money.service.UserLoginService;
import com.money.service.UserShowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@CrossOrigin
public class LoginController {

    //将Service注入Web层
    @Resource
    UserLoginService userLoginService;
    @Resource
    UserShowService userShowService;

    // 实现登录
    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // 处理登录请求
        return userLoginService.login(username,password);
    }
    // 注册
    @PostMapping(value = "/register")
    public String register(@RequestBody UserLoginBean user){
        return userLoginService.register(user);
    }
    // 根据id查询
    @PostMapping(value = "/showuser")
    public UserLoginBean showUser(@RequestParam Integer id){
        return userShowService.showUsers(id);
    }
    // 查询所有
    @PostMapping(value = "/showallusers")
    public List<UserLoginBean> showAllUser(){
        return userShowService.showAllUsers();
    }
    // 根据id删
    @PostMapping(value = "/deleteusers")
    public String deleteUsers(@RequestParam Integer id){
        return userShowService.deleteUserById(id);
    }
    // 根据id更新
    @PostMapping(value = "/updateusers")
    public String updateUsers(@RequestParam Integer id,@RequestBody UserLoginBean user){
        return userShowService.updateUserById(id,user);
    }

}

