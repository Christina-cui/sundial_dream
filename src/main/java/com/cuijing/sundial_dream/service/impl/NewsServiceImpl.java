package com.cuijing.sundial_dream.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.News;
import com.cuijing.sundial_dream.mapper.ActivityInfoMapper;
import com.cuijing.sundial_dream.mapper.NewsMapper;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.NewsService;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
}
