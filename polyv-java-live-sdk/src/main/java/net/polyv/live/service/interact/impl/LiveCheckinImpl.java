package net.polyv.live.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinListRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.interact.ILiveCheckinService;

/**
 * 直播签到管理
 * @author: thomas
 **/
@Slf4j
public class LiveCheckinImpl extends LiveBaseService implements ILiveCheckinService {
    
    
    
    /**
     * 查询签到结果，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-questionnaire-detail/
     * @param liveCheckinListRequest 查询签到结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCheckinListResponse getCheckinListInfo(LiveCheckinListRequest liveCheckinListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_CHECKIN_LIST_URL;
        return super.baseGet(url,liveCheckinListRequest,LiveCheckinListResponse.class);
    }
}
