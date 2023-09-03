package com.news.dao;

import com.news.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 18786
 */
@Repository
public interface NewsDao extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {
    /**
     * 根据栏目获取新闻
     *
     * @param cid
     * @return List<News>
     */
    List<News> getAllByCid(Integer cid);
}
