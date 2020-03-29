package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from t_admin where username = #{username}")
    Admin selectByName(@Param("username") String username);

    @Update("update t_admin set password = #{password} where username = #{username}")
    void updatePassword(@Param("password")String password,@Param("username")String username);

    @Select("select count(username) from t_admin where username = #{username}")
    int countByName(String username);
}
