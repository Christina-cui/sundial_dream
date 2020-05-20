package com.cuijing.sundial_dream.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author cuijing
 * @since 2020-03-21
 */
@TableName("t_user")
@Data
public class User extends SuperEntity {

    @TableId(value = "id",type = IdType.AUTO)
    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    @TableField(value = "phone")
    private String phone;

    /**
     * 姓名
     */
    @TableField(value = "real_name")
    @NotEmpty(message = "姓名不能为空")
    private String realName;

    /**
     * 密码
     */
    @TableField(value = "password")
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 用户头像
     */
    @TableField(value = "user_photo")
    private String userPhoto;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 生日
     */
    @TableField(value = "birth_date")
    private Date birthDate;

    /**
     * 状态，0：禁用，1：可用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;


    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;



}
