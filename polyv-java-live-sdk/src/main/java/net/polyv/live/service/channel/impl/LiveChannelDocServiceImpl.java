package net.polyv.live.service.channel.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.polyv.live.constant.LiveURL;
import net.polyv.live.entity.channel.doc.LiveChannelDocStatusRequest;
import net.polyv.live.entity.channel.doc.LiveChannelDocStatusResponse;
import net.polyv.live.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.entity.channel.doc.LiveDeleteChannelDocRequest;
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
        return this.baseGet(url, liveListChannelDocRequest,
                LiveListChannelDocResponse.class);
         
    }
    
    /**
     * 查询频道文档转换状态
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/wdgl/get-ppt-convert-status/
     * @param liveChannelDocStatusRequest 查询频道文档转换状态请求实体
     * @return 查询频道文档转换状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveChannelDocStatusResponse channelDocStatus(LiveChannelDocStatusRequest liveChannelDocStatusRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_DOC_STATUS_URL;
        List<LiveChannelDocStatusResponse.ChannelDocStatus> channelDocStatuses = this.baseGetReturnArray(url,
                liveChannelDocStatusRequest, LiveChannelDocStatusResponse.ChannelDocStatus.class);
        LiveChannelDocStatusResponse liveChannelDocStatusResponse = new LiveChannelDocStatusResponse();
        liveChannelDocStatusResponse.setChannelDocStatuses(channelDocStatuses);
        return liveChannelDocStatusResponse;
    }
    
    /**
     * 删除频道文档
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/wdgl/delete-document/
     * @param liveDeleteChannelDocRequest 删除频道文档请求实体
     * @return 删除频道文档返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteChannelDoc(LiveDeleteChannelDocRequest liveDeleteChannelDocRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_DOC_DELETE_URL;
        String liveDeleteChannelDocResponse = this.basePost(url, liveDeleteChannelDocRequest, String.class);
        return "".equals(liveDeleteChannelDocResponse);
    }
    
    /**
     * 上传频道文档
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbglgn/wdgl/upload-ppt-file/
     * @param liveCreateChannelDocRequest 上传频道文档请求实体
     * @return 上传频道文档返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveCreateChannelDocResponse createChannelDoc(LiveCreateChannelDocRequest liveCreateChannelDocRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CREATE_CHANNEL_DOC_URL;
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("file", liveCreateChannelDocRequest.getFile());
        return this.baseUploadFile(url,
                liveCreateChannelDocRequest, fileMap, LiveCreateChannelDocResponse.class);
        
    }
    
}
