package  net.polyv.vod.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import net.polyv.vod.config.InitConfig;
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
        InitConfig.initVodPolyv();
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