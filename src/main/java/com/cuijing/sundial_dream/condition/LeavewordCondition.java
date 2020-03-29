package com.cuijing.sundial_dream.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class LeavewordCondition {

    @ApiModelProperty("留言id")
    private Long id;

    @ApiModelProperty("留言标题")
    private String title;

    @ApiModelProperty("留言人")
    private Long userId;

    @ApiModelProperty("起始留言时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveStartTime;

    @ApiModelProperty("结束留言时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveEndTime;

    @ApiModelProperty("大于留言时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveGeTime;

    @ApiModelProperty("小于留言时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveLeTime;

}
