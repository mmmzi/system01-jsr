package com.example.system01.Mapper;

import com.example.system01.Dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 通过Sql语句查询数据库是否存在用户
     */
    @Select("SELECT * from user where username = #{username} and password = #{password}")
    User getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * from user")
    List<User> FindAll();

    @Select("SELECT * from user where username = #{username}")
    User CheckUsername(@Param("username") String username);

    @Insert("INSERT into user(username,password) values(#{username},#{password}) ")
    void AddOne(@Param("username") String username, @Param("password") String password);

    @Delete("DELETE from user where id = #{id}")
    void DelOne(@Param("id") Integer id);

    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id =#{id}")
    void ChangeOne(@Param("id")Integer id, @Param("username") String username, @Param("password") String password);
}
