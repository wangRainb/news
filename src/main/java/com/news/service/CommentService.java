package com.news.service;

import com.news.pojo.Comment;
import org.springframework.data.domain.Page;

/**
 * @author 18786
 */
public interface CommentService {
    /**
     * 根据新闻编号获取评论列表
     *
     * @param pageNum
     * @param pageSize
     * @param nid
     */
    Page<Comment> getCommentList(Integer pageNum, Integer pageSize, Integer nid);

    /**
     * 获取新闻的评论数
     *
     * @param nid
     * @return int
     */
    int getCommentCount(Integer nid);

    /**
     * 添加评论
     *
     * @param comment
     */
    void addComment(Comment comment);
}
