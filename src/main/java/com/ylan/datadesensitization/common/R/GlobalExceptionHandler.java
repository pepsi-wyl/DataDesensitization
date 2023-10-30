package com.ylan.datadesensitization.common.R;

import com.ylan.datadesensitization.common.R.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author by pepsi-wyl
 * @date 2023-10-30 10:42
 * @Description: 接口全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 接口自定义返回异常类
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ApiResult bizExceptionError(BizException e) {
        log.error("============接口全局异常处理 => BizException", e);
        return ApiResult.fail(e.getResultCodeEnum(), e.getMessage());
    }

    /**
     * NullPointerException系统异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ApiResult nullPointerExceptionError(NullPointerException e) {
        log.error("============接口全局异常处理 => NullPointerException", e);
        return ApiResult.fail(ResultCodeEnum.NULL_POINTER_ERROR, e.getMessage());
    }

    /**
     * RuntimeException系统异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ApiResult systemExceptionError(RuntimeException e) {
        log.error("============接口全局异常处理 => RuntimeException", e);
        return ApiResult.fail(ResultCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
}