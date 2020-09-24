package net.polyv.live.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveGlobalConfig;

/**
 * @author: thomas
 * @date: 2020/9/24
 **/
@Slf4j
@Component
public class StartupListener implements ApplicationContextAware   {
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String appId = "fqs60f693j";
        String appSecret = "ed96a87e486c4c9a9591cf2a61d72a67";
        String userId = "152de5237d";
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.info("--初始化完成--");
    }
    
    
}