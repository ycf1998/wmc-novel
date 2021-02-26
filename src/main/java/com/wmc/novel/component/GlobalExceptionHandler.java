package com.wmc.novel.component;


import com.wmc.novel.common.CommResp;
import com.wmc.novel.common.ErrorCode;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @className: GlobalExceptionHandler
 * @description: 全局异常处理类
 * @author money
 * @date 2021年2月23日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({BindException.class})
    public CommResp MethodArgumentNotValidExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return CommResp.result(ErrorCode.PARAMETER_CHECK_ERROR.getCode(), objectError.getDefaultMessage());
    }
}
