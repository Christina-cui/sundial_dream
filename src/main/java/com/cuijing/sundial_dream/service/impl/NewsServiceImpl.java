package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.condition.NewsCondition;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.News;
import com.cuijing.sundial_dream.mapper.ActivityInfoMapper;
import com.cuijing.sundial_dream.mapper.NewsMapper;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.NewsService;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsMapper, News> implements NewsService {
    @Override
    public boolean saveNews(News news) {
        return saveOrUpdate(news);
    }

    @Override
    public IPage<News> findNewsByPage(NewsCondition condition, Page page) {
        return baseMapper.selectNewsByPage(condition,page);
    }

    @Override
    public boolean updateNews(News news) {
        return saveOrUpdate(news);
    }

    @Override
    public boolean deleteNews(Long id) {
        return deleteById(id);
    }

    @Override
    public void deleteManyNews(NewsCondition condition) {
        baseMapper.deleteManyNews(condition);
    }
}
