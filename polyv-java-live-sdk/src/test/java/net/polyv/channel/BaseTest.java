package net.polyv.channel;


import net.polyv.live.config.LiveGlobalConfig;

/**
 * @author: thomas
 * @date: 2020/9/23
 **/
public class BaseTest {
    BaseTest(){
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId,userId,appSecret);
        System.out.println("--初始化完成--");
    }
    
}
