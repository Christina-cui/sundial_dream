package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.soap.addressing.server.AbstractActionMethodEndpointMapping;

import java.util.List;
import java.util.Optional;

public interface AdminService extends IService<Admin> {

    /*验证用户登录*/
    Admin checkLogin(Admin admin);

    /**
     * 修改密码
     */
    @Transactional
    void changePassword(Admin admin,Admin old);

//    /**
//     * 忘记密码
//     */
//    @Transactional
//    void forgetPassword(Admin admin);

    /**
     * 重置密码
     */
    @Transactional
    void resetPassword(String username);

    /**
     * 分页查询所有管理员
     */
    IPage<Admin> findAllAdminByPage(Page page);

    /**
     * 根据用户名查找管理员对象
     */
    Optional<Admin> findAdminByName(String name);

    /**
     * 新增管理员
     */
    @Transactional
    Admin saveAdmin(Admin admin);

}
