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

    /**
     * 判断用户是否在某新闻下评论过
     *
     * @param uid
     * @param nid
     * @return boolean
     */
    boolean existsCommentByUidAndNid(Integer uid, Integer nid);

    /**
     * 根据用户id删除评论
     *
     * @param uid
     */
    void deleteAllByUid(Integer uid);

    /**
     * 根据新闻id删除评论
     *
     * @param nid
     */
    void deleteAllByNid(Integer nid);

}
