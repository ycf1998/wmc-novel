package com.wmc.novel.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @className: ResetPasswordDto
 * @description:
 * @author: money
 * @date: 2021-02-26 20:19
 */
@Getter
@Setter
public class ResetPasswordDto {
	
	@NotNull(message="参数错误")
	private Integer id;
	
	@NotBlank(message="旧密码不能为空")
	private String oldPassword;
	
	@NotBlank(message="新密码不能为空")
	private String newPassword;

}
