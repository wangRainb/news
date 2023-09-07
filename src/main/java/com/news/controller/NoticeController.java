package com.news.controller;

import com.mysql.cj.util.StringUtils;
import com.news.pojo.Notice;
import com.news.service.NoticeService;
import com.news.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 18786
 */
@Controller
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @GetMapping("/admin/notice")
    public String noticeView() {
        return "admin/notice";
    }

    @GetMapping("/admin/notice/getNotices")
    @ResponseBody
    public JsonResult getNoticeList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Notice> page = noticeService.getNoticeList(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>(16);
        map.put("msg", page);
        return JsonResult.ok(map);
    }

    @GetMapping("/admin/notice/addNotice")
    public String addNoticeView() {
        return "admin/addNotice";
    }

    @PostMapping("/admin/notice/addNotice")
    public String addNews(String title, String content) {
        if (StringUtils.isNullOrEmpty(title)
                || StringUtils.isNullOrEmpty(content)) {
            throw new RuntimeException();
        }
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateTime(LocalDateTime.now());
        noticeService.addNotice(notice);
        return "redirect:/admin/notice";
    }

    @PostMapping("/admin/news/deleteNotice/{id}")
    @ResponseBody
    public JsonResult deleteNotice(@PathVariable Integer id) {
        if (id == null) {
            return JsonResult.error("id不能为空！");
        } else {
            noticeService.deleteNotice(id);
            return JsonResult.ok("删除成功！");
        }
    }

    @GetMapping("/notice")
    public String noticeView(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             Model model) {
        model.addAttribute("notices", noticeService.getNoticeList(pageNum, pageSize));
        return "notice";
    }
}
