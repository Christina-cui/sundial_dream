package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.ActivityType;
import com.cuijing.sundial_dream.mapper.ActivityInfoMapper;
import com.cuijing.sundial_dream.mapper.ActivityTypeMapper;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.ActivityTypeService;
import com.yikaiye.common.data.service.impl.BaseServiceImpl;
import com.yikaiye.common.utils.QueryWrappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ActivityTypeServiceImpl extends BaseServiceImpl<ActivityTypeMapper, ActivityType> implements ActivityTypeService {
    @Override
    public boolean saveActivityType(ActivityType activityType) {
        return saveOrUpdate(activityType);
    }

    @Override
    public IPage<ActivityType> findActivityType(Page page) {
        IPage<ActivityType> pageList = baseMapper.selectPage(page, QueryWrappers.wrapper());
        return pageList;
    }

    @Override
    public List<ActivityType> findAllType() {
        return baseMapper.selectList(QueryWrappers.wrapper());
    }

    @Override
    public Optional<ActivityType> findActivityType(int typeId) {
        return baseMapper.selectById();
    }

    @Override
    public boolean updateActivityType(ActivityType activityType) {
        return false;
    }

    @Override
    public boolean deleteActivityType(int typeId) {
        return false;
    }
}
