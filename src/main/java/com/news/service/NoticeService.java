package com.news.service;

import com.news.pojo.Notice;
import org.springframework.data.domain.Page;

/**
 * @author 18786
 */
public interface NoticeService {
    /**
     * 获取公告列表
     *
     * @param pageNum
     * @param pageSize
     * @return Page<Notice>
     */
    Page<Notice> getNoticeList(Integer pageNum, Integer pageSize);

    /**
     * 添加公告
     *
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 删除公告
     *
     * @param id
     */
    void deleteNotice(Integer id);
}
