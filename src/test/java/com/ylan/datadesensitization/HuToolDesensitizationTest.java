package com.ylan.datadesensitization;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author by pepsi-wyl
 * @date 2023-10-18 20:51
 * @description: Hutool实现数据脱敏测试
 */

@Log
@SpringBootTest
public class HuToolDesensitizationTest {

    /**
     * 用户姓名
     */
    @Test
    public void testUserNameDesensitization(){
        // 武**
        String string = DesensitizedUtil.chineseName("武扬岚");
        log.info(string);
    }

    /**
     * 手机号
     */
    @Test
    public void testPhoneDesensitization(){
        // 136****0837
        String string = DesensitizedUtil.mobilePhone("13673330837");
        log.info(string);
    }

    /**
     * 身份证
     */
    @Test
    public void testIdCardDesensitization(){
        // 4110************21
        String string = DesensitizedUtil.idCardNum("411021199901102321",4,2);
        log.info(string);
    }

    /**
     * 银行卡
     */
    @Test
    public void testBankCardDesensitization(){
        // 6217 **** **** *** 5666
        String string = DesensitizedUtil.bankCard("6217000130008255666");
        log.info(string);
    }

}