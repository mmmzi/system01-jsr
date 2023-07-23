package com.example.system01.Service;

import com.example.system01.Dto.User;

import java.util.List;

public interface UserService {
    User getUserByNameAndPassword(String username, String password);
    List<User> FindAll();

    boolean CheckUsername(String username);

    void AddOne(String username, String password);

    void DelOne(Integer id);

    void ChangeOne(Integer id, String username, String password);
}
