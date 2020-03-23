package com.cuijing.sundial_dream.condition;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ActivityInfoCondition {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private Integer typeId;
}
