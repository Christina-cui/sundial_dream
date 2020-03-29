package com.cuijing.sundial_dream.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotEmpty;

@Data
@TableName("t_activity_type")
public class ActivityType extends SuperEntity {
    /*活动类型id*/
    @TableField(value = "id")
    private Long typeId;

    /*活动类型名称*/
    @NotEmpty(message="活动类型名称不能为空")
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonActivityType=new JSONObject(); 
		jsonActivityType.put("typeId", this.getTypeId());
		jsonActivityType.put("typeName", this.getTypeName());
		return jsonActivityType;
    }}