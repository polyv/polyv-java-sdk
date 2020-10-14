package net.polyv.vod.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.polyv.vod.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.service.account.impl.VodAccountServiceImpl;

/**
 * 渠道管理测试类接入DEMO
 * @author: thomas
 **/
@Controller
@RequestMapping("/channel")
@Api(value="点播账户管理",tags="点播账户管理")
public class AccountAction {
    
    /**
     *  获取用户空间及流量情况 , API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-main/getspaceandflow/
     * @param vodAccountSpaceDataRequest 入参封装
     * @return 账户的详细返回数据
     * @throws IOException  IO异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    @ApiOperation(value="获取用户空间及流量情况 , API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-main/getspaceandflow/",notes = "调用示例：参考polyv-java-vod-sdk单元测试 VodAccountServiceImplTest.testGetAccountSpaceFlow()方法。<a target=\"_blank\"  href=\"http://47.115.173.234:3000/#/channel/channelManager?id=sdk%e9%a2%91%e9%81%93%e6%93%8d%e4%bd%9c\">获取用户空间及流量情况 </a>    " )
    @PostMapping("/getAccountSpaceFlow")
    @ResponseBody
    public VodAccountSpaceDataResponse getAccountSpaceFlow(VodAccountSpaceDataRequest vodAccountSpaceDataRequest)
            throws IOException, NoSuchAlgorithmException {
        return new VodAccountServiceImpl().getAccountSpaceFlow(vodAccountSpaceDataRequest);
    }
    
    
}
