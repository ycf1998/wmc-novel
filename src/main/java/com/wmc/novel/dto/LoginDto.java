package com.wmc.novel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @className: LoginDto
 * @description:
 * @author: money
 * @date: 2021-02-26 20:26
 */
@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "账户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
