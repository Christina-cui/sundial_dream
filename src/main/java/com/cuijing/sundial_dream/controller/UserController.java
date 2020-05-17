package com.cuijing.sundial_dream.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.cuijing.sundial_dream.common.CurrentUser;
import com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String checked = request.getParameter("checked");
        String msg = "";
        //根据名字
        msg = userService.checkLogin(userName,password);
        if(msg.equals("success")){
            session.setAttribute(CurrentUser);
            success = true;
            session.setAttribute("user_name",userName);
        }
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //将要被返回到客户端的对象
        JSONObject json=new JSONObject();
        json.put("success", success);
        json.put("msg", msg);
        out.println(json.toString());
        out.flush();
        out.close();

    }

    @RequestMapping("/register")
    public String register(){
        return "user/register";

    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable("id") long id){
        return userService.getOne(id);
    }

    @RequestMapping("/test")
    public String test(){
        return "index.html";
    }

}

