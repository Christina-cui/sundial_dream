package com.cuijing.sundial_dream.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SignUpCondition {
    @ApiModelProperty("报名信息id")
    private Long id;

    @ApiModelProperty("活动id")
    private Long activityId;

    @ApiModelProperty("报名人")
    private Long userId;

    @ApiModelProperty("大于报名时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signUpGeTime;

    @ApiModelProperty("小于报名时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signUpLeTime;


}
