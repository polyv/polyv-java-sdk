package net.polyv.live.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
    private InitConfig(){}
    
    /**
     * 初始化配置
     */
    public static void initPolyvLive(){
        String appId = "frlr1zazn3";
        String userId = "xxxx";
        String appSecret = "xxxx";
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.debug("--初始化完成--");
    }
    
}
