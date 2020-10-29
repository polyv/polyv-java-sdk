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
        String userId = "1b448be323";
        String writeToken = "xxx-x";
        String readToken = "xxx-x-x";
        String secretKey = "xxx";
        
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
}
