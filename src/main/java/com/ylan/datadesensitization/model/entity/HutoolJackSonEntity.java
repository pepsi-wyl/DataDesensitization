package com.ylan.datadesensitization.model.entity;

import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import com.ylan.datadesensitization.desensitizationCore.annotation.Desensitization;
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
public class HutoolJackSonEntity {

    @Desensitization(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 5, endExclude = 10, symbol = '#')
    private String custom;

    @Desensitization(type = DesensitizationTypeEnum.USER_ID)
    private String userId;

    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String userName;

    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Desensitization(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String fixedPhone;

    @Desensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @Desensitization(type = DesensitizationTypeEnum.CAR_LICENSE)
    private String carLicense;

    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @Desensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;

}