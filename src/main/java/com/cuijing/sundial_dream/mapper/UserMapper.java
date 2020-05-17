package com.cuijing.sundial_dream.mapper;

import com.cuijing.sundial_dream.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;

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

    @Select("SELECT * FROM t_user where phone=#{phone}")
    User selectByPhone(String phone);

    @Select("SELECT count(id) FROM t_user where phone=#{phone}")
    int countByPhone(String phone);

    @Select("SELECT * FROM t_user where email = #{mail} ")
    User selectByMail(@Param("mail")String mail);

    @Update("UPDATE TABLE t_user set password = #{password} where id = #{id}")
    void updatePwdById(@Param("password")String password,@Param("id")Long id);

}
