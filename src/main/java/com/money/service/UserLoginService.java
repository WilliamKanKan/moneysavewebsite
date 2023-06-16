package com.money.service;

import com.money.entity.UserLoginBean;
import com.money.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    //将mapper层属性注入service层
    @Autowired
    private UserLoginMapper userLoginMapper;
    public UserLoginBean userLogin(String username, String password) {
        return userLoginMapper.findByUsernameAndPassword(username,password);
    }

    public void Insert(String username, String password, String email) {
        UserLoginBean user = new UserLoginBean();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userLoginMapper.createNewUsers(user);
    }
}
