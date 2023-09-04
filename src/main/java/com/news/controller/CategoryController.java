package com.news.controller;

import com.news.pojo.Category;
import com.news.service.CategoryService;
import com.news.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 18786
 */
@Controller
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String categoryView() {
        return "admin/category";
    }

    @GetMapping("/category/getCategories")
    @ResponseBody
    public JsonResult getCategoryList() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("msg", categoryService.getCategoryList());
        return JsonResult.ok(map);
    }

    @PostMapping("/admin/category/addCategory")
    @ResponseBody
    public JsonResult addCategory(@RequestBody Category category) {
        if (categoryService.checkCategoryIsExist(category.getName())) {
            return JsonResult.error("该栏目名已存在！");
        } else {
            categoryService.addCategory(category);
            return JsonResult.ok("添加成功！");
        }
    }

    @PostMapping("/admin/category/updateCategory")
    @ResponseBody
    public JsonResult updateCategory(@RequestBody Category category) {
        if (categoryService.checkCategoryIsExist(category.getName())) {
            return JsonResult.error("该栏目名已存在！");
        } else {
            categoryService.updateCategory(category);
            return JsonResult.ok("修改成功！");
        }
    }

    @PostMapping("/admin/category/deleteCategory/{id}")
    @ResponseBody
    public JsonResult deleteCategory(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id)) {
            return JsonResult.error("id不能为空");
        } else {
            if (categoryService.deleteCategory(id)) {
                return JsonResult.ok("删除成功！");
            } else {
                return JsonResult.error("该栏目下还有新闻无法删除！");
            }
        }
    }
}
