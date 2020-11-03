package net.polyv.live.config;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.entity.AccountInfo;
import net.polyv.common.util.FileUtil;

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
        String appId = "xxxxx";
        String userId = "xxxxxxx";
        String appSecret = "xxxxxxx";
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.debug("--初始化完成--");
    }
    
    /**
     * 初始化配置
     */
    public static void initPolyvLiveByFile(){
        AccountInfo accountInfo = FileUtil.readConfigFromFile(null);
        String appId = accountInfo.getLiveConfig().getAppId();
        String userId = accountInfo.getLiveConfig().getUserId();
        String appSecret =accountInfo.getLiveConfig().getAppSecret();
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.debug("--初始化完成--");
    }
}
