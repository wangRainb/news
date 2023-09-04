package com.news.controller;

import com.news.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author 18786
 */
@Controller
public class IndexController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getCategoryList());
        return "index";
    }
}
