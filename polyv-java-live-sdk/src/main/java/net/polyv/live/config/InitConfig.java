package net.polyv.live.config;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
    
    /**
     * 初始化配置
     */
    public static void initPolyvLive(){
        String appId = "xxxxx";
        String appSecret = "xxxxx";
        String userId = "xxx";
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.debug("--初始化完成--");
    }
    
}
