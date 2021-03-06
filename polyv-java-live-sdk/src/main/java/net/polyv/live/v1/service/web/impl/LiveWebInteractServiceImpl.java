package net.polyv.live.v1.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.web.interact.LiveChannelDonateRequest;
import net.polyv.live.v1.entity.web.interact.LiveChannelDonateResponse;
import net.polyv.live.v1.entity.web.interact.LiveGetChannelWxShareRequest;
import net.polyv.live.v1.entity.web.interact.LiveGetChannelWxShareResponse;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelCashRequest;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelGoodRequest;
import net.polyv.live.v1.entity.web.interact.LiveUpdateChannelWxShareRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.web.ILiveWebInteractService;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * @author: sadboy
 **/
public class LiveWebInteractServiceImpl extends LiveBaseService implements ILiveWebInteractService {
    
    /**
     * 设置道具打赏
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
        Map<String, String> signMap = LiveSignUtil.getSignMap(liveUpdateChannelGoodRequest);
        signMap.put("channelId", String.valueOf(liveUpdateChannelGoodRequest.getChannelId()));
        String liveUpdateChannelGoodResponse = this.postJsonBodyReturnOne(url, signMap, liveUpdateChannelGoodRequest,
                String.class);
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
        Map<String, String> signMap = LiveSignUtil.getSignMap(liveUpdateChannelCashRequest);
        signMap.put("channelId", String.valueOf(liveUpdateChannelCashRequest.getChannelId()));
        String liveUpdateChannelCashResponse = this.postJsonBodyReturnOne(url, signMap, liveUpdateChannelCashRequest,
                String.class);
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
    public LiveChannelDonateResponse getChannelDonate(LiveChannelDonateRequest liveChannelDonateRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_DONATE_GET_URL;
        LiveChannelDonateResponse liveChannelDonateResponse = this.getReturnOne(url, liveChannelDonateRequest,
                LiveChannelDonateResponse.class);
        return liveChannelDonateResponse;
    }
    
    /**
     * 设置频道微信分享信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/update-weixin-share/
     * @param liveUpdateChannelWxShareRequest 设置频道微信分享信息请求实体
     * @return 设置频道微信分享信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean updateChannelWxShare(LiveUpdateChannelWxShareRequest liveUpdateChannelWxShareRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.UPDATE_CHANNEL_WX_SHARE_URL;
        String liveUpdateChannelWxShareResponse = this.postFormBodyReturnOne(url, liveUpdateChannelWxShareRequest, String.class);
        return "success".equals(liveUpdateChannelWxShareResponse);
    }
    
    /**
     * 查询频道微信分享信息
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/get-weixin-share/
     * @param liveGetChannelWxShareRequest 查询频道微信分享信息请求实体
     * @return 查询频道微信分享信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveGetChannelWxShareResponse getChannelWxShare(LiveGetChannelWxShareRequest liveGetChannelWxShareRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.GET_CHANNEL_WX_SHARE_URL;
        return this.getReturnOne(url,liveGetChannelWxShareRequest,LiveGetChannelWxShareResponse.class);
    }
    
}
