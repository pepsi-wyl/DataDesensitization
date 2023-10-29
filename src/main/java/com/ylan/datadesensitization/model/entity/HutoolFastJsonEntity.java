package com.ylan.datadesensitization.model.entity;

import com.ylan.datadesensitization.desensitizationCore.annotation.FastJSONDesensitization;
import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 17:28
 * 测试实体类
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HutoolFastJsonEntity {
    @FastJSONDesensitization(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 5, endExclude = 10, symbol = '#')
    private String custom;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.USER_ID)
    private String userId;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String userName;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String fixedPhone;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.CAR_LICENSE)
    private String carLicense;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @FastJSONDesensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;
}