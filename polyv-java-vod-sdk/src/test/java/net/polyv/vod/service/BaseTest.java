package net.polyv.vod.service;

import net.polyv.vod.config.VodGlobalConfig;

/**
 * @author: thomas
 **/
public class BaseTest {
    /**
     * 系统默认初始化
     */
    BaseTest() {
        String userId = "1b448be323";
        String writeToken = "f9810825-7512-476d-95ec-9ff2968df5de";
        String readToken = "6e26de86-57f1-436d-8b3f-95ff69c971f7";
        String secretKey = "8eVs9NVrNm";
        String secretKeyBak = "VjmWPuCTpE";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        System.out.println("--初始化完成--");
    }
    
}
