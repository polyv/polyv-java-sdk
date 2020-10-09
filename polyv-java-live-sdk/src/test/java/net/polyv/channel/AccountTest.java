package net.polyv.channel;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.account.LiveListAccountDetailRequest;
import net.polyv.live.entity.account.LiveListAccountDetailResponse;
import net.polyv.live.entity.account.LiveListAccountRequest;
import net.polyv.live.entity.account.LiveListAccountResponse;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordRequest;
import net.polyv.live.entity.channel.LiveListChannelPPTRecordResponse;
import net.polyv.live.service.account.impl.LiveAccountServiceImpl;
import net.polyv.live.service.channel.impl.LiveChannelServiceImpl;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: sadboy
 **/
@Slf4j
public class AccountTest {

 
    public AccountTest(){
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret);
        System.out.println("--初始化完成--");
    }

    /**
     * 分页查询账号下所有频道详细信息成功
     * @throws IOException
     */
    @Test
    public void testListAccountDetail() throws IOException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        liveListAccountDetailRequest.setPage(1);
        LiveListAccountDetailResponse liveListAccountDetailResponse = new LiveAccountServiceImpl().listAccountDetail(liveListAccountDetailRequest);
        Assert.assertNotNull(liveListAccountDetailResponse);
        if(liveListAccountDetailResponse != null){
            //to do something ......
            log.debug("分页查询账号下所有频道详细信息成功" + JSON.toJSONString(liveListAccountDetailResponse));
        }
    }
    
    /**
     * 查询账号下的频道列表(频道号列表)
     * TODO 等待api端修改后再写
     * @throws IOException
     */
    @Test
    public void testListAccount() throws IOException {
//        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
//        LiveListAccountResponse liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
//        Assert.assertNotNull(liveListAccountDetailResponse);
//        if(liveListAccountDetailResponse != null){
//            //to do something ......
//            log.debug("分页查询账号下所有频道详细信息成功" + JSON.toJSONString(liveListAccountDetailResponse));
//        }
    }
    
    
}
