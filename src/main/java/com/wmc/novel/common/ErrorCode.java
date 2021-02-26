package com.wmc.novel.common;

/**
 * 
 * @className: ErrorCode
 * @description: 错误状态码
 * @author money
 * @date 2020年11月16日
 */
public enum ErrorCode {

	SYSTEM_ERROR(500, "系统错误"), 
	PARAMETER_CHECK_ERROR(400, "参数校验错误"),
	UNLOGIN_ERROR(401, "用户未登录或登录状态超时失效"),
	AUTH_VALID_ERROR(403, "用户权限不足"),;

	private final Integer value;
	private final String message;

	ErrorCode(int value, String message) {
		this.value = value;
		this.message = message;
	}

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public String getCode() {
		return value.toString();
	}

	public static ErrorCode getByCode(Integer value) {
		for (ErrorCode _enum : values()) {
			if (_enum.getValue() == value) {
				return _enum;
			}
		}
		return null;
	}

}
