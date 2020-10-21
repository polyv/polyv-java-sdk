package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoSortRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelPlaybackServiceImpl;

/**
 * @author: sadboy
 **/
@Slf4j
public class LiveChannelPlaybackImplTest extends BaseTest {
    
    /**
     * 测试查询频道录制视频信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(1951952)
                .setStartDate("2020-01-01")
                .setEndDate("2020-10-14")
                .setSessionId(null);
        LiveChannelVideoListResponse liveChannelVideoListResponse =
                new LiveChannelPlaybackServiceImpl().listChannelVideo(
                liveChannelVideoListRequest);
        Assert.assertNotNull(liveChannelVideoListResponse);
        if (liveChannelVideoListResponse != null) {
            //to do something ......
            log.debug("查询频道录制视频信息成功{}", JSON.toJSONString(liveChannelVideoListResponse));
        }
    }
    
    /**
     * 测试设置频道回放设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlaybackSetting() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        List<String> videoIds = listChannelVideoIds(channelId);
        LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest = new LiveChannelPlaybackSettingRequest();
        liveChannelPlaybackSettingRequest.setChannelId(channelId)
                .setPlaybackEnabled("Y")
                .setType("single")
                .setOrigin("playback")
                .setVideoId(videoIds.get(0));
        String liveChannelPlaybackSettingResponse = new LiveChannelPlaybackServiceImpl().channelPlaybackSetting(
                liveChannelPlaybackSettingRequest);
        Assert.assertNotNull(liveChannelPlaybackSettingResponse);
        if (liveChannelPlaybackSettingResponse != null) {
            //to do something ......
            log.debug("设置频道回放设置成功{}", JSON.toJSONString(liveChannelPlaybackSettingResponse));
        }
    }
    
    /**
     * 测试设置后台回放开关
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest = new LiveChannelPlaybackEnabledRequest();
        liveChannelPlaybackEnabledRequest.setChannelId(1951952).setPlayBackEnabled("Y");
        Integer liveChannelPlaybackEnabledResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledSetting(
                liveChannelPlaybackEnabledRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledResponse);
        if (liveChannelPlaybackEnabledResponse != null) {
            //to do something ......
            log.debug("测试设置后台回放开关成功{}", liveChannelPlaybackEnabledResponse);
        }
    }
    
    /**
     * 测试查询视频库列表
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelVideoLibrary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(1951952).setListType("playback");
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse =
                new LiveChannelPlaybackServiceImpl().listChannelVideoLibrary(
                liveListChannelVideoLibraryRequest);
        Assert.assertNotNull(liveListChannelVideoLibraryResponse);
        if (liveListChannelVideoLibraryResponse != null) {
            //to do something ......
            log.debug("测试查询视频库列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
        }
    }
    
    /**
     * 测试查询频道直播场次信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListChannelSessionInfo() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest = new LiveListChannelSessionInfoRequest();
        liveListChannelSessionInfoRequest.setChannelId(1951952)
                .setStartDate("2020-10-01")
                .setEndDate("2020-10-24")
                .setCurrentPage(1);
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse =
                new LiveChannelPlaybackServiceImpl().listChannelSessionInfo(
                liveListChannelSessionInfoRequest);
        Assert.assertNotNull(liveListChannelSessionInfoResponse);
        if (liveListChannelSessionInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
        }
    }
    
    /**
     * 测试查询频道的回放开关状态
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        liveChannelPlaybackEnabledInfoRequest.setChannelId(1951952);
        String liveChannelPlaybackEnabledInfoResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledInfo(
                liveChannelPlaybackEnabledInfoRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledInfoResponse);
        if (liveChannelPlaybackEnabledInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道的回放开关状态成功{}", liveChannelPlaybackEnabledInfoResponse);
        }
    }
    
    /**
     * 测试查询指定文件ID的录制文件信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelVideoOnly() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        String fileId = listChannelFileIds(channelId).get(0);
        LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
        liveChannelVideoOnlyRequest.setChannelId(1951952).setFileId(fileId);
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse =
                new LiveChannelPlaybackServiceImpl().channelVideoOnly(
                liveChannelVideoOnlyRequest);
        Assert.assertNotNull(liveChannelVideoOnlyResponse);
        if (liveChannelVideoOnlyResponse != null) {
            //to do something ......
            log.debug("测试查询指定文件ID的录制文件信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
        }
    }
    
    
    /**
     * 测试将点播中的视频添加到视频库
     * 注：点播视频得设置标签为频道号，多个用英文逗号分隔
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testAddChannelVideoPlayback() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest =
                new LiveCreateChannelVideoPlaybackRequest();
        liveCreateChannelVideoPlaybackRequest.setChannelId(1958888)
                .setVid("1b448be32340ff32f52c5db0f9e06a75_1")
                .setListType("vod");
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse =
                new LiveChannelPlaybackServiceImpl()
                .addChannelVideoPlayback(liveCreateChannelVideoPlaybackRequest);
        Assert.assertNotNull(liveCreateChannelVideoPlaybackResponse);
        if (liveCreateChannelVideoPlaybackResponse != null) {
            //to do something ......
            log.debug("测试将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
        }
    }
    
    /**
     * 测试设置视频库列表排序
     * TODO 对接未通过，暂时注释
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
//    public void testChannelVideoSort() throws IOException, NoSuchAlgorithmException {
//        List<String> videoIdList = listChannelVideoIds(1965681);//992d36fa40,f1574595e1
//        Collections.shuffle(videoIdList);
//        LiveChannelVideoSortRequest liveChannelVideoSortRequest = new LiveChannelVideoSortRequest();
//        liveChannelVideoSortRequest.setChannelId(1965681)
//                .setVideoIds(videoIdList)
//                .setListType("playback");
//        String liveChannelVideoSortResponse = new LiveChannelPlaybackServiceImpl().channelVideoSort(
//                liveChannelVideoSortRequest);
//        Assert.assertNotNull(liveChannelVideoSortResponse);
//        if ("".equals(liveChannelVideoSortResponse)) {
//            //to do something ......
//            log.debug("测试设置视频库列表排序成功{}", JSON.toJSONString(liveChannelVideoSortResponse));
//        }
//    }
    
}
