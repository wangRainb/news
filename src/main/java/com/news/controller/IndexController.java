package com.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 18786
 */
@Controller
public class IndexController {
    @GetMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }
}
