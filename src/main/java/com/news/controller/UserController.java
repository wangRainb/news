package com.news.controller;

import com.alibaba.fastjson.JSON;
import com.news.pojo.User;
import com.news.service.UserService;
import com.news.utils.JsonResult;
import org.springframework.data.domain.Page;
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
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/admin/user")
    public String userView() {
        return "admin/user";
    }

    @GetMapping("/admin/user/getUsers")
    @ResponseBody
    public JsonResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("username") String username) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> page = userService.getUserList(pageNum, pageSize, username);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", JSON.toJSONString(page));
        return JsonResult.ok(map);
    }

    @PostMapping("/admin/user/deleteUser/{id}")
    @ResponseBody
    public JsonResult deleteUser(@PathVariable Integer id) {
        if (id == null) {
            return JsonResult.error("id不能为空");
        } else {
            userService.deleteUser(id);
            return JsonResult.ok("删除成功");
        }
    }

    @GetMapping("/admin/user/lock/{id}/{lockDays}")
    @ResponseBody
    public JsonResult lock(@PathVariable Integer id,
                           @PathVariable Integer lockDays) {
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(lockDays)) {
            userService.lock(id, lockDays);
            return JsonResult.ok("封号成功");
        } else {
            return JsonResult.error("id和封号天数不能为空");
        }
    }
}
