package com.ppjj.cmmall.product.execption;

import com.ppjj.cmmall.common.exception.BizCodeEnum;
import com.ppjj.cmmall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/*
 * @Author PangJie___
 * @Description //TODO 异常处理
 * @Date 10:50 2023/1/13
 * param
 * return
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.ppjj.cmmall.product.controller")
public class CmmallExceptionControllerAdvice {

    /*
     * @Author PangJie___
     * @Description  数据校验异常
     * @Date 11:26 2023/1/13
     * param 
     * return 
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}, 异常类型{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> collect = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return R.error(BizCodeEnum.VALID_EXCEPTION, collect);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION);
    }
}
