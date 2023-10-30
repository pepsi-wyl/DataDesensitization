package com.ylan.datadesensitization.common.R;

import com.ylan.datadesensitization.common.R.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * @author by pepsi-wyl
 * @date 2023-10-30 10:56
 * @Description: 接口业务异常类
 */

@Getter
public class BizException extends RuntimeException{

    /**
     * 接口结果状态码枚举类
     */
    private ResultCodeEnum resultCodeEnum;

    public BizException(ResultCodeEnum resultCodeEnum) {
        super("(code => " + resultCodeEnum.getCode().toString() + " message => " + resultCodeEnum.getMessage() + ")");
        this.resultCodeEnum = resultCodeEnum;
    }
}