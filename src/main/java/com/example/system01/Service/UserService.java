package com.example.system01.Service;

import com.example.system01.Dto.User;

import java.util.List;

public interface UserService {

    User getUserByNameAndPassword(String username, String password);

    List<User> getAll();

    //根据用户名查找数据
    boolean CheckUsername(String username);

    void addUser(String username, String password);

    void deleteUserById(Integer id);

    void updateUser(Integer id, String username, String password);

    boolean searchUserById(Integer id);
}
