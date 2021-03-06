package com.cuijing.sundial_dream.entity;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cuijing.sundial_dream.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@TableName("t_activity_info")
@Data
public class ActivityInfo extends SuperEntity {
    /*活动id*/
    @TableField(value = "id")
    private Long activityId;

    /*活动类型*/
    @TableField(value = "type_id")
    private Long typeId;

    /*活动主题*/
    @NotEmpty(message="活动主题不能为空")
    @TableField(value = "title")
    private String title;

    /*活动图片*/
    @TableField(value = "activity_photo")
    private String activityPhoto;

    /*活动内容*/
    @NotEmpty(message="活动内容不能为空")
    @TableField(value = "content")
    private String content;

    /*活动时间*/
    @NotEmpty(message="活动时间不能为空")
    @TableField(value = "activity_time")
    private Date activityTime;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    @TableField(value = "check")
    private Integer check;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "people_num")
    private Integer peopleNum;


    @TableField(exist = false)
    private ActivityType typeObj;
    @TableField(exist = false)
    private User user;

    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonActivityInfo=new JSONObject();
        jsonActivityInfo.put("activityId", this.getActivityId());
        jsonActivityInfo.put("typeObj", this.getTypeObj());
        jsonActivityInfo.put("title", this.getTitle());
        jsonActivityInfo.put("activityPhoto", this.getActivityPhoto());
        jsonActivityInfo.put("content", this.getContent());
        jsonActivityInfo.put("activityTime", this.getActivityTime());
        jsonActivityInfo.put("check",this.getCheck());
        jsonActivityInfo.put("peopleNum",this.getPeopleNum());
        jsonActivityInfo.put("user",this.getUser());

        return jsonActivityInfo;
    }
}
