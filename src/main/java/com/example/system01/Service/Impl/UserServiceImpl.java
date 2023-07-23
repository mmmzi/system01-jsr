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
    public List<User> FindAll() {
        return userMapper.FindAll();
    }

    @Override
    public boolean CheckUsername(String username) {
        User tempuser = userMapper.CheckUsername(username);
        System.out.println(tempuser);
        if(tempuser!=null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void AddOne(String username,String password) {
        userMapper.AddOne(username,password);
    }

    @Override
    public void DelOne(Integer id) {
        userMapper.DelOne(id);
    }

    @Override
    public void ChangeOne(Integer id, String username, String password) {
        userMapper.ChangeOne(id,username,password);
    }
}
