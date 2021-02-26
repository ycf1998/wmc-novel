package com.wmc.novel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @className: UpdateAdminDto
 * @description:
 * @author: money
 * @date: 2021-02-26 20:19
 */
@Getter
@Setter
public class UpdateAdminDto {

    @NotNull(message="参数错误")
    private Integer id;

    /**
     * 昵称
     */
    @NotBlank(message="昵称不能为空")
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;
}
