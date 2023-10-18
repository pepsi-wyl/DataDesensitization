package com.ylan.datadesensitization.core.annotation;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:11
 * @description: 字段脱敏注解Desensitization
 */

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ylan.datadesensitization.core.DesensitizationSerialize;
import com.ylan.datadesensitization.core.DesensitizationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)          // 作用于字段上
@Retention(RetentionPolicy.RUNTIME) // 运行时候生效
@JacksonAnnotationsInside           //
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {

    /**
     * 脱敏数据类型，在 CUSTOMIZE_RULE 的时候，startInclude和endExclude生效
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
}