package com.ylan.datadesensitization.common.R;

import com.ylan.datadesensitization.common.R.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:31
 * @description 接口返回对象
 */

@Data
@Accessors(chain = true)  // 链式调用
public class ApiResult<T> implements Serializable {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 接口请求时间
     */
    private long timestamp;

    private ApiResult(){}

    private ApiResult(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    private ApiResult(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(ResultCodeEnum.HTTP_200);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ResultCodeEnum.HTTP_200, data);
    }

    public static <T> ApiResult<T> success(ResultCodeEnum resultCodeEnum) {
        return new ApiResult<>(resultCodeEnum);
    }

    public static <T> ApiResult<T> success(ResultCodeEnum resultCodeEnum, T data) {
        return new ApiResult<>(resultCodeEnum, data);
    }

    public static <T> ApiResult<T> fail() {
        return new ApiResult<>(ResultCodeEnum.HTTP_500);
    }

    public static <T> ApiResult<T> fail(T data) {
        return new ApiResult<>(ResultCodeEnum.HTTP_500, data);
    }

    public static <T> ApiResult<T> fail(ResultCodeEnum resultCodeEnum) {
        return new ApiResult<>(resultCodeEnum);
    }

    public static <T> ApiResult<T> fail(ResultCodeEnum resultCodeEnum, T data) {
        return new ApiResult<>(resultCodeEnum, data);
    }

    @Override
    public String toString() {
        return "ApiResult(code=" + this.getCode() + ", msg=" + this.getMessage() + ", timestamp=" + this.getTimestamp() + ", data=" + this.getData() + ")";
    }
}