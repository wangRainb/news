package com.news.controller;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.news.pojo.Category;
import com.news.pojo.News;
import com.news.service.CategoryService;
import com.news.service.NewsService;
import com.news.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 18786
 */
@Controller
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private CategoryService categoryService;

    @GetMapping("/admin/news")
    public String newsView() {
        return "admin/news";
    }

    @GetMapping("/admin/news/getNews")
    @ResponseBody
    public JsonResult getNewsList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("categoryId") String categoryId,
                                  @RequestParam("search") String search) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<News> page = newsService.getNewsList(pageNum, pageSize, categoryId, search);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", JSON.toJSONString(page));
        return JsonResult.ok(map);
    }

    @GetMapping("/admin/news/addNews")
    public String addNewsView(Model model) {
        model.addAttribute("categories", categoryService.getCategoryList());
        return "admin/addNews";
    }

    @PostMapping("/admin/news/addNews")
    public String addNews(String title,
                          String content,
                          String cid,
                          String author,
                          @RequestPart(value = "img", required = false) byte[] img) throws Exception {
        if (StringUtils.isNullOrEmpty(title)
                || StringUtils.isNullOrEmpty(content)
                || StringUtils.isNullOrEmpty(cid)
                || StringUtils.isNullOrEmpty(author)) {
            throw new RuntimeException();
        }
        Optional<Category> optionalCategory = categoryService.getCategoryById(Integer.valueOf(cid));
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(optionalCategory.get());
        news.setAuthor(author);
        news.setCreateTime(LocalDateTime.now());
        newsService.addNews(news, img);
        return "redirect:/admin/news";
    }


    @PostMapping("/admin/news/deleteNews/{id}")
    @ResponseBody
    public JsonResult deleteNews(@PathVariable Integer id) {
        if (id == null) {
            return JsonResult.error("id不能为空！");
        } else {
            newsService.deleteNews(id);
            return JsonResult.ok("删除成功！");
        }
    }
}
