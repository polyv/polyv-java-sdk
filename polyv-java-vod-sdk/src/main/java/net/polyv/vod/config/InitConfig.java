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
        String writeToken = "s2GUm9YXzWpU1Z6-Uagx0rs3oG0QNZQq";
        String readToken = "WmBI70idfN-4Sb9qfRot7-gqkkzWcCRG";
        String secretKey = "GyqkTJckCT";
        
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
}
