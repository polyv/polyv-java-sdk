package net.polyv.live.v1.config;

import net.polyv.common.v1.base.HttpClientUtil;
import net.polyv.common.v1.base.HttpUtil;
import net.polyv.common.v1.exception.GlobalUncaughtExceptionHandler;

/**
 * 直播全局配置类
 * @author: thomas
 **/
 
public class LiveGlobalConfig {
    private LiveGlobalConfig(){};
    
    private static String APP_ID = "";
    private static String USER_ID = "";
    private static String APP_SECRET = "";
    
    public static String getAppId() {
        return APP_ID;
    }
    
    public static String getUserId() {
        return USER_ID;
    }
    
    public static String getAppSecret() {
        return APP_SECRET;
    }
    
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
        HttpUtil.SDK="LIVE_SDK";
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
        HttpUtil.SDK="LIVE_SDK";
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpClientUtil.init();
        //全局异常和错误处理配置
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
    
    
}
