package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.Leaveword;
import com.cuijing.sundial_dream.mapper.AdminMapper;
import com.cuijing.sundial_dream.mapper.LeavewordMapper;
import com.cuijing.sundial_dream.service.AdminService;
import com.cuijing.sundial_dream.service.LeavewordService;
import org.springframework.stereotype.Service;


@Service
public class LeavewordServiceImpl extends ServiceImpl<LeavewordMapper, Leaveword> implements LeavewordService {
}
