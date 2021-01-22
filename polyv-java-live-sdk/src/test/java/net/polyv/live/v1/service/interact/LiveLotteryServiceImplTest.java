package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.FileUtil;
import net.polyv.live.v1.entity.interact.LiveDownloadLotteryDetailRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryResponse;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailRequest;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailResponse;
import net.polyv.live.v1.entity.interact.LiveSendChannelLikeRequest;
import net.polyv.live.v1.entity.interact.LiveSendChannelRewardMsgRequest;
import net.polyv.live.v1.entity.interact.LiveSetLotteryWinnerInfoRequest;
import net.polyv.live.v1.service.BaseTest;
import net.polyv.live.v1.service.interact.impl.LiveLotteryServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * 抽奖管理
 * @author: sadboy
 **/
@Slf4j
public class LiveLotteryServiceImplTest extends BaseTest {
    
    
    /**
     * 测试获取频道抽奖记录列表
     * 描述：获取频道抽奖记录列表（通过直播端发起抽奖）
     * API地址：CHANNEL_LOTTERY_LIST_GET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testListLottery() throws Exception, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        try {
            liveListLotteryRequest.setChannelId(super.createChannel())
                    .setStartTime(super.getDate(1601481600000l))
                    .setEndTime(super.getDate(2021,1,21))
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListLotteryResponse = new LiveLotteryServiceImpl().listLottery(liveListLotteryRequest);
            Assert.assertNotNull(liveListLotteryResponse);
            if (liveListLotteryResponse != null) {
                //to do something ......
                log.debug("测试获取频道抽奖记录列表成功，{}", JSON.toJSONString(liveListLotteryResponse));
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
     * 测试获取频道单场抽奖的中奖记录
     * API地址：CHANNEL_LOTTERY_WINNER_DETAIL_GET_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetLotteryWinnerDetail() throws Exception, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        try {
            liveLotteryWinnerDetailRequest.setChannelId(super.createChannel())
                    .setLotteryId("fv3mao43u6")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveLotteryWinnerDetailResponse = new LiveLotteryServiceImpl().getLotteryWinnerDetail(
                    liveLotteryWinnerDetailRequest);
            Assert.assertNotNull(liveLotteryWinnerDetailResponse);
            if (liveLotteryWinnerDetailResponse != null) {
                //to do something ......
                log.debug("测试获取频道单场抽奖的中奖记录成功，{}", JSON.toJSONString(liveLotteryWinnerDetailResponse));
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
     * 测试设置抽奖中奖者信息
     * TODO 等待数据验证
     * 描述：用于提交中奖者填写的信息
     * 约束：2.只能成功保存一次观众中奖信息
     * 约束：3.中奖信息需在7天内提交保存，否则会失效
     * API地址：CHANNEL_SET_LOTTERY_WINNER_INFO_URL
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSetLotteryWinnerInfo() throws Exception, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        try {
            liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                    .setLotteryId("fv3mao43u6")
                    .setWinnerCode("wMpUjVSi")
                    .setViewerId("asdadsdas")
                    .setName("sadboy")
                    .setTelephone("18974718689")
                    .setReceiveInfo("[{\"field\":\"姓名\",\"value\":\"测试\"},{\"field\":\"手机\",\"value\":\"13412345678\"}]")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSetLotteryWinnerInfoResponse = new LiveLotteryServiceImpl().setLotteryWinnerInfo(
                    liveSetLotteryWinnerInfoRequest);
            Assert.assertNotNull(liveSetLotteryWinnerInfoResponse);
            if (liveSetLotteryWinnerInfoResponse) {
                //to do something ......
                log.debug("测试设置抽奖中奖者信息成功");
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
     * 测试导出频道单场抽奖的中奖记录
     * 描述：用于下载频道的单场抽奖的中奖记录
     * 返回：返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
     * API地址：DOWNLOAD_LOTTERY_WINNER_DETAIL_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testDownloadLotteryDetail() throws Exception, NoSuchAlgorithmException {
        LiveDownloadLotteryDetailRequest liveDownloadLotteryDetailRequest = new LiveDownloadLotteryDetailRequest();
        byte[] liveDownloadLotteryDetailResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoadLotteryWinner.xlsx";
            liveDownloadLotteryDetailRequest.setChannelId(createChannel())
                    .setLotteryId("fv3hogjmh3")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDownloadLotteryDetailResponse = new LiveLotteryServiceImpl().downloadLotteryDetail(
                    liveDownloadLotteryDetailRequest);
            Assert.assertNotNull(liveDownloadLotteryDetailResponse);
            if (liveDownloadLotteryDetailResponse != null) {
                FileUtil.writeFile(liveDownloadLotteryDetailResponse, path);
                //to do something ......
                log.debug("测试导出频道单场抽奖的中奖记录成功, 文件长度 {}", liveDownloadLotteryDetailResponse.length);
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
     * 测试发送点赞
     * 描述：用于实现用户自开发观看页点赞效果，通过调用接口可以进行点赞，默认每次请求都是一次点赞
     * 约束：2、接口内部进行了判断，每个观众的点赞间隔1s的限制，根据提交的观众ID来区分
     * 约束：3、如果有需求支持同时点多个赞，可提交非必填参数times，times最大不能超过30。*如果提交次数为n，则需n-1秒才能继续点赞
     * 约束：4、viewerId由调用端去进行区分用户即可
     * 返回：点赞数
     * API地址：SEND_CHANNEL_LIKE_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSendChannelLike() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelLikeRequest liveSendChannelLikeRequest = new LiveSendChannelLikeRequest();
        Integer liveSendChannelLikeResponse;
        try {
            liveSendChannelLikeRequest.setChannelId(createChannel())
                    .setViewerId(getRandomString(16))
                    .setTimes(13)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSendChannelLikeResponse = new LiveLotteryServiceImpl().sendChannelLike(liveSendChannelLikeRequest);
            Assert.assertNotNull(liveSendChannelLikeResponse);
            if (liveSendChannelLikeResponse != null) {
                //to do something ......
                log.debug("测试发送点赞成功,{}", JSON.toJSONString(liveSendChannelLikeResponse));
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
     * 测试发送打赏消息
     * 描述：用于发送打赏消息,请求成功后，服务器会向聊天室的用户广播打赏消息
     * 约束：viewerId需要是在线的viewerId
     * 返回：true为发送成功，false为发送失败
     * TODO 未测试通过，等后台回复如何获取在线viewerId
     * API地址：SEND_REWARD_MSG_URL
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testSkipSendChannelRewardMsg() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelRewardMsgRequest liveSendChannelRewardMsgRequest = new LiveSendChannelRewardMsgRequest();
        Boolean liveSendChannelRewardMsgResponse;
        try {
            liveSendChannelRewardMsgRequest.setChannelId(createChannel())
                    .setNickname("sadboy")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740,3965499425&fm=26&gp=0.jpg")
                    .setDonateType("cash")
                    .setContent("1")
                    .setGoodImage("https://s1.videocc.net/live-admin/img/icon-redpack-new.ae299535.png")
                    .setSessionId(null)
                    .setGoodNum("1")
                    .setNeedUserImage("N")
                    .setViewerId(getRandomString(16))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSendChannelRewardMsgResponse = new LiveLotteryServiceImpl().sendChannelRewardMsg(
                    liveSendChannelRewardMsgRequest);
            Assert.assertTrue(liveSendChannelRewardMsgResponse);
            if (liveSendChannelRewardMsgResponse) {
                //to do something ......
                log.debug("测试发送打赏消息成功");
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
     * 测试用例结束
     */
}
