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
        queryWrapper.eq("username", user.getUsername()) .or().eq("email", user.getEmail()).select("email").select("username","email");
        UserLoginBean existingUser = userLoginMapper.selectOne(queryWrapper);
        String emailValidationResult = emailMatcher(user);
        String passwordValidationResult = passwordMatcher(user);
        if (existingUser != null) {
            if(existingUser.getUsername().equals(user.getUsername())){
                return "Username already exists";
            }else {
                return "Email already exists";
            }

        }
        // 判断邮箱地址是否合法，[A-Za-z0-9+_.-] 可以是大小写字母或者数字或者特殊字符+_.- @ 邮箱地址标准配
        //  @后面也必须要跟至少两个数字或者字母，大小写不限，最后再解一个. 和至少两个字母，因为这是通常的域名格式

        else if (!emailValidationResult.equals("success")) {
            return emailValidationResult;
        }
        // 验证密码是否符合要求 必须包含字母大小写、数字和特殊符号【@#$%^&+=】)
        else if (!passwordValidationResult.equals("success")) {
            return passwordValidationResult;
        }
        // 验证密码和确认密码是否匹配
        else if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Password does not match";
        } else {
            userLoginMapper.insert(user);
            return "success";
        }

    }
    // 验证邮箱格式方法
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
    // 验证密码格式方法
    public String passwordMatcher(UserLoginBean user) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());
        if (!passwordMatcher.matches()) {
            return "Incorrect password format";
        } else {
            return "success";
        }
    }
}