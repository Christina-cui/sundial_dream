package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.LeavewordCondition;
import com.cuijing.sundial_dream.entity.Leaveword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LeavewordMapper extends BaseMapper<Leaveword> {


    IPage<Leaveword> selectLeaveWordsByPage(LeavewordCondition condition, Page page);

    void deleteManyLeavewords(LeavewordCondition condition);


}
