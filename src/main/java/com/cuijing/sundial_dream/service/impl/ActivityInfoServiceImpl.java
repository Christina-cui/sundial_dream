package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.condition.ActivityInfoCondition;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.mapper.ActivityInfoMapper;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.utils.Lambdas;
import com.cuijing.sundial_dream.utils.QueryWrappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ActivityInfoServiceImpl extends BaseServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {


    @Override
    public boolean saveActivityInfo(ActivityInfo activityInfo) {
        return saveOrUpdate(activityInfo);
    }

    @Override
    public IPage<ActivityInfo> findActivityWithType(ActivityInfoCondition condition, Page page) {
        QueryWrapper<ActivityInfo> wrapper = QueryWrappers.wrapper();
        Lambdas.apply(condition.getTitle(), v -> wrapper.like("title",condition.getTitle()));
        Lambdas.apply(condition.getTypeId(),v->wrapper.eq("type_id",condition.getTypeId()));
        IPage<ActivityInfo> pageList = baseMapper.selectPage(page,wrapper);
        return pageList;
    }

    @Override
    public List<ActivityInfo> selectAllActivityInfo() {
        return baseMapper.selectList(QueryWrappers.wrapper());
    }

    @Override
    public Optional<ActivityInfo> findById(Long id) {
        return Optional.ofNullable(baseMapper.selectById(id));
    }

    @Override
    public List<ActivityInfo> findByName(String titleName) {
        QueryWrapper<ActivityInfo> wrapper = new QueryWrapper<>();
        wrapper.like("title",titleName);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public boolean updateActivityInfo(ActivityInfo info) {
        return saveOrUpdate(info);
    }

    @Override
    public boolean deleteActivityInfo(Long i) {
        return deleteById(i);
    }
}
