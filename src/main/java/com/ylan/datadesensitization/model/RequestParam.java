package com.ylan.datadesensitization.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author by pepsi-wyl
 * @date 2023-11-27 21:13
 * @deprecated 请求参数实体类
 */
@Data
public class RequestParam {

    @NotNull(message = "缺少page参数")

    private Integer page;

    @NotNull(message = "缺少rows参数")
    private Integer rows;

    @NotNull(message = "缺少userName参数")
    private String userName;
}