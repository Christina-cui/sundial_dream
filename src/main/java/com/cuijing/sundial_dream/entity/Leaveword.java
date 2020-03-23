package com.cuijing.sundial_dream.entity;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@TableName("t_leaveword")
public class Leaveword {
    /*留言id*/
    @TableId(value = "id")
    private Long leaveWordId;

    /*留言标题*/
    @NotEmpty(message="留言标题不能为空")
    @TableField(value = "leave_title")
    private String leaveTitle;

    /*留言内容*/
    @NotEmpty(message="留言内容不能为空")
    @TableField(value = "leave_content")
    private String leaveContent;

    /*留言人*/
    @TableField(value = "user_id")
    private Long userId;

    /*留言时间*/
    @TableField(value = "leave_time")
    private Date leaveTime;

    /*管理回复*/
    @TableField(value = "reply_content")
    private String replyContent;

    /*回复时间*/
    @TableField(value = "reply_time")
    private Date replyTime;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonLeaveword=new JSONObject(); 
		jsonLeaveword.put("leaveWordId", this.getLeaveWordId());
		jsonLeaveword.put("leaveTitle", this.getLeaveTitle());
		jsonLeaveword.put("leaveContent", this.getLeaveContent());
		jsonLeaveword.put("userId", this.getUserId());
		jsonLeaveword.put("leaveTime", this.getLeaveTime());
		jsonLeaveword.put("replyContent", this.getReplyContent());
		jsonLeaveword.put("replyTime", this.getReplyTime());
		return jsonLeaveword;
    }}