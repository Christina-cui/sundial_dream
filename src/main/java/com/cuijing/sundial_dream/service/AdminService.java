package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.entity.Admin;
import org.springframework.transaction.annotation.Transactional;

public interface AdminService extends IService<Admin> {

    public String getErrMessage();

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
     * @return
     */
    Admin findAdminByName(String name);

    /**
     * 新增管理员
     */
    @Transactional
    Admin saveAdmin(Admin admin);

    Admin findAdminByUserName(String username);
}
