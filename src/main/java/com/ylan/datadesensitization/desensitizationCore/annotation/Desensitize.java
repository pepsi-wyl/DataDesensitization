package com.ylan.datadesensitization.desensitizationCore.annotation;

import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import java.lang.annotation.*;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 13:52
 * @description: 标记在字段上，使用何种策略来脱敏
 */
@Documented
@Inherited                                       // 说明子类可以继承父类中的该注解
@Target(value = {ElementType.FIELD})             // 标识该注解作用于字段上
@Retention(value = RetentionPolicy.RUNTIME)      // 表示该注解运行时候生效
public @interface Desensitize {

    /**
     * 脱敏策略，在使用 CUSTOMIZE_RULE 的时候，startInclude 和 endExclude生效  默认 CUSTOMIZE_RULE
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.CUSTOMIZE_RULE;

    /**
     * 脱敏开始位置（包含） 默认 0
     */
    int startInclude() default 0;

    /**
     * 脱敏结束位置（不包含） 默认 0
     */
    int endExclude() default 0;

    /**
     * 脱敏字符 默认 *
     */
    char symbol() default '*';
}