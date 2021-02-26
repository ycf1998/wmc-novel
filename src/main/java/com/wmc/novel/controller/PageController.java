package com.wmc.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: PageController
 * @description: 页面视图跳转
 * @author: money
 * @date: 2021-02-26 20:41
 */
@Controller
public class PageController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    // ======================== 个人
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/resetPwd")
    public String resetPwd() {
        return "resetPwd";
    }

    // ====================== 人员管理
    @GetMapping("/ums/{viewName}")
    public String ums(@PathVariable String viewName) {
        return "ums/" + viewName;
    }
}
