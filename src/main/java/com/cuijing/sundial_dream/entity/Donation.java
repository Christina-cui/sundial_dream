package com.cuijing.sundial_dream.entity;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cuijing.sundial_dream.enums.AllEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@TableName("t_donation")
public class Donation extends SuperEntity {
    /*捐款id*/
    @TableId(value = "id" )
    private Long donationId;

    /*捐款人*/
    @TableField(value = "user_id")
    private Long userId;

    /*捐款金额*/
    @NotNull(message="必须输入捐款金额")
    @TableField(value = "donation_money")
    private Float donationMoney;

    /*捐款时间*/
    @TableField(value = "donation_time")
    private Date donationTime;

    /*捐款备注*/
    @TableField(value = "donation_memo")
    private String donationMemo;

    /*审核状态*/
    @NotEmpty(message="审核状态不能为空")
    @TableField("shenHeState")
    private AllEnum.ShenHeEnum shenHeState;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonDonation=new JSONObject(); 
		jsonDonation.put("donationId", this.getDonationId());
		jsonDonation.put("userId", this.getUserId());
		jsonDonation.put("donationMoney", this.getDonationMoney());
		jsonDonation.put("dunationTime", this.getDonationTime());
		jsonDonation.put("dunationMemo", this.getDonationMemo());
		jsonDonation.put("sheHeState", this.getShenHeState());
		return jsonDonation;
    }}