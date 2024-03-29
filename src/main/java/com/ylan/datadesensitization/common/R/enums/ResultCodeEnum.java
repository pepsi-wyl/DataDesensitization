package com.ylan.datadesensitization.common.R.enums;

import lombok.Getter;

/**
 * @author by pepsi-wyl
 * @date 2023-10-30 9:27
 * @Description: 接口结果状态码枚举类
 */

@Getter
public enum ResultCodeEnum {

    // =================================================<<<<<<<<<<<<<<<<<<<<<<< HTTP状态码 >>>>>>>>>>>>>>>>>>================================================================
    HTTP_200(200,"请求成功"),
    HTTP_500(500,"请求失败"),


    // =================================================<<<<<<<<<<<<<<<<<<<<<<< 系统异常状态码 [9001-9999] >>>>>>>>>>>>>>>>>>================================================================
    SUBRESOURCE_ERROR(9000,"资源不存在异常"),
    SYSTEM_ERROR(9001,"系统错误"),
    NULL_POINTER_ERROR(9002,"空指针异常"),
    PARAM_ERROR(9003,"参数错误"),

    // =================================================<<<<<<<<<<<<<<<<<<<<<<< 接口服务状态码 [1001-1999]>>>>>>>>>>>>>>>>>>================================================================
    // ......

    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}