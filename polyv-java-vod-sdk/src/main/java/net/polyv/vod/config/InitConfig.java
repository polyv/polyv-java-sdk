package net.polyv.vod.config;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.entity.AccountInfo;
import net.polyv.common.util.FileUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class InitConfig {
    
    /**
     * 点播初始化
     */
    public static void initPolyvVod() {
        String userId = "xxxxxx";
        String writeToken = "xxxxx-x";
        String readToken = "xxxxx-x-x";
        String secretKey = "xxxxx";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
    /**
     * 初始化配置
     */
    public static void initPolyvVodByFile(){
        AccountInfo accountInfo = FileUtil.readConfigFromFile(null);
        String userId = accountInfo.getVodConfig().getUserId();
        String writeToken = accountInfo.getVodConfig().getWriteToken();
        String readToken = accountInfo.getVodConfig().getReadToken();
        String secretKey = accountInfo.getVodConfig().getSecretKey();
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
    
}
