package com.example.system01.Controller;

import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    /**
     * 检测用户和密码是否正确
     */
    @PostMapping("/login")
    public User login(@RequestBody User user, HttpServletRequest request) {
        /**
         * 对用户输入的密码进行md5转换
         */
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        /**
         * 调用service服务查询数据库是否存在当前用户
         */
        User dbUser = userService.getUserByNameAndPassword(user.getUsername(), md5password);
        if (dbUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", dbUser); // 存储用户信息
        }
        return dbUser;
    }

}

