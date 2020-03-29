package com.cuijing.sundial_dream.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuijing.sundial_dream.condition.LeavewordCondition;
import com.cuijing.sundial_dream.condition.NewsCondition;
import com.cuijing.sundial_dream.entity.Leaveword;
import com.cuijing.sundial_dream.entity.News;
import org.springframework.transaction.annotation.Transactional;

public interface NewsService extends IService<News> {
    /**
     * 添加新闻
     */
    @Transactional
    boolean saveNews(News news);
    /**
     * 根据查询条件分页查找
     */
    IPage<News> findNewsByPage(NewsCondition condition, Page page);

    /**
     * 更新留言记录
     */
    @Transactional
    boolean updateNews(News news);

    /**
     * 删除一条留言记录
     */
    @Transactional
    boolean deleteNews(Long id);

    /**
     * 根据条件删除多条记录
     */
    @Transactional
    void deleteManyNews(NewsCondition condition);

}
