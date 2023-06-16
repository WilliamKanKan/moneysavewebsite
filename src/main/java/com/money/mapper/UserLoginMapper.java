package com.money.mapper;

import com.money.entity.UserLoginBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserLoginMapper {

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    UserLoginBean findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Insert("insert into users (username, password, email) values (#{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewUsers(UserLoginBean user);

}
