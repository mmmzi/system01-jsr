package com.example.system01.Controller;

import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户数据的相关操作
 */
@RestController
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) {

        //对用户输入的密码进行md5转换
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        //调用service服务查询数据库是否存在当前用户
        User dbUser = userService.getUserByNameAndPassword(user.getUsername(), md5password);
        if (dbUser != null) {
            return "qiutian";
        }
        return "wrong";
    }

    /**
     * 查询所有用户信息
     * @return 查询成功则返回List<User>,否则返回null
     */
    @PostMapping("/getAll")
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        list = userService.getAll();
        if (!list.isEmpty()) {
            list.stream()
                    .forEach(user -> System.out.println(user));
            return list;
        } else {
            return null;
        }
    }

    /**
     * 添加一名用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        if (!userService.CheckUsername(user.getUsername())) {
            userService.addUser(user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            return "true";
        } else {
            return "Wrong";
        }
    }

    /**
     * 删除一名用户
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUserById")
    public String deleteUserById(@RequestBody Integer id) {
        if (userService.searchUserById(id)) {
            userService.deleteUserById(id);
            return "true";
        } else {
            return "wrong";
        }
    }

    /**
     * 更新用户的信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        if (!userService.CheckUsername(user.getUsername())) {
            //对前端传入的密码进行md5转换
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            userService.updateUser(user.getId(), user.getUsername(), password);
            return "true";
        } else {
            return "false";
        }
    }

}

