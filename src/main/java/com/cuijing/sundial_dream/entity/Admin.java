package com.cuijing.sundial_dream.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yikaiye.common.data.entity.SuperEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@TableName("admin")
public class Admin extends SuperEntity {
    /**
     * 主键标识
     */
    @TableField(value = "username")
    @NotEmpty(message="用户名不能为空")
    private String username;
    /*登陆密码*/

    @TableField(value = "password")
    @NotEmpty(message="登陆密码不能为空")
    private String password;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;
}
