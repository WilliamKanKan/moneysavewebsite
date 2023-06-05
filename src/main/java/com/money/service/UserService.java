package com.money.service;

import com.money.entity.UserBean;
import com.money.mapper.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserService {
    //将dao层属性注入service层
    @Resource
    private UserMapper userMapper;

    public UserBean LoginIn(String username, String password) {
        return userMapper.getInfo(username,password);
    }

    public void Insert(String username, String password, String email) {
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userMapper.saveInfo(user);
        System.out.println("Generated id: " + user.getId());
    }
}
