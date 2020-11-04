package net.polyv.live.config;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.entity.AccountInfo;
import net.polyv.common.util.FileUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
    private InitConfig() {
    }
    
    /**
     * 初始化配置
     */
    public static void initPolyvLive(String appId, String userId, String appSecret) {
        LiveGlobalConfig.init(appId, userId, appSecret);
        log.debug("--初始化完成--");
    }
    
    
    /**
     * 初始化配置
     * 从文件读取JSON初始化配置，JSON格式如下：liveConfig:直播配置，vodConfig：点播配置
     * {"liveConfig":{"appId":"xxx","userId":"xxx","appSecret":"xxx"},"vodConfig":{"userId":"xxx","writeToken":"xxxxxxe","readToken":"xxxxxx","secretKey":"xxxx"}}
     */
    public static void initPolyvLiveByFile(String path) {
        AccountInfo accountInfo = FileUtil.readConfigFromFile(path);
        String appId = accountInfo.getLiveConfig().getAppId();
        String userId = accountInfo.getLiveConfig().getUserId();
        String appSecret = accountInfo.getLiveConfig().getAppSecret();
        InitConfig.initPolyvLive(appId, userId, appSecret);
    }
}
