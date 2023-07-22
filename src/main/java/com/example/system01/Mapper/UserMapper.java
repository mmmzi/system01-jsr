package com.example.system01.Mapper;

import com.example.system01.Dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过Sql语句查询数据库是否存在用户
     */
    @Select("SELECT * from user where username = #{username} and password = #{password}")
    User getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
