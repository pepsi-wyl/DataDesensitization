package com.ylan.datadesensitization.model.entity;

import com.ylan.datadesensitization.core.DesensitizationTypeEnum;
import com.ylan.datadesensitization.core.annotation.Desensitization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:28
 * 测试实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TestEntity {

    @Desensitization(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 5, endExclude = 10)
    private String custom;

    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String userName;

    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Desensitization(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 0, endExclude = 2)
    private String address;

    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

}