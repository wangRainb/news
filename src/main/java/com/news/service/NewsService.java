package com.news.service;

import com.news.pojo.News;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * @throws IOException
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

    /**
     * 修改新闻
     *
     * @param news
     * @param file
     * @throws IOException
     */
    void updateNews(News news, MultipartFile file) throws IOException;

    /**
     * 获取新闻列表
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return Page<News>
     */
    Page<News> getNewsList(Integer pageNum, Integer pageSize, String categoryId);

    /**
     * 获取相关新闻
     *
     * @param id
     * @param cid
     * @return List<News>
     */
    List<News> getRelatedNews(Integer id, Integer cid);

    /**
     * 更新新闻浏览量
     *
     * @param id
     */
    void updateNewsView(Integer id);

    /**
     * 获取新闻总数
     *
     * @return
     */
    long getNewsCount();

    /**
     * 获取总浏览量
     *
     * @return
     */
    long getViewCount();
}
