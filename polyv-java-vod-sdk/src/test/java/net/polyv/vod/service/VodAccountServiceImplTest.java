package net.polyv.vod.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import net.polyv.vod.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.service.account.impl.VodAccountServiceImpl;
import net.polyv.vod.util.VodSignUtil;

/**
 * @author: thomas
 **/
public class VodAccountServiceImplTest extends BaseTest {
    
    
    /**
     * 获取用户空间及流量情况
     */
    @Test
    public void testGetAccountSpaceFlow() throws Exception, NoSuchAlgorithmException {
        VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest();
        vodAccountSpaceDataRequest.setDate(super.getDate(2020,10,13))
                .setPtime(new Date().getTime())
                .setRequestId(VodSignUtil.generateUUID());
        VodAccountSpaceDataResponse accountSpaceFlow = new VodAccountServiceImpl().getAccountSpaceFlow(
                vodAccountSpaceDataRequest);
        Assert.assertNotNull(accountSpaceFlow);
        Assert.assertNotEquals(accountSpaceFlow.getTotalFlow(), new Long(0));
    }
}
