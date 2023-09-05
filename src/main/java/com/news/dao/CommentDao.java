package com.news.dao;

import com.news.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 18786
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
    /**
     * 获取新闻评论数
     *
     * @param nid
     * @return int
     */
    int countByNid(Integer nid);
}
