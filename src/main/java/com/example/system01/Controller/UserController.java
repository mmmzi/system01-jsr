package com.example.system01.Controller;

import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import com.example.system01.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(tags = "用户的相关操作")
@RestController
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    /**
     * 检测用户和密码是否正确
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        /**
         * 对用户输入的密码进行md5转换
         */
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        String qiutian = null;
        /**
         * 调用service服务查询数据库是否存在当前用户
         */
        User dbUser = userService.getUserByNameAndPassword(user.getUsername(), md5password);
        if (dbUser != null) {
//            qiutian = UUID.randomUUID().toString();
            int temp = user.getId();
            String temp2 = String.valueOf(temp);
            qiutian = JwtUtils.CreateToken(temp2, user.getUsername());
            System.out.println(qiutian);
            response.addHeader("token", qiutian);
            Cookie cookie = new Cookie("token", qiutian);
            cookie.setPath("/");
            response.addCookie(cookie);
            return qiutian;
        }
        return qiutian;
    }

    @ApiOperation("查询所有用户信息")
    @PostMapping("/findall")
    public List<User> FindAll() {
        List<User> list = new ArrayList<>();
        list = userService.FindAll();
        if (!list.isEmpty()) {
            list.stream()
                    .forEach(user -> System.out.println(user));
            return list;
        } else {
            return null;
        }
    }

    @ApiOperation("添加一名用户")
    @PostMapping("/addone")
    public String AddOne(@RequestBody User user) {
        if (!userService.CheckUsername(user.getUsername())) {
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            String username = user.getUsername();
            userService.AddOne(username, password);
            return "true";
        } else {
            return "Wrong";
        }
    }

    @ApiOperation("删除一名用户")
    @PostMapping("/delone")
    public String DelOne(@RequestBody User user) {
        if (userService.CheckUsername(user.getUsername())) {
            Integer id = user.getId();
            userService.DelOne(id);
            return "true";
        } else {
            return "wrong";
        }
    }

    @ApiOperation("更改一名用户信息")
    @PostMapping("/changeone")
    public String ChangeOne(@RequestBody User user) {
        if (!userService.CheckUsername(user.getUsername())) {
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            userService.ChangeOne(user.getId(), user.getUsername(), password);
            return "true";
        } else {
            return "false";
        }
    }

}

