package com.ylan.datadesensitization.desensitizationCore.annotation;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:11
 * @description: 字段脱敏注解
 */

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ylan.datadesensitization.desensitizationCore.serialize.DesensitizationSerialize;
import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)          // 标识该注解作用于字段上
@Retention(RetentionPolicy.RUNTIME) // 表示该注解运行时候生效
@JacksonAnnotationsInside           // 元注解 <用户打包其他注解一起使用>
@JsonSerialize(using = DesensitizationSerialize.class)  // 自定义我们的序列化规则
public @interface Desensitization {

    /**
     * 脱敏策略，在使用 CUSTOMIZE_RULE 的时候，startInclude 和 endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.CUSTOMIZE_RULE;

    /**
     * 脱敏开始位置（包含）
     */
    int startInclude() default 0;

    /**
     * 脱敏结束位置（不包含）
     */
    int endExclude() default 0;

    /**
     * 脱敏字符 *
     */
    String symbol() default "*";
}