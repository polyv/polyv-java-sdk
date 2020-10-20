package net.polyv.live.service;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.service.channel.impl.LiveChannelPlaybackServiceImpl;

/**
 * @author: thomas
 **/
public class BaseTest {
    /**
     * 系统账号密钥配置
     */
    public BaseTest() {
        String appId = "frlr1zazn3";
        String appSecret = "5d5ade8f71f24bb9a2d1176cd607dd17";
        String userId = "1b448be323";
        LiveGlobalConfig.init(appId, userId, appSecret);
        System.out.println("--初始化完成--");
    }
    
    /**
     * 创建channel并返回channelId
     * @param liveChannelRequest
     * @return 频道id
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected Integer createChannel(LiveChannelRequest liveChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        return liveChannelResponse.getChannelId();
    }
    
    /**
     * 创建channel并返回channelId
     * @return 频道id
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected Integer createChannel() throws IOException, NoSuchAlgorithmException {
        Integer channelId = 1965681;
        return channelId;
//        LiveChannelRequest liveChannelRequest = new LiveChannelRequest().setName("test直播频道")
//                .setChannelPasswd("666888")
//                .setScene(LiveConstant.SceneType.PPT.getDesc());
//        return createChannel(liveChannelRequest);
    }
    
    /**
     * 删除频道，删除失败则断言出错
     * @param channelId 频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected void deleteChannel(Integer channelId) throws IOException, NoSuchAlgorithmException {
//        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest().setChannelId(channelId);
//        String deleteChannel = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
//        Assert.assertTrue("true".equals(deleteChannel));
    }
    
    /**
     * 创建子频道并返回子频道id
     * @param liveCreateSonChannelRequest
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String createSonChannel(LiveCreateSonChannelRequest liveCreateSonChannelRequest)
            throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelResponse liveCreateSonChannelResponse =
                new LiveChannelOperateServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        return liveCreateSonChannelResponse.getAccount();
    }
    
    /**
     * 创建子频道并返回子频道id
     * @param channelId 频道id
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String createSonChannel(Integer channelId) throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest().setChannelId(
                channelId)
                .setRole("Guest")
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        return createSonChannel(liveCreateSonChannelRequest);
    }
    
    /**
     * 查询channelId对应视频库的一个视频url
     * @param channelId
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String getChannelVideoFileUrl(Integer channelId) throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(1951952)
                .setStartDate("2020-01-01")
                .setEndDate("2020-10-14")
                .setSessionId(null);
        LiveChannelVideoListResponse liveChannelVideoListResponse =
                new LiveChannelPlaybackServiceImpl().listChannelVideo(
                liveChannelVideoListRequest);
        Assert.assertNotNull(liveChannelVideoListResponse);
        List<LiveChannelVideoListResponse.ChannelVedioInfo> channelVedioInfos =
                liveChannelVideoListResponse.getChannelVedioInfos();
        Assert.assertNotNull(channelVedioInfos);
        Assert.assertTrue(channelVedioInfos.size() > 0);
        return channelVedioInfos.get(0).getUrl();
    }
    
    /**
     * 获取回放videoIds
     * @param channelId 频道id
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected List<String> listChannelVideoIds(Integer channelId) throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(channelId).setListType("playback");
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse =
                new LiveChannelPlaybackServiceImpl().listChannelVideoLibrary(
                liveListChannelVideoLibraryRequest);
        Assert.assertNotNull(liveListChannelVideoLibraryResponse);
        List<LiveListChannelVideoLibraryResponse.ChannelVideoLibrary> contents =
                liveListChannelVideoLibraryResponse.getContents();
        int size = contents.size();
        Assert.assertTrue(size > 0);
        List<String> videoIds = new ArrayList<>(size);
        for (LiveListChannelVideoLibraryResponse.ChannelVideoLibrary temp : contents) {
            videoIds.add(temp.getVideoId());
        }
        return videoIds;
    }
    
    /**
     * 获取频道视频fileIds
     * @param channelId 频道id
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected List<String> listChannelFileIds(Integer channelId) throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(channelId)
                .setStartDate("2020-01-01")
                .setEndDate("2020-10-14")
                .setSessionId(null);
        LiveChannelVideoListResponse liveChannelVideoListResponse =
                new LiveChannelPlaybackServiceImpl().listChannelVideo(
                liveChannelVideoListRequest);
        Assert.assertNotNull(liveChannelVideoListResponse);
        List<LiveChannelVideoListResponse.ChannelVedioInfo> channelVedioInfos =
                liveChannelVideoListResponse.getChannelVedioInfos();
        int size = channelVedioInfos.size();
        Assert.assertTrue(size > 0);
        List<String> fileIds = new ArrayList<>(size);
        for (LiveChannelVideoListResponse.ChannelVedioInfo temp : channelVedioInfos) {
            fileIds.add(temp.getFileId());
        }
        return fileIds;
    }
    
}
