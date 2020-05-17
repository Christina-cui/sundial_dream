package com.cuijing.sundial_dream.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.cuijing.sundial_dream.entity.ActivityType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Data
public class ActivityInfoVo {
    private Long activityId;

    private ActivityType type;

    /*活动主题*/
    private String title;

    /*活动图片*/
    private String activityPhoto;

    /*活动内容*/
    private String content;

    /*活动时间*/
    private Date activityTime;

    private Integer check;
}
