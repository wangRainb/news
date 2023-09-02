package com.news.controller;

import cn.hutool.core.date.DateUtil;
import com.news.pojo.LoginModel;
import com.news.pojo.RegisterModel;
import com.news.pojo.User;
import com.news.service.UserService;
import com.news.utils.DigestUtil;
import com.news.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 18786
 */
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody LoginModel model, HttpServletRequest request) {
        if (StringUtils.isEmpty(model.getPassword()) && StringUtils.isEmpty(model.getUsername())) {
            return JsonResult.error("用户名或密码不能为空！");
        }
        String md5Password = DigestUtil.md5Hex(model.getPassword());
        User user = userService.checkUser(model.getUsername(), md5Password);
        if (user == null) {
            return JsonResult.error("用户名或密码错误！");
        } else {
            if (user.isLocked()) {
                LocalDateTime dateTime = user.getLockDate().plusDays(user.getLockDays());
                String formatDateTime = DateUtil.format(dateTime, "yyyy-MM-dd HH:mm:ss");
                return JsonResult.error("您的账号已被封禁，" + formatDateTime + "解封");
            }
            if (!user.isRole() && model.isRole()) {
                return JsonResult.error("您没有权限进行管理员登录！");
            }
            user.setPassword(null);
            request.getSession().setAttribute("user", user);
            return JsonResult.ok("登录成功！");
        }
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public JsonResult register(@RequestBody RegisterModel model) {
        if (StringUtils.isEmpty(model.getPassword()) && StringUtils.isEmpty(model.getUsername())) {
            return JsonResult.error("用户名或密码不能为空！");
        }
        if (userService.checkUserIsExist(model.getUsername())) {
            return JsonResult.error("该用户已存在！");
        } else {
            String md5Password = DigestUtil.md5Hex(model.getPassword());
            User user = new User();
            user.setUsername(model.getUsername());
            user.setPassword(md5Password);
            userService.addUser(user);
            return JsonResult.ok("注册成功！");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/login");
    }
}
