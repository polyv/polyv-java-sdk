package net.polyv.live.v1.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.interact.LiveCheckinListRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinListResponse;
import net.polyv.live.v1.entity.interact.LiveCheckinMetadataBySessionIdRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinMetadataBySessionIdResponse;
import net.polyv.live.v1.entity.interact.LiveCheckinRequest;
import net.polyv.live.v1.entity.interact.LiveCheckinResponse;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.interact.ILiveCheckinService;

/**
 * 直播签到管理
 * @author: thomas
 **/
@Slf4j

public class LiveCheckinServiceImpl extends LiveBaseService
        implements ILiveCheckinService {
    
    
    
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
    /**
     * 查询指定签到ID的签到记录，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-checkin/
     * @param liveCheckinRequest 查询指定签到ID的签到记录请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveCheckinResponse> getCheckinInfoById(LiveCheckinRequest liveCheckinRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_CHECKIN_BY_ID_URL;
        return super.baseGetReturnArray(url,liveCheckinRequest,LiveCheckinResponse.class);
    }
    /**
     * 依据指定直播场次sessionId查询签到场次信息，API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/get-checkin-list-by-sessionid/
     * @param liveCheckinMetadataBySessionIdRequest 依据指定直播场次sessionId查询签到场次信息请求实体
     * @return 响应实体
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<LiveCheckinMetadataBySessionIdResponse> getCheckinMetadataBySessionId(
            LiveCheckinMetadataBySessionIdRequest liveCheckinMetadataBySessionIdRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_CHECKIN_METADATA_BY_SESSIONID_URL;
        return super.baseGetReturnArray(url,liveCheckinMetadataBySessionIdRequest,LiveCheckinMetadataBySessionIdResponse.class);
    }
}
