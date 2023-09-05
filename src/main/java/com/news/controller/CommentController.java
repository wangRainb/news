package com.news.controller;

import com.mysql.cj.util.StringUtils;
import com.news.pojo.Comment;
import com.news.service.CommentService;
import com.news.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 18786
 */
@Controller
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("/comment/getComments")
    @ResponseBody
    public JsonResult getCommentList(@RequestParam("nid") Integer nid,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize) {
        if (nid == null) {
            return JsonResult.error("nid不能为空");
        } else {
            if (pageNum == null) {
                pageNum = 0;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            Page<Comment> page = commentService.getCommentList(pageNum, pageSize, Integer.valueOf(nid));
            Map<String, Object> map = new HashMap<>(16);
            map.put("msg", page);
            return JsonResult.ok(map);
        }
    }

    @PostMapping("/comment/addComment")
    @ResponseBody
    public JsonResult addComment(@RequestBody Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        commentService.addComment(comment);
        return JsonResult.ok("评论成功！");
    }

    @GetMapping("/comment/getCommentCount/{nid}")
    @ResponseBody
    public JsonResult getCommentCount(@PathVariable String nid) {
        if (StringUtils.isNullOrEmpty(nid)) {
            return JsonResult.error("nid不能为空");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("msg", commentService.getCommentCount(Integer.valueOf(nid)));
        return JsonResult.ok(map);
    }
}
