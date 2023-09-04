package com.news.dao;

import com.news.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 18786
 */
@Repository
public interface NoticeDao extends JpaRepository<Notice, Integer>, JpaSpecificationExecutor<Notice> {
}
