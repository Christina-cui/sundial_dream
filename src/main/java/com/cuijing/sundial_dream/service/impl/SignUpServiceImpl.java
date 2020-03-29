package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.condition.SignUpCondition;
import com.cuijing.sundial_dream.entity.SignUp;
import com.cuijing.sundial_dream.mapper.SignUpMapper;
import com.cuijing.sundial_dream.service.SignUpService;
import org.springframework.stereotype.Service;


@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements SignUpService {
    @Override
    public IPage<SignUp> findSignUpByPage(SignUpCondition condition, Page page) {
        return baseMapper.selectSignByPage(condition,page);
    }

    @Override
    public boolean saveSignUp(SignUp signUp) {
        return saveOrUpdate(signUp);
    }

    @Override
    public boolean updateSignUp(SignUp signUp) {
        return saveOrUpdate(signUp);
    }

    @Override
    public void deleteSignById(Long id) {
        deleteSignById(id);
    }

    @Override
    public void deleteSignUps(SignUpCondition condition) {
        baseMapper.deleteManySignUps(condition);
    }
}
