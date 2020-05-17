package com.cuijing.sundial_dream.condition;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import java.util.Date;

@Data
public class ActivityInfoCondition {

    @ApiModelProperty(value = "发布人")
    private Long userId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private Integer typeId;

    @ApiModelProperty("起始活动发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束活动发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty("大于活动发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date geTime;

    @ApiModelProperty("小于活动发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leTime;

    @ApiModelProperty("审核状态")
    private Integer check;


}
