package com.ylan.datadesensitization.desensitizationCore;

import cn.hutool.core.util.DesensitizedUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.ylan.datadesensitization.desensitizationCore.annotation.FastJSONDesensitization;
import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import com.ylan.datadesensitization.desensitizationCore.utils.PrivacyUtil;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 17:00
 * @description: FastJSON 脱敏序列化过滤器
 */
public class FastJSONDesensitizationValueFilter implements ValueFilter {

    // 身份证前N位不脱敏
    private final Integer ID_CARD_NUM_FRONT = 6;

    // 身份证后N位不脱敏
    private final Integer ID_CARD_NUM_END = 4;

    // 地址后N位脱敏
    private final Integer ADDRESS_SENSITIVE_SIZE = 8;

    @Override
    public Object process(Object object, String name, Object value) {

        // 判断 值为空 或 值不是String
        if (ObjectUtils.isEmpty(value) || !(value instanceof String)) {
            return value;
        }

        try {
            // 获取字段反射信息
            Field field = object.getClass().getDeclaredField(name);

            // 获取字段注解信息
            FastJSONDesensitization fastJSONDesensitization = field.getAnnotation(FastJSONDesensitization.class);

            //  字段不存在FastJSONDesensitization注解 或者 字段属性类型不是 String
            if (ObjectUtils.isEmpty(fastJSONDesensitization) || String.class != field.getType()) {
                return value;
            }

            // 获取脱敏注解内数据信息
            String originVal = String.valueOf(value);
            DesensitizationTypeEnum type = fastJSONDesensitization.type();
            int startInclude = fastJSONDesensitization.startInclude();
            int endExclude = fastJSONDesensitization.endExclude();
            char symbol = fastJSONDesensitization.symbol();

            // 返回脱敏后的数据
            return dataMasking(type, originVal, startInclude, endExclude, symbol);

        } catch (NoSuchFieldException e) {
            new RuntimeException(e);
        }

        return value;
    }

    /**
     * 数据脱敏方法
     * @param type
     * @param originVal
     * @param startInclude
     * @param endExclude
     * @param symbol
     * @return
     */
    private String dataMasking(DesensitizationTypeEnum type, String originVal, int startInclude, int endExclude, char symbol) {
        // 脱敏后的数据
        String newValue = null;

        switch (type) {
            // 自定义类型脱敏 [startInclude,endExclude)
            case CUSTOMIZE_RULE:
                newValue = PrivacyUtil.hide(originVal, startInclude, endExclude, symbol);
            // 用户id脱敏
            case USER_ID:
                newValue = String.valueOf(DesensitizedUtil.userId());
                break;
            // 中文姓名脱敏
            case CHINESE_NAME:
                newValue = DesensitizedUtil.chineseName(String.valueOf(originVal));
                break;
            // 身份证脱敏
            case ID_CARD:
                newValue = DesensitizedUtil.idCardNum(String.valueOf(originVal), ID_CARD_NUM_FRONT, ID_CARD_NUM_END);
                break;
            // 固定电话脱敏
            case FIXED_PHONE:
                newValue = DesensitizedUtil.fixedPhone(String.valueOf(originVal));
                break;
            // 手机号脱敏 前三后四不掩码，其他全部用*进行掩码
            case MOBILE_PHONE:
                newValue = DesensitizedUtil.mobilePhone(String.valueOf(originVal));
                break;
            // 地址脱敏 脱敏后N位
            case ADDRESS:
                newValue = DesensitizedUtil.address(String.valueOf(originVal), ADDRESS_SENSITIVE_SIZE);
                break;
            // 邮箱脱敏
            case EMAIL:
                newValue = DesensitizedUtil.email(String.valueOf(originVal));
                break;
            // 密码脱敏
            case PASSWORD:
                newValue = DesensitizedUtil.password(String.valueOf(originVal));
                break;
            // 中国大陆车牌脱敏
            case CAR_LICENSE:
                newValue = DesensitizedUtil.carLicense(String.valueOf(originVal));
                break;
            // 银行卡脱敏
            case BANK_CARD:
                newValue = DesensitizedUtil.bankCard(String.valueOf(originVal));
                break;
            default:
                newValue = originVal;
        }
        return  newValue;
    }
}