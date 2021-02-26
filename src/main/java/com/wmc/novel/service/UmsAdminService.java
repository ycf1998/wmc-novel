package com.wmc.novel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wmc.novel.dto.LoginDto;
import com.wmc.novel.dto.ResetPasswordDto;
import com.wmc.novel.dto.UpdateAdminDto;
import com.wmc.novel.mbg.entity.UmsAdmin;

/**
 * @className: UmsAdminService
 * @description:
 * @author: money
 * @date: 2021-02-26 20:19
 */
public interface UmsAdminService {

    /**
     * 获取个人信息
     *
     * @param id
     * @return
     */
    UmsAdmin getAdminInfoById(Integer id);

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    UmsAdmin login(LoginDto loginDto);

    /**
     * 修改密码：
     *
     * @param resetPasswordDto
     * @return
     */
    boolean resetPwd(ResetPasswordDto resetPasswordDto);

    /**
     * 修改信息
     *
     * @param updateAdminDto
     * @return
     */
    int modify(UpdateAdminDto updateAdminDto);

    /**
     * 获取管理员列表
     * @param page
     * @param limit
     * @return
     */
    IPage<UmsAdmin> getList(Integer page, Integer limit);

    /**
     * 修改状态 0：禁用 1：启用
     * @param status
     */
    int modifyStatus(Integer id, boolean status);

    /**
     * 新增管理员
     * @param umsAdmin
     * @return
     */
    int add(UmsAdmin umsAdmin);

    /**
     * 重置密码
     * @param id
     * @return
     */
    int resetPwd(Integer id);

}
