package net.polyv.live.config;

import net.polyv.common.base.HttpClientUtil;
import net.polyv.common.exception.GlobalUncaughtExceptionHandler;

/**
 * 直播全局配置类
 * @author: thomas
 **/

public class LiveGlobalConfig {
    
    public static String APP_ID = "";
    public static String USER_ID = "";
    public static String APP_SECRET = "";
    
    //重试策略
    public static Boolean RETRY_FLAG =  false;
 
    
    /**
     *  系统全局初始化
     * @param appId appId
     * @param userId userId
     * @param appSecret appSecret
     */
    public static void  init(String appId,String userId,String appSecret){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        HttpClientUtil.init();
        //全局异常和错误处理配置
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
    
    /**
     *  系统全局初始化
     * @param appId appId
     * @param userId userId
     * @param appSecret appSecret
     * @param timeOut timeOut
     * @param maxClientNum maxClientNum
     */
    public static void  init(String appId,String userId,String appSecret,Integer timeOut ,Integer maxClientNum){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpClientUtil.init();
    }
    
    /**
     *  系统全局初始化
     * @param appId appId
     * @param userId userId
     * @param appSecret appSecret
     * @param timeOut timeOut
     * @param maxClientNum maxClientNum
     * @param retryFlag retryFlag
     */
    private static void  init(String appId,String userId,String appSecret,Integer timeOut ,Integer maxClientNum,Boolean retryFlag){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        LiveGlobalConfig.RETRY_FLAG = retryFlag;
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpClientUtil.init();
    }
    
    
    
}
