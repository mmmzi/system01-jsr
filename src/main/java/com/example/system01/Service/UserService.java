package com.example.system01.Service;

import com.example.system01.Dto.User;

public interface UserService {
    User getUserByNameAndPassword(String username, String password);
}
