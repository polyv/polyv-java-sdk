package net.polyv.live.service.webview1.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.webview.LiveCreateChannelWriteListRequest;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.webview1.ILiveWebViewService;
/**
 * 直播Web观看页管理
 * @author: sadboy
 **/
public class LiveWebViewServiceImpl extends LiveBaseService implements ILiveWebViewService {
    
    /**
     * 添加单个白名单
     * @param liveCreateChannelWriteListRequest 添加单个白名单请求体
     * @return 添加单个白名单返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public String createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_WRITE_LIST_ADD_URL;
        String liveCreateChannelWriteListResponse = this.basePost(url,liveCreateChannelWriteListRequest,String.class);
        return liveCreateChannelWriteListResponse;
    }
    
}
