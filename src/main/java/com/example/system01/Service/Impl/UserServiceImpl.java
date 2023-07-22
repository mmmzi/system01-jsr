package com.example.system01.Service.Impl;

import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByNameAndPassword(String username, String password) {

        User dbUser = userMapper.getUserByNameAndPassword(username, password);
        return dbUser;
    }
}
