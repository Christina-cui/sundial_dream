package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.condition.ActivityInfoCondition;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ActivityInfoService extends IService<ActivityInfo> {


    /*添加公益活动记录*/
    @Transactional
    boolean saveActivityInfo(ActivityInfo activityInfo);

    /**
     * 根据查询条件分页查找
     * @param condition
     * @param page
     * @return
     */
    IPage<ActivityInfo> findActivityWithType(ActivityInfoCondition condition, Page page);

    /**
     * 查询所有公益活动记录
     * @return
     */
    List<ActivityInfo> selectAllActivityInfo();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Optional<ActivityInfo> findById(Long id);

    /**
     * 根据名字查找
     * @param titleName
     * @return
     */
    List<ActivityInfo> findByName(String titleName);

    /**
     * 更新公益活动记录
     * @param info
     * @return
     */
    @Transactional
    boolean updateActivityInfo(ActivityInfo info);

    /**
     * 删除一条公益活动记录
     */
    @Transactional
    boolean deleteActivityInfo(Long i);


}
