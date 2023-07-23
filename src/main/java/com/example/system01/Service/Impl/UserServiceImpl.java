package com.example.system01.Service.Impl;

import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        User dbUser = userMapper.getUserByNameAndPassword(username, password);
        return dbUser;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public boolean CheckUsername(String username) {
        User tempuser = userMapper.CheckUsername(username);
        System.out.println(tempuser);
        return tempuser != null;
    }

    @Override
    public void addUser(String username, String password) {
        userMapper.addUser(username, password);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUser(Integer id, String username, String password) {
        userMapper.updateUser(id, username, password);
    }

    @Override
    public boolean searchUserById(Integer id) {
        User tempUser = userMapper.searchUserById(id);
        return tempUser != null;
    }
}
