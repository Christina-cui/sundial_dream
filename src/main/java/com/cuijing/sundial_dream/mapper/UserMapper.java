package com.cuijing.sundial_dream.mapper;

import com.cuijing.sundial_dream.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user where phone=#{phone}")
    User selectByPhone(String phone);

    @Select("SELECT count(id) FROM user where phone=#{phone}")
    int countByPhone(String phone);

}
