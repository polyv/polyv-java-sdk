package net.polyv.live.service.channel.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.doc.LiveListChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveListChannelDocResponse;
import net.polyv.live.service.LiveBaseService;
import net.polyv.live.service.channel.ILiveChannelDocService;

/**
 * 直播文档管理实现类
 * @author: sadboy
 **/
public class LiveChannelDocServiceImpl extends LiveBaseService implements ILiveChannelDocService {
    
    /**
     * 获取频道文档列表
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/wdgl/get-ppt-list/
     * @param liveListChannelDocRequest 获取频道文档列表请求实体
     * @return 获取频道文档列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListChannelDocResponse listChannelDoc(LiveListChannelDocRequest liveListChannelDocRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_DOC_LIST_URL;
        LiveListChannelDocResponse liveListChannelDocResponse = this.baseGet(url, liveListChannelDocRequest,
                LiveListChannelDocResponse.class);
        return liveListChannelDocResponse;
    }
    
}
