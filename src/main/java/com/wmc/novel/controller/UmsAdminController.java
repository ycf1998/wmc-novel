package com.wmc.novel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmc.novel.common.CommResp;
import com.wmc.novel.dto.LoginDto;
import com.wmc.novel.dto.ResetPasswordDto;
import com.wmc.novel.dto.UpdateAdminDto;
import com.wmc.novel.mbg.entity.UmsAdmin;
import com.wmc.novel.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @className: UmsAdminController
 * @description: 管理员Controller
 * @author: money
 * @date: 2021-02-24 21:12
 */
@Controller
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @PostMapping("/login")
    public String login(@Validated LoginDto loginDto, Model model, HttpSession session) {
        UmsAdmin admin = umsAdminService.login(loginDto);
        if (admin != null) {
            if (!admin.getStatus()) {
                session.setAttribute("message", "账号已被禁用，请联系管理员");
                return "login";
            }
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("admin", admin);
            return "index";
        } else {
            model.addAttribute("message", "账号或密码错误");
            return "login";
        }
    }

    @PostMapping("/modify")
    @ResponseBody
    public CommResp modify(@RequestBody @Validated UpdateAdminDto updateAdminDto, HttpSession session) {
        if (umsAdminService.modify(updateAdminDto) > 0) {
            session.setAttribute("admin", umsAdminService.getAdminInfoById(updateAdminDto.getId()));
            return CommResp.success();
        }
        return CommResp.fail("修改失败", null);
    }

    @PostMapping("/resetPassword")
    @ResponseBody
    public CommResp resetPwd(@RequestBody @Validated ResetPasswordDto resetPasswordDto) {
        if (umsAdminService.resetPwd(resetPasswordDto)) {
            return CommResp.success();
        }
        return CommResp.fail("旧密码错误", null);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }


    @GetMapping("/list")
    @ResponseBody
    public CommResp getList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "limit", defaultValue = "15") Integer limit) {
        IPage<UmsAdmin> list = umsAdminService.getList(page, limit);
        return CommResp.success(list.getRecords(), list.getTotal(), page, limit);
    }

    @PostMapping("/update/status")
    @ResponseBody
    public CommResp updateStatus(Integer id, boolean status) {
        if (umsAdminService.modifyStatus(id, status) > 0) {
            return CommResp.success();
        } else {
            return CommResp.fail();
        }
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public CommResp resetPwd(Integer id) {
        if (umsAdminService.resetPwd(id) > 0) {
            return CommResp.success();
        } else {
            return CommResp.fail();
        }
    }

    @PostMapping("")
    @ResponseBody
    public CommResp add(@RequestBody UmsAdmin umsAdmin) {
        int count = umsAdminService.add(umsAdmin);
        if ( count > 0) {
            return CommResp.success();
        } else if (count == -2) {
            return CommResp.fail("登录名已存在", null);
        } else {
            return CommResp.fail("新增失败");
        }
    }

    @PutMapping("")
    @ResponseBody
    public CommResp update(@Valid @RequestBody UpdateAdminDto updateAdminDto, HttpSession session) {
        int count = umsAdminService.modify(updateAdminDto);
        if (count > 0) {
            return CommResp.success();
        }else if (count == -2) {
            return CommResp.fail("登录名已存在", null);
        } else {
            return CommResp.fail("修改失败");
        }
    }
}
