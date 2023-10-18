package com.ylan.datadesensitization.common.R;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:31
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResResult<T> {
    /**
     * 编码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static <T> ResResult<T> success(T data) {
        return new ResResult<>("200","成功", data);
    }
}
