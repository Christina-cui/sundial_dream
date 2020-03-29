package com.cuijing.sundial_dream.entity;


import javax.validation.constraints.NotEmpty;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cuijing.sundial_dream.enums.AllEnum;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_news")
public class News {
    /*新闻id*/
    @TableId(value = "id")
    private Long newsId;

    /**
     * 新闻发布人
     */
    @NotEmpty(message="新闻发布人不能为空")
    @TableField(value = "user_id")
    private Long userId;

    /*新闻标题*/
    @NotEmpty(message="新闻标题不能为空")
    @TableField(value = "title")
    private String title;

    /*新闻分类*/
    @NotEmpty(message="新闻分类不能为空")
    @TableField(value = "news_type")
    private AllEnum.NewsTypeEnum newsType;

    /*新闻内容*/
    @NotEmpty(message="新闻内容不能为空")
    @TableField(value = "content")
    private String content;

    /*发布时间*/
    @NotEmpty(message="发布时间不能为空")
    @TableField(value = "publish_time")
    private Date publishDate;

    /**
     * 是否删除，1：删除；0：正常
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonNews=new JSONObject(); 
		jsonNews.put("newsId", this.getNewsId());
		jsonNews.put("title", this.getTitle());
		jsonNews.put("newType", this.getNewsType());
		jsonNews.put("content", this.getContent());
		jsonNews.put("publishDate", this.getPublishDate());
		return jsonNews;
    }}