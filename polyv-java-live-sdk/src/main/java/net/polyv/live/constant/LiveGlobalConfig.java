package net.polyv.live.constant;

import net.polyv.common.base.HttpClientUtil;

/**
 * @author: thomas
 * @date: 2020/9/22
 **/

public class LiveGlobalConfig {
    
    public static String APP_ID = "";
    public static String USER_ID = "";
    public static String APP_SECRET = "";
    
    //重试策略
    public static Boolean RETRY_FLAG =  false;
//    public static String RETRY_ = "";
    
    public static void  init(String appId,String userId,String appSecret){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        HttpClientUtil.init();
    }
    
    public static void  init(String appId,String userId,String appSecret,Integer timeOut ,Integer maxClientNum){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpClientUtil.init();
    }
    
    public static void  init(String appId,String userId,String appSecret,Integer timeOut ,Integer maxClientNum,Boolean retryFlag){
        LiveGlobalConfig.APP_ID = appId;
        LiveGlobalConfig.USER_ID = userId;
        LiveGlobalConfig.APP_SECRET = appSecret;
        LiveGlobalConfig.RETRY_FLAG = retryFlag;
        HttpClientUtil.setTimeOut(timeOut);
        HttpClientUtil.setMaxClientNum(maxClientNum);
        HttpClientUtil.init();
    }
    
    
    
}
