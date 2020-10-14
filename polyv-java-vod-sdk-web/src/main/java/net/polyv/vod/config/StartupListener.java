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
        String userId = "1b448be323";
        String writeToken = "f9810825-7512-476d-95ec-9ff2968df5de";
        String readToken = "6e26de86-57f1-436d-8b3f-95ff69c971f7";
        String secretKey = "8eVs9NVrNm";
        String secretKeyBak = "VjmWPuCTpE";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.info("--初始化完成--");
    }
    
    
}