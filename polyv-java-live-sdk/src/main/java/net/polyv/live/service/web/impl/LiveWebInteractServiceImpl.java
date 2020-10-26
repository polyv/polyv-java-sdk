package net.polyv.live.service.web.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import net.polyv.live.constant.LiveURL;
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
        signMap.put("channelId",liveUpdateChannelGoodRequest.getChannelId().toString());
        String liveUpdateChannelGoodResponse = this.basePostJson(url, signMap, liveUpdateChannelGoodRequest, String.class);
        return "true".equals(liveUpdateChannelGoodResponse);
    }
    
}
