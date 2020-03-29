package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.condition.LeavewordCondition;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.Leaveword;
import com.cuijing.sundial_dream.mapper.AdminMapper;
import com.cuijing.sundial_dream.mapper.LeavewordMapper;
import com.cuijing.sundial_dream.service.AdminService;
import com.cuijing.sundial_dream.service.LeavewordService;
import org.springframework.stereotype.Service;


@Service
public class LeavewordServiceImpl extends BaseServiceImpl<LeavewordMapper, Leaveword> implements LeavewordService {
    @Override
    public boolean saveLeaveWord(Leaveword leaveword) {
        return saveOrUpdate(leaveword);
    }

    @Override
    public IPage<Leaveword> findLeaveWordsByPage(LeavewordCondition condition, Page page) {
        return baseMapper.selectLeaveWordsByPage(condition,page);
    }

    @Override
    public boolean updateLeaveword(Leaveword leaveword) {
        return saveOrUpdate(leaveword);
    }

    @Override
    public boolean deleteLeaveword(Long id) {
        return deleteById(id);

    }

    @Override
    public void deleteManyLeavewords(LeavewordCondition condition) {
        baseMapper.deleteManyLeavewords(condition);
    }
}
