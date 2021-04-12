package net.polyv.vod.v1.service;

import java.util.Calendar;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.v1.service.account.impl.VodAccountServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * @author: sadboy
 **/
@Slf4j
public class VodVideoDemo {
    
    /**
     * 调用demo，必须处理PloyvSdkException。
     *
     * 参数合法性校验：SDK采用自定义验证框架对输入参数进行校验，如有参数不合格，将抛出PloyvSdkException异常，exception的message
     * 包括具体校验不通过的字段信息，此异常是运行时异常，必须捕获处理相关业务逻辑；
     *
     * 解析返回数据：解析返回数据，如SDK调用正常成功，将封装响应对象，正常返回，如服务器返回错误信息，SDK将将抛出PloyvSdkException异常，exception的message
     * 包括具体服务器执行错误信息，此异常是运行时异常，必须捕获处理相关业务逻辑；
     * @param args
     */
    public static void main(String[] args) {
        String userId = "xxx";
        String secretKey = "xxx";
        VodGlobalConfig.init(userId, secretKey);
        log.debug("--初始化完成--");
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(2020, 10, 13);
            VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest();
            VodAccountSpaceDataResponse vodAccountSpaceDataResponse;
            vodAccountSpaceDataRequest.setDate(instance.getTime());
            vodAccountSpaceDataResponse = new VodAccountServiceImpl().getAccountSpaceFlow(vodAccountSpaceDataRequest);
            Assert.assertNotNull(vodAccountSpaceDataResponse);
            if (vodAccountSpaceDataResponse != null) {
                log.debug("测试获取用户空间及流量情况成功,{}", JSON.toJSONString(vodAccountSpaceDataResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
        } catch (Exception e) {
            log.error("SDK调用异常", e);
        }
    }
    
}
