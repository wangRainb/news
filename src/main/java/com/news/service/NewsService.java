package com.news.service;

import com.news.pojo.News;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * @author 18786
 */
public interface NewsService {
    /**
     * 获取新闻列表
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param search
     * @return Page<News>
     */
    Page<News> getNewsList(Integer pageNum, Integer pageSize, String categoryId, String search);

    /**
     * 添加新闻
     *
     * @param news
     * @param img
     */
    void addNews(News news, byte[] img) throws IOException;

    /**
     * 删除新闻
     *
     * @param id
     */
    void deleteNews(Integer id);

    /**
     * 获取新闻
     *
     * @param id
     * @return News
     */
    News getNews(Integer id);
}
