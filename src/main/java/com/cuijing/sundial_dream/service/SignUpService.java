package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.condition.SignUpCondition;
import com.cuijing.sundial_dream.entity.News;
import com.cuijing.sundial_dream.entity.SignUp;
import org.springframework.transaction.annotation.Transactional;

public interface SignUpService extends IService<SignUp> {
    /**
     * 根据条件查找报名人
     */
    IPage<SignUp> findSignUpByPage(SignUpCondition condition, Page page);

    /**
     * 添加一条报名信息
     */
    @Transactional
    boolean saveSignUp(SignUp signUp);

    /***
     * 修改一条报名信息
     */
    @Transactional
    boolean updateSignUp(SignUp signUp);
    /**
     * 删除一条报名信息
     */
    @Transactional
    void deleteSignById(Long id);
    /**
     * 根据条件删除报名信息
     */
    @Transactional
    void deleteSignUps(SignUpCondition condition);
}
