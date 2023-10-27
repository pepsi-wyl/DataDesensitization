package com.ylan.datadesensitization.model.entity;

import com.ylan.datadesensitization.desensitizationCore.annotation.Desensitize;
import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 14:10
 * 测试实体类
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HutoolControllerAdviceEntity implements Serializable {

    @Desensitize(type = DesensitizationTypeEnum.CUSTOMIZE_RULE, startInclude = 5, endExclude = 10, symbol = '#')
    private String custom;

    @Desensitize(type = DesensitizationTypeEnum.USER_ID)
    private String userId;

    @Desensitize(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String userName;

    @Desensitize(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    @Desensitize(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Desensitize(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String fixedPhone;

    @Desensitize(type = DesensitizationTypeEnum.ADDRESS)
    private String address;

    @Desensitize(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @Desensitize(type = DesensitizationTypeEnum.CAR_LICENSE)
    private String carLicense;

    @Desensitize(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @Desensitize(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;

}