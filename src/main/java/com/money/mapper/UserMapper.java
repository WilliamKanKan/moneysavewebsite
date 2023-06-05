package com.money.mapper;

import com.money.entity.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //查询，可以实现登录功能
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    UserBean getInfo(@Param("username") String username, @Param("password") String password);

    //多个参数要加@Param修饰
    //增加，可以实现注册功能
    @Insert("insert into users(username, password, email) values (#{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveInfo(UserBean user);

}
