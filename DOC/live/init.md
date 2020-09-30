#### 系统初始化

在执行业务代码之前，需要您先配置自己的测试账号信息，包括UserId \ AppId \ AppSecret , 如没有以上信息，请参考[前提条件](/quick_start?id=前提条件)部分获取，下面用系统默认测试账号

````java
  public void init(){
        String appId = "fqs60f693j";
        String appSecret = "ed96a87e486c4c9a9591cf2a61d72a67";
        String userId = "152de5237d";
        LiveGlobalConfig.init(appId,userId,appSecret);
        System.out.println("--初始化完成--");
    }
````

以上代码一般配置于随系统启动执行一次的全局初始化中，如用spring框架，可以参考如下：

````java
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
public class StartupListener implements ApplicationContextAware   {
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId,userId,appSecret);
        log.info("--初始化完成--");
    }
    
    
}
````

