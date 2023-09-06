package com.news.service.impl;

import com.news.dao.CategoryDao;
import com.news.dao.NewsDao;
import com.news.pojo.Category;
import com.news.pojo.News;
import com.news.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 18786
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private NewsDao newsDao;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategory(Integer id) {
        List<News> news = newsDao.getAllByCid(id);
        if (!news.isEmpty()) {
            return false;
        } else {
            categoryDao.deleteById(id);
            return true;
        }
    }

    @Override
    public Map<String, Long> getCategory() {
        List<Category> categoryList = categoryDao.findAll();
        Map<String, Long> map = new HashMap<>(16);
        for (Category category : categoryList) {
            map.put(category.getName(), newsDao.countByCid(category.getId()));
        }
        return map;
    }
}
