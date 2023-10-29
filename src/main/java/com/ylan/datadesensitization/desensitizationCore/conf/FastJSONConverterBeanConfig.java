package com.ylan.datadesensitization.desensitizationCore.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ylan.datadesensitization.desensitizationCore.FastJSONDesensitizationValueFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by pepsi-wyl
 * @date 2023-10-29 21:22
 * @description: 在配置类中配置FastJSON转换器<创建FastJSON 序列化过滤器>
 */
@Configuration
public class FastJSONConverterBeanConfig {
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 添加自己写的拦截器
        fastJsonConfig.setSerializeFilters(new FastJSONDesensitizationValueFilter());
        // 3.在converter中添加配置信息
        converter.setFastJsonConfig(fastJsonConfig);
        // 4.返回HttpMessageConverters对象
        return converter;
    }
}