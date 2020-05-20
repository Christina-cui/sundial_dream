package com.cuijing.sundial_dream.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.cuijing.sundial_dream.bean.dto.UserDto;
import com.cuijing.sundial_dream.common.Result;
import com.cuijing.sundial_dream.common.ResultGenerator;
import com.cuijing.sundial_dream.converter.UserConverter;
import com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody UserDto userDto) {
        String msg = "";
        //根据名字
        User user = userService.findByName(userDto.getUserName());
        if (ObjectUtils.isNull(user)) {
            user = userService.findByMail(userDto.getUserName());
            if (ObjectUtils.isNull(user)) {
                user = userService.findByPhone(userDto.getUserName());
            }
        }
        if (ObjectUtils.isNotNull(user)) {
            if (Objects.equals(user.getPassword(), userDto.getPassword())) {
                Map data = new HashMap();
                data.put("currentUser", userConverter.make(user));
                return ResultGenerator.genSuccessResult(data);
            } else {
                return ResultGenerator.genFailResult("密码错误");
            }
        }
        return ResultGenerator.genFailResult("该用户不存在");
//        HttpSession session = null;
//        if(Objects.equals("success",msg)){
//            Map<String,String> map = new HashMap<>();
//            session.setAttribute("user",user);
//        }
//        //将要被返回到客户端的对象
//        JSONObject json=new JSONObject();
//        json.put("success", success);
//        json.put("msg", msg);
//        return json;
    }

    @RequestMapping("/register")
    public String register() {
        return "user/register";

    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable("id") long id) {
        return userService.getOne(id);
    }

    @RequestMapping("/test")
    public String test() {
        return "index.html";
    }

}

