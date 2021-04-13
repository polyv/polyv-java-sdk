package net.polyv.vod.v1.config;

import net.polyv.common.v1.base.HttpClientUtil;
import net.polyv.common.v1.base.HttpUtil;
import net.polyv.common.v1.exception.GlobalUncaughtExceptionHandler;

/**
 * @author: thomas
 **/  
public class VodGlobalConfig {
    /**
     * 点播userId
     */
    private static String USER_ID;
    
    /**
     * 写入密钥
     */
    private static String WRITE_TOKEN;
    
    /**
     * 读取数据密钥
     */
    private static String READ_TOKEN;
    
    /**
     * 鉴权密钥
     */
    private static String SECRET_KEY;
    
    
    
    public static String getUserId() {
        return USER_ID;
    }
    
    public static String getWriteToken() {
        return WRITE_TOKEN;
    }
    
    public static String getReadToken() {
        return READ_TOKEN;
    }
    
    public static String getSecretKey() {
        return SECRET_KEY;
    }
    
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
        HttpUtil.setSDK("JAVA_VOD_SDK");
        HttpClientUtil.init();
        //全局异常和错误处理配置
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
    
    /**
     * 点播业务全局初始化
     * @param userId 系统分配的USERID
     * @param writeToken 写入操作密钥
     * @param readToken 读取操作密钥
     * @param secretKey 鉴权密钥
     * @param timeOut HTTP连接超时时间
     * @param maxClientNum 连接池最大并发连接数
     */
    public static void  init(String userId,String writeToken,String readToken,String secretKey,Integer timeOut ,Integer maxClientNum){
        VodGlobalConfig.USER_ID = userId;
        VodGlobalConfig.WRITE_TOKEN = writeToken;
        VodGlobalConfig.READ_TOKEN=readToken;
        VodGlobalConfig.SECRET_KEY = secretKey;
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpUtil.setSDK("VOD_SDK");
        HttpClientUtil.init();
        //全局异常和错误处理配置
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
    
}
