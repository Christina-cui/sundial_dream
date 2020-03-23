package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.mapper.UserMapper;
import com.cuijing.sundial_dream.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yikaiye.common.error.CommonErrors;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
    public int saveUser(User user) {
        CommonErrors.CONFLICT.check(baseMapper.countByPhone(user.getPhone()) <= 0, "手机号码已存在");
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

    }

    @Override
    public List<User> findAllUserByPage(Page page) {
        return null;
    }
}
