



您可以通过直接添加Maven依赖方式安装POLYV SDK for Java 的点播功能。 

## 前提条件

在安装和使用POLYV SDK for Java前，确保您已经：

- 安装Java环境。

  POLYV SDK for Java要求使用JDK1.8或更高版本。

- 注册[保利威账号](https://www.polyv.net/)并获取[访问密钥信息](http://my.polyv.net/secure/setting/api)（**userid** 、**writetoken**、**readtoken**、**secretkey**）。

  > 注意：<font color=#FF0000 >本系统默认演示账号的登录名为：sdk-demo\@polyv.net   ,   密码为：sdk2345</font>  
  >
  > <font color=#FF0000 >本系统默认演示账号信息为:
  >
  > userid= 1b448be323  
  >
  > writetoken= f9810825-7512-476d-95ec-9ff2968df5de  
  >
  >  readtoken= 6e26de86-57f1-436d-8b3f-95ff69c971f7 
  >
  >  secretkey= 8eVs9NVrNm </font>
  
   
  
- 密钥参数作用
  
  - **useId**：用于保利威视服务器与您的服务器进行通讯的时候的身份验证
  - **readtoken**：读密钥，用来从保利威视服务器上读取数据
  - **writetoken**：写密钥，用来向保利威视服务器上写入数据
  - **secretkey**：调用保利威视的API接口做签名访问时要用到
  - 要获取到userId、writetoken、readtoken、secretkey这四个参数的值，可以在**顶部功能列表**的**右侧**点击“**设置**”—点击“**API接口**”，如下图所示:
  
  ![QQ图片20160621162908](img/QQ%E5%9B%BE%E7%89%8720160621162908.png)

## 1.添加Maven依赖 

如果您使用Maven管理Java项目，可以通过在pom.xml文件中添加Maven依赖安装POLYV SDK for Java。点播SDK产品的Maven依赖信息如下： 

```xml
<dependency>
    <groupId>net.polyv</groupId>
     <artifactId>polyv-java-vod-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
 <dependency>
     <groupId>junit</groupId>
     <artifactId>junit</artifactId>
     <version>4.12</version>
     <scope>test</scope>
</dependency>
```

> 注意：<font color=#FF0000 >为了消除Java的冗长代码  ,SDK使用了jdk1.8的最新特性lombok，请在IntelliJ或者Eclipse 中配置对lombok的支持 </font>

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
</dependency>
```

## 2.初始化系统

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



## 3.执行测试代码

测试获取用户空间及流量情况，单元测试代码如下：

```java
package  net.polyv.vod.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.polyv.vod.config.VodGlobalConfig;
import net.polyv.vod.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.service.account.impl.VodAccountServiceImpl;

/**
 * @author: thomas
 **/
public class VodAccountServiceImplTest   {
    
    /**
     * 初始化全局配置
     */
    public VodAccountServiceImplTest(){
        String userId = "1b448be323";
        String writeToken = "f9810825-7512-476d-95ec-9ff2968df5de";
        String readToken = "6e26de86-57f1-436d-8b3f-95ff69c971f7";
        String secretKey = "8eVs9NVrNm";
        String secretKeyBak = "VjmWPuCTpE";
        VodGlobalConfig.init(userId, writeToken, readToken, secretKey);
        System.out.println("--初始化完成--");
    }
    /**
     * 获取用户空间及流量情况
     */
    @Test
    public void  testGetAccountSpaceFlow() throws IOException, NoSuchAlgorithmException {
        VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest().setDate("2020-10-13");
        VodAccountSpaceDataResponse accountSpaceFlow = new VodAccountServiceImpl().getAccountSpaceFlow(
                vodAccountSpaceDataRequest);
        Assert.assertNotNull(accountSpaceFlow);
        Assert.assertNotEquals(accountSpaceFlow.getTotalFlow(),new Long(0));
    }
} 
```

执行单元测试后，控制台应有如下关键输出，表示整合完成：

```JSON
[main] INFO net.polyv.common.base.HttpClientUtil - ---init HTTP POOL httpClient ----
--初始化完成--

[main] DEBUG net.polyv.vod.util.VodSignUtil - 参与签名参数：{"date":"2020-10-13","requestId":"e3a896bead224fefb9f4410a46e3ad3b","userid":"1b448be323","ptime":"1602747282665"}
[main] DEBUG net.polyv.vod.util.VodSignUtil - 签名原始字符串：date=2020-10-13&ptime=1602747282665&requestId=e3a896bead224fefb9f4410a46e3ad3b&userid=1b448be3238eVs9NVrNm
[main] DEBUG net.polyv.vod.util.VodSignUtil - 签名结果：7A8787F61283ACD3A7B6EC3058EA0BF559795725
[AppClassLoader@18b4aac2] warning javax.* types are not being woven because the weaver option '-Xset:weaveJavaxPackages=true' has not been specified
十月 15, 2020 3:34:43 下午 org.hibernate.validator.internal.util.Version <clinit>
INFO: HV000001: Hibernate Validator 5.0.0.Final
[main] DEBUG net.polyv.common.base.HttpUtil - http 请求 url: https://api.polyv.net/v2/user/1b448be323/main?date=2020-10-13&requestId=e3a896bead224fefb9f4410a46e3ad3b&sign=7A8787F61283ACD3A7B6EC3058EA0BF559795725&userid=1b448be323&ptime=1602747282665 , 请求参数: {"date":"2020-10-13","ptime":"1602747282665","requestId":"e3a896bead224fefb9f4410a46e3ad3b","sign":"7A8787F61283ACD3A7B6EC3058EA0BF559795725","userid":"1b448be323"}
[main] DEBUG net.polyv.common.base.HttpUtil - http 请求结果: {"code":200,"status":"success","message":"success","data":{"videoCount":3,"totalFlow":21474836480,"usedSpace":0,"usedFlow":0,"totalSpace":21474836480,"userId":"1b448be323","email":"sdk-demo@polyv.net"}}


[Thread-1] INFO net.polyv.common.base.HttpClientUtil - -----destroy HTTP POOL httpClient------
```



至此，您已经完成点播SDK基本配置，可以使用点播SDK进行其他功能开发和测试，如您接入过程有任何问题，可以通过以下方式反馈：

1.发邮件反馈，wujie@polyv.net ；

2.[官网在线咨询反馈](https://www.polyv.net/)；

请将问题的运行环境、操作步骤、错误反馈信息、联系方式同步反馈，便于问题的快速定位和解决； 


















