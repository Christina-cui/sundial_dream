package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.common.error.CommonErrors;
import com.cuijing.sundial_dream.common.error.Errors;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.mapper.AdminMapper;
import com.cuijing.sundial_dream.service.AdminService;
import com.cuijing.sundial_dream.utils.QueryWrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements AdminService {

    private static final String DEFAULT_PASSWORD = "Aa123456";
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String errMessage;
    @Override
    public String getErrMessage() { return this.errMessage; }

    @Override
    public Admin checkLogin(Admin admin) {
        Admin loginAdmin = findAdminByName(admin.getUsername());
        if (loginAdmin == null){
            this.errMessage="用户不存在";
        }else
        if(!Objects.equals(loginAdmin.getPassword(),admin.getPassword())){
            this.errMessage="密码错误";
            return null;
        }
        return loginAdmin;
    }

    @Override
    public void changePassword(Admin admin,Admin old) {
        Errors.notFound().check(!Objects.isNull(admin),"用户不存在");
        if(admin== null){
            this.errMessage="用户不存在";
        }
        else if( findAdminByName(admin.getUsername())==null){
            this.errMessage="用户不存在";
        }
        else if(!Objects.equals(admin.getPassword(),old.getPassword())){
            this.errMessage="密码错误";
        }else{
            baseMapper.updatePassword(admin.getPassword(),admin.getUsername());
        }
    }

//    @Override
//    public void forgetPassword(Admin admin) {
//
//    }

    @Override
    public void resetPassword(String username) {
        baseMapper.updatePassword(username,passwordEncoder.encode(DEFAULT_PASSWORD));
    }

    @Override
    public IPage<Admin> findAllAdminByPage(Page page) {
        return baseMapper.selectPage(page, QueryWrappers.wrapper());
    }

    @Override
    public Admin findAdminByName(String name) {
        return baseMapper.selectByName(name);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        CommonErrors.CONFLICT.check(baseMapper.countByName(admin.getUsername())>=0,"用户名已存在");
        this.errMessage="用户名已存在";
        admin.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        save(admin);
        return admin;
    }

    @Override
    public Admin findAdminByUserName(String username) {
        return baseMapper.selectByName(username);
    }
}
