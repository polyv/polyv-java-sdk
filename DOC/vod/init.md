#### 系统初始化

在执行测试代码之前，需要您先配置自己的测试账号信息，包括（**userid** 、**writetoken**、**readtoken**、**secretkey**） , 如没有以上信息，请参考本文[前提条件](/quick_start?id=前提条件)部分获取，下面用系统默认测试账号

````java
  public void init(){
        String userId = "1b448be323";
        String writeToken = "f9810825-7512-476d-95ec-9ff2968df5de";
        String readToken = "6e26de86-57f1-436d-8b3f-95ff69c971f7";
        String secretKey = "8eVs9NVrNm";
        String secretKeyBak = "VjmWPuCTpE";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        log.info("--初始化完成--");
    }
````

以上代码一般配置于随系统启动执行一次的全局初始化中，如用spring框架，可以参考如下：

````java
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
````

