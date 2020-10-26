package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.web.interact.LiveChannelDonateRequest;
import net.polyv.live.entity.web.interact.LiveChannelDonateResponse;
import net.polyv.live.entity.web.interact.LiveUpdateChannelCashRequest;
import net.polyv.live.entity.web.interact.LiveUpdateChannelGoodRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.web.ILiveWebInteractService;
import net.polyv.live.util.MapUtil;

/**
 * @author: sadboy
 **/
public class LiveWebInteractServiceImpl extends LiveBaseService implements ILiveWebInteractService {
    
    /**
     * 测试设置道具打赏
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/updategood/
     * @param liveUpdateChannelGoodRequest 设置道具打赏请求实体
     * @return 设置道具打赏返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelGood(LiveUpdateChannelGoodRequest liveUpdateChannelGoodRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.GOOD_DONATE_SET_URL;
        Map<String,String> signMap = MapUtil.getSignMap(liveUpdateChannelGoodRequest);
        signMap.put("channelId",String.valueOf(liveUpdateChannelGoodRequest.getChannelId()));
        String liveUpdateChannelGoodResponse = this.basePostJson(url, signMap, liveUpdateChannelGoodRequest, String.class);
        return "true".equals(liveUpdateChannelGoodResponse);
    }
    
    /**
     * 设置现金打赏
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/updatecash/
     * @param liveUpdateChannelCashRequest 设置现金打赏请求实体
     * @return 设置现金打赏返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelCash(LiveUpdateChannelCashRequest liveUpdateChannelCashRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CASH_DONATE_SET_URL;
        Map<String,String> signMap = MapUtil.getSignMap(liveUpdateChannelCashRequest);
        signMap.put("channelId",String.valueOf(liveUpdateChannelCashRequest.getChannelId()));
        String liveUpdateChannelCashResponse = this.basePostJson(url, signMap, liveUpdateChannelCashRequest, String.class);
        return "true".equals(liveUpdateChannelCashResponse);
    }
    
    /**
     * 查询打赏设置
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/donate-get/
     * @param liveChannelDonateRequest 查询打赏设置请求实体
     * @return 查询打赏设置返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelDonateResponse channelDonate(LiveChannelDonateRequest liveChannelDonateRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_DONATE_GET_URL;
        LiveChannelDonateResponse liveChannelDonateResponse = this.baseGet(url, liveChannelDonateRequest,
                LiveChannelDonateResponse.class);
        return liveChannelDonateResponse;
    }
    
}
