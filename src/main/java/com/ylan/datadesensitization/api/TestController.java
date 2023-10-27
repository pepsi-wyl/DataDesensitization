package com.ylan.datadesensitization.api;

import com.ylan.datadesensitization.common.R.ResResult;
import com.ylan.datadesensitization.model.entity.HutoolJackSonEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 21:30
 */

@RestController
@RequestMapping("/desensitization")
public class TestController {

    // GET http://localhost:8080//desensitization/HutoolJackSonEntityTest
    @RequestMapping("/HutoolJackSonEntityTest")
    public ResResult HutoolJackSonEntityTest(){

        HutoolJackSonEntity testEntity = HutoolJackSonEntity.builder()
                .custom("测试自定义脱敏")
                .userId("9898989898989")
                .userName("武扬岚")
                .idCard("4444199810015555")
                .phone("13673330837")
                .fixedPhone("0372-5608110")
                .address("北京市通州区永丰里E区1号楼1单元601室")
                .email("pepsiwyl@gmail.com")
                .carLicense("京A88888")
                .password("daisudhajkdioain")
                .bankCard("6226920277165332")
                .build();

        return ResResult.success(testEntity);
    }
}
