package com.cuijing.sundial_dream.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.NewsCondition;
import com.cuijing.sundial_dream.entity.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<News> {

    IPage<News> selectNewsByPage(NewsCondition condition, Page page);

    void deleteManyNews(NewsCondition condition);

}
