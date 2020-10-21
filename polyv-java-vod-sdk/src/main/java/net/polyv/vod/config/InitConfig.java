package net.polyv.vod.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
    
    /**
     * 点播初始化
     */
    public static void initVodPolyv() {
        String userId = "xxxx";
        String writeToken = "xxxxxx";
        String readToken = "xxxx";
        String secretKey = "xxxx";
        
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
}
