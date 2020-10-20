package net.polyv.live.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.interact.LiveCheckinListResponse;
import net.polyv.live.entity.interact.LiveCheckinListRequest;

/**
 * 直播签到管理
 * @author: thomas
 **/
public interface ILiveCheckinService {
    
    /**
     * 查询签到结果，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-questionnaire-detail/
     * @param liveCheckinListRequest 查询签到结果请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveCheckinListResponse getCheckinListInfo(LiveCheckinListRequest liveCheckinListRequest)
            throws IOException, NoSuchAlgorithmException;
     
}
