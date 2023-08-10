package com.example.system01.Controller;

import com.example.system01.Dto.R;
import com.example.system01.Dto.User;
import com.example.system01.Mapper.UserMapper;
import com.example.system01.Service.UserService;
import com.example.system01.utils.JwtUtil;
import com.example.system01.valid.AddGroup;
import com.example.system01.valid.UpdateGroup;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        String Token = null;
        //对用户输入的密码进行md5转换
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        //调用service服务查询数据库是否存在当前用户
        User dbUser = userService.getUserByNameAndPassword(user.getUsername(), md5password);
        if (dbUser != null) {
            //创建Token并返回给前端
            Token = JwtUtil.createToken(user.getId(), user.getUsername());
            System.out.println(Token);
            return Token;
        }
        return "wrong";
    }

    /**
     * 检测请求头里的token
     */
    @GetMapping("check_token")
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }

    /**
     * 查询所有用户信息
     *
     * @return 查询成功则返回List<User>,否则返回null
     */
    @GetMapping("/getAll")
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
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public R addUser(@Validated({AddGroup.class}) @RequestBody User user/*, BindingResult result*/) {
//        if(result.hasErrors()){
//            Map<String,String> map = new HashMap<>();
//            //1.获取校验的错误结果
//            result.getFieldErrors().forEach((item)->{
//               String message = item.getDefaultMessage();
//               String field = item.getField();
//               map.put(field,message);
//            });
//            return R.error(400,"提交的数据不合法").put("data",map);
//        }
        if (!userService.CheckUsername(user.getUsername())) {
            userService.addUser(user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            return R.ok();
        } else {
            return R.error(400, "提交的数据不合法");
        }
    }

    /**
     * 删除一名用户
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUserById")
    public R deleteUserById(@RequestBody Integer id) {
        if (userService.searchUserById(id)) {
            userService.deleteUserById(id);
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 更新用户的信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public R updateUser(@Validated({UpdateGroup.class}) @RequestBody User user) {
        if (!userService.CheckUsername(user.getUsername())) {
            //对前端传入的密码进行md5转换
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            //System.out.println("userid is " + user.getId()+user.getUsername());
            userService.updateUser(user.getId(), user.getUsername(), password);
            return R.ok();
        } else {
            return R.error();
        }
    }

}

