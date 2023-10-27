package com.ylan.datadesensitization.desensitizationCore.annotation;

import java.lang.annotation.*;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 14:00
 * @description: 标记在类、方法上，是否需要脱敏
 */
@Documented
@Inherited                                            // 说明子类可以继承父类中的该注解
@Target(value={ElementType.METHOD, ElementType.TYPE}) // 标识该注解作用于类、方法上，是否需要脱敏
@Retention(value = RetentionPolicy.RUNTIME)           // 表示该注解运行时候生效
public @interface DesensitizeSupport {

}