package com.news.service.impl;

import com.news.dao.NoticeDao;
import com.news.pojo.Notice;
import com.news.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 18786
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;


    @Override
    public Page<Notice> getNoticeList(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Notice> page = noticeDao.findAll(pageRequest);
        return page;
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDao.save(notice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNotice(Integer id) {
        noticeDao.deleteById(id);
    }
}
