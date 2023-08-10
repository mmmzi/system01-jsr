package com.example.system01.Dto;

import com.example.system01.valid.AddGroup;
import com.example.system01.valid.ListValue;
import com.example.system01.valid.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Min(value = 1, groups = {UpdateGroup.class})
    @NotNull(message = "修改用户信息必须指定id", groups = {UpdateGroup.class})
    @Null(message = "新增用户数据不能指定id", groups = {AddGroup.class})
    private int id;

    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class})
    private String username;

    @ListValue(vals = {"123", "456"}, message = "密码必须是指定的字符串", groups = {UpdateGroup.class})
    private String password;

}
