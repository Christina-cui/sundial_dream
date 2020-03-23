package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
public interface UserService extends IService<User> {
    User getOne(Long id);

    /** 根据手机号查询详细信息 */
    Optional<User> findByPhone(String phone);


    @Transactional
    int saveUser(User user);

    @Transactional
    int update(User user);

    @Transactional
    int delete(int id);

    /** 重置密码 */
    @Transactional
    void resetPassword(Long id);


    List<User> findAllUserByPage(Page page);




}
