package com.example.system01.Mapper;

import com.example.system01.Dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from user where username = #{username} and password = #{password}")
    User getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * from user")
    List<User> getAll();

    @Select("SELECT * from user where username = #{username}")
    User CheckUsername(@Param("username") String username);

    @Insert("INSERT into user(username,password) values(#{username},#{password}) ")
    void addUser(@Param("username") String username, @Param("password") String password);

    @Delete("DELETE from user where id = #{id}")
    void deleteUserById(@Param("id") Integer id);

    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id =#{id}")
    void updateUser(@Param("id") Integer id, @Param("username") String username, @Param("password") String password);

    @Select("SELECT * from user where id = #{id}")
    User searchUserById(@Param("id") Integer id);
}
