package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;

/**
 * 直播直播状态接口
 * @author: sadboy
 **/
public interface ILiveChannelDocService {
    
    /**
     * 获取频道文档列表
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/wdgl/get-ppt-list/
     * @param liveListChannelDocRequest 获取频道文档列表请求实体
     * @return 获取频道文档列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListChannelDocResponse listChannelDoc(LiveListChannelDocRequest liveListChannelDocRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
