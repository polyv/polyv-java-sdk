package net.polyv.live.service.channel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;
import net.polyv.live.entity.channel.playback.LiveChannelDefaultVideoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledInfoRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackEnabledRequest;
import net.polyv.live.entity.channel.playback.LiveChannelPlaybackSettingRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoListResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyRequest;
import net.polyv.live.entity.channel.playback.LiveChannelVideoOnlyResponse;
import net.polyv.live.entity.channel.playback.LiveChannelVideoSortRequest;
import net.polyv.live.entity.channel.playback.LiveConvertChannelVideoListAsyncRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackRequest;
import net.polyv.live.entity.channel.playback.LiveCreateChannelVideoPlaybackResponse;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelPlaybackVideoRequest;
import net.polyv.live.entity.channel.playback.LiveDeleteChannelVideoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelSessionInfoResponse;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryRequest;
import net.polyv.live.entity.channel.playback.LiveListChannelVideoLibraryResponse;
import net.polyv.live.entity.channel.playback.LiveMergeChannelVideoAsyncRequest;
import net.polyv.live.service.BaseTest;
import net.polyv.live.service.channel.impl.LiveChannelPlaybackServiceImpl;
import net.polyv.live.util.LiveSignUtil;

/**
 * 录制回放
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
        LiveChannelVideoListResponse liveChannelVideoListResponse;
        try {
            liveChannelVideoListRequest.setChannelId("1951952")
                    .setStartDate("2020-01-01")
                    .setEndDate("2020-10-14")
                    .setSessionId(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoListResponse = new LiveChannelPlaybackServiceImpl().listChannelVideo(
                    liveChannelVideoListRequest);
            Assert.assertNotNull(liveChannelVideoListResponse);
            if (liveChannelVideoListResponse != null) {
                //to do something ......
                log.debug("查询频道录制视频信息成功{}", JSON.toJSONString(liveChannelVideoListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置频道回放设置
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlaybackSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest;
        Boolean liveChannelPlaybackSettingResponse;
        try {
            String channelId = createChannel();
            List<String> videoIds = listChannelVideoIds(channelId);
            liveChannelPlaybackSettingRequest = new LiveChannelPlaybackSettingRequest();
            liveChannelPlaybackSettingRequest.setChannelId(channelId)
                    .setPlaybackEnabled("Y")
                    .setType("single")
                    .setOrigin("playback")
                    .setVideoId(videoIds.get(0))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackSettingResponse = new LiveChannelPlaybackServiceImpl().channelPlaybackSetting(
                    liveChannelPlaybackSettingRequest);
            Assert.assertNotNull(liveChannelPlaybackSettingResponse);
            if (liveChannelPlaybackSettingResponse) {
                //to do something ......
                log.debug("设置频道回放设置成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置后台回放开关
     * 返回：成功返回频道号
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest = new LiveChannelPlaybackEnabledRequest();
        String liveChannelPlaybackEnabledResponse;
        try {
            liveChannelPlaybackEnabledRequest.setChannelId(createChannel())
                    .setPlayBackEnabled("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledSetting(
                    liveChannelPlaybackEnabledRequest);
            Assert.assertNotNull(liveChannelPlaybackEnabledResponse);
            if (liveChannelPlaybackEnabledResponse != null) {
                //to do something ......
                log.debug("测试设置后台回放开关成功{}", liveChannelPlaybackEnabledResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
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
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse;
        try {
            liveListChannelVideoLibraryRequest.setChannelId("1951952")
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelVideoLibraryResponse = new LiveChannelPlaybackServiceImpl().listChannelVideoLibrary(
                    liveListChannelVideoLibraryRequest);
            Assert.assertNotNull(liveListChannelVideoLibraryResponse);
            if (liveListChannelVideoLibraryResponse != null) {
                //to do something ......
                log.debug("测试查询视频库列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
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
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse;
        try {
            liveListChannelSessionInfoRequest.setChannelId(createChannel())
                    .setStartDate("2020-10-01")
                    .setEndDate("2020-10-24")
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelSessionInfoResponse = new LiveChannelPlaybackServiceImpl().listChannelSessionInfo(
                    liveListChannelSessionInfoRequest);
            Assert.assertNotNull(liveListChannelSessionInfoResponse);
            if (liveListChannelSessionInfoResponse != null) {
                //to do something ......
                log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询频道的回放开关状态
     * 返回：Y为开启，N为关闭
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelPlayBackEnabledInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        String liveChannelPlaybackEnabledInfoResponse;
        try {
            liveChannelPlaybackEnabledInfoRequest.setChannelId(createChannel())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledInfoResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledInfo(
                    liveChannelPlaybackEnabledInfoRequest);
            Assert.assertNotNull(liveChannelPlaybackEnabledInfoResponse);
            if ("Y".equals(liveChannelPlaybackEnabledInfoResponse)) {
                //to do something ......
                log.debug("测试查询频道的回放开关状态成功{}", liveChannelPlaybackEnabledInfoResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试查询指定文件ID的录制文件信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelVideoOnly() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse;
        try {
            String channelId = createChannel();
            String fileId = listChannelFileIds(channelId).get(0);
            liveChannelVideoOnlyRequest.setChannelId(channelId)
                    .setFileId(fileId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoOnlyResponse = new LiveChannelPlaybackServiceImpl().channelVideoOnly(
                    liveChannelVideoOnlyRequest);
            Assert.assertNotNull(liveChannelVideoOnlyResponse);
            if (liveChannelVideoOnlyResponse != null) {
                //to do something ......
                log.debug("测试查询指定文件ID的录制文件信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    
    /**
     * 测试将点播中的视频添加到视频库
     * 约束：2、点播视频得设置标签为频道号，多个用英文逗号分隔
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testAddChannelVideoPlayback() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest =
                new LiveCreateChannelVideoPlaybackRequest();
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse;
        try {
            liveCreateChannelVideoPlaybackRequest.setChannelId(getAloneChannelId())
                    .setVid("1b448be32340ff32f52c5db0f9e06a75_1")
                    .setListType("vod")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelVideoPlaybackResponse = new LiveChannelPlaybackServiceImpl().addChannelVideoPlayback(
                    liveCreateChannelVideoPlaybackRequest);
            Assert.assertNotNull(liveCreateChannelVideoPlaybackResponse);
            if (liveCreateChannelVideoPlaybackResponse != null) {
                //to do something ......
                log.debug("测试将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置视频库列表排序
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelVideoSort() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoSortRequest liveChannelVideoSortRequest = new LiveChannelVideoSortRequest();
        Boolean liveChannelVideoSortResponse;
        try {
            List<String> videoIdList = listChannelVideoIds("1965681");//992d36fa40,f1574595e1
            Collections.shuffle(videoIdList);
            liveChannelVideoSortRequest.setChannelId("1965681")
                    .setVideoIds(videoIdList)
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoSortResponse = new LiveChannelPlaybackServiceImpl().channelVideoSort(
                    liveChannelVideoSortRequest);
            Assert.assertNotNull(liveChannelVideoSortResponse);
            if (liveChannelVideoSortResponse) {
                //to do something ......
                log.debug("测试设置视频库列表排序成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试设置视频库列表的默认视频
     * 返回：true为设置成功，false为设置失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testChannelDefaultVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelDefaultVideoRequest liveChannelDefaultVideoRequest = new LiveChannelDefaultVideoRequest();
        Boolean liveChannelDefaultVideoResponse;
        try {
            liveChannelDefaultVideoRequest.setChannelId("1965681")
                    .setVideoId("f1574595e1")
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelDefaultVideoResponse = new LiveChannelPlaybackServiceImpl().channelDefaultVideo(
                    liveChannelDefaultVideoRequest);
            Assert.assertNotNull(liveChannelDefaultVideoResponse);
            if (liveChannelDefaultVideoResponse) {
                //to do something ......
                log.debug("测试设置视频库列表的默认视频成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试异步合并直播录制文件
     * 返回：true为提交成功，false为提交失败，具体合并是否成功以回调为准
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testMergeChannelVideoAsync() throws IOException, NoSuchAlgorithmException {
        LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest = new LiveMergeChannelVideoAsyncRequest();
        Boolean liveMergeChannelVideoAsyncResponse;
        try {
            liveMergeChannelVideoAsyncRequest.setChannelId("1951952")
                    .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                    .setFileName("测试合并-可删除")
                    .setCallbackUrl(null)
                    .setAutoConvert("Y")
                    .setMergeMp4("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveMergeChannelVideoAsyncResponse = new LiveChannelPlaybackServiceImpl().mergeChannelVideoAsync(
                    liveMergeChannelVideoAsyncRequest);
            Assert.assertNotNull(liveMergeChannelVideoAsyncResponse);
            if (liveMergeChannelVideoAsyncResponse) {
                //to do something ......
                log.debug("测试异步合并直播录制文件,具体是否成功以回调为准");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试异步批量转存录制文件到点播
     * 返回：true为提交成功，false为提交失败，具体转存是否成功以回调为准
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testConvertChannelVideoListAsync() throws IOException, NoSuchAlgorithmException {
        LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest =
                new LiveConvertChannelVideoListAsyncRequest();
        Boolean liveConvertChannelVideoResponse;
        try {
            liveConvertChannelVideoListAsyncRequest.setChannelId("1951952")
                    .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                    .setFileName("删除-直播录制转点播")
                    .setCataId(null)
                    .setCallbackUrl(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveConvertChannelVideoResponse = new LiveChannelPlaybackServiceImpl().convertChannelVideoListAsync(
                    liveConvertChannelVideoListAsyncRequest);
            Assert.assertNotNull(liveConvertChannelVideoResponse);
            if (liveConvertChannelVideoResponse) {
                //to do something ......
                log.debug("测试异步批量转存录制文件到点播,具体是否成功以回调为准");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除直播暂存中的录制文件
     * 返回：true为删除成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest = new LiveDeleteChannelVideoRequest();
        Boolean liveDeleteChannelVideoResponse;
        try {
            liveDeleteChannelVideoRequest.setChannelId("1951952")
                    .setStartTime("20201016111234")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelVideo(
                    liveDeleteChannelVideoRequest);
            Assert.assertNotNull(liveDeleteChannelVideoResponse);
            if (liveDeleteChannelVideoResponse) {
                //to do something ......
                log.debug("测试删除直播暂存中的录制文件");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试删除视频库列表中的视频
     * 返回:true为删除成功，false为删除失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDeleteChannelPlaybackVideo() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest =
                new LiveDeleteChannelPlaybackVideoRequest();
        Boolean liveDeleteChannelPlaybackVideoResponse;
        try {
            String channelId = "1951952";
            String videoId = "07f5bbeb67";
            liveDeleteChannelPlaybackVideoRequest.setChannelId(channelId)
                    .setVideoId(videoId)
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelPlaybackVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelPlaybackVideo(
                    liveDeleteChannelPlaybackVideoRequest);
            Assert.assertNotNull(liveDeleteChannelPlaybackVideoResponse);
            if (liveDeleteChannelPlaybackVideoResponse) {
                //to do something ......
                log.debug("测试删除视频库列表中的视频成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
}
