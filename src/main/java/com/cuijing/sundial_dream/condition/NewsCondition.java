package com.cuijing.sundial_dream.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class NewsCondition {

    @ApiModelProperty("新闻id")
    private Long id;

    @ApiModelProperty("新闻标题")
    private String title;

    @ApiModelProperty("新闻类别")
    private String newsType;

    @ApiModelProperty("发布人")
    private Long userId;

    @ApiModelProperty("起始发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsStartTime;

    @ApiModelProperty("结束发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsEndTime;

    @ApiModelProperty("大于发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsGeTime;

    @ApiModelProperty("小于发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newsLeTime;
}
