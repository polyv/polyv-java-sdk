package net.polyv.live.config;

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
public class StartupListener implements ApplicationContextAware {
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initPolyvLive();
    }
    
    public void initPolyvLive() {
        String appId = "frlr1zazn3";
        String appSecret = "3a7cd25d068442f080adcb337c701fc3";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret);
        log.debug("--初始化完成--");
    }
    
    
}