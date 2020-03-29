package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.condition.LeavewordCondition;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.Leaveword;
import org.springframework.transaction.annotation.Transactional;

public interface LeavewordService extends IService<Leaveword> {
    /**
     * 添加留言信息
     */
    @Transactional
    boolean saveLeaveWord(Leaveword leaveword);
    /**
     * 分页查找留言记录
     */
    IPage<Leaveword> findLeaveWordsByPage(LeavewordCondition condition, Page page);

    /**
     * 更新留言记录
     */
    @Transactional
    boolean updateLeaveword(Leaveword leaveword);

    /**
     * 删除一条留言记录
     */
    @Transactional
    boolean deleteLeaveword(Long id);

    /**
     * 根据条件删除多条记录
     */
    @Transactional
    void deleteManyLeavewords(LeavewordCondition condition);

}
