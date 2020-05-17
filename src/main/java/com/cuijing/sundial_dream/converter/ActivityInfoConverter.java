package com.cuijing.sundial_dream.converter;


import com.cuijing.sundial_dream.config.CommonConverterConfig;
import com.cuijing.sundial_dream.dto.ActivityInfoVo;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import org.mapstruct.Mapper;

@Mapper(config = CommonConverterConfig.class)
public interface ActivityInfoConverter {
    ActivityInfoVo detail(ActivityInfo activityInfo);
}
