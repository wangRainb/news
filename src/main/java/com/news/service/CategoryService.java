package com.news.service;

import com.news.pojo.Category;

import java.util.List;
import java.util.Optional;

/**
 * @author 18786
 */
public interface CategoryService {
    /**
     * 获取栏目列表
     *
     * @return Page<Category>
     */
    List<Category> getCategoryList();

    /**
     * 添加栏目
     *
     * @param category
     */
    void addCategory(Category category);

    /**
     * 通过栏目名检查栏目是否已存在
     *
     * @param name
     * @return
     */
    boolean checkCategoryIsExist(String name);

    /**
     * 修改栏目
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除栏目
     *
     * @param id
     */
    void deleteCategory(Integer id);

    /**
     * 通过id获取栏目
     *
     * @param id
     * @return Optional<Category>
     */
    Optional<Category> getCategoryById(Integer id);
}
