package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.ActivityType;
import com.cuijing.sundial_dream.entity.SignUp;
import com.cuijing.sundial_dream.mapper.ActivityTypeMapper;
import com.cuijing.sundial_dream.mapper.SignUpMapper;
import com.cuijing.sundial_dream.service.ActivityTypeService;
import com.cuijing.sundial_dream.service.SignUpService;
import org.springframework.stereotype.Service;


@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements SignUpService {
}
