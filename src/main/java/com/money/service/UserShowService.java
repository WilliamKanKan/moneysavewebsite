package com.money.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.money.entity.UserLoginBean;
import com.money.mapper.UserLoginMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserShowService {
    //将mapper层属性注入service层
    @Resource
    private UserLoginMapper userLoginMapper;

    public UserLoginBean showUsers(Integer id) {
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id).select("id", "username", "gender", "status", "description");
        return userLoginMapper.selectOne(queryWrapper);
    }

    public List<UserLoginBean> showAllUsers() {
        return userLoginMapper.selectList(null);
    }

    public String deleteUserById(Integer id) {
        int rowsAffected = userLoginMapper.deleteById(id);
        if (rowsAffected > 0) {
            return "success";
        } else {
            return "failure";
        }

    }

    public String updateUserById(Integer id, UserLoginBean user) {
        UserLoginBean findUser = userLoginMapper.selectById(id);
        if (findUser == null) {
            return "failure";
        }
        findUser.setUsername(user.getUsername());
        findUser.setEmail(user.getEmail());
        int rowsAffected = userLoginMapper.updateById(findUser);
        if (rowsAffected > 0) {
            return "success";
        } else {
            return "failure";
        }
    }
}
