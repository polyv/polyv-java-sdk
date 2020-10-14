package net.polyv.vod.service.account.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.config.VodGlobalConfig;
import net.polyv.vod.constant.VodURL;
import net.polyv.vod.entity.account.VodAccountSpaceDataRequest;
import net.polyv.vod.entity.account.VodAccountSpaceDataResponse;
import net.polyv.vod.service.VodBaseService;
import net.polyv.vod.service.account.IVodAccountService;

/**
 * @author: thomas
 **/
public class VodAccountServiceImpl extends VodBaseService implements IVodAccountService {
    /**
     * 获取用户空间及流量情况 , API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-api-main/getspaceandflow/
     * @param vodAccountSpaceDataRequest 获取用户空间及流量情况请求实体
     * @return 获取用户空间及流量详细数据
     * @throws IOException IO异常
     * @throws NoSuchAlgorithmException 签名算法异常
     */
    @Override
    public VodAccountSpaceDataResponse getAccountSpaceFlow(VodAccountSpaceDataRequest vodAccountSpaceDataRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.ACCOUNT_SPACE_FLOW_URL;
        VodAccountSpaceDataResponse vodAccountSpaceDataResponse = this.basePostJson(url, VodGlobalConfig.USER_ID,
                vodAccountSpaceDataRequest, VodAccountSpaceDataResponse.class);
        return vodAccountSpaceDataResponse;
    }
}
