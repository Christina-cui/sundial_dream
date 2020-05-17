package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.ActivityInfoCondition;
import com.cuijing.sundial_dream.condition.DonationCondition;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.Donation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    IPage<ActivityInfo> selectAllActivityInfo(Page page, ActivityInfoCondition condition);


}
