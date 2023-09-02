package com.news.dao;

import com.news.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 18786
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    /**
     * 通过栏目名检查栏目是否已存在
     *
     * @param name
     * @return
     */
    boolean existsCategoryByName(String name);
}
