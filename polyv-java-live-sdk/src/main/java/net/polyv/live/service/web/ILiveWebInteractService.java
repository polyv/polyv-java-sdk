package net.polyv.live.service.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.live.entity.web.interact.LiveUpdateChannelCashRequest;
import net.polyv.live.entity.web.interact.LiveUpdateChannelGoodRequest;

/**
 * @author: sadboy
 **/
public interface ILiveWebInteractService {
    
    /**
     * 设置道具打赏
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/updategood/
     * @param liveUpdateChannelGoodRequest 设置道具打赏请求实体
     * @return 设置道具打赏返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelGood(LiveUpdateChannelGoodRequest liveUpdateChannelGoodRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 设置现金打赏
     * API地址：https://dev.polyv.net/2018/liveproduct/l-api/szgkygg/ymhd/updatecash/
     * @param liveUpdateChannelCashRequest 设置现金打赏请求实体
     * @return 设置现金打赏返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean updateChannelCash(LiveUpdateChannelCashRequest liveUpdateChannelCashRequest)
            throws IOException, NoSuchAlgorithmException;
    
}
