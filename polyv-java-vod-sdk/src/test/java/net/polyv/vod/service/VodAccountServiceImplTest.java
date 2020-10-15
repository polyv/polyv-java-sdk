package net.polyv.vod.service;

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
@Epic("点播账户相关操作")
@Feature("点播账户相关操作1")
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
    @Story("获取账户空间及流量情况")
    public void  testGetAccountSpaceFlow() throws IOException, NoSuchAlgorithmException {
        VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest().setDate("2020-10-13");
        VodAccountSpaceDataResponse accountSpaceFlow = new VodAccountServiceImpl().getAccountSpaceFlow(
                vodAccountSpaceDataRequest);
        Assert.assertNotNull(accountSpaceFlow);
        Assert.assertNotEquals(accountSpaceFlow.getTotalFlow(),new Long(0));
    }
}
