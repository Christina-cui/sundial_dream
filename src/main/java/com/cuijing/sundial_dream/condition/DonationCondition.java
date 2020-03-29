package com.cuijing.sundial_dream.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class DonationCondition {

    @ApiModelProperty("起始捐款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donationStartTime;

    @ApiModelProperty("结束捐款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donationEndTime;

    @ApiModelProperty("大于捐款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donationGeTime;

    @ApiModelProperty("小于捐款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date donationLeTime;

    @ApiModelProperty("捐款人")
    private Long userId;

    @ApiModelProperty("捐款金额")
    private Float donateMoney;

}
