package com.money.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.money.entity.UserLoginBean;
import com.money.mapper.UserLoginMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserLoginService {
    //将mapper层属性注入service层
    @Resource
    private UserLoginMapper userLoginMapper;

    public String login(String username, String password) {
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).select("username", "password");
        UserLoginBean user = userLoginMapper.selectOne(queryWrapper);
        if (user == null) {
            return "User does not exist";
        }
        if (!user.getPassword().equals(password)) {
            return "Password is not matched";
        }
        return "success";
    }
    public String register(UserLoginBean user) {
        // 根据用户名查询用户是否已存在，如果存在则返回相应的提示信息
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).select("username");
        UserLoginBean existingUser = userLoginMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            return "Username already exists";
        }
        String emailValidationResult = emailMatcher(user);
        if (!emailValidationResult.equals("success")) {
            return emailValidationResult;
        }
        // 验证密码和确认密码是否匹配
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Password does not match";
        } else {
            userLoginMapper.insert(user);
            return "success";
        }

    }
    public String emailMatcher(UserLoginBean user) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,}\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());
        if (!emailMatcher.matches()) {
            return "Incorrect email format";
        } else {
            return "success";
        }
    }
}