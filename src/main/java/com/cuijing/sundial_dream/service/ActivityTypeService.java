package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.entity.ActivityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ActivityTypeService extends IService<ActivityType> {

    /*添加活动类型记录*/
    boolean saveActivityType(ActivityType activityType);

    /*按照查询条件分页查询活动类型记录*/
    IPage<ActivityType> findActivityType(Page page);

    /*按照查询条件查询所有记录*/
    List<ActivityType> findAllType();

    /*根据主键获取活动类型记录*/
    Optional<ActivityType> findActivityType(int typeId);

    /*更新活动类型记录*/
    boolean updateActivityType(ActivityType activityType);

    /*删除一条活动类型记录*/
    boolean deleteActivityType (int typeId);





}
