package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.Admin;
import com.cuijing.sundial_dream.entity.Donation;
import com.cuijing.sundial_dream.mapper.AdminMapper;
import com.cuijing.sundial_dream.mapper.DonationMapper;
import com.cuijing.sundial_dream.service.AdminService;
import com.cuijing.sundial_dream.service.DonationService;
import org.springframework.stereotype.Service;


@Service
public class DonationServiceImpl extends ServiceImpl<DonationMapper, Donation> implements DonationService {
}
