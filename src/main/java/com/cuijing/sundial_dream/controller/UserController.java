package com.cuijing.sundial_dream.controller;


import com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable("id") long id){
        return userService.getOne(id);
    }

    @RequestMapping("/test")
    public String test(){
        return "html";
    }

}

