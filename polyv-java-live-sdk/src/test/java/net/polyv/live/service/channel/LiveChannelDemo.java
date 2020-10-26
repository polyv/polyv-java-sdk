package net.polyv.live.service.channel;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.config.InitConfig;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.service.channel.impl.LiveChannelOperateServiceImpl;

/**
 * @author: thomas
 **/
@Slf4j
public class LiveChannelDemo {
    /**
     * 调用demo，必须处理BusinessException。
     * <p>
     * 参数合法性校验：SDK采用Hibernate-validator规范对输入参数进行校验，如有参数不合格，将抛出BusinessionException异常，exception的message
     * 包括具体校验不通过的字段信息，此异常是运行时异常，必须捕获处理相关业务逻辑；
     * <p>
     * 解析返回数据：解析返回数据，如SDK调用正常成功，将封装响应对象，正常返回，如服务器返回错误信息，SDK将将抛出BusinessionException异常，exception的message
     * 包括具体服务器执行错误信息，此异常是运行时异常，必须捕获处理相关业务逻辑；
     * @param args
     */
    public static void main(String[] args) {
        //全局初始化，此处应该全局执行一次
        InitConfig.initPolyvLive();
        try {
            LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
            liveChannelRequest.setName("Spring 知识精讲") //设置频道主题信息
                    .setChannelPasswd("666888")   //设置频道密码
                    .setRequestId("2860257a405447e1bbbe9161da2dee72"); // 设置请求流水号
            LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(
                    liveChannelRequest);
            Assert.assertNotNull(liveChannelResponse);
            if (liveChannelResponse != null) {
                //to do something ......
                log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
            }
        } catch (PloyvSdkException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error("SDK调用异常", e);
        }
    }
    
    
}
