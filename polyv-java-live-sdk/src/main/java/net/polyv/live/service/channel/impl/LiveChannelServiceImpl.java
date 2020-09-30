package net.polyv.live.service.channel.impl;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.LiveChannelDetailRequest;
import net.polyv.live.entity.channel.LiveChannelRequest;
import net.polyv.live.entity.channel.LiveChannelResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelService;

/**
 * 直播频道管理
 * @author: thomas
 
 **/
@Slf4j
public class LiveChannelServiceImpl extends LiveBaseService implements ILiveChannelService {
    /**
     * 直播频道创建
     * @param liveChannelRequest  直播频道请求实体
     * @return 频道数据
     * @throws IOException 客户端和服务器读写异常
     */
    @Override
    public LiveChannelResponse createChannel( LiveChannelRequest liveChannelRequest  ) throws IOException {
        liveChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
        String url = LiveURL.CHANNEL_CREATE_URL;
        LiveChannelResponse liveChannelResponse = this.basePost(url, liveChannelRequest, LiveChannelResponse.class);
        return liveChannelResponse;
    }

    /**
     * 设置频道详情
     * 注意：设置前，请确认您的套餐是否包含对应场景
     * @param liveChannelDetailRequest 设置频道详情请求实体
     * @return  频道详情
     * @throws IOException 异常
     */
    @Override
    public String updateChannelDetail(LiveChannelDetailRequest liveChannelDetailRequest) throws IOException {
        //此处password字段与channelPasswd都表示频道密码，先做兼容
        if("channelPasswd".equals(liveChannelDetailRequest.getField())){
            liveChannelDetailRequest.setField("password");
        }
        String url = LiveURL.CHANNEL_DETAIL_SET_URL;
        return this.basePost(url, liveChannelDetailRequest, String.class);
    }


//    @Override
//    public LiveChannelResponse createChannel(LiveChannelRequest liveChannelRequest) throws IOException {
//        liveChannelRequest.setUserId(LiveGlobalConfig.USER_ID);
//        String url = LiveURL.CHANNEL_CREATE_URL;
//
//        LiveChannelResponse liveChannelResponse = null;
//        if(StringUtils.isBlank(liveChannelRequest.getRequestId())){
//            liveChannelRequest.setRequestId(LiveSignUtil.generateUUID());
//        }
//        liveChannelRequest.setAppId(LiveGlobalConfig.APP_ID);
//        Map<String, String> channelMap = MapUtil.objectToMap(liveChannelRequest);
//        LiveSignUtil.setLiveSign(channelMap, LiveGlobalConfig.APP_ID,LiveGlobalConfig.APP_SECRET);
//        String response = HttpUtil.sendPostDataByMap(url,channelMap, Consts.UTF_8.toString());
//        if(StringUtils.isNotBlank(response)){
//            LiveCommonResponse liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
//            if(liveCommonResponse.getCode()!=200){
//                String message = "创建直播频道错误，请求流水号："+liveChannelRequest.getRequestId()+" ,错误原因： "+liveCommonResponse.getMessage();
//                BusinessException exception=  new BusinessException(liveCommonResponse.getCode(),message);
//                log.error(message,exception);
//                throw exception;
//            }else{
//                liveChannelResponse = liveCommonResponse.parseData(LiveChannelResponse.class);
//            }
//        }else{
//            String message = "创建直播频道错误，请求流水号："+liveChannelRequest.getRequestId()+" ,错误原因： 服务器接口未返回任何数据";
//            BusinessException exception=  new BusinessException(LiveConstant.ERROR_CODE,message);
//            log.error(message,exception);
//            throw exception;
//        }
//        return liveChannelResponse;
//    }
}
