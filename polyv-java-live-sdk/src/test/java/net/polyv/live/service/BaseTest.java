package net.polyv.live.service;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.config.InitConfig;
import net.polyv.live.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveCreateSonChannelResponse;
import net.polyv.live.entity.channel.operate.LiveDeleteSonChannelRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListRequest;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoListResponse;
import net.polyv.live.entity.channel.operate.LiveSonChannelInfoResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class BaseTest {
    /**
     * 系统账号密钥配置
     */
    public BaseTest() {
        InitConfig.initPolyvLive();
    }
    
    /**
     * 创建channel并返回channelId
     * @param liveChannelRequest
     * @return 频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected String createChannel(LiveChannelRequest liveChannelRequest) throws IOException, NoSuchAlgorithmException {
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        return liveChannelResponse.getChannelId();
    }
    
    /**
     * 创建channel并返回channelId
     * @return 频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected String createChannel() {
        String channelId = "1965681";
        return channelId;
//        LiveChannelRequest liveChannelRequest = new LiveChannelRequest().setName("test直播频道")
//                .setChannelPasswd("666888")
//                .setScene(LiveConstant.SceneType.PPT.getDesc());
//        return createChannel(liveChannelRequest);
    }
    
    /**
     * 获取测试纯视频类型频道id
     * @return
     */
    protected String getAloneChannelId() {
        return "1958888";
    }
    
    /**
     * 删除频道，删除失败则断言出错
     * @param channelId 频道号
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    protected void deleteChannel(String channelId) throws IOException, NoSuchAlgorithmException {
//        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest().setChannelId(channelId);
//        String deleteChannel = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
//        Assert.assertTrue("true".equals(deleteChannel));
    }
    
    /**
     * 创建子频道并返回子频道号
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
     * 创建子频道并返回子频道号
     * @param channelId 频道号
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String createSonChannel(String channelId) throws IOException, NoSuchAlgorithmException {
        return "00111965681";
//        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
//        liveCreateSonChannelRequest.setChannelId(channelId)
//                .setRole("Guest")
//                .setNickname("sadboy")
//                .setActor("教授")
//                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
//                .setRequestId(LiveSignUtil.generateUUID());
//        return createSonChannel(liveCreateSonChannelRequest);
    }
    
    /**
     * 获取需要删除的子频道id
     * @return
     */
    protected List<String> getDelSonChannelIds() {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveSonChannelInfoListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfoList(
                    liveSonChannelInfoListRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
                List<String> sonChannelIds = new ArrayList<>();
                for (LiveSonChannelInfoResponse temp : liveSonChannelInfoResponse.getSonChannelInfos()) {
                    sonChannelIds.add(temp.getAccount());
                }
                return sonChannelIds;
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
        }
        return new ArrayList<>();
    }
    
    /**
     * 删除子频道
     * @param sonChannelId
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected void deleteSonChannel(String sonChannelId) throws IOException, NoSuchAlgorithmException {
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        Boolean liveDeleteSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            
            liveDeleteSonChannelRequest.setChannelId(channelId)
                    .setAccount(sonChannelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteSonChannelResponse = new LiveChannelOperateServiceImpl().deleteSonChannel(
                    liveDeleteSonChannelRequest);
            Assert.assertNotNull(liveDeleteSonChannelResponse);
            if (liveDeleteSonChannelResponse) {
                //to do something ......
                log.debug("测试删除子频道成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 查询channelId对应视频库的一个视频url
     * @param channelId
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected String getChannelVideoFileUrl(String channelId) throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId("1951952")
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
     * @param channelId 频道号
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected List<String> listChannelVideoIds(String channelId) throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(channelId)
                .setListType("playback")
                .setRequestId(LiveSignUtil.generateUUID());
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
     * @param channelId 频道号
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected List<String> listChannelFileIds(String channelId) throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(channelId)
                .setStartDate("2020-01-01")
                .setEndDate("2020-11-11")
                .setSessionId(null)
                .setRequestId(LiveSignUtil.generateUUID());
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
