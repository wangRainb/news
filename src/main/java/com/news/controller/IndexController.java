package com.news.controller;

import com.news.service.CategoryService;
import com.news.service.CommentService;
import com.news.service.NewsService;
import com.news.service.UserService;
import com.news.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 18786
 */
@Controller
public class IndexController {
    @Resource
    private NewsService newsService;
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/index")
    public String adminIndex(Model model) {
        model.addAttribute("newsCount", newsService.getNewsCount());
        model.addAttribute("commentCount", commentService.getCommentCount());
        model.addAttribute("userCount", userService.getUserCount());
        model.addAttribute("viewCount", newsService.getViewCount());
        return "admin/index";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/index/getCategory")
    @ResponseBody
    public JsonResult getCategory() {
        Map<String, Long> map = categoryService.getCategory();
        Map<String, Object> returnMap = new HashMap<>(16);
        returnMap.put("keys", map.keySet());
        returnMap.put("values", map.values());
        return JsonResult.ok(returnMap);
    }
}
