package com.ylan.datadesensitization.model.entity;

import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import com.ylan.datadesensitization.desensitizationCore.annotation.JackSonDesensitization;
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

    @JackSonDesensitization(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 5, endExclude = 10, symbol = '#')
    private String custom;

    @JackSonDesensitization(type = DesensitizationTypeEnum.USER_ID)
    private String userId;

    @JackSonDesensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String userName;

    @JackSonDesensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    @JackSonDesensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @JackSonDesensitization(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String fixedPhone;

    @JackSonDesensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    @JackSonDesensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @JackSonDesensitization(type = DesensitizationTypeEnum.CAR_LICENSE)
    private String carLicense;

    @JackSonDesensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @JackSonDesensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;

}