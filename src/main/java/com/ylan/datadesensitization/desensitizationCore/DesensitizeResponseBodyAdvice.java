package com.ylan.datadesensitization.desensitizationCore;

import cn.hutool.core.util.DesensitizedUtil;
import com.ylan.datadesensitization.desensitizationCore.annotation.Desensitize;
import com.ylan.datadesensitization.desensitizationCore.annotation.DesensitizeSupport;
import com.ylan.datadesensitization.desensitizationCore.enums.DesensitizationTypeEnum;
import com.ylan.datadesensitization.desensitizationCore.utils.PrivacyUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2023-10-27 14:23
 */

// 全局异常处理
// 全局数据绑定
// 全局数据预处理
@ControllerAdvice

public class DesensitizeResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    // R 中字段名称
    private final static String DATA_FILED = "data";

    // 身份证前N位不脱敏
    private final Integer ID_CARD_NUM_FRONT = 6;

    // 身份证后N位不脱敏
    private final Integer ID_CARD_NUM_END = 4;

    // 地址后N位脱敏
    private final Integer ADDRESS_SENSITIVE_SIZE = 8;

    /**
     * supports 返回为true时候执行beforeBodyWrite中的方法
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //1.首先判断该方法是否存在@DesensitizeSupport注解
        //2.判断类上是否存在
        Method method = returnType.getMethod();
        DesensitizeSupport annotation = method.getAnnotation(DesensitizeSupport.class);
        DesensitizeSupport clazzSup = method.getDeclaringClass().getAnnotation(DesensitizeSupport.class);
        return annotation != null || clazzSup != null;
    }

    /**
     * beforeBodyWrite
     * @param body 响应对象response中的响应体，可以对响应体做统一的处理，比如加密、签名、脱敏等操作
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            // 获取data字段
            Field dataField = body.getClass().getDeclaredField(DATA_FILED);
            // data字段设置可访问
            dataField.setAccessible(true);
            // data字段获取数据
            Object dataValue = dataField.get(body);

            // List类型
            if (dataValue instanceof List){
                List filedValue = (List) dataValue;
                // 对值进行脱敏
                for (Object obj : filedValue) {
                    desensitizationValue(obj);
                }
            }

            // Entity类型
            desensitizationValue(dataValue);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return body;
    }

    private void desensitizationValue(Object obj) throws IllegalAccessException {
        if (obj != null){
            // 获取要脱敏的类
            Class<?> clazz = obj.getClass();
            // 获取本类和父类的属性
            List<Field> fieldList = getAllFields(clazz);

            // 遍历所有属性 进行字段脱敏
            for (Field field : fieldList) {
                // 获取属性上的注解
                Desensitize annotation = field.getAnnotation(Desensitize.class);
                if (annotation == null) continue;

                // 判断属性的类型非字符串的类型 直接返回
                Class<?> type = field.getType();
                if (String.class != type) continue;

                // 获取脱敏注解内数据信息
                DesensitizationTypeEnum annotType = annotation.type();
                int startInclude = annotation.startInclude();
                int endExclude = annotation.endExclude();
                char symbol = annotation.symbol();

                // 对该字段进行数据脱敏，并设置脱敏后的值
                field.setAccessible(true);
                String oldValue = (String) field.get(obj);
                String newValue = dataMasking(annotType, oldValue, startInclude, endExclude, symbol);
                field.set(obj, newValue);
            }
        }
    }

    /**
     * 获取所有的字段（包括父类的）
     * @param clazz
     * @return
     */
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        // 循环获取类的所有字段
        while (clazz != null) {
            // 当前类的所有字段
            Field[] declaredFields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(declaredFields));
            // 获取父类，然后获取父类的属性
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 数据脱敏方法
     * @param annotType
     * @param oldValue
     * @param startInclude
     * @param endExclude
     * @param symbol
     * @return
     * @throws IOException
     */
    private String dataMasking(DesensitizationTypeEnum annotType, String oldValue, int startInclude, int endExclude, char symbol) {
        // 脱敏后的数据
        String newValue = null;

        switch (annotType) {
            // 自定义类型脱敏 [startInclude,endExclude)
            case CUSTOMIZE_RULE:
                newValue = PrivacyUtil.hide(oldValue, startInclude, endExclude, symbol);
            // 用户id脱敏
            case USER_ID:
                newValue = String.valueOf(DesensitizedUtil.userId());
                break;
            // 中文姓名脱敏
            case CHINESE_NAME:
                newValue = DesensitizedUtil.chineseName(String.valueOf(oldValue));
                break;
            // 身份证脱敏
            case ID_CARD:
                newValue = DesensitizedUtil.idCardNum(String.valueOf(oldValue), ID_CARD_NUM_FRONT, ID_CARD_NUM_END);
                break;
            // 固定电话脱敏
            case FIXED_PHONE:
                newValue = DesensitizedUtil.fixedPhone(String.valueOf(oldValue));
                break;
            // 手机号脱敏 前三后四不掩码，其他全部用*进行掩码
            case MOBILE_PHONE:
                newValue = DesensitizedUtil.mobilePhone(String.valueOf(oldValue));
                break;
            // 地址脱敏 脱敏后N位
            case ADDRESS:
                newValue = DesensitizedUtil.address(String.valueOf(oldValue), ADDRESS_SENSITIVE_SIZE);
                break;
            // 邮箱脱敏
            case EMAIL:
                newValue = DesensitizedUtil.email(String.valueOf(oldValue));
                break;
            // 密码脱敏
            case PASSWORD:
                newValue = DesensitizedUtil.password(String.valueOf(oldValue));
                break;
            // 中国大陆车牌脱敏
            case CAR_LICENSE:
                newValue = DesensitizedUtil.carLicense(String.valueOf(oldValue));
                break;
            // 银行卡脱敏
            case BANK_CARD:
                newValue = DesensitizedUtil.bankCard(String.valueOf(oldValue));
                break;
            default:
                newValue = oldValue;
        }
        return  newValue;
    }
}