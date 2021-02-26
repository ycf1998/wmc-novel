package com.wmc.novel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmc.novel.dto.LoginDto;
import com.wmc.novel.dto.ResetPasswordDto;
import com.wmc.novel.dto.UpdateAdminDto;
import com.wmc.novel.mbg.entity.UmsAdmin;
import com.wmc.novel.mbg.mapper.UmsAdminMapper;
import com.wmc.novel.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @className: UmsAdminServiceImpl
 * @description:
 * @author: money
 * @date: 2021-02-26 20:19
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    private UmsAdmin getUmsAdminByUserName(String username) {
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return umsAdminMapper.selectOne(wrapper);
    }

    @Override
    public UmsAdmin getAdminInfoById(Integer id) {
        UmsAdmin umsAdmin = umsAdminMapper.selectById(id);
        umsAdmin.setPassword(null);
        return umsAdmin;
    }

    @Override
    public UmsAdmin login(LoginDto loginDto) {
        UmsAdmin umsAdmin = getUmsAdminByUserName(loginDto.getUsername());
        if (umsAdmin != null && umsAdmin.getPassword().equals(loginDto.getPassword())) {
//            umsAdmin.setInitLogin(false);
            umsAdmin.setLastTime(LocalDateTime.now());
            umsAdminMapper.updateById(umsAdmin);
            umsAdmin.setPassword(null);
            return umsAdmin;
        }
        return null;
    }

    @Override
    public boolean resetPwd(ResetPasswordDto resetPasswordDto) {
        UpdateWrapper<UmsAdmin> wrapper = new UpdateWrapper<>();
        wrapper.set("password", resetPasswordDto.getNewPassword()).eq("id", resetPasswordDto.getId()).eq("password",
                resetPasswordDto.getOldPassword());
        return umsAdminMapper.update(new UmsAdmin(), wrapper) > 0;
    }

    @Override
    public int modify(UpdateAdminDto updateAdminDto) {
        if (updateAdminDto.getId() == null) {
            return -1;
        }
        UmsAdmin umsAdminByUserName = getAdminInfoById(updateAdminDto.getId());
        if (umsAdminByUserName != null && !umsAdminByUserName.getId().equals(updateAdminDto.getId())) {
            return -2;
        }
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(updateAdminDto, umsAdmin);
        umsAdmin.setUpdateTime(LocalDateTime.now());
        return umsAdminMapper.updateById(umsAdmin);
    }

    @Override
    public IPage<UmsAdmin> getList(Integer page, Integer limit) {
        IPage<UmsAdmin> pageInfo = new Page<>(page, limit);
        return umsAdminMapper.selectPage(pageInfo, null);
    }

    @Override
    public int modifyStatus(Integer id, boolean status) {
        UpdateWrapper<UmsAdmin> wrapper = new UpdateWrapper<>();
        return umsAdminMapper.update(null, wrapper.set("status", status).eq("id", id));
    }

    @Override
    public int add(UmsAdmin umsAdmin) {
        UmsAdmin umsAdminByUserName = getUmsAdminByUserName(umsAdmin.getUsername());
        if (umsAdminByUserName != null) {
            return -2;
        }
        umsAdmin.setCreateTime(LocalDateTime.now());
        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public int resetPwd(Integer id) {
        return umsAdminMapper.update(null, new UpdateWrapper<UmsAdmin>().eq("id", id).set("password", "1"));
    }

}
