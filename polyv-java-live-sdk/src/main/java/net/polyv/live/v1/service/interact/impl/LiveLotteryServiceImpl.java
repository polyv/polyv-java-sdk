package net.polyv.live.v1.service.interact.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.v1.constant.LiveURL;
import net.polyv.live.v1.entity.interact.LiveDownloadLotteryDetailRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryResponse;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailRequest;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailResponse;
import net.polyv.live.v1.entity.interact.LiveSendChannelLikeRequest;
import net.polyv.live.v1.entity.interact.LiveSendChannelRewardMsgRequest;
import net.polyv.live.v1.entity.interact.LiveSetLotteryWinnerInfoRequest;
import net.polyv.live.v1.service.LiveBaseService;
import net.polyv.live.v1.service.interact.ILiveLotteryService;

/**
 * 抽奖管理
 * @author: sadboy
 **/
public class LiveLotteryServiceImpl  extends LiveBaseService implements ILiveLotteryService {
    
    /**
     * 获取频道抽奖记录列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/list-lottery/
     * @param liveListLotteryRequest 获取频道抽奖记录列表请求实体
     * @return 获取频道抽奖记录列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveListLotteryResponse listLottery(LiveListLotteryRequest liveListLotteryRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LOTTERY_LIST_GET_URL;
        return this.getReturnOne(url, liveListLotteryRequest,
                LiveListLotteryResponse.class);
        
    }
    
    /**
     * 获取频道单场抽奖的中奖记录
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/get-winner-detail/
     * @param liveLotteryWinnerDetailRequest 获取频道单场抽奖的中奖记录请求实体
     * @return 获取频道单场抽奖的中奖记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public LiveLotteryWinnerDetailResponse getLotteryWinnerDetail(
            LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_LOTTERY_WINNER_DETAIL_GET_URL;
        return this.getReturnOne(url,
                liveLotteryWinnerDetailRequest, LiveLotteryWinnerDetailResponse.class);
        
    }
    
    /**
     * 设置抽奖中奖者信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/tjzjxx/
     * @param liveSetLotteryWinnerInfoRequest 设置抽奖中奖者信息请求实体
     * @return 设置抽奖中奖者信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean setLotteryWinnerInfo(LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.CHANNEL_SET_LOTTERY_WINNER_INFO_URL;
        String liveSetLotteryWinnerInfoResponse = this.getReturnOne(url, liveSetLotteryWinnerInfoRequest, String.class);
        return "success".equals(liveSetLotteryWinnerInfoResponse);
    }
    
    /**
     * 导出频道单场抽奖的中奖记录
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/download-winner-detail/
     * @param liveDownloadLotteryDetailRequest 导出频道单场抽奖的中奖记录请求实体
     * @return 导出频道单场抽奖的中奖记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public byte[] downloadLotteryDetail(LiveDownloadLotteryDetailRequest liveDownloadLotteryDetailRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.DOWNLOAD_LOTTERY_WINNER_DETAIL_URL;
        return this.getReturnBinary(url,liveDownloadLotteryDetailRequest);
    }
    
    /**
     * 发送点赞
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/like/
     * @param liveSendChannelLikeRequest 发送点赞请求实体
     * @return 发送点赞返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer sendChannelLike(LiveSendChannelLikeRequest liveSendChannelLikeRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.getRealUrl(LiveURL.SEND_CHANNEL_LIKE_URL,liveSendChannelLikeRequest.getChannelId());
        return this.postFormBodyReturnOne(url,liveSendChannelLikeRequest,Integer.class);
    }
    
    /**
     * 发送打赏消息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/fsdsxx/
     * @param liveSendChannelRewardMsgRequest 发送打赏消息请求实体
     * @return 发送打赏消息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean sendChannelRewardMsg(LiveSendChannelRewardMsgRequest liveSendChannelRewardMsgRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = LiveURL.SEND_REWARD_MSG_URL;
        String liveSendChannelRewardMsgResponse = this.postFormBodyReturnOne(url,liveSendChannelRewardMsgRequest,String.class);
        return "0".equals(liveSendChannelRewardMsgResponse);
    }
    
}
