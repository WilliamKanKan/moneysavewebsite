package com.money.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginBean implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @TableField(exist = false)
    private String confirmPassword;
    private String email;
    private String description;
    private Integer status;
    private Integer gender;
    private Long createTime;
}

