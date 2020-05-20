package com.cuijing.sundial_dream.converter;

import com.cuijing.sundial_dream.bean.vo.UserVo;
import  com.cuijing.sundial_dream.entity.User;
import com.cuijing.sundial_dream.bean.dto.UserDto;
import com.cuijing.sundial_dream.config.CommonConverterConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonConverterConfig.class)
public interface UserConverter {
    UserDto create(User user);

    @Mapping(target = "name",source = "realName")
    UserVo make(User user);
}
