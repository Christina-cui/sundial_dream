package com.cuijing.sundial_dream.entity;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@TableName("t_sign_up")
public class SignUp {

    /*报名id*/
    @TableId(value = "id")
    private Long signId;

    /*报名的活动*/
    @TableField(value = "activity_id")
    private Long activityId;

    /*报名人*/
    @TableField(value = "user_id")
    private Long userId;

    /*报名宣誓*/
    @NotEmpty(message="报名宣誓不能为空")
    @TableField(value = "sign_up_vow")
    private String signUpVow;

    /*报名时间*/
    @NotEmpty(message="报名时间不能为空")
    @TableField(value = "sign_up_time")
    private Date signUpTime;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonSignUp=new JSONObject(); 
		jsonSignUp.put("signId", this.getSignId());
		jsonSignUp.put("activityId", this.activityId);
		jsonSignUp.put("userId", this.userId);
		jsonSignUp.put("signUpVow", this.getSignUpVow());
		jsonSignUp.put("signUpTime", this.getSignUpTime());
		return jsonSignUp;
    }}