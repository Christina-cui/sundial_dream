package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.NewsCondition;
import com.cuijing.sundial_dream.condition.SignUpCondition;
import com.cuijing.sundial_dream.entity.News;
import com.cuijing.sundial_dream.entity.SignUp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper extends BaseMapper<SignUp> {

    IPage<SignUp> selectSignByPage(SignUpCondition condition, Page page);

    void deleteManySignUps(SignUpCondition condition);
}
