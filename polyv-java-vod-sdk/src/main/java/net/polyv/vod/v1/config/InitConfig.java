package net.polyv.vod.v1.config;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.entity.AccountInfo;
import net.polyv.common.v1.util.FileUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
//     * 点播初始化
//     */
//    public static void initPolyvVod() {
//        String userId = "xxxxxx";
//        String writeToken = "xxxxx-x";
//        String readToken = "xxxxx-x-x";
//        String secretKey = "xxxxx";
//        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
//        log.debug("--初始化完成--");
//    }
    
    /**
     * 初始化配置
     * 从文件读取JSON初始化配置，JSON格式如下：liveConfig:直播配置，vodConfig：点播配置
     * {"liveConfig":{"appId":"xxx","userId":"xxx","appSecret":"xxx"},"vodConfig":{"userId":"xxx",
     * "writeToken":"xxxxxxe","readToken":"xxxxxx","secretKey":"xxxx"}}
     */
    public static void initPolyvVodByFile(String path) {
        AccountInfo accountInfo = FileUtil.readConfigFromFile(path);
        String userId = accountInfo.getVodConfig().getUserId();
        String appId = accountInfo.getVodConfig().getAppId();
//        String writeToken = accountInfo.getVodConfig().getWriteToken();
//        String readToken = accountInfo.getVodConfig().getReadToken();
        String secretKey = accountInfo.getVodConfig().getSecretKey();
//        VodGlobalConfig.init(userId, appId, writeToken, readToken, secretKey);
        VodGlobalConfig.init(userId, appId,  secretKey);
        log.debug("--初始化完成--");
    }
    
    
}
