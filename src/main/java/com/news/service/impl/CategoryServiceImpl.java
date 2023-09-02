package com.news.service.impl;

import com.news.dao.CategoryDao;
import com.news.pojo.Category;
import com.news.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author 18786
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public boolean checkCategoryIsExist(String name) {
        return categoryDao.existsCategoryByName(name);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDao.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryDao.findById(id);
    }
}
