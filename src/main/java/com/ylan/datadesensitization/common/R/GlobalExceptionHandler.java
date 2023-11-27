package com.ylan.datadesensitization.common.R;

import com.ylan.datadesensitization.common.R.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2023-10-30 10:42
 * @Description: 接口全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===========================================>业务异常========================================
    /**
     * 接口自定义返回异常类
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ApiResult bizExceptionError(BizException e) {
        log.error("============接口全局异常处理 => BizException", e);
        return ApiResult.fail(e.getResultCodeEnum(), e.getMessage());
    }

    // ===========================================>参数异常========================================
    /**
     * 字段参数校验失败异常类
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResult> methodArgumentNotValidExceptionError(MethodArgumentNotValidException e) {
        log.error("============字段参数校验失败 => MethodArgumentNotValidException", e);
        // ======>封装异常信息开始<=======
        LinkedHashMap<Object, Object> errors = new LinkedHashMap<>();
        e.getFieldErrors().forEach((fieldError)->
            errors.put(fieldError.getField(),fieldError.getDefaultMessage())
        );
        // ======>封装异常信息结束<=======
        return new ResponseEntity<>(ApiResult.fail(ResultCodeEnum.PARAM_ERROR, errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ===========================================>404异常========================================
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResult> noHandlerFoundExceptionError(NoHandlerFoundException e) {
        log.error("============字段参数校验失败 => NoHandlerFoundException", e);
        return new ResponseEntity<>(ApiResult.fail(ResultCodeEnum.SUBRESOURCE_ERROR, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // ===========================================>系统异常========================================
    /**
     * NullPointerException系统异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseEntity<ApiResult> nullPointerExceptionError(NullPointerException e) {
        log.error("============接口全局异常处理 => NullPointerException", e);
        return new ResponseEntity<>(ApiResult.fail(ResultCodeEnum.NULL_POINTER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * RuntimeException系统异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ApiResult> systemExceptionError(RuntimeException e) {
        log.error("============接口全局异常处理 => RuntimeException", e);
        return new ResponseEntity<>(ApiResult.fail(ResultCodeEnum.SYSTEM_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}