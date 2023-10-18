package com.ylan.datadesensitization.controller;

import com.ylan.datadesensitization.common.R.ResResult;
import com.ylan.datadesensitization.model.entity.TestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:30
 */

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test1")
    public ResResult testDesensitization(){

        TestEntity testEntity = TestEntity.builder()
                .custom("1111111111111111111")
                .userName("武扬岚")
                .password("daisudhajkdioain")
                .phone("13673330837")
                .address("地球-中国-北京市通州区永丰里E区")
                .email("pepsiwyl@gmail.com")
                .idCard("4444199810015555")
                .build();

        return ResResult.success(testEntity);
    }
}
