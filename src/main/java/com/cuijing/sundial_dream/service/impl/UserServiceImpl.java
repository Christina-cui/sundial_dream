package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.common.error.CommonErrors;
import com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.mapper.UserMapper;
import com.cuijing.sundial_dream.service.UserService;
import com.cuijing.sundial_dream.utils.QueryWrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private static final String DEFAULT_PASSWORD = "Aa123456";
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getOne(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return Optional.ofNullable(baseMapper.selectByPhone(phone));
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return Optional.ofNullable(baseMapper.selectByMail(mail));
    }

    @Override
    public int saveUser(User user) {
        CommonErrors.CONFLICT.check(baseMapper.countByPhone(user.getPhone()) <= 0, "手机号码已存在");
        CommonErrors.CONFLICT.check(baseMapper.selectByMail(user.getMail())!=null,"邮箱已被注册");
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        return baseMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return baseMapper.updateById(user);
    }

    @Override
    public int delete(int id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public void resetPassword(Long id) {
        String password = passwordEncoder.encode(DEFAULT_PASSWORD);
        baseMapper.updatePwdById(password,id);
    }

    @Override
    public IPage<User> findAllUserByPage(Page page) {
        return baseMapper.selectPage(page, QueryWrappers.wrapper());
    }
}
