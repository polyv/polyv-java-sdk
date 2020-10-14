package net.polyv.live.service.webView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.webView.LiveCreateChannelWriteListRequest;

/**
 * 直播Web观看页管理
 * @author: sadboy
 **/
public interface ILiveWebViewService {
    
    /**
     * 添加单个白名单
     * @param liveCreateChannelWriteListRequest 添加单个白名单请求体
     * @return 添加单个白名单返回体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    String createChannelWriteList(LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest)
            throws IOException, NoSuchAlgorithmException;
    
}