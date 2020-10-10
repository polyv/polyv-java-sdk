package net.polyv.vod.config;

import net.polyv.common.base.HttpClientUtil;
import net.polyv.common.exception.GlobalUncaughtExceptionHandler;

/**
 * @author: thomas
 **/
public class VodGlobalConfig {
    /**
     * 点播userId
     */
    public static String USER_ID;
    
    /**
     * 写入密钥
     */
    public static String WRITE_TOKEN;
    
    /**
     * 读取数据密钥
     */
    public static String READ_TOKEN;
    
    /**
     * 鉴权密钥
     */
    public static String SECRET_KEY;
    
    
    /**
     * 点播业务全局初始化
     * @param userId 系统分配的USERID
     * @param writeToken 写入操作密钥
     * @param readToken 读取操作密钥
     * @param secretKey 鉴权密钥
     */
    public static void  init(String userId,String writeToken,String readToken,String secretKey){
        VodGlobalConfig.USER_ID = userId;
        VodGlobalConfig.WRITE_TOKEN = writeToken;
        VodGlobalConfig.READ_TOKEN=readToken;
        VodGlobalConfig.SECRET_KEY = secretKey;
        HttpClientUtil.init();
        //全局异常和错误处理配置
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
}
