package net.polyv.live.v1.service.interact;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.v1.entity.interact.LiveDownloadLotteryDetailRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryRequest;
import net.polyv.live.v1.entity.interact.LiveListLotteryResponse;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailRequest;
import net.polyv.live.v1.entity.interact.LiveLotteryWinnerDetailResponse;
import net.polyv.live.v1.entity.interact.LiveSendChannelLikeRequest;
import net.polyv.live.v1.entity.interact.LiveSendChannelRewardMsgRequest;
import net.polyv.live.v1.entity.interact.LiveSetLotteryWinnerInfoRequest;

/**
 * @author: sadboy
 */
public interface ILiveLotteryService {
    
    /**
     * 获取频道抽奖记录列表
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/list-lottery/
     * @param liveListLotteryRequest 获取频道抽奖记录列表请求实体
     * @return 获取频道抽奖记录列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveListLotteryResponse listLottery(LiveListLotteryRequest liveListLotteryRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 获取频道单场抽奖的中奖记录
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/get-winner-detail/
     * @param liveLotteryWinnerDetailRequest 获取频道单场抽奖的中奖记录请求实体
     * @return 获取频道单场抽奖的中奖记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    LiveLotteryWinnerDetailResponse getLotteryWinnerDetail(
            LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置抽奖中奖者信息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/tjzjxx/
     * @param liveSetLotteryWinnerInfoRequest 设置抽奖中奖者信息请求实体
     * @return 设置抽奖中奖者信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean setLotteryWinnerInfo(LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 导出频道单场抽奖的中奖记录
     * API地址：https://dev.polyv.net/2020/liveproduct/l-api/zbhd/download-winner-detail/
     * @param liveDownloadLotteryDetailRequest 导出频道单场抽奖的中奖记录请求实体
     * @return 导出频道单场抽奖的中奖记录返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    byte[] downloadLotteryDetail(LiveDownloadLotteryDetailRequest liveDownloadLotteryDetailRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 发送点赞
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/zbhd/like/
     * @param liveSendChannelLikeRequest 发送点赞请求实体
     * @return 发送点赞返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer sendChannelLike(LiveSendChannelLikeRequest liveSendChannelLikeRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 发送打赏消息
     * API地址：https://dev.polyv.net/2019/liveproduct/l-api/zbhd/fsdsxx/
     * @param liveSendChannelRewardMsgRequest 发送打赏消息请求实体
     * @return 发送打赏消息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean sendChannelRewardMsg(LiveSendChannelRewardMsgRequest liveSendChannelRewardMsgRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
