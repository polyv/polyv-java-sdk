package net.polyv.channel;


import net.polyv.live.config.LiveGlobalConfig;

/**
 * @author: thomas
 * @date: 2020/9/23
 **/
public class BaseTest {
    BaseTest(){
    
//        HttpClientUtil.setTimeOut(10000);
        String appId = "fqs60f693j";
        String appSecret = "ed96a87e486c4c9a9591cf2a61d72a67";
        String userId = "152de5237d";
        LiveGlobalConfig.init(appId,userId,appSecret);
        System.out.println("--初始化完成--");
    }
    
}
