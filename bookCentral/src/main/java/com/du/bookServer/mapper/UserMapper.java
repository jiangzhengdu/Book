package com.du.bookServer.mapper;

import com.du.bookServer.domain.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User queryById(@Param("id") int id);
    @Select("select * from user where username = #{name}")
    User queryByName(@Param("name") String name);

    @Insert({"insert into user(username, password, balance, role) values(#{username}, #{password}, #{balance}, #{role})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveUser(User user);
}
