package net.polyv.vod.v1.service.account;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.v1.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.account.impl.VodAccountServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 获取用户空间及流量情况
 * @author: thomas
 **/
@Slf4j
public class VodAccountServiceImplTest extends BaseTest {
    
    /**
     * 获取用户空间及流量情况
     * API地址：ACCOUNT_SPACE_FLOW_URL
     */
    @Test
    public void testGetAccountSpaceFlow() throws Exception, NoSuchAlgorithmException {
        VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest();
        VodAccountSpaceDataResponse vodAccountSpaceDataResponse;
        try {
            vodAccountSpaceDataRequest.setDate(super.getDate(2020, 10, 13)).setRequestId(VodSignUtil.generateUUID());
            vodAccountSpaceDataResponse = new VodAccountServiceImpl().getAccountSpaceFlow(vodAccountSpaceDataRequest);
            Assert.assertNotNull(vodAccountSpaceDataResponse);
            if (vodAccountSpaceDataResponse != null) {
                log.debug("测试获取用户空间及流量情况成功,{}", JSON.toJSONString(vodAccountSpaceDataResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试用例结束
     */
}
