package com.news.controller;

import com.mysql.cj.util.StringUtils;
import com.news.pojo.User;
import com.news.service.UserService;
import com.news.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public JsonResult getUserList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam("username") String username) {
        Page<User> page = userService.getUserList(pageNum, pageSize, username);
        Map<String, Object> map = new HashMap<>(16);
        map.put("msg", page);
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
        if (id != null && lockDays != null) {
            userService.lock(id, lockDays);
            return JsonResult.ok("封号成功");
        } else {
            return JsonResult.error("id和封号天数不能为空");
        }
    }

    @GetMapping("/profile/{id}")
    public String profileView(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new RuntimeException("id不能为空！");
        } else {
            model.addAttribute("profile", userService.getUser(id));
            return "profile";
        }
    }

    @GetMapping("/updateProfile/{id}")
    public String updateProfileView(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new RuntimeException("id不能为空！");
        } else {
            model.addAttribute("profile", userService.getUser(id));
            return "updateProfile";
        }
    }

    @PostMapping("/updateProfile")
    public String updateProfile(String id,
                                String username,
                                String phone,
                                String email,
                                @RequestPart(value = "img", required = false) MultipartFile file,
                                HttpServletRequest request) throws Exception {
        if (StringUtils.isNullOrEmpty(id)
                || StringUtils.isNullOrEmpty(username)
                || StringUtils.isNullOrEmpty(phone)
                || StringUtils.isNullOrEmpty(email)) {
            throw new RuntimeException();
        } else {
            User user = new User();
            user.setId(Integer.valueOf(id));
            user.setUsername(username);
            user.setPhone(phone);
            user.setEmail(email);
            user = userService.updateUser(user, file);
            request.getSession().setAttribute("user", user);
            return "redirect:/profile/" + user.getId();
        }
    }

    @GetMapping("/updatePassword/{id}")
    public String updatePassword(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new RuntimeException("id不能为空！");
        } else {
            model.addAttribute("profile", userService.getUser(id));
            return "updatePassword";
        }
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public JsonResult updatePassword(String id, String password) {
        if (StringUtils.isNullOrEmpty(id)) {
            return JsonResult.error("id不能为空！");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return JsonResult.error("密码不能为空！");
        } else {
            userService.updatePasswordById(Integer.valueOf(id), password);
            return JsonResult.ok("修改密码成功！");
        }
    }
}
