package net.polyv.vod.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认启动配置类
 * @author: thomas
 **/
@Slf4j
@Component
public class StartupListener implements ApplicationContextAware   {
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initVodPolyv();
    }
    
    private void initVodPolyv(){
        String userId = "1b448be323";
        String writeToken = "s2GUm9YXzWpU1Z6-Uagx0rs3oG0QNZQq";
        String readToken = "WmBI70idfN-4Sb9qfRot7-gqkkzWcCRG";
        String secretKey = "GyqkTJckCT";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.debug("--初始化完成--");
    }
    
    
    
}