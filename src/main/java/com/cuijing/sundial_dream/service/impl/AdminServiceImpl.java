package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.mapper.ActivityInfoMapper;
import com.cuijing.sundial_dream.mapper.AdminMapper;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.AdminService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
